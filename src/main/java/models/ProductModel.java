/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import classes.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alexis Avila
 */
public class ProductModel {

    public static ArrayList<Product> getProductList() {
        DB_Connection db_connect = new DB_Connection();

        PreparedStatement ps;
        ResultSet rs;

        try ( Connection conexion = db_connect.get_connection()) {
            String query = "SELECT * FROM products";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Product> productList = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getString("description"), rs.getString("supplier"), rs.getInt("quantity"), rs.getDouble("price"), rs.getString("brand"));
                product.setId(rs.getInt("code"));
                productList.add(product);

            }
            return productList;
        } catch (SQLException e) {
            System.out.println("no se pudieron recuperar los productos");
            System.out.println(e);
        }

        return null;

    }

    //CRUD
    public static Product getOneProduct(int id) {
        Product product = null;
        DB_Connection db_connect = new DB_Connection();
        try ( Connection connection = db_connect.get_connection()) {
            PreparedStatement ps;
            ResultSet rs;

            String query = "select * from products where code = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
               product = new Product(rs.getString("description"), rs.getString("supplier"), rs.getInt("quantity"), rs.getDouble("price"), rs.getString("brand"));

            }
            ps.close();

            return product;
        } catch (SQLException e) {
            System.out.println("Product was not recovered due to a fatal error has occurred");
            System.out.println(e);
            return null;
        }

    }

    public static boolean deleteProduct(int id) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "delete from products where code = ?";
                ps = conexion.prepareStatement(query);
                ps.setInt(1, id);
                ps.executeUpdate();

                ps.close();
                System.out.println("The product was deleted successfully");
                return true;
            } catch (SQLException e) {
                System.out.println("The product was not deleted due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    public static void createProduct(Product product) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "insert into products (description, brand, supplier, price, quantity) values (?, ?, ?, ?, ?)";
                ps = conexion.prepareStatement(query);
                ps.setString(1, product.getDescription());
                ps.setString(2, product.getBrand());
                ps.setString(3, product.getSupplier());
                ps.setDouble(4, product.getPrice());
                ps.setInt(5, product.getQuantity());
               ps.executeUpdate();

                System.out.println("¡New product was created successfully!");
                ps.close();
            } catch (SQLException e) {
                System.out.println("The product was not created due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void updateProduct(Product product) {
       DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "update products set description = ?, brand = ?, supplier = ?, price = ?, quantity = ?  where code = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, product.getDescription());
                ps.setString(2, product.getBrand());
                ps.setString(3, product.getSupplier());
                ps.setDouble(4, product.getPrice());
                ps.setInt(5, product.getQuantity());
                ps.setInt(6, product.getId());
                ps.executeUpdate();   
                ps.close();
                System.out.println("¡The product was updated successfully!");
            } catch (SQLException e) {
                System.out.println("The product not was updated due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
