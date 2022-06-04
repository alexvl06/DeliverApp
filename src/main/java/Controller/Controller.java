/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import classes.*;
import java.awt.PopupMenu;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import models.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Alexis Avila
 */
public class Controller {

    public ArrayList<Legal> legalList = new ArrayList<>();
    public ArrayList<Natural> naturalList = new ArrayList<>();
    public ArrayList<Product> productList = new ArrayList<>();
    public ArrayList<Request> requestList = new ArrayList<>();
    public boolean listAccepted;

    public DefaultListModel createClientJlistModel(String type) {
        this.naturalList = NaturalModel.getNaturalList();
        DefaultListModel model = new DefaultListModel();
        String secondName, secondLastName;
        if ("Natural".equals(type)) {

            this.naturalList = NaturalModel.getNaturalList();
            for (int i = 0; i < this.naturalList.size(); i++) {
                if (this.naturalList.get(i).getSecondName() != null) {

                    secondName = this.naturalList.get(i).getSecondName();
                } else {
                    secondName = "";
                }

                if (this.naturalList.get(i).getSecondLastName() != null) {

                    secondLastName = this.naturalList.get(i).getSecondLastName();
                } else {
                    secondLastName = "";
                }
                model.addElement(this.naturalList.get(i).getId() + ". " + this.naturalList.get(i).getFirstName() + " " + secondName + " " + this.naturalList.get(i).getFirstLastName() + " " + secondLastName);

            }

        } else {
            this.legalList = LegalModel.getLegalList();
            for (int i = 0; i < this.legalList.size(); i++) {
                model.addElement(this.legalList.get(i).getId() + ". " + this.legalList.get(i).getBusinessName());

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

        this.productList = ProductModel.getProductList();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < this.productList.size(); i++) {
            model.addElement(this.productList.get(i).getId() + ". " + this.productList.get(i).getDescription() + " " + this.productList.get(i).getBrand() + " a " + this.productList.get(i).getPrice() + "$ pesos (" + this.productList.get(i).getQuantity() + ")");

        }
        return model;
    }

    public void updateProduct(int code, String br, String sup, String value, String quant, String desc) {

        Product product = new Product(desc, sup, Integer.parseInt(quant), Double.parseDouble(value), br);
        product.setId(code);
        ProductModel.updateProduct(product);

    }

    public boolean deletedProduct(int index) {
        return ProductModel.deleteProduct(index);
    }

    public Product getProductData(int i) {
        Product product = ProductModel.getOneProduct(i);
        return product;
    }

    public DefaultListModel createRequestsList(ArrayList<Integer> productIds, int idClient, boolean decrementFlat) {

        this.requestList = RequestModel.getRequestListByClientId(idClient);

        if (RequestModel.getRequestListByClientId(idClient).isEmpty()) {

            if (!decrementFlat) {
                this.addRequest(productIds, idClient);
            }

        } else {

            this.listAccepted = true;

            for (int i = 0; i < productIds.size(); i++) {
                Product product = ProductModel.getOneProduct(productIds.get(i));
                for (int j = 0; j < this.requestList.size(); j++) {
                    if ((this.requestList.get(j).getProduct().getId() == productIds.get(i))) {
                        if ("Pagado".equals(this.requestList.get(j).getStatus())) {
                            this.listAccepted = true;
                        } else {

                            this.listAccepted = false;
                            if ((!decrementFlat && this.requestList.get(j).getQuantity().equals(product.getQuantity())) || (decrementFlat && this.requestList.get(j).getQuantity().equals(1))) {

                                if (decrementFlat) {

                                    this.removeRequest(this.requestList.get(j).getId());

                                }

                            } else {
                                if (decrementFlat) {
                                    this.requestList.get(j).decreaseQuantity();

                                } else {
                                    this.requestList.get(j).increaseQuantity();

                                }
                                RequestModel.updateRequest(this.requestList.get(j));
                                productIds.set(0, productIds.get(i));

                            }
                        }

                    }

                }

            }
            if (this.listAccepted) {
                this.addRequest(productIds, idClient);
            }
        }
        return this.createRequestModel(idClient);

    }

    public DefaultListModel createRequestModel(int id) {

        DefaultListModel model = new DefaultListModel();

        this.requestList = RequestModel.getRequestListByClientId(id);
        for (int i = 0; i < this.requestList.size(); i++) {
            if ("Pendiente".equals(requestList.get(i).getStatus())) {
                Product product = ProductModel.getOneProduct(this.requestList.get(i).getProduct().getId());

                model.addElement(this.requestList.get(i).getId() + ". " + product.getDescription() + " " + product.getBrand() + " (" + this.requestList.get(i).getQuantity() + ")");
            }

        }
        return model;

    }

    private void addRequest(ArrayList<Integer> productIndexs, int idClient) {
        for (int i = 0; i < productIndexs.size(); i++) {
            Product product = ProductModel.getOneProduct(productIndexs.get(i));

            Request request = new Request(product, 1, idClient);
            RequestModel.createRequest(request);
        }
    }

    private void removeRequest(int id) {

        RequestModel.deleteRequest(id);

    }

    public DefaultTableModel createTableModelOfRequestData(int index) {

        Object columnas[] = {"ID", "Marca", "Detalle", "Cantidad", "Valor", "Total", "Fecha", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        this.requestList = RequestModel.getRequestListByClientId(index);
        for (int j = 0; j < this.requestList.size(); j++) {
            if("Pendiente".equals(this.requestList.get(j).getStatus())){
                Product product = ProductModel.getOneProduct(this.requestList.get(j).getProduct().getId());
                model.addRow(new Object[]{this.requestList.get(j).getId(), product.getBrand(), product.getDescription(), this.requestList.get(j).getQuantity(), product.getPrice(), product.getPrice() * this.requestList.get(j).getQuantity(), this.requestList.get(j).getCreationDate(), this.requestList.get(j).getStatus()});
            }
            
        }

        return model;
    }

    public Double getTotalToPay(int index) {
        this.requestList = RequestModel.getRequestListByClientId(index);
        this.naturalList = NaturalModel.getNaturalList();
        this.legalList = LegalModel.getLegalList();

        String flat = this.getIndexClientFromClientLists(index);
        if (flat.charAt(0) == 'N') {
            flat = flat.replace("N", "");
            int i = Integer.parseInt(flat);
            this.naturalList.get(i).setRequestList(this.requestList);
            return this.naturalList.get(i).getTotalValueToPay();
        } else {
            flat = flat.replace("L", "");
            int i = Integer.parseInt(flat);
            this.legalList.get(i).setRequestList(this.requestList);
            return this.legalList.get(i).getTotalValueToPay();

        }

    }

    public void updateMoney(int index, Double money) {

        ClientModel.updateMoney(money, index);

    }

    public void updateProductList(int index) {
        this.requestList = RequestModel.getRequestListByClientId(index);
        for (int j = 0; j < this.requestList.size(); j++) {
            Integer quantity = ProductModel.getOneProduct(this.requestList.get(j).getProduct().getId()).getQuantity() - this.requestList.get(j).getQuantity();
            if (quantity == 0) {
                ProductModel.deleteProduct(this.requestList.get(j).getProduct().getId());
            } else {
                Product product = ProductModel.getOneProduct(this.requestList.get(j).getProduct().getId());
                product.setQuantity(quantity);
                ProductModel.updateProduct(product);
            }

        }

    }

    public String getBill(int index) {
        this.requestList = RequestModel.getRequestListByClientId(index);
        this.naturalList = NaturalModel.getNaturalList();
        this.legalList = LegalModel.getLegalList();

        String flat = this.getIndexClientFromClientLists(index);
        if (flat.charAt(0) == 'N') {
            flat = flat.replace("N", "");
            int i = Integer.parseInt(flat);
            this.naturalList.get(i).setRequestList(this.requestList);
            Payment pay = this.naturalList.get(i).toPay(this.naturalList.get(i).getTotalValueToPay(), index);
            
            NaturalModel.updateNatural(this.naturalList.get(i));
            return pay.getBill();

        } else {
            flat = flat.replace("L", "");
            int i = Integer.parseInt(flat);
            this.legalList.get(i).setRequestList(this.requestList);
            Payment pay = this.legalList.get(i).toPay(this.legalList.get(i).getTotalValueToPay(), index);
            this.changeStatusToPayed();
            LegalModel.updateLegal(this.legalList.get(i));
            return pay.getBill();

        }

    }

    private void changeStatusToPayed() {
        for (int i = 0; i < requestList.size(); i++) {
            this.requestList.get(i).setStatus("Pagado");
            RequestModel.updateRequest(requestList.get(i));
        }

    }

    public void resetRequestList(int id) {
        RequestModel.deleteAllRequests(id);
    }

    private String getIndexClientFromClientLists(int index) {
        this.naturalList = NaturalModel.getNaturalList();
        this.legalList = LegalModel.getLegalList();

        for (int i = 0; i < this.naturalList.size(); i++) {
            if (this.naturalList.get(i).getId() == index) {
                return "N" + i;
            }
        }
        for (int i = 0; i < this.legalList.size(); i++) {
            if (this.legalList.get(i).getId() == index) {
                return "L" + i;
            }
        }
        return null;
    }

    public ChartPanel createChartReport() {
        ArrayList<MostWanted> mostWantedList = MostWantedReport.getMostWanted();
        
        DefaultPieDataset data = new DefaultPieDataset();
        mostWantedList.forEach(element->{
            data.setValue(element.getDescription(), element.getTotalQuantity());
            System.out.println("Most wanted element from the array list: "+element.getDescription()+" And its quantity: "+element.getTotalQuantity());
           
        });
        
        System.out.println("Total items in dataset: "+data.getItemCount());
        
        JFreeChart chart  = ChartFactory.createPieChart(
                "Top 3",
                data,
                true,
                true,
                false
        );
        
        return new ChartPanel(chart);
    }
    
    public void MostWantedFileCreation(){
        ArrayList<MostWanted> mostWanted = MostWantedReport.getMostWanted();
        MostWantedReport.exportReportToExcelFile(mostWanted);
    }
    

}
