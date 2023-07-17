import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class rockPaperScissors extends JFrame{
	private String user;
	private JButton rock;
	private JButton paper;
	private JButton scissors;
	private JButton ret;
	private JPanel panel;
	private int player=0;
	private int computer=0;
	private String cmpt;
	private Random generator=new Random();
rockPaperScissors(String user){
	super("Rock Paper Scissors");
	this.user=user;
	panel=new JPanel();
	BorderLayout blayout=new BorderLayout();
	GridLayout layout=new GridLayout(1,4);
	Handler handler=new Handler();
	setLayout(blayout);
	panel.setLayout(layout);
	
	
	rock=new JButton("Rock");
	paper=new JButton("Paper");
	scissors=new JButton("Scissors");
	ret=new JButton("Menu");
	
	rock.setBackground(Color.BLACK);
	rock.setForeground(Color.RED);
	paper.setBackground(Color.BLACK);
	paper.setForeground(Color.RED);
	scissors.setBackground(Color.BLACK);
	scissors.setForeground(Color.RED);
	ret.setBackground(Color.BLACK);
	ret.setForeground(Color.RED);
	
	panel.add(rock);
	panel.add(paper);
	panel.add(scissors);
	add(ret, BorderLayout.NORTH);
	add(panel, BorderLayout.CENTER);
	
	rock.addActionListener(handler);
	paper.addActionListener(handler);
	scissors.addActionListener(handler);
	ret.addActionListener(handler);
}
private class Handler implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getActionCommand().contentEquals("Menu")) {
			rockPaperScissors.this.setVisible(false);
			GameMenu menu=new GameMenu(user);
			menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
			menu.setSize(300,200);
			menu.setVisible(true);
		}
		else {
		int random=generator.nextInt(3);
		String choosen=a.getActionCommand();
		if(random==0) {
			cmpt="Rock";
		}
		else if(random==1) {
			cmpt="Paper";
		}
		else {
			cmpt="Scissors";
		}
	if(choosen.contentEquals(cmpt)){
		JOptionPane.showMessageDialog(null,"Computer selected: "+cmpt+" DRAW");
	}
	else if(cmpt.contentEquals("Rock")&&choosen.equals("Paper")) {
		player++;
		JOptionPane.showMessageDialog(null,"Computer selected: "+cmpt+" Player Wins Player:"+player+"Computer:"+computer);
	}
	else if(cmpt.contentEquals("Rock")&&choosen.equals("Scissors")) {
		computer++;
		JOptionPane.showMessageDialog(null,"Computer selected: "+cmpt+" Computer Wins Player:"+player+"Computer:"+computer);
	}
	else if(cmpt.contentEquals("Paper")&&choosen.equals("Scissors")) {
		player++;
		JOptionPane.showMessageDialog(null,"Computer selected: "+cmpt+" Player Wins Player:"+player+"Computer:"+computer);
	}
	else if(cmpt.contentEquals("Paper")&&choosen.equals("Rock")) {
		computer++;
		JOptionPane.showMessageDialog(null,"Computer selected: "+cmpt+" Computer Wins Player:"+player+"Computer:"+computer);
	}
	else if(cmpt.contentEquals("Scissors")&&choosen.equals("Rock")) {
		player++;
		JOptionPane.showMessageDialog(null,"Computer selected: "+cmpt+" Player Wins Player:"+player+"Computer:"+computer);
	}
	else if(cmpt.contentEquals("Scissors")&&choosen.equals("Paper")) {
		computer++;
		JOptionPane.showMessageDialog(null,"Computer selected: "+cmpt+" Computer Wins Player:"+player+"Computer:"+computer);
	}
	}
	}
}
}
