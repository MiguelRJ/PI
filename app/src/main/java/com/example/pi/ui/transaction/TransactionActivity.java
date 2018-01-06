package com.example.pi.ui.transaction;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.example.pi.R;
import com.example.pi.ui.base.BaseAppCompatActivity;
import com.example.pi.ui.piggybank.fragment.AddPiggyBankView;
import com.example.pi.ui.piggybank.presenter.AddPiggyBankPresenter;
import com.example.pi.ui.transaction.fragment.AddTransactionView;
import com.example.pi.ui.transaction.fragment.ListTransactionView;
import com.example.pi.ui.transaction.presenter.AddTransactionPresenter;
import com.example.pi.ui.transaction.presenter.ListTransactionPresenter;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *      Attributes
 *      onCreate()
 *      onCreateOptionsMenu()
 *      onOptionsItemSelected()
 */

public class TransactionActivity extends BaseAppCompatActivity implements ListTransactionView.ListTransactionListener{

    private ListTransactionView listView;
    private ListTransactionPresenter listPresenter;
    private AddTransactionView addView;
    private AddTransactionPresenter addPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        listView = (ListTransactionView) fragmentManager.findFragmentByTag(ListTransactionView.TAG);
        if (listView == null) {
            listView = (ListTransactionView) ListTransactionView.newInstance(null);
            fragmentTransaction.add(android.R.id.content,listView,ListTransactionView.TAG);
            fragmentTransaction.commit();
        }

        listPresenter = new ListTransactionPresenter(listView);
        listView.setPresenter(listPresenter);
    }

    @Override
    public void addNewTransaction(Bundle bundle) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        addView = (AddTransactionView) fragmentManager.findFragmentByTag(AddTransactionView.TAG);
        if (addView == null){
            if (bundle != null){
                addView = AddTransactionView.newInstance(bundle);
            } else {
                addView = AddTransactionView.newInstance(null);
            }
            fragmentTransaction.replace(android.R.id.content,addView,AddTransactionView.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        addPresenter = new AddTransactionPresenter(addView);

        addView.setPresenter(addPresenter);
    }
}
