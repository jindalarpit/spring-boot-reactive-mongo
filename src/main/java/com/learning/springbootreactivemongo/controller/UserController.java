package com.learning.springbootreactivemongo.controller;

import com.learning.springbootreactivemongo.entity.User;
import com.learning.springbootreactivemongo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping()
    public @ResponseBody Mono<User> addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping()
    public @ResponseBody Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

}
