package com.mrj.morejmoney.data.repository;

import android.util.Log;

import com.mrj.morejmoney.data.model.User;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *      ArrayList<User>
 *      userRepository
 *      Constructor
 *      initialize
 *      getInstance() of Repository
 *      addUser()
 *      getUsers()
 */

public class UserRepository {

    private ArrayList<User> users;
    private static UserRepository userRepository;

    private UserRepository() {
        users = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        addUser(new User(0,"Miguel","miguel.rj96@gmail.com","Miguel1", "Miguel Rodriguez Jimenez",new GregorianCalendar(1996,9,7),"Hombre",600000000,"Malaga","@migue.rj",true));
        addUser(new User(1,"Lourdes","Lourdes@gmail.com","Lourdes1", "Lourdes",new GregorianCalendar(1996,9,7),"Mujer",600000001,"Malaga","@Lourdes",false));
        addUser(new User(2,"yo","yo@yo.com","Miguel1", "yo",new GregorianCalendar(1996,9,7),"Hombre",600000001,"Malaga","@yo",false));
    }

    /**
     * validar si un usuario existe o no
     * @param username
     * @param password
     * @return
     */
    public boolean validateCredentials(String username, String password){
        Iterator<User> iterator = users.iterator();
        User user;
        while (iterator.hasNext()){
            user = iterator.next();
            if (user.getUser().toLowerCase().equals(username.toLowerCase()) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si un usuario ya esta registrado
     * @param username
     * @return
     */
    public boolean isUserAlreadySignIn(String username) {
        Iterator<User> iterator = users.iterator();
        User user;
        while (iterator.hasNext()){
            user = iterator.next();
            if (user.getUser().toLowerCase().equals(username.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si un email ya esta registrado
     * @param email
     * @return
     */
    public boolean isEmailAlreadySignIn(String email) {
        Iterator<User> iterator = users.iterator();
        User user;
        while (iterator.hasNext()){
            user = iterator.next();
            if (user.getEmail().toLowerCase().equals(email.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * Existe un usuario con X nombre
     * @param name
     * @return
     */
    public boolean existsUserBy(String name){
        Iterator<User> iterator = users.iterator();
        User user;
        while (iterator.hasNext()){
            user = iterator.next();
            if (user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve el id de un usuario con nombre X
     * @param username
     * @return
     */
    public int getUserIdBy(String username){
        Iterator<User> iterator = users.iterator();
        User user;
        while (iterator.hasNext()){
            user = iterator.next();
            if (user.getUser().toLowerCase().equals(username.toLowerCase())){
                return user.getId();
            }
        }
        return -1;
    }

    /**
     * Devuelve la password de un usuario con nombre X
     * @param username
     * @return
     */
    public String getPasswordBy(String username){
        Iterator<User> iterator = users.iterator();
        User user;
        while (iterator.hasNext()){
            user = iterator.next();
            if (user.getUser().toLowerCase().equals(username.toLowerCase())){
                return user.getPassword();
            }
        }
        return "null";
    }

    /**
     * Obtiene el mayor Id
     * @return
     */
    public int getLastId(){
        int id = 0;
        for (int i=0; i < users.size(); i++) {
            Log.e("id",String.valueOf(users.get(i).getId()));
            if (users.get(i).getId() > id){
                id = users.get(i).getId();
            }
        }
        return id;
    }


    /* GET INSTANCE OF REPOSITORY */
    public static UserRepository getInstance(){
        if(userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    /* ADD USER */
    public void addUser(User user){
        users.add(user);
    }

    /* GET USERS */
    public ArrayList<User> getUsers(){
        return users;
    }



}
