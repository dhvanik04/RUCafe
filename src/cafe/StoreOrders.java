package cafe;

import java.util.ArrayList;

/**
 * This is the store orders class to create an instance of store orders
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class StoreOrders implements Customizable
{
    /**
     * Array list of orders
     */
    private ArrayList<Order> orders;

    /**
     * Constructor to create an instance of store orders
     */
    public StoreOrders()
    {
        this.orders = new ArrayList<>();
    }

    /**
     * This function is for adding a order to the list of orders
     *
     * @param obj to be added
     * @return true if order is added correctly, false otherwise
     */
    @Override
    public boolean add(Object obj)
    {

        if (obj instanceof Order)
        {
            orders.add((Order) obj);
            return true;
        }
        return false;

    }

    /**
     * This function is for removing a order to the list of orders
     *
     * @param obj to be removed
     * @return true if order is rmoved correctly, false otherwise
     */
    @Override
    public boolean remove(Object obj)
    {
        if (obj instanceof Order)
        {
            orders.remove(obj);
            return true;
        }
        return false;
    }

    /**
     * Gets the arraylist of all the orders in the store
     *
     * @return arraylist of orders in the store
     */
    public ArrayList<Order> getOrders()
    {
        return orders;
    }

    /**
     * Gets the number of store orders
     *
     * @return int value of the number of store orders you have
     */
    public int getNumOrders()
    {
        return orders.size();
    }
}
