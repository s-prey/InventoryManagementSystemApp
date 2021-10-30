package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    Stage stage;
    Parent scene;


    @FXML
    private VBox mainPartTableView;

    @FXML
    private TextField searchPartTxtField;

    @FXML
    private TableView<Part> mainPartsTableView;

    @FXML
    private TableColumn<Part, Integer> mainPartIDCol;

    @FXML
    private TableColumn<Part, String> mainPartNameCol;

    @FXML
    private TableColumn<Part, Integer> mainPartInvLevelCol;

    @FXML
    private TableColumn<Part, Double> mainPartPriceCol;

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

    @FXML
    void onActionAddPartMain(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddProductMain(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePartMain(ActionEvent event) {
    // BUTTON DELETES THE SELECTED PART EVENT. NEEDS HANDLER/

    }

    @FXML
    void onActionDeleteProductMain(ActionEvent event) {
        // BUTTON DELETES THE SELECTED PRODUCT. NEEDS HANDLER.
    }

    @FXML
    void onActionExitMain(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    void onActionModifyPartMain(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyProductMain(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSearchPart(ActionEvent event) {
    // SEARCH PART FILTER TABLEVIEW. NEEDS HANDLER.
    }

    @FXML
    void onActionSearchProduct(ActionEvent event) {
    // SEARCH PRODUCT FILTER TABLEVIEW. NEEDS HANDLER.
    }

    public boolean searchPart(int id) {
        for(Part part : Inventory.getAllParts()){
            if(part.getId() == id)
                return true;
        }
        return false;
    }

    public boolean searchProduct(int id) {
        for(Part product : Inventory.getAllParts()){
            if(product.getId() == id)
                return true;
        }
        return false;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Sets items in Tableview from the getAllParts method from Inventory class
        mainPartsTableView.setItems(Inventory.getAllParts());
        // Sets items in Tableview from the getAllProducts method from Inventory class
        mainProductTableView.setItems(Inventory.getAllProducts());

        // Converts parameter data types from Part superclass to strings for display in Part Tableview columns.
        mainPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainPartInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Converts parameter data types from Product class to strings for display in Product Tableview columns.
        mainProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainProductNamCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainProductInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        if(searchPart(4))
            System.out.println("Part Found");
        else
            System.out.println("Part Not Found");

        if(searchProduct(4))
            System.out.println("Product Found");
        else
            System.out.println("Product Not Found");
    }

}




