package cafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the controller class to handle the functionalities of ordering coffee
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class OrderCoffeeController implements Initializable
{
    /**
     * checkbox for milk addin
     */
    @FXML
    private CheckBox milk;

    /**
     * checkbox for cream addin
     */
    @FXML
    private CheckBox cream;

    /**
     * checkbox for whipped cream addin
     */
    @FXML
    private CheckBox whippedCream;

    /**
     * checkbox for syrup addin
     */
    @FXML
    private CheckBox syrup;

    /**
     * checkbox for caramel addin
     */
    @FXML
    private CheckBox caramel;

    /**
     * combo box to store the different sizes
     */
    @FXML
    private ComboBox<String> size;
    /**
     * combo box to store the different quantities
     */
    @FXML
    private ComboBox<String> quantity;
    /**
     * text field for displaying total
     */
    @FXML
    private TextField total;

    /**
     * object reference to the main controller
     */
    private static MainMenuController mainController;
    /**
     * instance of order
     */
    private static Order order;
    /**
     * final constant to store the price of short black coffee
     */
    private static final double SHORTBLACK = 1.99;

    /**
     * initializes the view of the 'Order Coffee' screen when window is opened up
     *
     * @param url            of the corresponding screen
     * @param resourceBundle of the corresponding screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        size.getItems().removeAll(size.getItems());
        size.getItems().addAll("Short", "Tall", "Grande", "Venti");
        size.getSelectionModel().select("Short");

        quantity.getItems().removeAll(quantity.getItems());
        quantity.getItems().addAll("1", "2", "3", "4", "5");
        quantity.getSelectionModel().select("1");

        total.setText(String.valueOf(SHORTBLACK));
    }

    /**
     * This function is to dynamically set total whenever any of the components are changed on the coffee screen
     *
     * @param event event triggered when any of the sizes or quantity or addins are selected or their values are changed
     */
    @FXML
    void setText(ActionEvent event)
    {
        String selectedQuantity = quantity.getSelectionModel().getSelectedItem();
        String selectedSize = size.getSelectionModel().getSelectedItem();
        MenuItem coffee = new Coffee(selectedSize, Integer.parseInt(selectedQuantity));
        if (cream.isSelected())
        {
            ((Coffee) coffee).add("cream");
        }
        if (whippedCream.isSelected())
        {
            ((Coffee) coffee).add("whipped cream");
        }
        if (syrup.isSelected())
        {
            ((Coffee) coffee).add("syrup");
        }
        if (milk.isSelected())
        {
            ((Coffee) coffee).add("milk");
        }
        if (caramel.isSelected())
        {
            ((Coffee) coffee).add("caramel");
        }
        coffee.itemPrice();

        total.setText(String.format("%,.2f", coffee.getPrice()));
    }


    /**
     * This function adds the coffee to the order
     *
     * @param event event triggered when the add to order button iss selected
     */
    @FXML
    void addToOrder(ActionEvent event)
    {

        String selectedQuantity = quantity.getSelectionModel().getSelectedItem();
        String selectedSize = size.getSelectionModel().getSelectedItem();
        MenuItem coffee = new Coffee(selectedSize, Integer.parseInt(selectedQuantity));
        if (cream.isSelected())
        {
            ((Coffee) coffee).add("cream");
        }
        if (whippedCream.isSelected())
        {
            ((Coffee) coffee).add("whipped cream");
        }
        if (syrup.isSelected())
        {
            ((Coffee) coffee).add("syrup");
        }
        if (milk.isSelected())
        {
            ((Coffee) coffee).add("milk");
        }
        if (caramel.isSelected())
        {
            ((Coffee) coffee).add("caramel");
        }
        coffee.itemPrice();
        order.add(coffee);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Add to Order");
        alert.setContentText("Coffee added to order.");
        alert.showAndWait();

        if (cream.isSelected())
        {
            cream.setSelected(false);
        }
        if (whippedCream.isSelected())
        {
            whippedCream.setSelected(false);
        }
        if (syrup.isSelected())
        {
            syrup.setSelected(false);
        }
        if (milk.isSelected())
        {
            milk.setSelected(false);
        }
        if (caramel.isSelected())
        {
            caramel.setSelected(false);
        }
        quantity.getSelectionModel().select("1");
        size.getSelectionModel().select("Short");
        total.setText(String.valueOf(SHORTBLACK));

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
    }
}
