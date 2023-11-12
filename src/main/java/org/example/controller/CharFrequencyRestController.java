package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Character Frequency")
public class CharFrequencyRestController {
    @GetMapping("/char-frequency")
    @ApiOperation(value = "Get character frequency in a string",
            notes = "Returns a sorted map of characters and their frequencies in the input string.")
    public ResponseEntity<Map<String, Integer>> getCharFrequency(
            @ApiParam(value = "The input string for character frequency calculation", required = true)
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
