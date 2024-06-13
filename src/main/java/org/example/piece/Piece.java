package org.example.piece;

import java.io.Serializable;

public abstract class Piece implements Serializable {
    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
