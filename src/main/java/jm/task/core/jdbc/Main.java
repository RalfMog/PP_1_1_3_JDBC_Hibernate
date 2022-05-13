package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();
        List<User> userList = new ArrayList<>();
        Collections.addAll(userList, new User("Name1", "LastName1", (byte) 20),
                new User("Name2", "LastName2", (byte) 25),
                new User("Name3", "LastName3", (byte) 31),
                new User("Name4", "LastName4", (byte) 38));
        for (int i = 0; i < 4; i++) {
            userDao.saveUser(userList.get(i).getName(), userList.get(i).getLastName(), userList.get(i).getAge());
            System.out.printf("User с именем – %s добавлен в базу данных\n", userDao.getAllUsers().get(i).getName());
        }

        System.out.printf(userDao.getAllUsers().toString());

//        userDao.saveUser()
//        userDao.createUsersTable();
//        userDao.removeUserById(1);
//        userDao.removeUserById(7);
//        userDao.getAllUsers();
//        userDao.cleanUsersTable();
//
//        userDao.dropUsersTable();
    }
}
