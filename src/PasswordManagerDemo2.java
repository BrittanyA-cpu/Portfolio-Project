import components.passwordmanager.PasswordManager;
import components.passwordmanager.PasswordManager1L;
import components.set.Set;

public class PasswordManagerDemo2 {
    public static void main(String[] args) {
        PasswordManager pm = new PasswordManager1L();

        String[] users = { "alice", "bob", "carol" };
        for (String user : users) {
            pm.createAccount(user, "initpass");
        }

        System.out.println("Total accounts: " + pm.size());

        for (String user : users) {
            Set<String> hashedPasswords = pm.get(user);
            System.out.println(user + "'s passwords: " + hashedPasswords);
        }

        pm.remove("carol");
        System.out.println("Users after removing Carol: " + pm.usernames());
    }
}