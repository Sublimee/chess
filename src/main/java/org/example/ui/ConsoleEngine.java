package org.example.ui;

import org.example.Game;

import static org.example.ui.InputProcessor.PROCESS_INPUT_ERR;
import static org.example.ui.OutputProcessor.showBoard;
import static org.example.ui.OutputProcessor.showErrorInputMessage;
import static org.example.ui.OutputProcessor.showState;

public class ConsoleEngine {

    private final Game game;
    private final InputProcessor inputProcessor;

    public ConsoleEngine(Game game, InputProcessor inputProcessor) {
        this.game = game;
        this.inputProcessor = inputProcessor;
    }

    public void loop() {
        do {
            showBoard(game.getBoard());
            showState(game.getState());
            inputProcessor.processInput(game);
            if (inputProcessor.getProcessInputStatus() == PROCESS_INPUT_ERR){
                showErrorInputMessage();
            }
        } while (!game.isEndGame());
        showState(game.getState());
    }
}