package com.example.pi.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.example.pi.R;
import com.example.pi.data.prefs.AppPreferencesHelper;
import com.example.pi.ui.MenuActivity;
import com.example.pi.ui.base.BaseAppCompatActivity;
import com.example.pi.ui.pi.PIApplication;
import com.example.pi.ui.signin.SigninActivity;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *
 * @date 04/01/18
 *      EditText
 *      AppPreferencesHelper
 */

public class LoginActivity extends BaseAppCompatActivity implements View.OnClickListener,LoginContract.View {

    private LoginPresenter presenter;

    private EditText edtUser, edtPassvword;
    private TextView txvSignUp;
    private CheckBox chkRemember;
    private Button btnLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppPreferencesHelper app = ((PIApplication)getApplicationContext()).getAppPreferencesHelper();
        presenter = new LoginPresenter(this);

        edtUser = findViewById(R.id.edtUser);
        edtPassvword = findViewById(R.id.edtPassword);
        txvSignUp = findViewById(R.id.txvSignUp);
        chkRemember = findViewById(R.id.chkRemember);
        txvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SigninActivity.class));
            }
        });
        btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnLogIn){
            presenter.validateCredentials(edtUser.getText().toString(),edtPassvword.getText().toString());
        }
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
    }

    @Override
    public void onUserEmptyError() {
        onError(R.string.errorUserEmpty);
    }

    @Override
    public void onPasswordEmptyError() {
        onError(R.string.errorPasswordEmpty);
    }

    @Override
    public void onPasswordError() {
        onError(R.string.errorPassword);
    }

    @Override
    public void onCredentialsFail() {
        onError(R.string.errorCredentialsFail);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
