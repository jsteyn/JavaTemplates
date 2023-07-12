package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

    protected final Session getSession() {
        return session;
    }

    protected abstract void onOpen();
    protected abstract void onClose();
}
