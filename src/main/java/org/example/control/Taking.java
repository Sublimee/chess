package org.example.control;

import org.example.board.Board;
import org.example.board.Field;
import org.example.piece.Piece;

public class Taking extends Moving {
    private final Piece taken;

    public Taking(Field from, Field to, Piece piece, Piece taken) {
        super(from, to, piece);
        this.taken = taken;
    }

    @Override
    public void perform(Board board) {
        board.removePiece(this.getTo());
        board.removePiece(this.getFrom());
        board.setPiece(this.getTo(), this.getPiece());
    }

    public Piece getTaken() {
        return taken;
    }
}