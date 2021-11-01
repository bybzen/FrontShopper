package controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import models.Items;
import service.MyListener;

public class ItemController {

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(items);
    }

    private Items items;
    private MyListener myListener;

    @FXML
    private ImageView clothesImg;
    @FXML
    private Label clothesNameLabel,priceLable;

    private TextFlow text_flow = new TextFlow();



    public void setData(Items items, MyListener myListener) {
        this.items = items;
        this.myListener = myListener;
        clothesNameLabel.setText(items.getNameProduct());
        clothesNameLabel.setAlignment(Pos.CENTER);
        priceLable.setText(String.valueOf(items.getPrice()) + " BAHT");
        priceLable.setAlignment(Pos.CENTER);
        Image image = new Image(getClass().getResourceAsStream(items.getImgSrc()));
        clothesImg.setImage(image);
    }
}
