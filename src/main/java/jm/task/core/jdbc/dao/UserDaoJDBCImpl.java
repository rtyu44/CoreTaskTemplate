package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.engine.jdbc.spi.StatementPreparer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id SERIAL NOT NULL, name VARCHAR(255), last_name VARCHAR(255), age INT, PRIMARY KEY(id))");
            System.out.println("table create");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE if exists users");
            System.out.println("table delete");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser( String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, last_name, age) VALUES(?,?,?)")) {
//            preparedStatement.setInt(1,id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            System.out.println("User deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUser = new ArrayList<>();

        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users")) {
            //ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                allUser.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUser;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("table clear");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("the table is not cleared");
        }
    }
}
