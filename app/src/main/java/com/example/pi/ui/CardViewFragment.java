package com.example.pi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pi.R;
import com.example.pi.adapter.ListPiggyBankAdapter;
import com.example.pi.ui.base.BaseFragment;

/**
 * Created by Miguel on 23/02/2018.
 */

public class CardViewFragment extends BaseFragment {

    private Button btnAddPiggyBank;
    private RecyclerView rwPiggyBanks;
    private ListPiggyBankAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ListPiggyBankAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_cardview_piggybanks,container,false);

        btnAddPiggyBank = rootView.findViewById(R.id.btnAddPiggyBank);

        rwPiggyBanks = rootView.findViewById(R.id.rwPiggyBanks);
        rwPiggyBanks.setHasFixedSize(true);
        //rwPiggyBanks.setNestedScrollingEnabled(true);

        rwPiggyBanks.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rwPiggyBanks.setAdapter(adapter);
    }
}
