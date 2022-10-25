package com.example.kidnursery;

public class ExpAndProf {

    Double expenses;
    Double income;
    String date;

    public ExpAndProf(Double expenses, Double income, String date) {
        this.expenses = expenses;
        this.income = income;
        this.date = date;
    }

    public ExpAndProf(Double expenses, String date) {
        this.expenses = expenses;
        this.date = date;
        this.income=0.0;
    }


    public ExpAndProf(String date, Double income) {
        this.income = income;
        this.date = date;
        this.expenses=0.0;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
