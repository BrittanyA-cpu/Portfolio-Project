package components.passwordmanager;

import components.set.Set;
import components.standard.Standard;

/**
 * {@code PasswordManagerKernel} a representation of a password storing system
 * that connects usernames to hashed passwords.
 *
 *
 * @mathsubtypes <pre>
 * PasswordsManager is modeled as
 *  (accounts: finite set of (username: string, passwordHash: string))
 * </pre>
 *
 * @mathmodel <pre>
 *  Each username is unique and each password is stored as a hash.
 * </pre>
 */
public interface PasswordManagerKernel extends Standard<PasswordManager> {

    /**
     * Stores the password for a given username. Passwords should be hashed
     * before being stored.
     *
     * @param username
     *            the username string given to store
     * @param passwords
     *            the password (as given) for the username
     * @updates this
     * @requires username and password are not null
     * @ensures get(username) = hashed(password)
     */
    void store(String username, String password);

    /**
     * Returns the stored password (hashed) for the given username.
     *
     * @param username
     *            the username whose password is returned
     * @requires username is in DOMAIN(this)
     * @ensures get = this[username]
     * @return the hashed password for the given username
     */
    String get(String username);

    /**
     * Removes the account for the given username and returns its stored
     * password.
     *
     * @param username
     *            the username that is removed
     * @updates this
     * @requires username is in DOMAIN(this)
     * @ensures username is removed from DOMAIN(this)
     * @return the password hash for the removed username
     */
    String remove(String username);

    /**
     * Returns all the usernames currently stored.
     *
     * @ensures usernames = DOMAIN(this)
     * @return a set that contains all the usernames in the manager
     */
    Set<String> usernames();

    /**
     * Returns true if the given username exists in the system, false otherwise.
     *
     * @param username
     *            the given username to check
     * @requires username != null
     * @return True or False
     */
    boolean contains(String username);

    /*
     * Hashed the given password.
     *
     * @param the given password
     *
     * @requires password != null
     *
     * @ensures hashPassword = hashed version of given password
     *
     * @return hashed password string
     */
    String hashPassword(String password);
}