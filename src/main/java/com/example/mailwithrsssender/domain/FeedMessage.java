package com.example.mailwithrsssender.domain;

import lombok.Builder;
import javax.persistence.*;

@Entity
public class FeedMessage extends BaseEntity {

    private String link;
    private String title;

    @ManyToOne
    @JoinColumn(name = "canal_id")
    private Canal canal;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Builder
    public FeedMessage(String link,
            Customer customer, String title, Canal canal) {
        this.link = link;
        this.title = title;
        this.canal = canal;
        this.customer = customer;
    }
    @Builder
    public FeedMessage(String link,
                       String title, Canal canal) {
        this.link = link;
        this.title = title;
        this.canal = canal;

    }

    public FeedMessage(){

    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
