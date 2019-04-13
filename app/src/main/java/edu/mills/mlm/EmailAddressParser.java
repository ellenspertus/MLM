package edu.mills.mlm;

import java.util.regex.Pattern;

/**
 * Static utility class for parsing and validating email addresses.
 */
public class EmailAddressParser {
    // Source: https://stackoverflow.com/a/8204716/631051
    // This does not work for non-ASCII characters in username.
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                    Pattern.CASE_INSENSITIVE);

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
        return email != null && VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
    }
}
