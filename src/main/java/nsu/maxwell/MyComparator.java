package nsu.maxwell;

import java.util.Map;

public interface MyComparator {
    VersionInfo getChanges(Map<String, String> oldVersion, Map<String, String> newVersion);
}
