package com.example.java_final_1.controller;
import com.example.java_final_1.models.User;
import com.example.java_final_1.repositories.UserRepository;
import com.example.java_final_1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity <List<User>> getUsers(){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity <User> getUserById(@PathVariable("id") String id){
        Optional<User> userOptional= userRepository.findById(id);
        if (userOptional.isPresent()) {
            return new ResponseEntity(userOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity("User doesn't exists",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{email}/{password}")
    public ResponseEntity<User> getUserByEmailPassword(@PathVariable("email") String email,@PathVariable("password") String password){
        User authuser= userService.getUserByEmailPassword(email, password);

        if (authuser!=null) {
            return new ResponseEntity(authuser, HttpStatus.OK);
        } else {
            return new ResponseEntity("email or password is incorrect, please try again with correct details", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping( "/user/save")
    public ResponseEntity createUser(@RequestBody User user){

        if(userService.addUser(user)==null){
            return new ResponseEntity("user already exist ",HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity("user save successfullly", HttpStatus.OK );
        }

    }
}


