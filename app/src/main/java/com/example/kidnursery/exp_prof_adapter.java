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

public class exp_prof_adapter extends ArrayAdapter<ExpAndProf> {

    private Context context;
    private ArrayList<ExpAndProf> e_a_p;

    public exp_prof_adapter( Context context, ArrayList<ExpAndProf> e_a_p) {

        super(context, 0, e_a_p);
        this.context = context;
        this.e_a_p = e_a_p;
    }

    @NonNull

    @Nullable
    @Override
    public ExpAndProf getItem(int position) {
        return e_a_p.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //  View items = convertView;
        if(convertView == null){

            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.exp_prof_item_view,null);
            ExpAndProf e1 = e_a_p.get(position);

            TextView tv1 = convertView.findViewById(R.id.date);
            tv1.setText(e1.date+"");

            TextView tv2 = convertView.findViewById(R.id.income);
            tv2.setText(e1.income+"");

            TextView tv3 = convertView.findViewById(R.id.expenses);
            tv3.setText(e1.expenses+"");

            return convertView;

        }




        return convertView;
    }

    @Override
    public int getCount() {
        return this.e_a_p.size();
    }

}

