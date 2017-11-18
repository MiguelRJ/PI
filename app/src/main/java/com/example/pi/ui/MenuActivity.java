package com.example.pi.ui;

import android.app.ActivityGroup;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.pi.R;
import com.example.pi.adapter.PiggyBankAdapter;
import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.ui.fragment.FastPayFragment;
import com.example.pi.ui.fragment.PiggyBankFragment;
import com.example.pi.ui.piggybank.PiggyBankActivity;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *
 */

public class MenuActivity extends AppCompatActivity implements View.OnClickListener,FastPayFragment.FastPayFragmentListener,PiggyBankFragment.PiggyBankFragmentListener {

    TabHost tab;
    private FloatingActionButton fabOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tab = findViewById(R.id.tabHost);
        fabOpen = findViewById(R.id.fabOpen);
        fabOpen.setOnClickListener(this);
        tabHostInitialize();
    }

    @Override
    public void onClick(View v) {
        if (v == fabOpen){
            startActivity( new Intent(MenuActivity.this, PiggyBankActivity.class));
        }
    }

    public void tabHostInitialize() {
        tab.setup();
        tab.addTab(tab.newTabSpec("tab0").setIndicator("PiggyBanks", null).setContent(R.id.fragmentPiggyBank));
        tab.addTab(tab.newTabSpec("tab2").setIndicator("Transaction", null).setContent(R.id.llyTransaction));
    }


    @Override
    public void onFastPayFragment() {

    }

    @Override
    public void onPiggyBankFragment() {

    }
}
