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

public class Bishop extends Piece{

	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES= {-9, -7, 7, 9};
	
	Bishop(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		final List<Move> legalMoves = new ArrayList<Move>();
		
		for (final int currentCandidateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
		   int candidateDestinationCoordinate = this.piecePosition;
			
			while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
				if(isFirstColumnSolution(candidateDestinationCoordinate, currentCandidateOffset) ||
					isEightColumnSolution(candidateDestinationCoordinate, candidateDestinationCoordinate)){
						break;
					}
				
				candidateDestinationCoordinate += currentCandidateOffset;
			
				if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
					
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);			
					if(!candidateDestinationTile.isTileOccupied()) {
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
					}
					else {
						final Piece pieceAtDestination = candidateDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						
						//Schlagen der Figur möglich
						if(this.pieceAlliance != pieceAlliance) {
							legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
						}
						break;
					}					
				}
			}		
		}		
		return (List<Move>) Collections.unmodifiableCollection(legalMoves);
	}
	
	private static boolean isFirstColumnSolution(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
	}
	
	private static boolean isEightColumnSolution(final int currentPosition, final int candidateOffset) {
		return BoardUtils.EIGTHS_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 9);
	}

}
