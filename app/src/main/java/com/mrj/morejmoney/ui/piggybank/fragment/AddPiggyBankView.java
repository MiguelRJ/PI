package com.mrj.morejmoney.ui.piggybank.fragment;

import android.app.DatePickerDialog.OnDateSetListener;
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
import android.widget.DatePicker;
import android.widget.EditText;

import com.mrj.morejmoney.R;
import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.data.prefs.AppPreferencesHelper;
import com.mrj.morejmoney.ui.base.BaseFragment;
import com.mrj.morejmoney.ui.base.BasePresenter;
import com.mrj.morejmoney.ui.piggybank.contract.AddPiggyBankContract;
import com.mrj.morejmoney.ui.piggybank.presenter.AddPiggyBankPresenter;
import com.mrj.morejmoney.ui.utils.AppConstants;
import com.mrj.morejmoney.ui.utils.CommonDatePicker;
import com.mrj.morejmoney.ui.utils.ModeAdd;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      extends BaseFragment
 *      implements AddPiggyBankContract.View
 *      tag
 *      presenter
 *      ModeAdd
 *      public static AddPiggyBankView newInstance(Bundle bundle)
 *
 *
 */

public class AddPiggyBankView extends BaseFragment implements AddPiggyBankContract.View {

    public static final String TAG = "AddPiggyBankView";
    private AddPiggyBankContract.Presenter presenter;

    private PiggyBank piggyBankActual;
    private TextInputLayout tilName;
    private EditText edtDate;
    private Toolbar toolbar;

    static ModeAdd mode;

    public static AddPiggyBankView newInstance(Bundle bundle){
        AddPiggyBankView addPiggyBankView= new AddPiggyBankView();
        mode = new ModeAdd(ModeAdd.MODE_ADD);
        if (bundle != null){
            mode.setMode(ModeAdd.MODE_EDIT);
            addPiggyBankView.setArguments(bundle);
        }
        return addPiggyBankView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AddPiggyBankPresenter(this);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_add_piggybank,container,false);

        toolbar = rootView.findViewById(R.id.toolBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        tilName = rootView.findViewById(R.id.tilName);
        tilName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtDate = rootView.findViewById(R.id.edtDate);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });


        if (getArguments() != null){
            piggyBankActual = getArguments().getParcelable(PiggyBank.TAG);
        } else {
            piggyBankActual = new PiggyBank(
                    -1,
                    Integer.parseInt(String.valueOf(AppPreferencesHelper.getInstance().getCurrentUserId())),
                    "",
                    0,
                    Calendar.getInstance()
            );
        }

        tilName.getEditText().setText(piggyBankActual.getName().toString());
        edtDate.setText(AppConstants.df.format(piggyBankActual.getCreationDate().getTime()));
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
                piggyBankActual.setName(tilName.getEditText().getText().toString().trim());
                presenter.validatePiggyBank(piggyBankActual);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /* implements AddPiggyBankContract.View */
    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (AddPiggyBankContract.Presenter) presenter;
    }

    @Override
    public void showOnSucces() {
        showMessage(getString(R.string.PiggyBankSaved));
        FragmentManager fm = getFragmentManager();
        fm.popBackStack();
    }

    @Override
    public void showNameEmptyError() {
        tilName.setError(getString(R.string.NameEmptyError));
    }

    @Override
    public void showDuplicatedName() {
        tilName.setError(getString(R.string.DuplicatedName));
    }
    /* implements AddPiggyBankContract.View */

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.OnDestroy();
    }

    private void showDatePicker() {
        CommonDatePicker date = new CommonDatePicker();
        Bundle args = new Bundle();
        args.putInt("year", piggyBankActual.getCreationDate().get(Calendar.YEAR));
        args.putInt("month", piggyBankActual.getCreationDate().get(Calendar.MONTH));
        args.putInt("day", piggyBankActual.getCreationDate().get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        date.setCallBack(onDateSetListener);
        date.show(getFragmentManager(), CommonDatePicker.TAG);
    }

    OnDateSetListener onDateSetListener = new OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            try {
                piggyBankActual.getCreationDate().setTime(AppConstants.df.parse(day+"/"+month+"/"+year));
                edtDate.setText(AppConstants.df.format(piggyBankActual.getCreationDate().getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };
}
