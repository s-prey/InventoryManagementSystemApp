package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;


public class DisplayMainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        //primaryStage.setTitle();
        primaryStage.setScene(new Scene(root/*, 300, 275*/));
        primaryStage.show();
    }




   public static void main(String[] args) {

        // Creates new parts with pre-populated values for the Parts Tableview.
       InHouse part1 = new InHouse(1, "Shifter", 49.99, 10, 5, 15, 2552);
       InHouse part2 = new InHouse(2, "Rear Sprocket", 99.99, 15, 5, 15, 8929);
       InHouse part3 = new InHouse(3, "Handlebar", 74.99, 14, 6, 20, 2777);
       Outsourced part4 = new Outsourced(4, "Seat", 29.99, 5, 2, 10, "Bike Seats Inc.");
        // utilizes addPart method from the Inventory class for view in the Part Tableview.
       Inventory.addPart(part1);
       Inventory.addPart(part2);
       Inventory.addPart(part3);
       Inventory.addPart(part4);

       // Creates new products with pre-populated values for the Product Tableview.
       Product product1 = new Product(1, "GT Bicycle", 499.99, 10, 5, 12);
       Product product2 = new Product(2, "Specialized Bicycle", 1199.99, 6, 3, 10);
        // utilizes addProduct method from the Inventory class for view in the Product Tableview.
       Inventory.addProduct(product1);
       Inventory.addProduct(product2);

        launch(args);
    }


}
