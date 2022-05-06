/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deliverapp.classes;

/**
 *
 * @author Alexis Avila
 */
public class ProductLine {

    protected Double price;
    protected Integer quantity;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



    public Boolean reduceQuantity() {
        if (this.quantity == 0) {
            return false;
        }

        this.quantity--;
        return true;

    }
}
