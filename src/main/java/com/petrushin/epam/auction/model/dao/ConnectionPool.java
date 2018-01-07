package com.petrushin.epam.auction.model.dao;

import com.petrushin.epam.auction.exceptions.ConnectionPoolException;
import com.petrushin.epam.auction.util.ResourceReaderUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton class witch creates pool of connections
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class ConnectionPool {

    /**
     * Property keys with DB connection info
     */
    private static final String DB_PROPS = "db";
    private static final String JDBC_DRIVER = "jdbc_driver";
    private static final String JDBC_DB_URL = "jdbc_db_url";
    private static final String JDBC_USER = "jdbc_user";
    private static final String JDBC_PASSWORD = "jdbc_password";
    private static final String MAX_ACTIVE = "jdbc_max_active_connections";
    private static final int FIRST_CONNECTION = 0;


    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Lock instanceLock = new ReentrantLock();
    private static Lock connectionLock = new ReentrantLock();

    private static ConnectionPool instance;

    /**
     * List with available connections.
     */
    private static List<Connection> availableConnections = new ArrayList<>();


    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            instanceLock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    initializeConnectionPool();
                    isCreated.compareAndSet(false, true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    /**
     * Returns available connection.
     */
    public Connection getConnection() {
        connectionLock.lock();
        Connection connection = null;
        if (availableConnections.size() > 0) {
            connection = availableConnections.get(FIRST_CONNECTION);
            availableConnections.remove(FIRST_CONNECTION);
        }
        connectionLock.unlock();
        return connection;
    }

    /**
     * Returns connection back to connection pool.
     */
    public void returnConnection(Connection connection) {
        connectionLock.lock();
        availableConnections.add(connection);
        connectionLock.unlock();
    }

    /**
     * Creates new connection for connection pool.
     */
    private static Connection createNewConnectionForPool() {
        try {
            String driverNameValue = ResourceReaderUtil.getValue(DB_PROPS, JDBC_DRIVER);
            Class.forName(driverNameValue);

            String url = ResourceReaderUtil.getValue(DB_PROPS, JDBC_DB_URL);
            String name = ResourceReaderUtil.getValue(DB_PROPS, JDBC_USER);
            String password = ResourceReaderUtil.getValue(DB_PROPS, JDBC_PASSWORD);
            return DriverManager.getConnection(url, name, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException();
        }
    }

    /**
     * Fills the connection pool.
     */
    private static void initializeConnectionPool() {
        while (!checkIfConnectionPoolIsFull()) {
            availableConnections.add(createNewConnectionForPool());
        }
    }


    private static synchronized boolean checkIfConnectionPoolIsFull() {
        String maxConnectionsValue = ResourceReaderUtil.getValue(DB_PROPS, MAX_ACTIVE);
        int MAX_POOL_SIZE = Integer.parseInt(maxConnectionsValue);
        return availableConnections.size() >= MAX_POOL_SIZE;
    }


}
