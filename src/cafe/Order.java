package cafe;

import java.util.ArrayList;

/**
 * This is the order class to create an instance of order and to store list of menu items
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class Order implements Customizable
{
    /**
     * Array list of menu items to store menu items
     */
    private ArrayList<MenuItem> menuItems;
    /**
     * instance variable to store subtotal
     */
    private double subTotal;
    /**
     * instance variable to store total
     */
    private double total;
    /**
     * instance variable to store order ID
     */
    private int orderID;
    /**
     * instance variable to store and increment order numbers
     */
    private static int ordernum = 1;
    /**
     * Sales tax decimal value in NJ
     */
    private static final double SALESTAX = 0.06625;

    /**
     * Constructor to create an instance of order
     */
    public Order()
    {
        this.orderID = ordernum;
        ordernum++;
        this.subTotal = 0;
        this.total = 0;
        this.menuItems = new ArrayList<>();
    }

    /**
     * Adds a menuitem to the order
     * @param obj to be added
     * @return true if menu item is added correctly, false otherwise
     */
    @Override
    public boolean add(Object obj)
    {
        if (!(obj instanceof MenuItem))
            return false;

        menuItems.add((MenuItem) obj);

        return true;
    }

    /**
     * Removes a menuitem from the order
     * @param obj to be removed
     * @return true if menu item is removed correctly, false otherwise
     */
    @Override
    public boolean remove(Object obj)
    {
        if (obj instanceof MenuItem)
        {
            menuItems.remove(obj);
            return true;
        }
        return false;
    }

    /**
     * Calculate the subtotal of all the menu items in the list
     */
    public void calculateSubtotal()
    {
        this.subTotal = 0;

        for (int i = 0; i < menuItems.size(); i++)
        {
            this.subTotal = this.subTotal + menuItems.get(i).getPrice();
        }
    }

    /**
     * Calculate the total of all the menu items in the list
     */
    public void calculateTotal()
    {
        total = subTotal + SALESTAX * subTotal;
    }

    /**
     * Prints out the information of the menu items that are added to the order
     *
     * @return String of the details of menu items in the order
     */
    @Override
    public String toString()
    {
        String output = "";
        for (int i = 0; i < menuItems.size(); i++)
        {
            output = output + menuItems.get(i).toString() + "\n";
        }

        return output;
    }

    /**
     * Gets the value of the subtotal to be paid on this order
     *
     * @return the dollar and cents value of the subtotal
     */
    public double getSubTotal()
    {
        return this.subTotal;
    }

    /**
     * Gets the total amount to be paid on this order
     *
     * @return the dollar and cents value of the order total
     */
    public double getTotal()
    {
        return this.total;
    }

    /**
     * Gets the value of the salestax to be paid on this order
     *
     * @return the dollar and cents value of the salestax
     */
    public double getSalestax()
    {
        return SALESTAX * this.subTotal;
    }

    /**
     * Gets the orderID of this order
     *
     * @return the int of the orderID
     */
    public int getOrderID()
    {
        return this.orderID;
    }

    /**
     * Gets the list of menuItems in this order
     *
     * @return arraylist of menuitems
     */
    public ArrayList<MenuItem> getMenuItem()
    {
        return menuItems;
    }
}
