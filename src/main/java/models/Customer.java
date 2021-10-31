package models;

public class Customer extends Admin{
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String customerId;

    public Customer(){

    }

    public Customer(String username, String password, String firstName, String lastName, String address, String phone) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    //    public Customer(String username, String password, String firstName, String lastName, String address, String phone) {
//        super(username, password);
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.phone = phone;
//    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    //    @Override
//    public String getUsername() {
//        return super.getUsername();
//    }
//
//    @Override
//    public String getPassword() {
//        return super.getPassword();
//    }




}
