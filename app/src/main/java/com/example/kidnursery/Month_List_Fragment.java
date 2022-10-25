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

public class Month_List_Fragment extends Fragment {
    ArrayList<Credit_Info> cri;
    Database db;
    private ListView listView;

    Month_List_View_Adapter mlva;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View month_list = inflater.inflate(R.layout.month_list_fragment_layout, container, false);

        db = new Database(getActivity());
        cri = new ArrayList<Credit_Info>();

        Bundle bundle = getArguments();
        String strtext = "";
        if (bundle == null) {
            Toast.makeText(getActivity(), "Bundle is null " , Toast.LENGTH_LONG).show();
        } else {
            strtext = bundle.getString("month");
        }

        if(strtext.equals("January")) {
            MyTask m = (MyTask)new MyTask().execute(1);
            try {

                m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else if(strtext.equals("February")) {
            MyTask m = (MyTask)new MyTask().execute(2);
            try {

                m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else if(strtext.equals("March")) {
            MyTask m = (MyTask)new MyTask().execute(3);
            try {

                m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else if(strtext.equals("April")) {
            MyTask m = (MyTask)new MyTask().execute(4);
            try {

                m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else if(strtext.equals("May")) {
            MyTask m = (MyTask)new MyTask().execute(5);
            try {

                m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else if(strtext.equals("June")) {
            MyTask m = (MyTask)new MyTask().execute(6);
            try {

                m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else if(strtext.equals("July")) {
            MyTask m = (MyTask)new MyTask().execute(7);
            try {

                m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else if(strtext.equals("August")) {
            MyTask m = (MyTask)new MyTask().execute(8);
            try {

                m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else if(strtext.equals("September")) {
            MyTask m = (MyTask)new MyTask().execute(9);
            try {

                m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else if(strtext.equals("October")) {
            MyTask m = (MyTask)new MyTask().execute(10);
            try {

                m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else if(strtext.equals("November")) {
            MyTask m = (MyTask)new MyTask().execute(11);
            try {

                m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        else if(strtext.equals("December")) {
            MyTask m = (MyTask)new MyTask().execute(12);
            try {

                 m.get();
//                if (cri.isEmpty() == true)
//                    System.out.println("******************************************** 333 The ArrayList is empty");
//                else
//                    System.out.println("******************************************** 333 The ArrayList is not empty");

                listView = (ListView) month_list.findViewById(R.id.month_list_items);

                mlva = new Month_List_View_Adapter(getActivity(), cri);
                listView.setAdapter(null);
                listView.setAdapter(mlva);

            } catch (InterruptedException e) {
                e.printStackTrace();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


        return month_list;
    }


    class MyTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            Integer month = params[0];
            cri.clear();
            Cursor cursor = db.getDataByMonth_Credit(month.toString());
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

                    cri.add(new Credit_Info(crID, prent_Name, prent_Number, Kid_name, activeHour, date, Amount));

                }

            }
            System.out.println("************************************************ 22222222");

            return null;
        }
    }
}
