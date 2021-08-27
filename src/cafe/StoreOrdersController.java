package cafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This is the controller class to handle store orders
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class StoreOrdersController implements Initializable
{
    /**
     * instance of an order
     */
    private static Order order;
    /**
     * object reference to Main Menu Controller
     */
    private static MainMenuController mainController;
    /**
     * object reference to Current Order Controller
     */
    private static CurrentOrderController orderController;
    /**
     * instance of store orders
     */
    private static StoreOrders store;

    /**
     * text field that displays the total cost for the current order
     */
    @FXML
    private TextField orderTotal;

    /**
     * combo box to chose the order number for the order that you want to look at
     */
    @FXML
    private ComboBox<String> OrderNumber;

    /**
     * List view displays the menuitems that have been ordered in the selected order number
     */
    @FXML
    private ListView<String> displayOrders;

    /**
     * This function is to cancel orders
     *
     * @param event event triggered when cancel order button is clicked
     */
    @FXML
    void cancelOrders(ActionEvent event)
    {
        if (store.getNumOrders() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No orders here");
            alert.setContentText("Nothing to cancel.");
            alert.showAndWait();
            return;
        }

        int ordernumber = Integer.parseInt(OrderNumber.getSelectionModel().getSelectedItem());
        int i = -1;
        for (int a = 0; a < store.getNumOrders(); a++)
        {
            if (ordernumber == store.getOrders().get(a).getOrderID())
            {
                i = a;
            }
        }

        store.remove(store.getOrders().get(i));
        int len = store.getNumOrders();
        OrderNumber.getItems().remove(i);


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Order canceled");
        alert.setContentText("Order canceled");
        alert.showAndWait();


        if (store.getNumOrders() != 0)
        {
            OrderNumber.getSelectionModel().select(String.valueOf(store.getOrders().get(0).getOrderID()));
        }
        else
        {
            displayOrders.getItems().clear();
            orderTotal.clear();
        }


    }

    /**
     * This function is to export orders
     *
     * @param event event triggered by clicking on the export orders button
     * @throws IOException handles IO exception
     */
    @FXML
    void exportOrders(ActionEvent event) throws IOException
    {
        try
        {
            if (store.getNumOrders() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Update");
                alert.setHeaderText("Export Store Orders");
                alert.setContentText("There are no orders to be exported.");
                alert.showAndWait();
                return;
            }
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Target File for the Export");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
            //write code to write to the file.
            FileWriter writer = new FileWriter(targetFile);
            for (int i = 0; i < store.getNumOrders(); i++) //works but is this the format she wants it in???
            {
                writer.append("Store Order Number: " + store.getOrders().get(i).getOrderID() + "\n");
                writer.append(store.getOrders().get(i).toString() + "\n");
            }
            writer.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Export Store Orders");
            alert.setContentText("Finished Exporting all orders.");
            alert.showAndWait();
        }
        catch (NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update");
            alert.setHeaderText("Export file not selected");
            alert.setContentText("Please select a file to export or create a new file");
            alert.showAndWait();
            return;
        }

    }

    /**
     * This function is to handle when the combo box of selecting order numbers is clicked
     *
     * @param event event triggered when combo box for order number is selected
     */
    @FXML
    void selectOrderNumbers(ActionEvent event)
    {
        if (store.getNumOrders() == 0)
        {
            displayOrders.getItems().clear();
            orderTotal.clear();
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Alert");
            alert1.setHeaderText("No orders");
            alert1.setContentText("There are no more orders to view. Pls go and order something.");
            alert1.showAndWait();
            return;
        }

        if (OrderNumber.getSelectionModel().isEmpty())
        {
            OrderNumber.getSelectionModel().select(String.valueOf(store.getOrders().get(0).getOrderID()));

        }
        int ordernumber = Integer.valueOf(OrderNumber.getSelectionModel().getSelectedItem());
        int i = -1;
        for (int a = 0; a < store.getNumOrders(); a++)
        {
            if (ordernumber == store.getOrders().get(a).getOrderID())
            {
                i = a;
            }
        }

        ObservableList<String> items = FXCollections.observableArrayList();
        items.clear();
        ArrayList<MenuItem> menuItem = store.getOrders().get(i).getMenuItem();
        for (int x = 0; x < menuItem.size(); x++)
        {
            items.add(menuItem.get(x).toString());
        }
        displayOrders.setItems(items);
        orderTotal.setText(String.format("%,.2f", store.getOrders().get(i).getTotal()));

    }

    /**
     * Sets the controller to the main menu controller
     *
     * @param controller the object reference to mainmenucontroller
     */
    public static void setMainController(MainMenuController controller)
    {
        mainController = controller;
        order = controller.order;
        store = controller.store;
    }

    /**
     * initializes the view of the 'Store Orders' screen when window is opened up
     *
     * @param url            of the corresponding screen
     * @param resourceBundle of the corresponding screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if (store.getNumOrders() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("No Store Orders");
            alert.setContentText("There are no stores to show yet.");
            alert.showAndWait();
            return;
        }
        ObservableList<String> items = FXCollections.observableArrayList();
        ArrayList<Order> orderList = store.getOrders();
        int len = store.getNumOrders();
        for (int i = 0; i < len; i++)
        {
            OrderNumber.getItems().add("" + store.getOrders().get(i).getOrderID());
        }
        ArrayList<MenuItem> menuItem = orderList.get(0).getMenuItem();
        for (int i = 0; i < menuItem.size(); i++)
        {
            items.add(menuItem.get(i).toString());
        }
        OrderNumber.getSelectionModel().select(String.valueOf(store.getOrders().get(0).getOrderID()));
        displayOrders.setItems(items);
        orderTotal.setText(String.format("%,.2f", orderList.get(0).getTotal()));
    }
}

