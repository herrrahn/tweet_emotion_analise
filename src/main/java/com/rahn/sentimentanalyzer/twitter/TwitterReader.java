package com.rahn.sentimentanalyzer.twitter;

import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.List;

@Service()
public class TwitterReader {

    private static final String CONSUMER_KEY = "YOUR KEY";
    private static final String CONSUMER_KEY_SECRET = "YOUR KEY";

    private static final String ACCESS_TOKEN = "YOUR KEY";
    private static final String ACCESS_TOKEN_SECRET = "YOUR KEY";

    public List<Status> readTwitter(String text) throws TwitterException {

        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);

        // here's the difference
        AccessToken oathAccessToken = new AccessToken(ACCESS_TOKEN,
                ACCESS_TOKEN_SECRET);

        twitter.setOAuthAccessToken(oathAccessToken);
        // end of difference

        Query query = new Query(text);
        QueryResult list = twitter.search(query);

        for (Status each : list.getTweets()) {

            System.out.println("Sent by: @" + each.getUser().getScreenName()
                    + " - " + each.getUser().getName() + "\n" + each.getText()
                    + "\n");
        }

        return list.getTweets();

    }

}
