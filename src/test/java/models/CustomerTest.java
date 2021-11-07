package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomerTest {

    Customer customer;

    @BeforeEach
    void setup(){

        customer = new  Customer();
        customer.setUsername("customer01");
        customer.setPassword("111");
    }


    @Test
    public void testCheckUsernameCustomer(){
        assertEquals("customer01", customer.getUsername());
    }

    @Test
    public void testCheckPasswordCustomer(){

        assertEquals("111", customer.getPassword());

    }


}
