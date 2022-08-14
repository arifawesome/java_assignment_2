package com.example.java_final_1.service;
import com.example.java_final_1.models.User;
import com.example.java_final_1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.List;


@Service
public class UserService{


    @Autowired
    private UserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }



    public User addUser(User user){
        User u = userRepository.findByEmail(user.getEmail());
        if(u==null) {
            String encodedPassword = passwordEncoder().encode(user.getPassword());
            user.setPassword(encodedPassword);
            return userRepository.save(user);
        } else{
            return null;
        }
    }
    public User getUserByEmailPassword(String email, String password) {
        User foundUser = userRepository.findByEmail(email);
        if(BCrypt.checkpw(password, foundUser.getPassword())){
            return foundUser;
        }
        else{
            return null;
        }
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }
}
