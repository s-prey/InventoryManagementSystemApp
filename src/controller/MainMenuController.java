package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class is used as a java fx controller for the inventory management system main menu GUI screen.*/
public class MainMenuController implements Initializable {

    Stage stage;
    Parent scene;

    // FXML InHouse Parts Table
    @FXML
    private TextField searchPartTxtField;

    @FXML
    private VBox mainPartTableView;

    @FXML
    private TableView<Part> mainPartsTableView;

    @FXML
    private TableColumn<InHouse, Integer> mainPartIDCol;

    @FXML
    private TableColumn<InHouse, String> mainPartNameCol;

    @FXML
    private TableColumn<InHouse, Integer> mainPartInvLevelCol;

    @FXML
    private TableColumn<InHouse, Double> mainPartPriceCol;

    // FXML Product Table
    @FXML
    private TextField searchProductTxtField;

    @FXML
    private TableView<Product> mainProductTableView;

    @FXML
    private TableColumn<Product, Integer> mainProductIDCol;

    @FXML
    private TableColumn<Product, String> mainProductNamCol;

    @FXML
    private TableColumn<Product, Integer> mainProductInvLevelCol;

    @FXML
    private TableColumn<Product, Double> mainProductPriceCol;


    public  ObservableList<Part> searchPartData = FXCollections.observableArrayList();
    public  ObservableList<Product> searchProductData = FXCollections.observableArrayList();
    static int selectedPartID;
    static int selectedProductID = 0;
    private static int modifyProductIndex;
    private static Part partToModify;
    private static Product productToModify;

    /** This is the initialize method.
     This method is used to initialize data for the main menu controller.
     @param url uniform resource locator to initialise
     @param rb resource bundle to initialize*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Sets items in Tableview from the getAllParts method from Inventory class
        mainPartsTableView.setItems(Inventory.getAllParts());
        mainPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainPartInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        // Sets items in Tableview from the getAllProducts method from Inventory class
        mainProductTableView.setItems(Inventory.getAllProducts());
        mainProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainProductNamCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainProductInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    //********************************* PART METHODS ********************************************************************

    /** This is the add part change scene method.
     This method loads the add part menu screen when the add button is selected from the main menu.
     @param event java fxml method trigger event*/
    @FXML
    void onActionAddPartMain(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    // Method to change scene to modify part scene
    /** This is the modify part change scene method.
     this method loads the modify part menu screen and passes part information from the main menu selected part.
     @param event java fxml method trigger event*/
    @FXML
    void onActionModifyPartMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
        loader.load();

        partToModify = mainPartsTableView.getSelectionModel().getSelectedItem();

        ModifyPartController.passPart(partToModify);
        ModifyPartController MPTMController = loader.getController();
        MPTMController.sendPart(mainPartsTableView.getSelectionModel().getSelectedItem());


        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }


