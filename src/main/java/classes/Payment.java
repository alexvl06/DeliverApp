/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author inter-telco
 */
public class Payment {

    private final String id;
    private final Date creationDate;
    private final int userId;
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }



 
    public Payment(int userId, Double value) {
       Random random = new Random();
        
        this.id = Integer.toString(random.nextInt(0+100));
        this.creationDate = new Date();
        this.userId = userId;
        this.value = value;
    }

    public String getBill() {
        

        return " Factura número: " + this.id + "\n Fecha de pago: " + this.creationDate + "\n Id de usuario: " + this.userId + "\n Por concepto de: $" + this.value + " pesos.";
    }

}
