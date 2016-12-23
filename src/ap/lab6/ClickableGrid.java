/**
 * @author divansh 2015027
 * 
 */

package ap.lab6;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ClickableGrid implements ActionListener {
	public boolean gameStarted = false;
	JPanel gridPanel;
	public ArrayList<JButton> cells;
	public static boolean player1Turn = true;
	Board myBoard = new Board();
	int internalBoard[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public ClickableGrid(int rowCount, int columnCount) {
		gridPanel = new JPanel();
		cells = new ArrayList<>();
		gridPanel.setLayout(new GridLayout(rowCount, columnCount));
		for (int i = 0; i < 9; i++) {
			JButton xButton = new JButton("-");
			xButton.setActionCommand(String.valueOf(i));
			xButton.addActionListener(this);
			cells.add(xButton);
			gridPanel.add(xButton);

		}
		// gridPanel.setPreferredSize(new
		// Dimension(2*gridPanel.getWidth(),2*gridPanel.getHeight()/3));

	}

	public boolean isGameStarted() {
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public boolean isPlayer1Turn() {
		return player1Turn;
	}

	public ArrayList<JButton> getCells() {
		return cells;
	}

	public void setGridPanel(JPanel gridPanel) {
		this.gridPanel = gridPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton tempButton = cells.get(Integer.parseInt(e.getActionCommand()));
		int x = Integer.parseInt(e.getActionCommand()) / 3;
		int y = Integer.parseInt(e.getActionCommand()) % 3;

		if (myFrame.flag == true) {

			if (myFrame.gameNumber == 1) {
				startGame1(tempButton, x, y);
			}
			if (myFrame.gameNumber == 2) {
				game2(x, y, tempButton);
			}
			if (myFrame.gameNumber == 3) {
				game3();
			}
			if (myFrame.gameNumber == 4) {
				// for(int i=0;i<9;i++)internalBoard[i]=0;
				game4(x + 1, y + 1, tempButton, internalBoard);
			}
			if (myFrame.flag == true) {
				if (player1Turn == true) {
					myFrame.ta.append("O's turn\n");
				} else {
					myFrame.ta.append("X's turn\n");
				}
			}
		} else {
			myFrame.ta.append("Start new game by choosing usernames\n");
		}

	}

	public void game2(int x, int y, JButton tempButton) {

		// ("Player 1 is O Computer is X");

		myBoard.print();
		if (myBoard.checkState() == 0) {
			System.out.println("User cHOOSES " + x + 1 + " " + y + 1);
			int flag = -1;

			System.out.println("Player 1's move (1 based indexing) ");
			flag = myBoard.put(x + 1, y + 1, 'O');
			myBoard.print();
			if (flag != -1) {
				tempButton.setText("O");
			} else {
				myFrame.ta.append("Invalid\n");
				return;
			}

			System.out.println("CHECKING STATE HERE");
			System.out.println(myBoard.checkState());
			myBoard.print();

			if (myBoard.checkState() != 0) {

				myFrame.ta.setText("GAME OVER\n");
				if (myBoard.checkState() == 1) {
					myFrame.ta.append("O wins\n");
				} else if (myBoard.checkState() == 2) {
					myFrame.ta.append("X wins\n");
				} else if (myBoard.checkState() == -1) {
					myFrame.ta.append("draw\n");
				}
				myFrame.flag = false;
				return;
			}

			int arr[] = new int[9];
			int end = 0;
			char mat[][] = myBoard.get();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (mat[i][j] == '-') {
						arr[end] = 3 * i + j;
						end++;
					}
				}
			}
			Random randobj = new Random();
			int randval = randobj.nextInt(end);
			myBoard.print();
			x = arr[randval] / 3;
			y = arr[randval] % 3;
			System.out.println("randval  =" + randval);
			myFrame.ta.append(("Computer has chosen " + (x + 1) + (", " + (y + 1)) + "\n"));
			myBoard.put(x + 1, y + 1, 'X');
			myBoard.print();
			cells.get(arr[randval]).setText("X");
			/*
			 * for(JButton tempButton2 : cells) {
			 * if(Integer.parseInt(tempButton2.getActionCommand())==arr[randval]
			 * ) { tempButton2.setText("X"); } }
			 */
			if (myBoard.checkState() != 0) {

				myFrame.ta.setText("GAME OVER\n");
				if (myBoard.checkState() == 1) {
					myFrame.ta.append("O wins\n");
				} else if (myBoard.checkState() == 2) {
					myFrame.ta.append("X wins\n");
				} else if (myBoard.checkState() == -1) {
					myFrame.ta.append("draw\n");
				}
				myFrame.flag = false;
				return;
			}

		}
		System.out.println("CHECKING STATE HERE AGAIN");
		System.out.println(myBoard.checkState());
		myBoard.print();

	}

	public JPanel getGridPanel() {
		return gridPanel;
	}

	public int game1(int x, int y) {
		myBoard.print();
		if (player1Turn == true) {
			return myBoard.put(x, y, 'O');
		} else {
			return myBoard.put(x, y, 'X');
		}
	}

	public void startGame1(JButton tempButton, int x, int y) {
		if (myBoard.checkState() == 0) {

			int ret = game1(x + 1, y + 1); // check validity of position
			if (ret == -1) {
				myFrame.ta.append("Invalid Move\n"); // invalid do nothing
			} else {

				if (player1Turn == true) // 1st player turn
				{
					tempButton.setText("O");
				} else {
					tempButton.setText("X");
				}
				player1Turn = !player1Turn;
			}

		}
		if (myBoard.checkState() != 0) {
			myFrame.ta.setText("GAME OVER\n");
			if (myBoard.checkState() == 1) {
				myFrame.ta.append("O wins\n");
			} else if (myBoard.checkState() == 2) {
				myFrame.ta.append("X wins\n");
			} else if (myBoard.checkState() == -1) {
				myFrame.ta.append("draw\n");
			}
			myFrame.flag = false;
		}

	}

	public Board getMyBoard() {
		return myBoard;
	}

	public void resetGuiBoard() {
		myBoard.resetBoard();
		for (JButton tButton : cells) {
			tButton.setText("");
		}
	}

	public void game3() {
		int delay = 1000;
		javax.swing.Timer timer = new javax.swing.Timer(1000, this);
		
		int board[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		myFrame.ta.append("Assuming Random starts first\n");
		while (win(board) == 0 && tie(board) != -1) {
			int arr[] = new int[9];
			int end = 0;

			for (int j = 0; j < 9; j++) {
				if (board[j] == 0) {
					arr[end] = j;
					end++;
				}
			}

			Random randobj = new Random();
			int randval = randobj.nextInt(end);
			int x = arr[randval] / 3;
			int y = arr[randval] % 3;
			myFrame.ta.append(("Computer has chosen " + (x + 1) + (", " + (y + 1)))+"\n");
			board[3 * x + y] = -1;
			
			cells.get(3*x+y).setText("O");
			System.out.println("Board:");
			
			TimerTask timerTask = new TimerTask() {
				
				@Override
				public void run() {
					cells.get(3*x+y).setText("O");
				}
			};
			Timer myTimer= new Timer();
			myTimer.schedule(timerTask, 3 * 1000, 3* 1000);
			/*long count= 0;	
			while(count<=100000000L)count++;*/
			draw(board);
			if (win(board) == 0 && tie(board) != -1) {
				myFrame.ta.append("AI's move\n");
				 int move = computerMove(board);
				System.out.println("AI MOVE __------------  "+ move +"\n");
				draw(board);
/*				count = 0;
				while(count<=100000000L)count++;*/
			}
		}
		switch (win(board)) {
		case 0:
			myFrame.ta.append("Draw\n");
			break;
		case 1:
			// myFrame.ta.append("Board:\n");
			draw(board);
			myFrame.ta.append("Random loses AI wins\n");
			break;
		case -1:
			myFrame.ta.append("Random wins\n");
			break;
		}
	}

	private static int win(int barray[]) {
		int wins[][] = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 },
				{ 2, 4, 6 } };
		int i;
		for (i = 0; i < 8; ++i) {
			if (barray[wins[i][0]] != 0 && barray[wins[i][0]] == barray[wins[i][1]]
					&& barray[wins[i][0]] == barray[wins[i][2]])
				return barray[wins[i][2]];
		}
		return 0;

	}

	private static int minimax(int barray[], int player) {
		int winner = win(barray);
		if (winner != 0)
			return winner * player;

		int move = -1;
		int score = -10;
		int i;
		for (i = 0; i < 9; ++i) {
			if (barray[i] == 0) {
				barray[i] = player;
				int thisScore = -minimax(barray, player * -1);
				if (thisScore > score) {
					score = thisScore;
					move = i;
				}
				barray[i] = 0;
			}
		}
		if (move == -1)
			return 0;
		return score;
	}

	private static int computerMove(int board[]) {
		int move = -1;
		int score = -10;
		int i;
		for (i = 0; i < 9; ++i) {
			if (board[i] == 0) {
				board[i] = 1;
				int tempScore = -minimax(board, -1);
				board[i] = 0;
				if (tempScore > score) {
					score = tempScore;
					move = i;
				}
			}
		}
		board[move] = 1;
		return move;
	}

	private static int tie(int board[]) {
		for (int i = 0; i < 9; i++) {
			if (board[i] == 0)
				return 0;
		}
		return -1;
	}

	// Helper functions for minimax algorithm
	// Algorithm would have been difficult to implement without these

	private void draw(int b[]) {

		for (int i = 0; i < 9; i++) {
			(cells.get(i)).setText(dict(b[i]));
			//cells.get(i).doClick();
		}

		System.out.printf("%s %s %s\n", dict(b[0]), dict(b[1]), dict(b[2]));

		System.out.printf("%s %s %s\n", dict(b[3]), dict(b[4]), dict(b[5]));

		System.out.printf("%s %s %s\n", dict(b[6]), dict(b[7]), dict(b[8]));
	}

	private static String dict(int i) {
		switch (i) {
		case -1:
			return "O";
		case 0:
			return "";
		case 1:
			return "X";
		}
		return "";
	}

	public void game4(int x, int y, JButton tempButton, int board[]) {
		System.out.println("Board:");

		int input = 1;
		if (3 * (x - 1) + y - 1 >= 9 || 3 * (x - 1) + y - 1 < 0 || board[3 * (x - 1) + y - 1] != 0) {
			myFrame.ta.append("Invalid input\n");
		} else {
			board[3 * (x - 1) + (y - 1)] = -1;
			tempButton.setText("O");
		}
		
		

		if (win(board) == 0 && tie(board) != -1) {

			myFrame.ta.append("AI's move\n");
			computerMove(board);
			draw(board);
		}
		System.out.println("WIN BOARD = " + win(board) + " tie = " + tie(board));

		if (!(win(board) == 0 && tie(board) != -1)) {
			switch (win(board)) {
			case 0:
				System.out.println("Draw");
				myFrame.flag = false;
				break;
			case 1:
				System.out.println("Board:");
				draw(board);
				myFrame.flag = false;
				System.out.println("You lose AI wins");
				break;
			case -1:
				System.out.println("You win");
				myFrame.flag = false;
				break;
			}
			for (int i = 0; i < 9; i++)
				board[i] = 0;
		}

	}

}