package com.example.pi.data.db.repository;

import com.example.pi.data.db.model.PiggyBank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *      ArrayList<PiggyBank>
 *      piggyBankRepository
 *      Constructor
 *      initialize
 *      getInstance() of Repository
 *      addPiggyBank()
 *      getPiggybanks()
 *      getPiggybanksOrderByCreationDate()
 *      getPiggybanksOrderByTotalAmount()
 */

public class PiggyBankRepository {

    private ArrayList<PiggyBank> piggybanks;
    private static PiggyBankRepository piggyBankRepository;

    static {
        piggyBankRepository = new PiggyBankRepository();
    }

    private PiggyBankRepository(){
        this.piggybanks = new ArrayList<>();
        initialize();
    }

    private void initialize(){
        addPiggyBank(new PiggyBank(0,0,"Cartera"));
        addPiggyBank(new PiggyBank(1,0,"Almohada",new GregorianCalendar(2017,11,17,15,30,00)));
        addPiggyBank(new PiggyBank(0,1,"Piedra"));
        addPiggyBank(new PiggyBank(1,1,"FundaMovil",new GregorianCalendar(2017,11,17,15,30,00)));
    }

    /* GET INSTANCE OF REPOSITORY */
    public static PiggyBankRepository getInstance(){
        return piggyBankRepository;
    }

    /* ADD PIGGY BANK */
    public void addPiggyBank(PiggyBank piggyBank){
        piggybanks.add(piggyBank);
    }

    /* GET PIGGY BANKS ORDER BY */
    public ArrayList<PiggyBank> getPiggybanks(){
        Collections.sort(piggybanks);
        return piggybanks;
    }

    public ArrayList<PiggyBank> getPiggybanksOrderByCreationDate(){
        Collections.sort(piggybanks, new PiggyBank.PiggyBankOrderByCreationDate());
        return piggybanks;
    }

    public ArrayList<PiggyBank> getPiggybanksOrderByTotalAmount(){
        Collections.sort(piggybanks, new PiggyBank.PiggyBankOrderByTotalAmount());
        return piggybanks;
    }
}
