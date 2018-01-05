package com.example.pi.ui.transaction.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pi.R;
import com.example.pi.adapter.TransactionAdapter;
import com.example.pi.data.db.model.Transaction;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.transaction.contract.ListTransactionContract;
import com.example.pi.ui.transaction.presenter.ListTransactionPresenter;

import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public class ListTransactionView extends Fragment implements ListTransactionContract.View {

    public static final String TAG = "ListTransactionView";
    public interface ListTransactionListener {
        void addNewSector(Bundle bundle);
    }
    private ListTransactionView.ListTransactionListener callback;
    private TransactionAdapter adapter;
    private ListTransactionContract.Presenter presenter;

    private RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new TransactionAdapter();
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
        FloatingActionButton fab = rootView.findViewById(R.id.fabTransaction);
        toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);

        setHasOptionsMenu(true);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.addNewSector(null);
            }
        });
        presenter.loadTransaction();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setAdapter(adapter);
    }

    /* implements ListTransactionContract.View */
    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (ListTransactionContract.Presenter) presenter;
    }

    @Override
    public void showTransaction(List<Transaction> list) {

    }
    /* implements ListTransactionContract.View */
}
