/**
 * @author divansh 2015027
 * 
 */


package ap.lab6;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders.OptionDialogBorder;
//OPTION PANE MULTIPLE INPUT
//http://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog

public class myFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Dimension md;
	public static boolean flag = false;
	public static TextArea ta = new TextArea();
	public static int gameNumber = -1;
	public ClickableGrid myClickableGrid;
	private JButton choice1 = new JButton("User 1 vs User 2"), choice2 = new JButton("User vs CPU"),
			choice3 = new JButton("CPU vs AI bot"), choice4 = new JButton("User vs AI bot"),
			choice5 = new JButton("Clear and Restart"), choice6 = new JButton("Exit");
	CardLayout card = new CardLayout();
	Panel myPanel = new Panel();

	public myFrame(String icon) {
		Toolkit mykit = Toolkit.getDefaultToolkit();
		md = mykit.getScreenSize();
		Dimension md = mykit.getScreenSize();
		setSize(md.width / 2, md.height / 2);

		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			Image img = new ImageIcon(icon).getImage();
			setIconImage(img);
		} catch (Exception e) {
			System.out.println("Image not found");
		}
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(md.width / 2, md.height / 2));

		myPanel.setLayout(card);
		myPanel.add(getCard1(), "Card 1");
		myPanel.add(getCard2(), "Card 2");
		card.show(myPanel, "Card 1");
		add(myPanel);

	}

	public Dimension getDimensions() {
		return new Dimension(md.width / 2, md.height / 2);
	}

	public Container getCard1() {
		JPanel outside = new JPanel();
		JPanel inside = new JPanel();
		outside.setLayout(new BoxLayout(outside, BoxLayout.LINE_AXIS));
		inside.setLayout((new BoxLayout(inside, BoxLayout.PAGE_AXIS)));
		outside.add(Box.createHorizontalStrut(myFrame.md.width / 6));
		outside.add(inside);
		outside.add(Box.createHorizontalStrut(myFrame.md.width / 6));
		inside.add(Box.createVerticalStrut(myFrame.md.height / 10));
		JLabel title = new JLabel("Tic-Tac-Toe");
		title.setFont(title.getFont().deriveFont(25.0f));
		inside.add(title);
		inside.add(Box.createVerticalStrut(myFrame.md.height / 20));
		JButton button1 = new JButton("Start Game");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.next(myPanel);
			}
		});

		JButton button2 = new JButton("Exit");
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});

		Dimension d = new Dimension(200, 40);
		setButtonSize(button1, d);
		setButtonSize(button2, d);
		inside.add(button1);
		inside.add(Box.createVerticalStrut(myFrame.md.height / 20));
		inside.add(button2);
		inside.add(Box.createVerticalStrut(myFrame.md.height / 10));
		// add(outside);
		return outside;
	}

	public void setButtonSize(JButton button, Dimension d) {
		button.setMaximumSize(d);
		button.setMinimumSize(d);
		button.setPreferredSize(d);
		button.setSize(d);

	}

	public Container getCard2() {
		JPanel ret = new JPanel();
		ret.setLayout(new BoxLayout(ret, BoxLayout.LINE_AXIS));
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(6, 0, 0, 10));

		setMultipleActionCommand(1, choice1);
		setMultipleActionCommand(2, choice2);
		setMultipleActionCommand(4, choice4);

		// ADD Action listeners here

		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(2, 0, 0, 10));
		// jp2.setLayout(new BoxLayout(jp2, BoxLayout.PAGE_AXIS));
		myClickableGrid = new ClickableGrid(3, 3);

		choice3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (flag == false) {
					
					myClickableGrid.resetGuiBoard();
	/*				try {
						///TimeUnit.SECONDS.sleep(2);
					} catch (Exception e) {

					}*/
					myClickableGrid.game3();
				} else {
					myFrame.ta.append("Clear and reset and start new game or finish this game\n");
				}
			}
		});
		choice5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice1.setText("User1 vs User2");
				choice2.setText("User vs CPU");
				choice3.setText("CPU vs AI Bot");
				choice4.setText("User vs AI Bot");
				choice6.setText("Clear and Restart");
				choice6.setText("Exit");
				myClickableGrid.resetGuiBoard();
				flag = false;
			}
		});
		choice6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);

			}
		});

		jp1.add(choice1);
		jp1.add(choice2);
		jp1.add(choice3);
		jp1.add(choice4);
		jp1.add(choice5);
		jp1.add(choice6);

		JPanel gridPanel = myClickableGrid.getGridPanel();

		// gridPanel.setSize(new Dimension(jp2.getWidth(),2*jp2.getHeight()/3));

		jp2.add(gridPanel);
		/*
		 * ta.setSize(d); ta.setPreferredSize(d); ta.setMinimumSize(d);
		 * ta.setMaximumSize(d);
		 */
		// ta.setSize(new Dimension(jp2.getWidth(),jp2.getHeight()/3));
		// jp2.add(Box.createVerticalGlue());
		jp2.add(ta);
		ret.add(jp1);
		ret.add(Box.createHorizontalStrut(20));
		ret.add(jp2);
		return ret;

	}

	class popup implements ActionListener {
		
		

		@Override
		public void actionPerformed(ActionEvent e) {

			JOptionPane jOptionPane = new JOptionPane();
			JTextField user1 = new JTextField(20);
			JTextField user2 = new JTextField(20);
			JPanel jdialog = new JPanel();
			jdialog.setLayout(new GridLayout(2, 0));
			jdialog.add(user1);
			jdialog.add(new JLabel("User1 "));
			jdialog.add(user2);
			jdialog.add(new JLabel("User2 "));

			jOptionPane.add(jdialog);
			if (flag == false) {
				myClickableGrid.resetGuiBoard();//LATEST CHANGE
				int result = jOptionPane.showConfirmDialog(null, jdialog, "Fill both or cancel",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					String name1 = user1.getText(), name2 = user2.getText();
					if (!name1.equals("") && !name2.equals("")) {
						flag = true;
						choice1.setText("User1 vs User2");
						choice2.setText("User vs CPU");
						choice3.setText("CPU vs AI Bot");
						choice4.setText("User vs AI Bot");
						choice6.setText("Clear and Restart");
						choice6.setText("Exit");
						myClickableGrid.resetGuiBoard();
						ta.setText("Game started between " + name1 + " and " + name2 + "\n"+"First player is always O\n");
						if(ClickableGrid.player1Turn==true)
						{
							myFrame.ta.append("O's turn\n");
						}
						else {
							myFrame.ta.append("X's turn\n");
						}

						((JButton) e.getSource()).setText(name1 + "vs " + name2);
						gameNumber = Integer.parseInt(e.getActionCommand());
					} else {
						ta.append("Please enetr valid names\n");

					}
				} else {
					ta.append("Please enter valid names\n");

					// flag = true;

				}
			}

		}

		public Point getSelectedCell(int rowCount, int columnCount, JPanel gridPanel, MouseEvent e) {
			int width = gridPanel.getWidth();
			int height = gridPanel.getHeight();
			int cellWidth = width / columnCount;
			int cellHeight = height / rowCount;
			int column = e.getX() / cellWidth;
			int row = e.getY() / cellHeight;
			Point selectedCell = null;
			if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {

				selectedCell = new Point(column, row);
			}
			return selectedCell;
		}

	}

	public void setMultipleActionCommand(int i, JButton choice) {
		choice.addActionListener(new popup());
		choice.setActionCommand(Integer.toString(i));
	}

}
