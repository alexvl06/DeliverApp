/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author Alexis Avila
 */
public class Natural extends Client {
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;

    public Natural(String firstName, String secondName, String firstLastName, String secondLastName, String address, String email, String id, Double money, String phoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.address = address;
        this.cart = new ShoppingCart();
        this.email = email;
        this.id = id;
        this.money = money;
        this.phoneNumber = phoneNumber;
    }
    
    public Natural(){}
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }
    
}
