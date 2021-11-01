package service;

import models.Admin;
import models.Customer;

import java.util.HashMap;
import java.util.List;

public class AccountManagement {

    private HashMap<String, Customer> customerMap = new HashMap<>();
    private HashMap<String, Admin> adminMap = new HashMap<>();


    private Admin adminNow;
    private Customer customerNow;

    public AccountManagement() {

        adminNow = null;
        customerNow = null;
    }

    public Admin getAdminNow() {

        return adminNow;
    }

    public Customer getCustomerNow() {

        return customerNow;
    }


    public void setAdminNow(Admin adminNow) {

        this.adminNow = adminNow;
    }

    public void setCustomerNow(Customer customerNow) {

        this.customerNow = customerNow;
    }

    public void setCustomerMapFromList(List<Customer> customerList) {

        this.customerMap = customerListToMap(customerList);
    }

    public void setAdminMapFromList(List<Admin> adminList) {

        this.adminMap = adminListToMap(adminList);
    }

    public String checkAdminAccount(String username, String password) {
        for (Admin admin : adminMap.values()) {
//            try{
            if (admin.checkAdminLogin(username, password)) {
                adminNow = admin;
                return "adminLogin";
            }
//            }
//            catch (IllegalArgumentException e){
//                throw new IllegalArgumentException(e.getMessage()); }
        }
        if (customerNow == null) {
            return "adminNull";
//            throw new IllegalArgumentException("We don't have this username in the database.");
        }
        return "null";
    }

    private HashMap<String, Admin> adminListToMap(List<Admin> admin) {
        adminMap.clear();
        for (Admin admin1 : admin) {
            adminMap.put(admin1.getUsername(), admin1);
        }
        return adminMap;
    }

    public String checkCustomerAccount(String username, String password) {
        for (Customer customer : customerMap.values()) {
//            try{
            if (customer.checkAdminLogin(username, password)) {
                customerNow = customer;
                return "customerLogin";
            }
//            }
//            catch (IllegalArgumentException e){
//                throw new IllegalArgumentException(e.getMessage()); }
        }
        if (customerNow == null) {
            return "customerNull";
//            throw new IllegalArgumentException("We don't have this username in the database.");
        }
        return "null";
    }

    public boolean checkCustomerUsername(String username) {
        for (Customer customer : customerMap.values()) {
            if (customer.checkUsername(username)) {
                customerNow = customer;
                return true;
            }
        }
        return false;
    }


    private HashMap<String, Customer> customerListToMap(List<Customer> list) {
        customerMap.clear();
        for (Customer customer : list) {
            customerMap.put(customer.getUsername(), customer);
        }
        return customerMap;
    }

    public HashMap<String, Customer> getCustomerMap() {

        return customerMap;
    }

    public Customer getCustomerAccount(String username) {
        for (Customer customerAcc : customerMap.values()){
            if (customerAcc.checkUsername(username)){
                customerNow = customerAcc;
                return customerNow;
            }
        }
        return null;
    }
    public Admin getAdminAccount(String username) {
        for (Admin admin : adminMap.values()){
            if (admin.checkUsername(username)){
                adminNow = admin;
                return adminNow;
            }
        }
        return null;
    }

}
