package com.example.mailwithrsssender.services;

import com.example.mailwithrsssender.domain.Customer;


public interface CustomerService extends CrudService<Customer, Long> {
    Customer findCustomerByEmail(String email);

}
