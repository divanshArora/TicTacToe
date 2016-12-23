/**
 * @author divansh 2015027
 * 
 */

package ap.lab6;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JPanel;


//LAYOUT TUTORIAL
//http://stackoverflow.com/questions/12835198/positioning-of-components-how-to-place-a-few-buttons-center-screen-same-size
//GOOD PRACTICES
//http://stackoverflow.com/questions/5473828/java-swing-gui-best-practices-from-a-code-standpoint
public class TicTacToeGame {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				myFrame myframe  = new myFrame("ttt.png");
				myframe.setTitle("Tic Tac Toe");
				myframe.setVisible(true);
				
			}
		});
	}
	

}
