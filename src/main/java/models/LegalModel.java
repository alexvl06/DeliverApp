/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import classes.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Alexis Avila
 */
public class LegalModel {

    public static ArrayList<Legal> getLegalList() {
        DB_Connection db_connect = new DB_Connection();

        PreparedStatement ps;
        ResultSet rs;

        try ( Connection conexion = db_connect.get_connection()) {
            String query = "SELECT * FROM legals inner join clients on legals.idClient = clients.idClient";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Legal> legalList = new ArrayList<>();
            while (rs.next()) {
                Legal legal = new Legal(rs.getString("business name"), rs.getString("NIT"), rs.getString("address"),
                        rs.getString("email"), rs.getInt("idClient"),
                        rs.getDouble("money"), rs.getString("phoneNumber")
                );
                legalList.add(legal);

            }
            return legalList;
        } catch (SQLException e) {
            System.out.println("no se pudieron recuperar los datos de los clientes");
            System.out.println(e);
        }

        return null;

    }

    //CRUD
    public static Legal getOneClient(int id) {
        Legal legal = new Legal();

        DB_Connection db_connect = new DB_Connection();
        try ( Connection connection = db_connect.get_connection()) {
            PreparedStatement ps;
            ResultSet rs;
            try {
                String query = "SELECT * FROM legals inner join clients on legals.idClient = clients.idClient where clients.idClient = ?";
                ps = connection.prepareStatement(query);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    legal = new Legal(rs.getString("business name"), rs.getString("NIT"), rs.getString("address"),
                            rs.getString("email"), rs.getInt("idClient"),
                            rs.getDouble("money"), rs.getString("phoneNumber")
                    );
                }

                ps.close();

                return legal;
            } catch (SQLException e) {
                System.out.println("Legal client was not recovered due to a fatal error has occurred");
                System.out.println(e);
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

    }

    public static void createLegal(Legal legal) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "insert into clients (address, phoneNumber, email, money) values (?, ?, ?, ?)";
                ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, legal.getAddress());
                ps.setString(2, legal.getPhoneNumber());
                ps.setString(3, legal.getEmail());
                ps.setDouble(4, legal.getMoney());
                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int idClient = generatedKeys.getInt(1);
                        query = "insert into legals (NIT, `business name`, idClient) values (?, ?, ?)";
                        ps = conexion.prepareStatement(query);
                        ps.setString(1, legal.getNIT());
                        ps.setString(2, legal.getBusinessName());
                        ps.setInt(3, idClient);
                        ps.executeUpdate();

                    }
                }
                System.out.println("¡New client was created successfully!");
                ps.close();
            } catch (SQLException e) {
                System.out.println("The client was not created due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void updateLegal(Legal legal) {

        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "update clients set address = ?, phoneNumber = ?, email = ?, money = ?  where idClient = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, legal.getAddress());
                ps.setString(2, legal.getPhoneNumber());
                ps.setString(3, legal.getEmail());
                ps.setDouble(4, legal.getMoney());
                ps.setInt(5, legal.getId());
                ps.executeUpdate();
                query = "update legals set `business name` = ? where NIT = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, legal.getBusinessName());
                ps.setString(2, legal.getNIT());
                ps.executeUpdate();
                ps.close();
                System.out.println("¡Legal client was updated successfully!");
            } catch (SQLException e) {
                System.out.println("Legal client not was updated due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
