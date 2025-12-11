import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.passwordmanager.PasswordManager;
import components.passwordmanager.PasswordManager1L;
import components.set.Set;

public class PasswordManager1LTest {

    /** Test store and contains */
    @Test
    public void testStoreAndContains() {
        PasswordManager pm = new PasswordManager1L();
        pm.store("alice", "pw1");
        assertTrue(pm.contains("alice"));
        assertFalse(pm.contains("bob"));
    }

    /** Test get */
    @Test
    public void testGet() {
        PasswordManager pm = new PasswordManager1L();
        pm.store("bob", "pw2");
        Set<String> passwords = pm.get("bob");
        assertTrue(passwords.contains(pm.hashPassword("pw2")));
        // ensure copy is returned
        passwords.add("hack");
        Set<String> passwordsAfter = pm.get("bob");
        assertFalse(passwordsAfter.contains("hack"));
    }

    /** Test remove */
    @Test
    public void testRemove() {
        PasswordManager pm = new PasswordManager1L();
        pm.store("carol", "pw3");
        Set<String> removed = pm.remove("carol");
        assertTrue(removed.contains(pm.hashPassword("pw3")));
        assertFalse(pm.contains("carol"));
    }

    /** Test usernames */
    @Test
    public void testUsernames() {
        PasswordManager pm = new PasswordManager1L();
        pm.store("alice", "pw1");
        pm.store("bob", "pw2");
        Set<String> users = pm.usernames();
        assertTrue(users.contains("alice"));
        assertTrue(users.contains("bob"));
        assertEquals(2, users.size());
    }

    @Test
    public void testHashPassword() {
        PasswordManager pm = new PasswordManager1L();

        String password = "password123";
        String expectedHash = Integer.toString(
                new StringBuilder(password).reverse().toString().hashCode());

        assertEquals(expectedHash, pm.hashPassword(password));
    }

    /** Test createNewRep */
    @Test
    public void testCreateNewRep() {
        PasswordManager pm = new PasswordManager1L();
        PasswordManager newRep = pm.createNewRep();
        assertTrue(newRep instanceof PasswordManager1L);
        assertEquals(0, newRep.size());
    }

    /** Test newInstance */
    @Test
    public void testNewInstance() {
        PasswordManager pm = new PasswordManager1L();
        PasswordManager instance = pm.newInstance();
        assertTrue(instance instanceof PasswordManager1L);
        assertEquals(0, instance.size());
    }

    /** Test transferFrom */
    @Test
    public void testTransferFrom() {
        PasswordManager pm1 = new PasswordManager1L();
        PasswordManager pm2 = new PasswordManager1L();
        pm1.store("alice", "pw1");
        pm1.store("bob", "pw2");
        pm2.transferFrom(pm1);
        assertTrue(pm2.contains("alice"));
        assertTrue(pm2.contains("bob"));
        assertEquals(0, pm1.size());
    }

    /** Test clear */
    @Test
    public void testClear() {
        PasswordManager pm = new PasswordManager1L();
        pm.store("alice", "pw1");
        pm.store("bob", "pw2");
        pm.clear();
        assertEquals(0, pm.size());
    }
}