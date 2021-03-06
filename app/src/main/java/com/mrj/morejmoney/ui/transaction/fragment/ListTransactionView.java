package com.mrj.morejmoney.ui.transaction.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mrj.morejmoney.R;
import com.mrj.morejmoney.adapter.TransactionAdapter;
import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.ui.base.BasePresenter;
import com.mrj.morejmoney.ui.transaction.TransactionMultiChoiceModeListener;
import com.mrj.morejmoney.ui.transaction.contract.ListTransactionContract;
import com.mrj.morejmoney.ui.transaction.presenter.ListTransactionPresenter;

import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public class ListTransactionView extends ListFragment implements ListTransactionContract.View {

    public static final String TAG = "ListTransactionView";

    public interface ListTransactionListener {
        void addNewTransaction(Bundle bundle);
    }

    private ListTransactionView.ListTransactionListener callback;
    private TransactionAdapter adapter;
    private ListTransactionContract.Presenter presenter;

    private ListView listView;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ProgressDialog progress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new TransactionAdapter(getActivity());
        this.presenter = new ListTransactionPresenter(this);
        setRetainInstance(true);
        progress = new ProgressDialog(getActivity());
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage(getActivity().getResources().getString(R.string.downloading));
        progress.setCancelable(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (ListTransactionListener)activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implements ListTransactionListener");
        }
    }

    public static Fragment newInstance(Bundle bundle){
        ListTransactionView listTransactionView = new ListTransactionView();
        if (bundle != null) {
            listTransactionView.setArguments(bundle);
        }
        return listTransactionView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_list_transaction,container,false);

        fab = rootView.findViewById(R.id.fabTransaction);
        toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        listView = rootView.findViewById(android.R.id.list);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.addNewTransaction(null);
            }
        });
        presenter.loadTransaction();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Transaction.TAG,(Transaction)adapterView.getItemAtPosition(position));
                callback.addNewTransaction(bundle);
            }
        });
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        getListView().setMultiChoiceModeListener(new TransactionMultiChoiceModeListener(presenter));
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                getListView().setItemChecked(position,!presenter.isPositionChecked(position));
                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("");
        getActivity().getMenuInflater().inflate(R.menu.menu_transaction_longclick,menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_activity_transaction, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_order_by_creationDate:
                adapter = new TransactionAdapter(getActivity());
                listView.setAdapter(adapter);
                return true;
            case R.id.action_order_by_totalAmountDESC:
                listView.setAdapter(adapter.orderByAmountDESC());
                return true;
            case R.id.action_order_by_totalAmountASC:
                listView.setAdapter(adapter.orderByAmountASC());
                return true;
            case R.id.action_order_by_creationDateDESC:
                listView.setAdapter(adapter.orderByCreationDateDESC());
                return true;
            case R.id.action_order_by_creationDateASC:
                listView.setAdapter(adapter.orderByCreationDateASC());
                return true;
            default:
                break;
        }
        return false;
    }

    /* implements ListTransactionContract.View */
    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (ListTransactionContract.Presenter) presenter;
    }

    @Override
    public void showTransaction(List<Transaction> list) {
        adapter.clear();
        adapter.addAll(list);
    }

    @Override
    public Transaction getTransaction(Integer position) {
        return adapter.getItem(position);
    }

    @Override
    public String nameSelecteds() {
        return getActivity().getResources().getString(R.string.selecteds);
    }

    @Override
    public void showProgress() {
        progress.show();
    }

    @Override
    public void dismissProgress() {
        progress.dismiss();
    }
    /* implements ListTransactionContract.View */
}
