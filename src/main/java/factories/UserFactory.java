package factories;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    public UserFactory(){

    }

    public List<User> createUsers(){
        List<User> userList = new ArrayList<>();

        userList.add(new User(0, "admin", "password"));

        return userList;
    }
}
