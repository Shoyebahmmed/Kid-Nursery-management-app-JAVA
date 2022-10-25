package com.example.kidnursery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchKid extends AppCompatActivity implements Footer_Fragment.footerOnClickSetListener, Find_Details_Fragment.onClickSetListener{

    Database db;
    TextView kidId;
    TextView parentName;
    TextView parentNumber;
    TextView kidName;
    TextView kidAge;
    TextView registerDate;
    LinearLayout detailsContainer;
    Button search_another;
    ImageView modify;
    ImageView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
        hideNavigationBar();

        db = new Database(getApplicationContext());
        kidId=findViewById(R.id.getKidId);
        parentName = findViewById(R.id.getParentName);
        parentNumber = findViewById(R.id.getParentNum);
        kidName = findViewById(R.id.getKidName);
        kidAge = findViewById(R.id.getKidAge);
        registerDate = findViewById(R.id.getRegisterDate);
        detailsContainer =findViewById(R.id.details_layout);
        search_another=(Button)findViewById(R.id.seacrh_another_button);
        modify=(ImageView)findViewById(R.id.modify_button);
        delete=(ImageView)findViewById(R.id.delete_button);

        Footer_Fragment footer = new Footer_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("page", "none");
        footer.setArguments(bundle);
        fragmentTransaction.add(R.id.footer_container, footer).commit();

        Find_Details_Fragment find_details_fragment = new Find_Details_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.find_details_layout_container, find_details_fragment).commit();

        search_another.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SearchKid.class);
                startActivity(i);
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button save_button=findViewById(R.id.save_button);
                save_button.setVisibility(View.VISIBLE);

                TextView textView_id = findViewById(R.id.getKidId);

                TextView textView_name = findViewById(R.id.getParentName);
                EditText editText_name = findViewById(R.id.editName);
                editText_name.setVisibility(View.VISIBLE);
                editText_name.setText(textView_name.getText().toString());
                textView_name.setVisibility(View.GONE);

                TextView textView_number = findViewById(R.id.getParentNum);
                EditText editText_number = findViewById(R.id.editNumber);
                editText_number.setVisibility(View.VISIBLE);
                editText_number.setText(textView_number.getText().toString());
                textView_number.setVisibility(View.GONE);

                TextView textView_kidName = findViewById(R.id.getKidName);
                EditText editText_kidName = findViewById(R.id.editKidName);
                editText_kidName.setVisibility(View.VISIBLE);
                editText_kidName.setText(textView_kidName.getText().toString());
                textView_kidName.setVisibility(View.GONE);

                TextView textView_kidAge = findViewById(R.id.getKidAge);
                EditText editText_kidAge = findViewById(R.id.editKidAge);
                editText_kidAge.setVisibility(View.VISIBLE);
                editText_kidAge.setText(textView_kidAge.getText().toString());
                textView_kidAge.setVisibility(View.GONE);

                save_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                        alert.setTitle("Save Changes\n"+"Id: "+textView_id.getText().toString()+"       Name: "+textView_kidName.getText().toString());
                        alert.setMessage("Are you sure to modify this entry?");
                        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean modified=db.updateUserInfo(textView_id.getText().toString(),editText_name.getText().toString(),
                                        editText_number.getText().toString(),editText_kidName.getText().toString(),
                                        Integer.parseInt(editText_kidAge.getText().toString()));
                                if(modified) {
                                    Toast.makeText(getApplicationContext(), "Details Modified", Toast.LENGTH_SHORT).show();
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Intent reload=new Intent(getApplicationContext(),SearchKid.class);
                                    startActivity(reload);
                                }
                            }
                        });

                        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        try {
                            alert.show();
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Delete Entry\n"+"Id: "+kidId.getText().toString()+"       Name: "+kidName.getText().toString());
                alert.setMessage("Are you sure to delete this entry?");
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean deleted=db.deleteUserInfo(kidId.getText().toString());
                        if(deleted) {
                            Toast.makeText(getApplicationContext(), "Kid deleted from Database", Toast.LENGTH_SHORT).show();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Intent intent=new Intent(getApplicationContext(),SearchKid.class);
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.show();
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

            String prent_Name, prent_Number, Kid_name, register_date;
            int Kid_age;

            Cursor cursor = db.searchKid(pr_name.getText().toString(), pr_num.getText().toString(), kid_name.getText().toString());


            if (((cursor != null) && (cursor.getCount() > 0))) {
//
                while(cursor.moveToNext()) {
                    int index;

                    index = cursor.getColumnIndexOrThrow("id");
                    int id = cursor.getInt(index);

                    index = cursor.getColumnIndexOrThrow("Parent_name");
                    prent_Name = cursor.getString(index);

                    index = cursor.getColumnIndexOrThrow("Parent_number");
                    prent_Number = cursor.getString(index);

                    index = cursor.getColumnIndexOrThrow("Kid_name");
                    Kid_name = cursor.getString(index);

                    index = cursor.getColumnIndexOrThrow("kid_age");
                    Kid_age = cursor.getInt(index);

                    index = cursor.getColumnIndexOrThrow("register_date");
                    register_date = cursor.getString(index);


                    kidId.setText(id+"");
                    parentName.setText(prent_Name);
                    parentNumber.setText(prent_Number);
                    kidName.setText(Kid_name);
                    kidAge.setText(Kid_age+"");
                    registerDate.setText(register_date);

                }
                detailsContainer.setVisibility(View.VISIBLE);
                search.setVisibility(View.INVISIBLE);
                search_another.setVisibility(View.VISIBLE);

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