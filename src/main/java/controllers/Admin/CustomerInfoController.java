package controllers.Admin;

import config.ComponentConfig;
import controllers.ItemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import models.Items;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountManagement;
import service.MyListener;
import service.ShopperApplicationAPIService;

import java.io.IOException;

public class CustomerInfoController {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button logoHomeBtn;

    private ShopperApplicationAPIService service;
    private AccountManagement accountManagement;
//    private MyListener myListener;



    public void setService(ShopperApplicationAPIService service){
        this.service = service;
    }

    public void setAccountManagement(AccountManagement accountManagement){
        this.accountManagement = accountManagement;
    }

    @FXML
    public void initialize(){
        accountManagement = new AccountManagement();
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        service = context.getBean(ShopperApplicationAPIService.class);
        accountManagement.setCustomerMapFromList(service.getAllCustomer());

        int column = 0;
        int row = 1;
        try {
            grid.getChildren().clear();
            for (int i = 0; i < accountManagement.getCustomerMap().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/customer.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CustomerController customerController = fxmlLoader.getController();
                customerController.setData(accountManagement.getCustomerMap().get(i));

                if (column == 2) {
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

                GridPane.setMargin(anchorPane, new Insets(50));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void handleLogoHomeBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin_menu.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        AdminMenuController ap = loader.getController();
        stage.show();
    }
}
