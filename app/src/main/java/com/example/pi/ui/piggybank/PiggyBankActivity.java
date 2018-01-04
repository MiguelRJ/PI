package com.example.pi.ui.piggybank;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import com.example.pi.R;
import com.example.pi.adapter.PiggyBankAdapter;
import com.example.pi.ui.about.AboutUsActivity;
import com.example.pi.ui.fragment.PiggyBankFragment;
import com.example.pi.ui.piggybank.fragment.ListPiggyBankView;
import com.example.pi.ui.piggybank.presenter.ListPiggyBankPresenter;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *      Attributes
 *      onCreate()
 *      onCreateOptionsMenu()
 *      onOptionsItemSelected()
 */

public class PiggyBankActivity extends AppCompatActivity implements PiggyBankFragment.PiggyBankFragmentListener,ListPiggyBankView.ListPiggyBankListener {

    private ListPiggyBankView listView;
    private ListPiggyBankPresenter listPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piggybank);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        listView = (ListPiggyBankView) fragmentManager.findFragmentByTag(ListPiggyBankView.TAG);
        if (listView == null) {
            listView = (ListPiggyBankView) ListPiggyBankView.newInstance(null);
            fragmentTransaction.add(android.R.id.content,listView, ListPiggyBankView.TAG);
            fragmentTransaction.commit();
        }

        listPresenter = new ListPiggyBankPresenter(listView);

        listView.setPresenter(listPresenter);
    }



    @Override
    public void onPiggyBankFragment() {

    }

    @Override
    public void addNewPiggyBank(Bundle bundle) {

    }
}
