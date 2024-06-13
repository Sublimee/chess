package org.example.ui;

import org.example.board.Field;
import org.example.piece.Piece;
import org.example.state.State;

import java.util.Map;

import static org.example.state.State.CHECKMATE;
import static org.example.state.State.DRAW;
import static org.example.state.State.DRAW_OFFERED;
import static org.example.state.State.ONGOING;
import static org.example.state.State.RESIGN;
import static org.example.state.State.STALEMATE;
import static org.example.ui.PieceRepresentation.getPieceRepresentation;

public class OutputProcessor {

    private static final Map<State, String> stateToTitleMap = Map.of(
            ONGOING, "Введите ход или команду: ",
            DRAW_OFFERED, "Вам предложили ничью. Введите ход или команду: ",
            DRAW, "Ничья.",
            RESIGN, "Игрок сдался.",
            CHECKMATE, "Мат",
            STALEMATE, "Пат."
    );

    private OutputProcessor() {
    }

    public static void showBoard(Map<Field, Piece> pieces) {
        char[][] board = new char[8][8];

        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                board[rank][file] = 'ｏ';
            }
        }

        for (Map.Entry<Field, Piece> entry : pieces.entrySet()) {
            Field field = entry.getKey();
            Piece piece = entry.getValue();
            int rank = 8 - field.getRank();
            int file = field.getFile().ordinal();
            board[rank][file] = getPieceRepresentation(piece);
        }

        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                System.out.print(board[rank][file] + " ");
            }
            System.out.println();
        }
    }

    public static void showState(State state) {
        System.out.println(stateToTitleMap.get(state));
    }

    public static void showErrorInputMessage() {
        System.out.println("Ошибка ввода");
    }
}
