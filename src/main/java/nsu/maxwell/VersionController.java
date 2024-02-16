package nsu.maxwell;

import java.util.HashSet;
import java.util.Map;

public class VersionController {
    public VersionInfo getChanges(Map<String, String> oldVersion, Map<String, String> newVersion) {
        VersionInfo versionInfo = new VersionInfo(oldVersion.keySet(), new HashSet<>(), new HashSet<>());

        newVersion.forEach((key, value) -> {
            String oldValue = oldVersion.get(key);

            versionInfo.vanished().remove(key);

            if (oldValue == null) {
                versionInfo.added().add(key);
            } else if (!oldValue.equals(value)) {
                versionInfo.edited().add(key);
            }
        });

        return versionInfo;
    }
}