package com.seamlessfactions.seamlesslinks;

public class Config {
    public static boolean debug = SeamlessLinks.getInstance().getConfig().getBoolean("debug");
    public static boolean ignore_cancelled = SeamlessLinks.getInstance().getConfig().getBoolean("ignore-cancelled");
}
