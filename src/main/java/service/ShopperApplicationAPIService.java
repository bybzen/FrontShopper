package service;

import models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ShopperApplicationAPIService {

    private RestTemplate restTemplate ;

    private String url="https://project-shopper-api.herokuapp.com/";

    public ShopperApplicationAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Admin> getAdmin(){

        ResponseEntity<Admin[]> response = restTemplate.getForEntity(url+"admin", Admin[].class);
        Admin[] admin = response.getBody();
        return Arrays.asList(admin);
    }
    public void updateAdmin(Admin admin) {
        String urlAmUpdate = url + "admin/" + admin.getAdminId();
        restTemplate.put(urlAmUpdate,admin,Admin.class);
    }


    public List<Customer> getAllCustomer(){
        ResponseEntity<Customer[]> response = restTemplate.getForEntity(url+"customer", Customer[].class);
        Customer[] customers = response.getBody();
        return Arrays.asList(customers);
    }

    public void addCustomer(Customer newCustomer){
        restTemplate.postForObject(url+"customer", newCustomer, Customer.class);
    }

    public void updateCustomer(Customer customer) {
        String urlCusUpdate = url + "customer/" + customer.getCustomerId();
        restTemplate.put(urlCusUpdate,customer,Customer.class);
    }

    public void deleteCustomer() {
        restTemplate.delete(url+"customer");
    }

    public List<Items> getAllItems(){
        ResponseEntity<Items[]> response = restTemplate.getForEntity(url+"items", Items[].class);
        Items[] items = response.getBody();
        return Arrays.asList(items);
    }

    public List<CheckOutOrder> getAllCheckOutOrder(){
        ResponseEntity<CheckOutOrder[]> response = restTemplate.getForEntity(url+"checkout", CheckOutOrder[].class);
        CheckOutOrder[] checkOutOrders = response.getBody();
        return Arrays.asList(checkOutOrders);
    }

    public void addCheckOutOrder(CheckOutOrder checkoutOrder){
        restTemplate.postForObject(url+"checkout", checkoutOrder, CheckOutOrder.class);
    }

    public void deleteCheckOutOrder(CheckOutOrder checkoutOrder){
        String urlCheckDelete = url +"checkout/" + checkoutOrder.getPurchaseOrderId();
        restTemplate.delete(urlCheckDelete, checkoutOrder, CheckOutOrder.class);
    }

    public List<CustomerOrdered> getAllCustomerOrdered(){
        ResponseEntity<CustomerOrdered[]> response = restTemplate.getForEntity(url+ "ordered", CustomerOrdered[].class);
        CustomerOrdered[] customerOrdered = response.getBody();
        return Arrays.asList(customerOrdered);
    }

    public void addCustomerOrdered(CustomerOrdered customerOrdered){
        restTemplate.postForObject(url+"ordered", customerOrdered, CustomerOrdered.class);
    }

}
