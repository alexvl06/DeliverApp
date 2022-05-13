/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import classes.Product;
import java.util.ArrayList;

/**
 *
 * @author Alexis Avila
 */
public class ProductModel {

    private static final ArrayList<Product> productList = new ArrayList<>();

    public static ArrayList<Product> getProductList() {
        return productList;
    }

  
   public static void defaultProductList(){
      productList.add(new Product("1", "pan tajado", "Pan pa' ya", 20, 4200.0, "Bimbo"));
      productList.add(new Product("2", "cuchilla para afeitar", "Almacenes Éxito", 68,4900.0, "Gillete"));
      productList.add(new Product("3", "Torta de las tres leches", "Punto caliente", 5, 18200.0, "Deli"));
      productList.add(new Product("4", "pañuelos", "P&G", 45, 3200.0, "Familia"));
      productList.add(new Product("5", "pasta ramen", "Oriental Food", 34, 1800.0, "Ajino-men"));
   }

    //CRUD
    public static Product getOneProduct(String id) {
        try {
            for (int i = 0; i < ProductModel.productList.size(); i++) {
                if (ProductModel.productList.get(i).getId().equals(id)) {

                    return ProductModel.productList.get(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Client not found: " + e);
        }
        return null;

    }

    public static boolean deleteProduct(String id) {
        for (int i = 0; i < ProductModel.productList.size(); i++) {
            if (ProductModel.productList.get(i).getId().equals(id)) {

                ProductModel.productList.remove(i);
                for(int j = i; j<ProductModel.productList.size(); j++ ){
                    ProductModel.productList.get(j).setId(Integer.toString(Integer.parseInt(ProductModel.productList.get(j).getId())-1));
                }
                return true;
            }
        }
        return false;
    }

    public static void createProduct(Product Product) {
        ProductModel.productList.add(Product);
    }

    public static void updateProduct(Product product) {
        for (int i = 0; i < ProductModel.productList.size(); i++) {
            if (ProductModel.productList.get(i).getId().equals(product.getId())) {

                ProductModel.productList.set(i, product);
            }
        }
    }
}
