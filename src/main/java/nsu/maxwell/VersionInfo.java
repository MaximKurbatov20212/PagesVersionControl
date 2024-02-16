package nsu.maxwell;

import java.util.Set;

public record VersionInfo(Set<String> vanishedPages, Set<String> editedPages, Set<String> addedUrls) {}
