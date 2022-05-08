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

    private static ArrayList<Legal> legalList = new ArrayList<Legal>();

    public static ArrayList<Legal> getLegalList() {
        return legalList;
    }

    public static void setLegalList(ArrayList<Legal> legalList) {
        LegalModel.legalList = legalList;
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
