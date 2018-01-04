package com.example.pi.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pi.R;
import com.example.pi.data.prefs.AppPreferencesHelper;
import com.example.pi.ui.MenuActivity;
import com.example.pi.ui.pi.PIApplication;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *
 * @date 04/01/18
 *      EditText
 *      AppPreferencesHelper
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUser, edtPassvword;
    private Button btnLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUser = findViewById(R.id.edtUser);
        edtPassvword = findViewById(R.id.edtPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogIn){
            AppPreferencesHelper app = ((PIApplication)getApplicationContext()).getAppPreferencesHelper();
            app.setCurrentUserName(edtUser.getText().toString());
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
        }
    }


}
