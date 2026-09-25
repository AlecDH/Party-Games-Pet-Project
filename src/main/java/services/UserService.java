package services;

import entities.User;
import factories.UserFactory;

import java.util.List;

public class UserService {

    List<User> userList;
    UserFactory userFactory;

    public UserService(){
        userFactory = new UserFactory();
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

    public boolean findUser(String username){
        for (User u : userList){
            if(u.getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }

    public User createUser(String username, String password){
        User user = userFactory.createUser(username, password);
        userList.add(user);
        return user;
    }

}
