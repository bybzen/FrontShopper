package service;

import models.CheckOutOrder;
import models.CustomerOrdered;
import models.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemManagement {

    private List<Items> itemsList = new ArrayList<>();
    private List<CheckOutOrder> allCheckoutList = new ArrayList<>();
    private List<CheckOutOrder> checkOutOrderListOfUsername = new ArrayList<>();
    private List<CustomerOrdered> allCustomerOrderedList = new ArrayList<>();
    private List<CustomerOrdered> customerOrderListOfUsername = new ArrayList<>();

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
        this.allCheckoutList = allCheckOutToList(allCheckoutList);
    }

    public List<CheckOutOrder> checkOutOrderListOfUsername(String username){
        total = 0;
        for (CheckOutOrder check: allCheckoutList){
            if (check.getUsername().equals(username)){
                checkOutOrderListOfUsername.add(check);
                total = total + (check.getPrice()*check.getQuantity()) ;
            }
        }
        return checkOutOrderListOfUsername;
    }

    public List<CustomerOrdered> customerOrderListOfUsername(String username){
        for (CustomerOrdered ordered: allCustomerOrderedList){
            if (ordered.getUsername().equals(username)){
                customerOrderListOfUsername.add(ordered);
            }
        }
        return customerOrderListOfUsername;
    }


    public float getTotal() {
        return total;
    }

    private List<CustomerOrdered> allCustomerOrderToList(List<CustomerOrdered> list) {
        allCustomerOrderedList.clear();
        for (CustomerOrdered ordered : list) {
            allCustomerOrderedList.add(ordered);
        }
        return allCustomerOrderedList;
    }

    public void setCustomerOrderedMapFromList(List<CustomerOrdered> allCustomerOrderedList) {
        this.allCustomerOrderedList = allCustomerOrderToList(allCustomerOrderedList);
    }

    public List<CustomerOrdered> getAllCustomerOrderedList() {
        return allCustomerOrderedList;
    }

    public List<CustomerOrdered> getCustomerOrderListOfUsername() {
        return customerOrderListOfUsername;
    }

}
