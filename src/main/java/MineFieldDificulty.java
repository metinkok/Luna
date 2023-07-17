import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MineFieldDificulty extends JFrame {
	private String user;
	private JButton easy;
	private JButton normal;
	private JButton hard;
	private JButton impossible;
	MineFieldDificulty(String s){
		super("Settings");
		user=s;
		easy=new JButton("Easy");
		normal=new JButton("Normal");
		hard=new JButton("Hard");
		impossible=new JButton("Impossible");
		easy.setBackground(Color.BLACK);
		normal.setBackground(Color.BLACK);
		hard.setBackground(Color.BLACK);
		impossible.setBackground(Color.BLACK);
		easy.setForeground(Color.RED);
		normal.setForeground(Color.RED);
		hard.setForeground(Color.RED);
		impossible.setForeground(Color.RED);
		
		GridLayout layout=new GridLayout(4,1);
		setLayout(layout);
		Handler handler=new Handler();
		
		add(easy);
		add(normal);
		add(hard);
		add(impossible);
		
		easy.addActionListener(handler);
		normal.addActionListener(handler);
		hard.addActionListener(handler);
		impossible.addActionListener(handler);
	}
	private class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			MineFieldDificulty.this.setVisible(false);
			if(e.getActionCommand().equals("Easy")) {
				JOptionPane.showMessageDialog(null, "There are 5 mines");
				MineField m=new MineField(user,5);
				m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				m.setSize(750,600);
				m.setVisible(true);
			}
			else if(e.getActionCommand().equals("Normal")) {
				JOptionPane.showMessageDialog(null, "There are 20 mines");
				MineField m=new MineField(user,20);
				m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				m.setSize(750,600);
				m.setVisible(true);
			}
			else if(e.getActionCommand().equals("Hard")) {
				JOptionPane.showMessageDialog(null, "There are 30 mines");
				MineField m=new MineField(user,30);
				m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				m.setSize(750,600);
				m.setVisible(true);
			}
			else if(e.getActionCommand().equals("Impossible")) {
				JOptionPane.showMessageDialog(null, "There are 99 mines");
				MineField m=new MineField(user,99);
				m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				m.setSize(750,600);
				m.setVisible(true);
			}
			
		}
		
	}
}
