package service;

import models.Admin;
import models.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ShopperApplicationAPIService {

    private RestTemplate restTemplate ;

    public ShopperApplicationAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
//
//    public Admin[] getAdmin(){
//        String url = "http://localhost:8080/admin";
//        ResponseEntity<Admin[]> response = restTemplate.getForEntity(url, Admin[].class);
//        Admin[] admins = response.getBody();
//        return admins;
//    }

    public List<Admin> getAdmin(){
        String url = "http://localhost:8080/admin";
        ResponseEntity<Admin[]> response = restTemplate.getForEntity(url, Admin[].class);
        Admin[] admin = response.getBody();
        return Arrays.asList(admin);
    }

    public List<Customer> getAllCustomer(){
        String url = "http://localhost:8080/customer";
        ResponseEntity<Customer[]> response = restTemplate.getForEntity(url, Customer[].class);
        Customer[] customers = response.getBody();
        return Arrays.asList(customers);
    }
}
