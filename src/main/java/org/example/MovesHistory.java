package org.example;

import org.example.exception.NotImplementedException;
import org.example.control.Move;
import org.example.piece.Color;

import java.util.Stack;

class MovesHistory {

    public static final int GET_LAST_MOVE_NIL = 0;                 // getLastMove() ещё не вызывалась
    public static final int GET_LAST_MOVE_OK = 1;                  // последняя команда добавления нового хода getLastMove() отработала нормально
    public static final int GET_LAST_MOVE_ERR = 1;                 // история ходов пуста

    public static final int IS_LAST_MOVES_PAIRS_REPEATED_NIL = 0;  // isLastMovesPairsRepeated() ещё не вызывалась
    public static final int IS_LAST_MOVES_PAIRS_REPEATED_OK = 1;   // последний запрос isLastMovesPairsRepeated() отработал нормально
    public static final int IS_LAST_MOVES_PAIRS_REPEATED_ERR = 2;  // количество последних анализируемых ходов count больше, чем количество элементов в истории ходов

    public static final int IS_LAST_MOVES_NO_TAKING_NIL = 0;       // is_last_moves_repeated() ещё не вызывалась
    public static final int IS_LAST_MOVES_NO_TAKING_OK = 1;        // последний запрос is_last_moves_no_taking() отработал нормально
    public static final int IS_LAST_MOVES_NO_TAKING_ERR = 2;       // количество последних анализируемых ходов count больше, чем количество элементов в истории ходов

    private final Stack<Move> moves;

    int getLastMoveStatus = GET_LAST_MOVE_NIL;
    int isLastMovesPairsRepeatedStatus = IS_LAST_MOVES_PAIRS_REPEATED_NIL;
    int isLastMovesNoTakingStatus = IS_LAST_MOVES_NO_TAKING_NIL;

    // конструктор

    // постусловие: создана пустая история ходов
    public MovesHistory() {
        moves = new Stack<>();
    }


    // команды

    // постусловие: в историю ходов добавлено новое значение move
    public void addMove(Move move) {
        moves.add(move);
    }


    // запросы

    // предусловие: история ходов не пуста
    public Move getLastMove() { // получить последний сделанный ход
        Move result;
        if (!moves.isEmpty()) {
            result = moves.peek();
            getLastMoveStatus = GET_LAST_MOVE_OK;
        } else {
            result = null;
            getLastMoveStatus = GET_LAST_MOVE_ERR;
        }
        return result;
    }

    // предусловие: количество последних анализируемых 2 * movePairsCount ходов меньше или равно, количеству элементов в истории ходов
    public boolean isLastMovesPairsRepeated(int movePairsCount) throws NotImplementedException { // повторяются ли последние count пар ходов
        throw new NotImplementedException();
    }

    // предусловие: количество последних анализируемых ходов count меньше или равно, количеству элементов в истории ходов
    public Move isLastMovesNoTaking(int count) throws NotImplementedException { // было ли взятие последние count ходов
        throw new NotImplementedException();
    }

    public int size() {
        return moves.size();
    }


    // дополнительные запросы

    // возвращает значение GET_LAST_MOVE_
    public int getGetLastMoveStatus() {
        return getLastMoveStatus;
    }

    // возвращает значение IS_LAST_MOVES_PAIRS_REPEATED_
    public int getIsLastMovesRepeatedStatus() {
        return isLastMovesPairsRepeatedStatus;
    }

    // возвращает значение IS_LAST_MOVES_NO_TAKING_
    public int getIsLastMovesNoTakingStatus() {
        return isLastMovesNoTakingStatus;
    }
}