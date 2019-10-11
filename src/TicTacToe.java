
/*
 * Edward Smith
 * Unit 2 Activity 2
 * Tic_Tac_Toe
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe 
{
	public static JFrame frame = new JFrame("Tic Tac Toe");
	public static JButton[][] buttons = new JButton[3][3];
	public static JButton restart = new JButton("Restart");
	public static JButton X = new JButton("X");
	public static JButton O = new JButton("O");
	//create main panel to put layer others on top
	public static JPanel mainPanel = new JPanel(new BorderLayout());
	public static JPanel menu = new JPanel(new BorderLayout());
	public static JPanel game = new JPanel(new GridLayout(3,3));
	public static JLabel label = new JLabel("                                           Select the player to start" );
	public static int moveCounter = 9;
	public static boolean players;
	public static void main(String[] args) 
	{
		// declarations, initializations, variables
		Font xofont = new Font("Courier New", Font.BOLD, 25);
		Font font = new Font("Courier New", Font.BOLD, 50);
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//initialize buttons
		X.setFont(xofont);
		O.setFont(xofont);
		//Setting dimensions of panels
		menu.setPreferredSize(new Dimension(400,70));                    
		game.setPreferredSize(new Dimension(400,400));
		//Add both reset and player selection buttons to menu container panel
		menu.add(restart,BorderLayout.NORTH);
		menu.add(X,BorderLayout.WEST);
		menu.add(O,BorderLayout.EAST);
		menu.add(label,BorderLayout.CENTER);
		//Adds panel and sub-panels to frame 
		mainPanel.add(menu,BorderLayout.NORTH);                              
		mainPanel.add(game,BorderLayout.SOUTH);
		frame.add(mainPanel);
		//restarts game if restart button is pressed
		restart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				restart();
			}
		});
		//Selects which player starts
		X.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(moveCounter<9)
				{
					return;
				}
				players = true;
				label.setText("                                                             X's turn");
			}
		});
		O.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(moveCounter<9)
				{
					return;
				}
				players = false;
				label.setText("                                                             O's turn");
			}
		});
		for(int l=0;l<3;l++)
		{
			for(int r=0;r<3;r++)
			{
				int y = l;
				int x = r;
				buttons[l][r] = new JButton();
				buttons[l][r].setFont(font);
				game.add(buttons[l][r]);
				buttons[l][r].addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(buttons[y][x].getText()=="X"||buttons[y][x].getText()=="O")
						{
							return;
						}
						if(players)
						{
							if(moveCounter%2==0)
							{
								buttons[y][x].setText("O");
								label.setText("                                                             X's turn");
							}
							else
							{
								buttons[y][x].setText("X");
								label.setText("                                                             O's turn");
							}
						}
						else if(!players)
						{
							if(moveCounter%2==0)
							{
								buttons[y][x].setText("X");
								label.setText("                                                             O's turn");
							}
							else
							{
								buttons[y][x].setText("O");
								label.setText("                                                             X's turn");
							}
						}
						moveCounter--;
						String player = buttons[y][x].getText();
						if(moveCounter<5)
						{
						checkWin(player,x,y);
						}
					}
				});
			}//end inner loop
		}//end outer loop
	}//end main
	public static void checkWin(String player,int x,int y)
	{
		//checks columns
		if(buttons[0][0].getText()==player && buttons[1][0].getText()==player && buttons[2][0].getText()==player)
		{
			displayWin(x,y);
		}
		else if(buttons[0][1].getText()==player && buttons[1][1].getText()==player && buttons[2][1].getText()==player)
		{
			displayWin(x,y);
		}
		else if(buttons[0][2].getText()==player && buttons[1][2].getText()==player && buttons[2][2].getText()==player)
		{
			displayWin(x,y);
		}
		//checks rows
		else if(buttons[0][0].getText()==player && buttons[0][1].getText()==player && buttons[0][2].getText()==player)
		{
			displayWin(x,y);
		}
		else if(buttons[1][0].getText()==player && buttons[1][1].getText()==player && buttons[1][2].getText()==player)
		{
			displayWin(x,y);
		}
		else if(buttons[2][0].getText()==player && buttons[2][1].getText()==player && buttons[2][2].getText()==player)
		{
			displayWin(x,y);
		}
		//checks diagonals
		else if(buttons[0][0].getText()==player && buttons[1][1].getText()==player && buttons[2][2].getText()==player)
		{
			displayWin(x,y);
		}
		else if(buttons[0][2].getText()==player && buttons[1][1].getText()==player && buttons[2][0].getText()==player)
		{
			displayWin(x,y);
		}
		else if(moveCounter==0)
		{
			JOptionPane.showMessageDialog(frame,"Draw!");
			playAgain();
		}
	}//end checkWin()
	public static void displayWin(int x,int y)
	{
		JOptionPane.showMessageDialog(frame,buttons[y][x].getText()+ " wins!");
		playAgain();
	}//end displayWin() 
	public static void playAgain()
	{
		JDialog.setDefaultLookAndFeelDecorated(true);
		int response = JOptionPane.showConfirmDialog(frame,"Would you like to play again?","Tic Tac Toe",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(response == JOptionPane.YES_OPTION)
		{
			restart();
		}
		else if(response == JOptionPane.NO_OPTION)
		{
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}//end playAgain()
	public static void restart()
	{
		for(int l=0;l<3;l++)
		{
			for(int r=0;r<3;r++)
			{
				buttons[l][r].setText(" ");
			}//end inner for loop
			players = false;
			moveCounter = 9;
		}//end outer for loop
		label.setText("                                           Select the player to start" );
	}//end restart()
}//end class
