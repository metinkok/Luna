import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class TicTacToe extends JFrame {
	private String user;
	private int moves=9;
	private JButton ret;
	private JButton[][] buttons;
	private boolean won=true;
	private JPanel panel;
	private Random generator=new Random();
TicTacToe(String user){
	super("Tic Tac Toe");
	this.user=user;
	GridLayout layout=new GridLayout(3,3);
	BorderLayout blayout=new BorderLayout();
	
	Handler handler=new Handler();
	int count=0;
	ret=new JButton("Menu");
	panel=new JPanel();
	buttons=new JButton[3][3];

	setLayout(blayout);
	panel.setLayout(layout);
	
	for(int i=0; i<3; i++) {
		for(int j=0; j<3; j++) {
			buttons[i][j]=new JButton(""+count);
			panel.add(buttons[i][j]);
			buttons[i][j].addActionListener(handler);
			count++;
		}
	}
	ret.setBackground(Color.BLACK);
	ret.setForeground(Color.RED);
	
	ret.addActionListener(handler);
	
	add(panel, BorderLayout.CENTER);
	add(ret,BorderLayout.NORTH);
	
}private class Handler implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contentEquals("Menu")) {
			TicTacToe.this.setVisible(false);
			GameMenu panel2=new GameMenu(user);
			panel2.setDefaultCloseOperation(EXIT_ON_CLOSE);
			panel2.setSize(300,200);
			panel2.setVisible(true);
		}
		else {
			String click=e.getActionCommand();
			int clicked=Integer.parseInt(click);
			buttons[clicked/3][clicked%3].setText("X");
			buttons[clicked/3][clicked%3].setBackground(Color.RED);
			moves=moves-1;
			control("Player");
			if(moves==0 && won) {
				JOptionPane.showMessageDialog(null,"DRAW");
				TicTacToe.this.setVisible(false);
				GameMenu panel2=new GameMenu(user);
				panel2.setDefaultCloseOperation(EXIT_ON_CLOSE);
				panel2.setSize(300,200);
				panel2.setVisible(true);
		}
			else if(won) {
				ai();
			}
		}
	}
	private void ai() {
		int i=generator.nextInt(3);
		int j=generator.nextInt(3);
		JButton button=buttons[i][j];
		while(button.getText().equals("X")||button.getText().equals("O")) {
			i=generator.nextInt(3);
			j=generator.nextInt(3);
			button=buttons[i][j];
		}
		int clicked=Integer.parseInt(button.getText());
		buttons[clicked/3][clicked%3].setText("O");
		buttons[clicked/3][clicked%3].setBackground(Color.GREEN);
		control("Computer");
		moves=moves-1;
	}
	private void control(String s) {
		if(yatay()||dikey()||capraz()) {
			won=false;
			JOptionPane.showMessageDialog(null,s+" WINS");
			TicTacToe.this.setVisible(false);
			GameMenu panel=new GameMenu(user);
			panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
			panel.setSize(250,300);
			panel.setVisible(true);
		}
	}
	private boolean yatay() {
		for(int i=0; i<3; i++) {
			if((buttons[i][0].getText().contentEquals(buttons[i][1].getText()))&&(buttons[i][0].getText().contentEquals(buttons[i][2].getText()))) {
				return true;
			}
		}
		return false;
	}
	private boolean dikey() {
		for(int i=0; i<3; i++) {
			if((buttons[0][i].getText().contentEquals(buttons[1][i].getText()))&&(buttons[0][i].getText().contentEquals(buttons[2][i].getText()))) {
				return true;
			}
		}
		return false;
	}
	private boolean capraz() {
		if((buttons[0][0].getText().contentEquals(buttons[1][1].getText()))&&(buttons[0][0].getText().contentEquals(buttons[2][2].getText()))) {
			return true;
		}
		else if((buttons[0][2].getText().contentEquals(buttons[1][1].getText()))&&(buttons[0][2].getText().contentEquals(buttons[2][0].getText()))) {
			return true;
		}
		return false;
	}
}
}
