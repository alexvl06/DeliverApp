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

    private static final ArrayList<Natural> naturalList = new ArrayList<>();



    public static ArrayList<Natural> getNaturalList() {
        return naturalList;
    }

    public static void defaultNaturalList() {
        naturalList.add(new Natural("Alexis", "Rafael del Carmen", "Ávila", "Ortiz", "Ciudadela Sevilla, cll 66a #55a-51, Medellín, Antioquia", "alexisavila1991@gmail.com", "1", 1200.0, "3053478433"));
        naturalList.add(new Natural("Yina", " Juliana", " Micanquer", " Caipe", "Ciudadela Sevilla, cll 66a #55a-51, Medellín, Antioquia", "yinajuliana03@gmail.com", "2", 1000.0, "3173856632"));
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
