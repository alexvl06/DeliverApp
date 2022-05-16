/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author inter-telco
 */
public class Product extends ProductLine {

    private String id;
    private String description;
    private String brand;
    private String supplier;
    private double price;



    public void setPrice(double price) {
        this.price = price;
    }



 

    public Product(String id, String description, String supplier, Integer quantity, Double price, String brand) {
        this.id = id;
        this.description = description;
        this.supplier = supplier;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
