package controllers;

import config.ComponentConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Admin;
import models.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.AccountManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;
import java.util.Optional;

public class ChangePasswordController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField newPasswordField, confirmPasswordField,oldPasswordField;
    @FXML
    private Button changePasswordBtn;

    private ShopperApplicationAPIService service;
    private AccountManagement accountManagement;
    private Customer currentCustomer;
    private Admin currentAdmin;

    @FXML
    public void initialize() {
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
    public void handleSubmitChangePasswordBtn(ActionEvent event) throws IOException {

        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        if (usernameField.getText().equals("") || newPasswordField.getText().equals("") || confirmPasswordField.getText().equals("")) {
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("ERROR");
            warning.setContentText(null);
            warning.setHeaderText("Please enter your information completely.");
            warning.showAndWait();
        } else if (accountManagement.checkCustomerAccount(usernameField.getText(),oldPasswordField.getText())) {
            if (newPasswordField.getText().equals(confirmPasswordField.getText())) {
                Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
                a1.setTitle("Confirm");
                a1.setContentText("Confirm to change password ?");
                a1.setHeaderText(null);
                ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
                a1.getButtonTypes().setAll(buttonYes, buttonNo);
                Optional<ButtonType> result = a1.showAndWait();
                if (result.get() == buttonYes) {

                    accountManagement.setCustomerNow(accountManagement.getCustomerAccount(usernameField.getText()));
                    accountManagement.getCustomerNow().setPassword(newPasswordField.getText());
                    service.updateCustomer(accountManagement.getCustomerNow());


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                    stage.setScene(new Scene(loader.load(), 1280, 720));
                    LoginController register = loader.getController();
                    stage.show();

                }
            } else if (!newPasswordField.getText().equals(confirmPasswordField.getText())) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Password");
                error.setContentText("Please try again");
                error.setHeaderText("Your confirm password doesn't match !");
                error.showAndWait();
                newPasswordField.clear();
                confirmPasswordField.clear();
                oldPasswordField.clear();
            }
        } else if (accountManagement.checkAdminAccount(usernameField.getText(),oldPasswordField.getText())) {
            if (newPasswordField.getText().equals(confirmPasswordField.getText())) {
                Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
                a1.setTitle("Confirm");
                a1.setContentText("Confirm to change password ?");
                a1.setHeaderText(null);
                ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
                a1.getButtonTypes().setAll(buttonYes, buttonNo);
                Optional<ButtonType> result = a1.showAndWait();
                if (result.get() == buttonYes) {
                    accountManagement.setAdminNow(accountManagement.getAdminAccount(usernameField.getText()));
                    accountManagement.getAdminNow().setPassword(newPasswordField.getText());
                    service.updateAdmin(accountManagement.getAdminNow());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                    stage.setScene(new Scene(loader.load(), 1280, 720));
                    LoginController register = loader.getController();
                    stage.show();

                }
            } else if (!newPasswordField.getText().equals(confirmPasswordField.getText())) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Password");
                error.setContentText("Please try again");
                error.setHeaderText("Your confirm password doesn't match !");
                error.showAndWait();
                newPasswordField.clear();
                confirmPasswordField.clear();
                oldPasswordField.clear();
            }
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("USERNAME/PASSWORD");
            error.setContentText("Please try again");
            error.setHeaderText("The username/password you entered did not match with our records.");
            error.showAndWait();
            newPasswordField.clear();
            confirmPasswordField.clear();
            usernameField.clear();
            oldPasswordField.clear();
        }
    }
}


