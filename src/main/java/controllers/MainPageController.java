package controllers;

import config.ComponentConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class MainPageController {

    @FXML
    private Button logoHomebtn;
    @FXML
    private Button loginHomebtn;
    @FXML
    private Button searchbtn;
    @FXML
    private VBox chosenClothesCard;
    @FXML
    private Label clothesNameLabel;
    @FXML
    private Label prizeClothesLabel;
    @FXML
    private ImageView clothesImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    public void initialize(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentConfig.class);
    }

    @FXML
    public void handleLoginMenuBtn(ActionEvent event) throws IOException {
        Button a = (Button) event.getSource();
        Stage stage = (Stage) a.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        stage.setScene(new Scene(loader.load(), 1280, 720));
        LoginController ap = loader.getController();
        stage.show();
    }

}
