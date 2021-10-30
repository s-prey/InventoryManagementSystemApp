package controller;

import javafx.collections.FXCollections;
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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class is used as a java fx controller to modify existing parts via the javafx GUI screen.*/
public class ModifyPartController implements Initializable {

    Stage stage;
    Parent scene;


    //FXML TextFields and Radio Buttons
    @FXML
    private TextField partIDTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partPriceTxt;

    @FXML
    private TextField partInvTxt;

    @FXML
    private TextField partMaxTxt;

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
    private Label partMachineIDCompanyNameLbl;

    private static Part partToModify;
    private boolean isInHouse;


    /** This is the pass part to modify method.
     This method passes the selected part data from the main menu screen for the modify part controller class.
     @param part modified part to pass data from
     */
    public static void passPart(Part part) {
        partToModify = part;
    }

    /** This is the initialize method.
     This method is used to initialize data for the modify part controller class.
     @param url uniform resource locator to initialise
     @param rb resource bundle to initialize
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (partToModify instanceof InHouse) {
            partMachineIDCompanyNameLbl.setText("Machine ID");
            partInHouseRBtn.setToggleGroup(partSourceTG);
            partInHouseRBtn.setSelected(true);

        } else if (partToModify instanceof Outsourced) {
            partMachineIDCompanyNameLbl.setText("Company Name");
            partOutsourcedRBtn.setToggleGroup(partSourceTG);
            partOutsourcedRBtn.setSelected(true);
        }


        int selectedPartID = MainMenuController.selectedPartID;
        partIDTxt.setText(Integer.toString(selectedPartID));
        Part partToModify = Inventory.lookupPart(selectedPartID);
        partIDTxt.setDisable(true);
        partIDTxt.setPromptText(Integer.toString(partToModify.getId()));
        partNameTxt.setText(partToModify.getName());
        partInvTxt.setText(Integer.toString(partToModify.getStock()));
        partPriceTxt.setText(Double.toString(partToModify.getPrice()));
        partMaxTxt.setText(Integer.toString(partToModify.getMax()));
        partMinTxt.setText(Integer.toString(partToModify.getMin()));
    }

    /** This is the display InHouse part method.
     This method sets the InHouse part machine ID field label based on boolean value of InHouse part.
     @param event java fxml method trigger event
     */
    @FXML
    void displayInHouse(ActionEvent event) {
        partMachineIDCompanyNameLbl.setText("Machine ID");
        isInHouse = true;
    }
    /** This is the display Outsourced part method.
     This method sets the Outsourced part company name field label based on boolean value of InHouse part.
     @param event java fxml method trigger event
     */
    @FXML
    void displayOutsourced(ActionEvent event) {
        partMachineIDCompanyNameLbl.setText("Company Name");
        isInHouse = false;
    }

    /** This is the cancel part method.
     This method generates a cancel part confirmation screen when the cancel button is selected then returns
     to the main screen if OK is selected.
     @param event java fxml method trigger event
     */
        @FXML
    void onActionCancelPart(ActionEvent event) throws IOException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cancel");
            alert.setHeaderText("Confirm Part Cancel ");
            alert.setContentText("Updated part information will not be saved, do you wish to continue cancel?");
            alert.showAndWait();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
    }

    /** This is the save part method.
     This method saves a part object (either InHouse or Outsourced) when the save button is selected in the
     add part GUI screen.
     @param event java fxml method trigger event
     */
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        try {
            int partID = partToModify.getId();
            String partName = partNameTxt.getText();
            int partInventory = Integer.parseInt(partInvTxt.getText());
            double partPrice = Double.parseDouble(partPriceTxt.getText());
            int partMax = Integer.parseInt(partMaxTxt.getText());
            int partMin = Integer.parseInt(partMinTxt.getText());
            int machineID = 0;
            String companyName = null;

            if (isInHouse) {
                machineID = Integer.parseInt(partMachineIDCompanyNameTxt.getText());
            }
            else if (!isInHouse) {
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
                alert.setContentText("Part Inventory must be between Part Min and Part Max!");
                alert.showAndWait();
                return;
            }

            if (isInHouse) {
               Inventory.updatePart(partID, new InHouse(partID, partName, partPrice, partInventory, partMin, partMax, machineID));
            } else if (!isInHouse){
                Inventory.updatePart(partID, new Outsourced(partID, partName, partPrice, partInventory, partMin, partMax,  companyName));
            }
        } catch (NumberFormatException e) {
            System.err.println("Unable to format, error type. " + e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Add part error");
            alert.setContentText("Part input format error! Please enter part data in the correct format.");
            alert.showAndWait();
            return;
        }
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /** this is the send part information method.
     This method passes part information for the selected modified part in the main mane screen, used by the modify
     part change scene method by main menu controller.
     @param part part to get parameters*/
    public void sendPart(Part part) {
        partIDTxt.setText(String.valueOf(part.getId()));
        partNameTxt.setText(part.getName());
        partPriceTxt.setText(String.valueOf(part.getPrice()));
        partInvTxt.setText(String.valueOf(part.getStock()));
        partMaxTxt.setText(String.valueOf(part.getMax()));
        partMinTxt.setText(String.valueOf(part.getMin()));

        if (part instanceof InHouse) {
            partMachineIDCompanyNameTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
            partInHouseRBtn.setToggleGroup(partSourceTG);
            partInHouseRBtn.setSelected(true);
            partMachineIDCompanyNameLbl.setText("Machine ID");
            isInHouse = true;

        } else if (part instanceof Outsourced) {
            partMachineIDCompanyNameTxt.setText(String.valueOf(((Outsourced) part).getCompanyName()));
            partOutsourcedRBtn.setToggleGroup(partSourceTG);
            partOutsourcedRBtn.setSelected(true);
            partMachineIDCompanyNameLbl.setText("Company Name");
            isInHouse = false;
        }
    }
}
