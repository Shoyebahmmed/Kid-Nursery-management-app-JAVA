package com.example.kidnursery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Enrolment extends AppCompatActivity implements Footer_Fragment.footerOnClickSetListener{

    EditText kid_age;
    EditText kid_name;
    EditText parent_name;
    EditText parent_contact;
    EditText register_date;

    DatePicker datePicker;

    ImageView calendar;
    Button enrol_button;
    Button set_date;

    ImageView tick_image;
    TextView enrol_success;
    RelativeLayout details_container;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroment_screen);
        hideNavigationBar();

        Footer_Fragment footer = new Footer_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("page", "add");
        footer.setArguments(bundle);
        fragmentTransaction.add(R.id.footer_container, footer).commit();

        kid_age=(EditText)findViewById(R.id.kid_age);
        kid_name=(EditText)findViewById(R.id.kid_name);
        parent_name=(EditText)findViewById(R.id.parent_name);
        parent_contact=(EditText)findViewById(R.id.parent_contact);
        register_date=(EditText)findViewById(R.id.register_date);
        enrol_success=(TextView)findViewById(R.id.enrol_success);
        datePicker=(DatePicker)findViewById(R.id.date_picker);

        enrol_button=(Button) findViewById(R.id.enrol_button);
        set_date=(Button) findViewById(R.id.set_date_button);

        calendar=(ImageView)findViewById(R.id.calendar);
        tick_image=(ImageView)findViewById(R.id.tick);

        details_container= (RelativeLayout) findViewById(R.id.enter_details);


        db = new Database(getApplicationContext());

        enrol_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean validity=true;

                if(kid_age.getText().length()==0) {
                    kid_age.setError("Field cannot be left blank.");
                    validity=false;
                }
                else if(Integer.parseInt(kid_age.getText().toString())>6 || Integer.parseInt(kid_age.getText().toString())<2){
                    kid_age.setError("Child's age should be between 2-6");
                    validity=false;
                }

                if(kid_name.getText().length()==0){
                    kid_name.setError("Field cannot be left blank.");
                    validity=false;
                }
                else if(kid_name.getText().toString().matches(".*\\d.*")){
                    kid_name.setError("Invalid entry.");
                    validity=false;
                }

                if(parent_name.getText().length()==0){
                    parent_name.setError("Field cannot be left blank.");
                    validity=false;
                }

                else if(parent_name.getText().toString().matches(".*\\d.*")){
                    parent_name.setError("Invalid entry.");
                    validity=false;
                }


                if(parent_contact.getText().length()==0){
                    parent_contact.setError("Field cannot be left blank.");
                    validity=false;
                }
                else if(parent_contact.getText().length()!=12){
                    parent_contact.setError("Invalid Phone Number.");
                    validity=false;
                }

                if(register_date.getText().length()==0){
                    register_date.setError("Field cannot be left blank.");
                    validity=false;
                }
                else if(!register_date.getText().toString().matches("^([1-9]|[12][0-9]|3[01])-([1-9]|1[012])-((201[8-9]|202[0-2]))$")){
                    register_date.setError("Invalid Entry");
                    validity=false;
                }

                if(validity)
                {
                    String p_name = parent_name.getText().toString();
                    String p_num = parent_contact.getText().toString();
                    String k_name = kid_name.getText().toString();
                    int k_age = Integer.parseInt(kid_age.getText().toString());
                    String date = register_date.getText().toString();

                    boolean insertChecker = db.insertUserInfo(p_name, p_num, k_name, k_age,date);
                    if(insertChecker == true) {
                        try {
                            details_container.setVisibility(View.INVISIBLE);
                            enrol_button.setVisibility(View.INVISIBLE);
                            tick_image.setVisibility(View.VISIBLE);
                            enrol_success.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "New Kid added to the System", Toast.LENGTH_SHORT).show();
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent i1 = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(i1);

                    }
                    else
                        Toast.makeText(getApplicationContext(), "ERROR!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePicker.setVisibility(View.VISIBLE);
                set_date.setVisibility(View.VISIBLE);

                enrol_button.setVisibility(View.INVISIBLE);
            }
        });

        set_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_date.setText(getDate());

                datePicker.setVisibility(View.INVISIBLE);
                set_date.setVisibility(View.INVISIBLE);
                enrol_button.setVisibility(View.VISIBLE);

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