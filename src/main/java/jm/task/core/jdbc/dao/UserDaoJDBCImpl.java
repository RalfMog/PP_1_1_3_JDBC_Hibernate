package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getInstance().getConnection();
    private PreparedStatement statement;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement = connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS USER (ID bigint PRIMARY KEY AUTO_INCREMENT NOT NULL,
                    NAME varchar(64) NOT NULL,
                    LASTNAME varchar(100) NOT NULL,
                    AGE tinyint NOT NULL)""");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(statement, connection);
        }
    }

    public void dropUsersTable() {
        try {
            statement = connection.prepareStatement("DROP TABLE IF EXISTS USER");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(statement, connection);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement = connection.prepareStatement("INSERT INTO USER (NAME, LASTNAME, AGE) VALUES(?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(statement, connection);
        }
    }

    public void removeUserById(long id) {
        try {
            statement = connection.prepareStatement("DELETE FROM USER WHERE ID=?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(statement, connection);
        }
    }

    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement("SELECT ID, NAME, LASTNAME, AGE FROM USER");
            rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("ID"));
                user.setName(rs.getString("NAME"));
                user.setLastName(rs.getString("LASTNAME"));
                user.setAge(rs.getByte("AGE"));
                resultList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(rs, statement, connection);
        }
        return resultList;
    }

    public void cleanUsersTable() {
        try {
            statement = connection.prepareStatement("TRUNCATE TABLE USER");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(statement, connection);
        }
    }
}
