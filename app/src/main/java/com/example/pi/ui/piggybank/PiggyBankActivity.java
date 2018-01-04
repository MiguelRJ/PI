package com.example.pi.ui.piggybank;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.pi.R;
import com.example.pi.ui.piggybank.fragment.AddPiggyBankView;
import com.example.pi.ui.piggybank.fragment.ListPiggyBankView;
import com.example.pi.ui.piggybank.presenter.AddPiggyBankPresenter;
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

public class PiggyBankActivity extends AppCompatActivity implements ListPiggyBankView.ListPiggyBankListener {

    private ListPiggyBankView listView;
    private ListPiggyBankPresenter listPresenter;
    private AddPiggyBankView addView;
    private AddPiggyBankPresenter addPresenter;

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
    public void addNewPiggyBank(Bundle bundle) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        addView = (AddPiggyBankView) fragmentManager.findFragmentByTag(AddPiggyBankView.TAG);
        if (addView == null){
            if (bundle != null){
                addView = AddPiggyBankView.newInstance(bundle);
            } else {
                addView = AddPiggyBankView.newInstance(null);
            }
            fragmentTransaction.replace(android.R.id.content,addView,AddPiggyBankView.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        addPresenter = new AddPiggyBankPresenter(addView);

        addView.setPresenter(addPresenter);
    }
}
