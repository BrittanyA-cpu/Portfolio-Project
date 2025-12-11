package components.passwordmanager;

/**
 * {@code NaturalNumberKernel} enhanced with additional methods.
 *
 *
 * @mathsubtypes <pre>
 * PasswordManager is modeled as
 *   (accounts: finite set of (username: string, passwordHashes: set of string))
 * </pre>
 *
 * @mathmodel <pre>
 * Each username is unique and each password is stored as a hash.
 * Each username may have multiple passwords associated with it.
 * </pre>
 **/

public interface PasswordManager extends PasswordManagerKernel {

    /**
     * Checks if the given username exists.
     *
     * @param username
     *            the username that will be checked
     * @requires username != null
     *
     * @ensures isUser = (username is in DOMAIN(this))
     *
     * @return true if username exists, false if not
     */
    boolean hasUser(String username);

    /**
     *
     * @param username
     *            the username that needs to be verified
     * @param password
     *            the password (in plaintext) to verify
     * @requires username != null and password != null
     *
     * @ensures isVerified = (username is in DOMAIN(this) and this[username] =
     *          hashedPasswords(password))
     *
     * @return true if the input matches on eof the stores passwords, false if
     *         not
     */
    boolean verify(String username, String password);

    /**
     * @ensures count = |DOMAIN(this)|
     *
     * @return the number of usernames stored in the manager
     */
    int size();

    /**
     * Creates a new account if the username does not exist already.
     *
     * @param username
     *            the username for the account
     * @param password
     *            the given password for the new account
     * @updates this
     *
     * @requires username != null and password != null and username not in
     *           DOMAIN(this)
     *
     * @ensures username is added to DOMAIN(this) with a set containing its
     *          hashed passwords
     */
    void createAccount(String username, String password);

    /**
     * Logs in using the given username and password if they match with the
     * stored credentials.
     *
     *
     * @param username
     *            the username given to log in
     * @param password
     *            the password given to verify
     *
     * @requires username != null and password != null
     *
     * @ensures result = (username is in DOMAIN(this) and this[username] =
     *          hashed(password)
     *
     * @return true if log in was successful, false if not
     */
    boolean login(String username, String password);

    /**
     * Replaces a specific old password with a new password for an existing
     * account.
     *
     * @param username
     *            the account username
     * @param oldPassword
     *            the password to be replaced
     * @param newPassword
     *            the new password
     * @updates this
     * @requires username in DOMAIN(this) and oldPassword is in this[username]
     *           and newPassword != null
     * @ensures oldPassword is replaced by newPassword in this[username]
     */
    void updatePassword(String username, String oldPassword,
            String newPassword);

    /**
     * Prints the total under of accounts stored. Calls {@code size}.
     *
     * @ensures output = "Total accounts: " + |DOMAIN(this)|
     */
    void totalAccounts();

    PasswordManager createNewRep();

}
