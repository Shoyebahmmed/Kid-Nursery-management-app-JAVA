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

public class Year_exp_prof_Fragment extends Fragment {
    ArrayList<ExpAndProf> eapList = new ArrayList<>();
    Database db;
    ListView listView;

    exp_prof_adapter epa;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View expensesList = inflater.inflate(R.layout.expenses_fragement_listview, container, false);

        db = new Database(getActivity());

        Bundle bundle = getArguments();
        String strtext = "";
        if (bundle == null) {
            Toast.makeText(getActivity(), "Bundle is null " , Toast.LENGTH_LONG).show();
        } else {
            strtext = bundle.getString("year");
            System.out.println("************************************************ strtex = " + strtext);
        }

        aTask a = (aTask)new aTask().execute(strtext);
        try {
            a.get();
            if (eapList.isEmpty() == true)
                System.out.println("******************************************** 333 The ArrayList is empty");
            else
                System.out.println("******************************************** 333 The ArrayList is not empty");
            listView = (ListView) expensesList.findViewById(R.id.elist);
            epa = new exp_prof_adapter(getActivity(), eapList);
            listView.setAdapter(epa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();

        }
        System.out.println("************************************************ YESSSSSSSSSS");




        return expensesList;
    }


    class aTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String year = params[0];
            Cursor cursor = db.getExpenseByYear(year);
            Cursor cursor2 = db.getIncomeByYear(year);

            System.out.println("************************************************ 111111111");
            if (((cursor != null) && (cursor.getCount() > 0))) {
//
                while(cursor.moveToNext()) {

                    int index;
                    String  incomeDate;
                    String  expenseDate;
                    double income;
                    double expense;

                    //  index = cursor2.getColumnIndexOrThrow("PAYMENT_DATE");
                    // incomeDate = cursor2.getString(index);

                    index = cursor.getColumnIndexOrThrow("Purchase_date");
                    expenseDate = cursor.getString(index);

                    //   index = cursor2.getColumnIndexOrThrow("AMOUNT");
                    // income = cursor2.getDouble(index);

                    index = cursor.getColumnIndexOrThrow("Amount");
                    expense = cursor.getDouble(index);



                    System.out.println("************************************************ 877778");

                    eapList.add(new ExpAndProf(expense,expenseDate));

                }


            }
            if (((cursor2 != null) && (cursor2.getCount() > 0))) {


                while(cursor2.moveToNext()) {

                    int index;
                    String  incomeDate;
                    //String  expenseDate;
                    double income;
                    //double expense;

                    index = cursor2.getColumnIndexOrThrow("PAYMENT_DATE");
                    incomeDate = cursor2.getString(index);

                    // index = cursor.getColumnIndexOrThrow("Purchase_date");
                    //expenseDate = cursor.getString(index);

                    index = cursor2.getColumnIndexOrThrow("AMOUNT");
                    income = cursor2.getDouble(index);

                    ///index = cursor.getColumnIndexOrThrow("Amount");
                    //expense = cursor.getDouble(index);



                    System.out.println("************************************************ 877778");

                    eapList.add(new ExpAndProf(incomeDate,income));

                }


            }
            System.out.println("************************************************ 22222222");

            return null;
        }
    }
}

