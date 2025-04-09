package org.sopt.utils;

public class IdGenerator {
    private static int id = 0;

    public static void initialize(int maxId) {
        id = maxId;
    }
    public static int generateId() {
        return id++;
    }
}
