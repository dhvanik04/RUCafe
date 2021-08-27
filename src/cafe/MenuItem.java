package cafe;

/**
 * This is the Menu Item class for creating an instance of menu item. It is the super class for coffee and donut class
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class MenuItem
{
    /**
     * instance variable to store price
     */
    protected double price;
    /**
     * instance variable to store quantity
     */
    protected int quantity;

    /**
     * Constructor to create an instance of menu item
     *
     * @param quantity quantity of menu item
     */
    public MenuItem(int quantity)
    {
        this.price = 0.0;
        this.quantity = quantity;
    }

    /**
     * Calculates the item price of menu item
     */
    public void itemPrice()
    {
        this.price = 0.0;
    }

    /**
     * Prints the information - this function is overridden by the subclasses
     *
     * @return a string of information for the item
     */
    @Override
    public String toString()
    {
        return "";
    }

    /**
     * Gets the price of the MenuItem
     *
     * @return the dollar and cents price of the item
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Sets the quantity of the MenuItem
     *
     * @param quantity the new quantity that the MenuItem should be set to.
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * Gets the quantity of the MenuItem
     *
     * @return int value of the quantity
     */
    public int getQuantity()
    {
        return quantity;
    }


}
