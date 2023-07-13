package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public abstract class DatabaseManager {
    private final SessionFactory sessionFactory;
    private Session session = null;

    public DatabaseManager() {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public void open() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        onOpen();
    }

    public void close() {
        onClose();
        session.getTransaction().commit();
        session.close();
        session = null;
    }

    public <T> void create(T object) {
        getSession().persist(object);
    }

    public <T> List<T> read(Class<T> type) {
        return getSession().createQuery("from " + type.getName(), type).getResultList();
    }

    public <T> List<T> read(Class<T> type, String where, String[] aliases, Object[] values) {
        Query<T> q = getSession().createQuery("from " + type.getName() + " where " + where, type);
        for (int i = 0; i < aliases.length; i++) {
            q.setParameter(aliases[i], values[i]);
        }
        return q.getResultList();
    }

    public List read(String query, String[] aliases, Object[] values) {
        Query q = getSession().createQuery(query);
        for (int i = 0; i < aliases.length; i++) {
            q.setParameter(aliases[i], values[i]);
        }
        return q.getResultList();
    }

    public <T> void update(T object) {
        getSession().merge(object);
    }

    public <T> void delete(T object) {
        getSession().delete(object);
    }

    protected final Session getSession() {
        return session;
    }

    protected abstract void onOpen();
    protected abstract void onClose();
}
