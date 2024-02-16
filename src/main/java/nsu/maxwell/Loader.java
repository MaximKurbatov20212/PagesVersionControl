package nsu.maxwell;

import java.util.Map;
import java.util.Set;

abstract class Loader {
    record Page(String url, String data) {}
    record Pages(Set<Page> pages) {}
    abstract Map<String, String> load(String file);
}
