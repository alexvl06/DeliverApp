/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import classes.*;
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
    
    public static void defaultLegalList(){
        legalList.add(new Legal("Inter-Telco S.A.S.", "900.382.516-1", "Edificio Prisma, Cra 35A N° 15B-35, Oficina 212, Medellín, Antioquia", "soporte@inter-telco.com", "1", 8500.0, "5200500"));
        legalList.add(new Legal("SUMMA-sci S.A.S.", "901.033.369-2", "CALLE 51 SUR 48 57 MAYORCA ETAPA 3 TORRE NORTE PISO 12, MEDELLIN, ANTIOQUIA", "shappy@summa-sci.com", "2", 12500.0, "6046398"));
    }

    private LegalModel() {
    }


    //CRUD
    public static Legal getOneClient(String id) {
        try {
            for (int i = 0; i < LegalModel.legalList.size(); i++) {
                if (LegalModel.legalList.get(i).getId().equals(id)) {

                    return LegalModel.legalList.get(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Client not found: " + e);
        }
        return null;

    }

    public static boolean deleteLegal(String id) {
        for (int i = 0; i < LegalModel.legalList.size(); i++) {
            if (LegalModel.legalList.get(i).getId().equals(id)) {

                LegalModel.legalList.remove(i);
                for (int j = i; j < LegalModel.legalList.size(); j++) {
                    LegalModel.legalList.get(j).setId(Integer.toString(Integer.parseInt(LegalModel.legalList.get(j).getId()) - 1));
                }
                return true;
            }
        }
        return false;
    }

    public static void createLegal(Legal legal) {
        LegalModel.legalList.add(legal);
    }

    public static void updateLegal(Legal legal) {
        for (int i = 0; i < LegalModel.legalList.size(); i++) {
            if (LegalModel.legalList.get(i).getId().equals(legal.getId())) {

                LegalModel.legalList.set(i, legal);
            }
        }
    }

}
