package com.petrushin.dao;

import com.petrushin.builder.AbstractBuilder;
import com.petrushin.builder.exceptions.AbstractBuilderException;
import com.petrushin.dao.exception.AbstractDAOException;
import com.petrushin.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T> {

    private static final Logger LOGGER =
            LogManager.getLogger(AbstractDAO.class);

    protected AbstractBuilder<T> builder;

    public AbstractDAO(AbstractBuilder<T> builder) {
        this.builder = builder;
    }

    protected T findById(int id, String query)
            throws AbstractDAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        T t = null;
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                t = builder.createEntity(resultSet);
            }
        } catch (SQLException | AbstractBuilderException
                | ConnectionPoolException e) {
            throw new AbstractDAOException(
                    "Error with findByID operation" + e.getMessage());
        } finally {
            closeAll(connection, statement);
        }
        return t;
    }

    protected List<T> getAll(String query)
            throws AbstractDAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        List<T> list;
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            list = builder.createEntityList(resultSet);
        } catch (SQLException | ConnectionPoolException
                | AbstractBuilderException e) {
            throw new AbstractDAOException(
                    "Error with getAll operation" + e.getMessage());
        } finally {
            closeAll(connection, statement);
        }
        return list;
    }

    protected boolean add(T t, String query)
            throws AbstractDAOException {
       return transactionalOperation(t, query);

    }

    protected boolean delete(int id, String query)
            throws AbstractDAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(true);
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowCountChanged = statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(false);
            return rowCountChanged == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new AbstractDAOException(
                    "Error with delete operation " + e.getMessage());
        } finally {
            closeAll(connection, statement);
        }
    }

    protected boolean update(T t, String query)
            throws AbstractDAOException {
       return transactionalOperation(t, query);
    }

    protected boolean transactionalOperation(T t, String query)
            throws AbstractDAOException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(query);
            builder.initStatement(t, statement);
            int rowCountChanged = statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return rowCountChanged == 1;
        } catch (ConnectionPoolException | AbstractBuilderException | SQLException e) {
            rollback(connection);
            throw new AbstractDAOException("Transaction Operation exception" + e.getMessage());
        }
    }

    protected void closeAll(Connection connection,
                            PreparedStatement statement)
            throws AbstractDAOException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new AbstractDAOException(
                        "Error with statement close", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new AbstractDAOException(
                        "Error with Connection close", e);
            }
        }
    }


    protected void rollback(Connection connection)
            throws AbstractDAOException {
        if (connection != null) {
            try {
                connection.rollback();
                LOGGER.error("Connection was rollbacked");
            } catch (SQLException ex) {
                throw new AbstractDAOException(
                        "Transaction rollback error " + ex.getMessage());
            }
        }
    }

}
