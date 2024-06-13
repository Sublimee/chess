package org.example.state;


public enum State {
    ONGOING(false),
    DRAW_OFFERED(false),
    DRAW(true),
    RESIGN(true),
    CHECKMATE(true),
    STALEMATE(true);

    private final boolean isEndGame;

    State(boolean isEndGame) {
        this.isEndGame = isEndGame;
    }

    public boolean isEndGame() {
        return isEndGame;
    }
}