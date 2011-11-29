/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 *
 * Copyright (c) 2011 Worcester Polytechnic Institute.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Author:
 *    gpollice
 *******************************************************************************/
package strategy.playerBLHM;

import static strategy.common.PieceType.BOMB;
import static strategy.common.PieceType.CAPTAIN;
import static strategy.common.PieceType.COLONEL;
import static strategy.common.PieceType.FLAG;
import static strategy.common.PieceType.GENERAL;
import static strategy.common.PieceType.LIEUTENANT;
import static strategy.common.PieceType.MAJOR;
import static strategy.common.PieceType.MARSHAL;
import static strategy.common.PieceType.MINER;
import static strategy.common.PieceType.SCOUT;
import static strategy.common.PieceType.SERGEANT;
import static strategy.common.PieceType.SPY;
import strategy.common.*;
import strategy.tournament.*;

/**
 *
 * @author gpollice
 * @version Oct 5, 2011
 */
public class StrategyPlayerImpl implements StrategyPlayer
{
	private PlayerColor myColor;
	protected PiecePositionAssociation[] myStartingConfiguration;
	public RectangularStrategyBoard boardCopy;
	protected PlayerMove[] myMoves;
	private int moveIndex = 0;

	private static final PieceType[] redPieces = {
		BOMB,       SERGEANT,   MINER,      GENERAL,    MAJOR,      // (0, 0)..(0, 4)
		BOMB,       BOMB,       CAPTAIN,    BOMB,       MINER,      // (0, 5)..(0, 9)
		SPY,        COLONEL,    LIEUTENANT, CAPTAIN,    SCOUT,      // (1, 0)..(1, 4)
		SERGEANT,   MAJOR,      SERGEANT,   COLONEL,    LIEUTENANT, // (1, 5)..(1, 9)
		CAPTAIN,    SERGEANT,   MARSHAL,    MINER,      LIEUTENANT, // (2, 0)..(2, 4)
		CAPTAIN,    SCOUT,      SCOUT,      LIEUTENANT, MINER,      // (2, 5)..(2, 9)
		MAJOR,      SCOUT,      SCOUT,      SCOUT,      BOMB,       // (3, 0)..(3, 4)
		MINER,      SCOUT,      BOMB,       FLAG,       SCOUT       // (3, 5)..(3, 9)
	};

	private static final PieceType[] bluePieces = {
		SCOUT,      FLAG,       SERGEANT,   MINER,      SCOUT,      // (6, 0)..(6, 4)
		SCOUT,      CAPTAIN,    MINER,      SCOUT,      SCOUT,      // (6, 5)..(6, 9)
		LIEUTENANT, MAJOR,      CAPTAIN,    BOMB,       LIEUTENANT, // (7, 0)..(7, 4)
		LIEUTENANT, SCOUT,      SCOUT,      MAJOR,      CAPTAIN,    // (7, 5)..(7, 9)
		MINER,      SPY,        SCOUT,      CAPTAIN,    SERGEANT,   // (8, 0)..(8, 4)
		BOMB,       BOMB,       BOMB,       MARSHAL,    SERGEANT,   // (8, 5)..(8, 9)
		MAJOR,      GENERAL,    SERGEANT,   MINER,      COLONEL,    // (9, 0)..(9, 4)
		BOMB,       COLONEL,    BOMB,       MINER,      LIEUTENANT  // (9, 5)..(9, 9)
	};

	/**
	 * Default
	 * @param myColor
	 */
	public StrategyPlayerImpl(PlayerColor myColor)
	{
		this.myColor = myColor;
		setStartingConfiguration();
		setStartingBoard();
		setMyMoves();
	}

	private void setStartingConfiguration()
	{
		PieceType[] pieces = myColor == PlayerColor.RED ? redPieces : bluePieces;
		myStartingConfiguration = new PiecePositionAssociation[40];
		int ix = 0;
		int startRow = myColor == PlayerColor.RED ? 0 : 6;
		for (int row = startRow; row < (startRow + 4); row++) {
			for (int col = 0; col < 10; col++) {
				myStartingConfiguration[ix] = new PiecePositionAssociation(
						new Piece(pieces[ix], myColor), new Position(row, col));
				ix++;
			}
		}
	}

	private void setStartingBoard(){
		boardCopy = new RectangularStrategyBoard(10,10);
		// insert your code
		int i=0;
		int rows=10,columns=10;
		int row=0,col=0;
		for(i=0;i<rows*columns;i++){
			boardCopy.array[i] = Piece.NULL_PIECE;
		}
		if(myColor == PlayerColor.RED){
			for(i=0;i<redPieces.length;i++){
				if(i<10){
					row=0;
				}
				else{
					row= i/10;
				}
				if(i<10){
					col=i;
				}
				else{
					col=i%10;
				}
				boardCopy.putPieceAt(new Position(row,col), new Piece(redPieces[i], myColor));
			}
		}
		if(myColor == PlayerColor.BLUE){
			for(i=0;i<bluePieces.length;i++){
				if(i<10){
					row=0;
					
				}
				else{
					row= i/10;
				}
				if(i<10){
					col=i;
				}
				else{
					col=i%10;
				}
				row+=6;
				boardCopy.putPieceAt(new Position(row,col), new Piece(bluePieces[i], myColor));
			}
		}
	}

	private void setMyMoves()
	{
		if (myColor == PlayerColor.RED) {
			myMoves = new PlayerMove[] {
					makeMove(3, 0, 4, 0),
					makeMove(3, 1, 6, 1)
			};
		} else {
			myMoves = new PlayerMove[] {
					makeMove(6, 3, 5, 3)
			};
		}
	}

	private static PlayerMove makeMove(int fromRow, int fromCol, int toRow, int toCol)
	{
		return new PlayerMove(
				new Position(fromRow, fromCol),
				new Position(toRow, toCol));
	}

	/*
	 * @see strategy.tournament.StrategyPlayer#getStartingConfiguration()
	 */
	@Override
	public PiecePositionAssociation[] getStartingConfiguration()
	{
		return myStartingConfiguration;
	}

	/*
	 * @see strategy.tournament.StrategyPlayer#move(strategy.tournament.MoveResult)
	 */
	@Override
	public PlayerMove move(MoveResult gameUpdate)
	{
		return myMoves[moveIndex++];
	}
}
