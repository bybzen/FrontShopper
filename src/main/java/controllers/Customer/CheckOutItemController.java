package controllers.Customer;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.CheckOutOrder;
import models.Items;
import service.MyCheckout;
import service.MyListener;

public class CheckOutItemController {

    @FXML
    private ImageView clothesImg;

    @FXML
    private Label nameProductLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label sizeLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myCheckout.onClickCheckoutItem(chekOutItem);
    }

    private CheckOutOrder chekOutItem;
    private MyCheckout myCheckout;

    public void setData(CheckOutOrder checkOutOrder) {
        this.chekOutItem = checkOutOrder;
        nameProductLabel.setText("PRODUCT : " + checkOutOrder.getNameProduct());
//        nameProductLabel.setAlignment(Pos.CENTER);
        priceLabel.setText("PRICE : " + String.valueOf(checkOutOrder.getPrice()) + " BAHT");
//        priceLabel.setAlignment(Pos.CENTER);
        sizeLabel.setText("SIZE : " + checkOutOrder.getSize());
        quantityLabel.setText( "QUANTITY : " + String.valueOf(checkOutOrder.getQuantity()));
        Image image = new Image(getClass().getResourceAsStream(checkOutOrder.getImgSrc()));
        clothesImg.setImage(image);
    }
}
