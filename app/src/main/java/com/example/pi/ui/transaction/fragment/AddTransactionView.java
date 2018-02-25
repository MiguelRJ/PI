package com.example.pi.ui.transaction.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.FragmentManager;
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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import com.example.pi.R;
import com.example.pi.adapter.PiggyBankAdapterSpinner;
import com.example.pi.data.model.PiggyBank;
import com.example.pi.data.model.Transaction;
import com.example.pi.data.prefs.AppPreferencesHelper;
import com.example.pi.ui.base.BaseFragment;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.transaction.contract.AddTransactionContract;
import com.example.pi.ui.transaction.presenter.AddTransactionPresenter;
import com.example.pi.ui.utils.AppConstants;
import com.example.pi.ui.utils.CommonDatePicker;
import com.example.pi.ui.utils.CommonTimePicker;
import com.example.pi.ui.utils.ModeAdd;

import java.util.ArrayList;
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
    private PiggyBankAdapterSpinner adapterSpinner;

    private Transaction transactionActual;
    private RadioButton rbPayment, rbDeposit;
    private EditText edtAmount;
    private TextInputLayout tilComment;
    private EditText edtDate, edtTime;
    private Toolbar toolbar;
    private Spinner spnPiggyBank;
    private Calendar calendar;

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
        adapterSpinner = new PiggyBankAdapterSpinner(getActivity());
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
        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString();
                int posDot = temp.indexOf(".");

                if (posDot <= 0) {
                    return;
                }
                if (temp.length() - posDot - 1 > 2) {
                    s.delete(posDot + 3, posDot + 4);
                }
            }
        });

        edtDate = rootView.findViewById(R.id.edtDate);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        edtTime = rootView.findViewById(R.id.edtTime);
        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        spnPiggyBank = rootView.findViewById(R.id.spnPiggyBank);

        if (getArguments() != null){
            transactionActual = getArguments().getParcelable(Transaction.TAG);
        } else {
            transactionActual = new Transaction(
                    -1,
                    Integer.parseInt(String.valueOf(AppPreferencesHelper.getInstance().getCurrentUserId())),
                    0,
                    0,
                    false,
                    0,
                    Calendar.getInstance(),
                    "",
                    0,
                    0,
                    null
            );
        }

        if (transactionActual.isPayment()){
            rbPayment.setChecked(true);
        } else {
            rbDeposit.setChecked(true);
        }
        edtAmount.setText(AppConstants.decimalformat.format(transactionActual.getAmount()).replace(",","."));
        tilComment.getEditText().setText(transactionActual.getComment());
        calendar = transactionActual.getCreationDate();
        edtDate.setText(AppConstants.df.format(calendar.getTime()));
        edtTime.setText(AppConstants.tf.format(calendar.getTime()));

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadPiggyBank();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_action_save,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                transactionActual.setIdPiggyBank(((PiggyBank)spnPiggyBank.getSelectedItem()).getId());
                if (rbPayment.isChecked()){
                    transactionActual.setPayment(true);
                } else {
                    transactionActual.setPayment(false);
                }
                if (edtAmount.getText().toString().isEmpty()){
                    transactionActual.setAmount(Double.NaN);
                } else {
                    transactionActual.setAmount(Double.parseDouble(edtAmount.getText().toString()));
                }
                transactionActual.setComment(tilComment.getEditText().getText().toString());
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

    @Override
    public void onAmountEmptyError() {
        showMessage(getString(R.string.errorAmountEmpty));
    }

    @Override
    public void showPiggyBankOnSpinner(ArrayList<PiggyBank> piggyBanks) {
        adapterSpinner.clear();
        adapterSpinner.addAll(piggyBanks);
        spnPiggyBank.setAdapter(adapterSpinner);
        spnPiggyBank.setSelection(transactionActual.getIdPiggyBank()-1);
    }
    /* implements AddTransactionContract.View */

    private void showDatePicker() {
        CommonDatePicker date = new CommonDatePicker();
        Bundle args = new Bundle();
        args.putInt("year", calendar.get(Calendar.YEAR));
        args.putInt("month", calendar.get(Calendar.MONTH));
        args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        date.setCallBack(onDateSetListener);
        date.show(getFragmentManager(), CommonDatePicker.TAG);
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            calendar = new GregorianCalendar(year,month,day);
            edtDate.setText(AppConstants.df.format(calendar.getTime()));
        }
    };

    private void showTimePicker() {
        CommonTimePicker time = new CommonTimePicker();
        Bundle args = new Bundle();
        args.putInt("year", calendar.get(Calendar.YEAR));
        args.putInt("month", calendar.get(Calendar.MONTH));
        args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
        time.setArguments(args);
        time.setCallBack(onTimeSetListener);
        time.show(getFragmentManager(), CommonTimePicker.TAG);
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            edtTime.setText(AppConstants.tf.format(calendar.getTime()));
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            calendar.set(year,month,day);
            edtDate.setText(AppConstants.df.format(calendar.getTime()));
        }
    };
}
