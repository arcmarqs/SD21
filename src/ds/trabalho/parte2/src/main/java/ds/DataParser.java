package ds;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

class DataParser {
    static Map<String,String> parsed = new HashMap<>();

    public Map<String,String> getRandEntries() throws Exception {

    String file = "src/main/resources/dataset.json";
    String json = readjs(file);
    parsed = new Gson().fromJson(json,Map.class);

    Map<String,String> result = new HashMap<>();

    result.putAll(parsed);

    return result;
    }

    public static String readjs(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
    
}
