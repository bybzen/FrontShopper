package service;

import models.CheckOutOrder;
import models.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemManagement {

    private List<Items> itemsList = new ArrayList<>();
    private List<CheckOutOrder> allCheckoutList = new ArrayList<>();
    private List<CheckOutOrder> checkOutOrderListOfUsername = new ArrayList<>();
    private float total ;

    private Items itemNow;

    public List<CheckOutOrder> getAllCheckoutList() {
        return allCheckoutList;
    }

    public void setAllCheckoutList(List<CheckOutOrder> allCheckoutList) {
        this.allCheckoutList = allCheckoutList;
    }

    public List<CheckOutOrder> getCheckOutOrderListOfUsername() {
        return checkOutOrderListOfUsername;
    }


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

    private List<CheckOutOrder> allCheckOutToList(List<CheckOutOrder> list) {
        allCheckoutList.clear();
        for (CheckOutOrder check : list) {
            allCheckoutList.add(check);
        }
        return allCheckoutList;
    }

    public void setItemMapFromList(List<Items> itemList) {

        this.itemsList = itemListToMap(itemList);
    }

    public void setCheckoutMapFromList(List<CheckOutOrder> allCheckoutList) {
//        System.out.println(allCheckoutList.size());
        this.allCheckoutList = allCheckOutToList(allCheckoutList);
    }

    public List<CheckOutOrder> checkOutOrderListOfUsername(String username){
        total = 0;
        for (CheckOutOrder check: allCheckoutList){
            if (check.getUsername().equals(username)){
                checkOutOrderListOfUsername.add(check);
                total = total + check.getPrice();
            }
        }
        return checkOutOrderListOfUsername;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    //    public float totalPrice(){
//        total = 0;
//        for (CheckOutOrder check: allCheckoutList){
//            total = total+check.getPrice();
//            }
//
//        return total;
//    }

}
