/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deliverapp.classes;

import java.util.ArrayList;

/**
 *
 * @author inter-telco
 */
public class Client {
   private String id;
   private String address;
   private String phoneNumber;
   private String email;
   private ShoppingCart cart;
   private Double money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
   
    public void doRequest(String productName, Integer quantity){
        cart.addRequest(productName, quantity);
    }
   
    public ArrayList<Product> getListProduct(){
     return this.cart.getProductList();
    }
    
    public Double getTotalValueToPay(){
         return this.cart.getTotalDebt();
    } 
    
    public Boolean toPay(Double value, String id){
        return this.cart.toPay(value, id);
    }

    public Client(String id, String address, ShoppingCart cart, String phoneNumber, String email, Double money) {
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cart = cart;
        this.money = money;
    }
    
}
