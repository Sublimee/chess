package org.example.board;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum File implements Serializable {
    A, B, C, D, E, F, G, H;

    private static final Map<Character, File> charToFileMap = new HashMap<>();

    static {
        Arrays.stream(File.values()).forEach(file -> charToFileMap.put(file.name().charAt(0), file));
    }

    public static File fromChar(char c) {
        return charToFileMap.get(Character.toUpperCase(c));
    }
}