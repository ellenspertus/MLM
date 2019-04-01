package edu.mills.mlm;

/**
 * Static utility class for parsing and validating email addresses.
 */
public class EmailAddressParser {
    private EmailAddressParser() {}

    /**
     * Tests whether an email address is syntactically valid.
     * The criteria used are those specified in
     * <a href="https://tools.ietf.org/html/rfc5321">RFC 5321</a>.
     *
     * @param email the email address to test
     * @return {@code true} if the email address is valid,
     * {@code false} otherwise
     */
    public static boolean isValid(String email) {
        return false;
    }
}
