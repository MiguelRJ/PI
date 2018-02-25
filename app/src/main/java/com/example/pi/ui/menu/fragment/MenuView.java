package com.example.pi.ui.menu.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.Button;

import com.example.pi.R;
import com.example.pi.adapter.ListPiggyBankAdapter;
import com.example.pi.data.model.PiggyBank;
import com.example.pi.ui.base.BaseFragment;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.menu.contract.MenuContract;
import com.example.pi.ui.menu.presenter.MenuPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 25/02/2018.
 */

public class MenuView extends BaseFragment implements MenuContract.View {

    public static final String TAG = "MenuView";

    public interface MenuListener {
        void loadAllPiggyBank();
    }

    private MenuListener callback;
    private ListPiggyBankAdapter adapter;
    private MenuPresenter presenter;

    private RecyclerView rwPiggyBank;
    private Button btnAddPiggyBank;
    private Button btnMorePiggyBank;
    private Toolbar toolbar;

    public static Fragment newInstance(Bundle bundle){
        MenuView listPiggyBank = new MenuView();
        if (bundle != null) {
            listPiggyBank.setArguments(bundle);
        }
        return listPiggyBank;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (MenuView.MenuListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must be implements MenuListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_menu, container,false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        btnAddPiggyBank = view.findViewById(R.id.btnAddPiggyBank);
        btnMorePiggyBank = view.findViewById(R.id.btnMorePiggyBank);
        btnMorePiggyBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.loadAllPiggyBank();
            }
        });
        rwPiggyBank = view.findViewById(R.id.rwPiggyBanks);
        rwPiggyBank.setHasFixedSize(true);
        rwPiggyBank.setLayoutManager(new GridLayoutManager(getActivity(),3));

        presenter.loadPiggyBank();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showPiggyBank(ArrayList<PiggyBank> list) {
        adapter = new ListPiggyBankAdapter(getActivity(),list);
        rwPiggyBank.setAdapter(adapter);
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (MenuPresenter) presenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_activity,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_aboutus:
                //startActivity(new Intent(MenuActivity.this, AboutUsActivity.class));
                break;
            case R.id.action_preferences:
                //startActivity(new Intent(MenuActivity.this, AccountSettingActivity.class));
                break;
            default:
                break;
        }
        return false;
    }
}
