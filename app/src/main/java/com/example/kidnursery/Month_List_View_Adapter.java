package com.example.kidnursery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Month_List_View_Adapter extends ArrayAdapter<Credit_Info> {

    private Context context;
    private ArrayList<Credit_Info> info_list;

    public Month_List_View_Adapter(Context context, ArrayList<Credit_Info> info_list) {
        super(context, 0, info_list);
        this.context = context;
        this.info_list = info_list;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItems = convertView;
        if(listItems == null){
            listItems = LayoutInflater.from(context).inflate(R.layout.month_list_item, parent, false);
        }

        Credit_Info ci = info_list.get(position);

        TextView t1 = (TextView) listItems.findViewById(R.id.par_name);
        t1.setText(ci.getCou_name());

        TextView t2 = (TextView) listItems.findViewById(R.id.ki_name);
        t2.setText(ci.getKid_name());

        TextView t3 =(TextView) listItems.findViewById(R.id.credit_date);
        t3.setText(ci.getDate());

        TextView t4 = (TextView) listItems.findViewById(R.id.credit_amount);
        t4.setText(ci.getAmount()+"");

        return listItems;
    }

}
