package org.example.control;

import org.example.board.Board;
import org.example.board.Field;
import org.example.piece.Piece;

public class Moving implements Action {

    private final Field from;
    private final Field to;
    private final Piece piece;

    public Moving(Field from, Field to, Piece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public Field getFrom() {
        return from;
    }

    public Field getTo() {
        return to;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public void perform(Board board) {
        board.removePiece(from);
        board.setPiece(to, piece);
    }
}
