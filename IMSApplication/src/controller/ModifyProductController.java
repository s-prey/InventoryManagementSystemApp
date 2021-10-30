package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyProductController {

    Stage stage;
    Parent scene;

    @FXML
    private TextField productIDTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField ProductInvTxt;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TextField searchPartTxtField;

    @FXML
    private TableView<?> addPartTableView;

    @FXML
    private TableColumn<?, ?> addPartIDCol;

    @FXML
    private TableColumn<?, ?> addPartNameCol;

    @FXML
    private TableColumn<?, ?> addPartInvLevelCol;

    @FXML
    private TableColumn<?, ?> addPartPriceCol;

    @FXML
    private TableView<?> removePartTableView;

    @FXML
    private TableColumn<?, ?> rmvPartIDCol;

    @FXML
    private TableColumn<?, ?> rmvPartNameCol;

    @FXML
    private TableColumn<?, ?> rmvInvLevelCol;

    @FXML
    private TableColumn<?, ?> rmvPartPriceCol;

    @FXML
    void onActionCancelProductEntry(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
    // BUTTON REMOVES PART ASSOCIATED WITH SELECTED PRODUCT. NEEDS HANDLER.
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {

        // BUTTON NEEDS TO SAVE PRODUCT IN INVENTORY WITH ASSOCIATED PARTS. NEEDS HANDLER.
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
