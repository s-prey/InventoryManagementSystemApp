module MVCApplicationPractice {

    requires javafx.fxml;
    requires javafx.controls;


    opens model;
    opens controller;
    opens InventoryManagmentSystemApplication;
}