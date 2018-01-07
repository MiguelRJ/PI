package com.example.pi.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.pi.R;
import com.example.pi.ui.base.BaseAppCompatActivity;
import com.example.pi.ui.login.LoginActivity;

public class SigninActivity extends BaseAppCompatActivity implements View.OnClickListener,SigninContract.View {

    private SigninContract.Presenter presenter;

    private EditText edtUser, edtPassword, edtEmail, edtName;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        presenter = new SigninPresenter(this);

        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSignIn){
            Log.e("passact",edtPassword.getText().toString());
            presenter.validateCredentials(
                    edtUser.getText().toString(),
                    edtPassword.getText().toString(),
                    edtEmail.getText().toString(),
                    edtName.getText().toString()
            );
        }
    }

    /* implements SigninContract.View */
    @Override
    public void onSuccess() {
        startActivity(new Intent(SigninActivity.this, LoginActivity.class));
    }

    @Override
    public void onUserDuplicated() {
        onError(R.string.errorUserDuplicated);
    }

    @Override
    public void onEmailDuplicated() {
        onError(R.string.errorEmailDuplicated);
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
    public void onEmailEmptyError() {
        onError(R.string.errorEmailEmpty);
    }

    @Override
    public void onPasswordError() {
        onError(R.string.errorPasswordText);
    }

    @Override
    public void onEmailError() {
        onError(R.string.errorEmail);
    }

    /* implements SigninContract.View */
}
