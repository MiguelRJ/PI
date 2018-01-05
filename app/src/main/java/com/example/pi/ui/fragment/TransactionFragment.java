package com.example.pi.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.example.pi.ui.about.AboutUsActivity;
import com.example.pi.ui.piggybank.AddPiggyBankActivity;
import com.example.pi.ui.prefs.AccountSettingActivity;

/**
 * Created by
 *
 * @author Miguel Rodriguez Jimenez
 * @date 18/11/17
 * TransactionFragmentListener{}
 * onAttach()
 * onCreateView()
 * onViewCreated()
 * onClick(View v)
 * onDetach()
 * onCreateOptionsMenu()
 * onOptionsItemSelected()
 */

public class TransactionFragment extends Fragment {

    private TransactionFragmentListener mCallBack;
    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private TransactionAdapter.OnItemClickListener listener;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    public interface TransactionFragmentListener {
        void onTransactionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1));
        fab = view.findViewById(R.id.fabTransaction);
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity().getApplicationContext(), AddPiggyBankActivity.class));
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_activity_transaction, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_order_by_creationDate:
                adapter = new TransactionAdapter(listener);
                recyclerView.setAdapter(adapter);
                return true;
            case R.id.action_order_by_totalAmount:
                recyclerView.setAdapter(adapter.orderByAmount());
                return true;
            case R.id.action_aboutus:
                startActivity(new Intent(getActivity().getApplicationContext(), AboutUsActivity.class));
                break;
            case R.id.action_preferences:
                startActivity(new Intent(getActivity().getApplicationContext(), AccountSettingActivity.class));
                break;
            default:
                break;
        }
        return false;
    }

}
