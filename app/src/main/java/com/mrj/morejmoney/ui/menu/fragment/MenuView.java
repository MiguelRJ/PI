package com.mrj.morejmoney.ui.menu.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import com.mrj.morejmoney.R;
import com.mrj.morejmoney.adapter.PiggyBankAdapterCardView;
import com.mrj.morejmoney.adapter.TransactionAdapterCardView;
import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.ui.base.BaseFragment;
import com.mrj.morejmoney.ui.base.BasePresenter;
import com.mrj.morejmoney.ui.menu.contract.MenuContract;
import com.mrj.morejmoney.ui.menu.presenter.MenuPresenter;
import com.mrj.morejmoney.ui.utils.ComonDialog;

/**
 * Created by Miguel on 25/02/2018.
 */

public class MenuView extends BaseFragment implements MenuContract.View {

    public static final String TAG = "MenuView";

    public interface MenuListener {
        void loadAllPiggyBank();
        void addNewPiggyBank(Bundle bundle);
        void loadAllTransaction();
        void addNewTransaction(Bundle bundle);
        void aboutUs();
        void preferences();
    }

    private MenuListener callback;
    private PiggyBankAdapterCardView adapterPB;
    private TransactionAdapterCardView adapterT;
    private MenuPresenter presenter;
    private PiggyBankAdapterCardView.OnItemClickListener listenerPB;
    private TransactionAdapterCardView.OnItemClickListener listenerT;

    private RecyclerView rwPiggyBank, rwTransaction;
    private Button btnAddPiggyBank, btnMorePiggyBank, btnMoreTransaction;
    private FloatingActionButton fabAddTransaction;
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
        listenerPB = new PiggyBankAdapterCardView.OnItemClickListener() {
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
        listenerT = new TransactionAdapterCardView.OnItemClickListener() {
            @Override
            public void OnItemClick(Transaction transaction) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Transaction.TAG,transaction);
                callback.addNewTransaction(bundle);
            }

            @Override
            public void OnItemLongClick(Transaction transaction) {
                Bundle bundle = new Bundle();
                bundle.putString(ComonDialog.TITTLE, getActivity().getResources().getString(R.string.titleDeleteTransaction));
                bundle.putString(ComonDialog.MESSAGE, getActivity().getResources().getString(R.string.messageDeleteTransaction) + "\n " + String.valueOf(transaction.getAmount()));
                bundle.putString(ComonDialog.TAG, Transaction.TAG);
                bundle.putParcelable(Transaction.TAG, transaction);
                Dialog dialog = ComonDialog.showConfirmDialog(bundle, getActivity(), presenter, MenuPresenter.DELETE_TRANSACTION);
                dialog.show();
            }
        };
        this.adapterPB = new PiggyBankAdapterCardView(getActivity(),listenerPB);
        this.adapterT = new TransactionAdapterCardView(getActivity(),listenerT);
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

        btnMoreTransaction = view.findViewById(R.id.btnMoreTransaction);
        btnMoreTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.loadAllTransaction();
            }
        });
        fabAddTransaction = view.findViewById(R.id.fabAddTransaction);
        fabAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.addNewTransaction(null);
            }
        });

        rwPiggyBank = view.findViewById(R.id.rwPiggyBanks);
        rwPiggyBank.setHasFixedSize(true);
        rwPiggyBank.setLayoutManager(new GridLayoutManager(getActivity(),3));

        rwTransaction = view.findViewById(R.id.rwTransactions);
        rwTransaction.setHasFixedSize(true);
        rwTransaction.setLayoutManager(new GridLayoutManager(getActivity(),1));

        adapterPB = new PiggyBankAdapterCardView(getActivity(), listenerPB);
        adapterT = new TransactionAdapterCardView(getActivity(),listenerT);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rwPiggyBank.setAdapter(adapterPB);
        rwTransaction.setAdapter(adapterT);
    }

    @Override
    public void onDeleted() {
        showMessage(getString(R.string.Deleted));
        adapterPB = new PiggyBankAdapterCardView(getActivity(), listenerPB);
        adapterT = new TransactionAdapterCardView(getActivity(),listenerT);
        rwPiggyBank.setAdapter(adapterPB);
        rwTransaction.setAdapter(adapterT);
    }

    @Override
    public void onSuccess() {
        showMessage(getString(R.string.onSuccess));
        adapterPB = new PiggyBankAdapterCardView(getActivity(), listenerPB);
        adapterT = new TransactionAdapterCardView(getActivity(), listenerT);
        rwPiggyBank.setAdapter(adapterPB);
        rwTransaction.setAdapter(adapterT);
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
                callback.aboutUs();
                break;
            case R.id.action_preferences:
                callback.preferences();
                break;
            default:
                break;
        }
        return false;
    }
}
