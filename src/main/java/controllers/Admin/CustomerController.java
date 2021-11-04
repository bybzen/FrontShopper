package controllers.Admin;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import models.Customer;
import models.Items;
import service.MyListener;

public class CustomerController {
    @FXML
    private Label usernameLabel,firstNameLabel,lastNameLabel,phoneLabel,addressLabel;

    private Customer customer;
    private MyListener myListener;

    public void setData(Customer customer) {
        this.customer = customer;
        this.myListener = myListener;
        usernameLabel.setText("Username : " + customer.getUsername());
        firstNameLabel.setText("First Name : " + customer.getFirstName());
        lastNameLabel.setText("Last Name : " + customer.getLastName());
        phoneLabel.setText("Phone Number : " + customer.getPhone());
        addressLabel.setText("Address : " + customer.getAddress());
    }

}
