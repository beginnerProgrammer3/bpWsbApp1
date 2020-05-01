package com.example.mailwithrsssender.domain;

import lombok.Builder;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "canals")
public class Canal extends BaseEntity{

//    @NotNull(message = "Name may not be null")
    private String url;

    @OneToMany(mappedBy = "canal", fetch = FetchType.EAGER)
    private Set<FeedMessage> messages = new HashSet<>();

    @ManyToOne
    private Customer customer;

    @Builder
    public Canal(Long id,String url, Set<FeedMessage> messages,Customer customer) {
        super(id);
        this.url = url;
        this.messages = messages;
        this.customer = customer;
    }
    public Canal(Long id,String url, Set<FeedMessage> messages) {
        super(id);
        this.url = url;
        this.messages = messages;

    }

    public Canal(){

    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<FeedMessage> getMessages() {
        return messages;
    }

    public void setMessages(Set<FeedMessage> messages) {
        this.messages = messages;
    }

}
