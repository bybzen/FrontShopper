package models;

import models.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTest {

    Admin admin;


    @BeforeEach
    void setup (){
        admin = new Admin();
        admin.setUsername("acctest");
        admin.setPassword("1234");
    }

    @Test
    public void testCheckUsernameAdmin(){

        assertEquals("acctest", admin.getUsername());
    }

    @Test
    public void testCheckPasswordAdmin(){
        assertEquals("1234", admin.getPassword());
    }



}
