package nsu.maxwell;

import java.util.HashSet;
import java.util.Map;

public class VersionController {
    public VersionInfo getChanges(Map<String, String> oldVersion, Map<String, String> newVersion) {
        VersionInfo versionInfo = new VersionInfo(oldVersion.keySet(), new HashSet<>(), new HashSet<>());

        newVersion.forEach((key, value) -> {
            String oldValue = oldVersion.get(key);

            versionInfo.vanishedPages().remove(key);

            if (oldValue == null) {
                versionInfo.addedUrls().add(key);
            } else if (!oldValue.equals(value)) {
                versionInfo.editedPages().add(key);
            }
        });

        return versionInfo;
    }
}