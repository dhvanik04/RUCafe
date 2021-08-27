package cafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This is the controller accompanying the your orders view
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class CurrentOrderController implements Initializable
{
    /**
     * instance of store orders class - holds the object reference to store orders
     */
    private static StoreOrders store;

    /**
     * instance of MainMenuController - holds the object reference to the main controller
     */
    private static MainMenuController mainController;
    /**
     * instance of order - holds the object reference of order
     */
    private static Order order;

    /**
     * textfield for subtotal
     */
    @FXML
    private TextField subTotal;

    /**
     * textfield for salestax
     */
    @FXML
    private TextField salesTax;

    /**
     * textfield for total
     */
    @FXML
    private TextField total;

    /**
     * list view to display orders
     */
    @FXML
    private ListView<String> listView;

    /**
     * This function is to place an order accompanying the place order button
     *
     * @param event the event that is triggered when place order is clicked
     */
    @FXML
    void placeOrder(ActionEvent event)
    {
        if (listView.getItems().size() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No orders");
            alert.setContentText("No orders to place");
            alert.showAndWait();
            return;
        }
        store.add(order);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Order Placed");
        alert.setContentText("Order is placed");
        alert.showAndWait();
        order = new Order();
        MainMenuController.order = order;
        ObservableList<String> items = FXCollections.observableArrayList();
        items.clear();
        listView.setItems(items);

        subTotal.setText("0.00");
        total.setText("0.00");
        salesTax.setText("0.00");
    }

    /**
     * This function is to remove an order item accompanying the remove selected item button
     *
     * @param event the event that is triggered when remove selected item button is triggered
     */
    @FXML
    void removeSelectedItems(ActionEvent event)
    {
        if (listView.getItems().size() == 0 || listView.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing to remove");
            alert.setContentText("Nothing to remove.");
            alert.showAndWait();
            return;
        }
        int x = listView.getSelectionModel().getSelectedIndex();
        System.out.println();
        listView.getItems().remove(x);
        order.remove(order.getMenuItem().get(x));
        order.calculateSubtotal();
        subTotal.setText(String.format("%,.2f", order.getSubTotal()));
        order.calculateTotal();
        salesTax.setText(String.format("%,.2f", order.getSalestax()));
        total.setText(String.format("%,.2f", order.getTotal()));
    }


    /**
     * initializes the view of the 'your order' screen when window is opened up
     *
     * @param url            of the corresponding screen
     * @param resourceBundle of the corresponding screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.clear();
        listView.setItems(items);
        ArrayList<MenuItem> menuItemsList = order.getMenuItem();

        for (int i = 0; i < menuItemsList.size(); i++)
        {
            listView.getItems().add(menuItemsList.get(i).toString());
        }

        order.calculateSubtotal();
        subTotal.setText(String.format("%,.2f", order.getSubTotal()));
        order.calculateTotal();
        salesTax.setText(String.format("%,.2f", order.getSalestax()));
        total.setText(String.format("%,.2f", order.getTotal()));

    }

    /**
     * Sets the controller to the mainmenucontroller
     *
     * @param controller the object reference to mainmenucontroller
     */
    public static void setMainController(MainMenuController controller)
    {
        mainController = controller;
        order = controller.order;
        store = controller.store;
    }
}
