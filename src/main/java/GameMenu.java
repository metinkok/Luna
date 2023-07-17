import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;
import java.io.*;
public class GameMenu extends JFrame{
	private String user;
	private JButton Mine;
	private JButton ttt;
	private JButton rps;
	private JButton TABU;
	private JButton Return;
GameMenu(String user){
	super("Menu");
	this.user=user;
	GridLayout layout=new GridLayout(4,1);
	Handler handler=new Handler();
	Mine=new JButton("Mine Field");
	ttt=new JButton("Tic Tac Toe");
	rps=new JButton("Rock Paper Scissors");
	TABU=new JButton("TABU");
	Return=new JButton("Return");
	setLayout(layout);
	
	Mine.setBackground(Color.BLACK);
	Mine.setForeground(Color.RED);
	ttt.setBackground(Color.BLACK);
	ttt.setForeground(Color.RED);
	rps.setBackground(Color.BLACK);
	rps.setForeground(Color.RED);
	TABU.setBackground(Color.BLACK);
	TABU.setForeground(Color.RED);
	Return.setBackground(Color.BLACK);
	Return.setForeground(Color.RED);
	add(Mine);
	add(ttt);
	add(rps);
	add(TABU);
	add(Return);

	Mine.addActionListener(handler);
	ttt.addActionListener(handler);
	rps.addActionListener(handler);
	TABU.addActionListener(handler);
	Return.addActionListener(handler);
}
private class Handler implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		GameMenu.this.setVisible(false);
		if(e.getActionCommand().equals("Mine Field")) {
			MineFieldDificulty m=new MineFieldDificulty(user);
			m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			m.setSize(300,400);
			m.setVisible(true);
		}
		else if(e.getActionCommand().equals("Tic Tac Toe")) {
			TicTacToe panel=new TicTacToe(user);
			panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
			panel.setSize(300,400);
			panel.setVisible(true);
		}
		else if(e.getActionCommand().equals("Rock Paper Scissors")) {
			rockPaperScissors panel=new rockPaperScissors(user);
			panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
			panel.setSize(300,100);
			panel.setVisible(true);
		}
		else if(e.getActionCommand().equals("TABU")){
			Game game=new Game();
			game.set();
			game.start();
		}
		else if(e.getActionCommand().equals("Return")) {
			Menu panel=new Menu(user);
			panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
			panel.setSize(750,100);
			panel.setVisible(true);
		}
		
	}
	
}
}