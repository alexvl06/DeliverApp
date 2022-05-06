/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author Alexis Avila
 */
public class Legal extends Client {

    private String businessName;
    private String NIT;

    public Legal(String businessName, String NIT, String address, ShoppingCart cart, String email, String id, Double money, String phoneNumber) {
        this.businessName = businessName;
        this.NIT = NIT;
        this.address = address;
        this.cart = cart;
        this.email = email;
        this.id = id;
        this.money = money;
        this.phoneNumber = phoneNumber;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;

    }

}
