package com.example.kidnursery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class expensesListView extends AppCompatActivity implements Footer_Fragment.footerOnClickSetListener{

    Spinner gSpinner;
    Spinner monthSpinner;
    String gType;
    String mType;
    boolean selected=true;
    boolean monthSelected=true;
    TextView error;
    LinearLayout selectMonth;
    FrameLayout myContainer;
    Button generateByMonth;
    Button generateByYear;
    Button getGenerateByDate;
    EditText getDate;
    EditText getYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list_view);


        gSpinner = findViewById(R.id.spinner2);
        error = findViewById(R.id.gError);
        generateByMonth = findViewById(R.id.generateByMonth);
        monthSpinner = findViewById(R.id.spinner3);
        selectMonth = findViewById(R.id.selectMonth);
        generateByYear = findViewById(R.id.generateByYear);
        myContainer = findViewById(R.id.expAndProContainer);
        getGenerateByDate = findViewById(R.id.generateByDate);
        getDate = findViewById(R.id.getDate);
        getYear = findViewById(R.id.getYear);




        ArrayAdapter<String> genAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.generate));
        genAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gSpinner.setAdapter(genAdapter);
        gSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(gSpinner.getItemAtPosition(pos).toString().equals("Please select the type"))
                    selected=false;
                if(pos==0) {
                    selected=false;
                }

                if (pos == 1) {
                    selected=true;
                    error.setVisibility(View.GONE);
                    getGenerateByDate.setVisibility(View.VISIBLE);
                    getDate.setVisibility(View.VISIBLE);
                    selectMonth.setVisibility(View.INVISIBLE);
                    generateByYear.setVisibility(View.INVISIBLE);
                    generateByMonth.setVisibility(View.INVISIBLE);
                    getYear.setVisibility(View.INVISIBLE);
                    gType="Date";
                }

                else if (pos == 2) {
                    selected=true;
                    error.setVisibility(View.GONE);
                    selectMonth.setVisibility(View.VISIBLE);
                    generateByMonth.setVisibility(View.VISIBLE);
                    getGenerateByDate.setVisibility(View.INVISIBLE);
                    getDate.setVisibility(View.INVISIBLE);
                    generateByYear.setVisibility(View.GONE);
                    getYear.setVisibility(View.INVISIBLE);
                    gType="Month";
                }

                else if (pos == 3) {
                    selected=true;
                    error.setVisibility(View.GONE);
                    selectMonth.setVisibility(View.GONE);
                    getGenerateByDate.setVisibility(View.INVISIBLE);
                    getDate.setVisibility(View.INVISIBLE);
                    generateByMonth.setVisibility(View.GONE);
                    generateByYear.setVisibility(View.VISIBLE);
                    getYear.setVisibility(View.VISIBLE);
                    gType="Year";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selected=false;
            }


        });


        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.month));
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(monthSpinner.getItemAtPosition(pos).toString().equals("Please select the type"))
                    monthSelected=false;
                if(pos==0) {
                    monthSelected=false;
                }

                if (pos == 1) {
                    monthSelected=true;
                    mType="January";

                }

                else if (pos == 2) {
                    monthSelected=true;
                    mType="February";
                }

                else if (pos == 3) {
                    monthSelected=true;
                    mType="March";
                }

                else if (pos == 4) {
                    monthSelected=true;
                    mType="April";
                }

                else if (pos == 5) {
                    monthSelected=true;
                    mType="May";
                }

                else if (pos == 6) {
                    monthSelected=true;
                    mType="June";
                }

                else if (pos == 7) {
                    monthSelected=true;
                    mType="July";
                }

                else if (pos == 8) {
                    monthSelected=true;
                    mType="August";
                }

                else if (pos == 9) {
                    monthSelected=true;
                    mType="September";
                }

                else if (pos == 10) {
                    monthSelected=true;
                    mType="October";
                }

                else if (pos == 11) {
                    monthSelected=true;
                    mType="November";
                }

                else if (pos == 12) {
                    monthSelected=true;
                    mType="December";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                monthSelected=false;
            }

        });




        generateByMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selected==false)
                {
                    error.setVisibility(View.VISIBLE);
                    error.setText("Please select expense type");
                    return ;
                }

                if(gType.equals("Date")) {

                }
                else if(gType.equals("Month")) {
                    if(monthSelected==false)
                    {
                        error.setVisibility(View.VISIBLE);
                        error.setText("Please select expense type");
                        return ;
                    }
                    selectMonth.setVisibility(View.VISIBLE);
                    if(mType.equals("January")) {



                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        myDBData.putString("month", mType);
                        mlf.setArguments(myDBData);
                        System.out.println("************************************************ mtype = " + mType);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                    }
                    else if(mType.equals("February")) {


                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        myDBData.putString("month", mType);
                        mlf.setArguments(myDBData);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                    }
                    else if(mType.equals("March")) {

                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        myDBData.putString("month", mType);
                        mlf.setArguments(myDBData);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                    }
                    else if(mType.equals("April")) {


                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        myDBData.putString("month", mType);
                        mlf.setArguments(myDBData);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                    }
                    else if(mType.equals("May")) {


                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        myDBData.putString("month", mType);
                        mlf.setArguments(myDBData);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                    }
                    else if(mType.equals("June")) {


                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        myDBData.putString("month", mType);
                        mlf.setArguments(myDBData);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                    }
                    else if(mType.equals("July")) {


                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        mlf.setArguments(myDBData);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                    }
                    else if(mType.equals("August")) {


                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        myDBData.putString("month", mType);
                        mlf.setArguments(myDBData);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                    }
                    else if(mType.equals("September")) {


                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        myDBData.putString("month", mType);
                        mlf.setArguments(myDBData);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                    }
                    else if(mType.equals("October")) {

                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        myDBData.putString("month", mType);
                        mlf.setArguments(myDBData);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                    }
                    else if(mType.equals("November")) {

                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        myDBData.putString("month", mType);
                        mlf.setArguments(myDBData);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                    }
                    else if(mType.equals("December")) {


                        exp_prof_fragment mlf = new exp_prof_fragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Bundle myDBData = new Bundle();
                        myDBData.putString("month", mType);
                        mlf.setArguments(myDBData);
                        fragmentTransaction.add(R.id.expAndProContainer, mlf).commit();

                        //System.out.println("************************************************************************ " + mType);

                    }
                }
                else if(gType.equals("Year")) {

                }

            }
        });

        generateByYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validity = true;
                System.out.println("********************************************************** bbbbbbbbbbbbbbbbb");
                if(getYear.getText().length()==0){
                    getYear.setError("Field cannot be left blank.");
                    System.out.println("********************************************************** ccccccccccccccccccccccc");
                    validity=false;
                }
                else if(!getYear.getText().toString().matches("^((201[8-9]|202[0-2]))$")){
                    getYear.setError("Invalid Entry");
                    System.out.println("********************************************************** zzzzzzzzzzzzzzzzz");
                    validity=false;
                }

                if(validity) {
                    String year = getYear.getText().toString();
                    Year_exp_prof_Fragment ylf = new Year_exp_prof_Fragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    Bundle myDBData = new Bundle();
                    myDBData.putString("year", year);
                    ylf.setArguments(myDBData);
                    System.out.println("********************************************************** aaaaaaaaaaaaaaaaaaaaa");
                    fragmentTransaction.add(R.id.expAndProContainer, ylf).commit();
                }
            }
        });



        getGenerateByDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validity = true;

                if(getDate.getText().length()==0){
                    getDate.setError("Field cannot be left blank.");
                    validity=false;
                }
                else if(!getDate.getText().toString().matches("^([1-9]|[12][0-9]|3[01])-([1-9]|1[012])-((201[8-9]|202[0-2]))$")){
                    getDate.setError("Invalid Entry");
                    validity=false;
                }

                if(validity)
                {
                    String date = getDate.getText().toString();
                    getYear.setVisibility(View.INVISIBLE);
                    Date_exp_prof_Fragment dlf = new Date_exp_prof_Fragment();
                    FragmentManager fragmentManager3 = getSupportFragmentManager();
                    final FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();

                    Bundle myDBData = new Bundle();
                    myDBData.putString("date", date);
                    dlf.setArguments(myDBData);
                    System.out.println("********************************************************** 8888888888888");
                    fragmentTransaction3.add(R.id.expAndProContainer, dlf).commit();
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