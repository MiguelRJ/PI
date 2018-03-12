package com.mrj.morejmoney.ui.piggybank.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
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

import com.mrj.morejmoney.R;
import com.mrj.morejmoney.adapter.PiggyBankAdapter;
import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.ui.base.BaseFragment;
import com.mrj.morejmoney.ui.base.BasePresenter;
import com.mrj.morejmoney.ui.piggybank.contract.ListPiggyBankContract;
import com.mrj.morejmoney.ui.piggybank.presenter.ListPiggyBankPresenter;
import com.mrj.morejmoney.ui.utils.ComonDialog;

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

public class ListPiggyBankView extends BaseFragment implements ListPiggyBankContract.View {

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
    private ProgressDialog progress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listener = new PiggyBankAdapter.OnItemClickListener() {
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
                    Dialog dialog = ComonDialog.showConfirmDialog(bundle, getActivity(), presenter, ListPiggyBankPresenter.DELETE_ALL);
                    dialog.show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString(ComonDialog.TITTLE, getActivity().getResources().getString(R.string.titleDeletePiggyBank));
                    bundle.putString(ComonDialog.MESSAGE, getActivity().getResources().getString(R.string.messageDeletePiggyBank) + " " + piggyBank.getName());
                    bundle.putString(ComonDialog.TAG, PiggyBank.TAG);
                    bundle.putParcelable(PiggyBank.TAG, piggyBank);
                    Dialog dialog = ComonDialog.showConfirmDialog(bundle, getActivity(), presenter, ListPiggyBankPresenter.DELETE);
                    dialog.show();
                }
            }
        };
        this.adapter = new PiggyBankAdapter(listener);
        //this.presenter = new ListPiggyBankPresenter(this);
        setRetainInstance(true);
        progress = new ProgressDialog(getActivity());
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage(getActivity().getResources().getString(R.string.downloading));
        progress.setCancelable(false);
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
        adapter = new PiggyBankAdapter(listener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDeletedPiggyBank() {
        showMessage(getString(R.string.PiggyBankDeleted));
        adapter = new PiggyBankAdapter(listener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        progress.show();
    }

    @Override
    public void dismissProgress() {
        progress.dismiss();
    }

    @Override
    public void onSuccess() {
        showMessage(getString(R.string.onSuccess));
        adapter = new PiggyBankAdapter(listener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onError() {
        showMessage(getString(R.string.onError));
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
