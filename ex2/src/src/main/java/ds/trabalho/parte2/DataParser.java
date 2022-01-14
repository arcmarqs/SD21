package ds.trabalho.parte2;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataParser {
    static Map<String,String> parsed = new HashMap<>();
    public static Map<String, String> getRandomEntries() throws Exception{
        Gson gson = new Gson();
        String file = "src/main/resources/dataset.json";
        String json = readjs(file);
        List<Object> entries = new LinkedList<>();
        Type elemtType = new TypeToken<List<Object>>() {}.getType();
        Map<String,String> res = new HashMap<>();
        entries = gson.fromJson(json, elemtType);
        for(int i = 0; i < 3;i++){
            int rand = getRandomInt(entries.size());
            String id = entries.get(rand).toString();
            String value = id.substring(id.indexOf(",")+7,id.indexOf("}"));
            id = id.substring(id.indexOf("{")+4, id.indexOf(","));
            res.put(id,value);
            
        } 
        return res;      
    }

    public static String readjs(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static int getRandomInt(int max) {
        return (int) (Math.random()*max);
    }
    
}
