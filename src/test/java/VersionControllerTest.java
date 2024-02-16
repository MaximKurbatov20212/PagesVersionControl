import nsu.maxwell.VersionController;
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
    private static VersionController versionController;
    private static Map<String, String> oldVersion;
    private static Map<String, String> newVersion;


    @BeforeClass
    public static void setUp() {
        versionController = new VersionController();
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
        VersionInfo res = versionController.getChanges(oldVersion, newVersion);

        assertEquals(res.added().size(), 0);
        assertEquals(res.vanished().size(), 0);
        assertEquals(res.edited().size(), 0);
    }

    @Test
    public void testEqualPages() {
        oldVersion.put("url1", "1");
        newVersion.put("url1", "1");

        VersionInfo res = versionController.getChanges(oldVersion, newVersion);

        assertEquals(res.added().size(), 0);
        assertEquals(res.vanished().size(), 0);
        assertEquals(res.edited().size(), 0);
    }

    @Test
    public void testVanishedPageNumber() {
        oldVersion.put("url1", "1");
        oldVersion.put("url2", "1");
        oldVersion.put("url3", "1");

        VersionInfo res = versionController.getChanges(oldVersion, newVersion);
        assertEquals(res.vanished().size(), 3);
    }

    @Test
    public void testEditedPageNumber() {
        oldVersion.put("url1", "1");
        newVersion.put("url1", "2");

        VersionInfo res = versionController.getChanges(oldVersion, newVersion);

        assertEquals(res.edited().size(), 1);
    }

    @Test
    public void testAddedPageNumber() {
        oldVersion.put("url1", "1");
        newVersion.put("url2", "2");

        VersionInfo res = versionController.getChanges(oldVersion, newVersion);

        assertEquals(res.added().size(), 1);
    }

    @Test
    public void testVanishedPage() {
        oldVersion.put("url1", "1");
        oldVersion.put("url2", "1");
        oldVersion.put("url3", "1");

        VersionInfo res = versionController.getChanges(oldVersion, newVersion);

        Set<String> expected = Set.of("url1", "url2", "url3");

        assertTrue(res.vanished().containsAll(expected));
    }

    @Test
    public void testEditedPage() {
        oldVersion.put("url1", "1");
        oldVersion.put("url2", "1");
        oldVersion.put("url3", "1");

        newVersion.put("url1", "2");
        newVersion.put("url2", "2");
        newVersion.put("url3", "2");

        VersionInfo res = versionController.getChanges(oldVersion, newVersion);

        Set<String> expected = Set.of("url1", "url2", "url3");

        assertTrue(res.edited().containsAll(expected));
    }

    @Test
    public void testAddedPage() {
        newVersion.put("url1", "2");
        newVersion.put("url2", "2");
        newVersion.put("url3", "2");

        VersionInfo res = versionController.getChanges(oldVersion, newVersion);

        Set<String> expected = Set.of("url1", "url2", "url3");

        assertTrue(res.added().containsAll(expected));
    }


    @Test
    public void testAll() {
        oldVersion.put("url1", "1");
        oldVersion.put("url2", "1");

        newVersion.put("url1", "2");
        newVersion.put("url3", "2");

        VersionInfo res = versionController.getChanges(oldVersion, newVersion);

        Set<String> expectedVanished = Set.of("url2");
        Set<String> expectedEdited = Set.of("url1");
        Set<String> expectedAdded = Set.of("url3");

        assertTrue(res.added().containsAll(expectedAdded));
        assertTrue(res.edited().containsAll(expectedEdited));
        assertTrue(res.vanished().containsAll(expectedVanished));
    }

}
