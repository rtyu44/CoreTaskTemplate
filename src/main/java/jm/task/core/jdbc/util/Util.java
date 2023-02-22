package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class Util {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
//    private static final String DIALECT = "org.hibernate.dialect.PostgreSQL82Dialect";

    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "org.postgresql.Driver");
                properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "root");
//                properties.put(Environment.FORMAT_SQL, "true");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL82Dialect");
//                properties.put(Environment.SHOW_SQL, "true");
//                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//                properties.put(Environment.HBM2DDL_AUTO, "create");
//                properties.put(Environment.POOL_SIZE, "5");

                Configuration configuration = new Configuration();
                        configuration.addProperties(properties)
                        .addAnnotatedClass(User.class);
//                        .buildSessionFactory(); /*здесь возможная ошибка*/
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

