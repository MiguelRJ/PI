package com.example.pi.ui.piggybank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.pi.R;
import com.example.pi.data.db.model.PiggyBank;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddPiggyBankActivity extends AppCompatActivity {

    private PiggyBank piggyBank;
    private TextView txvName;
    private TextView txvId;
    private TextView txvIdUser;
    private TextView txvTotalAmount;
    private TextView txvCreationDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_piggy_bank);
        piggyBank = getIntent().getExtras().getParcelable("piggybank");
        txvName = findViewById(R.id.txvName);
        txvId = findViewById(R.id.txvId);
        txvIdUser = findViewById(R.id.txvIdUser);
        txvTotalAmount = findViewById(R.id.txvTotalAmount);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        txvCreationDate = findViewById(R.id.txvCreationDate);

        txvName.setText(piggyBank.getName().toString());
        txvId.setText(String.valueOf(piggyBank.getId()));
        txvIdUser.setText(String.valueOf(piggyBank.getIdUser()));
        txvTotalAmount.setText(String.valueOf(piggyBank.getTotalAmount()));
        txvCreationDate.setText(df.format(Calendar.getInstance().getTime()));
    }
}
