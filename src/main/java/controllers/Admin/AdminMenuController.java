package controllers.Admin;

import config.ComponentConfig;
import controllers.Customer.CustomerOrdersController;
import controllers.MainPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;

public class AdminMenuController {

    @FXML
    private Button logoHomeBtn,customerInfoBtn,customerOrderBtn,logoutBtn;
    AccountManagement accountManagement;
    ShopperApplicationAPIService service;

    @FXML
    public void initialize() {
        accountManagement = new AccountManagement();
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        service = context.getBean(ShopperApplicationAPIService.class);
        accountManagement.setCustomerMapFromList(service.getAllCustomer());
    }


    @FXML
    public void handleCustomerInfoBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin_customerInfo.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        CustomerInfoController ap = loader.getController();
        ap.setService(service);

        stage.show();
    }

    @FXML
    public void handleCustomerOrderBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin_order_of_customer.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        CustomerOrdersController ap = loader.getController();
        ap.setService(service);
        ap.setAccountManagement(accountManagement);
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



    public void setAccountManagement(AccountManagement accountManagement){
        this.accountManagement = accountManagement;
    }

    public void setService(ShopperApplicationAPIService service){
        this.service = service;
    }
}
