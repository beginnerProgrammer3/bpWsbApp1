package com.example.mailwithrsssender.springdatajpa;

import com.example.mailwithrsssender.domain.Customer;
import com.example.mailwithrsssender.repositories.CanalRepository;
import com.example.mailwithrsssender.repositories.CustomerRepository;
import com.example.mailwithrsssender.repositories.FeedMessageRepository;
import com.example.mailwithrsssender.services.CustomerService;
import org.springframework.stereotype.Service;
import java.util.Set;


@Service
public class CustomerSDJservice implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CanalRepository canalRepository;
    private final FeedMessageRepository feedMessageRepository;


    public CustomerSDJservice(CustomerRepository customerRepository, CanalRepository canalRepository, FeedMessageRepository feedMessageRepository) {
        this.customerRepository = customerRepository;
        this.canalRepository = canalRepository;
        this.feedMessageRepository = feedMessageRepository;

    }

    @Override
    public Set<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findById(Long aLong) {
        return null;
    }



    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer o) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }


    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}
