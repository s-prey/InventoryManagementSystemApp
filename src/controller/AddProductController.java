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
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class is used as a java fx controller to add products via the javafx GUI screen.*/
public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;
    boolean partInTable;

    @FXML
    private TextField productIDTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField productInvTxt;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TextField searchPartTxtField;

    //***************** Top Table ***********************************
    @FXML
    private TableView<Part> addPartTableView;

    @FXML
    private TableColumn<Part, Integer> addPartIDCol;

    @FXML
    private TableColumn<Part, String> addPartNameCol;

    @FXML
    private TableColumn<Part, Integer> addPartInvLevelCol;

    @FXML
    private TableColumn<Part, Double> addPartPriceCol;

    @FXML
    private TableView<Part> removePartTableView;

    //***************** Bottom Table ************************************
    @FXML
    private TableColumn<Part, Integer> rmvPartIDCol;

    @FXML
    private TableColumn<Part, String> rmvPartNameCol;

    @FXML
    private TableColumn<Part, Integer> rmvInvLevelCol;

    @FXML
    private TableColumn<Part, Double> rmvPartPriceCol;

   private ObservableList<Part> partsToAdd = FXCollections.observableArrayList();

    /** This is a get get new ID method.
     This method generates a new ID for new product.
     @return returns new product ID*/
   public static int getNewID() {
       int newID = 1;
       for (int i = 0; i < Inventory.getAllProducts().size(); i++) {
           newID++;
       }
       return newID;
   }

    /** This is the initialize method.
     This method is used to initialize data for the add product controller.
     @param url uniform resource locator to initialise
     @param rb resource bundle to initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Sets items in Tableview from the getAllParts method from Inventory class
        addPartTableView.setItems(Inventory.getAllParts());

        // Sets items in Tableview from the getAllProducts method from Inventory class
        removePartTableView.setItems(partsToAdd);

        // Top Table - Converts parameter data types from Part superclass to strings for display in Part Tableview columns.
        addPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Bottom Table - Converts parameter data types from Product class to strings for display in Product Tableview columns.
        rmvPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        rmvPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        rmvInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        rmvPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    /** This is the update part to add method.
     This method updates the remove parts tableview from parts to add tableview.
     */
    void updatePartsToAdd() {
        removePartTableView.setItems(partsToAdd);
    }

    /** This is the add product method.
     This method adds a selected part from the part tableview to the product associated parts tableview.
     @param event java fxml method trigger event
     */
    @FXML
    void onActionAddProduct(ActionEvent event) {
        Part part = addPartTableView.getSelectionModel().getSelectedItem();
        partInTable = false;

        if (part != null) {
            for (int i = 0; i < partsToAdd.size(); i++) {
                if (partsToAdd.get(i).getId() == part.getId()) {
                    partInTable = true;
                }
            }

            if (partInTable) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Duplicate Part");
                alert.setHeaderText("Part already with product.");
                alert.setContentText("Part is already associated with product.");
                alert.showAndWait();
            } else {
                partsToAdd.add(part);
                updatePartsToAdd();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Part Selected");
            alert.setHeaderText("No part selected to add to product.");
            alert.setContentText("Please select part to add to product.");
            alert.showAndWait();
        }
    }

    /** This is the cancel add product method.
     This method returns to the main menu GUI screen after accepting a cancel confirmation without saving a product.
     @param event java fxml method trigger event*/
    @FXML
    void onActionCancelProductEntry(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Add Product");
        alert.setHeaderText("Confirm Cancellation");
        alert.setContentText("Do you wish to cancel?");
        alert.showAndWait();

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the removed associated part method.
     This method removed a part from the product associated part table view.
     @param event java fxml method trigger event*/
    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       Part part = removePartTableView.getSelectionModel().getSelectedItem();

       alert.setTitle("Remove Part");
        alert.setHeaderText("Confirm part removal");
        alert.setContentText("Do you wish to remove the selected part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            partsToAdd.remove(part);
            updatePartsToAdd();;
        }
    }

    /** This is the save product method.
     This method saves the product with entered product parameters and associated parts.
     @param event java fxml method trigger event*/
    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {

        // Saves Add Product info, converts string txt from add product form to variable types.
        try {
            int productID = getNewID();
            String productName = productNameTxt.getText();
            int productInv = Integer.parseInt(productInvTxt.getText());
            double productPrice = Double.parseDouble(productPriceTxt.getText());
            int productMax = Integer.parseInt(productMaxTxt.getText());
            int productMin = Integer.parseInt(productMinTxt.getText());
            double partsTotalPrice = 0.0;

            //Calculates the total price of all added parts
            for (int i = 0; i < partsToAdd.size(); i++) {
                partsTotalPrice += partsToAdd.get(i).getPrice();
            }

            if (productMax < productMin) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Add product error");
                alert.setContentText("Product Max must be greater than Product Min!");
                alert.showAndWait();
                return;

            } else if (productInv > productMax || productInv < productMin) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Add product error");
                alert.setContentText("Product Inventory cannot be greater than Product Max or less than Product Min!");
                alert.showAndWait();
                return;

            } else if (productPrice < partsTotalPrice) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Add product error");
                alert.setContentText("Product Price cannot be less than total parts price!");
                alert.showAndWait();
                return;
            }

            // Creates new product using Inventory.addProduct method from Superclass.
            Product product = new Product(productID, productName, productPrice, productInv, productMin, productMax);
            for (int i = 0; i < partsToAdd.size(); i++) {
                product.addAssociatedPart(partsToAdd.get(i));
            }
            Inventory.addProduct(product);

        } catch (NumberFormatException e) {
            System.err.println("Unable to format, error type " + e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Add product error");
            alert.setContentText("Input format error!");
            alert.showAndWait();
            return;
        }

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the search add part method.
     This method searches and filters parts in the add part tableview based on user entered part ID or part name in
     the search field.
     @param event java fxml method trigger event*/
    @FXML
    void onActionSearchPart(ActionEvent event) {

       String searchPartString = searchPartTxtField.getText();

       ObservableList<Part> filteredPartList = searchByPartName(searchPartString);

       if (filteredPartList.size() == 0) {
           try {
               int id = Integer.parseInt(searchPartString);
               Part searchPart = searchPartID(id);
               if (searchPart != null) {
                   filteredPartList.add(searchPart);
               }
           }
           catch (NumberFormatException e) {
               //Not needed
           }
       }
       addPartTableView.setItems(filteredPartList);

    }
    // Method to search list of parts by part ID
    /** This is the search part ID method.
     This method is used to search for parts based on part ID integer.
     @param id part ID to search
     @return returns null with no part found
     */
    private Part searchPartID(int id) {
       ObservableList<Part> allParts = Inventory.getAllParts();
       for (int i = 0; i < allParts.size(); i++) {
           Part searchPart = allParts.get(i);

           if (searchPart.getId() == id) {
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
        ObservableList<Part> allPartsFilteredList = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();
        for (Part searchPart : allParts) {
            if(searchPart.getName().toLowerCase().contains(partName)) {
                allPartsFilteredList.add(searchPart);
            }
        }
        return allPartsFilteredList;
    }

}
