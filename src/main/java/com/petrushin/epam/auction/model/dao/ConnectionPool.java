package com.petrushin.epam.auction.model.dao;

import com.petrushin.epam.auction.exceptions.ConnectionPoolException;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {

    private static final String DB_PROPS = "db";
    private static final String JDBC_DRIVER = "jdbc_driver";
    private static final String JDBC_DB_URL = "jdbc_db_url";
    private static final String JDBC_USER = "jdbc_user";
    private static final String JDBC_PASSWORD = "jdbc_password";
    private static final String MAX_ACTIVE = "jdbc_max_active_connections";

    public static Connection getConnection() throws ConnectionPoolException {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(DB_PROPS);
            String driverValue = bundle.getString(JDBC_DRIVER);
            Class.forName(driverValue);

            GenericObjectPool gPool = new GenericObjectPool();
            String maxActiveValue = bundle.getString(MAX_ACTIVE);
            int maxActive = Integer.parseInt(maxActiveValue);
            gPool.setMaxActive(maxActive);

            String urlValue = bundle.getString(JDBC_DB_URL);
            String userValue = bundle.getString(JDBC_USER);
            String passwordValue = bundle.getString(JDBC_PASSWORD);
            ConnectionFactory cFactory = new DriverManagerConnectionFactory(urlValue, userValue, passwordValue);

            PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(cFactory,
                    gPool, null, null, false, true);
            PoolingDataSource poolingDataSource = new PoolingDataSource(gPool);
            Connection connection = poolingDataSource.getConnection();

            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException(
                    "Error with connection pool " + e.getMessage(), e);
        }

    }


}
