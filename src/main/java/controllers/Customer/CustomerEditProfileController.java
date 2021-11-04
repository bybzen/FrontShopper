package controllers.Customer;

import controllers.MainPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.AccountManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;
import java.util.Optional;

public class CustomerEditProfileController {

    @FXML
    private Button logoHomeBtn,submitEditField;

    @FXML
    private TextField firstNameEditField,lastNameEditField,addressEditField,phoneEditField;


    private AccountManagement accountManagement;
    private ShopperApplicationAPIService service;

    public void setAccountManagement(AccountManagement accountManagement){
        this.accountManagement = accountManagement;
        firstNameEditField.setText(accountManagement.getCustomerNow().getFirstName());
        lastNameEditField.setText(accountManagement.getCustomerNow().getLastName());
        phoneEditField.setText(accountManagement.getCustomerNow().getPhone());
        addressEditField.setText(accountManagement.getCustomerNow().getAddress());
    }

    public void setService(ShopperApplicationAPIService service){
        this.service = service;
    }


    @FXML
    public void handleLogoHomeBtn(ActionEvent event) throws IOException {
        Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
        a1.setTitle("Go Back To Menu");
        a1.setContentText("Confirm to go back to menu ?");
        a1.setHeaderText("Your profile never edit yet.");
        ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        a1.getButtonTypes().setAll(buttonYes, buttonNo);
        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == buttonYes) {
            Button a = (Button) event.getSource();
            Stage stage = (Stage) a.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/customer_menu.fxml"));
            stage.setScene(new Scene(loader.load(), 1280, 720));
            CustomerMenuController ap = loader.getController();
            ap.setAccountManagement(accountManagement);
            ap.setService(service);
            stage.show();
        }
    }

    @FXML
    public void handleSubmitEditBtn(ActionEvent event) throws IOException {
        if (!accountManagement.getCustomerNow().getFirstName().equals(firstNameEditField.getText()) ||
                !accountManagement.getCustomerNow().getLastName().equals(lastNameEditField.getText()) ||
                !accountManagement.getCustomerNow().getPhone().equals(phoneEditField.getText()) ||
                !accountManagement.getCustomerNow().getAddress().equals(addressEditField.getText())) {
            Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
            a1.setTitle("Confirm");
            a1.setContentText("Confirm to edit your profile ?");
            a1.setHeaderText(null);
            ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            a1.getButtonTypes().setAll(buttonYes, buttonNo);
            Optional<ButtonType> result = a1.showAndWait();
            if (result.get() == buttonYes) {
                accountManagement.getCustomerNow().setFirstName(firstNameEditField.getText());
                accountManagement.getCustomerNow().setLastName(lastNameEditField.getText());
                accountManagement.getCustomerNow().setPhone(phoneEditField.getText());
                accountManagement.getCustomerNow().setAddress(addressEditField.getText());
                service.updateCustomer(accountManagement.getCustomerNow());
                Button a = (Button) event.getSource();
                Stage stage = (Stage) a.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/customer_menu.fxml"));
                stage.setScene(new Scene(loader.load(), 1280, 720));
                CustomerMenuController ap = loader.getController();
                ap.setAccountManagement(accountManagement);
                ap.setService(service);
                stage.show();
            }
        }
        else {
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setTitle("ERROR");
            warning.setContentText(null);
            warning.setHeaderText("Your profile never edit yet.");
            warning.showAndWait();

        }
    }
}
