package kambariu_nuoma.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by kradl on 2017-05-24.
 */
public class RentContract {
    int id, roomID;
    Timestamp signingTime;
    String username;
    Date fromDate, toDate;
    double totalPrice;


    public RentContract(int id, int roomID, String username, Date fromDate, Date toDate, double totalPrice) {
        this(id, roomID, username, fromDate, toDate);
        this.totalPrice = totalPrice;
    }

    public RentContract(int id, int roomID, String username, Date fromDate, Date toDate) {
        this(roomID, username);
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public RentContract(int roomID, String username) {
        this.roomID = roomID;
        this.username = username;
        LocalDateTime timeNow = LocalDateTime.now();
        this.signingTime = Timestamp.valueOf(timeNow);
    }

    public RentContract(int id, int roomID, Timestamp signingTime, String username, Date fromDate, Date toDate, double totalPrice) {
        this.id = id;
        this.roomID = roomID;
        this.signingTime = signingTime;
        this.username = username;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.totalPrice = totalPrice;
    }

    public RentContract() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Timestamp getSigningTime() {
        return signingTime;
    }

    public void setSigningTime(Timestamp signingTime) {
        this.signingTime = signingTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double priceForDay, long numberOfDays) {
        this.totalPrice = priceForDay * numberOfDays;
    }

    public long getNumberOfDays() {
        try {
            if (validate() > 0)
                return ChronoUnit.DAYS.between(fromDate.toLocalDate(), toDate.toLocalDate());
            else
                return -1;
        } catch (NullPointerException ex) {
            return -1;
        }
    }

    //-1 - nenurodyta nuomos pradžios data
    //-2 - nenurodyta nuomos pabaigos data
    //-3 - nuomos pabaigos data nėra vėliau nei nuomos pradžios data.
    public int validate () {
        if (fromDate == null)
            return -1;
        if (toDate == null)
            return -2;
        if (!fromDate.before(toDate))
            return -3;
        return 1;
    }

    @Override
    public String toString() {
        return "RentContract{" +
                "id=" + id +
                ", roomID=" + roomID +
                ", signingTime=" + signingTime +
                ", username='" + username + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
