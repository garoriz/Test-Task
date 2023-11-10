package org.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CharFrequencyRestController {
    @GetMapping("/char-frequency")
    public ResponseEntity<Map<String, Integer>> getCharFrequency(
            @RequestParam(name = "string") String stringUrlParam
    ) {
        Map<Character, Integer> charFrequency = calculateCharacterFrequency(stringUrlParam);
        Map<String, Integer> sortedCharFrequency = charFrequency.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .collect(Collectors.toMap(
                        entry -> String.valueOf(entry.getKey()),
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        return new ResponseEntity<>(sortedCharFrequency, HttpStatus.OK);
    }

    private Map<Character, Integer> calculateCharacterFrequency(String str) {
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (char c : str.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        return charFrequency;
    }
}
