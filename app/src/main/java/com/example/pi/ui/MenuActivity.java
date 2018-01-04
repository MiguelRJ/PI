package com.example.pi.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.pi.R;
import com.example.pi.data.prefs.AppPreferencesHelper;
import com.example.pi.ui.fragment.FastPayFragment;
import com.example.pi.ui.fragment.PiggyBankNoAppBarFragment;
import com.example.pi.ui.fragment.TransactionNoAppBarFragment;
import com.example.pi.ui.pi.PIApplication;
import com.example.pi.ui.piggybank.PiggyBankActivity;
import com.example.pi.ui.transaction.TransactionActivity;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *
 */

public class MenuActivity extends AppCompatActivity implements View.OnClickListener,FastPayFragment.FastPayFragmentListener,PiggyBankNoAppBarFragment.PiggyBankNoAppBarFragmentListener,TransactionNoAppBarFragment.TransactionNoAppBarFragmentListener {

    TabHost tab;
    private FloatingActionButton fabOpenPiggyBank;
    private FloatingActionButton fabOpenTransaction;
    String TAB_1_TAG;
    String TAB_2_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TAB_1_TAG = getApplicationContext().getResources().getString(R.string.tabPiggyBank);
        TAB_2_TAG = getApplicationContext().getResources().getString(R.string.tabTransaction);
        tab = findViewById(R.id.tabHost);
        fabOpenPiggyBank = findViewById(R.id.fabOpenPiggyBank);
        fabOpenPiggyBank.setOnClickListener(this);
        fabOpenTransaction = findViewById(R.id.fabOpenTransaction);
        fabOpenTransaction.setOnClickListener(this);
        tabHostInitialize();
        AppPreferencesHelper app = ((PIApplication)getApplicationContext()).getAppPreferencesHelper();
        String message = app.getCurrentUserId()+". Tu usuario de sesion es "+ app.getCurrentUserName();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
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

    public void tabHostInitialize() {
        tab.setup();
        tab.addTab(tab.newTabSpec("tab0").setIndicator(TAB_1_TAG, null).setContent(R.id.llyPiggyBank));
        tab.addTab(tab.newTabSpec("tab2").setIndicator(TAB_2_TAG, null).setContent(R.id.llyTransaction));
    }

    @Override
    public void onFastPayFragment() {

    }

    @Override
    public void onPiggyBankNoAppBarFragment() {

    }

    @Override
    public void onTransactionFragment() {

    }
}
