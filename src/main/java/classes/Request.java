/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.Date;

/**
 *
 * @author inter-telco
 */
public class Request {

    private String id;
    private String productId;
    private Date creationDate;
    private String description;
    private String brand;
    private Integer quantity;
    private Double value;
    private String status;
    private Send send;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Send getSend() {
        return send;
    }

    public void setSend(Send send) {
        this.send = send;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Request(String id, String description, Integer quantity, String brand) {
        this.productId = id;
        this.description = description;
        this.quantity = quantity;
        this.value = 0.0;
        this.status = "untested";
        this.creationDate = new Date();
        this.brand = brand;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public void increaseQuantity(){      
        int q = this.getQuantity();
        this.setQuantity(++q);
    }
        public void descreaseQuantity(){      
        int q = this.getQuantity();
        this.setQuantity(--q);
    }
}
