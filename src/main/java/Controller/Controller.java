/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import classes.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import models.*;

/**
 *
 * @author Alexis Avila
 */
public class Controller {

    public static ArrayList<Legal> legalList = new ArrayList<>();
    public static ArrayList<Natural> naturalList = new ArrayList<>();
    public static ArrayList<Product> productList = new ArrayList<>();
    public static ArrayList<Request> requestList = new ArrayList<>();

    public static void startTemporalDB() {
        Controller.legalList = LegalModel.getLegalList();
        Controller.naturalList = NaturalModel.getNaturalList();
        Controller.productList = ProductModel.getProductList();
        Controller.requestList = RequestModel.getRequestList();
    }

    public static DefaultListModel createClientJlistModel(String type) {
        DefaultListModel model = new DefaultListModel();
        if ("Natural".equals(type)) {

            Controller.naturalList= NaturalModel.getNaturalList();
            for (int i = 0; i < Controller.naturalList.size(); i++) {
                model.addElement(Controller.naturalList.get(i).getId() + ". " + Controller.naturalList.get(i).getFirstName() + " " + Controller.naturalList.get(i).getSecondName() + " " + Controller.naturalList.get(i).getFirstLastName() + " " + Controller.naturalList.get(i).getSecondLastName());

            }

        } else {
            Controller.legalList = LegalModel.getLegalList();
            for (int i = 0; i < Controller.legalList.size(); i++) {
                model.addElement(Controller.legalList.get(i).getId() + ". " + Controller.legalList.get(i).getBusinessName());

            }

        }

        return model;

    }

    public void addClient(String businessName, String NIT, String adr, String mail, double parseDouble, String phoneNumber) {

        Legal legal = new Legal(businessName, NIT, adr, mail, 0, parseDouble, phoneNumber);
        LegalModel.createLegal(legal);
    }

    public void addClient(String CC, String firstName, String secondName, String firstLastName, String secondLastName, String adr, String mail, double parseDouble, String phoneNumber) {

        Natural natural = new Natural(CC, firstName, secondName, firstLastName, secondLastName, adr, mail, 0, parseDouble, phoneNumber);
        NaturalModel.createNatural(natural);

    }

    public Legal getLegalData(int itemSelected) {

        Legal legal = LegalModel.getOneClient(itemSelected);
        return legal;

    }

    public Natural getNaturalData(int itemSelected) {
        Natural natural = NaturalModel.getOneClient(itemSelected);
        return natural;
    }

    public boolean deletedClient(int id) {
        return ClientModel.deleteClient(id);
    }

    public void updateClient(String businessName, String NIT, String adr, String mail, int itemSelected, double parseDouble, String phoneNumber) {
        
        Legal legal = new Legal(businessName, NIT, adr, mail, itemSelected, parseDouble, phoneNumber);
        LegalModel.updateLegal(legal);
    }

    public void updateClient(String CC, String firstName, String secondName, String firstLastName, String secondLastName, String adr, int itemSelected, String mail, double parseDouble, String phoneNumber) {

        Natural natural = new Natural(CC, firstName, secondName, firstLastName, secondLastName, adr, mail, itemSelected, parseDouble, phoneNumber);
        NaturalModel.updateNatural(natural);
    }

    public void addProduct(String br, String sup, String value, String quant, String desc) {

        Product product = new Product(desc, sup, Integer.parseInt(quant), Double.parseDouble(value), br);

        ProductModel.createProduct(product);
    }

