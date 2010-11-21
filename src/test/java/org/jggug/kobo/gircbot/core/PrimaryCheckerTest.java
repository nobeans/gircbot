package org.jggug.kobo.gircbot.core;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PrimaryCheckerTest {

    private PrimaryChecker primaryChecker;
    private String defaultChannel;
    private List<String> orderedPrimaryNicks;
    private IrcControl ircControl;
    private List<String> returnValueToGetJoinedNicks;
    private String expectedChannel;

    @Before
    public void before() throws Exception {
        // Setup arguments
        defaultChannel = "#default";
        orderedPrimaryNicks = Arrays.asList("bot1", "bot2", "bot3");
        // Setup mock
        ircControl = mock(IrcControl.class);
        when(ircControl.getNick()).thenReturn("bot2");
        // Setup SUT
        returnValueToGetJoinedNicks = Arrays.asList("bot1", "bot2", "bot3");
        expectedChannel = "#test";
        primaryChecker = new PrimaryChecker(defaultChannel, orderedPrimaryNicks, ircControl) {
            @Override
            protected List<String> getJoinedNicks(String channel) {
                assertEquals(expectedChannel, channel);
                return returnValueToGetJoinedNicks;
            }
        };
    }

    @Test
    public void isPrimary_existingPriorerNickThanMe_toBeFalse() throws Exception {
        // Exercise & Verify
        assertFalse(primaryChecker.isPrimary("#test"));
    }

    @Test
    public void isPrimary_notExistingPriorerNickThanMe_toBeTrue() throws Exception {
        // Setup
        returnValueToGetJoinedNicks = Arrays.asList("bot2", "bot3");
        // Exercise & Verify
        assertTrue(primaryChecker.isPrimary("#test"));
    }

    @Test
    public void isPrimary_notExistingMe_toThrowException() throws Exception {
        // Setup
        returnValueToGetJoinedNicks = Arrays.asList("bot3");
        // Exercise & Verify
        try {
            primaryChecker.isPrimary("#test");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("why isn't there this bot in joinedNicks? : bot2 in [bot3]", e.getMessage());
        }
    }

    @Test
    public void isPrimary_notExistingAnyone_toThrowException() throws Exception {
        // Setup
        returnValueToGetJoinedNicks = Arrays.<String>asList();
        // Exercise & Verify
        try {
            primaryChecker.isPrimary("#test");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("why isn't there this bot in joinedNicks? : bot2 in []", e.getMessage());
        }
    }

    @Test
    public void isPrimary_emptyOrderedPrimaryNicks_toThrowException() throws Exception {
        // Setup
        primaryChecker.orderedPrimaryNicks = Arrays.<String>asList();
        // Exercise & Verify
        try {
            primaryChecker.isPrimary("#test");
            fail();
        } catch (IllegalStateException e) {
            assertEquals("why isn't there this bot in orderedPrimaryNicks? : bot2 in []", e.getMessage());
        }
    }

    @Test
    public void isPrimaryGlobally_existingPriorerNickThanMe_toBeFalse() throws Exception {
        // Setup
        expectedChannel = "#default";
        // Exercise & Verify
        assertFalse(primaryChecker.isPrimaryGlobally());
    }

    @Test
    public void isPrimaryGlobally_notExistingPriorerNickThanMe_toBeTrue() throws Exception {
        // Setup
        returnValueToGetJoinedNicks = Arrays.asList("bot2", "bot3");
        expectedChannel = "#default";
        // Exercise & Verify
        assertTrue(primaryChecker.isPrimaryGlobally());
    }

}
