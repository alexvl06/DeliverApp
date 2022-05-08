/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import classes.ShoppingCart;
import java.util.ArrayList;

/**
 *
 * @author Alexis Avila
 */
public class ShoppingCartModel {

    private static ArrayList<ShoppingCart> cartList;

    public ArrayList<ShoppingCart> getShoppingCartList() {
        return cartList;
    }

    public void setShoppingCartlList(ArrayList<ShoppingCart> cartList) {
        ShoppingCartModel.cartList = cartList;
    }

    //CRUD
    public static ShoppingCart getOneClient(String id) {
        try {
            for (int i = 0; i < ShoppingCartModel.cartList.size(); i++) {
                if (ShoppingCartModel.cartList.get(i).getId().equals(id)) {

                    return ShoppingCartModel.cartList.get(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Client not found: " + e);
        }
        return null;

    }

    public static boolean deleteShoppingCart(String id) {
        for (int i = 0; i < ShoppingCartModel.cartList.size(); i++) {
            if (ShoppingCartModel.cartList.get(i).getId().equals(id)) {

                ShoppingCartModel.cartList.remove(i);
                for (int j = i; j < ShoppingCartModel.cartList.size(); j++) {
                    ShoppingCartModel.cartList.get(j).setId(Integer.toString(Integer.parseInt(ShoppingCartModel.cartList.get(j).getId()) - 1));
                }
                return true;
            }
        }
        return false;
    }

    public void createShoppingCart(ShoppingCart cart) {
        ShoppingCartModel.cartList.add(cart);
    }

    public void updateShoppingCart(ShoppingCart cart) {
        for (int i = 0; i < ShoppingCartModel.cartList.size(); i++) {
            if (ShoppingCartModel.cartList.get(i).getId().equals(cart.getId())) {
            } else {
                ShoppingCartModel.cartList.set(i, cart);
            }
        }
    }

}
