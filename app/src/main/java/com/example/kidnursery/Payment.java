package com.example.kidnursery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class Payment extends AppCompatActivity implements Footer_Fragment.footerOnClickSetListener{


    TextView parentName;
    TextView parentNumber;
    TextView kidName;
    TextView activeHour;
    TextView totalAmount;
    Button done;
    Button discount;
    LinearLayout discountContainer;
    EditText userEnteredPromo;
    TextView text;

    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_screen2);
        hideNavigationBar();

        Footer_Fragment footer = new Footer_Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("page", "charge");
        footer.setArguments(bundle);
        fragmentTransaction.add(R.id.footer_container, footer).commit();


        parentName = findViewById(R.id.getParentName);
        parentNumber = findViewById(R.id.getParentNum);
        kidName = findViewById(R.id.getKidName);
        activeHour = findViewById(R.id.getActiveHour);
        done = findViewById(R.id.done);
        totalAmount = findViewById(R.id.getTotalAmount);
        discount = findViewById(R.id.discount);
        discountContainer = findViewById(R.id.discountContainer);
        userEnteredPromo = findViewById(R.id.getPromoCodeFromUser);
        text = findViewById(R.id.text);

        db = new Database(getApplicationContext());

        Intent i = getIntent();
        String pName = i.getStringExtra("pname");
        String pNumber = i.getStringExtra("pnum");
        String kName = i.getStringExtra("kname");
        String aHour = i.getStringExtra("active");

        Calculation cal = new Calculation(Double.parseDouble(aHour));
        String total = cal.Calculate_Total();

        parentName.setText(pName);
        parentNumber.setText(pNumber);
        kidName.setText(kName);
        activeHour.setText(aHour);
        totalAmount.setText(total + "  AED");



        discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done.setVisibility(View.INVISIBLE);
                discount.setVisibility(View.INVISIBLE);
                discountContainer.setVisibility(View.VISIBLE);

                TextView totalWithPromo = findViewById(R.id.getTotalAmountWithPromo);
                LinearLayout totalContainer = findViewById(R.id.totalWithPromoContainer);
                Button doneWithPromo = findViewById(R.id.doneWithPromo);
                Button calculatePromo = findViewById(R.id.calculateWithPromo);


                String a = userEnteredPromo.getText().toString();
                text.setText(userEnteredPromo.getText().toString());


                calculatePromo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String total_with_promo = cal.Calculate_Total_with_promo(userEnteredPromo.getText().toString());

                        boolean coupon_validity = true;
                        if(total_with_promo.equals(total)) {
                            userEnteredPromo.setError("Coupon Code is not Valid");
                            coupon_validity = false;
                        }


                        if(coupon_validity) {
                            totalWithPromo.setText(total_with_promo + "  AED");
                            totalContainer.setVisibility(View.VISIBLE);
                            doneWithPromo.setVisibility(View.VISIBLE);
                        }
                    }
                });

                doneWithPromo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Your Payment is Done.", Toast.LENGTH_SHORT).show();
                        // ************************************************************************************************ Add to Database
                        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                        StringBuilder builder=new StringBuilder();
                        builder.append(calendar.get(Calendar.DATE)+"-");
                        builder.append((calendar.get(Calendar.MONTH)+1)+"-");
                        builder.append(calendar.get(Calendar.YEAR));

                        String id = getAlphaNumericString(4);
                        String total_with_promo = cal.Calculate_Total_with_promo(userEnteredPromo.getText().toString());

                            boolean insertChecker = db.insertCreditInfo(id, pName, pNumber, kName, Double.parseDouble(aHour), Double.parseDouble(total_with_promo), builder.toString());
                            if(insertChecker == true) {
                                Toast.makeText(getApplicationContext(), "Payment done Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(getApplicationContext(), "Sorry. Try again!!!", Toast.LENGTH_SHORT).show();



                            Intent i = new Intent(getApplicationContext(), SearchForPayment.class);
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            startActivity(i);
                        }
                });

            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your Payment is Done.", Toast.LENGTH_SHORT).show();
                // ************************************************************************************************ Add to Database


                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                StringBuilder builder=new StringBuilder();
                builder.append(calendar.get(Calendar.DATE)+"-");
                builder.append((calendar.get(Calendar.MONTH)+1)+"-");
                builder.append(calendar.get(Calendar.YEAR));

                String id = getAlphaNumericString(4);

                boolean insertChecker = db.insertCreditInfo(id, pName, pNumber, kName, Double.parseDouble(aHour), Double.parseDouble(total), builder.toString());
                if(insertChecker == true) {
                    Toast.makeText(Payment.this, "Payment done Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Payment.this, "Sorry. Try again!!!", Toast.LENGTH_SHORT).show();



                Intent i = new Intent(getApplicationContext(), SearchForPayment.class);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(i);
            }
        });


    }


    public String getAlphaNumericString(int n) {


        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
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