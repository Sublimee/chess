package org.example.control;

import org.example.board.Board;

import java.util.List;

public class Move implements Control {
    private final List<Action> actions;

    public Move(List<Action> actions) {
        this.actions = actions;
    }

    public void make(Board board) {
        actions.forEach(x -> x.perform(board));
    }
}