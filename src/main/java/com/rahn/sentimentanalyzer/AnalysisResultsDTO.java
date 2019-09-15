package com.rahn.sentimentanalyzer;

import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;

public class AnalysisResultsDTO {
    private String tweet;
    private AnalysisResults analysisResults;

    public AnalysisResultsDTO(String tweet, AnalysisResults analysisResults) {
        this.tweet = tweet;
        this.analysisResults = analysisResults;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public AnalysisResults getAnalysisResults() {
        return analysisResults;
    }

    public void setAnalysisResults(AnalysisResults analysisResults) {
        this.analysisResults = analysisResults;
    }
}
