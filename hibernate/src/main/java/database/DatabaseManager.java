package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.security.KeyPair;
import java.util.List;
import java.util.Map;

/**
 * Base database management class for handling sessions and queries in a simpler manner.
 */
public class DatabaseManager {
    private final SessionFactory sessionFactory;
    // session is a value on open(), and set to null on close()
    private Session session = null;

    public DatabaseManager() {
        // Will load settings from resources/hibernate.cfg.xml by default
        Configuration configuration = new Configuration();
        // Can pass a string to refer to a different config file if needed
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    /**
     * Create a new session and transaction. Must be followed by {@link #close()}
     */
    public void open() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    /**
     * Close existing session and commit any changes.
     */
    public void close() {
        session.getTransaction().commit();
        session.close();
        session = null;
    }

    /**
     * Perform a database insert operation.
     * <br>
     * Must be called after {@link #open()}.
     * @param object Value to be inserted.
     * @param <T> Entity representing the table being interfaced with.
     */
    public <T> void create(T object) {
        getSession().persist(object);
    }

    /**
     * Perform a database select operation.
     * <br>
     * Must be called after {@link #open()}.
     * @param type Type of the entity being selected for.
     * @return List of all elements in the table.
     * @param <T> Entity representing the table being interfaced with.
     */
    public <T> List<T> read(Class<T> type) {
        return getSession().createQuery("from " + type.getName(), type).getResultList();
    }

    /**
     * Perform a database select operation with a custom where clause.
     * <br>
     * Must be called after {@link #open()}.
     * @param type Type of the entity being selected for.
     * @param where HQL format conditional statement.
     * @param parameters Mapping of values for any aliases defined in {@code where}.
     * @return List of filtered elements from the table.
     * @param <T> Entity representing the table being interfaced with.
     */
    public <T> List<T> read(Class<T> type, String where, Map<String, Object> parameters) {
        Query<T> q = getSession().createQuery("from " + type.getName() + " where " + where, type);
        for (Map.Entry<String, Object> value : parameters.entrySet()) {
            q.setParameter(value.getKey(), value.getValue());
        }
        return q.getResultList();
    }

    /**
     * Perform a database select operation with a custom from and where clause.
     * <br>
     * Must be called after {@link #open()}.
     * @param type Type of the entity being selected for.
     * @param from Names and aliases of the tables being queried. Must include the table associated with {@code T}.
     * @param where HQL format conditional statement.
     * @param parameters Mapping of values for any aliases defined in {@code where}.
     * @return List of filtered elements from the table.
     * @param <T> Entity representing the table being interfaced with.
     */
    public <T> List<T> read(Class<T> type, String from, String where, Map<String, Object> parameters) {
        Query q = getSession().createQuery("from " + from + " where " + where, type);
        for (Map.Entry<String, Object> value : parameters.entrySet()) {
            q.setParameter(value.getKey(), value.getValue());
        }
        return q.getResultList();
    }

    /**
     * Perform a database select operation with a custom from and where clause.
     * <br>
     * Must be called after {@link #open()}.
     * @param type Type of the entity being selected for.
     * @param fields HQL format list of fields to return.
     * @param from HQL format names and aliases of the tables being queried. Must include the table associated with {@code T}.
     * @param where HQL format conditional statement.
     * @param parameters Mapping of values for any aliases defined in {@code where}.
     * @return List of filtered elements from the table.
     * @param <T> Entity representing the table being interfaced with.
     */
    public <T> List<T> read(Class<T> type, String fields, String from, String where, Map<String, Object> parameters) {
        Query q = getSession().createQuery("select " + fields + " from " + from + " where " + where, type);
        for (Map.Entry<String, Object> value : parameters.entrySet()) {
            q.setParameter(value.getKey(), value.getValue());
        }
        return q.getResultList();
    }

    /**
     * Perform a database update operation on an existing record.
     * <br>
     * Must be called after {@link #open()}.
     * @param object Record to be updated. Generally should be obtained from a select operation.
     * @param <T> Entity representing the table being interfaced with.
     */
    public <T> void update(T object) {
        getSession().merge(object);
    }

    /**
     * Perform a database delete operation on an existing record.
     * <br>
     * Must be called after {@link #open()}.
     * @param object Record to be removed. Generally should be obtained from a select operation.
     * @param <T> Entity representing the table being interfaced with.
     */
    public <T> void delete(T object) {
        getSession().delete(object);
    }

    /**
     * Perform a custom database query.
     * <br>
     * Must be called after {@link #open()}.
     * @param query HQL format query to be applied.
     * @param parameters Mapping of values for any aliases defined in {@code query}.
     * @return List of results from the query.
     */
    public List<Object[]> query(String query, Map<String, Object> parameters) {
        Query<Object[]> q = getSession().createQuery(query, Object[].class);
        for (Map.Entry<String, Object> value : parameters.entrySet()) {
            q.setParameter(value.getKey(), value.getValue());
        }
        return q.getResultList();
    }

    protected final Session getSession() {
        return session;
    }
}
