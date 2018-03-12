package com.mrj.morejmoney.ui.recover;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pi.R;
import com.mrj.morejmoney.data.prefs.AppPreferencesHelper;
import com.mrj.morejmoney.ui.base.BaseAppCompatActivity;
import com.mrj.morejmoney.ui.login.LoginActivity;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 07/01/18
 *      Para recueprar la contraseña se implementara el envio de email
 *      para comprobar que se ahce bien la aplicacion hara un toas
 *      y de momento la contraseña se mostrar en el toast
 *
 *      Para no obtener las contraseñas a lo bruto, si el usuario o el email no existen la contraseña a mostrar sera null
 */

public class RecoverActivity extends BaseAppCompatActivity implements RecoverContract.View, View.OnClickListener {

    private RecoverPresenter presenter;

    private EditText edtUser, edtEmail;
    private Button btnRecoverPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
        presenter = new RecoverPresenter(this);

        edtUser = findViewById(R.id.edtUser);
        edtEmail = findViewById(R.id.edtEmail);

        btnRecoverPassword = findViewById(R.id.btnRecoverPassword);
        btnRecoverPassword.setOnClickListener(this);
    }

    /* implements RecoverContract.View */
    @Override
    public void onUserEmptyError() {
        onError(R.string.errorUserEmpty);
    }

    @Override
    public void onEmailEmptyError() {
        onError(R.string.errorEmailEmpty);
    }

    @Override
    public void onEmailError() {
        onError(R.string.errorEmail);
    }

    @Override
    public void onSuccess() {
        String anEmailSend = getString(R.string.AnEmailHasBeenSend);
        String message = anEmailSend + "\n"
                + edtEmail.getText().toString() + "\n"
                + AppPreferencesHelper.getInstance().getCurrentUserPassword();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RecoverActivity.this, LoginActivity.class));
    }
    /* implements RecoverContract.View */

    @Override
    public void onClick(View v) {
        if (v == btnRecoverPassword){
            presenter.validateCredentials(edtUser.getText().toString(),edtEmail.getText().toString());
        }
    }
}
