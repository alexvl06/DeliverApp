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
import java.util.ArrayList;

/**
 *
 * @author Alexis Avila
 */
public class NaturalModel {

    private static final ArrayList<Natural> naturalList = new ArrayList<>();

    public static ArrayList<Natural> getNaturalList() {
        return naturalList;
    }

    public static void defaultNaturalList() {
        naturalList.add(new Natural("1083555169", "Alexis", "Rafael del Carmen", "Ávila", "Ortiz", "Ciudadela Sevilla, cll 66a #55a-51, Medellín, Antioquia", "alexisavila1991@gmail.com", "1", 1200.0, "3053478433"));
        naturalList.add(new Natural("1017241138", "Yina", " Juliana", " Micanquer", " Caipe", "Ciudadela Sevilla, cll 66a #55a-51, Medellín, Antioquia", "yinajuliana03@gmail.com", "2", 1000.0, "3173856632"));
    }

    //CRUD
    public static Natural getOneClient(String id) {

        DB_Connection db_connect = new DB_Connection();
        try ( Connection connection = db_connect.get_connection()) {
            PreparedStatement ps;
            ResultSet rsNatural;
            ResultSet rsClient;

            String query = "select * from Natural where CC = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rsNatural = ps.executeQuery();
            ps.close();
            query = "select * from Clients where idClient = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, rsNatural.getString("idClient"));
            rsClient = ps.executeQuery();
            Natural natural = new Natural(rsNatural.getString("CC"), rsNatural.getString("firstname"), rsNatural.getString("secondname"), rsNatural.getString("first lastname"), rsNatural.getString("second lastname"), rsClient.getString("address"),
                     rsClient.getString("email"), Integer.toString(rsClient.getInt("idClient")),
                     rsClient.getDouble("money"), rsClient.getString("phoneNumber")
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

    public static void createNatural(Natural Natural) {
        NaturalModel.naturalList.add(Natural);
    }

    public static void updateNatural(Natural natural) {
        for (int i = 0; i < NaturalModel.naturalList.size(); i++) {
            if (NaturalModel.naturalList.get(i).getId().equals(natural.getId())) {

                NaturalModel.naturalList.set(i, natural);
            }
        }
    }
}
