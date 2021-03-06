package controllers.Customer;

import config.ComponentConfig;
import controllers.ItemController;
import controllers.LoginController;
import controllers.MainPageController;
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
import models.CheckOutOrder;
import models.Customer;
import models.Items;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountManagement;
import service.ItemManagement;
import service.MyListener;
import service.ShopperApplicationAPIService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MainPageLoginController {
    @FXML
    private Button logoutBtn,checkoutBtn,customerMenuBtn,searchbtn,logoHomebtn,addToCartBtn;
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

    private AccountManagement accountManagement;
    private ShopperApplicationAPIService service;
    private Image image;
    private MyListener myListener;
    private ItemManagement itemManagement;
    private Items chosenItem;


    @FXML
    public void initialize(){
        itemManagement = new ItemManagement();
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        service = context.getBean(ShopperApplicationAPIService.class);
        quantityClothes.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        sizeClothes.getItems().addAll("S","M","L","XL");
        itemManagement.setItemMapFromList(service.getAllItems());
        startRunClothes(itemManagement.getItemsList());
    }

    private void startRunClothes(List<Items> items){
        if (items.size() > 0) {
            setChosenItem(items.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Items items) {
                    sizeClothes.getSelectionModel().clearSelection();
                    quantityClothes.getSelectionModel().clearSelection();
                    setChosenItem(items);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            grid.getChildren().clear();
            for (int i = 0; i < items.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(items.get(i),myListener);

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

    @FXML
    public void handleCheckoutBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/check_out.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        CheckOutController ap = loader.getController();
        ap.setAccountManagement(accountManagement);
        ap.setItemManagement(itemManagement);
        ap.setService(service);
        stage.show();
    }
    @FXML
    public void LogoHomebtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainpage_login.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        MainPageLoginController ap = loader.getController();
        ap.setAccountManagement(accountManagement);
        ap.setService(service);
        System.out.println(accountManagement.getCustomerNow().getUsername());
//        grid.getChildren().clear();
        stage.show();
    }

    @FXML
    public void handleLogoutBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainpage.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        MainPageController ap = loader.getController();
        stage.show();
    }
    @FXML
    public void handleCustomerMenuBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/customer_menu.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        CustomerMenuController ap = loader.getController();
        ap.setAccountManagement(accountManagement);
        ap.setService(service);
        ap.setItemManagement(itemManagement);
        System.out.println(accountManagement.getCustomerNow().getUsername());
        stage.show();
    }
    @FXML
    public void handleAddCartBtn(ActionEvent event) throws IOException {
        if (sizeClothes.getValue() == null || quantityClothes.getValue() == null){
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("ERROR");
            warning.setContentText(null);
            warning.setHeaderText("Please select quantity and size completely.");
            warning.showAndWait();
        }
        else {
            Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
            a1.setTitle("Confirm");
            a1.setContentText("Confirm to add item to a cart ?");
            a1.setHeaderText(null);
            ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            a1.getButtonTypes().setAll(buttonYes, buttonNo);
            Optional<ButtonType> result = a1.showAndWait();
            if (result.get() == buttonYes) {
                String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
                CheckOutOrder newCheckOutOrder = new CheckOutOrder(accountManagement.getCustomerNow().getUsername(),chosenItem.getNameProduct(),chosenItem.getPrice(),
                        chosenItem.getPrice()*quantityClothes.getValue(),sizeClothes.getValue(),quantityClothes.getValue(),dateTime,accountManagement.getCustomerNow().getAddress(),chosenItem.getImgSrc());
                service.addCheckOutOrder(newCheckOutOrder);
                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setTitle("Done");
                warning.setContentText(null);
                warning.setHeaderText("Your item is already added.");
                warning.showAndWait();
            }
        }
//        Button a = (Button) event.getSource();
//        Stage stage = (Stage) a.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
//        stage.setScene(new Scene(loader.load(), 1280, 720));
//        LoginController ap = loader.getController();
//        stage.show();
    }

    @FXML
    public void handleSearchItem(ActionEvent event) throws IOException {
        startRunClothes(searchList(searchBox.getText(), itemManagement.getItemsList()));
    }

    private List<Items> searchList(String searchWords, List<Items> AllItemList){
        List<String> searchWordArray = Arrays.asList(searchWords.trim().split(" "));

        return AllItemList.stream().filter(input -> {
            return searchWordArray.stream().allMatch(word ->
                    input.getNameProduct().toUpperCase().contains(word.toUpperCase()));
        }).collect(Collectors.toList());

    }


    private void setChosenItem(Items items) {
        clothesNameLabel.setText(items.getNameProduct());
        clothesNameLabel.setAlignment(Pos.CENTER);
        prizeClothesLabel.setText(String.valueOf(items.getPrice()) + " BAHT");
        prizeClothesLabel.setAlignment(Pos.CENTER);
        image = new Image(getClass().getResourceAsStream(items.getImgSrc()));
        clothesImg.setImage(image);
        this.chosenItem = items;
    }

    public void setAccountManagement(AccountManagement accountManagement){
        this.accountManagement = accountManagement;
    }

    public void setService(ShopperApplicationAPIService service){
        this.service = service;

    }

//    public void setCustomerNow(Customer customerNow) {
//
//        this.customerNow = customerNow;
//    }
}
