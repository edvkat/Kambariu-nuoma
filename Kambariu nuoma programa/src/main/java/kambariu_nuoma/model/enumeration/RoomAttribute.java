package kambariu_nuoma.model.enumeration;

public enum RoomAttribute {
    NEAR_THE_SEA        (1),
    NEAR_THE_MOUNTAINS  (2),
    DECENT_AUDIO_SYSTEM (3),
    HISTORICAL_LOCATION (4),
    SWIMMING_POOL       (5),
    FREE_MEALS          (6),
    DOUBLE_BED          (7),
    BALCONY             (8),
    SKYLIGHTS           (9),
    BIG_STAGE           (10),
    BAR                 (11),
    AIR_CONDITIONING    (12);

    private final int id;

    RoomAttribute(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName () {
        return toString();
    }

    public static RoomAttribute getByValue (int type) {
        for (RoomAttribute attribute : RoomAttribute.values())
            if (attribute.id == type)
                return attribute;
        return null;
    }


    @Override
    public String toString() {
        return this.name().substring(0, 1).toUpperCase() +
                name().substring(1).toLowerCase();
    }
}
