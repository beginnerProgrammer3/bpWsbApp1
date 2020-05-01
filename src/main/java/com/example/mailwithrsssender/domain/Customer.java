package com.example.mailwithrsssender.domain;

import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "customers")
public class Customer extends BaseEntity{


    @Column
    @NotNull(message = "Name may not be null")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Set<Canal> canals = new HashSet<>();


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Canal> getCanals() {
        return canals;
    }

    public void setCanals(Set<Canal> canals) {
        this.canals = canals;
    }

    @Builder
    public Customer(Long id, String email, Set<Canal> canals) {
        super(id);
        this.email = email;
        this.canals = canals;
    }

    public Customer(){

    }

    @Override
    public Long getId() {
        return super.getId();
    }
}
