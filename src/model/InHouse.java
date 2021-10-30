package model;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Part;

import java.security.PublicKey;

/** this class creates InHouse parts for the inventory management system.*/
public class InHouse extends Part {

    private int machineId;

    // constructor for the InHouse class inherited from the Part super class.
    /** This is the InHouse part object constructor method.
     This method is used to setup the parameters for an InHouse part object.
     @param id InHouse part ID
     @param name InHouse part name
     @param price InHouse part price
     @param stock InHouse part inventory
     @param min InHouse part inventory min
     @param max InHouse part Inventory max
     @param machineId InHouse part machine ID*/
    public InHouse(int id, String name, double price, int stock, int min, int max,int machineId) {
        super(id, name, price, stock, min, max);
        setMachineId(machineId);

        this.machineId = machineId;
    }



    /** This is the get machine ID method.
     This method returns the InHouse part machine ID value.
     @return the machineId
     */
    public int getMachineId() {
        return machineId;

    }

    /** This is the set machine ID method.
     This method sets the machine ID value.
     @param machineId the machineId to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
