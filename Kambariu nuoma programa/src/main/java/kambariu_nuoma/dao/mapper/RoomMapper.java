package kambariu_nuoma.dao.mapper;

import kambariu_nuoma.model.Room;
import kambariu_nuoma.model.enumeration.RoomType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoomMapper implements RowMapper<Room> {

    @Override
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String location = resultSet.getString("location");
        int number = resultSet.getInt("number");
        int maxNumberOfPeople = resultSet.getInt("max_number_of_people");
        boolean isAvailable = resultSet.getBoolean("is_available");
        Date availableFrom = resultSet.getDate("available_from");
        double priceForDay = resultSet.getDouble("price_for_day");
        RoomType type = RoomType.getByValue(resultSet.getInt("type"));

        Room roomFromDB = new Room (id,  number, maxNumberOfPeople, location, isAvailable, availableFrom, priceForDay, type);
        return roomFromDB;
    }
}
