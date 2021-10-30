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
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;

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

    @FXML
    private TableColumn<Part, Integer> rmvPartIDCol;

    @FXML
    private TableColumn<Part, String> rmvPartNameCol;

    @FXML
    private TableColumn<Part, Integer> rmvInvLevelCol;

    @FXML
    private TableColumn<Part, Double> rmvPartPriceCol;

    @FXML
    void onActionAddProduct(ActionEvent event) {

        int id = Integer.parseInt(productIDTxt.getText());
        String name = productNameTxt.getText();
        double price = Double.parseDouble(productPriceTxt.getText());
        int stock = Integer.parseInt(productInvTxt.getText());
        int max = Integer.parseInt(productMaxTxt.getText());
        int min = Integer.parseInt(productMinTxt.getText());

        Inventory.addProduct(new Product(id, name, price, stock, min, max));
    }

    @FXML
    void onActionCancelProductEntry(ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
        // DELETES ASSOCIATED PART FROM PRODUCT. NEEDS HANDLER.
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        // NEEDS SAVE PRODUCT HANDLER.
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSearchPart(ActionEvent event) {
        // SEARCH PARTS IN TABLEVIEW THAT CAN BE ADDED TO THE PRODUCT. NEEDS HANDLER.
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Sets items in Tableview from the getAllParts method from Inventory class
        addPartTableView.setItems(Inventory.getAllParts());
        // Sets items in Tableview from the getAllProducts method from Inventory class
        //removePartTableView.setItems(Inventory.getAllParts());

        // Converts parameter data types from Part superclass to strings for display in Part Tableview columns.
        addPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Converts parameter data types from Product class to strings for display in Product Tableview columns.
        //rmvPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        //rmvPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        //rmvInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        //rmvPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
