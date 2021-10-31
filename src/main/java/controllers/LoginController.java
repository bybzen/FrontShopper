package controllers;

import config.ComponentConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        if(accountManagement.checkAdminAccount(loginUsernameField.getText(), loginPasswordField.getText()).equals("adminLogin")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/checkout.fxml")); // ต้องเปลี่ยน
            stage.setScene(new Scene(loader.load(), 1280, 720));
            CheckOutController login = loader.getController();
            login.setAccountManagement(accountManagement);
            login.setService(service);
            System.out.println("1");
        }
        else if (accountManagement.checkCustomerAccount(loginUsernameField.getText(), loginPasswordField.getText()).equals("customerLogin")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainpage_login.fxml"));
            stage.setScene(new Scene(loader.load(), 1280, 720));
            MainPageLoginController login = loader.getController();
            login.setAccountManagement(accountManagement);
            login.setService(service);
            System.out.println("2");
        }
        else if (loginUsernameField.getText().equals("") || loginPasswordField.getText().equals("")){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR");
            error.setContentText("Please enter Username/Password.");
            error.setHeaderText(null);
            error.showAndWait();
        }
        else if(accountManagement.checkAdminAccount(loginUsernameField.getText(), loginPasswordField.getText()).equals("null")){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Username/Password");
            error.setContentText("The username/password you entered did not match.");
            error.setHeaderText("Please try again");
            error.showAndWait();
            loginUsernameField.clear();
            loginPasswordField.clear();
        }
        else if (accountManagement.checkCustomerAccount(loginUsernameField.getText(), loginPasswordField.getText()).equals("null")){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Username/Password");
            error.setContentText("The username/password you entered did not match.");
            error.setHeaderText("Please try again");
            error.showAndWait();
            loginUsernameField.clear();
            loginPasswordField.clear();
        }
        else if (accountManagement.checkCustomerAccount(loginUsernameField.getText(), loginPasswordField.getText()).equals("adminNull")){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Username/Password");
            error.setContentText("The username/password you entered did not match.");
            error.setHeaderText("Please try again");
            error.showAndWait();
            loginUsernameField.clear();
            loginPasswordField.clear();
        }
        else if (accountManagement.checkCustomerAccount(loginUsernameField.getText(), loginPasswordField.getText()).equals("customerNull")){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Username/Password");
            error.setContentText("The username/password you entered did not match.");
            error.setHeaderText("Please try again");
            error.showAndWait();
            loginUsernameField.clear();
            loginPasswordField.clear();
        }
        else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Username/Password");
            error.setContentText("The username/password you entered did not match.");
            error.setHeaderText("Please try again");
            error.showAndWait();
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


}
