package model;

/**
 *
 * @author Steven Prey
 */

/** This class is an abstract class used to create InHouse or Outsourced parts.*/
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;




    // Default constructor for class part
    /** This is the part object default constructor method.
     This method is used to setup the parameters for a part object.
     @param id part ID
     @param name part name
     @param price part price
     @param stock part inventory
     @param min part min inventory
     @param max part max inventory
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;

    }

    /** This is the get ID method.
     This method returns the part ID.
     @return the id
     */
    public int getId() {
        return id;
    }

    /** This is the set ID method.
     This method sets the part ID.
     @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /** This is the get name method.
     This method returns the part name.
     @return the name
     */
    public String getName() {
        return name;
    }

    /** This is the set name method.
     This method sets the part name.
     @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /** This is get price method.
     This method returns the part price.
     @return the price
     */
    public double getPrice() {
        return price;
    }

    /** This is the set price method.
     This method sets the part price.
     @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** This is the get stock method.
     This method gets the part inventory level.
     @return the stock
     */
    public int getStock() {
        return stock;
    }

    /** This is the set stock method.
     This method sets the part inventory level.
     @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** This is the get min method.
     This method returns the part minimum inventory.
     @return the min
     */
    public int getMin() {
        return min;
    }

    /** This is the set min method.
     This method sets the part minimum inventory.
     @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** This is the get max method.
     This method returns the part maximum inventory.
     @return the max
     */
    public int getMax() {
        return max;
    }

    /** This is the set max method.
     This method sets the part maximum inventory.
     @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

}