package cafe;

/**
 * This is the customizable interace
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public interface Customizable
{
    /**
     * adds an object to a location specified
     * @param obj to be added
     * @return true if added correctly or false otherwise
     */
    boolean add(Object obj);

    /**
     * removes an object from a location specified
     * @param obj to be removed
     * @return true if removed correctly or false otherwise
     */
    boolean remove(Object obj);
}
