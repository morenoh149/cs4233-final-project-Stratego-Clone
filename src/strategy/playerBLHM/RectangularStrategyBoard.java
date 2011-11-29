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

import java.util.Iterator;

import strategy.*;
import strategy.common.*;

/**
 *
 * @author gpollice
 * @version Sep 2, 2011
 */
public class RectangularStrategyBoard implements StrategyBoard
{
	private final int rows;
	private final int columns;
	protected Piece[] array;
	/**
	 * Constructor for a rectangular board.
	 * 
	 * @param rows
	 * @param columns
	 */
	public RectangularStrategyBoard(int rows, int columns)
	{
		int i = 0;
		
		this.rows=rows;
		this.columns=columns;
		array = new Piece[rows*columns];
		for(i=0;i<rows*columns;i++){
			array[i] = Piece.NULL_PIECE;
		}
	}
	/**
	 * Constructor that initializes the board to a starting configuration.
	 * @param rows
	 * @param columns
	 * @param startingRedPieces the starting configuration of Red pieces
	 * @param startingBluePieces the starting configuration of Blue pieces
	 */
	public RectangularStrategyBoard(int rows, int columns, PiecePositionAssociation[] startingRedPieces,
			PiecePositionAssociation[] startingBluePieces){
		// insert your code
		int i=0;
		this.rows=rows;
		this.columns=columns;
		array = new Piece[rows*columns];
		for(i=0;i<rows*columns;i++){
			array[i] = Piece.NULL_PIECE;
		}
		for(i=0;i<startingRedPieces.length;i++){
			this.putPieceAt(startingRedPieces[i].getPosition(), startingRedPieces[i].getPiece());
		}
		for(i=0;i<startingBluePieces.length;i++){
			this.putPieceAt(startingBluePieces[i].getPosition(), startingBluePieces[i].getPiece());
		}
		
	}
	
	/*
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Piece> iterator()
	{
		return new ArrayIterator(array);
	}

	/*
	 * @see strategy.StrategyBoard#getPieceAt(strategy.Position)
	 */
	@Override
	public Piece getPieceAt(Position position)
	{
		if(!(position.getRow()>rows-1||position.getColumn()>columns-1||position.getColumn()<0||position.getRow()<0)){
			new StrategyException("Invalid location for piece retrieval");
		}
		return array[((position.getRow()*rows)+position.getColumn())];
	}

	/*
	 * @see strategy.StrategyBoard#putPieceAt(strategy.Position, strategy.Piece)
	 */
	@Override
	public void putPieceAt(Position position, Piece piece)
	{
		if(!(position.getRow()>rows-1||position.getColumn()>columns-1||position.getColumn()<0||position.getRow()<0)){
			new StrategyException("Invalid location for piece placement");
		}
		array[(position.getRow()*rows)+position.getColumn()] = piece;
	}

	/*
	 * @see strategy.StrategyBoard#isOccupied(strategy.Position)
	 */
	@Override
	public boolean isOccupied(Position position)
	{
		if(!(position.getRow()>rows-1||position.getColumn()>columns-1||position.getColumn()<0||position.getRow()<0)){
		new StrategyException("Invalid location for piece checking");
	}
		return !(array[(position.getRow()*rows)+position.getColumn()].equals(Piece.NULL_PIECE));
	}

	/*
	 * @see strategy.StrategyBoard#getDistance(strategy.Position, strategy.Position)
	 */
	@Override
	public int getDistance(Position from, Position to)
	{
		if(!(from.getRow()>rows-1||from.getColumn()>columns-1||from.getColumn()<0||from.getRow()<0)){
			new StrategyException("Invalid location for range checking");
		}
		if(!(to.getRow()>rows-1||to.getColumn()>columns-1||to.getColumn()<0||to.getRow()<0)){
			new StrategyException("Invalid location for range checking");
		}
		return (int)Math.sqrt(
				Math.pow((to.getRow()-from.getRow()),2) +
				Math.pow((to.getColumn()-from.getColumn()),2));
	}
	
	
	
	
	
	@Override
	public String toString(){
		String s = "Board: \n";
		String color = "";
		for(int i=(rows-1);i>=0;i--){
			for(int j=0;j<columns;j++){
				Position p = new Position(i,j);
				if(getPieceAt(p).equals(Piece.NULL_PIECE)){
					s = s + "[x,X] ";
				}else{
					if(getPieceAt(p).getColor()==PlayerColor.BLUE){
						color = "B";
					}
					if(getPieceAt(p).getColor()==PlayerColor.RED){
						color = "R";
					}
					s = s + "[" +getPieceAt(p).getType().getId() + "," + color + "] ";
				}
			}
			s = s + '\n';
		}
		return s;
	}
}
