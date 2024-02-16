package nsu.maxwell;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> oldVersion = new HashMap<>();
        Map<String, String> newVersion = new HashMap<>();
        VersionController versionController = new VersionController();

        oldVersion.put("http://localhost:3000/url1", "123");
        oldVersion.put("http://localhost:3000/url2", "123");
        oldVersion.put("http://localhost:3000/url3", "123");

        newVersion.put("http://localhost:3000/url1", "124");
        newVersion.put("http://localhost:3000/url4", "123");

        System.out.println(MessageFormatter.format(versionController.getChanges(oldVersion, newVersion)));
    }
}