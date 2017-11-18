package com.example.pi.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pi.R;
import com.example.pi.ui.MenuActivity;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogIn){
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
        }
    }
}
