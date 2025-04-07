package com.charity2.Service;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class SentimentAnalysisService {

    public Map<String, Object> analyzeSentiment(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);

        Map<String, Object> sentimentResults = new HashMap<>(); // Change to Map<String, Object>
        List<Map<String, Float>> sentenceSentiments = new ArrayList<>();

        for (CoreSentence sentence : document.sentences()) {
            Map<String, Float> sentenceSentiment = new HashMap<>();
            String sentiment = sentence.sentiment();
            sentenceSentiment.put("score", mapSentimentToScore(sentiment));
            sentenceSentiments.add(sentenceSentiment);
        }
        sentimentResults.put("averageScore", (float) document.sentences().stream().mapToDouble(sentence -> mapSentimentToScore(sentence.sentiment())).average().orElse(2.0));
        sentimentResults.put("sentenceSentiments", sentenceSentiments); // Store the List<Map<String, Float>> directly
        return sentimentResults;
    }

    private float mapSentimentToScore(String sentiment) {
        switch (sentiment) {
            case "Very negative":
                return 0.0f;
            case "Negative":
                return 1.0f;
            case "Neutral":
                return 2.0f;
            case "Positive":
                return 3.0f;
            case "Very positive":
                return 4.0f;
            default:
                return 2.0f; // Default to neutral
        }
    }
}