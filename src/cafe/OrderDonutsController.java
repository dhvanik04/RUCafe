package cafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.util.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the controller to handle the functionalities of ordering donut
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class OrderDonutsController implements Initializable
{
    /**
     * instance of order
     */
    private static Order order;
    /**
     * instance of MainMenuController
     */
    private static MainMenuController mainController;

    /**
     * instance variable to store subtotal
     */
    private double subtotal;
    /**
     * array list to store donuts
     */
    private ArrayList<MenuItem> donuts = new ArrayList<>();
    /**
     * array list to store selected donuts
     */
    private ArrayList<MenuItem> selectedDonuts = new ArrayList<>();
    /**
     * combo box to display/select different donut types
     */
    @FXML
    private ComboBox<String> type;
    /**
     * List View to show the different flavors offered for the dounts
     */
    @FXML
    private ListView<String> flavors;
    /**
     * List View to show the addedlistitems for the selected donuts to be ordered
     */
    @FXML
    private ListView<String> addedListItems;
    /**
     * button for adding all selected donuts to your order
     */
    @FXML
    private Button addButton;
    /**
     * text field to shoe the subtotal for the donuts that you have selected
     */
    @FXML
    private TextField subTotal;
    /**
     * combo box to chose the amount of the selected donut you want to order
     */
    @FXML
    private ComboBox<String> quantity;
    /**
     * button for adding the donut to the list of donuts you want to add to your order
     */
    @FXML
    private Button addDonut;
    /**
     * button for removing the donut to the list of donuts you previously  wanted to add to your order
     */
    @FXML
    private Button removeDonut;

    /**
     * This function is for adding a donut to the second list view and for displaying the subtotal
     *
     * @param event event triggered by clicking on the top double arrow button on the donut screen
     */
    @FXML
    void addDonut(ActionEvent event)
    {

        ObservableList<String> selectedFlavor =
                flavors.getSelectionModel().getSelectedItems();

        ObservableList<String> items = FXCollections.observableArrayList();

        String selectedQuantity = quantity.getSelectionModel().getSelectedItem();
        String selectedType = type.getSelectionModel().getSelectedItem();
        if (flavors.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Please select type or quantity to order");
            alert.setContentText("Nothing to add.");
            alert.showAndWait();
            return;
        }
        MenuItem donut = new Donut(selectedType, String.valueOf(selectedFlavor), Integer.parseInt(selectedQuantity));
        int flag = -1;
        for (int i = 0; i < selectedDonuts.size(); i++)
        {
            if (((Donut) selectedDonuts.get(i)).getFlavor().equals(((Donut) donut).getFlavor()))
            {
                int newQuantity = ((Donut) donut).getQuantity();
                ((Donut) selectedDonuts.get(i)).setQuantity(((Donut) selectedDonuts.get(i)).getQuantity() + newQuantity);
                flag = selectedDonuts.indexOf((Donut) selectedDonuts.get(i));
                addedListItems.getItems().set(flag, ((Donut) selectedDonuts.get(flag)).toString());
            }
        }
        if (flag == -1)
        {
            addedListItems.getItems().add(donut.toString());
            donuts.add(donut);
            selectedDonuts.add(donut);
        }

        subtotal = 0;
        for (int i = 0; i < selectedDonuts.size(); i++)
        {
            selectedDonuts.get(i).itemPrice();
            subtotal += selectedDonuts.get(i).getPrice();
        }
        subTotal.setText(String.format("%,.2f", subtotal));


    }

    /**
     * This function is for removing a donut from the second list view and for displaying the subtotal
     *
     * @param event event triggered by clicking on the bottom double arrow button on the donut screen
     */
    @FXML
    void removeDonuts(ActionEvent event)
    {
        try
        {
            int x = addedListItems.getSelectionModel().getSelectedIndex();
            addedListItems.getItems().remove(x);
            donuts.remove(donuts.get(x));
            selectedDonuts.remove(selectedDonuts.get(x));
            subtotal = 0;
            for (int i = 0; i < selectedDonuts.size(); i++)
            {
                selectedDonuts.get(i).itemPrice();
                subtotal += selectedDonuts.get(i).getPrice();
            }
            subTotal.setText(String.format("%,.2f", subtotal));
        }
        catch (RuntimeException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("Item not selected");
            alert.setContentText("Please select a donut to remove.");
            alert.showAndWait();
        }
    }


    /**
     * This function is for adding a donut to the order
     *
     * @param event event triggered by clicking on add to order button on the donut screen
     */
    @FXML
    void addToOrder(ActionEvent event)
    {

        if (addedListItems.getItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please add  donut");
            alert.setContentText("Nothing to add.");
            alert.showAndWait();
            return;
        }
        if (selectedDonuts.size() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a donut type");
            alert.setContentText("Nothing to add.");
            alert.showAndWait();
            return;
        }

        for (int i = 0; i < selectedDonuts.size(); i++)
        {
            order.add(selectedDonuts.get(i));
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Add to Order");
        alert.setContentText("Donut added to order.");
        alert.showAndWait();

        addedListItems.getItems().clear();
        subTotal.clear();
        selectedDonuts = new ArrayList<>();
    }

    /**
     * This function is for displaying the flavors when the type of the donut is selected
     *
     * @param event event triggered when the type of the donut is selected
     */
    @FXML
    void displayItems(ActionEvent event)
    {
        String selected = type.getSelectionModel().getSelectedItem();
        if (selected.equals("yeast donuts"))
        {
            flavors.getItems().removeAll(flavors.getItems());
            ObservableList<String> names = FXCollections.observableArrayList("jelly", "chocolate-frosted", "strawberry-frosted");
            flavors.setItems(names);
        }
        else if (selected.equals("cake donuts"))
        {
            type.getItems().removeAll(flavors.getItems());
            ObservableList<String> names = FXCollections.observableArrayList("cinnamon", "blueberry", "sugar");
            flavors.setItems(names);
        }
        else if (selected.equals("donut holes"))
        {
            flavors.getItems().removeAll(flavors.getItems());
            ObservableList<String> names = FXCollections.observableArrayList("lemon-filled", "cinnamon-sugar holes", "jelly holes");
            flavors.setItems(names);

        }

    }

    /**
     * initializes the view of the 'Order Donuts' screen when window is opened up
     *
     * @param url            of the corresponding screen
     * @param resourceBundle of the corresponding screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        type.getItems().removeAll(type.getItems());
        type.getItems().addAll("yeast donuts", "cake donuts", "donut holes");
        type.getSelectionModel().select("yeast donuts");

        ObservableList<String> names = FXCollections.observableArrayList("jelly", "chocolate-frosted", "strawberry-frosted");
        flavors.setItems(names);

        quantity.getItems().removeAll(quantity.getItems());
        quantity.getItems().addAll("1", "2", "3", "4", "5");
        quantity.getSelectionModel().select("1");
        subtotal = 0.0;
        subTotal.setText("0.00");

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



