package com.sb.security.service;

import com.sb.security.enity.User;
import com.sb.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service

public class UserService implements UserDetailsService {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
   private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = userRepo.findByUserName(username);
        return   userInfo.
                map(UserDetailsInfo::new)
                .orElseThrow(() -> new UsernameNotFoundException(" User Not Found " + username));


        }

    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userRepo.save(user);

        return "User Saved SuccessFully";
    }
    public List<User> getAllUser(){
        return userRepo.findAll();
    }
    public User getUser(Integer id){
        return userRepo.findById(id).get();
    }
}
