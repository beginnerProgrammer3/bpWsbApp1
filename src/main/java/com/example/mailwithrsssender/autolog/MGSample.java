package com.example.mailwithrsssender.autolog;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

/** Unused MailGun class to sending mail with mailgun API
 */
public class MGSample {

// Old MG Sender, i do not use this because i dont need that in student project

    public static JsonNode sendSimpleMessage(String user, String messages) throws UnirestException {

        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + "mailConfig.getYOUR_DOMAIN_NAME() + /messages")
                .basicAuth("api", "mailConfig.getKEY_TO_MAIL()")
                .field("from", "Excited User <USER@YOURDOMAIN.COM>")
                .field("to", user)
                .field("subject", "Your Rsses")
                .field("text", messages)
                .asJson();

        return request.getBody();
    }
}
