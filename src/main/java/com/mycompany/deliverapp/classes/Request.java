/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deliverapp.classes;

import java.util.Date;

/**
 *
 * @author inter-telco
 */
public class Request {
    private String id;
    private Date creationDate;
    private String productName;
    private Integer quantity;
    private Double value;
    private String status;
    private Send send;


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
    
    

    Request(String id, String productName, Integer quantity){
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.value = 0.0;
        this.status = "untested";
        this.creationDate = new Date();
    }

    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
