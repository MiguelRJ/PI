package com.example.pi.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.pi.R;
import com.example.pi.data.prefs.AppPreferencesHelper;
import com.example.pi.ui.about.AboutUsActivity;
import com.example.pi.ui.pi.PIApplication;
import com.example.pi.ui.piggybank.PiggyBankActivity;
import com.example.pi.ui.prefs.AccountSettingActivity;
import com.example.pi.ui.transaction.TransactionActivity;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *
 */

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton fabOpenPiggyBank;
    private FloatingActionButton fabOpenTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*fabOpenPiggyBank = findViewById(R.id.fabOpenPiggyBank);
        fabOpenPiggyBank.setOnClickListener(this);
        fabOpenTransaction = findViewById(R.id.fabOpenTransaction);
        fabOpenTransaction.setOnClickListener(this);*/
        /*AppPreferencesHelper app = ((PIApplication)getApplicationContext()).getAppPreferencesHelper();
        String message = app.getCurrentUserId()+". Tu usuario de sesion es "+ app.getCurrentUserName();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onClick(View v) {
        if (v == fabOpenPiggyBank){
            startActivity( new Intent(MenuActivity.this, PiggyBankActivity.class));
        }
        if (v == fabOpenTransaction){
            startActivity( new Intent(MenuActivity.this, TransactionActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_aboutus:
                startActivity(new Intent(MenuActivity.this, AboutUsActivity.class));
                break;
            case R.id.action_preferences:
                startActivity(new Intent(MenuActivity.this, AccountSettingActivity.class));
                break;
            default:
                break;
        }
        return false;
    }

}
