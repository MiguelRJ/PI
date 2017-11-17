package com.example.pi.data.db.repository;

import com.example.pi.data.db.model.User;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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
        addUser(new User(1,"Alberto","Alberto.rj96@gmail.com","Alberto1", "Alberto Rodriguez Jimenez",new GregorianCalendar(1996,9,7),"Hombre",600000001,"Malaga","@Alberto.rj",false));
    }

    /* GET INSTANCE OF REPOSITORY */
    public static UserRepository getInstance(){
        if(userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    /* ADD USER */
    private void addUser(User user){
        users.add(user);
    }

    /* GET USERS */
    public ArrayList<User> getUsers(){
        return users;
    }

}
