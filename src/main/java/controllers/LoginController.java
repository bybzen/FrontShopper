package controllers;

import config.ComponentConfig;
import controllers.Admin.AdminMenuController;
import controllers.Customer.MainPageLoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button logoHomebtn;
    @FXML
    private Button registerBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField loginUsernameField;
    @FXML
    private PasswordField loginPasswordField;

    private ShopperApplicationAPIService service;
    private AccountManagement accountManagement;

    @FXML
    public void initialize(){
        accountManagement = new AccountManagement();
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
        service = context.getBean(ShopperApplicationAPIService.class);
        accountManagement.setCustomerMapFromList(service.getAllCustomer());
        accountManagement.setAdminMapFromList(service.getAdmin());
    }

    @FXML
    public void handleLogoHomeBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainpage.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        MainPageController ap = loader.getController();
        stage.show();
    }

    @FXML
    public void handleLoginBtn(ActionEvent event) throws IOException{
        if (accountManagement.checkCustomerAccount(loginUsernameField.getText(),loginPasswordField.getText())) {
            Button a = (Button) event.getSource();
            Stage stage = (Stage) a.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainpage_login.fxml"));
            stage.setScene(new Scene(loader.load(), 1280, 720));
            MainPageLoginController register = loader.getController();
            register.setAccountManagement(accountManagement);
            register.setService(service);
            stage.show();
        }
        else if (loginUsernameField.getText().equals("") || loginPasswordField.getText().equals("")){
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("ERROR");
            warning.setContentText(null);
            warning.setHeaderText("Please enter your information completely.");
            warning.showAndWait();
        }
        else if (accountManagement.checkAdminAccount(loginUsernameField.getText(),loginPasswordField.getText())){
            Button a = (Button) event.getSource();
            Stage stage = (Stage) a.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin_menu.fxml"));
            stage.setScene(new Scene(loader.load(), 1280, 720));
            AdminMenuController register = loader.getController();
            register.setAccountManagement(accountManagement);
            register.setService(service);
            stage.show();
        }
        else {
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("ERROR");
            warning.setContentText("Please try again.");
            warning.setHeaderText("Your Username/Password doesn't match our record.");
            warning.showAndWait();
            loginUsernameField.clear();
            loginPasswordField.clear();
        }

    }

    @FXML
    public void handleRegisterBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/register.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        RegisterController register = loader.getController();
        register.setAccountManagement(accountManagement);
        register.setService(service);
        stage.show();
    }

    @FXML
    public void handleChangePasswordBtn(ActionEvent event) throws IOException{
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/change_password.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        ChangePasswordController register = loader.getController();
        stage.show();
    }

    public void setAccountManagement(AccountManagement accountManagement){
        this.accountManagement = accountManagement;
    }

    public void setService(ShopperApplicationAPIService service){
        this.service = service;
    }


}
