package com.rahn.sentimentanalyzer;

import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.rahn.sentimentanalyzer.twitter.TwitterReader;
import com.rahn.sentimentanalyzer.watson.WatsonAnalyser;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.*;

@Service
public class Orchestrator {

    private TwitterReader twitterReader;
    private WatsonAnalyser watsonAnalyser;

    @Autowired
    public Orchestrator(TwitterReader twitterReader, WatsonAnalyser watsonAnalyser) {
        this.twitterReader = twitterReader;

        this.watsonAnalyser = watsonAnalyser;
    }

    public List<AnalysisResultsDTO> analysisResults(String text) throws TwitterException, IOException {
        List<String> tweets = new ArrayList<>();
        tweets = this.twitterReader.readTwitter(text)
                .stream()
                .map(Status::getText).collect(Collectors.toList());

        List<AnalysisResultsDTO> analysedTweets = new java.util.ArrayList<>(emptyList());
        for (String tweet : tweets) {
            try {
                AnalysisResults analysisResults = this.watsonAnalyser.analise(tweet, text);
                analysedTweets.add(new AnalysisResultsDTO(tweet, analysisResults));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        return analysedTweets;

    }
}
