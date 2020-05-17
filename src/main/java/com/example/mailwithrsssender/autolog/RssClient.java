package com.example.mailwithrsssender.autolog;

import com.example.mailwithrsssender.domain.Canal;
import com.example.mailwithrsssender.domain.FeedMessage;
import com.example.mailwithrsssender.repositories.CanalRepository;
import com.example.mailwithrsssender.repositories.FeedMessageRepository;
import com.example.mailwithrsssender.services.CanalService;
import com.example.mailwithrsssender.services.FeedMessageService;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Main Rss Client class
 */
@Component
public class RssClient {


    /** Rss Client constructor
     */
    public RssClient() {

    }


/**
 *
 * get Rss from url
 * */
    public static void getRss(Set<FeedMessage> messages, Canal canal) {


        try{
            String url =canal.getUrl();

            try (XmlReader reader = new XmlReader(new URL(url))) {
                SyndFeed feed1 = new SyndFeedInput().build(reader);
                System.out.println(feed1.getTitle());
                for (SyndEntry entry : feed1.getEntries()) {
                    messages.add(new FeedMessage(entry.getLink(),entry.getTitle(),canal));

                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
        /** geting rss from url  **/
    public static void getRssFromUrl(String canalRss, List<FeedMessage> set, Canal canal) {
        try {
            String url = canalRss;

            try (XmlReader reader = new XmlReader(new URL(url))) {
                SyndFeed feed1 = new SyndFeedInput().build(reader);
                System.out.println(feed1.getTitle());
                for (SyndEntry entry : feed1.getEntries()) {
                    set.add(new FeedMessage(entry.getLink(), entry.getTitle(), canal));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
