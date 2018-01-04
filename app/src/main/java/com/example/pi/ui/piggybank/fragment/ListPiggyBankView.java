package com.example.pi.ui.piggybank.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.pi.R;
import com.example.pi.adapter.PiggyBankAdapter;
import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.piggybank.ViewPiggyBankActivity;
import com.example.pi.ui.piggybank.contract.ListPiggyBankContract;
import com.example.pi.ui.piggybank.presenter.ListPiggyBankPresenter;

import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      Interface methods...
 *      tag
 *      interface ListPiggyBankListener
 *      listener, adapter, presenter, listview, toolbar
 *      onCreate(@Nullable Bundle savedInstanceState)
 *          this.adapter = new Adapter(getActivity())
 *          this.presenter = new ListPiggyBankPresenter(this);
 *          setRetainInstance(true);
 *      public void onAttach(Activity activity)
 *      public static Fragment newInstance(Bundle bundle)
 */

public class ListPiggyBankView extends ListFragment implements ListPiggyBankContract.View {

    public static String TAG = "ListPiggyBankView";

    public interface ListPiggyBankListener {
        void addNewPiggyBank(Bundle bundle);
    }

    private ListPiggyBankListener callback;
    private PiggyBankAdapter adapter;
    private ListPiggyBankContract.Presenter presenter;

    private ListView listView;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new PiggyBankAdapter(getActivity());
        this.presenter = new ListPiggyBankPresenter(this);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (ListPiggyBankListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must be implements ListPiggyBankListener");
        }
    }

    public static Fragment newInstance(Bundle bundle){
        ListPiggyBankView listPiggyBank = new ListPiggyBankView();
        if (bundle != null) {
            listPiggyBank.setArguments(bundle);
        }
        return listPiggyBank;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_piggybank,container,false);

        listView = rootView.findViewById(android.R.id.list);
        toolbar = rootView.findViewById(R.id.toolbar);
        fab = rootView.findViewById(R.id.fabPiggyBank);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.addNewPiggyBank(null);
            }
        });

        presenter.loadPiggyBank();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(PiggyBank.TAG,(PiggyBank)adapterView.getItemAtPosition(position));
                callback.addNewPiggyBank(bundle);
            }
        });
    }

    /* implements ListPiggyBankContract.View */
    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (ListPiggyBankContract.Presenter) presenter;
    }

    @Override
    public void showPiggyBank(List<PiggyBank> list) {
        adapter.clear();
        adapter.addAll(list);
    }

    @Override
    public PiggyBank getPiggyBank(Integer position) {
        return adapter.getItem(position);
    }
    /* implements ListPiggyBankContract.View */
}
