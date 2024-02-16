package nsu.maxwell;

import java.util.Map;

public class Main {
    private static final int filesCount = 2;

    public static void main(String[] args) {
        PagesLoader pagesLoader = new PagesLoader();

        String[] files = args[0].split(" ");

        if (files.length != filesCount) {
            System.out.println("Bad number of arguments. Expected: oldVersionFile, newVersionFile");
        }

        Map<String, String> oldVersion = pagesLoader.load(files[0]);
        if (oldVersion == null) {
            return;
        }

        Map<String, String> newVersion = pagesLoader.load(files[1]);
        if (newVersion == null) {
            return;
        }

        VersionComparator versionComparator = new VersionComparator();
        MessageFormatter messageFormatter = new MessageFormatter();

        System.out.println(messageFormatter.format(versionComparator.getChanges(oldVersion, newVersion)));
    }
}