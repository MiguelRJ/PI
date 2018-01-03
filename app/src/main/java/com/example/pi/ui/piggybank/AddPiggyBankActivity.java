package com.example.pi.ui.piggybank;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.pi.R;
import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.data.db.repository.PiggyBankRepository;
import com.example.pi.ui.MenuActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

public class AddPiggyBankActivity extends AppCompatActivity{

    private PiggyBank piggyBank;
    private PiggyBankRepository piggyBankRepository;
    private ArrayList<PiggyBank> piggyBanks;
    private EditText edtName;
    private DatePicker dpDate;
    private Toolbar tbToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_piggybank);
        edtName = findViewById(R.id.edtName);
        dpDate = findViewById(R.id.dpDate);
        tbToolBar = findViewById(R.id.tbToolBar);
        setSupportActionBar(tbToolBar);
        //DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (getIntent().hasExtra("piggybank")) {
            piggyBank = getIntent().getExtras().getParcelable("piggybank");
            edtName.setText(piggyBank.getName().toString());
            Calendar c = piggyBank.getCreationDate();
            dpDate.updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        } else {
            piggyBank = null;
        }
        piggyBankRepository = PiggyBankRepository.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_addpiggybank,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                //piggyBankRepository.getPiggybanksOrderById().get(piggyBank.getId());
                //Toast.makeText(getApplicationContext(), piggyBankRepository.getList(), Toast.LENGTH_SHORT).show();
                if (piggyBank == null){
                    Log.d("menu save","piggybank es null");
                    piggyBankRepository.addPiggyBank(new PiggyBank(piggyBankRepository.getList(),1,edtName.getText().toString(),new GregorianCalendar(dpDate.getYear(),dpDate.getMonth()-1,dpDate.getDayOfMonth())));
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.addPiggyBank), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("menu save","piggybank no es null");
                    Calendar ca = new GregorianCalendar(dpDate.getYear(), dpDate.getMonth() - 1, dpDate.getDayOfMonth());
                    piggyBankRepository.modPiggyBank(piggyBank,edtName.getText().toString(),ca);
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.modPiggyBank), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.action_delete:
                //cuando borras el id 7 y el array list tiene menos tamaño da error
                if (piggyBank == null){
                    edtName.setText("");
                } else {
                    piggyBankRepository.deletePiggyBankIterator(piggyBank);
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.delPiggyBank), Toast.LENGTH_SHORT).show();
                }
        }
        startActivity(new Intent(this, MenuActivity.class));
        return super.onOptionsItemSelected(item);
    }

}
