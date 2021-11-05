package models;

import java.util.UUID;

public class CheckOutOrder {

    private String purchaseOrderId;

    private String nameProduct;
    private float price;
    private float total; // ราคาจ่ายทั้งหมด
    private String size;
    private int quantity;
    private String dateTime; //เวลาที่ทำการสั่ง
    private String addressCustomer;
    private String username;
    private String imgSrc;

    public CheckOutOrder(String username,String nameProduct, float price, float total, String size, int quantity, String dateTime, String addressCustomer, String imgSrc) {
        this.nameProduct = nameProduct;
        this.price = price;
        this.total = total;
        this.size = size;
        this.quantity = quantity;
        this.dateTime = dateTime;
        this.addressCustomer = addressCustomer;
        this.username = username;
        this.imgSrc = imgSrc;
    }

    public CheckOutOrder() {

    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }


    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


}
