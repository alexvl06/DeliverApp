/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;
import java.util.Objects;


/**
 *
 * @author inter-telco
 */
public class Client {
   protected int id;
   protected String address;
   protected String phoneNumber;
   protected String email;
   protected Double money;
   protected ArrayList<Request> requestList;

    public ArrayList<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(ArrayList<Request> requestList) {
        this.requestList = requestList;
    }
   


    public int getId() {
        return id;
    }

    public void setId(int id) {
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



    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
    
    

    public Payment toPay(Double value, int id) {
        Double debt = this.getTotalValueToPay();
        if (Objects.equals(value, debt)) {
            Payment pay = new Payment(id, value);
            for (int i = 0; i < this.requestList.size(); i++) {
                this.requestList.get(i).setStatus("Pagado");
            }
            return pay;
        } else {

            return null;
        }
        

    }
        public Double getTotalValueToPay() {
        Double debt = 0.0;
        for (int i = 0; i < this.requestList.size(); i++) {
            if("Pendiente".equals(requestList.get(i).getStatus())){
                debt += (this.requestList.get(i).getProduct().getPrice()*this.requestList.get(i).getQuantity());
            }
            
        }
        return debt;
    }
    
}
