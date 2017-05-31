package kambariu_nuoma.dao.mapper;

import kambariu_nuoma.model.RentContract;
import kambariu_nuoma.model.Room;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * Created by kradl on 2017-05-24.
 */
@Component
public class RentContractMapper implements RowMapper<RentContract> {

    @Override
    public RentContract mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        Timestamp signingTime = resultSet.getTimestamp("signing_date_time");
        int roomID = resultSet.getInt("fk_ROOM");
        String username = resultSet.getString("fk_USER");
        Date fromDate = resultSet.getDate("from_date");
        Date toDate = resultSet.getDate("until_date");
        double totalPrice = resultSet.getDouble("total_price");

        RentContract rentContractFromDB = new RentContract(id, roomID, signingTime, username, fromDate, toDate, totalPrice);
        return rentContractFromDB;
    }
}
