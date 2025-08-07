package com.user.service.controller;

import com.user.service.entities.User;
import com.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@Controller
//@ResponseBody
@RequestMapping("/users")
public class UserController {


@Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /// create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
       User user1 = userService.createUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    /// get single user by user id
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById( @PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
//        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    ///  get all user by user ID
    @GetMapping
   public ResponseEntity<List<User>> getAllUser(){
       List<User> allUser = userService.getAllUser();
       return ResponseEntity.ok(allUser);
   }

    /// delete use by user ID
    @DeleteMapping("/{userId}")
     public ResponseEntity<String> deleteUser( @PathVariable String userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User Deleted Successfully");
    }



    /// update user by user id
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId , @RequestBody User updateUser){
        User update = userService.updateUser(userId , updateUser);
        return ResponseEntity.ok("User update Successfully");
    }


}
