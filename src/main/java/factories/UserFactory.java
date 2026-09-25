package factories;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    public UserFactory(){

    }

    public List<User> createUsers(){
        List<User> userList = new ArrayList<>();

        userList.add(new User("admin", "password"));

        return userList;
    }

    public User createUser(String username, String password){
        return new User(username, password);
    }
}
