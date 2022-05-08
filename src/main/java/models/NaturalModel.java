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
public class NaturalModel {

    private static ArrayList<Natural> naturalList = new ArrayList<Natural>();



    public static ArrayList<Natural> getNaturalList() {
        return naturalList;
    }

    public static void setNaturalList(ArrayList<Natural> naturalList) {
        NaturalModel.naturalList = naturalList;
    }

    //CRUD
    public static Natural getOneClient(String id) {
        try {
            for (int i = 0; i < NaturalModel.naturalList.size(); i++) {
                if (NaturalModel.naturalList.get(i).getId().equals(id)) {

                    return NaturalModel.naturalList.get(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Client not found: " + e);
        }
        return null;

    }

    public static boolean deleteNatural(String id) {
        for (int i = 0; i < NaturalModel.naturalList.size(); i++) {
            if (NaturalModel.naturalList.get(i).getId().equals(id)) {

                NaturalModel.naturalList.remove(i);
                for (int j = i; j < NaturalModel.naturalList.size(); j++) {
                    NaturalModel.naturalList.get(j).setId(Integer.toString(Integer.parseInt(NaturalModel.naturalList.get(j).getId()) - 1));
                }
                return true;
            }
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
