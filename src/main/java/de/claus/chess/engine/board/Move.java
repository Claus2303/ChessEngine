package de.claus.chess.engine.board;

import de.claus.chess.engine.pieces.Piece;

public abstract class Move {

	final Board board;
	final Piece movedPiece;
	final int destimationCoordinate;
	
	private Move(final Board board, final Piece movesPiece, final int destimationCoordinate) {
		this.board = board;
		this.movedPiece = movesPiece;
		this.destimationCoordinate = destimationCoordinate;
	}
	
	
	public final static class MajorMove extends Move{

		public MajorMove(final Board board, final Piece movesPiece, final int destimationCoordinate) {
			super(board, movesPiece, destimationCoordinate);
		}
		
	}
	
	public final static class AttackMove extends Move{

		final Piece attackedPiece;
		
		public AttackMove(final Board board, final Piece movesPiece, final int destimationCoordinate, final Piece attackedPiece) {
			super(board, movesPiece, destimationCoordinate);
			this.attackedPiece = attackedPiece;
		}
		
	}
}
