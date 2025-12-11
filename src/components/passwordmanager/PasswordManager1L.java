package components.passwordmanager;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;

/**
 * Kernel implementation of a password manager.
 */
public class PasswordManager1L extends PasswordManagerSecondary {

    /*
     * @convention accounts != null for all u: String in DOMAIN($this.accounts):
     * u != null, $this.accounts.value(u) != null, and for all p in
     * $this.accounts.value(u): p != null, and $this.accounts.value(u) is not
     * empty
     */

    /*
     * @correspondence DOMAIN(this) = DOMAIN($this.accounts). For all u in
     * DOMAIN(this): this(u) = $this.accounts.value(u)
     */

    /**
     * Representation of the password manager.
     */
    private Map<String, Set<String>> accounts;

    /**
     * Constructor. Initializes an empty password manager.
     */
    public PasswordManager1L() {
        this.accounts = new Map1L<>();
    }

    @Override
    public final PasswordManager createNewRep() {
        return new PasswordManager1L();
    }

    @Override
    public final PasswordManager newInstance() {
        try {
            return (PasswordManager) this.getClass().getConstructor()
                    .newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass(), e);
        }
    }

    @Override
    public final void transferFrom(PasswordManager original) {
        assert original instanceof PasswordManager1L : "Violation of: original is of dynamic type PasswordManager1L";

        PasswordManager1L local = (PasswordManager1L) original;
        this.accounts = local.accounts;
        local.accounts = new Map1L<>();
    }

    @Override
    public final void clear() {
        this.accounts.clear();
    }

    @Override
    public final void store(String username, String password) {
        assert username != null
                && password != null : "Violation of: username and password are not null";

        String hashedPassword = this.hashPassword(password);

        if (this.accounts.hasKey(username)) {
            Set<String> passwords = this.accounts.value(username);
            passwords.add(hashedPassword);
        } else {
            Set<String> passwords = new Set1L<>();
            passwords.add(hashedPassword);
            this.accounts.add(username, passwords);
        }
    }

    @Override
    public final Set<String> get(String username) {
        assert this.accounts
                .hasKey(username) : "Violation of: username is in DOMAIN(this)";
        Set<String> original = this.accounts.value(username);
        Set<String> copy = new Set1L<>();
        for (String p : original) {
            copy.add(p);
        }
        return copy;
    }

    @Override
    public final Set<String> remove(String username) {
        assert this.accounts
                .hasKey(username) : "Violation of: username is in DOMAIN(this)";

        Set<String> removedPasswords = this.accounts.value(username);
        this.accounts.remove(username);

        Set<String> copy = new Set1L<>();
        for (String p : removedPasswords) {
            copy.add(p);
        }

        return copy;
    }

    @Override
    public final Set<String> usernames() {
        Set<String> result = new Set1L<>();
        for (Map.Pair<String, Set<String>> p : this.accounts) {
            result.add(p.key());
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

        StringBuilder reversed = new StringBuilder(password);
        reversed.reverse();

        return Integer.toString(reversed.toString().hashCode());
    }
}