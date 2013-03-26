package org.jggug.kobo.gircbot.core;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageUtilsTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        MessageUtils.loadBundle("test");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        MessageUtils.loadBundle(MessageUtils.BUNDLE_NAMES.get(1)); // messages.properties
    }

    @Test
    public void getMessage_hello() throws Exception {
        assertEquals("Hello, world!", MessageUtils.getMessage("hello", "world"));
    }

    @Test
    public void getMessage_bye() throws Exception {
        assertEquals("Good bye, world!", MessageUtils.getMessage("bye", "world"));
    }

    @Test
    public void getMessage_count() throws Exception {
        assertEquals("Count: 123", MessageUtils.getMessage("count", 123));
    }

    @Test
    public void getMessage_UTF8() throws Exception {
        assertEquals("こんにちは、世界！", MessageUtils.getMessage("hello.ja", "世界"));
    }

}
