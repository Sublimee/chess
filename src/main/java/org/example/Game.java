package org.example;

import org.example.board.Board;
import org.example.board.Field;
import org.example.control.Command;
import org.example.control.Move;
import org.example.piece.Color;
import org.example.piece.Piece;
import org.example.state.GameState;
import org.example.state.State;

import java.util.Map;

import static org.example.state.State.DRAW;
import static org.example.state.State.DRAW_OFFERED;
import static org.example.state.State.ONGOING;
import static org.example.state.State.RESIGN;

public class Game {

    public static final int MAKE_MOVE_NIL = 0;     // makeMove() ещё не вызывалась
    public static final int MAKE_MOVE_OK = 1;      // последняя команда выполнения хода makeMove() отработала нормально
    public static final int MAKE_MOVE_ERR = 2;     // ход не соответствует правилам шахмат

    public static final int EXECUTE_COMMAND_NIL = 0;     // executeCommand() ещё не вызывалась
    public static final int EXECUTE_COMMAND_OK = 1;      // последняя команда executeCommand() отработала нормально
    public static final int EXECUTE_COMMAND_ERR = 2;     // действие не найдено

    private final Board board;
    private final MovesHistory movesHistory;
    private final GameState state;

    private int makeMoveStatus = MAKE_MOVE_NIL;
    private int executeCommandStatus = EXECUTE_COMMAND_NIL;


    // конструктор

    // постусловие: создана новая игра
    public Game() {
        board = new Board();
        movesHistory = new MovesHistory();
        state = new GameState();
    }


    // команды

    // предусловие: ход соответствует правилам
    // постусловие: в соответствии с правилами фигура(ы) изменила(и) положение на игровой доске и при необходимости изменилось состояние игры
    public void makeMove(Move move) {
        if (state.isEndGame()) {
            makeMoveStatus = MAKE_MOVE_ERR;
        }
        // TODO другие проверки соответствия ходов правилам шахмат
        move.make(board);
        makeMoveStatus = MAKE_MOVE_OK;
    }

    // предусловие: игра распознает переданный тип действия пользователя
    // постусловие: меняется состояние игры
    public void executeCommand(Command command) {
        State newState;
        switch (command) {
            case null -> newState = null;
            case OFFER_DRAW -> newState = DRAW_OFFERED;
            case ACCEPT_DRAW -> newState = DRAW;
            case DECLINE_DRAW -> newState = ONGOING;
            case RESIGN -> newState = RESIGN;
        }
        state.transitTo(newState);
        if (state.getTransitToStatus() == GameState.TRANSIT_TO_OK) {
            executeCommandStatus = EXECUTE_COMMAND_OK;
        } else {
            executeCommandStatus = EXECUTE_COMMAND_ERR;
        }
    }


    // запросы

    // получить состояние игры
    public State getState() {
        return state.getState();
    }

    // получить расположение фигур на доске
    public Map<Field, Piece> getBoard() {
        return board.getPieces();
    }

    // получить очередность хода
    public Color getTurn() {
        return (movesHistory.size() % 2 == 0) ? Color.WHITE : Color.BLACK;
    }

    // проверить, является ли текущее состояние состоянием окончания игры
    public boolean isEndGame() {
        return getState().isEndGame();
    }


    // дополнительные запросы

    // возвращает значение MAKE_MOVE_
    public int getMakeMoveStatus() {
        return makeMoveStatus;
    }

    // возвращает значение EXECUTE_COMMAND_
    public int getExecuteCommandStatus() {
        return executeCommandStatus;
    }
}