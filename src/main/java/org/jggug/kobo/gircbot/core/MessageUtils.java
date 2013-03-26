package org.jggug.kobo.gircbot.core;

import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageUtils {

    protected static final List<String> BUNDLE_NAMES = Arrays.asList("gircbot-messages", "messages");
    private static final ResourceBundle.Control control = new UTF8ResouceBundleControl();
    private static ResourceBundle bundle;

    static void loadBundle(String bundleName) {
        bundle = ResourceBundle.getBundle(bundleName, control);
    }

    static {
        for (String bundleName : BUNDLE_NAMES) {
            try {
                loadBundle(bundleName);
            } catch (MissingResourceException e) {
                // do nothing
            }
        }
        assert bundle != null : "'messages' bundle should be bundled at least.";
    }

    public static String getMessage(String key, Object... args) {
        return String.format(bundle.getString(key), args);
    }
}
