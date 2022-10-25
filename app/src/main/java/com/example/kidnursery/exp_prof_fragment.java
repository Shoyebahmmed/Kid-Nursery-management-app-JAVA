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

public class exp_prof_fragment extends Fragment {
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
            strtext = bundle.getString("month");
            System.out.println("************************************************ strtex = " + strtext);
        }
        if(strtext.equals("January")) {
            aTask a = (aTask)new aTask().execute(1);
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
        }
        if(strtext.equals("February")) {
            aTask a = (aTask)new aTask().execute(2);
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
        }
        if(strtext.equals("March")) {
            aTask a = (aTask)new aTask().execute(3);
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
        }
        if(strtext.equals("April")) {
            aTask a = (aTask)new aTask().execute(4);
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
        }
        if(strtext.equals("May")) {
            aTask a = (aTask)new aTask().execute(5);
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
        }
        if(strtext.equals("June")) {
            aTask a = (aTask)new aTask().execute(6);
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
        }
        if(strtext.equals("July")) {
            aTask a = (aTask)new aTask().execute(7);
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
        }
        if(strtext.equals("August")) {
            aTask a = (aTask)new aTask().execute(8);
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
        }
        if(strtext.equals("September")) {
            aTask a = (aTask)new aTask().execute(9);
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
        }
        if(strtext.equals("October")) {
            aTask a = (aTask)new aTask().execute(10);
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
        }
        if(strtext.equals("November")) {
            aTask a = (aTask)new aTask().execute(11);
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
        }

        if(strtext.equals("December")) {
            aTask a = (aTask)new aTask().execute(12);
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
        }




        return expensesList;
    }


    class aTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            Integer month = params[0];
            Cursor cursor = db.getExpenseByMonth(month.toString());
            Cursor cursor2 = db.getIncomeByMonth(month.toString());

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

