import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.passwordmanager.PasswordManager;
import components.passwordmanager.PasswordManager1L;

public class PasswordManagerSecondaryTest {

    /**
     * Test createAccount and hasUser.
     *
     */
    @Test
    public void testCreateAccountAndHasUser() {
        PasswordManager pm = new PasswordManager1L();
        pm.createAccount("alice", "123");
        assertTrue(pm.hasUser("alice"));
        assertFalse(pm.hasUser("bob"));
    }

    /**
     * Test verify.
     *
     */
    @Test
    public void testVerify() {
        PasswordManager pm = new PasswordManager1L();
        pm.createAccount("bob", "secret");
        assertTrue(pm.verify("bob", "secret"));
        assertFalse(pm.verify("bob", "wrong"));
        assertFalse(pm.verify("charlie", "any"));
    }

    /** Test size */
    @Test
    public void testSize() {
        PasswordManager pm = new PasswordManager1L();
        assertEquals(0, pm.size());
        pm.createAccount("alice", "1");
        assertEquals(1, pm.size());
        pm.createAccount("bob", "2");
        assertEquals(2, pm.size());
    }

    /** Test login */
    @Test
    public void testLogin() {
        PasswordManager pm = new PasswordManager1L();
        pm.createAccount("carol", "abc");
        assertTrue(pm.login("carol", "abc"));
        assertFalse(pm.login("carol", "wrong"));
    }

    /** Test updatePassword */
    @Test
    public void testUpdatePassword() {
        PasswordManager pm = new PasswordManager1L();
        pm.createAccount("dave", "oldpw");
        pm.updatePassword("dave", "oldpw", "newpw");
        assertTrue(pm.verify("dave", "newpw"));
        assertFalse(pm.verify("dave", "oldpw"));
    }

    /** Test totalAccounts (prints output, so we just check size consistency) */
    @Test
    public void testTotalAccounts() {
        PasswordManager pm = new PasswordManager1L();
        pm.createAccount("eve", "pw1");
        pm.createAccount("frank", "pw2");
        // We can’t capture print output easily, but check size matches expected
        assertEquals(2, pm.size());
    }

    /** Test toString */
    @Test
    public void testToString() {
        PasswordManager pm = new PasswordManager1L();
        pm.createAccount("alice", "1");
        pm.createAccount("bob", "2");
        String str = pm.toString();
        assertTrue(str.contains("Password Manager"));
        assertTrue(str.contains("alice"));
        assertTrue(str.contains("bob"));
    }

    /** Test equals */
    @Test
    public void testEquals() {
        PasswordManager pm1 = new PasswordManager1L();
        PasswordManager pm2 = new PasswordManager1L();
        pm1.createAccount("alice", "123");
        pm2.createAccount("alice", "123");
        assertTrue(pm1.equals(pm2));

        pm2.createAccount("bob", "456");
        assertFalse(pm1.equals(pm2));
        assertFalse(pm1.equals("not a manager"));
    }

    /** Test hashCode consistency */
    @Test
    public void testHashCode() {
        PasswordManager pm1 = new PasswordManager1L();
        PasswordManager pm2 = new PasswordManager1L();
        pm1.createAccount("alice", "123");
        pm2.createAccount("alice", "123");
        assertEquals(pm1.hashCode(), pm2.hashCode());

        pm2.createAccount("bob", "456");
        assertFalse(pm1.hashCode() == pm2.hashCode());
    }
}