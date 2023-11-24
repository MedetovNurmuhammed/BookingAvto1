public class Driver {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;

    public Driver() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Driver(String name, String lastname, String address, String phoneNumber) {
        this.firstName = name;
        this.lastName = lastname;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", age=" + address +
                ", phonenumber=" + phoneNumber +
                '}';
    }

}
