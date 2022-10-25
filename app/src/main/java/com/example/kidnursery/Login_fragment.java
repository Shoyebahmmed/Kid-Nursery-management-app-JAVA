package com.example.kidnursery;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class Login_fragment extends Fragment {

    EditText username;
    EditText password;
    TextView attempts_count;
    LinearLayout attempts;

    Button login;
    int attempts_counter=3;

    loginOnClickSetListener listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_layout, container, false);

        username=(EditText) v.findViewById(R.id.username);
        password=(EditText) v.findViewById(R.id.password);
        attempts_count=(TextView) v.findViewById(R.id.attempts_count);
        attempts=(LinearLayout) v.findViewById(R.id.attempts);
        login=(Button) v.findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin123"))
                {
                    Toast.makeText(v.getContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent i=new Intent(v.getContext(), HomePage.class);
                    startActivity(i);
                }
                else
                    {
                    Toast.makeText(v.getContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                    attempts.setVisibility(View.VISIBLE);
                    attempts_counter--;
                    attempts_count.setText(Integer.toString(attempts_counter));
                    if (attempts_counter == 0)
                    {
                        login.setEnabled(false);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent i=new Intent(v.getContext(), StartPage.class);
                        startActivity(i);
                    }
                }
            }
        });

        return v;
    }
    public interface loginOnClickSetListener {
        public void goToLogin();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (loginOnClickSetListener) context;
    }

}
