import org.example.controller.CharFrequencyRestController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharFrequencyRestControllerTest {

    @Test
    void testGetCharFrequency() {
        String inputString = "aaaaabcccc";
        CharFrequencyRestController charFrequencyRestController = new CharFrequencyRestController();

        ResponseEntity<Map<String, Integer>> responseEntity = charFrequencyRestController.getCharFrequency(inputString);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Map<String, Integer> expectedResponse = Map.of(
                "a", 5,
                "c", 4,
                "b", 1
        );

        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testGetCharFrequencyEmptyString() {
        String inputString = "";
        CharFrequencyRestController charFrequencyRestController = new CharFrequencyRestController();

        ResponseEntity<Map<String, Integer>> responseEntity = charFrequencyRestController.getCharFrequency(inputString);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(Map.of(), responseEntity.getBody());
    }
}
