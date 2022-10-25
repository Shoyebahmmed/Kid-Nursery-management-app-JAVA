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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Expenditures extends AppCompatActivity implements Footer_Fragment.footerOnClickSetListener{

    String eType;
    EditText amount;
    EditText date;
    Spinner mySpinner;
    Button add_expen_button;


    DatePicker datePicker;
    ImageView calendar;
    Button set_date;

    Database db;
    TextView error;
    boolean selected=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenditures_screen);
        hideNavigationBar();

        Footer_Fragment footer = new Footer_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("page", "expenses");
        footer.setArguments(bundle);
        fragmentTransaction.add(R.id.footer_container, footer).commit();


        db=new Database(this);

        mySpinner = (Spinner) findViewById(R.id.spinner1);
        amount= (EditText) findViewById(R.id.amount);
        date=(EditText) findViewById(R.id.date);
        datePicker=(DatePicker)findViewById(R.id.date_picker);
        add_expen_button=findViewById(R.id.add_expen_button);
        error= (TextView) findViewById(R.id.error);
        set_date=(Button) findViewById(R.id.set_date_button);
        calendar=(ImageView)findViewById(R.id.calendar);


        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.list));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(mySpinner.getItemAtPosition(pos).toString().equals("Please select the type"))
                    selected=false;
                if(pos==0) {
                    selected=false;


                }if (pos == 1) {
                    selected=true;
                    error.setVisibility(View.GONE);
                    eType="Rent";

                } else if (pos == 2) {
                    selected=true;
                    error.setVisibility(View.GONE);
                    eType="Utilities";
                }else if (pos == 3) {
                    selected=true;
                    error.setVisibility(View.GONE);
                    eType="Human Resources";

                }else if (pos == 4) {
                    selected=true;
                    error.setVisibility(View.GONE);
                    eType="Maintenance";

                }else if (pos == 5) {
                    selected=true;
                    error.setVisibility(View.GONE);
                    eType="Equipments";

                }else if (pos == 6) {
                    selected=true;
                    error.setVisibility(View.GONE);
                    eType="Other";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selected=false;
            }


        });

        add_expen_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Confirm();
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePicker.setVisibility(View.VISIBLE);
                set_date.setVisibility(View.VISIBLE);
                add_expen_button.setVisibility(View.INVISIBLE);
            }
        });

        set_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date.setText(getDate());

                datePicker.setVisibility(View.INVISIBLE);
                set_date.setVisibility(View.INVISIBLE);
                add_expen_button.setVisibility(View.VISIBLE);

            }
        });


    }
    public String getDate(){
        StringBuilder builder=new StringBuilder();;
        builder.append(datePicker.getDayOfMonth()+"-");
        builder.append((datePicker.getMonth() + 1)+"-");
        builder.append(datePicker.getYear());
        return builder.toString();
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

    public void Confirm()
    {
        if(selected==false)
        {
            error.setVisibility(View.VISIBLE);
            error.setText("Please select expense type");
            return ;
        }

        if(amount.getText().length()==0){
            amount.setError("Field cannot be left blank.");
            return ;
        }

        if(date.getText().length()==0){
            date.setError("Field cannot be left blank.");
            return ;
        }
        if(!date.getText().toString().matches("^([1-9]|[12][0-9]|3[01])-([1-9]|1[012])-((201[8-9]|202[0-2]))$")){
            date.setError("Invalid entry");
            return;
        }

        boolean status=db.insertData(eType,amount.getText().toString(),date.getText().toString());

        if(status==true)
        {
            Toast.makeText(getApplicationContext(),"Entry Added",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getApplicationContext(),"ERROR !!!",Toast.LENGTH_LONG).show();

        Intent i = new Intent(getApplicationContext(), Expenditures.class);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(i);

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