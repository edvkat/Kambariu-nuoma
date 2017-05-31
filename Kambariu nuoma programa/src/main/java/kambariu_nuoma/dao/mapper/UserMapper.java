package kambariu_nuoma.dao.mapper;

import kambariu_nuoma.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dovile on 2017.05.24.
 */

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        String login = resultSet.getString("login");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String location = resultSet.getString("living_location");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");

        User userFromDB = new User(login, password, firstName, lastName, location, email);
        return userFromDB;
    }
}

