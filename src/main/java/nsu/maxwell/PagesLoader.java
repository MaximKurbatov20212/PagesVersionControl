package nsu.maxwell;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PagesLoader extends Loader {
    @Override
    public Map<String, String> load(String file) {
        Map<String, String> map = new HashMap<>();
        Gson gson = new Gson();

        try (JsonReader jsonReader = new JsonReader(new FileReader(file))) {
            Pages p = gson.fromJson(jsonReader, Pages.class);
            p.pages().forEach(page -> map.put(page.url(), page.data()));
            return map;
        } catch (IOException e) {
            System.err.println("Read file error" + e.getMessage());
        }
        return null;
    }
}
