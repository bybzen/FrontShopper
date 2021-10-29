package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.AccountManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;

public class RegisterController {

    @FXML
    private Button LogoHomeBtn;
    @FXML
    private TextField firstNameTextField,lastNameTextField,userNameTextField,telephoneField,addressField;
    @FXML
    private PasswordField passwordField,confirmPasswordField;

    private ShopperApplicationAPIService service;
    private AccountManagement accountManagement;

    public void setAccountManagement(AccountManagement accountManagement){
        this.accountManagement = accountManagement;
    }

    public void setService(ShopperApplicationAPIService service){
        this.service = service;
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

    }

}
