/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deliverapp.classes;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author inter-telco
 */
public class Payment {
    private String id;
    private Date creationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    private String userId;
    private Double value;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Payment(String id, String userId, Double value) {
        this.id = id;
        this.creationDate = new Date();
        this.userId = userId;
        this.value = value;
    }
    
    public String getBill(){
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        
        return " Factura número: "+this.id+"\n Fecha de pago: "+this.creationDate+"\n Id de usuario: "+df.format(this.creationDate)+"\n Por concepto de: $"+this.value+" pesos.";
    }
    
}
