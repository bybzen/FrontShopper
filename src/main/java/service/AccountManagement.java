package service;

import models.Admin;
import models.Customer;

import java.util.HashMap;
import java.util.List;

public class AccountManagement {

//    private Admin adminMap = new Admin();
    private HashMap<String,Customer> customerMap = new HashMap<>();
    private HashMap<String,Admin> adminMap = new HashMap<>();


    private Admin adminNow;
    private Customer customerNow;

    public AccountManagement(){

        adminNow = null;
        customerNow = null;
    }

//    public boolean checkAdminAccount(String username, String password) {
//        try {
//            if (adminMap.checkAdminLogin(username, password)) {
//                adminNow = adminMap;
//                return true;
//            }
//        }
//        catch (IllegalArgumentException e) {
//                throw new IllegalArgumentException(e.getMessage());
//        }
//        if (adminNow == null) {
//            throw new IllegalArgumentException("We don't have this username in the database.");
//        }
//        return false;
//    }

    public String checkAdminAccount(String username,String password){
        for(Admin admin : adminMap.values()){
//            try{
                if(admin.checkAdminLogin(username,password)){
                    adminNow = admin;
                    return "adminLogin";
                }
//            }
//            catch (IllegalArgumentException e){
//                throw new IllegalArgumentException(e.getMessage()); }
        }
        if(customerNow==null) {
            return "adminNull";
//            throw new IllegalArgumentException("We don't have this username in the database.");
        }
        return "null";
    }

    public String checkCustomerAccount(String username,String password){
        for(Customer customer : customerMap.values()){
//            try{
                if(customer.checkAdminLogin(username,password)){
                    customerNow = customer;
                    return "customerLogin";
                }
//            }
//            catch (IllegalArgumentException e){
//                throw new IllegalArgumentException(e.getMessage()); }
        }
        if(customerNow==null) {
            return "customerNull";
//            throw new IllegalArgumentException("We don't have this username in the database.");
        }
        return "null";
    }


    private HashMap<String,Customer> customerListToMap(List<Customer> list){
        customerMap.clear();
        for(Customer customer : list){
            customerMap.put(customer.getUsername(),customer);
        }
        return customerMap;
    }

    private HashMap<String,Admin> adminListToMap(List<Admin> admin){
        adminMap.clear();
        for(Admin admin1 : admin){
            adminMap.put(admin1.getUsername(),admin1);
        }
        return adminMap;
    }

    public void setCustomerMapFromList(List<Customer> customerList) {
        this.customerMap = customerListToMap(customerList);
    }

    public void setAdminMapFromList(List<Admin> adminList) {
        this.adminMap = adminListToMap(adminList);
    }

//    public void setAdminMapFromList(Admin admin) {
//        this.adminMap = admin;
//    }

}
