package models;

public class Items {
    private String nameProduct;
    private int prize;
    private String size;
    private String category;
    private String quantity;

    public Items(String nameProduct, int prize, String size, String category, String quantity) {
        this.nameProduct = nameProduct;
        this.prize = prize;
        this.size = size;
        this.category = category;
        this.quantity = quantity;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public int getPrize() {
        return prize;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSize() {
        return size;
    }

    public String getCategory() {
        return category;
    }
}
