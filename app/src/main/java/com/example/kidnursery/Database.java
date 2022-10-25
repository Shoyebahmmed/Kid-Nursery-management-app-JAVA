package com.example.kidnursery;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.Date;

public class Database extends SQLiteAssetHelper {

    public Database(Context context) {
        super(context, "Nursery.db", null, 1);
    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create Table user_information (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "Parent_name TEXT," +
//                "Parent_number TEXT," +
//                "Kid_name TEXT, " +
//                "kid_age INTEGER,"+
//                "register_date DATE)");
//
//
//        db.execSQL("create Table credit_information (CRID TEXT PRIMARY KEY, " +
//                "COUST_NAME TEXT," +
//                "COUST_NUMBER TEXT," +
//                "KID_NAME TEXT, " +
//                "ACTIVE_HOURS DECIMAL(4,2), " +
//                "AMOUNT DECIMAL(7,2), " +
//                "PAYMENT_DATE DATE)");
//
//        db.execSQL("create Table Expenses (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "Expense_type TEXT," +
//                "Amount DECIMAL(7,2), " +
//                "Purchase_date DATE)");
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists user_information");
        db.execSQL("drop Table if exists credit_information");
    }

    public Boolean insertUserInfo(String Parent_name, String Parent_number, String Kid_name, int kid_age, String register_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Parent_name", Parent_name);
        cv.put("Parent_number", Parent_number);
        cv.put("Kid_name", Kid_name);
        cv.put("kid_age", kid_age);
        cv.put("register_date",register_date);

        long result = db.insert("user_information", null, cv);

        if(result == -1)
            return false;

        else return true;
    }


    public Boolean insertCreditInfo(String id, String Coust_name, String Coust_number, String kid_name, double active_hours, double Amount,String payment_date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CRID", id);
        cv.put("COUST_NAME", Coust_name);
        cv.put("COUST_NUMBER", Coust_number);
        cv.put("KID_NAME", kid_name);
        cv.put("ACTIVE_HOURS", active_hours);
        cv.put("AMOUNT", Amount);
        cv.put("PAYMENT_DATE",payment_date);

        long result = db.insert("credit_information", null, cv);

        if(result == -1)
            return false;

        else return true;
    }



    public Boolean updateUserInfo(String id, String Parent_name, String Parent_number, String Kid_name, int kid_age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Parent_name", Parent_name);
        cv.put("Parent_number", Parent_number);
        cv.put("Kid_name", Kid_name);
        cv.put("kid_age", kid_age);

        Cursor cursor = db.rawQuery("Select * from user_information where id = ?", new String[] {id});

        if(cursor.getCount() > 0) {
            long result = db.update("user_information", cv, "id=?", new String[]{id});

            if (result == -1)
                return false;

            else return true;
        }

        return false;
    }


    public Boolean deleteUserInfo(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from user_information where id = ?", new String[] {id});

        if(cursor.getCount() > 0) {
            long result = db.delete("user_information", "id=?", new String[]{id});

            if (result == -1)
                return false;

            else return true;
        }

        return false;
    }


    public Boolean deleteCreditInfo(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from credit_information where id = ?", new String[] {id});

        if(cursor.getCount() > 0) {
            long result = db.delete("credit_information", "id=?", new String[]{id});

            if (result == -1)
                return false;

            else return true;
        }

        return false;
    }



    public Cursor getUserData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from user_information", null);
        return cursor;
    }


    public Cursor searchKid(String name, String p_num, String kidName) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("select * from user_information  where Parent_name = ? AND Parent_number = ? AND Kid_name = ?",
                new String[] { name, p_num, kidName});

        return c;
    }

    public Cursor searchKidFromCredit(String name, String p_num, String kidName) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("select * from credit_information  where COUST_NAME = ? AND COUST_NUMBER = ? AND KID_NAME = ?",
                new String[] { name, p_num, kidName});

        return c;
    }


    public boolean insertData(String EXPENSETYPE, String AMOUNT, String DATE) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Expense_type",EXPENSETYPE);
        contentValues.put("Amount", AMOUNT );
        contentValues.put("Purchase_date", DATE);

        long result = db.insert("Expenses",null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getDataByMonth_Credit(String month) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * from credit_information where PAYMENT_DATE like ?",
                new String[] { "%-" + month + "-%" });
        return c;
    }

    public Cursor getDataByMonth_Credit_ID(String month, String cname, String cnumber, String kidname) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * from credit_information where PAYMENT_DATE like ? AND COUST_NAME = ? AND COUST_NUMBER = ? AND KID_NAME = ?",
                new String[] { "%-" + month + "-%" , cname, cnumber, kidname});
        return c;
    }

    public Cursor getDataByYear_Credit(String year) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * from credit_information where PAYMENT_DATE like ?",
                new String[] { "%-" + year });
        return c;
    }

    public Cursor getDataByYear_Credit_ID(String year, String cname, String cnumber, String kidname) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * from credit_information where PAYMENT_DATE like ? AND COUST_NAME = ? AND COUST_NUMBER = ? AND KID_NAME = ?",
                new String[] { "%-" + year, cname, cnumber, kidname });
        return c;
    }

    public Cursor getDataByDate_Credit(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * from credit_information where PAYMENT_DATE = ?",
                new String[] { date });
        return c;
    }

    public Cursor getDataByDate_Credit_ID(String date, String cname, String cnumber, String kidname) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("********************************************* from Database name = " + cname);
        Cursor c = db.rawQuery("Select * from credit_information where PAYMENT_DATE = ? AND COUST_NAME = ? AND COUST_NUMBER = ? AND KID_NAME = ?",
                new String[] { date, cname, cnumber, kidname});
        return c;
    }

    public Cursor getExpense() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select Amount,Purchase_date from  Expenses ",null);
        return res;
    }
    public Cursor getIncome() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select AMOUNT,PAYMENT_DATE from  credit_information ",null);
        return res;
    }
    public Cursor getExpenseByYear(String year) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select Amount,Purchase_date from  Expenses where Purchase_date like ?",
                new String[] { "%-" + year });
        return res;
    }

    public Cursor getIncomeByYear(String year) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select AMOUNT,PAYMENT_DATE from  credit_information where PAYMENT_DATE like ?",
                new String[] { "%-" + year });
        return res;
    }

    public Cursor getExpenseByMonth(String month) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select Amount,Purchase_date from  Expenses where Purchase_date like ?",
                new String[] { "%-" + month + "-%" });
        return res;
    }

    public Cursor getIncomeByMonth(String month) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select AMOUNT,PAYMENT_DATE from  credit_information where PAYMENT_DATE like ?",
                new String[] { "%-" + month + "-%" });
        return res;
    }

    public Cursor getExpenseByDate(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select Amount,Purchase_date from  Expenses where Purchase_date = ?",
                new String[] { date });
        return res;
    }

    public Cursor getIncomeByDate(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select AMOUNT,PAYMENT_DATE from  credit_information where PAYMENT_DATE = ?",
                new String[] { date });
        return res;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+"Expenses",null);
        return res;
    }



    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Expenses", "ID = ?",new String[] {id});
    }


}
