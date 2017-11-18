package com.example.pi.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pi.R;
import com.example.pi.adapter.PiggyBankAdapter;
import com.example.pi.data.db.model.PiggyBank;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 18/11/17
 *      FastPayFragmentListener{}
 *      onAttach()
 *      onCreateView()
 *      onViewCreated()
 *          onClick(View v)
 *      onDetach()
 */

public class PiggyBankFragment extends Fragment {

    private PiggyBankFragmentListener mCallBack;
    public PiggyBankAdapter adapter;
    private Toolbar toolbar;
    private ListView listView;
    private FloatingActionButton fab;
    //private CoordinatorLayout coordinatorLayout;

    public interface PiggyBankFragmentListener {
        void onPiggyBankFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mCallBack = (PiggyBankFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FastPayFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_piggybank,container,false);
        listView = view.findViewById(R.id.listView);
        fab = view.findViewById(R.id.fab);
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }
        adapter = new PiggyBankAdapter(getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(PiggyBankActivity.this,AddPiggiBankActivity.class));
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
        inflater.inflate(R.menu.menu_activity_piggybank,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_order_by_name:
                adapter = new PiggyBankAdapter(getActivity());
                listView.setAdapter(adapter);
                return true;
            case R.id.action_order_by_totalAmount:
                listView.setAdapter(adapter.orderByTotalAmount());
                return true;
            case R.id.action_order_by_creationDate:
                listView.setAdapter(adapter.orderByCreationDate());
                return true;
            default:
                break;
        }
        return false;
    }
}
/*
@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_order_by_name:
                adapter = new PiggyBankAdapter(this);
                listView.setAdapter(adapter);
                break;
            case R.id.action_order_by_totalAmount:
                listView.setAdapter(adapter.orderByTotalAmount());
                break;
            case R.id.action_order_by_creationDate:
                listView.setAdapter(adapter.orderByCreationDate());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
 */
