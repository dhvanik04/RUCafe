package cafe;

import java.util.ArrayList;


/**
 * This is the Coffee class to create coffee objects for orders and store their corresponding information.
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class Coffee extends MenuItem implements Customizable
{
    /**
     * The ArrayList of String to store the add ins for coffee
     */
    private ArrayList<String> addin;
    /**
     * instance variable to store size
     */
    private String size;
    /**
     * final constant to store tall price
     */
    private static final double TALL = 2.49;
    /**
     * final constant to store grande price
     */
    private static final double GRANDE = 2.99;
    /**
     * final constant to store venti price
     */
    private static final double VENTI = 3.49;
    /**
     * final constant to store short black price
     */
    private static final double SHORTBLACK = 1.99;
    /**
     * final constant to store addin price
     */
    private static final double ADDIN = 0.20;

    /**
     * This is a constructor for Coffee
     *
     * @param size     of the coffee
     * @param quantity of the coffee ordered
     */
    public Coffee(String size, int quantity)
    {
        super(quantity);
        this.size = size;
        this.addin = new ArrayList<>();
        if (size.equals("Short"))
        {
            price = SHORTBLACK;
        }
        else if (size.equals("Tall"))
        {
            price = TALL;
        }
        else if (size.equals("Grande"))
        {
            price = GRANDE;
        }
        else if (size.equals("Venti"))
        {
            price = VENTI;
        }
        else
            price = 0;
    }

    /**
     * Adds an addin to the arrarylist of addins
     *
     * @param obj object to be added to the array list of addins
     * @return boolean whether the object is correctly added or not
     */
    @Override
    public boolean add(Object obj)
    {
        if (obj instanceof String)
        {
            addin.add((String) obj);
        }
        return false;
    }


    /**
     * Removes an addin from the arrarylist of addins
     *
     * @param obj object to be removed from the array list of addins
     * @return boolean whether the object is correctly removed or not
     */
    @Override
    public boolean remove(Object obj)
    {
        if (obj instanceof String)
        {
            addin.remove(obj);
        }
        return false;
    }

    /**
     * Calculates the price of coffee
     */
    @Override
    public void itemPrice()
    {
        if (size.equals("Short"))
        {
            price = SHORTBLACK;
        }
        else if (size.equals("Tall"))
        {
            price = TALL;
        }
        else if (size.equals("Grande"))
        {
            price = GRANDE;
        }
        else if (size.equals("Venti"))
        {
            price = VENTI;
        }
        else
            price = 0;
        price = price + addin.size() * ADDIN;
        price = price * quantity;
    }

    /**
     * Prints all of the Coffee's information
     *
     * @return a string that has the coffee size, quantity, and addins
     */
    @Override
    public String toString()
    {
        String output = "Coffee (" + this.quantity + ") " + size + " [";
        for (int i = 0; i < addin.size(); i++)
        {
            if (output.equals("Coffee (" + this.quantity + ") " + size + " ["))
                output = output + addin.get(i);
            else
                output = output + ", " + addin.get(i);
        }
        return output + "]";
    }

    /**
     * This function is to check is two coffees are equal or not
     *
     * @param c Object c instance of coffee
     * @return boolean true if equal, false otherwise
     */
    @Override
    public boolean equals(Object c)
    {
        if (c instanceof Coffee)
        {
            Coffee c1 = (Coffee) c;
            if (this.addin.size() != c1.getAddins().size())
            {
                return false;
            }
            if (!this.size.equals(c1.getSize()))
            {
                return false;
            }
            for (int i = 0; i < this.addin.size(); i++)
            {
                if (!c1.getAddins().contains(this.addin.get(i)))
                    return false;
            }

            return true;
        }
        else return false;
    }

    /**
     * Gets the addins in the coffee
     *
     * @return the arraylist of addins for the coffee
     */
    public ArrayList<String> getAddins()
    {
        return this.addin;
    }

    /**
     * Getter function to return the size of coffee
     *
     * @return String the size of the coffee
     */
    public String getSize()
    {
        return size;
    }


}
