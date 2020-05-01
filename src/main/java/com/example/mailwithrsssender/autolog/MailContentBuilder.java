package com.example.mailwithrsssender.autolog;


import com.example.mailwithrsssender.domain.FeedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
/** Mail content builder class
 */
@Service
public class MailContentBuilder {

    /** Template engine to add variable in mailTemplate(/resources/templates/mailTemplate)
     */
    private TemplateEngine templateEngine;

    /** Content Builder method
     */
    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    public String build(List<FeedMessage> message) {
        Context context = new Context();
        context.setVariable("message", message);

        /** templateEngine process data for mailTemplate.html
         */
        return templateEngine.process("mailTemplate", context);
    }

}
