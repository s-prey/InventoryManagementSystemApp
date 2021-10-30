package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;

public class AddPartController {

    Stage stage;
    Parent scene;



    @FXML
    private TextField partIDTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partPriceTxt;

    @FXML
    private TextField partInvTxt;

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
    private Label partMachineIDCompanyNameLbl;

   @FXML
   void displayInHouse() {
       partMachineIDCompanyNameLbl.setText("Machine ID");
   }

   @FXML
   void displayOutsourced() {
       partMachineIDCompanyNameLbl.setText("Company Name");
   }


    @FXML
    void onActionReturnToMain(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        // gets add part parameters from form as string values and converts to data types same as classes.

        if (partInHouseRBtn.isSelected()) {
            int id = Integer.parseInt(partIDTxt.getText());
            String name = partNameTxt.getText();
            double price = Double.parseDouble(partPriceTxt.getText());
            int stock = Integer.parseInt(partInvTxt.getText());
            int max = Integer.parseInt(PartMaxTxt.getText());
            int min = Integer.parseInt(partMinTxt.getText());
            int machineId = Integer.parseInt(partMachineIDCompanyNameTxt.getText());

            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));


        }
        if (partOutsourcedRBtn.isSelected()){
            int id = Integer.parseInt(partIDTxt.getText());
            String name = partNameTxt.getText();
            double price = Double.parseDouble(partPriceTxt.getText());
            int stock = Integer.parseInt(partInvTxt.getText());
            int max = Integer.parseInt(PartMaxTxt.getText());
            int min = Integer.parseInt(partMinTxt.getText());
            String companyName = partMachineIDCompanyNameTxt.getText();

            Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
        }

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


}
