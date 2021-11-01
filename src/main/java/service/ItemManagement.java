package service;

import models.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemManagement {

    List<Items> itemsList = new ArrayList<>();

    private Items itemNow;

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    public void setItemNow(Items itemNow) {
        this.itemNow = itemNow;
    }

    public List<Items> getItemsList() {
        return itemsList;
    }

    public Items getItemNow() {
        return itemNow;
    }

    private List<Items> itemListToMap(List<Items> list) {
        itemsList.clear();
        for (Items item : list) {
            itemsList.add(item);
        }
        return itemsList;
    }

    public void setItemMapFromList(List<Items> itemList) {

        this.itemsList = itemListToMap(itemList);
    }
}
