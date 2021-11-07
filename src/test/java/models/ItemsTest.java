package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemsTest {

    private Items items;

    @BeforeEach
    public void setup(){
        items = new Items();
        items.setItemId("1");
        items.setNameProduct("Jeans");
        items.setPrice(6990);
        items.setSize("M");
        items.setQuantity(2);
        items.setImgSrc("Test");
    }

    @Test
    public void TestCheckItemIDTrue(){
        assertTrue(items.checkItemId(items.getItemId()));
    }

    @Test
    public void TestCheckItemIDFalse(){
        assertFalse(items.checkItemId(items.getItemId()+1));
    }

}