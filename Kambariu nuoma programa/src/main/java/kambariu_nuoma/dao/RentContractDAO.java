package kambariu_nuoma.dao;

import kambariu_nuoma.dao.mapper.RentContractMapper;
import kambariu_nuoma.dao.mapper.RoomMapper;
import kambariu_nuoma.model.RentContract;
import kambariu_nuoma.model.Room;
import kambariu_nuoma.model.enumeration.RoomAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by kradl on 2017-05-24.
 */
@Component
public class RentContractDAO {
    private JdbcTemplate dbConnector;
    private RentContractMapper rentContractMapper;

    @Autowired
    public RentContractDAO(DataSource dataSource, RentContractMapper rentContractMapper) {
        this.dbConnector = new JdbcTemplate(dataSource);
        this.rentContractMapper = rentContractMapper;
    }

    public RentContract getContract(int id){
        String SQL = "SELECT * FROM RENT_CONTRACT WHERE id = ?;";
        Object[] sqlArgs = { id };
        RentContract rentContractFomDB = dbConnector.queryForObject(SQL, sqlArgs, rentContractMapper);
        return rentContractFomDB;
    }

    public List<RentContract> getAllContracts(String username){
        String SQL = "SELECT * FROM RENT_CONTRACT WHERE fk_USER = ?;";
        Object[] sqlArgs = { username };
        List<RentContract> rentContractFomDB = dbConnector.query(SQL, sqlArgs, rentContractMapper);
        return rentContractFomDB;
    }

    public List<RentContract> getValidRentContracts(String username, int numberToReturn, int offset) {
        String SQL = "SELECT * FROM RENT_CONTRACT WHERE fk_USER = ? AND until_date > NOW() LIMIT ? OFFSET ?;";
        Object[] sqlArgs = { username, numberToReturn, offset };
        List<RentContract> validContracts = dbConnector.query(SQL, sqlArgs, rentContractMapper);
        return validContracts;
    }

    public List<RentContract> getVoidRentContracts(String username, int numberToReturn, int offset) {
        String SQL = "SELECT * FROM RENT_CONTRACT WHERE fk_USER = ? AND until_date < NOW() LIMIT ? OFFSET ?;";
        Object[] sqlArgs = { username, numberToReturn, offset };
        List<RentContract> voidContracts = dbConnector.query(SQL, sqlArgs, rentContractMapper);
        return voidContracts;
    }

    public boolean insertContract(RentContract rentContract){
        String SQL = "INSERT INTO rent_Contract(signing_date_time, from_date, until_date, total_price, fk_USER, fk_ROOM) VALUES (?, ?, ?, ?, ?, ?);";
        Object[] sqlArgs = { rentContract.getSigningTime(), rentContract.getFromDate(), rentContract.getToDate(), rentContract.getTotalPrice(), rentContract.getUsername(), rentContract.getRoomID() };
        return dbConnector.update(SQL, sqlArgs) > 0;
    }

    public int getNumberOfValidRentContracts(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        int numberOfValidRentContracts = 0;
        try {
            connection = dbConnector.getDataSource().getConnection();
            statement = connection.prepareStatement("SELECT COUNT(*) FROM RENT_CONTRACT WHERE fk_USER = ? AND until_date > NOW();");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                numberOfValidRentContracts = resultSet.getInt(1);
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
        return  numberOfValidRentContracts;
    }

    public int getNumberOfVoidRentContracts(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        int numberOfVoidRentContracts = 0;
        try {
            connection = dbConnector.getDataSource().getConnection();
            statement = connection.prepareStatement("SELECT COUNT(*) FROM RENT_CONTRACT WHERE fk_USER = ? AND until_date < NOW();");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                numberOfVoidRentContracts = resultSet.getInt(1);
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
        return  numberOfVoidRentContracts;
    }
}
