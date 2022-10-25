package com.example.kidnursery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class All_Kids extends AppCompatActivity implements Footer_Fragment.footerOnClickSetListener{

    private ListView listView;
    kidsAdapter myAdapter;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_kids_screen);
        hideNavigationBar();


        Footer_Fragment footer = new Footer_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("page", "none");
        footer.setArguments(bundle);
        fragmentTransaction.add(R.id.footer_container, footer).commit();


        listView=(ListView) findViewById(R.id.all_kids);
        db = new Database(getApplicationContext());

        ArrayList<Kid> kidsList = new ArrayList<>();
        Cursor cursor=db.getUserData();

        int index;

        if (((cursor != null) && (cursor.getCount() > 0))) {

            while(cursor.moveToNext()) {

                index = cursor.getColumnIndexOrThrow("id");
                int id = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("Parent_name");
                String parent_name = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("Parent_number");
                String parent_contact = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("Kid_name");
                String kid_name = cursor.getString(index);

                index = cursor.getColumnIndexOrThrow("kid_age");
                int kid_age = cursor.getInt(index);

                index = cursor.getColumnIndexOrThrow("register_date");
                String register_date = cursor.getString(index);

                kidsList.add(new Kid(id,parent_name,parent_contact,kid_name,kid_age,register_date));
            }
        }
        else {
            Toast.makeText(this, "Sorry, No Records found", Toast.LENGTH_SHORT).show();
        }

        myAdapter = new kidsAdapter(this, kidsList);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        });
    }

    public void delete_kid(View view)
    {
        LinearLayout parentRow=(LinearLayout)view.getParent().getParent();

        TextView textView_id = parentRow.findViewById(R.id.getKidId);
        TextView textView_name = parentRow.findViewById(R.id.getKidName);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Entry\n"+"Id: "+textView_id.getText().toString()+"       Name: "+textView_name.getText().toString());
        alert.setMessage("Are you sure to delete this entry?");
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean deleted=db.deleteUserInfo(textView_id.getText().toString());
                if(deleted) {
                    Toast.makeText(getApplicationContext(), "Kid deleted from Database", Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent=new Intent(getApplicationContext(),All_Kids.class);
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

    public void modify_kid(View view)
    {
        LinearLayout parentRow=(LinearLayout)view.getParent().getParent();

        Button save_button=parentRow.findViewById(R.id.save_button);
        save_button.setVisibility(View.VISIBLE);

        TextView textView_id = parentRow.findViewById(R.id.getKidId);

        TextView textView_name = parentRow.findViewById(R.id.getParentName);
        EditText editText_name = parentRow.findViewById(R.id.editName);
        editText_name.setVisibility(View.VISIBLE);
        editText_name.setText(textView_name.getText().toString());
        textView_name.setVisibility(View.GONE);

        TextView textView_number = parentRow.findViewById(R.id.getParentNum);
        EditText editText_number = parentRow.findViewById(R.id.editNumber);
        editText_number.setVisibility(View.VISIBLE);
        editText_number.setText(textView_number.getText().toString());
        textView_number.setVisibility(View.GONE);

        TextView textView_kidName = parentRow.findViewById(R.id.getKidName);
        EditText editText_kidName = parentRow.findViewById(R.id.editKidName);
        editText_kidName.setVisibility(View.VISIBLE);
        editText_kidName.setText(textView_kidName.getText().toString());
        textView_kidName.setVisibility(View.GONE);

        TextView textView_kidAge = parentRow.findViewById(R.id.getKidAge);
        EditText editText_kidAge = parentRow.findViewById(R.id.editKidAge);
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
                            Intent reload=new Intent(getApplicationContext(),All_Kids.class);
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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}