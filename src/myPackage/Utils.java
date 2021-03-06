package myPackage;

import java.util.ArrayList;
import java.util.HashMap;

public class Utils {
	public static HashMap<Character, Integer> pieceValues = createPieceValueMap();
	
	
	public static boolean contains(char[] array, char c){
		for (int i = 0; i<array.length; i++){
			if (array[i] == c){
				return true;
			}
		}
		return false;
	}
	

	public static boolean checkLegalMoves(Game game, 
			ArrayList<Move> moves,
			String squareString, 
			String destinations) {
		
		boolean flag = true;

		Square square = new Square(squareString);
		int row = square.row, col = square.col;
		ArrayList<Move> legalMoves = makeMoves(squareString, destinations);

		// check that all legal moves have been generated
		for (int i = 0; i < legalMoves.size(); i++) {
			Move legalMove = legalMoves.get(i);
			if (legalMove.currRow == row && legalMove.currColumn == col) {
				if (!moves.contains(legalMove)) {
					System.out.format(
							"Legal move %s not found in position \n %s \n",
							legalMove.convertToUCIFormat(),game);
					flag = false;
				}
			}
		}

		// check that no illegal moves have been generated
		for (int i = 0; i < moves.size(); i++) {
			Move move = moves.get(i);
			// System.out.format("Move generated by engine: %s \n",
			// move.convertToUCIFormat());
			if (move.currRow == row && move.currColumn == col && !legalMoves.contains(move)) {
				System.out.format("Illegal move %s found in position \n %s \n", move.convertToUCIFormat(), game);
				flag = false;
			}
		}
		return flag;
	}
	
	/*Creates a list of Move objects out of the starting square 
	 * and multiple destinations
	 */
	private static ArrayList<Move> makeMoves(String square, String destinations){
		ArrayList<Move> moves = new ArrayList<Move>();
		if (destinations.equals("")){
			return moves;
		}
		String[] destinationArray = destinations.split(" ");
		for( int i = 0; i < destinationArray.length; i++){
			moves.add(new Move(square + destinationArray[i]));
		}
		return moves;
	}
	private static HashMap<Character, Integer> createPieceValueMap(){
		HashMap<Character, Integer> hash = new HashMap<Character, Integer> ();
		hash.put('Q', 900);
		hash.put('R', 500);
		hash.put('N', 325);
		hash.put('B', 325);
		hash.put('P', 100);
		hash.put('q', 900);
		hash.put('r', 500);
		hash.put('n', 325);
		hash.put('b', 325);
		hash.put('p', 100);
		return hash;
	}
}
