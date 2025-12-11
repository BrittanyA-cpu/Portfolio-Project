package components.passwordmanager;

import components.set.Set;
import components.standard.Standard;

/**
 * {@code PasswordManagerKernel} a representation of a password storing system
 * that connects usernames to hashed passwords.
 *
 *
 * @mathsubtypes <pre>
 * PasswordManager is modeled as
 *  (accounts: finite set of (username: string, passwordHashes: set of strings))
 * </pre>
 *
 * @mathmodel <pre>
 *  Each username is unique and each password is stored as a hash.
 * </pre>
 */
public interface PasswordManagerKernel extends Standard<PasswordManager> {

    /**
     * Stores the password for a given username. Passwords should be hashed
     * before being stored. If the username already exists, the password is
     * added to the set of existing passwords.
     *
     * @param username
     *            the username string given to store
     * @param password
     *            the password (as given) for the username
     * @updates this
     * @requires username and password are not null
     * @ensures get(username) = hashedPassword(password)
     */
    void store(String username, String password);

    /**
     * Returns all of the stored passwords (hashed) for the given username.
     *
     * @param username
     *            the username whose passwords are returned
     * @requires username is in DOMAIN(this) and there is at least one password
     *           stored for username
     * @ensures get = all of the stored passwords
     * @return a set if hashed passwords for the given username
     */
    Set<String> get(String username);

    /**
     * Removes the account for the given username and returns one of its stored
     * passwords.
     *
     * @param username
     *            the username that is removed
     * @updates this
     * @requires username is in DOMAIN(this)
     * @ensures username is removed from DOMAIN(this)
     * @return a set of all passwords hashes for the removed username
     */
    Set<String> remove(String username);

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
     * @return True if username exists, or False if it does not exist
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