package com.mrj.morejmoney.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.mrj.morejmoney.R;
import com.mrj.morejmoney.data.prefs.AppPreferencesHelper;
import com.mrj.morejmoney.ui.MainActivity;
import com.mrj.morejmoney.ui.base.BaseAppCompatActivity;
import com.mrj.morejmoney.ui.pi.PIApplication;
import com.mrj.morejmoney.ui.recover.RecoverActivity;
import com.mrj.morejmoney.ui.signin.SigninActivity;

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
    private TextView txvSignUp, txvForgotPassword;
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
        txvForgotPassword = findViewById(R.id.txvForgotPassword);
        chkRemember = findViewById(R.id.chkRemember);

        if (AppPreferencesHelper.getInstance().getCurrentRemember()) {
            edtUser.setText(AppPreferencesHelper.getInstance().getCurrentUserName());
            edtPassvword.setText(AppPreferencesHelper.getInstance().getCurrentUserPassword());
            chkRemember.setChecked(AppPreferencesHelper.getInstance().getCurrentRemember());
        }

        txvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SigninActivity.class));
            }
        });
        txvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RecoverActivity.class));
            }
        });
        btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnLogIn){
            presenter.validateCredentials(edtUser.getText().toString(),edtPassvword.getText().toString(),chkRemember.isChecked());
        }
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
