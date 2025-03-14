package bordgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int row, int column) {
		
		if(row < 1 || column < 1) {
			throw new BoardException("Erro created board: there must be at last 1 row and 1 column");
		}
		this.rows = row;
		this.columns = column;
		pieces = new Piece[row][column];
	}

	public int getRow() {
		return rows;
	}


	public int getColumn() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		
		if(!positionExists(row, column)) {
			throw new BoardException("position not on the board.");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		
		if(!positionExists(position)) {
			throw new BoardException("position not on the board.");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("there is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("position not on the board.");
		}
		
		if(piece(position) == null) {
			return null;
		}
		
		Piece aux = piece(position);
		aux.position = null;
		
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExists(int row, int column) {
		return row >=0 && row < this.rows && column >=0 && column < this.columns; 
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		
		if(!positionExists(position)) {
			throw new BoardException("position not on the board.");
		}
		return piece(position) != null;
	}
}
