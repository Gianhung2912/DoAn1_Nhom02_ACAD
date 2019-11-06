package ACAD_Application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application
{
    public static boolean isESCPressed;
    public static Parent root;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = FXMLLoader.load(getClass().getResource("MainSence.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setTitle("ACAD");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        this.stage = primaryStage;

        this.isESCPressed = false;

        root.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent ke)
            {
                if (ke.getCode() == KeyCode.ESCAPE)
                {
                    isESCPressed = true;
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
