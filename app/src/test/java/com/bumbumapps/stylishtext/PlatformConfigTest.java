package com.bumbumapps.stylishtext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.Test;

/** Regressions for targetSdk 34+: typed FGS, immutable PendingIntent, no legacy Play Core. */
public class PlatformConfigTest {
    private static String read(String rel) throws Exception {
        File f = new File(rel);
        if (!f.exists()) f = new File("app", rel);
        return new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
    }

    @Test
    public void bubbleServiceIsTypedSpecialUse() throws Exception {
        String m = read("src/main/AndroidManifest.xml");
        assertTrue(m.contains("android:foregroundServiceType=\"specialUse\""));
        assertTrue(m.contains("FOREGROUND_SERVICE_SPECIAL_USE"));
    }

    @Test
    public void notificationPendingIntentIsImmutable() throws Exception {
        String s = read("src/main/java/com/bumbumapps/stylishtext/floating/stylish/FloatingStylishService.java");
        assertTrue(s.contains("PendingIntent.FLAG_IMMUTABLE"));
    }

    @Test
    public void legacyPlayCoreIsGone() throws Exception {
        assertFalse(read("build.gradle").contains("com.google.android.play:core:"));
    }
}
