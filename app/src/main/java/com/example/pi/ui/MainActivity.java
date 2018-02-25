package com.example.pi.ui;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.pi.R;
import com.example.pi.ui.menu.fragment.MenuView;
import com.example.pi.ui.menu.presenter.MenuPresenter;
import com.example.pi.ui.piggybank.fragment.AddPiggyBankView;
import com.example.pi.ui.piggybank.fragment.ListPiggyBankView;
import com.example.pi.ui.piggybank.presenter.AddPiggyBankPresenter;
import com.example.pi.ui.piggybank.presenter.ListPiggyBankPresenter;

/**
 * Created by Miguel on 25/02/2018.
 */

public class MainActivity extends AppCompatActivity implements MenuView.MenuListener,
        ListPiggyBankView.ListPiggyBankListener {

    private MenuView menuView;
    private MenuPresenter menuPresenter;
    private ListPiggyBankView listView;
    private ListPiggyBankPresenter listPresenter;
    private AddPiggyBankView addView;
    private AddPiggyBankPresenter addPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        menuView = (MenuView) fm.findFragmentByTag(MenuView.TAG);
        if (menuView == null) {
            menuView = (MenuView) MenuView.newInstance(null);
            ft.add(android.R.id.content, menuView, MenuView.TAG);
            ft.commit();
        }
        menuPresenter = new MenuPresenter(menuView);
        menuView.setPresenter(menuPresenter);

    }

    @Override
    public void loadAllPiggyBank() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        listView = (ListPiggyBankView) fm.findFragmentByTag(ListPiggyBankView.TAG);
        if (listView == null) {
            listView = (ListPiggyBankView) ListPiggyBankView.newInstance(null);
            ft.replace(android.R.id.content,listView, ListPiggyBankView.TAG);
            ft.addToBackStack(null);
            ft.commit();
        }

        listPresenter = new ListPiggyBankPresenter(listView);

        listView.setPresenter(listPresenter);
    }

    @Override
    public void addNewPiggyBank(Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        addView = (AddPiggyBankView) fm.findFragmentByTag(AddPiggyBankView.TAG);
        if (addView == null){
            if (bundle != null){
                addView = AddPiggyBankView.newInstance(bundle);
            } else {
                addView = AddPiggyBankView.newInstance(null);
            }
            ft.replace(android.R.id.content,addView,AddPiggyBankView.TAG);
            ft.addToBackStack(null);
            ft.commit();
        }
        addPresenter = new AddPiggyBankPresenter(addView);

        addView.setPresenter(addPresenter);
    }
}
