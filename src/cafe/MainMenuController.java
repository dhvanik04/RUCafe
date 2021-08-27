package cafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the Main class to handle the GUI
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class MainMenuController implements Initializable
{
    /**
     * instance of order
     */
    protected static Order order;

    /**
     * instance of store orders
     */
    protected static StoreOrders store;

    /**
     * initializes the view of the 'RU Cafe' home screen when window is opened up
     *
     * @param url            of the corresponding screen
     * @param resourceBundle of the corresponding screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        order = new Order();
        store = new StoreOrders();
    }

    /**
     * This launches the Order Coffee screen for the coffee orders to be placed
     *
     * @param event event triggered by clicking on the order coffee button
     * @throws IOException handles IO Exception
     */
    @FXML
    void orderCoffee(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderCoffeeView.fxml"));
        OrderCoffeeController coffeeViewController = loader.getController(); //get the reference of the controller ...
        coffeeViewController.setMainController(this);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Order Coffee");
        stage.show();
    }

    /**
     * This launches the Order Donuts screen for the donut orders to be placed
     *
     * @param event event triggered by clicking on the order donut button
     * @throws IOException handles IO Exception
     */
    @FXML
    void orderDonuts(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderDonutsView.fxml"));
        OrderDonutsController donutViewController = loader.getController(); //get the reference of the controller ...
        donutViewController.setMainController(this);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Order Donuts");
        stage.show();

    }

    /**
     * This launches the Store Orders screen where all the store orders can be viewed and deleted/exported.
     *
     * @param event event triggered by clicking on the store orders button
     * @throws IOException handles IO Exception
     */
    @FXML
    void storeOrders(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrdersView.fxml"));
        StoreOrdersController storeOrderViewController = loader.getController(); //get the reference of the controller ...
        storeOrderViewController.setMainController(this);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Store Orders");
        stage.show();
    }

    /**
     * This launches the Your Orders screen where all the items in this order can be viewed and deleted or placed as an order.
     *
     * @param event event triggered by clicking on the your order button
     * @throws IOException handles IO Exception
     */
    @FXML
    void yourOrder(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentOrderView.fxml"));
        CurrentOrderController yourOrderViewController = loader.getController(); //get the reference of the controller ...
        yourOrderViewController.setMainController(this);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Your Order");
        stage.show();
    }

}


