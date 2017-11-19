package com.example.pi.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.pi.R;
import com.example.pi.adapter.TransactionAdapter;
import com.example.pi.data.db.model.Transaction;
import com.example.pi.data.db.repository.TransactionRepository;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 18/11/17
 *      FastPayFragmentListener{}
 *      onAttach()
 *      onCreateView()
 *      onViewCreated()
 *          onClick(View v)
 *      onDetach()
 */

public class FastPayFragment extends Fragment {

    private FastPayFragmentListener mCallBack;
    private TextInputEditText edtComment;
    private CheckBox chkImage;
    private CheckBox chk;
    private CheckBox chkLocation;
    private TextInputEditText edtAmount;
    private Button btnFastPay;
    private TransactionRepository transactionRepository;

    public interface FastPayFragmentListener {
        void onFastPayFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mCallBack = (FastPayFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FastPayFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fastpay,container,false);
        edtComment = view.findViewById(R.id.edtComment);
        chkImage = view.findViewById(R.id.chkImage);
        chk = view.findViewById(R.id.chk);
        chkLocation = view.findViewById(R.id.chkLocation);
        edtAmount = view.findViewById(R.id.edtAmount);
        btnFastPay = view.findViewById(R.id.btnFastPay);
        transactionRepository = new TransactionRepository();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnFastPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //transactionRepository.addTransaction(new Transaction(5,1,0,-1,true,Double.parseDouble(edtAmount.getText().toString()),edtComment.getText().toString(),-1, -1,new byte[]{}));
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }
}
