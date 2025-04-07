package com.charity2.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProfanityFilterService {

    private Set<String> badWords;

    public ProfanityFilterService() {
        try {
            File file = ResourceUtils.getFile("classpath:bad-words.txt");
            List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
            badWords = new HashSet<>(lines);
        } catch (IOException e) {
            badWords = new HashSet<>();
        }
    }

    public String filterProfanity(String text) {
        if (badWords.isEmpty()) {
            return text;
        }

        String filteredText = text;
        for (String badWord : badWords) {
            String regex = "(?i)" + Pattern.quote(badWord); // Case-insensitive regex
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(filteredText);

            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                filteredText = filteredText.substring(0, start) + generateAsterisks(end - start) + filteredText.substring(end);
            }
        }
        return filteredText;
    }

    private String generateAsterisks(int length) {
        return "*".repeat(length);
    }
}