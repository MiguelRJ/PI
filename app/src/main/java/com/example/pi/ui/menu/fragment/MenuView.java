package com.example.pi.ui.menu.fragment;

import android.app.Activity;
import android.app.Dialog;
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
import com.example.pi.adapter.PiggyBankAdapter;
import com.example.pi.data.model.PiggyBank;
import com.example.pi.ui.base.BaseFragment;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.menu.contract.MenuContract;
import com.example.pi.ui.menu.presenter.MenuPresenter;
import com.example.pi.ui.piggybank.presenter.ListPiggyBankPresenter;
import com.example.pi.ui.utils.ComonDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 25/02/2018.
 */

public class MenuView extends BaseFragment implements MenuContract.View {

    public static final String TAG = "MenuView";

    public interface MenuListener {
        void loadAllPiggyBank();
        void addNewPiggyBank(Bundle bundle);
    }

    private MenuListener callback;
    private ListPiggyBankAdapter adapter;
    private MenuPresenter presenter;
    private ListPiggyBankAdapter.OnItemClickListener listener;

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
        listener = new ListPiggyBankAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(PiggyBank piggyBank) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(PiggyBank.TAG,piggyBank);
                callback.addNewPiggyBank(bundle);
            }

            @Override
            public void OnItemLongClick(PiggyBank piggyBank) {
                if (presenter.existsAnyTransactionWithPiggyBankID(piggyBank.getId())) {
                    Bundle bundle = new Bundle();
                    bundle.putString(ComonDialog.TITTLE, getActivity().getResources().getString(R.string.titlePiggyBankNotEmpty));
                    bundle.putString(ComonDialog.MESSAGE, getActivity().getResources().getString(R.string.messagePiggyBankDeleteAll) + " '" + piggyBank.getName()+"'?");
                    bundle.putString(ComonDialog.TAG, PiggyBank.TAG);
                    bundle.putParcelable(PiggyBank.TAG, piggyBank);
                    Dialog dialog = ComonDialog.showConfirmDialog(bundle, getActivity(), presenter, MenuPresenter.DELETE_ALL);
                    dialog.show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString(ComonDialog.TITTLE, getActivity().getResources().getString(R.string.titleDeletePiggyBank));
                    bundle.putString(ComonDialog.MESSAGE, getActivity().getResources().getString(R.string.messageDeletePiggyBank) + " " + piggyBank.getName());
                    bundle.putString(ComonDialog.TAG, PiggyBank.TAG);
                    bundle.putParcelable(PiggyBank.TAG, piggyBank);
                    Dialog dialog = ComonDialog.showConfirmDialog(bundle, getActivity(), presenter, MenuPresenter.DELETE);
                    dialog.show();
                }
            }
        };
        this.adapter = new ListPiggyBankAdapter(getActivity(),listener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_menu, container,false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        btnAddPiggyBank = view.findViewById(R.id.btnAddPiggyBank);
        btnAddPiggyBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.addNewPiggyBank(null);
            }
        });
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
        adapter = new ListPiggyBankAdapter(getActivity(),listener);
        rwPiggyBank.setAdapter(adapter);
    }

    @Override
    public void onDeletedPiggyBank() {
        showMessage(getString(R.string.PiggyBankDeleted));
        adapter = new ListPiggyBankAdapter(getActivity(), listener);
        rwPiggyBank.setAdapter(adapter);
    }

    @Override
    public void onSuccess() {
        showMessage(getString(R.string.onSuccess));
        adapter = new ListPiggyBankAdapter(getActivity(), listener);
        rwPiggyBank.setAdapter(adapter);
    }

    @Override
    public void onError() {
        showMessage(getString(R.string.onError));
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
