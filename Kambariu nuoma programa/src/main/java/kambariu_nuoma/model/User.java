package kambariu_nuoma.model;

/**
 * Created by Dovile on 2017.05.24.
 */
public class User {
    String login, password, firstName, lastName;
    String livingLocation, email;

    public User(String login, String password, String firstName, String lastName, String livingLocation, String email) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.livingLocation = livingLocation;
        this.email = email;
    }

    public User(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLivingLocation() {
        return livingLocation;
    }

    public void setLivingLocation(String livingLocation) {
        this.livingLocation = livingLocation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", livingLocation='" + livingLocation + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
