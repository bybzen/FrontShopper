package controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.AccountManagement;
import service.ShopperApplicationAPIService;

import java.io.IOException;

public class CustomerInfoController {
    @FXML
    private Button logoHomeBtn;

    private ShopperApplicationAPIService service;


    public void setService(ShopperApplicationAPIService service){
        this.service = service;
    }

    @FXML
    public void handleLogoHomeBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin_menu.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        AdminMenuController ap = loader.getController();
        stage.show();
    }
}
