package com.example.java_final_1.service;

//import cn.stylefeng.roses.kernel.validator.api.validators.phone.PhoneValue;

import com.example.java_final_1.models.Customer;
import com.example.java_final_1.models.User;
import com.example.java_final_1.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

@Service
public class CustomerService  {

    @Autowired
    private CustomerRepository customerRepository;
    PasswordEncoder passwordEncoder;



    public Customer saveCustomer(Customer  customer)  {
        passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        int count = customerRepository.findAll().size();
        ArrayList<Customer> saveCustomer=new ArrayList<>();
        for (int i = 0; i < count; i++) {
        if(customerRepository.findAll().get(i).getEmail_phoneNumber().equalsIgnoreCase(customer.getEmail_phoneNumber()))
        {
            return null;
        }

        }Customer savedCustomer=customerRepository.save(customer);
        System.out.println(savedCustomer);
        return savedCustomer;


    }
    public List<Customer> authCustomer(Customer customer) {
        ArrayList<Customer> foundCustomer=new ArrayList<>();
        int count = customerRepository.findAll().size();
        for (int i = 0; i < count; i++) {
           Customer customerAtIndex=customerRepository.findAll().get(i);
           System.out.println(customerAtIndex);
            System.out.println(count);
            if(customerAtIndex.getEmail_phoneNumber().equalsIgnoreCase(customer.getEmail_phoneNumber()) && BCrypt.checkpw(customer.getPassword(), customerAtIndex.getPassword()))
            {
                System.out.println(customerAtIndex.getEmail_phoneNumber().equalsIgnoreCase(customer.getEmail_phoneNumber()));
                System.out.println(BCrypt.checkpw(customer.getPassword(), customerAtIndex.getPassword()));
                foundCustomer.add(customerAtIndex);
            }

        }
        return foundCustomer;



    }


}
