/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import classes.Request;
import java.util.ArrayList;

/**
 *
 * @author Alexis Avila
 */
public class RequestModel {

    private static ArrayList<Request> requestList = new ArrayList<>();

    public static ArrayList<Request> getRequestList() {
        return requestList;
    }

    public static void setRequestList(ArrayList<Request> requestList) {
        RequestModel.requestList = requestList;
    }

    //CRUD
    public static Request getOneRequest(String id) {
        try {
            for (int i = 0; i < RequestModel.requestList.size(); i++) {
                if (RequestModel.requestList.get(i).getId().equals(id)) {

                    return RequestModel.requestList.get(i);
                }
            }
        } catch (Exception e) {
            System.out.println("Client not found: " + e);
        }
        return null;

    }

    public static boolean deleteRequest(String id) {
        for (int i = 0; i < RequestModel.requestList.size(); i++) {
            if (RequestModel.requestList.get(i).getId().equals(id)) {

                RequestModel.requestList.remove(i);
                for (int j = i; j < RequestModel.requestList.size(); j++) {
                    RequestModel.requestList.get(j).setId(Integer.toString(Integer.parseInt(RequestModel.requestList.get(j).getId()) - 1));
                }
                return true;
            }
        }
        return false;
    }

    public static void createRequest(Request request) {
        request.setId(Integer.toString(RequestModel.getRequestList().size()+1));
        RequestModel.requestList.add(request);
    }

    public static void updateRequest(Request request) {
        for (int i = 0; i < RequestModel.requestList.size(); i++) {
            if (RequestModel.requestList.get(i).getId().equals(request.getId())) {
            } else {
                RequestModel.requestList.set(i, request);
            }
        }
    }

}
