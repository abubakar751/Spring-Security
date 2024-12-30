package com.sb.security.service;

import com.sb.security.enity.User;
import com.sb.security.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = userRepo.findByUserName(username);
        return   userInfo.
                map(UserDetailsInfo::new)
                .orElseThrow(() -> new UsernameNotFoundException(" User Not Found " + username));


        }

}