    public ListModel<String> createProductJlistModel() {

        Controller.productList = ProductModel.getProductList();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < Controller.productList.size(); i++) {
            model.addElement(Controller.productList.get(i).getId() + ". " + Controller.productList.get(i).getDescription() + " " + Controller.productList.get(i).getBrand() + " a " + Controller.productList.get(i).getPrice() + "$ pesos (" + Controller.productList.get(i).getQuantity() + ")");

        }
        return model;
    }

    public void updateProduct(int code, String br, String sup, String value, String quant, String desc) {

        Product product = new Product(desc, sup, Integer.parseInt(quant), Double.parseDouble(value), br);

        ProductModel.updateProduct(product);

    }

    public boolean deletedProduct(int index) {
        return ProductModel.deleteProduct(index);
    }

    public Product getProductData(int i) {
        Product product = ProductModel.getOneProduct(i);
        return product;
    }

    public DefaultListModel createRequestsList(int[] indexs, int idClient, boolean decrementFlat) {
        Controller.requestList = RequestModel.getRequestListByClientId(idClient);
        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < indexs.length; i++) {
            indexList.add(indexs[i]);
        }

        DefaultListModel model;

        if (RequestModel.getRequestList().isEmpty()) {

            if (!decrementFlat) {
                this.addRequest(indexList, idClient);
            }
            model = this.createRequestModel(idClient);

        } else {

            boolean listAccepted = true;

            for (int i = 0; i < indexs.length; i++) {

                for (int j = 0; j < Controller.requestList.size(); j++) {
                    if (Controller.requestList.get(j).getProduct().getId() == indexs[i]) {
                        if ((!decrementFlat && Controller.requestList.get(j).getQuantity().equals(ProductModel.getOneProduct(Controller.requestList.get(j).getProduct().getId()).getQuantity())) || (decrementFlat && Controller.requestList.get(j).getQuantity().equals(1))) {
                            listAccepted = false;
                            if (decrementFlat) {
                                ArrayList<Integer> integerList = new ArrayList<>();
                                integerList.add(indexs[i]);
                                this.removeRequest(integerList);

                            }
                            break;
                        } else {
                            if (decrementFlat) {
                                Controller.requestList.get(j).decreaseQuantity();

                            } else {
                                Controller.requestList.get(j).increaseQuantity();

                            }
                            RequestModel.updateRequest(Controller.requestList.get(j));

                            indexList.remove(new Integer(indexs[i]));

                        }

                    }

                }

            }
            if (listAccepted) {
                this.addRequest(indexList, idClient);
            }

            model = this.createRequestModel(idClient);

        }
        return model;
    }

    private DefaultListModel createRequestModel(int id) {
        DefaultListModel model = new DefaultListModel();

        Controller.requestList = RequestModel.getRequestListByClientId(id);
        for (int i = 0; i < Controller.requestList.size(); i++) {
            Product product = ProductModel.getOneProduct(Controller.requestList.get(i).getProduct().getId());
            model.addElement(Controller.requestList.get(i).getId() + ". " + product.getDescription() + " " + product.getBrand() + " (" + Controller.requestList.get(i).getQuantity() + ")");

        }
        return model;

    }

    private void addRequest(ArrayList<Integer> indexs, int idClient) {
        for (int i = 0; i < indexs.size(); i++) {
            Product product = ProductModel.getOneProduct(indexs.get(i));
            Request request = new Request(product, 1, idClient);
            RequestModel.createRequest(request);
        }
    }

    private void removeRequest(ArrayList<Integer> indexs) {
        for (int i = 0; i < indexs.size(); i++) {

            RequestModel.deleteRequest(indexs.get(i));
        }
    }

    public DefaultTableModel createTableModelOfRequestData(int index) {

        Object columnas[] = {"ID", "Marca", "Detalle", "Cantidad", "Valor", "Total", "Fecha", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        Controller.requestList = RequestModel.getRequestListByClientId(index);
        for (int j = 0; j < Controller.requestList .size(); j++) {
            Product product = ProductModel.getOneProduct(Controller.requestList .get(j).getProduct().getId());
            model.addRow(new Object[]{Controller.requestList .get(j).getId(), product.getBrand(), product.getDescription(), Controller.requestList .get(j).getQuantity(), product.getPrice(), product.getPrice() * Controller.requestList .get(j).getQuantity(), Controller.requestList .get(j).getCreationDate(), Controller.requestList .get(j).getStatus()});
        }

        return model;
    }

    public Double getTotalToPay(int index) {
        Controller.naturalList = NaturalModel.getNaturalList();
        Controller.legalList = LegalModel.getLegalList();

        String flat = this.getIndexClientFromClientLists(index);
        if (flat.charAt(0) == 'N') {
            flat = flat.replace("N", "");
            int i = Integer.parseInt(flat);
            return Controller.naturalList.get(i).getTotalValueToPay();
        } else {
            flat = flat.replace("L", "");
            int i = Integer.parseInt(flat);
            return Controller.legalList.get(i).getTotalValueToPay();

        }

       
    }

    public void updateMoney(int index, Double money) {

        ClientModel.updateMoney(money, index);

    }

    public void updateProductList(int index) {
        Controller.requestList  = RequestModel.getRequestListByClientId(index);
        for (int j = 0; j < Controller.requestList .size(); j++) {
            Integer quantity = ProductModel.getOneProduct(Controller.requestList .get(j).getProduct().getId()).getQuantity() - Controller.requestList .get(j).getQuantity();
            if (quantity == 0) {
                ProductModel.deleteProduct(Controller.requestList .get(j).getProduct().getId());
            } else {
                Product product = ProductModel.getOneProduct(Controller.requestList .get(j).getProduct().getId());
                product.setQuantity(quantity);
                ProductModel.updateProduct(product);
            }

        }

    }

    public String getBill(int index) {
        Controller.naturalList = NaturalModel.getNaturalList();
        Controller.legalList = LegalModel.getLegalList();

        String flat = this.getIndexClientFromClientLists(index);
        if (flat.charAt(0) == 'N') {
            flat = flat.replace("N", "");
            int i = Integer.parseInt(flat);
            Payment pay = new Payment(index, Controller.naturalList.get(i).getTotalValueToPay());
            Controller.naturalList.get(i).toPay(Controller.naturalList.get(i).getTotalValueToPay(), index);
            NaturalModel.updateNatural(Controller.naturalList.get(i));
            return pay.getBill();

        } else {
            flat = flat.replace("L", "");
            int i = Integer.parseInt(flat);
            Payment pay = new Payment(index, Controller.legalList.get(i).getTotalValueToPay());
            Controller.legalList.get(i).toPay(Controller.legalList.get(i).getTotalValueToPay(), index);
            LegalModel.updateLegal(Controller.legalList.get(i));
            return pay.getBill();

        }

    }


    public void resetRequestList(int id) {
        RequestModel.deleteAllRequests(id);
    }

    private String getIndexClientFromClientLists(int index) {
        Controller.naturalList = NaturalModel.getNaturalList();
        Controller.legalList = LegalModel.getLegalList();


        for (int i = 0; i < Controller.naturalList.size(); i++) {
            if (Controller.naturalList.get(i).getId() == index) {
                return "N" + i;
            }
        }
        for (int i = 0; i < Controller.legalList.size(); i++) {
            if (Controller.legalList.get(i).getId() == index) {
                return "L" + i;
            }
        }
        return null;
    }

}
