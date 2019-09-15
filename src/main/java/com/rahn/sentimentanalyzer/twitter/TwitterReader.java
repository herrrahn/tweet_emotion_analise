package com.rahn.sentimentanalyzer.twitter;

import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.List;

@Service()
public class TwitterReader {

    private static final String CONSUMER_KEY = "1N0rNlJtT6ky932Rlf2W8bqVs";
    private static final String CONSUMER_KEY_SECRET = "PcYLVukZlbZxe02BSpGUZiXhprTs56FI67HqEiUVIVCTzQ5ZE6";

    private static final String ACCESS_TOKEN = "337384343-PjW1xWNl4Ugi5iFSf7YmquoLwobc4oTQnSNtBbTk";
    private static final String ACCESS_TOKEN_SECRET = "i7GQpSTo3XBnFhXBVzzWnTOW4wTWynmt3V0ScUlQfoeCA";

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
