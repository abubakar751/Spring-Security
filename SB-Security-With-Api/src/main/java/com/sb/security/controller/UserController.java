package com.sb.security.controller;

import com.sb.security.enity.User;
import com.sb.security.logout.BlackList;
import com.sb.security.enity.AuthRequest;
import com.sb.security.service.JWTService;
import com.sb.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @Autowired
    private BlackList blackLis;
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
    return  userService.addUser(user);
    }
    @PostMapping("/login")
    public  String addUser(@RequestBody AuthRequest authRequest ){
       Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.userName(),authRequest.password()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.userName());
        }
        else {
            throw
                     new UsernameNotFoundException("Invalid user Request");
        }
    }
    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('USER_ROLES') or hasAuthority('ADMIN_ROLES')")
    public String logoutUser(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token= null;
        if(authHeader !=null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
        }
       // blackLis.blacKListToken(token);
        return "You have successfully logged out !!";
    }

    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') or hasAuthority('USER_ROLES')")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }
    @GetMapping("/getUsers/{id}")
    @PreAuthorize("hasAuthority('USER_ROLES')")
    public User getAllUsers(@PathVariable Integer id){
        return userService.getUser(id);
    }
}
