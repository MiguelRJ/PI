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
import android.widget.ListView;

import com.example.pi.R;
import com.example.pi.adapter.PiggyBankAdapter;
import com.example.pi.ui.piggybank.AddPiggyBankActivity;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 18/11/17
 *      PiggyBankNoAppBarFragmentListener{}
 *      onAttach()
 *      onCreateView()
 *      onViewCreated()
 *          onClick(View v)
 *      onDetach()
 *      onCreateOptionsMenu()
 *      onOptionsItemSelected()
 */

public class PiggyBankNoAppBarFragment extends Fragment {

    private PiggyBankNoAppBarFragmentListener mCallBack;
    private PiggyBankAdapter adapter;
    private ListView listView;
    private FloatingActionButton fab;

    public interface PiggyBankNoAppBarFragmentListener {
        void onPiggyBankNoAppBarFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mCallBack = (PiggyBankNoAppBarFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement PiggyBankNoAppBarFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_piggybanknoappbar,container,false);
        listView = view.findViewById(R.id.listView);
        fab = view.findViewById(R.id.fab);
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
                startActivity(new Intent(getActivity().getApplicationContext(), AddPiggyBankActivity.class));
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

}