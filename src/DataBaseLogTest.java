import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import utilities.FullStackException;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseLogTest {

    DatabaseLog db;
    DatabaseLog small;
    String e1 = "1 - LOGIN - Marina - 09-28-26_11:00";
    String e2 = "2 - EDIT - Walter - 09-28-26_11:30";
    String e3 = "3 - LOGOUT - Marina - 09-28-26_12:00";
    String e10 = "10 - EDIT - Walter - 09-29-26_10:00";

    @BeforeEach
    public void setUp() {
        db = new DatabaseLog();
        small = new DatabaseLog(2);
    }

    @Test
    public void testDefaultConstructor() {
        for (int i = 0; i < 10; i++) {
            db.addLog(e1);
        }
        assertEquals(e1, db.viewLog());
        Assertions.assertThrows(FullStackException.class, () -> {
            db.addLog(e2);
        });
        assertEquals(e1, db.removeLog());
    }

    @Test
    public void testCapacityConstructor() {
        small.addLog(e1);
        small.addLog(e2);
        assertEquals(e2, small.viewLog());
        Assertions.assertThrows(FullStackException.class, () -> {
            small.addLog(e3);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DatabaseLog(0);
        });
    }

    @Test
    public void testAddLog() {
        db.addLog(e1);
        assertEquals(e1, db.viewLog());
        db.addLog(e2);
        assertEquals(e2, db.viewLog());
        db.addLog(e3);
        assertEquals(e3, db.viewLog());
    }

    @Test
    public void testViewLog() {
        Assertions.assertThrows(EmptyStackException.class, () -> {
            db.viewLog();
        });
        db.addLog(e1);
        assertEquals(e1, db.viewLog());
        assertEquals(e1, db.viewLog());
        db.addLog(e2);
        assertEquals(e2, db.viewLog());
    }

    @Test
    public void testRemoveLog() {
        db.addLog(e1);
        db.addLog(e2);
        db.addLog(e3);
        assertEquals(e3, db.removeLog());
        assertEquals(e2, db.removeLog());
        assertEquals(e1, db.removeLog());
        Assertions.assertThrows(EmptyStackException.class, () -> {
            db.removeLog();
        });
    }

    @Test
    public void testGetLogID() {
        assertEquals("1", db.getLogID(e1));
        assertEquals("2", db.getLogID(e2));
        assertEquals("10", db.getLogID(e10));
    }

    @Test
    public void testGetAction() {
        assertEquals("LOGIN", db.getAction(e1));
        assertEquals("EDIT", db.getAction(e2));
        assertEquals("LOGOUT", db.getAction(e3));
    }

    @Test
    public void testGetName() {
        assertEquals("Marina", db.getName(e1));
        assertEquals("Walter", db.getName(e2));
        assertEquals("Walter", db.getName(e10));
    }

    @Test
    public void testGetTimestamp() {
        assertArrayEquals(new String[]{"09", "28", "26", "11", "00"}, db.getTimestamp(e1));
        assertArrayEquals(new String[]{"09", "28", "26", "11", "30"}, db.getTimestamp(e2));
        assertArrayEquals(new String[]{"09", "29", "26", "10", "00"}, db.getTimestamp(e10));
    }

    @Test
    public void testSummarizeLog() {
        assertEquals("ID: 1\nACTION: LOGIN\nNAME: Marina\nTIMESTAMP: 09-28-26_11:00",
                db.summarizeLog(e1));
        assertEquals("ID: 2\nACTION: EDIT\nNAME: Walter\nTIMESTAMP: 09-28-26_11:30",
                db.summarizeLog(e2));
        assertEquals("ID: 10\nACTION: EDIT\nNAME: Walter\nTIMESTAMP: 09-29-26_10:00",
                db.summarizeLog(e10));
    }

    @Test
    public void testGetRecentLogs() {
        db.addLog(e1);
        db.addLog(e2);
        db.addLog(e3);
        assertArrayEquals(new String[]{e3, e2}, db.getRecentLogs(2));
        assertEquals(e1, db.viewLog());
        assertArrayEquals(new String[]{e1}, db.getRecentLogs(5));
    }

    @Test
    public void testGetLogsForStudent() {
        db.addLog(e1);
        db.addLog(e2);
        db.addLog(e3);
        assertArrayEquals(new String[]{e3, e1}, db.getLogsForStudent("Marina"));

        DatabaseLog db2 = new DatabaseLog();
        db2.addLog(e1);
        db2.addLog(e2);
        db2.addLog(e3);
        assertArrayEquals(new String[]{e2}, db2.getLogsForStudent("Walter"));

        DatabaseLog db3 = new DatabaseLog();
        db3.addLog(e1);
        assertEquals(0, db3.getLogsForStudent("Jacob").length);

        assertEquals(0, new DatabaseLog().getLogsForStudent("Marina").length);
    }
}