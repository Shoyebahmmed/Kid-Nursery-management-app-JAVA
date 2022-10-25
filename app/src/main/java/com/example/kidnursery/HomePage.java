package com.example.kidnursery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity implements Footer_Fragment.footerOnClickSetListener{

    Button all_kids_button;
    Button search_kid_button;
    Button statements_button;
    Button logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_sceeen);
        hideNavigationBar();

        Footer_Fragment footer = new Footer_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("page", "home");
        footer.setArguments(bundle);
        fragmentTransaction.add(R.id.footer_container, footer).commit();

        all_kids_button=(Button)findViewById(R.id.all_kids_button);
        search_kid_button=(Button)findViewById(R.id.search_kid_button);
        statements_button=(Button)findViewById(R.id.statements_button);
        logout_button=(Button)findViewById(R.id.logout_button);


        search_kid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SearchKid.class);
                startActivity(i);
            }
        });
        all_kids_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),All_Kids.class);
                startActivity(i);
            }
        });

        statements_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Generate.class);
                startActivity(i);
            }
        });
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent logout=new Intent(getApplicationContext(),StartPage.class);
                startActivity(logout);
            }

        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
    }

    @Override
    public void setName(String s){

        switch(s) {
            case "home_clicked":
                Intent i1 = new Intent(getApplicationContext(), HomePage.class);
                startActivity(i1);

                break;

            case "charge_clicked":
                Intent i2 = new Intent(getApplicationContext(), SearchForPayment.class);
                startActivity(i2);

                break;

            case "add_clicked":
                Intent i3 = new Intent(getApplicationContext(), Enrolment.class);
                startActivity(i3);

                break;

            case "generate_clicked":
                Intent i4 = new Intent(getApplicationContext(), Generate.class);
                startActivity(i4);

                break;

            case "expenses_clicked":
                Intent i5 = new Intent(getApplicationContext(), Expenditures.class);
                startActivity(i5);

                break;

            default:
                break;
        } // switch
    } // setName function



    private void hideNavigationBar(){
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }
}