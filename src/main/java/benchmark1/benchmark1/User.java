package benchmark1.benchmark1;

import com.opencsv.bean.CsvBindByName;

import javax.annotation.Nonnull;

public class User {

    @CsvBindByName(column = "id")
    private String id;

    @CsvBindByName(column = "first_name")
    private String firstName;

    @CsvBindByName(column = "last_name")
    private String lastName;

    @CsvBindByName(column = "email")
    private String email;

    @CsvBindByName(column = "gender")
    private String gender;

    @CsvBindByName(column = "ip_address")
    private String IpAddress;

    public String getId() {
        return id;
    }

    public void setId(final @Nonnull String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(final String ipAddress) {
        IpAddress = ipAddress;
    }
}
