package com.example.kidnursery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class kidsAdapter extends ArrayAdapter<Kid> {

    private Context mContext;
    private List<Kid> kidList;

    public kidsAdapter(Context context, ArrayList<Kid> kidList)
    {
        super(context, 0 , kidList);
        mContext = context;
        this.kidList = kidList;
    }

    public List<Kid> getKidList() {
        return kidList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) listItem = LayoutInflater.from(mContext).inflate(R.layout.single_kid, parent, false);
        Kid currentKid = kidList.get(position);

        TextView id=(TextView) listItem.findViewById(R.id.getKidId);
        id.setText(String.valueOf(currentKid.getId()));

        TextView parent_name = listItem.findViewById(R.id.getParentName);
        parent_name.setText(currentKid.getParent_name());

        TextView parent_contact = (TextView) listItem.findViewById(R.id.getParentNum);
        parent_contact.setText(currentKid.getParent_contact());

        TextView kid_name = (TextView) listItem.findViewById(R.id.getKidName);
        kid_name.setText(currentKid.getKid_name());

        TextView kid_age=(TextView) listItem.findViewById(R.id.getKidAge);
        kid_age.setText(String.valueOf(currentKid.getKid_age()));

        TextView register_date = (TextView) listItem.findViewById(R.id.getRegisterDate);
        register_date.setText(currentKid.getRegister_date());

        return listItem;
    }

}
