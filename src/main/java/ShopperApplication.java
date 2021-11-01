import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ShopperApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/mainpage.fxml"));
        primaryStage.setTitle("Shopper Clothes Application");
        primaryStage.getIcons().add(new Image("/images/clothes-hanger.png"));
        primaryStage.setScene(new Scene(root));
//        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