    // Method to delete part from table list
    /** This is the delete part method.
     This method deletes a selected part from the parts tableview in the main menu.
     @param event java fxml method trigger event*/
    @FXML
    void onActionDeletePartMain(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected part, do you wish to continue?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Part part = mainPartsTableView.getSelectionModel().getSelectedItem();

            Inventory.deletePart(part);
        }
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        }


    //Method to search list of parts by part name
    /** This is the search part method.
     This method searches and filters parts in the main menu parts tableview based on user entered part ID or
     part name in the search field.
     @param event java fxml method trigger event*/
    @FXML
    void onActionSearchPart(ActionEvent event) {

        // Used to get text entered by the user in the search field
        String searchPartString = searchPartTxtField.getText();

        //Creates new observable list to search and filter for searched item
        ObservableList<Part> filteredPartList = searchByPartName(searchPartString);

        if(filteredPartList.size() == 0) {
            try {
                int id = Integer.parseInt(searchPartString);
                Part searchPart = searchPartID(id);
                if(searchPartString != null) {
                    filteredPartList.add(searchPart);
                }
            }
            catch(NumberFormatException e) {
                //Not needed
            }
        }
        mainPartsTableView.setItems(filteredPartList);
    }


    // Method to search list of parts by part ID
    /** This is the search part ID method.
     This method is used to search for parts based on part ID integer.
     @param id part ID to search
     @return returns null with no part found
     */
    private Part searchPartID(int id) {
        //List from part inventory then loop search for filtered part ID
        ObservableList<Part> allParts = Inventory.getAllParts();
        for(int i=0; i < allParts.size(); i++) {
            Part searchPart = allParts.get(i);

            if(searchPart.getId() == id) {
                return searchPart;
            }
        }
        return null;
    }


    // Method to search list of parts by part name
    /** This is the search part name method.
     This method is used to search for parts based on part name string.
     @param partName part Name to search
     @return return all parts filtered from part name search
     */
    private ObservableList<Part> searchByPartName(String partName) {
        // Observablelist to return filtered part name
        ObservableList<Part> allPartsFilteredList = FXCollections.observableArrayList();

        //List from inventory for part name filtered search
        ObservableList<Part> allParts = Inventory.getAllParts();

        //Loop through allParts list using variable searchPart
        for(Part searchPart : allParts) {
            if(searchPart.getName().toLowerCase().contains(partName)) {
                allPartsFilteredList.add(searchPart);
            }
        }
        return allPartsFilteredList;

    }


    //****************************** Search Product *******************************************************************
    // Method to change scene to add product scene
    /** This is the add product change scene method.
     This method loads the add product menu screen when the add product button is selected from the main menu.
     @param event java fxml method trigger event
     */
    @FXML
    void onActionAddProductMain(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    // Method to delete product from table list
    /** This is the delete product method.
     This method deletes the selected product from the product tableview in the main menu.
     @param event java fxml method trigger event
     */
    @FXML
    void onActionDeleteProductMain(ActionEvent event) throws IOException {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected product, do you wish to continue?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Product product = mainProductTableView.getSelectionModel().getSelectedItem();
            if(product.getAllAssociatedParts().isEmpty()) {
                Inventory.deleteProduct(product);
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Product Deletion Error");
                alert.setContentText("You cannot delete a product that has parts associated with it.\nPlease " +
                        "remove parts before deleting product.");
                alert.showAndWait();
            }
        }

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    // Method to change scene to modify product scene
    /** This is the modify product change scene method.
     this method loads the modify product menu screen and passes product information from the main menu selected part.
     @param event java fxml method trigger event
     */
    @FXML
    void onActionModifyProductMain(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();

        productToModify = mainProductTableView.getSelectionModel().getSelectedItem();
        ModifyProductController.passProduct(productToModify);

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the search product method.
     This method searches and filters products in the main menu products tableview based on user entered product ID or
     product name in the search field.
     @param event java fxml method trigger event
     */
    @FXML
    void onActionSearchProduct(ActionEvent event) {
    //Used to get text entered by the user in the search field
        String searchProductString = searchProductTxtField.getText();

        //Creates new observable list to search and filter for searched item
        ObservableList<Product> filteredProductList = searchByProductName(searchProductString);

        if(filteredProductList.size() == 0) {
            try {
                int id = Integer.parseInt(searchProductString);
                Product searchProduct = searchProductID(id);
                if(searchProductString != null) {
                    filteredProductList.add(searchProduct);
                }
            }
            catch(NumberFormatException e) {
            }
        }
        mainProductTableView.setItems(filteredProductList);
    }


    //Method to search list of products by product ID
    /** This is the search product ID method.
     This method is used to search for products based on product ID integer.
     @param id product ID to search
     @return returns null with no product found*/
    private Product searchProductID(int id) {
        //List from product inventory for product ID filtered search
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        //Loop through the product list
        for(int i=0; i < allProducts.size(); i++) {
            Product searchProduct = allProducts.get(i);
            if(searchProduct.getId() == id) {
                return searchProduct;
            }
        }
        return null;
    }

    /** This is the search product name method.
     This method is used to search for products based on product name string.
     @param productName product Name to search
     @return return all products filtered from product name search
     */
    private ObservableList<Product> searchByProductName(String productName) {
        //Observablelist to return filtered product name
        ObservableList<Product> allProductsFilteredList = FXCollections.observableArrayList();
        
        //List from inventory for part name filtered search
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        //Loop through allProducts list using variable searchProductString
        for(Product searchProductString : allProducts) {
            if(searchProductString.getName().toLowerCase().contains(productName)) {
                allProductsFilteredList.add(searchProductString);
            }
        }
        return allProductsFilteredList;
    }


    // Method to exit and close application
    /** This is the exit application method.
     This method closes and exits the application
     @param event java fxml method trigger event
     */
    @FXML
    void onActionExitMain(ActionEvent event) {

        System.exit(0);
    }

}




