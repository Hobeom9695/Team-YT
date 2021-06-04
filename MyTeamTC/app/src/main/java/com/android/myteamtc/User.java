package com.android.myteamtc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class User extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);


        Button buttonToJoin = findViewById(R.id.buttonToJoin);
        buttonToJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent JoinActivity = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(JoinActivity);
            }
        });


        Button buttonToLogin = findViewById(R.id.buttonToLogin );
        buttonToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(LoginActivity);
            }
        });

    }



}