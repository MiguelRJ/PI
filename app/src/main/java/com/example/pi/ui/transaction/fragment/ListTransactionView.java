package com.example.pi.ui.transaction.fragment;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import com.example.pi.R;
import com.example.pi.adapter.TransactionAdapter;
import com.example.pi.data.db.model.Transaction;
import com.example.pi.ui.about.AboutUsActivity;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.prefs.AccountSettingActivity;
import com.example.pi.ui.transaction.TransactionMultiChoiceModeListener;
import com.example.pi.ui.transaction.contract.ListTransactionContract;
import com.example.pi.ui.transaction.presenter.ListTransactionPresenter;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new TransactionAdapter(getActivity());
        this.presenter = new ListTransactionPresenter(this);
        setRetainInstance(true);
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
            case R.id.action_order_by_totalAmount:
                listView.setAdapter(adapter.orderByAmount());
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
    /* implements ListTransactionContract.View */
}
