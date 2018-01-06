package com.example.pi.ui.piggybank.fragment;

import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.pi.R;
import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.ui.base.BaseFragment;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.piggybank.contract.AddPiggyBankContract;
import com.example.pi.ui.piggybank.presenter.AddPiggyBankPresenter;
import com.example.pi.ui.utils.AppConstants;
import com.example.pi.ui.utils.CommonDatePicker;
import com.example.pi.ui.utils.ModeAdd;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    private int id;
    private int idUser;
    private TextInputLayout tilName;
    private EditText edtDate;
    private Toolbar toolbar;
    private Calendar calendar;

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
            id = piggyBankActual.getId();
            idUser = piggyBankActual.getIdUser();
            tilName.getEditText().setText(piggyBankActual.getName().toString());
            calendar = piggyBankActual.getCreationDate();
        } else {
            id = -1;
            idUser = -1;
            calendar = Calendar.getInstance();
        }

        edtDate.setText(AppConstants.df.format(calendar.getTime()));
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
                presenter.validatePiggyBank(id,idUser,tilName.getEditText().getText().toString(), (GregorianCalendar) calendar);
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
        FragmentManager fm = getActivity().getSupportFragmentManager();
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
        args.putInt("year", calendar.get(Calendar.YEAR));
        args.putInt("month", calendar.get(Calendar.MONTH));
        args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        date.setCallBack(onDateSetListener);
        date.show(getFragmentManager(), CommonDatePicker.TAG);
    }

    OnDateSetListener onDateSetListener = new OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            calendar = new GregorianCalendar(year,month,day);
            edtDate.setText(AppConstants.df.format(calendar.getTime()));
        }
    };
}
