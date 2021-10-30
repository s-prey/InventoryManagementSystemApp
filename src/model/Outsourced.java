package model;

import model.Part;

/** this class creates Outsourced parts for the inventory management system.*/
public class Outsourced extends Part {

    private String companyName;

    // constructor for the InHouse class inherited from the Part super class.
    /** This is the Outsourced part object constructor method.
     This method is used to setup the parameters for an Outsourced part object.
     @param id Outsourced part ID
     @param name Outsourced part name
     @param price Outsourced part price
     @param stock Outsourced part inventory
     @param min Outsourced part min inventory
     @param max Outsourced part max inventory
     @param companyName Outsourced part company name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);

        this.companyName = companyName;
    }

    /** This is the get company name method.
     This method returns the Outsourced part company name.
     @return returns the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /** This is the set company name method.
     This method sets the Outsourced part company name.
     @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
