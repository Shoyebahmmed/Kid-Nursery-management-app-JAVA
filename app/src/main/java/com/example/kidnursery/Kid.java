package com.example.kidnursery;

public class Kid {

    int id;
    String parent_name;
    String parent_contact;
    String kid_name;
    int kid_age;
    String register_date;

    public Kid(int id, String parent_name, String parent_contact, String kid_name, int kid_age, String register_date) {
        this.id=id;
        this.parent_name = parent_name;
        this.parent_contact = parent_contact;
        this.kid_name = kid_name;
        this.kid_age = kid_age;
        this.register_date=register_date;
    }

    public String getParent_name() {
        return parent_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getParent_contact() {
        return parent_contact;
    }

    public void setParent_contact(String parent_contact) {
        this.parent_contact = parent_contact;
    }

    public String getKid_name() {
        return kid_name;
    }

    public void setKid_name(String kid_name) {
        this.kid_name = kid_name;
    }

    public int getKid_age() {
        return kid_age;
    }

    public void setKid_age(int kid_age) {
        this.kid_age = kid_age;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }
}