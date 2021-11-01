package controllers;

import config.ComponentConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Items;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ItemManagement;
import service.MyListener;
import service.ShopperApplicationAPIService;


import java.io.IOException;


public class MainPageController {

    @FXML
    private Button logoHomebtn,loginHomebtn,addToCartBtn,searchbtn;
    @FXML
    private VBox chosenClothesCard;
    @FXML
    private Label clothesNameLabel,prizeClothesLabel;
    @FXML
    private ImageView clothesImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private ComboBox<String> sizeClothes;
    @FXML
    private ComboBox<Integer> quantityClothes;
    @FXML
    private TextField searchBox;

    private ShopperApplicationAPIService service;
    private Image image;
    private MyListener myListener;
    private ItemManagement itemManagement;


    @FXML
    public void initialize(){
        itemManagement = new ItemManagement();
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        service = context.getBean(ShopperApplicationAPIService.class);
        quantityClothes.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        sizeClothes.getItems().addAll("S","M","L","XL");
        itemManagement.setItemMapFromList(service.getAllItems());
        System.out.println(itemManagement.getItemsList());
        System.out.println(itemManagement.getItemsList().get(0));
        System.out.println(itemManagement.getItemsList().get(0).getNameProduct());

        if (itemManagement.getItemsList().size() > 0) {
            setChosenItem(itemManagement.getItemsList().get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Items items) {
                    setChosenItem(items);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < itemManagement.getItemsList().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(itemManagement.getItemsList().get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





//    @FXML
//    public void initialize(){
//        itemManagement = new ItemManagement();
//        service = context.getBean(ShopperApplicationAPIService.class);
//        itemManagement.setItemMapFromList(service.getAllItems());
//        System.out.println(itemManagement.getItemsMap());
//        itemManagement.setKeyAndValue(itemManagement.getItemsMap());
//        for (Object key : itemManagement.getMap().keySet()) {
//            Object value = itemManagement.getMap().get(key);
//            System.out.println(value);
//
//        }
//        System.out.println(itemManagement.getMap().keySet());
////        itemManagement.getItemsMap().put(itemManagement.)
//            if (itemManagement.getItemsMap().size() > 0) {
////                setChosenFruit();
//                myListener = new MyListener() {
//                    @Override
//                    public void onClickListener(Items items) {
//                        setChosenFruit(itemManagement.getItemsMap().get(items));
//
//                    }
//                };
//            }
//            int column = 0;
//            int row = 1;
//            try {
//                for (int i = 0; i < itemManagement.getItemsMap().size(); i++) {
//                    FXMLLoader fxmlLoader = new FXMLLoader();
//                    fxmlLoader.setLocation(getClass().getResource("item.fxml"));
//                    AnchorPane anchorPane = fxmlLoader.load();
//
//                    ItemController itemController = fxmlLoader.getController();
//                    itemController.setData(itemManagement.getItemsMap().get(i),myListener);
//
//                    if (column == 3) {
//                        column = 0;
//                        row++;
//                    }
//
//                    grid.add(anchorPane, column++, row); //(child,column,row)
//                    //set grid width
//                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                    grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                    //set grid height
//                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                    grid.setMaxHeight(Region.USE_PREF_SIZE);
//
//                    GridPane.setMargin(anchorPane, new Insets(10));
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

    @FXML
    public void handleAddCartBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        LoginController ap = loader.getController();
        stage.show();
    }

    @FXML
    public void handleLoginMenuBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        LoginController ap = loader.getController();
        stage.show();
    }

    private void setChosenItem(Items items) {
        clothesNameLabel.setText(items.getNameProduct());
        clothesNameLabel.setAlignment(Pos.CENTER);
        prizeClothesLabel.setText(String.valueOf(items.getPrice()) + " BAHT");
        prizeClothesLabel.setAlignment(Pos.CENTER);
        image = new Image(getClass().getResourceAsStream(items.getImgSrc()));
        clothesImg.setImage(image);
    }


}
