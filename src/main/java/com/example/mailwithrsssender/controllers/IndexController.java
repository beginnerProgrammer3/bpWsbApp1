package com.example.mailwithrsssender.controllers;


import com.example.mailwithrsssender.autolog.MGSample;
import com.example.mailwithrsssender.autolog.MailClient;
import com.example.mailwithrsssender.domain.Canal;
import com.example.mailwithrsssender.domain.Customer;
import com.example.mailwithrsssender.domain.FeedMessage;
import com.example.mailwithrsssender.services.CanalService;
import com.example.mailwithrsssender.services.CustomerService;
import com.example.mailwithrsssender.services.FeedMessageService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.*;


@Controller
public class IndexController {

    private final CustomerService customerService;
    private final CanalService canalService;
    private final MailClient mailClient1;

    public IndexController(CustomerService customerService, CanalService canalService, MailClient mailClient1) {
        this.customerService = customerService;
        this.canalService = canalService;
        this.mailClient1 = mailClient1;
    }

    @RequestMapping("/")
    public String index(Model model) {
        Customer customer = Customer.builder().build();
        Canal canal = Canal.builder().build();
        model.addAttribute("canal", canal);
        model.addAttribute("customer", customer);
        return "index";
    }
    @RequestMapping (value = "/", params = "sendtomail", method = RequestMethod.POST)
    public String sendToOneMail(Customer customer,Canal canal) throws UnirestException {
        mailClient1.getRssForOneMail(customer.getEmail(),canal.getUrl());
        return "index";
    }

    @RequestMapping (value = "/", params = "loadrss", method = RequestMethod.POST)
    public String loadRssToDebugger(Customer customer,Canal canal, Model model) throws UnirestException {
        model.addAttribute("feedMessages",mailClient1.getFeedMessageAndDebug(canal.getUrl()));
         return "index";
    }



    @PostMapping("/")
    public String processCreationForm(@Valid Customer customer, @Valid Canal canal) {



        Optional<Customer> customerFind = Optional.ofNullable(customerService.findCustomerByEmail(customer.getEmail()));


        if(customerFind.isPresent()) {
            customer.setId(customerFind.get().getId());

            Set<Canal> canals = canalService.findCanalByCustomerId(customer.getId());
            for (Canal findCanalExisting : canals) {
                if (findCanalExisting.getUrl().equals(canal.getUrl())) {

                }
                if (!findCanalExisting.getUrl().equals(canal.getUrl())) {
                    canal.setCustomer(customer);
                    customer.getCanals().addAll(canals);
                    canalService.save(canal);
                    customerService.save(customer);

                }
            }
        }
        else {
            canal.setCustomer(customer);
            customer.getCanals().add(canal);
            customerService.save(customer);
            canal.setCustomer(customer);
            canalService.save(canal);

        }

        return "redirect:/";
    }




}


