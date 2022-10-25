package com.example.kidnursery;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartPage extends AppCompatActivity implements Login_fragment.loginOnClickSetListener {

    ImageView start_screen;
    RelativeLayout login_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
        hideNavigationBar();

        start_screen=(ImageView) findViewById(R.id.start_screen_id);
        login_container=(RelativeLayout)findViewById(R.id.login_container);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Login_fragment loginFragment=new Login_fragment();
        ft.add(R.id.login_container,loginFragment);
        ft.commit();
        start_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_container.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void goToLogin() {

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
