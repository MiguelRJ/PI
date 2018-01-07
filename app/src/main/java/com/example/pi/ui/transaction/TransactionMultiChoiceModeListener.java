package com.example.pi.ui.transaction;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;

import com.example.pi.R;
import com.example.pi.ui.piggybank.contract.ListPiggyBankContract;
import com.example.pi.ui.transaction.contract.ListTransactionContract;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public class TransactionMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener {

    private ListTransactionContract.Presenter presenter;
    private int count;

    public TransactionMultiChoiceModeListener(ListTransactionContract.Presenter presenter){
        this.presenter = presenter;
        this.count = 0;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
        if (checked){
            count++;
            presenter.setNewSelection(position);
        } else {
            count--;
            presenter.removeSelection(position);
        }
        actionMode.setTitle(count + " " + presenter.nameSelecteds());
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.menu_transaction_longclick,menu);
        actionMode.setTitle("Start");
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // antes de crear la toolbar
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        // cuando se pulsa sobre el elemento
        switch (menuItem.getItemId()){
            case R.id.action_list_transaction_delete:
                presenter.deleteSelection();
                presenter.loadTransaction();
                break;
        }
        actionMode.finish();
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        count = 0;
        presenter.clearSelection();
    }
}
