import components.passwordmanager.PasswordManager;
import components.passwordmanager.PasswordManager1L;

public class PasswordManagerDemo1 {
    public static void main(String[] args) {
        PasswordManager pm = new PasswordManager1L();

        pm.createAccount("alice", "password123");
        pm.createAccount("bob", "qwerty");

        System.out.println(
                "Alice login successful? " + pm.login("alice", "password123"));
        System.out.println(
                "Bob login successful? " + pm.login("bob", "wrongpassword"));

        pm.updatePassword("bob", "qwerty", "newpass");
        System.out.println("Bob login successful after update? "
                + pm.login("bob", "newpass"));

        System.out.println("All users: " + pm.usernames());
    }
}