package org.example.ui;

import org.example.piece.Bishop;
import org.example.piece.Color;
import org.example.piece.King;
import org.example.piece.Knight;
import org.example.piece.Pawn;
import org.example.piece.Piece;
import org.example.piece.Queen;
import org.example.piece.Rook;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class PieceRepresentation {

    private static final Map<Class<? extends Piece>, Map<Color, Character>> pieceToChar = new HashMap<>();

    static {
        addPiece(Pawn.class, '♙', '♟');
        addPiece(Rook.class, '♖', '♜');
        addPiece(Knight.class, '♘', '♞');
        addPiece(Bishop.class, '♗', '♝');
        addPiece(Queen.class, '♕', '♛');
        addPiece(King.class, '♔', '♚');
    }

    private static void addPiece(Class<? extends Piece> pieceClass, char whiteChar, char blackChar) {
        Map<Color, Character> colorMap = new EnumMap<>(Color.class);
        colorMap.put(Color.WHITE, whiteChar);
        colorMap.put(Color.BLACK, blackChar);
        pieceToChar.put(pieceClass, colorMap);
    }

    public static Character getPieceRepresentation(Piece piece) {
        return pieceToChar.get(piece.getClass()).get(piece.getColor());
    }
}