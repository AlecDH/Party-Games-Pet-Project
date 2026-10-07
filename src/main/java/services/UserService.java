package services;

import entities.User;
import persistence.ConnectionPool;
import persistence.ReviewMapper;
import persistence.UserMapper;

import java.util.List;

public class UserService {

    private ConnectionPool connectionPool;
    private UserMapper userMapper;
    private ReviewMapper reviewMapper;
    private List<User> userList;

    public UserService(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
        userMapper = new UserMapper(connectionPool);
        reviewMapper = new ReviewMapper(connectionPool);
        //userList = userFactory.createUsers();
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
        //User user = userFactory.createUser(username, password);
        //userList.add(user);
        //return user;
        return null;
    }

}
