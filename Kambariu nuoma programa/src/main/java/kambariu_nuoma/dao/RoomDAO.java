package kambariu_nuoma.dao;

import kambariu_nuoma.dao.mapper.RoomAttributeMapper;
import kambariu_nuoma.dao.mapper.RoomMapper;
import kambariu_nuoma.model.Room;
import kambariu_nuoma.model.enumeration.RoomAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RoomDAO {

    private JdbcTemplate dbConnector;
    private RoomMapper roomMapper;
    private RoomAttributeMapper attributeMapper;

    @Autowired
    public RoomDAO (DataSource dataSource, RoomMapper mapper, RoomAttributeMapper attributeMapper) {
        dbConnector = new JdbcTemplate((dataSource));
        this.roomMapper = mapper;
        this.attributeMapper = attributeMapper;
    }

    public Room getRoom(int roomID) {
        String SQL = "SELECT * FROM ROOM WHERE id = ?;";
        Object[] sqlArgs = { roomID };
        Room roomFromDB = dbConnector.queryForObject(SQL, sqlArgs, roomMapper);
        Set<RoomAttribute> roomAttributes = getAttributesOfRoom(roomID);
        roomFromDB.setRoomAttributes(roomAttributes);
        return roomFromDB;
    }

    public int getNumberOfAvailableRooms() {
        Connection connection = null;
        PreparedStatement statement = null;
        int numberOfAvailableRooms = 0;
        try {
            connection = dbConnector.getDataSource().getConnection();
            statement = connection.prepareStatement("SELECT COUNT(id) FROM ROOM WHERE is_available = 1");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                numberOfAvailableRooms = resultSet.getInt(1);
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
        return  numberOfAvailableRooms;
    }

    public List<Room> getAllAvailableRooms(int numberToReturn, int offset) {
        String SQL = "SELECT * FROM ROOM WHERE is_available = 1 LIMIT ? OFFSET ?;";
        Object[] sqlArgs = { numberToReturn, offset };
        List<Room> availableRooms = dbConnector.query(SQL, sqlArgs, roomMapper);
        addAttributesForRooms(availableRooms);
        return availableRooms;
    }

    public List<Room> getAllRoomsRentedBy (String username) {
        String SQL = "SELECT ROOM.id, location, number, max_number_of_people, " +
                "is_available, available_from, price_for_day, type " +
                "FROM ROOM, RENT_CONTRACT, USER WHERE RENT_CONTRACT.fk_USER = ?" +
                "login AND RENT_CONTRACT.fk_ROOM = ROOM.id;";
        Object[] sqlArgs = { username };
        List<Room> roomsRentedBySpecificUser = dbConnector.query(SQL, sqlArgs, roomMapper);
        addAttributesForRooms(roomsRentedBySpecificUser);
        return roomsRentedBySpecificUser;
    }

    public Set<RoomAttribute> getAttributesOfRoom (int roomID) {
        String SQL = "SELECT * FROM SINGLE_ROOM_ATTRIBUTE WHERE fk_ROOM = ?;";
        Object[] sqlArgs = { roomID };
        Set<RoomAttribute> attributesOfRoom = new HashSet<>(dbConnector.query(SQL, sqlArgs, attributeMapper));
        return attributesOfRoom;
    }

    private void addAttributesForRooms(Iterable<Room> allRooms) {
        for (Room room : allRooms) {
            Set<RoomAttribute> attributes = getAttributesOfRoom(room.getId());
            room.setRoomAttributes(attributes);
        }
    }

    public boolean editRoom(int editableRoomID, boolean newAvailable, Date newAvailableFrom) {
        String SQL = "UPDATE ROOM SET is_available = ?, " +
                "available_from = ? WHERE id = ?;";
        Object[] sqlArgs = { newAvailable, newAvailableFrom, editableRoomID };
        return dbConnector.update(SQL, sqlArgs) > 0;
    }

    public boolean editRoom(int editableRoomID, double newPriceForDay) {
        String SQL = "UPDATE ROOM SET price_for_day = ? " +
                "WHERE id = ?;";
        Object[] sqlArgs = { newPriceForDay, editableRoomID };
        return dbConnector.update(SQL, sqlArgs) > 0;
    }
}
