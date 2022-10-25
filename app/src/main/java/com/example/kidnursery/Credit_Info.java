package com.example.kidnursery;

public class Credit_Info {

    String crID, cou_name, cou_number, kid_name, date;
    double act_hour, amount;

    public Credit_Info(String crID, String cou_name, String cou_number, String kid_name, double act_hour, String date, double amount) {
        this.crID = crID;
        this.cou_name = cou_name;
        this.cou_number = cou_number;
        this.kid_name = kid_name;
        this.act_hour = act_hour;
        this.date = date;
        this.amount = amount;
    }

    public String getCrID() {
        return crID;
    }

    public void setCrID(String crID) {
        this.crID = crID;
    }

    public String getCou_name() {
        return cou_name;
    }

    public void setCou_name(String cou_name) {
        this.cou_name = cou_name;
    }

    public String getCou_number() {
        return cou_number;
    }

    public void setCou_number(String cou_number) {
        this.cou_number = cou_number;
    }

    public String getKid_name() {
        return kid_name;
    }

    public void setKid_name(String kid_name) {
        this.kid_name = kid_name;
    }

    public double getAct_hour() {
        return act_hour;
    }

    public void setAct_hour(double act_hour) {
        this.act_hour = act_hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
