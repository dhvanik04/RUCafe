package cafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the Main class to handle the GUI
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class Main extends Application
{

    /**
     * Starts the GUI and opens the first window of RU cafe
     *
     * @param primaryStage for the RUCafe GUI
     * @throws Exception for any runtime errors
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
        primaryStage.setTitle("RU Cafe");
        primaryStage.setScene(new Scene(root, 600, 430));
        primaryStage.show();
    }


    /**
     * Main method in this class
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
