package com.example.pi.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pi.R;
import com.example.pi.adapter.PiggyBankAdapter;
import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.ui.piggybank.AddPiggyBankActivity;
import com.example.pi.ui.piggybank.ViewPiggyBankActivity;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 18/11/17
 *      PiggyBankFragmentListener{}
 *      onAttach()
 *      onCreateView()
 *      onViewCreated()
 *          onClick(View v)
 *      onDetach()
 *      onCreateOptionsMenu()
 *      onOptionsItemSelected()
 */

public class PiggyBankFragment extends Fragment {

    private PiggyBankFragmentListener mCallBack;
    private PiggyBankAdapter adapter;
    private Toolbar toolbar;
    private ListView listView;
    private FloatingActionButton fab;

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
        fab = view.findViewById(R.id.fabPiggyBank);
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }
        adapter = new PiggyBankAdapter(getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);
                PiggyBank pb = (PiggyBank) listItem;
                //Toast.makeText(getActivity().getApplicationContext(),pb.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ViewPiggyBankActivity.class);
                intent.putExtra("piggybank",pb);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), AddPiggyBankActivity.class));
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