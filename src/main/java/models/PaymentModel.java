/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import classes.Payment;
import java.util.ArrayList;

/**
 *
 * @author Alexis Avila
 */
public class PaymentModel {

    private static ArrayList<Payment> paymentList;

    public ArrayList<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentlList(ArrayList<Payment> paymentList) {
        PaymentModel.paymentList = paymentList;
    }

    //CRUD
    public Payment getOneClient(String id) {
        try {
            for (int i = 0; i < PaymentModel.paymentList.size(); i++) {
                if (PaymentModel.paymentList.get(i).getId().equals(id)) {

                    return PaymentModel.paymentList.get(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Client not found: " + e);
        }
        return null;

    }

    public boolean deletePayment(String id) {
        for (int i = 0; i < PaymentModel.paymentList.size(); i++) {
            if (PaymentModel.paymentList.get(i).getId().equals(id)) {

                PaymentModel.paymentList.remove(i);
                for (int j = i; j < PaymentModel.paymentList.size(); j++) {
                    PaymentModel.paymentList.get(j).setId(Integer.toString(Integer.parseInt(PaymentModel.paymentList.get(j).getId()) - 1));
                }
                return true;
            }
        }
        return false;
    }

    public void createPayment(Payment payment) {
        PaymentModel.paymentList.add(payment);
    }

    public void updatePayment(Payment payment) {
        for (int i = 0; i < PaymentModel.paymentList.size(); i++) {
            if (PaymentModel.paymentList.get(i).getId().equals(payment.getId())) {

                PaymentModel.paymentList.set(i, payment);
            }
        }
    }

}
