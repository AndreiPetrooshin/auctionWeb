package com.petrushin.dao;

import com.petrushin.creator.Creator;
import com.petrushin.exceptions.ConnectionPoolException;
import com.petrushin.exceptions.CreatorException;
import com.petrushin.exceptions.EntityDAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T> implements GenericDAO<T> {

    private static final Logger LOGGER =
            LogManager.getLogger(AbstractDAO.class);

    protected Creator<T> creator;

    public AbstractDAO(Creator<T> creator) {
        this.creator = creator;
    }

    protected T getByPK(Long id, String query)
            throws EntityDAOException {
        T t = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                t = creator.createEntity(resultSet);
            }
        } catch (SQLException | CreatorException
                | ConnectionPoolException e) {
            throw new EntityDAOException("Get by PK - " + id + " error. " + e.getMessage(), e);
        }
        return t;
    }

    protected List<T> getAll(String query)
            throws EntityDAOException {
        List<T> list;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            list = creator.createEntityList(resultSet);
        } catch (SQLException | ConnectionPoolException
                | CreatorException e) {
            throw new EntityDAOException("Get ALL error. " + e.getMessage(), e);
        }
        return list;
    }

    protected boolean save(T t, String query)
            throws EntityDAOException {
        return transactionalOperation(t, query);

    }

    protected boolean delete(Long id, String query)
            throws EntityDAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            statement.setLong(1, id);
            int rowCountChanged = statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return rowCountChanged == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new EntityDAOException("Delete operation error. " + e.getMessage(), e);
        }
    }

    protected boolean update(T t, String query)
            throws EntityDAOException {
        return transactionalOperation(t, query);
    }


    public void rollback(Connection connection)
            throws EntityDAOException {
        if (connection != null) {
            try {
                connection.rollback();
                LOGGER.error("Connection was rollbacked");
            } catch (SQLException e) {
                throw new EntityDAOException(
                        "Transaction rollback error. " + e.getMessage(), e);
            }
        }
    }


    private boolean transactionalOperation(T t, String query)
            throws EntityDAOException {
        Connection connectionToRollBack = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            connectionToRollBack = connection;
            connection.setAutoCommit(false);
            prepareStatement(t, statement);
            int rowCountChanged = statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return rowCountChanged == 1;
        } catch (ConnectionPoolException | CreatorException | SQLException e) {
            rollback(connectionToRollBack);
            throw new EntityDAOException(e.getMessage(), e);
        }
    }

    public abstract void prepareStatement(T t, PreparedStatement statement)
            throws CreatorException;

}
