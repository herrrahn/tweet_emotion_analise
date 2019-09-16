package com.rahn.tweet_emotion_analise;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
