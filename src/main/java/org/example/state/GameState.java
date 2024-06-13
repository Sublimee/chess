package org.example.state;

import static org.example.state.State.DRAW;
import static org.example.state.State.ONGOING;

public class GameState {

    public static final int TRANSIT_TO_NIL = 0;     // transitTo() ещё не вызывалась
    public static final int TRANSIT_TO_OK = 1;      // последняя команда смены состояния transitTo() отработала нормально
    public static final int TRANSIT_TO_ERR = 2;     // переход в недопустимое состояние

    private int transitToStatus = TRANSIT_TO_NIL;

    private State state;

    // конструктор

    // постусловие: создано новое состояние игры
    public GameState() {
        this.state = ONGOING;
    }


    // команды

    // предусловие: переход в состояние newState возможен
    // постусловие: состояние игры изменено на newState
    public void transitTo(State newState) {
        boolean canTransit = canTransitTo(newState);
        if (canTransit) {
            this.state = newState;
            transitToStatus = TRANSIT_TO_OK;
        } else {
            transitToStatus = TRANSIT_TO_ERR;
        }
    }


    // запросы

    // проверить возможность перехода в состояние newState
    public boolean canTransitTo(State newState) {
        return switch (this.state) {
            case ONGOING -> true;
            case DRAW_OFFERED -> newState == DRAW || newState == ONGOING;
            default -> false;
        };
    }

    // проверить, является ли текущее состояние состоянием окончания игры
    public boolean isEndGame() {
        return state.isEndGame();
    }

    // получить текущее состояние игры
    public State getState() {
        return state;
    }


    // дополнительные запросы

    // возвращает значение TRANSIT_TO_
    public int getTransitToStatus() {
        return transitToStatus;
    }
}