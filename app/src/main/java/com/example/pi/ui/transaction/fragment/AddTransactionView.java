package com.example.pi.ui.transaction.fragment;

import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import com.example.pi.R;
import com.example.pi.data.db.model.Transaction;
import com.example.pi.ui.base.BaseFragment;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.transaction.contract.AddTransactionContract;
import com.example.pi.ui.transaction.presenter.AddTransactionPresenter;
import com.example.pi.ui.utils.ModeAdd;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 06/01/18
 *
 */

public class AddTransactionView extends BaseFragment implements AddTransactionContract.View {

    public static final String TAG = "AddTransactionView";
    private AddTransactionContract.Presenter presenter;

    private Transaction transactionActual;
    private int id;
    private int idUser;
    private int idEstablishment;
    private RadioButton rbPayment, rbDeposit;
    private EditText edtAmount;
    private TextInputLayout tilComment;
    private DatePicker dpDate;
    private TimePicker tmTime;
    private Toolbar toolbar;

    static ModeAdd mode;

    public static AddTransactionView newInstance(Bundle bundle){
        AddTransactionView addTransactionView = new AddTransactionView();
        mode = new ModeAdd(ModeAdd.MODE_ADD);
        if (bundle != null){
            mode.setMode(ModeAdd.MODE_EDIT);
            addTransactionView.setArguments(bundle);
        }
        return addTransactionView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AddTransactionPresenter(this);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_add_transaction,container,false);

        toolbar = rootView.findViewById(R.id.toolBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        tilComment = rootView.findViewById(R.id.tilComment);
        tilComment.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilComment.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        rbPayment = rootView.findViewById(R.id.rbPayment);
        rbDeposit = rootView.findViewById(R.id.rbDeposit);
        edtAmount = rootView.findViewById(R.id.edtAmount);
        dpDate = rootView.findViewById(R.id.dpDate);
        tmTime = rootView.findViewById(R.id.tmTime);

        if (getArguments() != null){
            transactionActual = getArguments().getParcelable(Transaction.TAG);
            id = transactionActual.getId();
            idUser = transactionActual.getIdUser();
            idEstablishment = transactionActual.getIdEstablishment();
            if (transactionActual.isPayment()){
                rbPayment.setChecked(true);
            } else {
                rbDeposit.setChecked(true);
            }
            edtAmount.setText(String.valueOf(transactionActual.getAmount()));
            tilComment.getEditText().setText(transactionActual.getComment());
            Calendar c = transactionActual.getCreationDate();
            dpDate.updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tmTime.setHour(c.get(Calendar.HOUR));
                tmTime.setMinute(c.get(Calendar.MINUTE));
            }

        } else {
            id = -1;
            idUser = -1;
            idEstablishment = -1;
        }

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_action_save,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                transactionActual.setId(id);
                transactionActual.setIdUser(idUser);
                transactionActual.setIdEstablishment(idEstablishment);
                if (rbPayment.isChecked()){
                    transactionActual.setPayment(true);
                } else {
                    transactionActual.setPayment(false);
                }
                transactionActual.setAmount(Double.parseDouble(edtAmount.getText().toString()));
                transactionActual.setComment(tilComment.getEditText().getText().toString());
                GregorianCalendar calendar = new GregorianCalendar(dpDate.getYear(),dpDate.getMonth(),dpDate.getDayOfMonth());
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    calendar = new GregorianCalendar(dpDate.getYear(),dpDate.getMonth(),dpDate.getDayOfMonth(),tmTime.getHour(),tmTime.getMinute());
                }
                transactionActual.setCreationDate(calendar);
                presenter.validateTransaction(transactionActual);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /* implements AddTransactionContract.View */
    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (AddTransactionContract.Presenter)presenter;
    }

    @Override
    public void showOnSuccess() {
        showMessage(getString(R.string.TransactionSaved));
        FragmentManager fm = getFragmentManager();
        fm.popBackStack();
    }
    /* implements AddTransactionContract.View */
}
