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
import com.example.pi.ui.transaction.fragment.AddTransactionView;
import com.example.pi.ui.transaction.fragment.ListTransactionView;
import com.example.pi.ui.transaction.presenter.AddTransactionPresenter;
import com.example.pi.ui.transaction.presenter.ListTransactionPresenter;

/**
 * Created by Miguel on 25/02/2018.
 */

public class MainActivity extends AppCompatActivity implements MenuView.MenuListener,
        ListPiggyBankView.ListPiggyBankListener,
        ListTransactionView.ListTransactionListener {

    private MenuView menuView;
    private MenuPresenter menuPresenter;
    private ListPiggyBankView listViewPiggyBank;
    private ListPiggyBankPresenter listPresenterPiggyBank;
    private AddPiggyBankView addViewPiggyBank;
    private AddPiggyBankPresenter addPresenterPiggyBank;
    private ListTransactionView listViewTransaction;
    private ListTransactionPresenter listPresenterTransaction;
    private AddTransactionView addViewTransaction;
    private AddTransactionPresenter addPresenterTransaction;

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
        listViewPiggyBank = (ListPiggyBankView) fm.findFragmentByTag(ListPiggyBankView.TAG);
        if (listViewPiggyBank == null) {
            listViewPiggyBank = (ListPiggyBankView) ListPiggyBankView.newInstance(null);
            ft.replace(android.R.id.content,listViewPiggyBank, ListPiggyBankView.TAG);
            ft.addToBackStack(null);
            ft.commit();
        }
        listPresenterPiggyBank = new ListPiggyBankPresenter(listViewPiggyBank);
        listViewPiggyBank.setPresenter(listPresenterPiggyBank);
    }

    @Override
    public void addNewPiggyBank(Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        addViewPiggyBank = (AddPiggyBankView) fm.findFragmentByTag(AddPiggyBankView.TAG);
        if (addViewPiggyBank == null){
            if (bundle != null){
                addViewPiggyBank = AddPiggyBankView.newInstance(bundle);
            } else {
                addViewPiggyBank = AddPiggyBankView.newInstance(null);
            }
            ft.replace(android.R.id.content,addViewPiggyBank,AddPiggyBankView.TAG);
            ft.addToBackStack(null);
            ft.commit();
        }
        addPresenterPiggyBank = new AddPiggyBankPresenter(addViewPiggyBank);
        addViewPiggyBank.setPresenter(addPresenterPiggyBank);
    }

    @Override
    public void loadAllTransaction() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        listViewTransaction = (ListTransactionView) fm.findFragmentByTag(ListTransactionView.TAG);
        if (listViewTransaction == null) {
            listViewTransaction = (ListTransactionView) ListTransactionView.newInstance(null);
            ft.replace(android.R.id.content,listViewTransaction,ListTransactionView.TAG);
            ft.addToBackStack(null);
            ft.commit();
        }
        listPresenterTransaction = new ListTransactionPresenter(listViewTransaction);
        listViewTransaction.setPresenter(listPresenterTransaction);
    }

    @Override
    public void addNewTransaction(Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        addViewTransaction = (AddTransactionView) fm.findFragmentByTag(AddTransactionView.TAG);
        if (addViewTransaction == null){
            if (bundle != null){
                addViewTransaction = AddTransactionView.newInstance(bundle);
            } else {
                addViewTransaction = AddTransactionView.newInstance(null);
            }
            ft.replace(android.R.id.content,addViewTransaction,AddTransactionView.TAG);
            ft.addToBackStack(null);
            ft.commit();
        }
        addPresenterTransaction = new AddTransactionPresenter(addViewTransaction);
        addViewTransaction.setPresenter(addPresenterTransaction);
    }
}
