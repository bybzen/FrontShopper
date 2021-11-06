package controllers.Customer;

import config.ComponentConfig;
import controllers.Admin.CustomerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountManagement;
import service.ItemManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;

public class CustomerOrdersController {

    @FXML
    private Button logoHomeBtn;

    @FXML
    private GridPane grid;

    private AccountManagement accountManagement;
    private ItemManagement itemManagement;
    private ShopperApplicationAPIService service;

    public void setAccountManagement(AccountManagement accountManagement){
        this.accountManagement = accountManagement;

    }

    public void setService(ShopperApplicationAPIService service){
        this.service = service;
    }

    public void setItemManagement(ItemManagement itemManagement) {
        this.itemManagement = itemManagement;
        itemManagement.setCustomerOrderedMapFromList(service.getAllCustomerOrdered());
        itemManagement.customerOrderListOfUsername(accountManagement.getCustomerNow().getUsername());

        int column = 0;
        int row = 1;
        try {
            grid.getChildren().clear();
            for (int i = 0; i < itemManagement.getCustomerOrderListOfUsername().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/customer_ordered_item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CustomerOrderedItemController customerOrderedItemController = fxmlLoader.getController();
                customerOrderedItemController.setData(itemManagement.getCustomerOrderListOfUsername().get(i));

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

                GridPane.setMargin(anchorPane, new Insets(75));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/customer_menu.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        CustomerMenuController ap = loader.getController();
        ap.setAccountManagement(accountManagement);
        ap.setService(service);
        ap.setItemManagement(itemManagement);
        stage.show();
    }
}
