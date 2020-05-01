package com.example.mailwithrsssender.bootstrap;


import com.example.mailwithrsssender.domain.Canal;
import com.example.mailwithrsssender.domain.Customer;
import com.example.mailwithrsssender.domain.FeedMessage;

import com.example.mailwithrsssender.services.CanalService;
import com.example.mailwithrsssender.services.CustomerService;
import com.example.mailwithrsssender.services.FeedMessageService;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/** Test Data Loader
 */
//@Component
//public class LoadData implements CommandLineRunner {
//
//    private final CustomerService customerService;
//    private final CanalService canalService;
//    private final FeedMessageService feedMessageService;
//
//
//    public LoadData(CustomerService customerService, CanalService canalService, FeedMessageService feedMessageService) {
//        this.customerService = customerService;
//        this.canalService = canalService;
//        this.feedMessageService = feedMessageService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Set<FeedMessage> rsses = new HashSet<>();
//
//        Customer customer1 = new Customer();
//
//        Canal canal1 = new Canal();
//        canal1.setUrl("https://www.tvn24.pl/najnowsze.xml");
//
//
//        getRss(rsses, canal1.getUrl(),canal1);
//        canal1.setMessages(rsses);
//
//
//        Set<FeedMessage> rsses1 = new HashSet<>();
//        Canal canal2 = new Canal();
//        canal2.setUrl("https://www.espn.com/espn/rss/news");
//        Canal canal3 = new Canal();
//        canal3.setUrl("https://www.espn.com/espn/rss/news");
//
//        Set<FeedMessage> rsses3 = new HashSet<>();
//        getRss(rsses3, canal3.getUrl(),canal3);
//        canal3.setMessages(rsses3);
//        canal2.setMessages(rsses1);
//        getRss(rsses1, canal2.getUrl(),canal2);
////        canalService.save(canal2);
//        customer1.setEmail("cabalskaa@gmail.com");
//        customer1.getCanals().add(canal1);
//        customer1.getCanals().add(canal2);
//        canalService.save(canal1);
//        canalService.save(canal2);
//        canalService.save(canal3);
//
//        Customer customer2 = new Customer();
//        customer2.setEmail("jaceksko91@gmail.com");
//        customer2.getCanals().add(canal1);
//        customer2.getCanals().add(canal2);
//       // customerService.save(customer1);
//        customerService.save(customer2);
//

//    }
//
//
//    private void getRss(Set<FeedMessage> set, String rssLink,Canal canal) {
//
//
//        try{
//            String url =rssLink;
//
//            try (XmlReader reader = new XmlReader(new URL(url))) {
//                SyndFeed feed1 = new SyndFeedInput().build(reader);
//                System.out.println(feed1.getTitle());
//                System.out.println("***********************************");
//                for (SyndEntry entry : feed1.getEntries()) {
//                    set.add(new FeedMessage(entry.getLink(),entry.getTitle(),canal));
//
//
//                }
//                System.out.println("********AddedRss*********");
//            }
//        }  catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
