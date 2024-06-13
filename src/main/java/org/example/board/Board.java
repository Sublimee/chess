package org.example.board;

import org.example.DeepCopyUtils;
import org.example.piece.Bishop;
import org.example.piece.Color;
import org.example.piece.King;
import org.example.piece.Knight;
import org.example.piece.Pawn;
import org.example.piece.Piece;
import org.example.piece.Queen;
import org.example.piece.Rook;

import java.util.HashMap;
import java.util.Map;

public class Board {

    public static final int SET_PIECE_NIL = 0;     // setPiece() ещё не вызывалась
    public static final int SET_PIECE_OK = 1;      // последняя команда установки фигуры setPiece() отработала нормально
    public static final int SET_PIECE_ERR = 2;     // поле-приемник -- непустое

    public static final int REMOVE_PIECE_NIL = 0;  // removePiece() ещё не вызывалась
    public static final int REMOVE_PIECE_OK = 1;   // последняя команда удаления фигуры removePiece() отработала нормально
    public static final int REMOVE_PIECE_ERR = 2;  // поле, с которого нужно удалить фигуру -- пустое

    private final HashMap<Field, Piece> pieces;
    private int setPieceStatus = SET_PIECE_NIL;
    private int removePieceStatus = REMOVE_PIECE_NIL;


    // конструктор

    // постусловие: создана игровая доска с расставленными на ней фигурами
    public Board() {
        this.pieces = new HashMap<>();

        // set pawns
        for (File file : File.values()) {
            setPiece(new Field(file, 2), new Pawn(Color.WHITE));
            setPiece(new Field(file, 7), new Pawn(Color.BLACK));
        }

        // set rooks
        setPiece(new Field(File.A, 1), new Rook(Color.WHITE));
        setPiece(new Field(File.H, 1), new Rook(Color.WHITE));
        setPiece(new Field(File.A, 8), new Rook(Color.BLACK));
        setPiece(new Field(File.H, 8), new Rook(Color.BLACK));

        // set knights
        setPiece(new Field(File.B, 1), new Knight(Color.WHITE));
        setPiece(new Field(File.G, 1), new Knight(Color.WHITE));
        setPiece(new Field(File.B, 8), new Knight(Color.BLACK));
        setPiece(new Field(File.G, 8), new Knight(Color.BLACK));

        // set bishops
        setPiece(new Field(File.C, 1), new Bishop(Color.WHITE));
        setPiece(new Field(File.F, 1), new Bishop(Color.WHITE));
        setPiece(new Field(File.C, 8), new Bishop(Color.BLACK));
        setPiece(new Field(File.F, 8), new Bishop(Color.BLACK));

        // set queens
        setPiece(new Field(File.D, 1), new Queen(Color.WHITE));
        setPiece(new Field(File.D, 8), new Queen(Color.BLACK));

        // set kings
        setPiece(new Field(File.E, 1), new King(Color.WHITE));
        setPiece(new Field(File.E, 8), new King(Color.BLACK));
    }


    // команды

    // предусловие: поле-приемник -- пустое
    // постусловие: поле-приемник содержит переданную фигуру piece
    public void setPiece(Field field, Piece piece) {
        if (pieces.containsKey(field)) {
            setPieceStatus = SET_PIECE_ERR;
        } else {
            pieces.put(field, piece);
            setPieceStatus = SET_PIECE_OK;
        }
    }

    // предусловие: переданное поле field -- непустое
    // постусловие: на переданной клетке поля нет фигуры
    public void removePiece(Field field) {
        if (!pieces.containsKey(field)) {
            removePieceStatus = REMOVE_PIECE_ERR;
        } else {
            pieces.remove(field);
            removePieceStatus = REMOVE_PIECE_OK;
        }
    }


    // запросы

    // проверяет, занято ли поле фигурой
    public boolean isFieldEmpty(Field field) {
        return pieces.containsKey(field);
    }

    // получает фигуру по переданному полю
    public Piece getPiece(Field field) {
        return pieces.get(field);
    }

    // получает расположение фигур на доске
    public Map<Field, Piece> getPieces() {
        return DeepCopyUtils.deepCopy(pieces);
    }


    // дополнительные запросы

    // возвращает значение SET_PIECE_
    public int getSetPieceStatus() {
        return setPieceStatus;
    }

    // возвращает значение REMOVE_PIECE_
    public int getRemovePieceStatus() {
        return removePieceStatus;
    }
}