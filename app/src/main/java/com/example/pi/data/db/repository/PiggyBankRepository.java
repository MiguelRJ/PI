package com.example.pi.data.db.repository;

import android.widget.Toast;

import com.example.pi.data.db.model.PiggyBank;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;

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
        addPiggyBank(new PiggyBank(1,0,"Almohada",new GregorianCalendar(2017,11-1,17,15,30,00)));
        addPiggyBank(new PiggyBank(2,1,"Piedra"));
        addPiggyBank(new PiggyBank(3,1,"FundaMovil",new GregorianCalendar(2017,11-1,17,15,30,00)));
        addPiggyBank(new PiggyBank(4,0,"Cartera"));
        addPiggyBank(new PiggyBank(5,0,"Almohada",new GregorianCalendar(2017,11-1,17,15,30,00)));
        addPiggyBank(new PiggyBank(6,1,"Piedra"));
        addPiggyBank(new PiggyBank(7,1,"FundaMovil",new GregorianCalendar(2017,11-1,17,15,30,00)));
    }

    /* GET INSTANCE OF REPOSITORY */
    public static PiggyBankRepository getInstance(){
        return piggyBankRepository;
    }

    /* ADD PIGGY BANK */
    public void addPiggyBank(PiggyBank piggyBank){
        piggybanks.add(piggyBank);
    }

    public PiggyBank getPiggyBank(int id){
        return getPiggybanks().get(id);
    }

    public int getList(){
        int id = 0;
        for (int i=0; i < piggybanks.size(); i++) {
            if (piggybanks.get(i).getId() > id){
                id = piggybanks.get(i).getId();
            }
        }
        return id;
    }

    public String getLastId(){
        String mensaje = "";
        for (int i=0; i < piggybanks.size(); i++) {
            mensaje += String.valueOf(piggybanks.get(i).getId()+" ");
        }
        return mensaje;
    }

    /**
     * modifica una piggybank por su id e idUser
     * modificando el nombre y la fecha
     * @param id
     * @param idUser
     * @param name
     * @param calendar
     */
    public void modPiggyBank(int id, int idUser, String name, GregorianCalendar calendar){
        Iterator<PiggyBank> iterator = piggybanks.iterator();
        PiggyBank piggyBank;
        while (iterator.hasNext()){
            piggyBank = iterator.next();
            if ( (piggyBank.getId() == id) && piggyBank.getIdUser() == idUser ){
                piggyBank.setName(name);
                piggyBank.setCreationDate(calendar);
                break;
            }
        }
    }

    public void deletePiggyBankIterator(PiggyBank p){
        Iterator<PiggyBank> iterator = piggybanks.iterator();
        PiggyBank piggyBank;
        while (iterator.hasNext()){
            piggyBank = iterator.next();
            if ( (piggyBank.getId() == p.getId()) && piggyBank.getIdUser() == p.getIdUser() ){
                iterator.remove();
                break;
            }
        }
    }

    public boolean existsPiggyBankBy(String name) {
        Iterator<PiggyBank> iterator = piggybanks.iterator();
        PiggyBank piggyBank;
        while (iterator.hasNext()){
            piggyBank = iterator.next();
            if (piggyBank.getName().equals(name)){
                return true;
            }
        }
        return false;
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

    public ArrayList<PiggyBank> getPiggybanksOrderById(){
        Collections.sort(piggybanks, new PiggyBank.PiggyBankOrderById());
        return piggybanks;
    }



}
