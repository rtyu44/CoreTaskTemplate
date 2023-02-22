package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.security.auth.login.Configuration;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {



    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                                    "(id SERIAL NOT NULL, name VARCHAR(255), lastName VARCHAR(255), age INT, PRIMARY KEY(id))").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE if exists users").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createQuery("From User").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
        session.close();

//        for (Object o : instances) {
//            session.delete(o);
//        }
//
//            session.getTransaction().commit();
//            System.out.println("Таблица очищена");
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            if (session != null)
//                session.close();

    }
}