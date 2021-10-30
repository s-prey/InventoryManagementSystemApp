package InventoryManagmentSystemApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

/**
 *
 * @author Steven Prey
 */

/** This class creates an app for the inventory management system.*/
public class InventoryManagementSystemMain extends Application {

    /** this is the start stage method.
     this method starts the javafx stage and scene load
     @param primaryStage primary javafx stage to start
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        primaryStage.setScene(new Scene(root/*, 300, 275*/));
        primaryStage.show();
    }

    /** This is the main method.
     * This is the first method that gets called when the java program is started.
     @param args supplied command-line arguments*/
    public static void main(String[] args) {


        // Creates new parts with pre-populated values for the Parts Tableview.
       InHouse part1 = new InHouse(1, "Shifter", 49.99, 10, 5, 15, 2552);
       InHouse part2 = new InHouse(2, "Rear Sprocket", 99.99, 15, 5, 15, 8929);
       InHouse part3 = new InHouse(3, "Handlebar", 74.99, 14, 6, 20, 2777);
       Outsourced part4 = new Outsourced(4, "Seat", 29.99, 5, 2, 10,"Bike Seats Inc.");
        // utilizes addPart method from the Inventory class for view in the Part Tableview.
       Inventory.addPart(part1);
       Inventory.addPart(part2);
       Inventory.addPart(part3);
       Inventory.addPart(part4);

       // Creates new products with pre-populated values for the Product Tableview.
       Product product1 = new Product(1, "GT Bicycle", 499.99, 10, 5, 12);
       Product product2 = new Product(2, "Specialized Bicycle", 1199.99, 6, 3, 10);

       product1.addAssociatedPart(part1);
       product1.addAssociatedPart(part2);

       product2.addAssociatedPart(part3);
       product2.addAssociatedPart(part4);

       // utilizes addProduct method from the Inventory class for view in the Product Tableview.
       Inventory.addProduct(product1);
       Inventory.addProduct(product2);

        launch(args);
    }
}
