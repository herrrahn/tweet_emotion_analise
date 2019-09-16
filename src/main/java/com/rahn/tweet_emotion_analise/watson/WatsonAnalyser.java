package com.rahn.tweet_emotion_analise.watson;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.natural_language_understanding.v1.model.Features;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WatsonAnalyser {

    public AnalysisResults analise(String tweet, String text) {
        IamOptions options = new IamOptions.Builder()
                .apiKey("uo_rXnBXu7zuV014iOjhSLHm_5PZKI9Tl9YrxFRwZOUU")
                .build();

        NaturalLanguageUnderstanding naturalLanguageUnderstanding = new NaturalLanguageUnderstanding("2019-07-12", options);
        naturalLanguageUnderstanding.setEndPoint("https://gateway-lon.watsonplatform.net/natural-language-understanding/api");

        List<String> targets = new ArrayList<>();
        targets.add(text);

        EmotionOptions emotion= new EmotionOptions.Builder()
                .targets(targets)
                .build();

        Features features = new Features.Builder()
                .emotion(emotion)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(tweet)
                .features(features)
                .build();

        AnalysisResults response = naturalLanguageUnderstanding
                .analyze(parameters)
                .execute()
                .getResult();
        System.out.println(response);
        return response;

    }

}
