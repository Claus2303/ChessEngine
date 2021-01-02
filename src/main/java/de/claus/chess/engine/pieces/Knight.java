package de.claus.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.claus.chess.engine.Alliance;
import de.claus.chess.engine.board.Board;
import de.claus.chess.engine.board.BoardUtils;
import de.claus.chess.engine.board.Move;
import de.claus.chess.engine.board.Tile;

public class Knight extends Piece{

	private final static int[] CANDIDATE_MOVE_COORDINATES= {-17, -15, -10, -6, 6, 10, 15, 17};
	
	Knight(final int piecePosition, final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		
		
		final List<Move> legalMoves = new ArrayList<Move>();
		
		for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

				//Ausnahmen des Spingers am Rand
				if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset)||
						isSecondColumnExclusion(this.piecePosition, currentCandidateOffset)||
						isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset)||
						isEigthsColumnExclusion(this.piecePosition, currentCandidateOffset)){
					continue;
				}
				
				final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);			
				if(candidateDestinationTile.isTileOccupied()) {
					legalMoves.add(new Move());
				}
				else {
					final Piece pieceAtDestination = candidateDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					//Schlagen der Figur möglich
					if(this.pieceAlliance != pieceAlliance) {
						legalMoves.add(new Move());
					}
				}
			}
			
		}
		
		return (List<Move>) Collections.unmodifiableCollection(legalMoves);
	}

	private static boolean isFirstColumnExclusion(final int currrentPosition, final int candidateOffset) {
		
		return BoardUtils.FIRST_COLUMN[currrentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
		candidateOffset == 6||candidateOffset == 15);
	}
	
	private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
	}
	
	private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.SEVENTHS_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
	}
	
	private static boolean isEigthsColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.EIGTHS_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6|| candidateOffset == 10 || candidateOffset == 17);
	}
}
