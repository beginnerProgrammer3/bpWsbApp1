package com.example.mailwithrsssender.autolog;


import com.example.mailwithrsssender.domain.Canal;
import com.example.mailwithrsssender.domain.Customer;
import com.example.mailwithrsssender.domain.FeedMessage;
import com.example.mailwithrsssender.repositories.CanalRepository;
import com.example.mailwithrsssender.repositories.CustomerRepository;
import com.example.mailwithrsssender.repositories.FeedMessageRepository;

import com.mashape.unirest.http.exceptions.UnirestException;


import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.*;

import java.util.stream.Collectors;

import static com.example.mailwithrsssender.autolog.RssClient.getRss;
import static com.example.mailwithrsssender.autolog.RssClient.getRssFromUrl;

/** Mail MailClient class to send, prepare and debug email
 */
@Service
public class MailClient {


    private JavaMailSender mailSender;
    private MailContentBuilder mailContentBuilder;
    private CustomerRepository customerRepository;
    private CanalRepository canalRepository;
    private FeedMessageRepository feedMessageRepository;


    public MailClient(JavaMailSender mailSender, MailContentBuilder mailContentBuilder, CustomerRepository customerRepository, CanalRepository canalRepository, FeedMessageRepository feedMessageRepository) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
        this.customerRepository = customerRepository;
        this.canalRepository = canalRepository;
        this.feedMessageRepository = feedMessageRepository;
    }

    /**  This method sending mails at 9 am. to clients */
    @Scheduled(cron="00 26 14 * * *")
    public void sendToMe() {

        /** deleting all old feedMessages from database
         */
        feedMessageRepository.deleteAll();

        /** searching all canals and searching for them new rss-es,
         * after that messages are saved to database
         */
        canalRepository.findAll().forEach(canal -> {
            Set<FeedMessage> rss = new HashSet<>();
            getRss(rss, canal);
            canal.setMessages(rss);
            feedMessageRepository.saveAll(rss);
            canalRepository.save(canal);

        });

        /**
         * sending messages to all customers
         */
            for (Customer customer : customerRepository.findAll()) {
                Customer customerTemp = customer;
                String sender = customerTemp.getEmail();
                System.out.println(sender);
                Set<Canal> msgs = customerTemp.getCanals();

                StringBuilder messagesB = new StringBuilder();
                List<FeedMessage> mesages = new ArrayList<>();
                for (Canal canal : msgs) {
                    Set<FeedMessage> msg = canal.getMessages();

                    msg.stream().forEach(feedMessage ->messagesB.append(feedMessage.getTitle() +" "+
                            feedMessage.getLink()).append("\n").append("<br/>"));

                    mesages.addAll(msg.stream().collect(Collectors.toList()));

                }
//                 prepareAndSend(sender,mesages); for Mailgun
                /** Send method
                 */
                 prepareAndSendByGmail(sender,mesages);
                System.out.println("server send mail with rsses ");
            }

        }
    /**
     * preparing email context to send in mail
     *
     * @param recipient
     * @param messagesB list of messages to send
     */
    public void prepareAndSendByGmail(String recipient, List<FeedMessage> messagesB) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(recipient);
            messageHelper.setSubject("RSS Daily Sender");
            String content = mailContentBuilder.build(messagesB);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }


    /** method getting and sending email with rss-es to one client
     * @param mailClient
     * @param canalRss : canal to get rsses
     */
    public void getRssForOneMail(String mailClient,String canalRss) throws UnirestException {
        List<FeedMessage> set =new ArrayList<>();
        Canal canal = new Canal();

        getRssFromUrl(canalRss, set, canal);


        StringBuilder messagesMailbox = new StringBuilder();
        List<FeedMessage> simpleMessageWithRss= new ArrayList<>();

        for(FeedMessage msg :set){
            messagesMailbox.append(msg.getTitle() +" "+
                    msg.getLink()).append("\n").append(" ");
            simpleMessageWithRss.add(msg);
        }


        prepareAndSendByGmail(mailClient,simpleMessageWithRss);

    }

    /** "mail debugger" on site
     *
     * @param canalRss get canal url
     */
    public List<FeedMessage> getFeedMessageAndDebug(String canalRss) throws UnirestException {
        List<FeedMessage> feedMessages =new ArrayList<>();
        feedMessages.add(new FeedMessage(" !!!!!! IMPORTANT  !!!!!!!","  ",null));
        feedMessages.add(new FeedMessage(" In official message Link will be hidden behind sign >>> Go To Article <<<","  ",null));
        Canal canal = new Canal();


        getRssFromUrl(canalRss, feedMessages, canal);

        /** return feedmessage(title and url for rss) for index.html
         */
        return feedMessages;


    }




}