package com.example.kidnursery;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Footer_Fragment extends Fragment {

    ImageView home;
    ImageView charge;
    ImageView add;
    ImageView generate;
    ImageView expenses;

    footerOnClickSetListener listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.footer_fragment_layout, container, false);



        home = v.findViewById(R.id.home_icon);
        charge = v.findViewById(R.id.charge_kid_icon);
        add = v.findViewById(R.id.add_icon);
        generate = v.findViewById(R.id.generate_icon);
        expenses = v.findViewById(R.id.expense_icon);

        Bundle bundle = getArguments();
        String strtext = "";
        if (bundle == null) {
            Toast.makeText(getActivity(), "arguments is null " , Toast.LENGTH_LONG).show();
        } else {
            strtext = bundle.getString("page");
        }

        if(strtext.equals("home")) {
            home.setColorFilter(Color.rgb(255, 140, 0 ));
            charge.setColorFilter(Color.rgb(255, 255, 255));
            add.setColorFilter(Color.rgb(255, 255, 255 ));
            generate.setColorFilter(Color.rgb(255, 255, 255));
            expenses.setColorFilter(Color.rgb(255, 255, 255 ));
        }
        else if(strtext.equals("charge")) {
            home.setColorFilter(Color.rgb(255, 255, 255 ));
            charge.setColorFilter(Color.rgb(255, 140, 0));
            add.setColorFilter(Color.rgb(255, 255, 255));
            generate.setColorFilter(Color.rgb(255, 255, 255));
            expenses.setColorFilter(Color.rgb(255, 255, 255 ));
        }
        else if(strtext.equals("add")) {
            home.setColorFilter(Color.rgb(255, 255, 255 ));
            charge.setColorFilter(Color.rgb(255, 255, 255));
            add.setColorFilter(Color.rgb(255, 140, 0 ));
            generate.setColorFilter(Color.rgb(255, 255, 255));
            expenses.setColorFilter(Color.rgb(255, 255, 255 ));
        }
        else if(strtext.equals("generate")) {
            home.setColorFilter(Color.rgb(255, 255, 255 ));
            charge.setColorFilter(Color.rgb(255, 255, 255));
            add.setColorFilter(Color.rgb(255, 255, 255 ));
            generate.setColorFilter(Color.rgb(255, 140, 0 ));
            expenses.setColorFilter(Color.rgb(255, 255, 255 ));
        }
        else if(strtext.equals("expenses")) {
            home.setColorFilter(Color.rgb(255, 255, 255 ));
            charge.setColorFilter(Color.rgb(255, 255, 255));
            add.setColorFilter(Color.rgb(255, 255, 255 ));
            generate.setColorFilter(Color.rgb(255, 255, 255 ));
            expenses.setColorFilter(Color.rgb(255, 140, 0  ));
        }
        else if(strtext.equals("none")) {
            home.setColorFilter(Color.rgb(255, 255, 255 ));
            charge.setColorFilter(Color.rgb(255, 255, 255));
            add.setColorFilter(Color.rgb(255, 255, 255 ));
            generate.setColorFilter(Color.rgb(255, 255, 255 ));
            expenses.setColorFilter(Color.rgb(255, 255, 255  ));
        }


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setName("home_clicked");
            }
        });

        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setName("charge_clicked");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setName("add_clicked");
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setName("generate_clicked");
            }
        });

        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setName("expenses_clicked");
            }
        });




        return v;
    }

    public interface footerOnClickSetListener {
        public void setName(String name);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (footerOnClickSetListener) context;
    }

}
