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

public class Year_List_Fragment extends Fragment {

    Database db;
    private ListView listView;
    ArrayList<Credit_Info> ycri;

    Month_List_View_Adapter ymlva;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View year_list = inflater.inflate(R.layout.year_list_fragment_layout, container, false);

        db = new Database(getActivity());
        ycri = new ArrayList<Credit_Info>();

        Bundle bundle = getArguments();
        String strtext = "";
        if (bundle == null) {
            Toast.makeText(getActivity(), "Bundle is null ", Toast.LENGTH_LONG).show();
        } else {
            strtext = bundle.getString("year");
        }


        MyTask m = (MyTask)new MyTask().execute(strtext);
            try {

                m.get();
                listView = (ListView) year_list.findViewById(R.id.year_list_items);

                ymlva = new Month_List_View_Adapter(getActivity(), ycri);
                listView.setAdapter(null);
                listView.setAdapter(ymlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        return year_list;
    }


    class MyTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String year = params[0];
            Cursor cursor = db.getDataByYear_Credit(year.toString());
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

                    ycri.add(new Credit_Info(crID, prent_Name, prent_Number, Kid_name, activeHour, date, Amount));

                }

            }
            System.out.println("************************************************ 22222222");

            return null;
        }
    }

}
