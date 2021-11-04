package service;

import models.Admin;
import models.Customer;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement {

    private List<Customer> customerMap = new ArrayList<>();
    private List<Admin> adminMap = new ArrayList<>();

    private Admin adminNow;
    private Customer customerNow;

    public AccountManagement() {
        adminNow = new Admin();
        customerNow = new Customer();
    }


    public List<Admin> getAdminMap() {

        return adminMap;
    }

    public void setAdminMap(List<Admin> adminMap) {

        this.adminMap = adminMap;
    }

    public Admin getAdminNow() {

        return adminNow;
    }

    public void setAdminNow(Admin adminNow) {

        this.adminNow = adminNow;
    }

    public Customer getCustomerNow() {

        return customerNow;
    }

    public void setCustomerNow(Customer customerNow) {

        this.customerNow = customerNow;
    }

    public void setCustomerMapFromList(List<Customer> customerList) {

        this.customerMap = customerList;
    }

    public void setAdminMapFromList(List<Admin> adminList) {

        this.adminMap = adminList;
    }

    public List<Customer> getCustomerMap() {

        return customerMap;
    }

    public void setCustomerMap(List<Customer> customerMap) {

        this.customerMap = customerMap;
    }

    public boolean checkAdminAccount(String username, String password) {
        for (Admin admin : adminMap) {
            if (admin.getUsername().equals(username)) {
                if (admin.getPassword().equals(password)){
                    adminNow = admin;
                    return true;
                }

            }
        }
        return false;
    }

    public boolean checkCustomerAccount(String username, String password) {
        for (Customer customer : customerMap) {
            if (customer.getUsername().equals(username)) {
                if (customer.getPassword().equals(password)){
                    customerNow = customer;
                    return true;
                }

            }
        }
        return false;
    }

    public boolean checkCustomerUsername(String username) {
        for (Customer customer : customerMap) {
            if (customer.getUsername().equals(username)) {
                customerNow = customer;
                return true;
            }
        }
        return false;
    }



    public Customer getCustomerAccount(String username) {
        for (Customer customerAcc : customerMap){
            if (customerAcc.getUsername().equals(username)){
                customerNow = customerAcc;
                return customerNow;
            }
        }
        return null;
    }
    public Admin getAdminAccount(String username) {
        for (Admin admin : adminMap){
            if (admin.getUsername().equals(username)){
                adminNow = admin;
                return adminNow;
            }
        }
        return null;
    }


}
