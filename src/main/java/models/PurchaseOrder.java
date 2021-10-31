package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PurchaseOrder extends Items{
    private String purchaseNumber;
    private String time;


    public PurchaseOrder(String nameProduct, int prize, String size, String category, String quantity, String purchaseNumber, String time) {
        super(nameProduct, prize, size, quantity);
        this.purchaseNumber = purchaseNumber;
        this.time = time;
    }

//    public PurchaseOrder() {
//        super("Pink Dress", 500, "Free Size", "Dress", "1");
//    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public String getTime() {
        return time;
    }

    public String setTime() {
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
        LocalDateTime current = LocalDateTime.now();
        time = formatTime.format(current);
        return time;
    }
}
