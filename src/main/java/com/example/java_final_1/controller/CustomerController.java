package com.example.java_final_1.controller;
import com.example.java_final_1.models.Customer;
import com.example.java_final_1.models.MoviesAndTvs;
import com.example.java_final_1.repositories.CustomerRepository;
import com.example.java_final_1.service.CustomerService;
import com.example.java_final_1.models.Customer;
import com.example.java_final_1.repositories.CustomerRepository;
import com.example.java_final_1.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@Validated

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping("customer/save")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody  Customer customer) {
        if(customerService.saveCustomer(customer)==null){
            return new ResponseEntity("customer already exist with this email or phoneNumber", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.OK );
    }
    @PostMapping("customer/auth")
    public ResponseEntity<List<Customer>> authCustomer(@RequestBody  Customer customer) {
        List<Customer> foundCustomer = customerService.authCustomer(customer);
        if(foundCustomer.isEmpty()){
            return new ResponseEntity("no user exists ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(foundCustomer, HttpStatus.OK);

    }


    @GetMapping("/customers")
    public ResponseEntity<List<Customer>>getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            return new ResponseEntity(customerOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity( "customer is not exist",HttpStatus.NOT_FOUND);
        }

    }
}
