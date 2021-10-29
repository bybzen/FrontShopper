package models;

public class Customer extends Admin{
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public Customer(){

    }

    public Customer(String username, String password, String firstName, String lastName, String address, String phoneNumber) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }



}
