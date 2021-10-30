package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class is used to create products*/
public class Product extends Inventory {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** This is the product object default constructor method.
     This method is used to setup the parameters for a product object.
     @param id Product ID
     @param name Product name
     @param price Product price
     @param stock Product Inventory
     @param min Product min
     @param max Product max
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** This is the get ID method.
     This method returns the Product ID.
     @return returns product id
     */
    public int getId() {
        return id;
    }

    /** This is the set ID method.
     This method sets the Product ID.
     @param id the product id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /** This is the get name method.
     This method returns the product name.
     @return returns the product name
     */
    public String getName() {
        return name;
    }

    /** This is the set product name method.
     This method sets the product name.
     @param name the product name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /** This is the get price method.
     This method returns the product price.
     @return returns the product price
     */
    public double getPrice() {
        return price;
    }

    /** This is the set price method.
     This method sets the product price.
     @param price the product price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** This is the get stock method.
     This method returns the product inventory.
     @return returns the product stock
     */
    public int getStock() {
        return stock;
    }

    /** This is the set stock method.
     This method sets the product inventory.
     @param stock the product stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** This is the get min method.
     This method returns the product minimum inventory.
     @return returns the product min
     */
    public int getMin() {
        return min;
    }

    /** This is the set min method.
     This method sets the product minimum inventory.
     @param min the product min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** This is the get max method.
     This method returns the product maximum inventory.
     @return returns the product max
     */
    public int getMax() {
        return max;
    }

    /** This is the set max method.
     This method sets the product maximum inventory.
     @param max the product max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /** This is the add associated part method.
     This method adds parts associated with a product.
     @param part the part to add
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /** This is the delete associated part boolean method.
     This method returns true if associated parts are removed from product, otherwise returns false.
     @param selectedAssociatedPart selected associated part
     @return returns associated part removed, boolean true/false
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        for (Part part : associatedParts) {
            if (part.getId() == selectedAssociatedPart.getId()) {
                associatedParts.remove(part);
                return true;
            }
        }
        return false;
    }

    /** This is the get all associated parts method.
     The method returns all the associated part from the observable parts list.
     @return returns all associated parts
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
}
