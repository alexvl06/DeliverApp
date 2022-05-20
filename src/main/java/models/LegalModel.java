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

    private static final ArrayList<Legal> legalList = new ArrayList<>();

    public static ArrayList<Legal> getLegalList() {
        return legalList;
    }

    public static void defaultLegalList() {
        legalList.add(new Legal("Inter-Telco S.A.S.", "900.382.516-1", "Edificio Prisma, Cra 35A N° 15B-35, Oficina 212, Medellín, Antioquia", "soporte@inter-telco.com", "1", 8500.0, "5200500"));
        legalList.add(new Legal("SUMMA-sci S.A.S.", "901.033.369-2", "CALLE 51 SUR 48 57 MAYORCA ETAPA 3 TORRE NORTE PISO 12, MEDELLIN, ANTIOQUIA", "shappy@summa-sci.com", "2", 12500.0, "6046398"));
    }

    private LegalModel() {
    }

    //CRUD
    public static Legal getOneClient(String id) {

        DB_Connection db_connect = new DB_Connection();
        try ( Connection connection = db_connect.get_connection()) {
            PreparedStatement ps;
            ResultSet rsLegal;
            ResultSet rsClient;
            try {
                String query = "select * from Legals where NIT = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, id);
                rsLegal = ps.executeQuery();
                ps.close();
                query = "select * from Clients where idClient = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, rsLegal.getString("idClient"));
                rsClient = ps.executeQuery();
                Legal legal = new Legal(rsLegal.getString("business name"), rsLegal.getString("NIT"), rsClient.getString("address")
                , rsClient.getString("email"), Integer.toString(rsClient.getInt("idClient"))
                , rsClient.getDouble("money"), rsClient.getString("phoneNumber")
                );

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

    public static boolean deleteLegal(String id) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "delete from Legals where NIT = ?";
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

    public static void createLegal(Legal legal) {
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "insert into Clients (address, phoneNumber, email, money) values (?, ?, ?, ?)";
                ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, legal.getAddress());
                ps.setString(2, legal.getPhoneNumber());
                ps.setString(3, legal.getEmail());
                ps.setDouble(4, legal.getMoney());
                int rowsInserted = ps.executeUpdate();
                if(rowsInserted>0){
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    if(generatedKeys.next()){
                        int idClient = generatedKeys.getInt(1);
                        query = "insert into Legals (NIT, `business name`, idClient) values (?, ?, ?)";
                        ps = conexion.prepareStatement(query);
                        ps.setString(1, legal.getNIT());
                        ps.setString(2, legal.getBusinessName());
                        ps.setInt(3, idClient);
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
        LegalModel.legalList.add(legal);
    }

    public static void updateLegal(Legal legal) {
        
        DB_Connection db_connect = new DB_Connection();
        try ( Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps;
            try {
                String query = "update Clients set address = ?, phoneNumber = ?, email = ?, money = ?  where idClient = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, legal.getAddress());
                ps.setString(2, legal.getPhoneNumber());
                ps.setString(3, legal.getEmail());
                ps.setDouble(4, legal.getMoney());
                ps.setInt(5, Integer.parseInt(legal.getId()));
                ps.executeUpdate();
                query = "update Legals set `business name` = ? where NIT = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, legal.getBusinessName());
                ps.setString(1, legal.getNIT());
                ps.close();
                System.out.println("¡Legal client was updated successfully!");
            } catch (SQLException e) {
                System.out.println("Legal client not was updated due to a fatal error has occurred");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        for (int i = 0; i < LegalModel.legalList.size(); i++) {
            if (LegalModel.legalList.get(i).getId().equals(legal.getId())) {

                LegalModel.legalList.set(i, legal);
            }
        }
    }

}
