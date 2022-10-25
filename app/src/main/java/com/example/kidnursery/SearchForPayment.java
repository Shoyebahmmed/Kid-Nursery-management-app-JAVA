package com.example.kidnursery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchForPayment extends AppCompatActivity implements Footer_Fragment.footerOnClickSetListener, Find_Details_Fragment.onClickSetListener{
    Database db;
    TextView parentName;
    TextView parentNumber;
    TextView kidName;
    TextView kidAge;
    EditText activeHour;
    LinearLayout detailsContainer;
    Button calculate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_screen1);
        hideNavigationBar();

        db = new Database(getApplicationContext());
        parentName = findViewById(R.id.getParentName);
        parentNumber = findViewById(R.id.getParentNum);
        kidName = findViewById(R.id.getKidName);
        kidAge = findViewById(R.id.getKidAge);
        activeHour = findViewById(R.id.getActiveHour);
        detailsContainer =findViewById(R.id.details_layout);
        calculate = findViewById(R.id.calculate);


        Footer_Fragment footer = new Footer_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("page", "charge");
        footer.setArguments(bundle);
        fragmentTransaction.add(R.id.footer_container, footer).commit();


        Find_Details_Fragment find_details_fragment = new Find_Details_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.find_details_layout_container, find_details_fragment).commit();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validity=true;

                if(activeHour.getText().length()==0) {
                    activeHour.setError("Please Enter Active Hours");
                    validity=false;
                }
                else
                {
                    Intent i = new Intent(getApplicationContext(), Payment.class);
                    String str1 = parentName.getText().toString();
                    String str2 = parentNumber.getText().toString();
                    String str3 = kidName.getText().toString();
                    String str4 = activeHour.getText().toString();

                    i.putExtra("pname", str1);
                    i.putExtra("pnum", str2);
                    i.putExtra("kname", str3);
                    i.putExtra("active", str4);
                    startActivity(i);
                }

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


    @Override
    public void setString(String s) {

        if (s.equals("searchKid")) {
            EditText pr_name = findViewById(R.id.pr_name);
            EditText pr_num = findViewById(R.id.pr_number);
            EditText kid_name = findViewById(R.id.ki_name);
            Button search = findViewById(R.id.find_kid);

            String prent_Name, prent_Number, Kid_name;
            int Kid_age;

            Cursor cursor = db.searchKid(pr_name.getText().toString(), pr_num.getText().toString(), kid_name.getText().toString());


            if (((cursor != null) && (cursor.getCount() > 0))) {
//
                while(cursor.moveToNext()) {
                    int index;

                    index = cursor.getColumnIndexOrThrow("Parent_name");
                    prent_Name = cursor.getString(index);

                    index = cursor.getColumnIndexOrThrow("Parent_number");
                    prent_Number = cursor.getString(index);

                    index = cursor.getColumnIndexOrThrow("Kid_name");
                    Kid_name = cursor.getString(index);

                    index = cursor.getColumnIndexOrThrow("kid_age");
                    Kid_age = cursor.getInt(index);


                    parentName.setText(prent_Name);
                    parentNumber.setText(prent_Number);
                    kidName.setText(Kid_name);
                    kidAge.setText(Kid_age+"");

                }
                detailsContainer.setVisibility(View.VISIBLE);
                calculate.setVisibility(View.VISIBLE);
                search.setVisibility(View.INVISIBLE);

            }
            else {
                Toast.makeText(this, "Sorry, Record not found", Toast.LENGTH_SHORT).show();
            }
        }
    }



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