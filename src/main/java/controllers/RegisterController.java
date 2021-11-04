package controllers;

import config.ComponentConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class RegisterController {

    @FXML
    private Button LogoHomeBtn;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField addressField;
    @FXML
    private PasswordField passwordField,confirmPasswordField;

    private AccountManagement accountManagement;
    private ShopperApplicationAPIService service;

    public void setAccountManagement(AccountManagement accountManagement){
    }

    public void setService(ShopperApplicationAPIService service){
    }

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
    public void handleSignUpBtn(ActionEvent event) throws IOException{
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        if (!passwordField.getText().equals(confirmPasswordField.getText())){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Password");
            error.setContentText("Please try again");
            error.setHeaderText("Your confirm password doesn't match !");
            error.showAndWait();
            passwordField.clear();
            confirmPasswordField.clear();
        }
        else if (firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") ||
        userNameTextField.getText().equals("") || passwordField.getText().equals("") || confirmPasswordField.getText().equals("")
        || telephoneField.getText().equals("") || addressField.getText().equals("")){
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("ERROR");
            warning.setContentText(null);
            warning.setHeaderText("Please enter your information completely.");
            warning.showAndWait();
        }
        else if(userNameTextField.getText().equals("admin01")){
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("ERROR");
            warning.setContentText("Please try again");
            warning.setHeaderText("This username can't be use.");
            warning.showAndWait();
        }
        else if (accountManagement.checkCustomerUsername(userNameTextField.getText())){
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("ERROR");
            warning.setContentText("Please try again");
            warning.setHeaderText("This username has already been used.");
            warning.showAndWait();
        }
        else{
            Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
            a1.setTitle("Confirm");
            a1.setContentText("Confirm to create your account ?");
            a1.setHeaderText(null);
            ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            a1.getButtonTypes().setAll(buttonYes, buttonNo);
            Optional<ButtonType> result = a1.showAndWait();
            if (result.get() == buttonYes) {
                Customer newCustomer = new Customer(userNameTextField.getText(),passwordField.getText(),firstNameTextField.getText(),
                        lastNameTextField.getText(),addressField.getText(),telephoneField.getText());
                System.out.println(telephoneField.getText());
                service.addCustomer(newCustomer);
//                accountManagement.getCustomerMap().add(newCustomer);
//                accountManagement.getCustomerMap().put(newCustomer.getUsername(),newCustomer);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                stage.setScene(new Scene(loader.load(), 1280, 720));
                LoginController login = loader.getController();
                stage.show();
            }
        }

    }

}
