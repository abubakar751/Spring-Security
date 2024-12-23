package com.sbsecurity.controller;

import com.sbsecurity.model.User;
import com.sbsecurity.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;
@GetMapping("/getAll")
    public List<User> getAll(){
      return   userServices.getAll();
    }
    @GetMapping("/getByUserName/{userName}")
    public User getByUserName(@PathVariable  String userName){
  return   userServices.getUser(userName);
    }
    @PostMapping("/post")
     public User addUser(@RequestBody User user){
    return  userServices.addUser(user);
     }
}
