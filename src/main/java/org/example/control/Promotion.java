package org.example.control;

import org.example.piece.Piece;
import org.example.board.Board;
import org.example.board.Field;

public class Promotion implements Action {
    private final Field field;
    private final Piece promotedTo;

    public Promotion(Field field, Piece promotedTo) {
        this.field = field;
        this.promotedTo = promotedTo;
    }

    @Override
    public void perform(Board board) {
        board.removePiece(field);
        board.setPiece(field, this.getTo());
    }

    public Piece getTo() {
        return promotedTo;
    }
}