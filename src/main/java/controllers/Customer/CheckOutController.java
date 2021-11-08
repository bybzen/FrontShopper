package controllers.Customer;

import config.ComponentConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import models.CheckOutOrder;
import models.CustomerOrdered;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountManagement;
import service.ItemManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

public class CheckOutController {

    private ItemManagement itemManagement;
    private ShopperApplicationAPIService service;
    private AccountManagement accountManagement;

    @FXML
    private Button logoHomeBtn,continueBtn;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private Label addressLabel,totalLabel;


    @FXML
    public void initialize(){
        itemManagement = new ItemManagement();
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        service = context.getBean(ShopperApplicationAPIService.class);
    }

    @FXML
    public void handleLogoHomeBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainpage_login.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        MainPageLoginController ap = loader.getController();
        ap.setService(service);
        ap.setAccountManagement(accountManagement);
//        ap.setItemManagement(itemManagement);
        stage.show();
    }

    @FXML
    public void handleContinueBtn(ActionEvent event) throws IOException {
        if (itemManagement.getCheckOutOrderListOfUsername().size() > 0 ){
            Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
            a1.setTitle("Checkout Order");
            a1.setContentText("Confirm to checkout your order ?");
            a1.setHeaderText(null);
            ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            a1.getButtonTypes().setAll(buttonYes, buttonNo);
            Optional<ButtonType> result = a1.showAndWait();
            if (result.get() == buttonYes) {
                for (CheckOutOrder checkOutOrder : itemManagement.getCheckOutOrderListOfUsername()) {
                    String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
                    //                CustomerOrdered newCustomerOrdered = new CustomerOrdered(checkOutOrder.getPurchaseOrderId(),checkOutOrder.getUsername(),"Already Shipping",dateTime,itemManagement.getTotal());
                    CustomerOrdered newCustomerOrdered = new CustomerOrdered(checkOutOrder.getPurchaseOrderId(), checkOutOrder.getUsername(), checkOutOrder.getNameProduct(), checkOutOrder.getPrice(), checkOutOrder.getQuantity(),
                            checkOutOrder.getSize(), "Already Shipping", dateTime, itemManagement.getTotal(), checkOutOrder.getImgSrc());
                    service.addCustomerOrdered(newCustomerOrdered);
                    service.deleteCheckOutOrder(checkOutOrder);
                    Button a = (Button) event.getSource();
                    Stage stage = (Stage) a.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainpage_login.fxml"));
                    stage.setScene(new Scene(loader.load(), 1280, 720));
                    MainPageLoginController ap = loader.getController();
                    ap.setService(service);
                    ap.setAccountManagement(accountManagement);
                    //                ap.setItemManagement(itemManagement);
                    stage.show();
                }
            }
        }
        else {
            continueBtn.setDefaultButton(true);
//            Alert a1 = new Alert(Alert.AlertType.INFORMATION);
//            a1.setTitle("ERROR");
//            a1.setContentText("There is no item in a basket.");
//            a1.setHeaderText("Please add new item");
        }
    }

    @FXML
    public void handleDeleteBtn(ActionEvent event) throws IOException {
        Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
        a1.setTitle("Delete Order");
        a1.setContentText("Confirm to delete your order ?");
        a1.setHeaderText(null);
        ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        a1.getButtonTypes().setAll(buttonYes, buttonNo);
        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == buttonYes) {
            for (CheckOutOrder checkOutOrder : itemManagement.getCheckOutOrderListOfUsername()) {
                service.deleteCheckOutOrder(checkOutOrder);
                Button a = (Button) event.getSource();
                Stage stage = (Stage) a.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/check_out.fxml"));
                stage.setScene(new Scene(loader.load(), 1280, 720));
                CheckOutController ap = loader.getController();
                ap.setService(service);
                ap.setAccountManagement(accountManagement);
//                ap.setItemManagement(itemManagement);
                stage.show();
            }
        }
    }

    public void setItemManagement(ItemManagement itemManagement){
        this.itemManagement = itemManagement;
        itemManagement.setCheckoutMapFromList(service.getAllCheckOutOrder());
        itemManagement.checkOutOrderListOfUsername(accountManagement.getCustomerNow().getUsername());
        addressLabel.setText(accountManagement.getCustomerNow().getFirstName() + " " + accountManagement.getCustomerNow().getLastName() + "\n\n" + accountManagement.getCustomerNow().getAddress() +"\n\nTel : "+ accountManagement.getCustomerNow().getPhone());
        System.out.println(itemManagement.getTotal());
        totalLabel.setText(String.valueOf(itemManagement.getTotal()) + " BAHT");
        totalLabel.setAlignment(Pos.CENTER_RIGHT);

        int column = 0;
        int row = 1;
        try {
            grid.getChildren().clear();
            for (int i = 0; i < itemManagement.getCheckOutOrderListOfUsername().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/check_out_item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CheckOutItemController checkOutItemController = fxmlLoader.getController();
                checkOutItemController.setData(itemManagement.getCheckOutOrderListOfUsername().get(i));

                if (column == 1) {
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

                GridPane.setMargin(anchorPane, new Insets(15));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setAccountManagement(AccountManagement accountManagement){
        this.accountManagement = accountManagement;
        System.out.println(accountManagement.getCustomerNow().getUsername());
    }

    public void setService(ShopperApplicationAPIService service){
        this.service = service;
    }
}
