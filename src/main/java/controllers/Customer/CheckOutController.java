package controllers.Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.AccountManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;

public class CheckOutController {

    @FXML
    private Button logoHomeBtn;

    @FXML
    public void handleLogoHomeBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainpage_login.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        MainPageLoginController ap = loader.getController();
        stage.show();
    }

    AccountManagement accountManagement;
    ShopperApplicationAPIService service;

    public void setAccountManagement(AccountManagement accountManagement){
        this.accountManagement = accountManagement;
    }

    public void setService(ShopperApplicationAPIService service){
        this.service = service;
    }
}
