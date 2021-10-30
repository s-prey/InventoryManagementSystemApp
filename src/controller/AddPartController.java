package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

/** This class is used as a java fx controller to add parts via the javafx GUI screen.*/
public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;
    private boolean isInHouse;


    @FXML
    private TextField partIDTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partInvTxt;

    @FXML
    private TextField partPriceTxt;

    @FXML
    private TextField PartMaxTxt;

    @FXML
    private TextField partMachineIDCompanyNameTxt;

    @FXML
    private TextField partMinTxt;

    @FXML
    private RadioButton partInHouseRBtn;

    @FXML
    private ToggleGroup partSourceTG;

    @FXML
    private RadioButton partOutsourcedRBtn;

    @FXML
    private Button partSaveButton;

    @FXML
    private Button partCancelButton;

    @FXML
    private Label partMachineIDCompanyNameLbl;

    Part part;

    /** This is a get get new ID method.
     This method generates a new ID for new part.
     @return returns new part ID
     */
    public static int getNewID() {
        int newID = 1;
        for (int i = 0; i< Inventory.getAllParts().size(); i++) {
            newID++;
        }
        return  newID;
    }

    /** This is the initialize method.
     This method is used to initialize the add part controller.
     @param url uniform resource locator to initialise
     @param rb resource bundle to initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /** This is the display InHouse method.
     This method sets the toggle button and machine ID field label for the InHouse part GUI screen.
     @param event java fxml method trigger event
     */
    @FXML
    void displayInHouse(ActionEvent event) {
        partMachineIDCompanyNameLbl.setText("Machine ID");
        RadioButton selectedRadioButton = (RadioButton) partSourceTG.getSelectedToggle();
        String toggle = selectedRadioButton.getText();
        if(toggle.equals("partInHouseRBtn")) {
            partMachineIDCompanyNameLbl.setVisible(true);
        }
        isInHouse = true;
    }

    /** This is the display Outsourced method.
     This method sets the toggle button and company name field label for the Outsourced part GUI screen.
     @param event java fxml method trigger event*/
    @FXML
    void displayOutsourced(ActionEvent event) {
        partMachineIDCompanyNameLbl.setText("Company Name");

        RadioButton selectedRadioButton = (RadioButton) partSourceTG.getSelectedToggle();
        String toggle = selectedRadioButton.getText();
        if(toggle.equals("partOutsourcedRBtn")) {
            partMachineIDCompanyNameLbl.setVisible(false);
        }
        isInHouse = false;
    }

    /** This is the cancel part method.
     This method generates a cancel part confirmation screen when the cancel button is selected then returns
     to the main screen if OK is selected.
     @param event java fxml method trigger event*/
    @FXML
    void onActionReturnToMain(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Confirm Part Cancel ");
        alert.setContentText("Part information will not be saved, do you wish to continue cancel?");
        alert.showAndWait();

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the save part method.
     This method saves a part object (either InHouse or Outsourced) when the save button is selected in the
     add part GUI screen.
     @param event java fxml method trigger event*/
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        // Initializes variables and assigns field text to variables
        try {
            int partID = getNewID();
            String partName = partNameTxt.getText();
            int partInventory = Integer.parseInt(partInvTxt.getText());
            double partPrice = Double.parseDouble(partPriceTxt.getText());
            int partMax = Integer.parseInt(PartMaxTxt.getText());
            int partMin = Integer.parseInt(partMinTxt.getText());
            int machineID = 0;
            String companyName = null;

            // Checks for radio button selection and changes machineID/companyName field accordingly
            if (isInHouse) {
                machineID = Integer.parseInt(partMachineIDCompanyNameTxt.getText());
            } else if (!isInHouse) {
                companyName = partMachineIDCompanyNameTxt.getText();
            }


            if (partMax < partMin) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Add part error");
                alert.setContentText("Part Max must be greater than Part Min!");
                alert.showAndWait();
                return;

            } else if (partInventory > partMax || partInventory < partMin) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Add part error");
                alert.setContentText("Part Inventory cannot be greater than Part Max or less than Part Min!");
                alert.showAndWait();
                return;
            }


            if (isInHouse) {
                Inventory.addPart(new InHouse(partID, partName, partPrice, partInventory, partMin, partMax, machineID));

            } else if (!isInHouse){
                Inventory.addPart(new Outsourced(partID, partName, partPrice, partInventory, partMin, partMax, companyName));

            }
        } catch (NumberFormatException e) {
            System.err.println("Unable to format, error type. " + e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Add part error");
            alert.setContentText("Input format error!");
            alert.showAndWait();
            return;
        }

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}





