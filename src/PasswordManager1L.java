package components.passwordmanager;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;

public class PasswordManager1L extends PasswordManagerSecondary {
    /*
     * Representation of the password manager.
     */
    private Map<String, String> accounts;

    /**
     * Constructor. Initaializes an empty password manager.
     */
    public PasswordManager1L() {
        this.accounts = new Map1L<>();
    }

    @Override
    public final PasswordManager createNewRep() {
        return new PasswordManager1L();
    }

    @Override
    public final void clear() {
        this.accounts.clear();
    }

    @Override
    public final void store(String username, String password) {
        assert username != null
                && password != null : "Violation of: username and password are not null";

        if (this.accounts.hasKey(username)) {
            this.accounts.remove(username);
        }
        this.accounts.add(username, password);
    }

    @Override
    public final String get(String username) {
        assert this.accounts
                .hasKey(username) : "Violation of: username is in DOMAIN(this)";
        return this.accounts.value(username);
    }

    @Override
    public final String remove(String username) {
        assert this.accounts
                .hasKey(username) : "Violation of: username is in DOMAIN(this)";

        String removedPassword = this.accounts.value(username);
        this.accounts.remove(username);
        return removedPassword;
    }

    @Override
    public final Set<String> usernames() {
        Set<String> result = new Set1L<>();
        for (Map.Pair<String, String> i : this.accounts) {
            result.add(i.key());
        }

        return result;

    }

    @Override
    public final boolean contains(String username) {
        assert username != null : "Violation of: username is not null";

        return this.accounts.hasKey(username);
    }

    @Override
    public final String hashPassword(String password) {
        assert password != null : "Violation of: password is not null";

        String reversed = "";
        for (int i = password.length() - 1; i >= 0; i--) {
            reversed += password.charAt(i);
        }

        int firstHash = reversed.hashCode();

        int secondHash = Integer.toString(firstHash).hashCode();

        return Integer.toString(secondHash);
    }

}
