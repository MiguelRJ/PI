package com.example.pi.ui.piggybank.fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.pi.R;
import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.ui.base.BaseFragment;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.piggybank.contract.AddPiggyBankContract;
import com.example.pi.ui.piggybank.presenter.AddPiggyBankPresenter;
import com.example.pi.ui.utils.ModeAdd;

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

    private TextInputLayout tilName;
    private DatePicker dpCreationDate;
    private FloatingActionButton fab;

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

        if (getArguments() != null){
            tilName.getEditText().setText(((PiggyBank)getArguments().getParcelable(PiggyBank.TAG)).getName().toString());
            Calendar c = ((PiggyBank)getArguments().getParcelable(PiggyBank.TAG)).getCreationDate();
            dpCreationDate.updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        }
        return rootView;
    }

    /* implements AddPiggyBankContract.View */
    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (AddPiggyBankContract.Presenter) presenter;
    }

    @Override
    public void showOnSucces() {
        showMessage("Guardado");
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
}
