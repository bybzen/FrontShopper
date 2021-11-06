package controllers.Admin;

import config.ComponentConfig;
import controllers.Customer.CustomerMenuController;
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

public class OrderOfCustomerController {

    @FXML
    private Button logoHomeBtn;

    @FXML
    private GridPane grid;

    private ItemManagement itemManagement;
    private ShopperApplicationAPIService service;

    @FXML
    public void initialize(){
        itemManagement = new ItemManagement();
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        service = context.getBean(ShopperApplicationAPIService.class);
        itemManagement.setCustomerOrderedMapFromList(service.getAllCustomerOrdered());

        int column = 0;
        int row = 1;
        try {
            grid.getChildren().clear();
            for (int i = 0; i < itemManagement.getAllCustomerOrderedList().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/all_ordered_item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AllOrderedItemController allOrderedItemController = fxmlLoader.getController();
                allOrderedItemController.setData(itemManagement.getAllCustomerOrderedList().get(i));

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
    public void handleLogoHomeBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin_menu.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        AdminMenuController ap = loader.getController();
        stage.show();
    }

//    public void setAccountManagement(AccountManagement accountManagement){
//        this.accountManagement = accountManagement;
//    }
//
//    public void setService(ShopperApplicationAPIService service){
//        this.service = service;
//    }
}
