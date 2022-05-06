/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deliverapp.classes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Date;

/**
 *
 * @author inter-telco
 */
public class ShoppingCart {

    private Date creationDate;
    private ArrayList<Request> requestList;
    private ArrayList<Product> productList;
    private Payment pay;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Payment getPay() {
        return pay;
    }

    public void setPay(Payment pay) {
        this.pay = pay;
    }

    public ArrayList<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(ArrayList<Request> requestList) {
        this.requestList = requestList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void addRequest(String productName, Integer quantity) {
        this.requestList.add(new Request(String.valueOf(this.requestList.size()+1),productName, quantity));
    }

    public Double getTotalDebt() {
        Double debt = 0.0;
        for (int i = 0; i < this.requestList.size(); i++) {
            debt = debt + this.requestList.get(i).getValue();
        }
        return debt;
    }

    public Boolean toPay(Double value, String id) {
        Double debt = this.getTotalDebt();
        if (Objects.equals(value, debt)) {

            this.pay.setValue(value);
            this.pay.setUserId(id);
            for (int i = 0; i < this.requestList.size(); i++) {
                this.requestList.get(i).setStatus("Payed");
            }
            return true;
        } else {

            return false;
        }

    }

    public ShoppingCart(ArrayList<Product> productList) {
        this.creationDate = new Date();
        this.productList = productList;
        this.requestList = new ArrayList<>();
    }

    public Boolean cartIsLiving() {
        Date currentTime = new Date();

        if ((currentTime.getTime() - this.creationDate.getTime()) == Math.pow(10, 7) * 8.64) {
            this.creationDate = new Date();
            this.productList = new ArrayList<>();
            this.requestList = new ArrayList<>();
            return true;
        }
        return false;
    }

    public void validateRequestList() {
        for (int i = 0; i < this.requestList.size(); i++) {

            for (int j = 0; j < this.productList.size(); j++) {
                if (this.requestList.get(i).getId().equals(this.productList.get(j).getId())) {

                    this.requestList.get(i).setStatus("found");
                }
            }

        }

        for (int j = 0; j < this.productList.size(); j++) {
            if (!"found".equals(this.requestList.get(j).getStatus())) {

                this.requestList.get(j).setStatus("failed");
            }

        }
    }

    /*    void addRequest(String productName, Integer quantity) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}
