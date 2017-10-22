package io.yuri.yuriserver.utils;

public class ServerLog {

    public static void DebugLog(String log) {
        System.out.println("Debug.Log: " + log);
    }

    public static void LogError(String log) {
        System.out.println("LogError: " + log);
    }
}
