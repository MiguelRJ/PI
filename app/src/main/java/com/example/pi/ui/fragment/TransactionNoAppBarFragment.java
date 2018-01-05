package com.example.pi.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pi.R;
import com.example.pi.adapter.TransactionAdapter;
import com.example.pi.data.db.model.Transaction;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 18/11/17
 *      TransactionFragmentListener{}
 *      onAttach()
 *      onCreateView()
 *      onViewCreated()
 *          onClick(View v)
 *      onDetach()
 *      onCreateOptionsMenu()
 *      onOptionsItemSelected()
 */

public class TransactionNoAppBarFragment extends Fragment {

    private TransactionNoAppBarFragmentListener mCallBack;
    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private TransactionAdapter.OnItemClickListener listener;

    public interface TransactionNoAppBarFragmentListener {
        void onTransactionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_transactionnoappbar,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),1));
        setHasOptionsMenu(true);
        listener = new TransactionAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Transaction transaction) {
                Toast.makeText(getActivity(),String.valueOf(transaction.getAmount()),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(Transaction transaction) {
                Toast.makeText(getActivity(),transaction.getComment(),Toast.LENGTH_SHORT).show();
            }
        };

        if (savedInstanceState != null) {
            adapter = new TransactionAdapter(listener);
        } else {
            adapter = new TransactionAdapter(listener);
        }
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

}
