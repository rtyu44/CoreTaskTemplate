package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Джо", "Байден", (byte) 21);
        userService.saveUser("Трамп", "Дональд", (byte) 20);
        userService.saveUser("Барак", "Обама", (byte) 15);
        userService.saveUser("Джордж", "Буш", (byte) 17);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}