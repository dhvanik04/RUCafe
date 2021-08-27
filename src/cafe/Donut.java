package cafe;

/**
 * This is the Donut class to create donut objects for orders and store their corresponding information.
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class Donut extends MenuItem
{
    /**
     * instance variable to store flavor of donuts
     */
    private String flavor;
    /**
     * instance variable to store flavor of type
     */
    private String type;
    /**
     * final constant to store yeast donut
     */
    private static final double YEASTDONUT = 1.39;
    /**
     * final constant to store cake donut
     */
    private static final double CAKEDONUT = 1.59;
    /**
     * final constant to store donut hole
     */
    private static final double DONUTHOLE = 0.33;

    /**
     * This is the parametrized constructor to create a donut object
     *
     * @param type     string for the type of donut
     * @param flavor   string for the flavor of donut
     * @param quantity int for the quantity of donut
     */
    public Donut(String type, String flavor, int quantity)
    {
        super(quantity);
        this.flavor = flavor;
        this.type = type;
        if (type.equals("yeast donuts"))
        {
            price = YEASTDONUT;
        }
        else if (type.equals("cake donuts"))
        {
            price = CAKEDONUT;
        }
        else if (type.equals("donut holes"))
        {
            price = DONUTHOLE;
        }

    }

    /**
     * Calculates the price of donut
     */
    @Override
    public void itemPrice()
    {
        if (type.equals("yeast donuts"))
        {
            price = YEASTDONUT;
        }
        else if (type.equals("cake donuts"))
        {
            price = CAKEDONUT;
        }
        else if (type.equals("donut holes"))
        {
            price = DONUTHOLE;
        }
        else
            price = 0;
        price = price * quantity;
    }

    /**
     * Prints all of the Donut's information
     *
     * @return a string that has the donut flavor and quantity
     */
    @Override
    public String toString()
    {
        return flavor + "(" + quantity + ")";
    }

    /**
     * Getter method for getting the flavor of donut
     *
     * @return the flavor of the dount
     */
    public String getFlavor()
    {
        return flavor;
    }

    /**
     * This function is to check is two donuts are equal or not (by checking their flavor)
     *
     * @param c Object c instance of donut
     * @return boolean true if equal, false otherwise
     */
    @Override
    public boolean equals(Object c)
    {
        if (c instanceof Donut)
        {
            Donut c1 = (Donut) c;
            if (this.flavor.equals(c1.getFlavor()))
            {
                return true;
            }
            return false;
        }
        else return false;
    }


}
