package com.example.kidnursery;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Generate extends AppCompatActivity implements Footer_Fragment.footerOnClickSetListener, Find_Details_Fragment.onClickSetListener{

    Database db;
    Button specificKidButton;
    LinearLayout buttonsCon;
    FrameLayout searchFragment;
    Button generateProfitStatement;
    TextView parentName;
    TextView parentNumber;
    TextView kidName;
    Button generateButton;
    LinearLayout detailsContainer;
    Button generateAllKid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_main_screen);
        hideNavigationBar();

        Footer_Fragment footer = new Footer_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("page", "generate");
        footer.setArguments(bundle);
        fragmentTransaction.add(R.id.footer_container, footer).commit();

        Find_Details_Fragment find_details_fragment = new Find_Details_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.find_details_layout_container, find_details_fragment).commit();

        db = new Database(getApplicationContext());

        specificKidButton = findViewById(R.id.search_specific_kid);
        buttonsCon = findViewById(R.id.buttonSection);
        searchFragment = findViewById(R.id.find_details_layout_container);
        parentName = findViewById(R.id.getParentName);
        parentNumber = findViewById(R.id.getParentNum);
        generateProfitStatement=findViewById(R.id.generate_profit);
        kidName = findViewById(R.id.getKidName);
        detailsContainer =findViewById(R.id.details_layout);
        generateButton = findViewById(R.id.continue_button);
        generateAllKid = findViewById(R.id.all_kids);

        generateProfitStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), expensesListView.class);
                startActivity(i);
            }
        });

        specificKidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsCon.setVisibility(View.INVISIBLE);
                searchFragment.setVisibility(View.VISIBLE);
            }
        });

        generateAllKid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Generate_All_Kid.class);
                startActivity(i);

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

            String cust_id = "", prent_Name = "", prent_Number ="", Kid_name = "", date;
            double activeHour, Amount;

            Cursor cursor = db.searchKidFromCredit(pr_name.getText().toString(), pr_num.getText().toString(), kid_name.getText().toString());


            if (((cursor != null) && (cursor.getCount() > 0))) {
//
                while(cursor.moveToNext()) {
                    int index;

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

                    parentName.setText(prent_Name);
                    parentNumber.setText(prent_Number);
                    kidName.setText(Kid_name);

                }
                detailsContainer.setVisibility(View.VISIBLE);
                search.setVisibility(View.INVISIBLE);

                String name = prent_Name, kn = Kid_name, pn = prent_Number;
                generateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent iaa = new Intent(getApplicationContext(), Generate_Specific_Kid.class);
                        iaa.putExtra("Cust_name", name);
                        iaa.putExtra("Kid_name", kn);
                        iaa.putExtra("Cust_num", pn);
                        startActivity(iaa);
                    }
                });

            } else {
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