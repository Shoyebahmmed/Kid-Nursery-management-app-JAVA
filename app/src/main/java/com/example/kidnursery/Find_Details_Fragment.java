package com.example.kidnursery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Find_Details_Fragment extends Fragment {

    onClickSetListener listener;
    Button search_kid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.find_details_layout, container, false);

        search_kid = v.findViewById(R.id.find_kid);


        search_kid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setString("searchKid");
            }
        });

        return v;
    }

    public interface onClickSetListener{
        public void setString(String name);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (onClickSetListener) context;
    }
}
