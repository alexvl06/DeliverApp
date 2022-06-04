/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import classes.MostWanted;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Alexis Avila
 */
public class MostWantedReport {

    public static ArrayList<MostWanted> getMostWanted() {
        DB_Connection db_connect = new DB_Connection();

        PreparedStatement ps;
        ResultSet rs;

        try ( Connection conexion = db_connect.get_connection()) {
            String query = "select description, sum(requests.quantity) as `Total Quantity` from requests inner join products on requests.code = products.code where status = \"Pagado\" group by description limit 3";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<MostWanted> mostWantedList = new ArrayList<>();
            while (rs.next()) {
                //Product product, Integer quantity
                mostWantedList.add(new MostWanted(rs.getString("description"), rs.getInt("Total Quantity")));

            }
            rs.close();
            return mostWantedList;
        } catch (SQLException e) {
            System.out.println("No se pudo realizar el reporte de los mas pedidos.");
            System.out.println(e);
        }

        return null;

    }

    public static void exportReportToExcelFile(ArrayList<MostWanted> mostWanted) {
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell((short) 0);
        HSSFRichTextString text = new HSSFRichTextString("Descripción del producto");
        cell.setCellValue(text);
        cell = row.createCell((short) 1);
        text = new HSSFRichTextString("Cantidad vendida hasta el momento");
        cell.setCellValue(text);
        for (int i = 0; i < mostWanted.size(); i++) {
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            text = new HSSFRichTextString(mostWanted.get(i).getDescription());
            cell.setCellValue(text);
            cell = row.createCell(1);
            text = new HSSFRichTextString(Integer.toString(mostWanted.get(i).getTotalQuantity()));
            cell.setCellValue(text);
        }

        try {
            try ( FileOutputStream file = new FileOutputStream("MostWantedReport.xls")) {
                book.write(file);
            }
        } catch (IOException e) {
            System.out.println("Error exception: " + e);
        }
    }
}
