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
public class NaturalModel {

     public static ArrayList<Natural> getNaturalList(){
     DB_Connection db_connect = new DB_Connection();
        
        PreparedStatement ps;
        ResultSet rs;
        
        try(Connection conexion = db_connect.get_connection())  {        
            String query="SELECT * FROM naturals inner join clients on naturals.idClient = clients.idClient";
            ps=conexion.prepareStatement(query);
            rs=ps.executeQuery();
            ArrayList<Natural> naturalList = new ArrayList<>();
            while(rs.next()){
                    Natural natural = new Natural(rs.getString("CC"), rs.getString("firstname"), rs.getString("secondname"), rs.getString("first lastname"), rs.getString("second lastname"), rs.getString("address"),
                    rs.getString("email"), rs.getInt("idClient"),
                    rs.getDouble("money"), rs.getString("phoneNumber")
            );
                naturalList.add(natural);
                
            }
            return naturalList;
        }catch(SQLException e){
            System.out.println("no se pudieron recuperar los datos de los clientes");
            System.out.println(e);
        }
        
        return null;
 
 }

    //CRUD
    public static Natural getOneClient(int id) {

        DB_Connection db_connect = new DB_Connection();
        try ( Connection connection = db_connect.get_connection()) {
            PreparedStatement ps;
            ResultSet rs;


            String query = "select * from naturals inner join clients on naturals.idClient = clients.idClient  where idClient = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            ps.close();
            ps = connection.prepareStatement(query);
            ps.setString(1, rs.getString("idClient"));
            rs = ps.executeQuery();
            Natural natural = new Natural(rs.getString("CC"), rs.getString("firstname"), rs.getString("secondname"), rs.getString("first lastname"), rs.getString("second lastname"), rs.getString("address"),
                    rs.getString("email"), rs.getInt("idClient"),
                    rs.getDouble("money"), rs.getString("phoneNumber")
            );

            ps.close();

            return natural;
        } catch (SQLException e) {
            System.out.println("Natural client was not recovered due to a fatal error has occurred");
            System.out.println(e);
            return null;
        }

    }

    public static boolean deleteNatural(String id) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "delete from Naturals where NIT = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, id);
                ps.executeUpdate();

                ps.close();
                System.out.println("The client was deleted successfully");
                return true;
            } catch (SQLException e) {
                System.out.println("Client was not deleted due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    public static void createNatural(Natural natural) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "insert into clients (address, phoneNumber, email, money) values (?, ?, ?, ?)";
                ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, natural.getAddress());
                ps.setString(2, natural.getPhoneNumber());
                ps.setString(3, natural.getEmail());
                ps.setDouble(4, natural.getMoney());
                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int idClient = generatedKeys.getInt(1);
                        query = "insert into naturals (CC, firstname, secondname ,`first lastname` ,`second lastname` ,idClient) values (?, ?, ?, ? , ? , ?)";
                        ps = conexion.prepareStatement(query);
                        ps.setString(1, natural.getCC());
                        ps.setString(2, natural.getFirstName());
                        ps.setString(3, natural.getSecondName());
                        ps.setString(4, natural.getFirstLastName());
                        ps.setString(5, natural.getSecondLastName());
                        ps.setInt(6, idClient);
                        ps.executeUpdate();
                        ps.close();

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

    public static void updateNatural(Natural natural) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "update clients set address = ?, phoneNumber = ?, email = ?, money = ?  where idClient = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, natural.getAddress());
                ps.setString(2, natural.getPhoneNumber());
                ps.setString(3, natural.getEmail());
                ps.setDouble(4, natural.getMoney());
                ps.setInt(5, natural.getId());
                ps.executeUpdate();
                query = "update naturals set firstname = ?, secondName=?, `first lastname`=?, `second lastname`=? where CC = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, natural.getFirstName());
                ps.setString(2, natural.getSecondName());
                ps.setString(3, natural.getFirstLastName());
                ps.setString(4, natural.getSecondLastName());
                ps.setString(5, natural.getCC());
                   
                ps.close();
                System.out.println("¡Natural client was updated successfully!");
            } catch (SQLException e) {
                System.out.println("Natural client not was updated due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
