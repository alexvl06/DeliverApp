/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import classes.Product;
import classes.Request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alexis Avila
 */
public class RequestModel {

    public static ArrayList<Request> getRequestList() {
        DB_Connection db_connect = new DB_Connection();

        PreparedStatement ps;
        ResultSet rs;

        try ( Connection conexion = db_connect.get_connection()) {
            String query = "SELECT * FROM requests";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Request> requestList = new ArrayList<>();
            while (rs.next()) {
                //Product product, Integer quantity
                Product product = ProductModel.getOneProduct(rs.getInt("code"));
                Request request = new Request(product, rs.getInt("quantity"), rs.getInt("idClient"));
                requestList.add(request);

            }
            return requestList;
        } catch (SQLException e) {
            System.out.println("no se pudieron recuperar los pedidos");
            System.out.println(e);
        }

        return null;

    }
    //CRUD

    public static ArrayList<Request> getRequestListByClientId(int id) {
        DB_Connection db_connect = new DB_Connection();

        PreparedStatement ps;
        ResultSet rs;

        try ( Connection conexion = db_connect.get_connection()) {
            String query = "SELECT * FROM requests where idClient = ?";
            ps = conexion.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            ArrayList<Request> requestList = new ArrayList<>();
            while (rs.next()) {
                //Product product, Integer quantity
                Product product = ProductModel.getOneProduct(rs.getInt("code"));
                Request request = new Request(product, rs.getInt("quantity"), rs.getInt("idClient"));
                requestList.add(request);

            }
            return requestList;
        } catch (SQLException e) {
            System.out.println("no se pudieron recuperar los pedidos");
            System.out.println(e);
        }

        return null;

    }

    public static boolean deleteRequest(int id) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "delete from requests where id = ?";
                ps = conexion.prepareStatement(query);
                ps.setInt(1, id);
                ps.executeUpdate();

                ps.close();
                System.out.println("The request was deleted successfully");
                return true;
            } catch (SQLException e) {
                System.out.println("The request was not deleted due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    public static boolean deleteAllRequests(int id) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "delete from requests where idClient = ?";
                ps = conexion.prepareStatement(query);
                ps.setInt(1, id);
                ps.executeUpdate();

                ps.close();
                System.out.println("The requests were deleted successfully");
                return true;
            } catch (SQLException e) {
                System.out.println("The requests were not deleted due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    public static void createRequest(Request request) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "insert into request (creation_date, quantity, status, code, idClient) values (?, ?, ?, ?, ?)";
                ps = conexion.prepareStatement(query);
                ps.setString(1, request.getCreationDate());
                ps.setInt(2, request.getQuantity());
                ps.setString(3, request.getStatus());
                ps.setInt(4, request.getProduct().getId());
                ps.setInt(5, request.getIdClient());
                ps.executeUpdate();

                System.out.println("¡New request was created successfully!");
                ps.close();
            } catch (SQLException e) {
                System.out.println("The request was not created due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void updateRequest(Request request) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "update requests set creation_date = ?, quantity = ?, status = ?, code = ?, idClient = ?  where idRequest = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, request.getCreationDate());
                ps.setInt(2, request.getQuantity());
                ps.setString(3, request.getStatus());
                ps.setInt(4, request.getProduct().getId());
                ps.setInt(5, request.getIdClient());
                ps.setInt(6, request.getId());
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
