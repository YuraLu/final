package by.epam.trjava.tutorsystem.dao.pool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static final DBResourceManager instance = new DBResourceManager();

    private DBResourceManager() {
    }

    public static DBResourceManager getInstance() {
        return instance;
    }

    private final ResourceBundle bundle = ResourceBundle.getBundle("db");

    public String getProperty(String key) {
        return bundle.getString(key);
    }
}
