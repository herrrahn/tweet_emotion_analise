package com.rahn.sentimentanalyzer;

import com.ibm.watson.natural_language_understanding.v1.model.AnalysisResults;
import com.rahn.sentimentanalyzer.twitter.TwitterReader;
import com.rahn.sentimentanalyzer.watson.WatsonAnalyser;
import org.springframework.web.bind.annotation.*;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@CrossOrigin("*")
@RequestMapping("/api/rrr")
public class Controller {

    private Orchestrator orchestrator;

    public Controller(Orchestrator orchestrator){
        this.orchestrator = orchestrator;
    }

    @GetMapping(value = "/analise")
    public List<AnalysisResultsDTO> testWatson(@RequestParam(value = "hash_tag") String text) throws Exception {
        //return this.watsonAnalyser.analise("");
        return this.orchestrator.analysisResults(text);
    }
}
