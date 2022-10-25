package com.example.kidnursery;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Date_List_Specific_Kid_Fragment extends Fragment {
    ArrayList<Credit_Info> dcri;
    Database db;
    private ListView listView;

    Month_List_View_Adapter dmlva;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View date_list = inflater.inflate(R.layout.date_list_fragment_layout, container, false);

        db = new Database(getActivity());
        dcri = new ArrayList<Credit_Info>();

        Bundle bundle = getArguments();
        String strtext = "";
        String cust_Name = "";
        String cust_Number = "";
        String Kid_Name = "";
        if (bundle == null) {
            Toast.makeText(getActivity(), "Bundle is null ", Toast.LENGTH_LONG).show();
        } else {
            strtext = bundle.getString("date");
            cust_Name = bundle.getString("cName");
            cust_Number = bundle.getString("cNumber");
            Kid_Name = bundle.getString("kName");
        }
        System.out.println("******************************************** " + cust_Name);
        System.out.println("******************************************** " + cust_Number);
        System.out.println("******************************************** " + Kid_Name);
        getSpecifiqKid m = (getSpecifiqKid) new getSpecifiqKid().execute(strtext, cust_Name, cust_Number, Kid_Name);

        try {

            m.get();
            if (dcri.isEmpty() == true)
                System.out.println("******************************************** 333 The ArrayList is empty");
            else
                System.out.println("******************************************** 333 The ArrayList is not empty");

            listView = (ListView) date_list.findViewById(R.id.date_list_items);

            dmlva = new Month_List_View_Adapter(getActivity(), dcri);
            listView.setAdapter(null);
            listView.setAdapter(dmlva);

        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return date_list;
    }

    class getSpecifiqKid extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String enteredDate = params[0];
            String cust_name = params[1];
            String cust_number = params[2];
            String kid_name = params[3];
            dcri.clear();
            Cursor cursor = db.getDataByDate_Credit_ID(enteredDate, cust_name, cust_number, kid_name);
            System.out.println("************************************************ 111111111");
            if (((cursor != null) && (cursor.getCount() > 0))) {
                System.out.println("************************************************ 3333333333");
                while(cursor.moveToNext()) {
                    System.out.println("************************************************ 44444444444");
                    int index;
                    String crID, prent_Name, prent_Number, Kid_name, date;
                    double activeHour, Amount;

                    index = cursor.getColumnIndexOrThrow("CRID");
                    crID = cursor.getString(index);

                    index = cursor.getColumnIndexOrThrow("COUST_NAME");
                    prent_Name = cursor.getString(index);

                    index = cursor.getColumnIndexOrThrow("COUST_NUMBER");
                    prent_Number = cursor.getString(index);

                    index = cursor.getColumnIndexOrThrow("KID_NAME");
                    Kid_name = cursor.getString(index);

                    index = cursor.getColumnIndexOrThrow("ACTIVE_HOURS");
                    activeHour = cursor.getDouble(index);

                    index = cursor.getColumnIndexOrThrow("AMOUNT");
                    Amount = cursor.getDouble(index);

                    index = cursor.getColumnIndexOrThrow("PAYMENT_DATE");
                    date = cursor.getString(index);

                    System.out.println("************************************************ name = " + prent_Name);

                    dcri.add(new Credit_Info(crID, prent_Name, prent_Number, Kid_name, activeHour, date, Amount));

                }

            }
            System.out.println("************************************************ 22222222");

            return null;
        }
    }

}


