package kambariu_nuoma.model.enumeration;

public enum RoomType {
    ROOM            (1),
    CONFERENCE_HALL (2),
    CONCERT_HALL    (3);

    private final int id;

    RoomType(int id) {
        this.id = id;
    }

    public String getName (String name) {
        return toString();
    }

    public static RoomType getByValue (int value) {
        for (RoomType type : RoomType.values())
            if (type.id == value)
                return type;
        return null;
    }


    @Override
    public String toString() {
        return this.name().substring(0, 1).toUpperCase() +
                name().substring(1).toLowerCase();
    }
}
