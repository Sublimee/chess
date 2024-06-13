package org.example.ui;

import org.example.Game;
import org.example.board.Field;
import org.example.board.File;
import org.example.control.Action;
import org.example.control.Command;
import org.example.control.Control;
import org.example.control.Move;
import org.example.control.Moving;
import org.example.control.Promotion;
import org.example.control.Taking;
import org.example.piece.Bishop;
import org.example.piece.Color;
import org.example.piece.King;
import org.example.piece.Knight;
import org.example.piece.Piece;
import org.example.piece.Queen;
import org.example.piece.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.board.File.A;
import static org.example.board.File.C;
import static org.example.board.File.D;
import static org.example.board.File.E;
import static org.example.board.File.F;
import static org.example.board.File.G;
import static org.example.board.File.H;
import static org.example.control.Command.fromString;
import static org.example.control.Command.isCommand;
import static org.example.state.State.ONGOING;

public class InputProcessor {

    public static final int PROCESS_INPUT_NIL = 0;     // processInput() ещё не вызывалась
    public static final int PROCESS_INPUT_OK = 1;      // последняя команда обработки ввода processInput() отработала нормально
    public static final int PROCESS_INPUT_ERR = 2;     // некорректный ввод

    private static final String SHORT_CASTLING = "0-0";
    private static final String LONG_CASTLING = "0-0-0";

    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern reqularMovePattern = Pattern.compile("^([a-h][1-8]) ([a-h][1-8]) ?([NBRQ])?$");

    private int processInputStatus = PROCESS_INPUT_NIL;

    public void processInput(Game game) {
        String input = scanner.nextLine().trim();
        Control control = null;

        if (isCommand(input)) {
            control = fromString(input);
            game.executeCommand((Command) control);
            processInputStatus = PROCESS_INPUT_OK;
        }
        if (isMove(input) && game.getState() == ONGOING) {
            control = processMove(input, game);
            game.makeMove((Move) control);
            processInputStatus = PROCESS_INPUT_OK;
        }
        if (control == null) {
            processInputStatus = PROCESS_INPUT_ERR;
        }
    }

    public int getProcessInputStatus(){
        return processInputStatus;
    }

    private boolean isMove(String input) {
        return input.matches("^[a-h][1-8] [a-h][1-8] ?[NBRQ]?$") || input.equals(SHORT_CASTLING) || input.equals(LONG_CASTLING);
    }

    private Move processMove(String input, Game game) {
        List<Action> actions = switch (input) {
            case SHORT_CASTLING -> processCastling(game.getTurn(), true);
            case LONG_CASTLING -> processCastling(game.getTurn(), false);
            default -> processRegularMove(input, game);
        };

        return new Move(actions);
    }

    private List<Action> processCastling(Color color, boolean isShort) {
        int castlingRank = (color == Color.WHITE) ? 1 : 8;

        Field kingFrom = new Field(E, castlingRank);
        Field kingTo = new Field(isShort ? G : C, castlingRank);
        Field rookFrom = new Field(isShort ? H : A, castlingRank);
        Field rookTo = new Field(isShort ? F : D, castlingRank);

        List<Action> actions = new ArrayList<>();
        actions.add(new Moving(kingFrom, kingTo, new King(color)));
        actions.add(new Moving(rookFrom, rookTo, new Rook(color)));
        return actions;
    }

    private List<Action> processRegularMove(String input, Game game) {
        List<Action> actions = new ArrayList<>();
        Matcher matcher = reqularMovePattern.matcher(input);

        if (!matcher.matches()) {
            return actions;
        }

        Field from = toField(matcher.group(1));
        Field to = toField(matcher.group(2));
        Piece piece = game.getBoard().get(from);
        Piece taken = game.getBoard().get(to);
        if (taken == null) {
            Moving moving = new Moving(from, to, piece);
            actions.add(moving);
        } else {
            Taking taking = new Taking(from, to, piece, taken);
            actions.add(taking);
        }

        String promotion = matcher.group(3);
        if (promotion != null) {
            Piece promotedTo;
            Color color = game.getTurn();
            promotedTo = switch (promotion) {
                case "N" -> new Knight(color);
                case "B" -> new Bishop(color);
                case "R" -> new Rook(color);
                case "Q" -> new Queen(color);
                default -> throw new IllegalArgumentException();
            };
            actions.add(new Promotion(to, promotedTo));
        }

        return actions;
    }

    private Field toField(String input) {
        File file = File.valueOf(input.substring(0, 1).toUpperCase());
        int rank = Integer.parseInt(input.substring(1, 2));
        return new Field(file, rank);
    }
}