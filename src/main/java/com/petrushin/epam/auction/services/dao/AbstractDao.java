package com.petrushin.epam.auction.services.dao;

import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.creator.Creator;
import com.petrushin.epam.auction.services.domain.Identified;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This class implements {@link GenericDao} and implementing
 * basic methods.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public abstract class AbstractDao<T extends Identified> implements GenericDao<T> {

    private static final Logger LOGGER =
            LogManager.getLogger(AbstractDao.class);

    protected Creator<T> creator;

    public AbstractDao(Creator<T> creator) {
        this.creator = creator;
    }


    protected T getByPK(Long id, String query)
            throws EntityDAOException {
        T t = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                t = creator.createEntity(resultSet);
            }
            return t;
        } catch (SQLException | CreatorException e) {
            throw new EntityDAOException("Get by PK - " + id + " error. " + e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    protected List<T> getAll(String query)
            throws EntityDAOException {
        List<T> list;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            list = creator.createEntityList(resultSet);
            return list;
        } catch (SQLException | CreatorException e) {
            throw new EntityDAOException("Get ALL error. " + e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    protected boolean save(T t, String query)
            throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            prepareStatementForInsert(t, statement);
            int rowCountChanged = statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return rowCountChanged == 1;
        } catch (SQLException e) {
            rollback(connection);
            throw new EntityDAOException(e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

    }

    protected boolean delete(Long id, String query)
            throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            statement.setLong(1, id);
            int rowCountChanged = statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return rowCountChanged == 1;
        } catch (SQLException e) {
            throw new EntityDAOException("Delete operation error. " + e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    protected boolean update(T t, String query)
            throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            prepareStatementForUpdate(t, statement);
            int rowCountChanged = statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return rowCountChanged == 1;
        } catch (SQLException e) {
            rollback(connection);
            throw new EntityDAOException(e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * Roll backs connection if something goes wrong
     */
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


    /**
     * Prepares statement for update to current entity
     */
    protected abstract void prepareStatementForUpdate(T t, PreparedStatement statement)
            throws EntityDAOException;

    /**
     * Prepares statement for insert to current entity
     */
    protected abstract void prepareStatementForInsert(T t, PreparedStatement statement)
            throws EntityDAOException;

}
