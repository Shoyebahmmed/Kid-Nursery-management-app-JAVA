package com.example.kidnursery;

import java.text.DecimalFormat;

public class Calculation {
    public double activeHour;


    public Calculation(double activeHour) {
        this.activeHour = activeHour;
    }


    DecimalFormat f = new DecimalFormat("##.00");

    public String Calculate_Total(){
        double total = 0;
        if(activeHour < 4) {
            total = activeHour * 40;
        }
        else if(activeHour == 4) {
            total = 140;
        }
        else if(activeHour > 4) {
            double extra = (activeHour - 4) * 30;
            total = 140 + extra;
        }

        String finalAns = f.format(total);

        return finalAns;
    }


    public String Calculate_Total_with_promo(String promoCode){
        double original_price = Double.parseDouble(Calculate_Total());
        final String promo1 = "KID10";
        final String promo2 = "KID15";
        final String promo3 = "KID20";
        double discounted_price = 0;


        switch (promoCode) {

            case promo1:
                discounted_price = original_price - (original_price * 10 / 100);
                break;

            case promo2:
                discounted_price = original_price - (original_price * 15 / 100);
                break;

            case promo3:
                discounted_price = original_price - (original_price * 20 / 100);
                break;

            default:
                discounted_price = original_price;
                break;
        }

        String finalAns = f.format(discounted_price);

        return finalAns;
    }


}

