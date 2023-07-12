import database.DatabaseManager;
import database.model.Person;

public class DatabaseManagerImpl extends DatabaseManager {
    public DatabaseManagerImpl() {
        super();
    }

    public void insert(Person person) {
        getSession().save(person);
    }

    @Override
    protected void onOpen() {

    }

    @Override
    protected void onClose() {

    }
}
