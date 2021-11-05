package controllers.Customer;

import config.ComponentConfig;
import controllers.ItemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Items;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountManagement;
import service.ItemManagement;
import service.MyListener;
import service.ShopperApplicationAPIService;

import java.io.IOException;

public class CheckOutController {

    private ItemManagement itemManagement;
    private ShopperApplicationAPIService service;
    private AccountManagement accountManagement;
    private MyListener myListener;

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
