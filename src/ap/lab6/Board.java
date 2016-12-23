/**
 * @author divansh 2015027
 * 
 */

package ap.lab6;

public class Board {

	private char mat[][] = new char[3][3];

	public Board() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				mat[i][j] = '-';
			}
		}
	}

	public void resetBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				mat[i][j] = '-';
			}
		}

	}

	public void reset(int x, int y) {
		mat[x - 1][y - 1] = '-';
	}

	public int getco(int x, int y) {
		return mat[x][y];
	}

	public int put(int x, int y, char val) {
		// x--;y--;//1 based input
		if ((val == 'X' || val == 'O') && (x >= 1 && x <= 3 && y >= 1 && y <= 3) && mat[x - 1][y - 1] == '-') {
			mat[x - 1][y - 1] = val;
			return 0;
		} else {
			System.out.println("Invalid coordinates");
			return -1;
		}
	}

	public char[][] get() {
		return mat;
	}

	public void print() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.printf("%c ", mat[i][j]);
			}
			System.out.println();
		}
	}

	public int checkState() {

//		if(mat[0][1]==mat[1][1]&& mat[1][1]==mat[2][1] && mat[1][1]=='O')
	//		return 1;
		////////////////////////////////// HORIZONTAL
		for (int i = 0; i < 3; i++) {
			int count = 0;
			for (int j = 0; j < 2; j++) {
				if (mat[i][j] == mat[i][j + 1] && mat[i][j] != '-') {
					count++;
					// System.out.println("count "+ count);
				}
			}
			if (count == 2) {
				if (mat[i][0] == 'X') {
					System.out.println("X wins");
					return 2;
				}
				else if (mat[i][0] == 'O') {
					System.out.println("O wins");
					return 1;
				}
			}
		}
		////////////////////////////////////// VERTICAL
		int j;
		for (int i = 0; i < 3; i++) {
			int count = 0;
			for (j = 0; j < 2; j++) {
				if (mat[j][i] == mat[j + 1][i] && mat[j][i] != '-') {
					count++;
				}
			}
			if (count == 2) {
				if (mat[0][i] == 'X') {//LATEST CHANGE CHANGED J to I
					System.out.println("X wins");
					return 2;
				} else if (mat[0][i] == 'O') {//HERE ALSO
					System.out.println("O wins");
					return 1;
				}
			}
		}
		/////////////////////// DIAGONAL SLOPE -1
		if ((mat[0][0] == mat[1][1]) && (mat[2][2] == mat[1][1]) && mat[0][0] != '-') {
			if (mat[0][0] == 'X') {
				System.out.println("X wins");
				return 2;
			} else if (mat[0][0] == 'O') {
				System.out.println("O wins");
				return 1;
			}

		}
		///////////////////////////////// DIAGONAL SLOPE 1
		if ((mat[0][2] == mat[1][1]) && (mat[2][0] == mat[1][1]) && mat[0][2] != '-') {
			if (mat[0][2] == 'X') {
				System.out.println("X wins");
				return 2;
			} else if (mat[0][2] == 'O') {
				System.out.println("O wins");
				return 1;
			}

		}
		////////////////////////

		for (int i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				if (mat[i][j] == '-') {
					// System.out.println("Continue game");
					return 0;
				}
			}
		}
		System.out.println("Tie");
		return -1;
	}
}
