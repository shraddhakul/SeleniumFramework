package AutomationProject.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

public class DataReader {

    /**
     * Reads test data from a JSON file and converts it into
     * a list of HashMaps, where each HashMap represents a test case.
     *
     * @return List of HashMaps containing key-value pairs for each test case
     * @throws IOException if file read fails
     */
    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        // File path to your JSON test data
        String filePath = System.getProperty("user.dir") +
                "\\src\\test\\java\\AutomationProject\\data\\purchaseOrder.json";

        // Read entire JSON content as a String
        String jsonContent = FileUtils.readFileToString(
                new File(filePath), StandardCharsets.UTF_8);

        // Convert JSON string to List of HashMaps using Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {});
    }
}
