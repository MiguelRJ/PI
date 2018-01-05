package com.example.pi.ui.transaction;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.example.pi.R;
import com.example.pi.ui.base.BaseAppCompatActivity;
import com.example.pi.ui.piggybank.fragment.AddPiggyBankView;
import com.example.pi.ui.piggybank.presenter.AddPiggyBankPresenter;
import com.example.pi.ui.transaction.fragment.ListTransactionView;
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
    private ListTransactionPresenter presenter;

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

        presenter = new ListTransactionPresenter(listView);
        listView.setPresenter(presenter);
    }

    @Override
    public void addNewTransaction(Bundle bundle) {
        
    }
}
