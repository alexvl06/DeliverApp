/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

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
    private Payment pay;

    public void setPay(Payment pay) {
        this.pay = pay;
    }

    public Payment getPay() {
        return pay;
    }



    public ArrayList<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(ArrayList<Request> requestList) {
        this.requestList = requestList;
    }

    public void addRequest(Integer quantity, Product product) {
        this.requestList.add(new Request(product, quantity));
    }

    public Double getTotalDebt() {
        Double debt = 0.0;
        for (int i = 0; i < this.requestList.size(); i++) {
            debt = debt + (this.requestList.get(i).getProduct().getPrice()*this.requestList.get(i).getQuantity());
        }
        return debt;
    }

    public Boolean toPay(Double value, String id) {
        Double debt = this.getTotalDebt();
        if (Objects.equals(value, debt)) {
            this.pay = new Payment(id, value);
            for (int i = 0; i < this.requestList.size(); i++) {
                this.requestList.get(i).setStatus("Pagado");
            }
            return true;
        } else {

            return false;
        }

    }

    public ShoppingCart() {
        this.creationDate = new Date();
        this.requestList = new ArrayList<>();
        
    }

    public Boolean cartIsLiving() {
        Date currentTime = new Date();

        if ((currentTime.getTime() - this.creationDate.getTime()) == Math.pow(10, 7) * 8.64) {
            this.creationDate = new Date();
            this.requestList = new ArrayList<>();
            return true;
        }
        return false;
    }

}
