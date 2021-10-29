package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.AccountManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;

public class MainPageLoginController {
    @FXML
    private Button shoppingCartbtn;

    private AccountManagement accountManagement;
    private ShopperApplicationAPIService service;

    @FXML
    public void handleShoppingCartBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/shoppingCart.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        ShoppingCartController ap = loader.getController();
        stage.show();
    }

    public void setAccountManagement(AccountManagement accountManagement){
        this.accountManagement = accountManagement;
    }

    public void setService(ShopperApplicationAPIService service){
        this.service = service;
    }
}
