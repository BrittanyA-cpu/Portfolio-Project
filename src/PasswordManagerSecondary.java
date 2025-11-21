package components.passwordmanager;

import components.set.Set;

/**
 * Implementations of secondary methods for {@code PasswordManager}
 */

public abstract class PasswordManagerSecondary implements PasswordManager {

    @Override
    public boolean hasUser(String username) {
        assert username != null : "Violation of: username is not null";

        return this.contains(username);

    }

    @Override
    public boolean verify(String username, String password) {
        assert username != null
                && password != null : "Violation of: username and password are not null";

        if (!this.contains(username)) {
            return false;
        }

        String storedPassword = this.get(username);
        String givenPassword = this.hashPassword(password);

        return storedPassword.equals(givenPassword);
    }

    @Override
    public int size() {
        Set<String> allUsernames = this.usernames();
        return allUsernames.size();
    }

    @Override
    public void createAccount(String username, String password) {
        assert username != null
                && password != null : "Violation of: username and password are not null";
        assert !this.contains(
                username) : "Violation of: username not already in DOMAIN(this)";

        if (!this.contains(username)) {
            this.store(username, this.hashPassword(password));
        }
    }

    @Override
    public boolean login(String username, String password) {
        assert username != null
                && password != null : "Violation of: username and password are not null";

        return this.verify(username, password);
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        assert username != null
                && newPassword != null : "Violation of: username and newPassword are not null";
        assert this
                .contains(username) : "Violation of: username in DOMAIN(this)";

        this.remove(username);
        this.store(username, this.hashPassword(newPassword));
    }

    @Override
    public void totalAccounts() {
        System.out.println("Total account:" + this.size());
    }

    @Override
    public String toString() {
        return "Password Manager with" + this.size() + "accounts: "
                + this.usernames();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PasswordManager)) {
            return false;
        }
        PasswordManager m = (PasswordManager) obj;
        if (this.size() != m.size()) {
            return false;
        }

        for (String username : this.usernames()) {
            if (!m.contains(username)) {
                return false;
            }
            if (!this.get(username).equals(m.get(username))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int code = 0;

        for (String username : this.usernames()) {
            code += username.hashCode();
            code += this.get(username).hashCode();
        }
        return code;
    }

}
