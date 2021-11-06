package models;

public class CustomerOrdered {

    private String purchaseOrderId;
    private String username;
    private String nameProduct;
    private float price;
    private int quantity;
    private String size;

    private String status;
    private String dateTime;
    private float total;
    private String imgSrc;

    public CustomerOrdered(String purchaseOrderId, String username, String nameProduct, float price, int quantity, String size, String status, String dateTime, float total,String imgSrc) {
        this.purchaseOrderId = purchaseOrderId;
        this.username = username;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.status = status;
        this.dateTime = dateTime;
        this.total = total;
        this.imgSrc = imgSrc;
    }

    public CustomerOrdered() {
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
