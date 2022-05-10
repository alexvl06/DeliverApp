/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import classes.*;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
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
        ShoppingCart cart = new ShoppingCart();
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
        ShoppingCart cart = new ShoppingCart();
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
        ShoppingCart cart = new ShoppingCart();
        String index = Integer.toString(Integer.parseInt(itemSelected) + 1);
        Legal legal = new Legal(firstName, firstLastName, adr, cart, mail, index, parseDouble, phoneNumber);
        LegalModel.updateLegal(legal);
    }

    public void updateClient(String firstName, String secondName, String firstLastName, String secondLastName, String adr, String itemSelected, String mail, double parseDouble, String phoneNumber) {
        ShoppingCart cart = new ShoppingCart();
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
        String newIndex = Integer.toString(index + 1);
        return ProductModel.deleteProduct(newIndex);
    }

    public Product getProductData(int i) {
        Product product = ProductModel.getOneProduct(Integer.toString(i + 1));
        return product;
    }

    public DefaultListModel createRequestsList(int[] indexs, boolean decrementFlat) {
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < indexs.length; i++) {
            indexList.add(indexs[i]);
        }

        DefaultListModel model;

        if (RequestModel.getRequestList().isEmpty()) {

            if (!decrementFlat) {
                this.addRequest(indexList);
            }
            model = this.createRequestModel();

        } else {

            boolean listAccepted = true;

            for (int i = 0; i < indexs.length; i++) {

                for (int j = 0; j < RequestModel.getRequestList().size(); j++) {
                    if (RequestModel.getRequestList().get(j).getProductId().equals(Integer.toString(indexs[i] + 1))) {
                        if ((!decrementFlat && RequestModel.getRequestList().get(j).getQuantity().equals(ProductModel.getOneProduct(RequestModel.getRequestList().get(j).getProductId()).getQuantity())) || (decrementFlat && RequestModel.getRequestList().get(j).getQuantity().equals(1))) {
                            listAccepted = false;
                            if (decrementFlat) {
                                ArrayList<Integer> integerList = new ArrayList<>();
                                integerList.add(indexs[i]);
                                this.removeRequest(integerList);
                            }
                            break;
                        } else {
                            if (decrementFlat) {
                                RequestModel.getRequestList().get(j).decreaseQuantity();
                            } else {
                                RequestModel.getRequestList().get(j).increaseQuantity();
                            }

                            indexList.remove(new Integer(indexs[i]));

                        }

                    }

                }

            }
            if (listAccepted) {
                this.addRequest(indexList);
            }

            model = this.createRequestModel();

        }
        return model;
    }

    private DefaultListModel createRequestModel() {
        DefaultListModel model = new DefaultListModel();

        ArrayList<Request> request = RequestModel.getRequestList();
        for (int i = 0; i < request.size(); i++) {
            model.addElement(request.get(i).getDescription() + " " + request.get(i).getBrand() + " (" + request.get(i).getQuantity() + ")");

        }
        return model;

    }

    private void addRequest(ArrayList<Integer> indexs) {
        for (int i = 0; i < indexs.size(); i++) {
            Product product = ProductModel.getOneProduct(Integer.toString(indexs.get(i) + 1));
            Request request = new Request(product.getId(), product.getDescription(), 1, product.getBrand());
            RequestModel.createRequest(request);
        }
    }

    private void removeRequest(ArrayList<Integer> indexs) {
        for (int i = 0; i < indexs.size(); i++) {

            RequestModel.deleteRequest(Integer.toString(indexs.get(i) + 1));
        }
    }

    public void linkResquestListToUserCart(Map<Integer, String> index) {
        ArrayList<Integer> key = new ArrayList<>(index.keySet());
        ArrayList<String> value = new ArrayList<>(index.values());
        ShoppingCart cart = new ShoppingCart();
        this.setPricesToRequestList();
        cart.setRequestList(RequestModel.getRequestList());
        if ("Jurídico".equals(value.get(0))) {
            LegalModel.getLegalList().get(key.get(0)).setCart(cart);

        } else {
            NaturalModel.getNaturalList().get(key.get(0)).setCart(cart);

        }

    }

    private ArrayList<Request> getRequestFromIndex(Map<Integer, String> index) {
        ArrayList<Integer> key = new ArrayList<>(index.keySet());
        ArrayList<String> value = new ArrayList<>(index.values());
        ArrayList<Request> request;
        if ("Jurídico".equals(value.get(0))) {
            request = LegalModel.getLegalList().get(key.get(0)).getCart().getRequestList();

        } else {
            request = NaturalModel.getNaturalList().get(key.get(0)).getCart().getRequestList();

        }

        return request;
    }

    public DefaultTableModel createTableModelOfRequestData(Map<Integer, String> index) {

        Object columnas[] = {"ID", "Marca", "Detalle", "Cantidad", "Valor", "Total", "Fecha", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        ArrayList<Request> request = this.getRequestFromIndex(index);
        for (int j = 0; j < request.size(); j++) {
            model.addRow(new Object[]{request.get(j).getId(), request.get(j).getBrand(), request.get(j).getDescription(), request.get(j).getQuantity(), request.get(j).getValue(), request.get(j).getValue() * request.get(j).getQuantity(), request.get(j).getCreationDate(), request.get(j).getStatus()});
        }

        return model;
    }

    public Double getTotalToPay(Map<Integer, String> index) {
        ArrayList<Integer> key = new ArrayList<>(index.keySet());
        ArrayList<String> value = new ArrayList<>(index.values());
        Double total;
        if ("Jurídico".equals(value.get(0))) {
            total = LegalModel.getLegalList().get(key.get(0)).getTotalValueToPay();

        } else {
            total = NaturalModel.getNaturalList().get(key.get(0)).getTotalValueToPay();

        }
        return total;
    }

    public void updateMoney(Map<Integer, String> index, Double money) {
        ArrayList<Integer> key = new ArrayList<>(index.keySet());
        ArrayList<String> value = new ArrayList<>(index.values());

        if ("Jurídico".equals(value.get(0))) {
            LegalModel.getLegalList().get(key.get(0)).setMoney(money);
            LegalModel.getLegalList().get(key.get(0)).toPay(this.getTotalToPay(index), Integer.toString(key.get(0)));

        } else {
            NaturalModel.getNaturalList().get(key.get(0)).setMoney(money);
            NaturalModel.getNaturalList().get(key.get(0)).toPay(this.getTotalToPay(index), Integer.toString(key.get(0)));

        }

    }

    public void updateProductList(Map<Integer, String> index) {
        ArrayList<Request> request = this.getRequestFromIndex(index);
        for (int j = 0; j < request.size(); j++) {
            Integer quantity = ProductModel.getOneProduct(request.get(j).getProductId()).getQuantity() - request.get(j).getQuantity();
            if (quantity == 0) {
                ProductModel.deleteProduct(request.get(j).getProductId());
            } else {
                Product product = ProductModel.getOneProduct(request.get(j).getProductId());
                product.setQuantity(quantity);
                ProductModel.updateProduct(product);
            }

        }

    }

    public String getBill(Map<Integer, String> index) {

        ArrayList<Integer> key = new ArrayList<>(index.keySet());
        ArrayList<String> value = new ArrayList<>(index.values());
        String bill;
        if ("Jurídico".equals(value.get(0))) {
            bill = LegalModel.getLegalList().get(key.get(0)).getCart().getPay().getBill();

        } else {
            bill = NaturalModel.getNaturalList().get(key.get(0)).getCart().getPay().getBill();

        }

        return bill;
    }

    private void setPricesToRequestList() {
        Double price;
        for (int i = 0; i < RequestModel.getRequestList().size(); i++) {
            price = ProductModel.getOneProduct(RequestModel.getRequestList().get(i).getProductId()).getPrice();
            RequestModel.getRequestList().get(i).setValue(price);
        }
    }

    public void deleteCart(Map<Integer, String> index) {
        ArrayList<Integer> key = new ArrayList<>(index.keySet());
        ArrayList<String> value = new ArrayList<>(index.values());
        
        ShoppingCart cart = new ShoppingCart();
        if ("Jurídico".equals(value.get(0))) {
            LegalModel.getLegalList().get(key.get(0)).setCart(cart);

        } else {
            NaturalModel.getNaturalList().get(key.get(0)).setCart(cart);

        }

        this.resetRequestList();

    }
    
    public void resetRequestList(){
        ArrayList<Request> requestList = new ArrayList<>();
        RequestModel.setRequestList(requestList);
    }
}
