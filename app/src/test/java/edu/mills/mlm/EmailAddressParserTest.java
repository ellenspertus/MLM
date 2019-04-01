package edu.mills.mlm;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EmailAddressParserTest {
    @Test
    public void isValidRejectsNull() {
        assertFalse(EmailAddressParser.isValid(null));
    }

    @Test
    public void isValidRejectsEmptyString() {
        assertFalse(EmailAddressParser.isValid(""));
    }

    @Test
    public void isValidRejectsNoHost() {
        testIsValid("susan", false);
        testIsValid("first.last", false);
    }

    private void testIsValid(String email, boolean expected) {
        assertEquals("isValid(\"" + email + "\") returned " + !expected,
                expected,
                EmailAddressParser.isValid(email));
    }

    @Test
    public void isValidAcceptsTwoPartHosts() {
        testIsValid("susan@mills.edu", true);
        testIsValid("ellen@crackpot.com", true);
    }

    /*

    @Test
    public void isValidAcceptsSimpleEmailAddresses() {
        List<String> emails = Arrays.asList("susan@mills.edu", "ellen@crackpot.com");
        for (String email : emails) {
            assertTrue("isValid(\"" + email + "\") returned false, ",
                    EmailAddressParser.isValid(email));
        }
    }

    @Test
    public void isValidRejectsNoAtSign() {
        List<String> emails = Arrays.asList("susan.mills.edu", "ellen-at-crackpot.com");
        for (String email : emails) {
            assertFalse("isValid(\"" + email + "\") returned true, ",
                    EmailAddressParser.isValid(email));
        }
    }
    */

}