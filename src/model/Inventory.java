package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class handles data methods used by the Part and Product classes.*/
public class Inventory {

    //Global IDs for creating new parts/products using the next available id, attributes set here using existing parts/products in the tableviews
    private static int newPartID = 4;
    private static int newProductID = 2;

    //Static Members
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();



    //Methods

    /** This is the add part method.
     This method adds a part to the allParts observable list.
     @param newPart new part to add*/
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** This is the add product method.
     This method adds a product to the allProducts observable list.
     @param newProduct new product to add*/
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /** This is the lookup part method.
     This method returns a part from the allPart observable list via part ID.
     @param partID part ID to lookup
     @return returns part based on part ID parameter
     */
    public static Part lookupPart(int partID) {
        return allParts.get(partID);
    }

    /** This is the lookup product method.
     This method returns a product from the allProduct observable list via product ID.
     @param productID product ID to lookup
     @return returns product based on product ID parameter
     */
    public static Product lookupProduct(int productID) {
        return (Product)allProducts.get(productID);
    }

    /** This is the lookup part method.
     This method returns a part from the allPart observable list via part name.
     @param partName part name to lookup
     @return returns all parts based on part name string
     */
    public static ObservableList<Part> lookupPart(String partName) {
        return allParts;
    }

    /** This is the lookup product method.
     This method returns a product from the allProduct observable list via product name.
     @param productName product name to lookup
     @return returns all products based on product name string
     */
    public ObservableList<Product> lookupProduct(String productName) {
        return allProducts;
    }

    /** This update part method.
     This method is used to update a selected part object with the part index(i) and selected part parameters.
     @param index index of part to update
     @param selectedPart selected part to update
     */
    public static void updatePart(int index, Part selectedPart) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == selectedPart.getId()) {
                allParts.set(i, selectedPart);
                break;
            }
        }
    }

    /** This is the update product method.
     This method is used to update a selected product object with the product index(i) and selected product parameters.
     @param index index of product to update
     @param newProduct selected product to update
     */
    public static void updateProduct(int index, Product newProduct) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == newProduct.getId()) {
                allProducts.set(i, newProduct);
                break;
            }
        }
    }

    /** This is the delete part method.
     This method is used to delete a selected part from the allParts observable list.
     @param selectedPart selected part to delete
     @return returns a boolean true value for deleted part
     */
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return true;
    }

    /** This is the delete product method.
     This method is used to delete a selected product from the allProducts observable list.
     @param selectedProduct selected product to delete
     @return returns a boolean true value for deleted product
     */
    public static boolean deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
        return true;
    }

    /** This is the get all parts method.
     this method returns all parts in the part observable list.
     @return returns all parts
     */
    public static ObservableList<Part> getAllParts() {
       return allParts;
    }

    /** This is the get all products method.
     This method returns all products in the product observable list.
     @return returns all products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
