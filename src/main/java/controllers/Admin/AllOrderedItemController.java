package controllers.Admin;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.CustomerOrdered;

public class AllOrderedItemController {
    @FXML
    private ImageView clothesImg;

    @FXML
    private Label usernameLabel,nameProductLabel,priceLabel,sizeLabel,quantityLabel,totalLabel,statusLabel,dateLabel;

    private CustomerOrdered customerOrdered;

    public void setData(CustomerOrdered customerOrdered) {
        this.customerOrdered = customerOrdered;
        usernameLabel.setText("USERNAME : " + customerOrdered.getUsername());
        nameProductLabel.setText("PRODUCT : " + customerOrdered.getNameProduct());
//        nameProductLabel.setAlignment(Pos.CENTER);
        priceLabel.setText("PRICE : " + String.valueOf(customerOrdered.getPrice()) + " BAHT");
//        priceLabel.setAlignment(Pos.CENTER);
        sizeLabel.setText("SIZE : " + customerOrdered.getSize());
        quantityLabel.setText( "QUANTITY : " + String.valueOf(customerOrdered.getQuantity()));
        Image image = new Image(getClass().getResourceAsStream(customerOrdered.getImgSrc()));
        clothesImg.setImage(image);
        totalLabel.setText("TOTAL : " + String.valueOf(customerOrdered.getPrice()*customerOrdered.getQuantity()) + " BAHT");
        statusLabel.setText("STATUS : " + customerOrdered.getStatus());
        dateLabel.setText("DATE(D/M/Y) : " + customerOrdered.getDateTime());
        statusLabel.setAlignment(Pos.CENTER_RIGHT);
        dateLabel.setAlignment(Pos.CENTER_RIGHT);
    }
}
