package org.example.control;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Command implements Control {
    OFFER_DRAW,
    ACCEPT_DRAW,
    DECLINE_DRAW,
    RESIGN;

    private static final Map<String, Command> stringToCommandMap = new HashMap<>();

    static {
        Arrays.stream(Command.values()).forEach(command -> stringToCommandMap.put(command.name(), command));
    }

    public static Command fromString(String command) {
        return stringToCommandMap.get(command);
    }

    public static boolean isCommand(String command) {
        return stringToCommandMap.containsKey(command);
    }
}