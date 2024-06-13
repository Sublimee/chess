package org.example.control;

import org.example.board.Board;

public interface Action {

    void perform(Board board);
}