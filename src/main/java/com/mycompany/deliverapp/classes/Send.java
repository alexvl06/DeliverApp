/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deliverapp.classes;

import java.util.Date;

/**
 *
 * @author inter-telco
 */
public class Send {
    private String id;
    private String destiny;
    private String status;
    private String company;
    private Date sendingDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public Send(String id, String destiny, String status, String company) {
        this.id = id;
        this.destiny = destiny;
        this.status = status;
        this.company = company;
        this.sendingDate = new Date();
    }
    
    
}
