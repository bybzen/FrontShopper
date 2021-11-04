package controllers.Customer;

import controllers.MainPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.AccountManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;

public class CustomerMenuController {

    @FXML
    private Button logoutBtn,myOrdersBtn,editProfileBtn,logoHomeBtn;

    private AccountManagement accountManagement;
    private ShopperApplicationAPIService service;

    @FXML
    public void handleLogoHomeBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainpage_login.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        MainPageLoginController ap = loader.getController();
        ap.setAccountManagement(accountManagement);
        ap.setService(service);
        stage.show();
    }
    @FXML
    public void handleEditProfileBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/customer_edit_profile.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        CustomerEditProfileController ap = loader.getController();
        ap.setAccountManagement(accountManagement);
        ap.setService(service);
        System.out.println(accountManagement.getCustomerNow().getUsername());
        System.out.println(accountManagement.getCustomerNow().getFirstName());
        System.out.println(accountManagement.getCustomerNow().getLastName());
        System.out.println(accountManagement.getCustomerNow().getPhone());
        System.out.println(accountManagement.getCustomerNow().getAddress());
        stage.show();
    }

    @FXML
    public void handleMyOrderBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/customer_orders.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        CustomerOrdersController ap = loader.getController();
        ap.setAccountManagement(accountManagement);
        ap.setService(service);
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
