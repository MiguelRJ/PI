package com.example.pi.ui.piggybank.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Build;
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
import com.example.pi.R;
import com.example.pi.adapter.PiggyBankAdapter;
import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.data.db.model.Transaction;
import com.example.pi.ui.about.AboutUsActivity;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.piggybank.PiggyBankMultiChoiceModeListener;
import com.example.pi.ui.piggybank.contract.ListPiggyBankContract;
import com.example.pi.ui.piggybank.presenter.ListPiggyBankPresenter;
import com.example.pi.ui.prefs.AccountSettingActivity;
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
 *      public View onCreateView()
 *      public void onViewCreated()
 *      public void onCreateOptionsMenu()
 *      public boolean onOptionsItemSelected()
 */

public class ListPiggyBankView extends Fragment implements ListPiggyBankContract.View {

    public static final String TAG = "ListPiggyBankView";

    public interface ListPiggyBankListener {
        void addNewPiggyBank(Bundle bundle);
    }

    private ListPiggyBankListener callback;
    private PiggyBankAdapter adapter;
    private PiggyBankAdapter.OnItemClickListener listener;
    private ListPiggyBankContract.Presenter presenter;

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listener = new PiggyBankAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(PiggyBank piggyBank) {

            }

            @Override
            public void OnItemLongClick(PiggyBank piggyBank) {

            }
        };
        this.adapter = new PiggyBankAdapter(listener);
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
        View rootView = inflater.inflate(R.layout.view_list_piggybank,container,false);

        toolbar = rootView.findViewById(R.id.toolbar);
        fab = rootView.findViewById(R.id.fabPiggyBank);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        recyclerView = rootView.findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

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
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_activity_piggybank,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_order_by_name:
                adapter = new PiggyBankAdapter(listener);
                recyclerView.setAdapter(adapter);
                return true;
            case R.id.action_order_by_totalAmount:
                recyclerView.setAdapter(adapter.orderByTotalAmount());
                return true;
            case R.id.action_order_by_creationDate:
                recyclerView.setAdapter(adapter.orderByCreationDate());
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

    /* implements ListPiggyBankContract.View */
    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (ListPiggyBankContract.Presenter) presenter;
    }

    @Override
    public void showPiggyBank(List<PiggyBank> list) {

    }
    /* implements ListPiggyBankContract.View */

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.OnDestroy();
        adapter = null;
    }
}
