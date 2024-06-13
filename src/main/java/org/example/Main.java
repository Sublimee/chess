package org.example;

import org.example.ui.ConsoleEngine;
import org.example.ui.InputProcessor;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        InputProcessor inputProcessor = new InputProcessor();
        ConsoleEngine consoleEngine = new ConsoleEngine(game, inputProcessor);
        consoleEngine.loop();
    }
}