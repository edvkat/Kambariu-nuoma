package kambariu_nuoma.model;

import kambariu_nuoma.model.enumeration.RoomAttribute;
import kambariu_nuoma.model.enumeration.RoomType;

import java.sql.Date;
import java.util.Set;

public class Room {
    int id, number, maxNumberOfPeople;
    String location;
    boolean available;
    Date availableFrom;
    Set<RoomAttribute> roomAttributes;
    double priceForDay;
    RoomType type;

    public Room(int id, int number, int maxNumberOfPeople, String location, boolean available, Date availableFrom, double priceForDay, RoomType type) {
        this (id, number, maxNumberOfPeople, location, priceForDay, type);
        this.available = available;
        this.availableFrom = availableFrom;

    }

    public Room(int id, int number, int maxNumberOfPeople, String location, boolean available, Date availableFrom, Set<RoomAttribute> roomAttributes, double priceForDay, RoomType type) {
        this (id, number, maxNumberOfPeople, location, available, availableFrom, priceForDay, type);
        this.roomAttributes = roomAttributes;
    }

    public Room(int id, int number, int maxNumberOfPeople, String location, double priceForDay, RoomType type) {
        this.id = id;
        this.number = number;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.location = location;
        this.priceForDay = priceForDay;
        this.type = type;
    }

    public Room(int id, int number, int maxNumberOfPeople, String location, Set<RoomAttribute> roomAttributes, double priceForDay, RoomType type) {
        this (id, number, maxNumberOfPeople, location, priceForDay, type);
        this.roomAttributes = roomAttributes;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Set<RoomAttribute> getRoomAttributes() {
        return roomAttributes;
    }

    public void setRoomAttributes(Set<RoomAttribute> roomAttributes) {
        this.roomAttributes = roomAttributes;
    }

    public double getPriceForDay() {
        return priceForDay;
    }

    public void setPriceForDay(double priceForDay) {
        this.priceForDay = priceForDay;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public boolean addRoomAttribute(RoomAttribute attributeToAdd) {
        return roomAttributes.add(attributeToAdd);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", maxNumberOfPeople=" + maxNumberOfPeople +
                ", location='" + location + '\'' +
                ", available=" + available +
                ", availableFrom=" + availableFrom +
                ", roomAttributes=" + roomAttributes +
                ", priceForDay=" + priceForDay +
                ", type=" + type +
                '}';
    }
}
