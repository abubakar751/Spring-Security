package com.sbsecurity.services;

import com.sbsecurity.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServices {
    List<User> list =new ArrayList<>();
    public UserServices(){
        list.add(new User("Admin", "password","admin@gmail.com"));
        list.add(new User("Admin2", "password2","admin2@gmail.com"));
    }
    public List<User> getAll(){
        return list;
    }
public User getUser(String userName){
        return list.stream().filter((user) -> user.getUserName().equals(userName)).findAny().orElse(null );
}
public  User addUser(User user){
        list.add(user);
         return user;
}
}
