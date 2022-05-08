/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import classes.Send;
import java.util.ArrayList;

/**
 *
 * @author Alexis Avila
 */
public class SendModel {

    private static ArrayList<Send> sendList;

    public ArrayList<Send> getSendList() {
        return sendList;
    }

    public void setSendlList(ArrayList<Send> sendList) {
        SendModel.sendList = sendList;
    }

    //CRUD
    public Send getOneClient(String id) {
        try {
            for (int i = 0; i < SendModel.sendList.size(); i++) {
                if (SendModel.sendList.get(i).getId().equals(id)) {

                    return SendModel.sendList.get(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Client not found: " + e);
        }
        return null;

    }

    public static boolean deleteSend(String id) {
        for (int i = 0; i < SendModel.sendList.size(); i++) {
            if (SendModel.sendList.get(i).getId().equals(id)) {

                SendModel.sendList.remove(i);
                for (int j = i; j < SendModel.sendList.size(); j++) {
                    SendModel.sendList.get(j).setId(Integer.toString(Integer.parseInt(SendModel.sendList.get(j).getId()) - 1));
                }
                return true;
            }
        }
        return false;
    }

    public static void createSend(Send send) {
        SendModel.sendList.add(send);
    }

    public void updateSend(Send send) {
        for (int i = 0; i < SendModel.sendList.size(); i++) {
            if (SendModel.sendList.get(i).getId().equals(send.getId())) {
            } else {
                SendModel.sendList.set(i, send);
            }
        }
    }

}
