/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Alexis Avila
 */
public class ClientModel {
    
        public static void updateMoney(Double money, int idClient){
              DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "update Clients set money = ?  where idClient = ?";
                ps = conexion.prepareStatement(query);
                ps.setDouble(4, money);
                ps.setInt(5, idClient);
                ps.executeUpdate();
                System.out.println("¡Client money was updated successfully!");
            } catch (SQLException e) {
                System.out.println("Client money not was updated due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } 
    }

      public static boolean deleteClient(int id) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "delete from clients where idClient = ?";
                ps = conexion.prepareStatement(query);
                ps.setInt(1, id);
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
      
     
}
