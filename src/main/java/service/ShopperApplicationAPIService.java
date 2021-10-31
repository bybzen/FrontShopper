package service;

import controllers.ItemController;
import models.Admin;
import models.Customer;
import models.Items;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ShopperApplicationAPIService {

    private RestTemplate restTemplate ;

    public ShopperApplicationAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Admin> getAdmin(){
        String url = "http://localhost:8080/admin";
        ResponseEntity<Admin[]> response = restTemplate.getForEntity(url, Admin[].class);
        Admin[] admin = response.getBody();
        return Arrays.asList(admin);
    }
    public void updateAdmin(Admin admin) {
        String url = "http://localhost:8080/admin/" + admin.getAdminId();
        restTemplate.put(url,admin,Admin.class);
    }


    public List<Customer> getAllCustomer(){
        String url = "http://localhost:8080/customer";
        ResponseEntity<Customer[]> response = restTemplate.getForEntity(url, Customer[].class);
        Customer[] customers = response.getBody();
        return Arrays.asList(customers);
    }

    public void addCustomer(Customer newCustomer){
        String url = "http://localhost:8080/customer";
        restTemplate.postForObject(url, newCustomer, Customer.class);
    }

    public void updateCustomer(Customer customer) {
        String url = "http://localhost:8080/customer/" + customer.getCustomerId();
        restTemplate.put(url,customer,Customer.class);
    }

    public void deleteCustomer() {
        String url = "http://localhost:8080/customer";
        restTemplate.delete(url);
    }


    public List<Items> getAllItems(){
        String url = "http://localhost:8080/items";
        ResponseEntity<Items[]> response = restTemplate.getForEntity(url, Items[].class);
        Items[] items = response.getBody();
        return Arrays.asList(items);
    }
}
