package nsu.maxwell;

import java.util.Set;

public record VersionInfo(Set<String> vanished,  Set<String> edited,  Set<String> added) {}
