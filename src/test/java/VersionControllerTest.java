import nsu.maxwell.VersionComparator;
import nsu.maxwell.VersionInfo;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VersionControllerTest {
    private static VersionComparator versionComparator;
    private static Map<String, String> oldVersion;
    private static Map<String, String> newVersion;


    @BeforeClass
    public static void setUp() {
        versionComparator = new VersionComparator();
        oldVersion = new HashMap<>();
        newVersion = new HashMap<>();
    }

    @Before
    public void clean() {
        oldVersion.clear();
        newVersion.clear();
    }

    @Test
    public void testEmpty() {
        VersionInfo res = versionComparator.getChanges(oldVersion, newVersion);

        assertEquals(res.addedUrls().size(), 0);
        assertEquals(res.vanishedPages().size(), 0);
        assertEquals(res.editedPages().size(), 0);
    }

    @Test
    public void testEqualPages() {
        oldVersion.put("url1", "1");
        newVersion.put("url1", "1");

        VersionInfo res = versionComparator.getChanges(oldVersion, newVersion);

        assertEquals(res.addedUrls().size(), 0);
        assertEquals(res.vanishedPages().size(), 0);
        assertEquals(res.editedPages().size(), 0);
    }

    @Test
    public void testVanishedPageNumber() {
        oldVersion.put("url1", "1");
        oldVersion.put("url2", "1");
        oldVersion.put("url3", "1");

        VersionInfo res = versionComparator.getChanges(oldVersion, newVersion);
        assertEquals(res.vanishedPages().size(), 3);
    }

    @Test
    public void testEditedPageNumber() {
        oldVersion.put("url1", "1");
        newVersion.put("url1", "2");

        VersionInfo res = versionComparator.getChanges(oldVersion, newVersion);

        assertEquals(res.editedPages().size(), 1);
    }

    @Test
    public void testAddedPageNumber() {
        oldVersion.put("url1", "1");
        newVersion.put("url2", "2");

        VersionInfo res = versionComparator.getChanges(oldVersion, newVersion);

        assertEquals(res.addedUrls().size(), 1);
    }

    @Test
    public void testVanishedPage() {
        oldVersion.put("url1", "1");
        oldVersion.put("url2", "1");
        oldVersion.put("url3", "1");

        VersionInfo res = versionComparator.getChanges(oldVersion, newVersion);

        Set<String> expected = Set.of("url1", "url2", "url3");

        assertTrue(res.vanishedPages().containsAll(expected));
    }

    @Test
    public void testEditedPage() {
        oldVersion.put("url1", "1");
        oldVersion.put("url2", "1");
        oldVersion.put("url3", "1");

        newVersion.put("url1", "2");
        newVersion.put("url2", "2");
        newVersion.put("url3", "2");

        VersionInfo res = versionComparator.getChanges(oldVersion, newVersion);

        Set<String> expected = Set.of("url1", "url2", "url3");

        assertTrue(res.editedPages().containsAll(expected));
    }

    @Test
    public void testAddedPage() {
        newVersion.put("url1", "2");
        newVersion.put("url2", "2");
        newVersion.put("url3", "2");

        VersionInfo res = versionComparator.getChanges(oldVersion, newVersion);

        Set<String> expected = Set.of("url1", "url2", "url3");

        assertTrue(res.addedUrls().containsAll(expected));
    }


    @Test
    public void testAll() {
        oldVersion.put("url1", "1");
        oldVersion.put("url2", "1");

        newVersion.put("url1", "2");
        newVersion.put("url3", "2");

        VersionInfo res = versionComparator.getChanges(oldVersion, newVersion);

        Set<String> expectedVanished = Set.of("url2");
        Set<String> expectedEdited = Set.of("url1");
        Set<String> expectedAdded = Set.of("url3");

        assertTrue(res.addedUrls().containsAll(expectedAdded));
        assertTrue(res.editedPages().containsAll(expectedEdited));
        assertTrue(res.vanishedPages().containsAll(expectedVanished));
    }

}
