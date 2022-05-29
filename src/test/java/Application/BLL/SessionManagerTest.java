package Application.BLL;

import junit.framework.TestCase;

public class SessionManagerTest extends TestCase {

    public void testExtendToLength()
    {
        // test that zero length is handled correctly
        String token = SessionManager.extendToLength("test", 0);
        assertEquals("", token);

        // test that null input is handled correctly
        token = SessionManager.extendToLength(null, 5);
        assertEquals("", token);

        // test negative length is handled correctly
        token = SessionManager.extendToLength("test", -1);
        assertEquals("t", token);

        // test expansion
        token = SessionManager.extendToLength("test", 10);
        assertEquals("testtestte", token);

        // test truncation
        token = SessionManager.extendToLength("A_very_long_username", 16);
        assertEquals("A_very_long_user", token);
    }


    public void testCreateToken()
    {
        // test creation of token
        String token = SessionManager.createToken("test", "test");
        assertNotNull(token);

        var adminHash = "JDJ5JDA0JFdVUHJZVTNmWEV6blprRGlaVWpzV081VDJzMDlvczVKVjh0aGVPRldPamNkazVMOUJlZlJL";

        // test that the token is valid, against a known hash
        token = SessionManager.createToken("admin", "admin");
        assertEquals(adminHash, token);

        // test that the token is invalid, with a different username but same password
        token = SessionManager.createToken("admin2", "admin");
        assertNotSame(adminHash, token);
    }
}