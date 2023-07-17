import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.*;
public class Menu	extends JFrame {
	private String user;
	private JButton Write;
	private JButton Read;
	private JButton AI;
	private JButton WoW;
	private JButton Game;
	private JButton Dice;
	private final Color red=new Color(255,0,0);
	private final Color black=new Color(0,0,0);
	private File names=new File("fileNames.txt");
Menu(String user)
	{
	super("Welcome to personal asistant Luna");
	this.user=user;
	GridLayout layout=new GridLayout(2,2);
	setLayout(layout);
	JButton Write=new JButton("Write");
	JButton Read=new JButton("Decode");
	JButton AI=new JButton("Luna");
	JButton WoW=new JButton("WoW");
	JButton Game=new JButton("Game");
	JButton Dice=new JButton("Dice");

	Write.setForeground(red);
	Read.setForeground(red);
	AI.setForeground(red);
	WoW.setForeground(red);
	Game.setForeground(red);
	Dice.setForeground(red);

	Write.setBackground(black);
	Read.setBackground(black);
	AI.setBackground(black);
	WoW.setBackground(black);
	Game.setBackground(black);
	Dice.setBackground(black);

	add(Write);
	add(Read);
	add(AI);
	add(WoW);
	add(Dice);
	add(Game);

Handler handler=new Handler();
Write.addActionListener(handler);
Read.addActionListener(handler);
AI.addActionListener(handler);
WoW.addActionListener(handler);
Game.addActionListener(handler);
Dice.addActionListener(handler);

	}
private class Handler implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
		Menu.this.setVisible(false);
		if(e.getActionCommand().equals("Write"))
		{
			Writer w=new Writer(user);
			w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			w.setSize(750,600);
			w.setVisible(true);

		}
		else if(e.getActionCommand().equals("Decode"))
		{
			Reader r=new Reader(user);
			r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			r.setSize(750,600);
			r.setVisible(true);
			
		}
		else if(e.getActionCommand().equals("Luna"))
		{
			AI ai=new AI(user);
			ai.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ai.setSize(600,100);
			ai.setVisible(true);
		}
		else if(e.getActionCommand().equals("Game"))
		{
			GameMenu panel=new GameMenu(user);
			panel.setSize(250,200);
			panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
			panel.setVisible(true);
		}
		else if(e.getActionCommand().contentEquals("WoW"))
		{
			DW word=new DW(user);
			word.WordofWisdom();
		}
		else if(e.getActionCommand().contentEquals("Dice"))
		{
			Dice frp=new Dice(user);
			frp.setSize(550, 550);
			frp.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frp.setVisible(true);
		}
	}
}
}
