package services;

import entities.User;
import factories.UserFactory;

import java.util.List;

public class UserService {

    List<User> userList;

    public UserService(){
        UserFactory userFactory = new UserFactory();
        userList = userFactory.createUsers();
    }

    public User login(String username, String password){
        User user = null;

        for (User u : userList){
            if(u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)){
                user = u;
            }

        }

        return user;
    }

}
