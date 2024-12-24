package apps.multiplatform.utils;

import apps.multiplatform.pages.multiplatform.serverList.Server;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ServerUtils {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Method to write a list of servers to a JSON file
    public void writeServersToJsonFile(List<Server> servers, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), servers);
    }

    // Method to read a list of servers from a JSON file
    public List<Server> readServersFromJsonFile(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), new TypeReference<>() {
        });
    }
}
