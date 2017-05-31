package kambariu_nuoma.dao.mapper;

import kambariu_nuoma.model.enumeration.RoomAttribute;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoomAttributeMapper implements RowMapper<RoomAttribute> {
    @Override
    public RoomAttribute mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("fk_room_attribute");
        return RoomAttribute.getByValue(id);
    }
}
