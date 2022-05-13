/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author inter-telco
 */
public class Request {

    private String id;
    private Product product;
    private final Date creationDate;
    private Integer quantity;
    private String status;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCreationDate() {
        DateFormat dateShort = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat timeShort = DateFormat.getTimeInstance(DateFormat.SHORT);
        return dateShort.format(this.creationDate) + " " + timeShort.format(this.creationDate);
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Request(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.status = "Pendiente";
        this.creationDate = new Date();
    }

    public Integer getQuantity() {
        return quantity;
    }


    public void increaseQuantity() {
        int q = this.getQuantity();
        this.quantity = ++q;
    }

    public void decreaseQuantity() {
        int q = this.getQuantity();
        this.quantity = --q;
    }
}
