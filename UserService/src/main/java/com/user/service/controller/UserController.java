package com.user.service.controller;

import com.user.service.entities.User;
import com.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /// create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
       User user1 = userService.createUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    int retryCount = 1;
    /// get single user by user id
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker" , fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService" , fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter" , fallbackMethod = "ratingHotelFallback")

    public ResponseEntity<User> getUserById( @PathVariable String userId){
        logger.info("Get Single user handler : UserController");
        logger.info("Retry Count : {}" ,retryCount);
        retryCount++;
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
//        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    ///  creating Fallback Method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId , Exception exception){
//        logger.info("Fallback is executed because Service is down !! " + exception.getMessage());

        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created because some services is down !!")
                .userId("11223344")
                .build();
        return new ResponseEntity<>(user , HttpStatus.OK);
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
