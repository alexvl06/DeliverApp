/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import classes.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import models.*;

/**
 *
 * @author Alexis Avila
 */
public class Controller {

    public DefaultListModel createClientJlistModel(String type) {
        DefaultListModel model = new DefaultListModel();
        if ("Natural".equals(type)) {

            ArrayList<Natural> natural = NaturalModel.getNaturalList();
            for (int i = 0; i < natural.size(); i++) {
                model.addElement(natural.get(i).getFirstName() + " " + natural.get(i).getSecondName() + " " + natural.get(i).getFirstLastName() + " " + natural.get(i).getSecondLastName());

            }

        } else {
            ArrayList<Legal> legal = LegalModel.getLegalList();
            for (int i = 0; i < legal.size(); i++) {
                model.addElement(legal.get(i).getBusinessName());

            }

        }

        System.out.println("Client list: " + model);
        return model;

    }

    public void addClient(String businessName, String NIT, String adr, String mail, double parseDouble, String phoneNumber) {
        ShoppingCart cart = new ShoppingCart(ProductModel.getProductList());
        int id;
        if (LegalModel.getLegalList().isEmpty()) {
            id = 1;
        } else {
            id = LegalModel.getLegalList().size() + 1;
        }
        Legal legal = new Legal(businessName, NIT, adr, cart, mail, Integer.toString(id), parseDouble, phoneNumber);
        LegalModel.createLegal(legal);
    }

    public void addClient(String firstName, String secondName, String firstLastName, String secondLastName, String adr, String mail, double parseDouble, String phoneNumber) {
        ShoppingCart cart = new ShoppingCart(ProductModel.getProductList());
        int id;
        if (NaturalModel.getNaturalList().isEmpty()) {
            id = 1;
        } else {
            id = NaturalModel.getNaturalList().size() + 1;
        }
        Natural natural = new Natural(firstName, secondName, firstLastName, secondLastName, adr, cart, mail, Integer.toString(id), parseDouble, phoneNumber);
        NaturalModel.createNatural(natural);

    }

    public Legal getLegalData(String itemSelected) {

        Legal legal = LegalModel.getOneClient(Integer.toString(Integer.parseInt(itemSelected) + 1));
        return legal;

    }

    public Natural getNaturalData(String itemSelected) {
        Natural natural = NaturalModel.getOneClient(Integer.toString(Integer.parseInt(itemSelected) + 1));
        return natural;
    }

    public boolean deletedClient(String index, String selectedItem) {
        String newIndex = Integer.toString(Integer.parseInt(index) + 1);
        if (selectedItem.equals("Jurídico")) {
            return LegalModel.deleteLegal(newIndex);
        } else {
            return NaturalModel.deleteNatural(newIndex);
        }
    }

    public void updateClient(String firstName, String firstLastName, String adr, String mail, String itemSelected, double parseDouble, String phoneNumber) {
        ShoppingCart cart = new ShoppingCart(ProductModel.getProductList());
        String index = Integer.toString(Integer.parseInt(itemSelected) + 1);
        Legal legal = new Legal(firstName, firstLastName, adr, cart, mail, index, parseDouble, phoneNumber);
        LegalModel.updateLegal(legal);
    }

    public void updateClient(String firstName, String secondName, String firstLastName, String secondLastName, String adr, String itemSelected, String mail, double parseDouble, String phoneNumber) {
        ShoppingCart cart = new ShoppingCart(ProductModel.getProductList());
        String index = Integer.toString(Integer.parseInt(itemSelected) + 1);
        Natural natural = new Natural(firstName, secondName, firstLastName, secondLastName, adr, cart, mail, index, parseDouble, phoneNumber);
        NaturalModel.updateNatural(natural);
    }

    public void addProduct(String br, String sup, String value, String quant, String desc) {
        int id;
        if (ProductModel.getProductList().isEmpty()) {
            id = 1;
        } else {
            id = ProductModel.getProductList().size() + 1;
        }
        Product product = new Product(Integer.toString(id), desc, sup, Integer.parseInt(quant), Double.parseDouble(value), br);

        ProductModel.createProduct(product);
    }

    public ListModel<String> createProductJlistModel() {

        ArrayList<Product> product = ProductModel.getProductList();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < product.size(); i++) {
            model.addElement(product.get(i).getDescription() + " " + product.get(i).getBrand() + " a " + product.get(i).getPrice() + "$ pesos (" + product.get(i).getQuantity() + ")");

        }
        return model;
    }

    public void updateProduct(int i, String br, String sup, String value, String quant, String desc) {
        if (i != -1) {
         
            String index = Integer.toString(i + 1);
            Product product = new Product(index, desc, sup, Integer.parseInt(quant), Double.parseDouble(value), br);

            ProductModel.updateProduct(product);
        }

    }

    public boolean deletedProduct(int index) {
        String newIndex = Integer.toString(index+1);
        return ProductModel.deleteProduct(newIndex);
    }

}
