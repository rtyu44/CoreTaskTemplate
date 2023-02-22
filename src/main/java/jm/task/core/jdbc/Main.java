package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
//    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        UserDaoHibernateImpl userService = new UserDaoHibernateImpl();
        userService.createUsersTable();

        userService.saveUser("Liam", "Nilson", (byte) 21);
        userService.saveUser("Tramp", "Donald", (byte) 20);
        userService.saveUser("Bara", "arab", (byte) 15);
        userService.saveUser("Jacob", "wolf", (byte) 17);

        List<User> list = userService.getAllUsers();
        for (User user:list){
            System.out.println(user.toString());
            System.out.println("user add: " + user.getName());
        }

        userService.removeUserById(2);
//
//        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}