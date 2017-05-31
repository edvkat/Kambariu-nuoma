package kambariu_nuoma.dao;

import kambariu_nuoma.dao.mapper.RoomMapper;
import kambariu_nuoma.dao.mapper.UserMapper;
import kambariu_nuoma.model.LoginBean;
import kambariu_nuoma.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Dovile on 2017.05.24.
 */

@Component
public class UserDAO {
    private JdbcTemplate dbConnector;
    private UserMapper userMapper;

    @Autowired
    public UserDAO(DataSource dataSource, UserMapper userMapper) {
        this.dbConnector = new JdbcTemplate(dataSource);
        this.userMapper = userMapper;
    }

    public User getUser(String login){
        String SQL = "SELECT * FROM CUSTOMER WHERE login = ?;";
        Object[] sqlArgs = { login };
        User userFromDB = dbConnector.queryForObject(SQL, sqlArgs, userMapper);
        return userFromDB;
    }
    public List<User> getAllUsers(){
        String SQL = "SELECT * FROM CUSTOMER;";
        List<User> allUsers = dbConnector.query(SQL, userMapper);
        return allUsers;
    }

    public boolean insertUser(User userToAdd){
        String SQL = "INSERT INTO CUSTOMER (login, last_name, first_name, living_location, email, password) VALUES (?, ?, ?, ?, ?, ?);";
        Object[] sqlArgs = new Object[] {userToAdd.getLogin(), userToAdd.getLastName(), userToAdd.getFirstName(), userToAdd.getLivingLocation(), userToAdd.getEmail(), userToAdd.getPassword()};
        return dbConnector.update(SQL, sqlArgs) > 0;
    }

    public boolean editUser(String login, String firstName, String lastName, String livingLocation, String email){
        String SQL = "UPDATE CUSTOMER SET first_name = ?, last_name = ?, living_location = ?, email = ? WHERE login = ?;";
        Object[] sqlArgs = { firstName, lastName, livingLocation, email, login };
        return dbConnector.update(SQL, sqlArgs) > 0;
    }

    public boolean validateLogin(LoginBean loginBean) {
        boolean status = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnector.getDataSource().getConnection();
            statement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE login = ? AND password = ?");
            statement.setString(1, loginBean.getUsername());
            statement.setString(2, loginBean.getPassword());
            ResultSet resultSet = statement.executeQuery();
            status = resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    //-1 - blogas login'as, turi sudaryti >= 6 simboliai. Naudojami tik skaičių ir radžių simboliai.
    //-2 - el. paštas neatitinka šablono.
    //-3 - per trumpas slaptažodis. Turi būti mažiausiai 4 simboliai.
    //-4 - toks vartotojo vardas jau yra.
    //-5 - vardas neatitinka šablono.
    //-6 - pavardė neatitinka šablono.
    //-7 - klaidingai nurodyta gyvenamoji vieta.
    // 1 - OK.
    public int validateUser (User user) {
        int status = validateExistingUser(user);
        if (status > 0) {
            if (user.getLogin() == null || user.getLogin().length() < 6)
                return -1;
            if (userExists(user.getLogin()))
                return -4;
        }
        return status;
    }
    public int validateExistingUser(User user) {
        if (user.getEmail() == null || !user.getEmail().matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+$"))
            return -2;
        if (user.getPassword() == null || user.getPassword().length() < 4)
            return -3;
        if (user.getFirstName() == null || !user.getFirstName().matches("^([a-zA-zĄČĘĖĮŠŲŪŽąčęėįšųūž]+ ?)+$"))
            return -5;
        if (user.getLastName() == null || !user.getLastName().matches("^([a-zA-zĄČĘĖĮŠŲŪŽąčęėįšųūž]+ ?)+$"))
            return -6;
        if (user.getLivingLocation() == null || !user.getLivingLocation().matches("^(.+ ?)+$"))
            return -7;
        return 1;
    }

    private boolean userExists(String login) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean userExists = false;
        try {
            connection = dbConnector.getDataSource().getConnection();
            statement = connection.prepareStatement("SELECT login FROM CUSTOMER WHERE login = ?;");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            userExists = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userExists;
    }
}
