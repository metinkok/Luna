import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;

import java.awt.*;
import java.util.*;
public class MineField extends JFrame implements ActionListener
{
	static int count;
	private int mines;
	private String user;
	private String f[][];
	private static JButton[][] buttons;
	private JButton restart;
	private JButton Menu;
	private String click;
	private int clicked;
	private MouseListener mouse=new mouseListener();
MineField(String user, int mines)
{
super("Mayin Tarlasi");
this.user=user;
this.mines=mines;
Random generator=new Random();
int boyut=10;
count=mines;
f=new String[boyut][boyut];
int er=0;
JPanel panel=new JPanel();
JPanel panel2=new JPanel();

GridLayout layout=new GridLayout(boyut,boyut);
FlowLayout flayout=new FlowLayout();
BorderLayout blayout=new BorderLayout();


panel.setLayout(layout);
panel2.setLayout(flayout);
setLayout(blayout);

Menu=new JButton("Menu");
restart=new JButton("Restart");
Menu.setBackground(Color.BLACK);
Menu.setForeground(Color.RED);
restart.setBackground(Color.BLACK);
restart.setForeground(Color.RED);

panel2.add(Menu);
panel2.add(restart);
panel2.setBackground(Color.BLACK);
panel2.setForeground(Color.RED);
Menu.addActionListener(this);
restart.addActionListener(this);

	for(int i=0; i<boyut; i++)
	{
		for(int j=0; j<boyut; j++){
			f[i][j]="T";
	}
	}
	for(int i=0; i<mines; i++)
	{
		int x=generator.nextInt(boyut);
		int y=generator.nextInt(boyut);
		if(f[x][y].equals("*"))
			i--;
		f[x][y]="*";
	}
	for(int i=0; i<boyut; i++)
	{
		for(int j=0; j<boyut; j++)
		{
		er=0;
		if(i==0&&j==0)
		{
			if(f[1][0].equals("*"))
				er++;
			if(f[1][1].equals("*"))
				er++;
			if(f[0][1].equals("*"))
				er++;
		}
		else if(i==0&&j<boyut-1)
		{
			if(f[0][j-1].equals("*"))
				er++;
			if(f[1][j].equals("*"))
				er++;
			if(f[0][j+1].equals("*"))
				er++;
			if(f[1][j-1].equals("*"))
				er++;
			if(f[1][j+1].equals("*"))
				er++;
		}
		else if(i==0)//j==boyut
		{
			if(f[0][j-1].equals("*"))
				er++;
			if(f[1][j].equals("*"))
				er++;
			if(f[1][j-1].equals("*"))
				er++;
		}
		else if(j==0&&i<boyut-1)
		{
			if(f[i-1][0].equals("*"))
				er++;
			if(f[i][1].equals("*"))
				er++;
			if(f[i+1][0].equals("*"))
				er++;
			if(f[i-1][1].equals("*"))
				er++;
			if(f[i+1][1].equals("*"))
				er++;

		}
		else if(j==0)//i==boyut
		{
			if(f[i-1][0].equals("*"))
				er++;
			if(f[i][1].equals("*"))
				er++;
			if(f[i-1][1].equals("*"))
				er++;

		}
		else if(i==boyut-1&&j==boyut-1)
		{
			if(f[boyut-1][boyut-2].equals("*"))
				er++;
			if(f[boyut-2][boyut-2].equals("*"))
				er++;
			if(f[boyut-2][boyut-1].equals("*"))
				er++;
		}
		else if(i==boyut-1&&j>0)
		{
			if(f[boyut-1][j-1].equals("*"))
				er++;
			if(f[boyut-1][j+1].equals("*"))
				er++;
			if(f[boyut-2][j-1].equals("*"))
				er++;
			if(f[boyut-2][j].equals("*"))
				er++;
			if(f[boyut-2][j+1].equals("*"))
				er++;
		}
		else if(j==boyut-1&&i>0)
		{
			if(f[i-1][boyut-1].equals("*"))
				er++;
			if(f[i][boyut-1].equals("*"))
				er++;
			if(f[i+1][boyut-1].equals("*"))
				er++;
			if(f[i-1][boyut-2].equals("*"))
				er++;
			if(f[i][boyut-2].equals("*"))
				er++;
			if(f[i+1][boyut-2].equals("*"))
				er++;

		}
		else if(i==boyut-1)//j==0
		{
			if(f[boyut-1][j+1].equals("*"))
				er++;
			if(f[boyut-2][j].equals("*"))
				er++;
			if(f[boyut-2][j+1].equals("*"))
				er++;
		}
		else if(j==boyut-1)//i==0
		{
			if(f[i][boyut-1].equals("*"))
				er++;
			if(f[i+1][boyut-1].equals("*"))
				er++;
			if(f[i][boyut-2].equals("*"))
				er++;
			if(f[i+1][boyut-2].equals("*"))
				er++;

		}
		else
		{
			if(f[i-1][j-1].equals("*"))
				er++;
			if(f[i-1][j].equals("*"))
				er++;
			if(f[i-1][j+1].equals("*"))
				er++;
			if(f[i][j-1].equals("*"))
				er++;
			if(f[i][j+1].equals("*"))
				er++;
			if(f[i+1][j+-1].equals("*"))
				er++;
			if(f[i+1][j].equals("*"))
				er++;
			if(f[i+1][j+1].equals("*"))
				er++;
		}
		if(f[i][j].equals("*"));
			else
		f[i][j]=""+er;
		}	
	}


	for(int i=0; i<boyut; i++)
	{
		for(int j=0; j<boyut; j++){
			if(f[i][j].equals("*"))
				er++;
			System.out.print(f[i][j]);}
		System.out.println();
	}
	buttons=new JButton[boyut][boyut];
	for(int i=0; i<boyut; i++)
		for(int j=0; j<boyut; j++)
		{
			buttons[i][j]=new JButton(""+i+""+j);
			panel.add(buttons[i][j]);
			buttons[i][j].addActionListener(this);
			buttons[i][j].addMouseListener(mouse);
			buttons[i][j].setBackground(Color.WHITE);
			buttons[i][j].setForeground(Color.WHITE);
		}
add(panel, BorderLayout.CENTER);
add(panel2, BorderLayout.NORTH);

	}
	@Override
public void actionPerformed(ActionEvent e)
{
		if(e.getActionCommand().equals("Menu")) {
			MineField.this.setVisible(false);
			GameMenu panel=new GameMenu(user);
			panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
			panel.setSize(300,200);
			panel.setVisible(true);
		}
		else if(e.getActionCommand().equals("Restart")) {
			MineField.this.setVisible(false);
			MineField m=new MineField(user,mines);
			m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			m.setSize(750,600);
			m.setVisible(true);
		}
		else {
	click=e.getActionCommand();
	System.out.println(""+click);
	clicked=Integer.parseInt(click);
	System.out.println(""+f[clicked/10][clicked%10]);
	event(clicked,200);
	if(isFinished()) {
		JOptionPane.showMessageDialog(null, "VICTORY");
	}
	}
}
	public void event(int clicked, int already) {
		buttons[clicked/10][clicked%10].setText(" "+f[clicked/10][clicked%10]);
		buttons[clicked/10][clicked%10].setBackground(Color.BLACK);
		if(f[clicked/10][clicked%10].contentEquals("*")) {
			for(int i=0; i<10; i++)
			{
				for(int j=0; j<10; j++){
					buttons[i%10][j%10].setText(" "+f[i%10][j%10]);
					buttons[i%10][j%10].setBackground(Color.BLACK);
			}
			}
			JOptionPane.showMessageDialog(null,"BoooM!");
			count=-1;
		}
		else if(f[clicked/10][clicked%10].contentEquals("0")){
			buttons[clicked/10][clicked%10].setForeground(Color.WHITE);
		}
		else if(f[clicked/10][clicked%10].contentEquals("1")){
			buttons[clicked/10][clicked%10].setForeground(Color.GREEN);
		}
		else if(f[clicked/10][clicked%10].contentEquals("2")){
			buttons[clicked/10][clicked%10].setForeground(Color.GREEN);
		}
		else if(f[clicked/10][clicked%10].contentEquals("3")){
			buttons[clicked/10][clicked%10].setForeground(Color.BLUE);
		}
		else if(f[clicked/10][clicked%10].contentEquals("4")){
			buttons[clicked/10][clicked%10].setForeground(Color.BLUE);
		}
		else if(f[clicked/10][clicked%10].contentEquals("5")){
			buttons[clicked/10][clicked%10].setForeground(Color.ORANGE);
		}
		else if(f[clicked/10][clicked%10].contentEquals("6")){
			buttons[clicked/10][clicked%10].setForeground(Color.ORANGE);
		}
		else if(f[clicked/10][clicked%10].contentEquals("7")){
			buttons[clicked/10][clicked%10].setForeground(Color.RED);
		}
		else if(f[clicked/10][clicked%10].contentEquals("8")){
			buttons[clicked/10][clicked%10].setForeground(Color.RED);
		}
		if(f[clicked/10][clicked%10].contentEquals("0")) {
			if(clicked%10!=0 && already>clicked)
				event(clicked-1, clicked);
			if(clicked%10!=9 && (already==200 || already<clicked))
				event(clicked+1, clicked);
			if(clicked<90 && (already<clicked || already==200))
				event(clicked+10, 200);
			if(clicked>9 && (already>clicked))
				event(clicked-10, clicked);
		}
	}
	public void mouseEvent(MouseEvent e, JButton button) {
		if (SwingUtilities.isRightMouseButton(e) && !(button.getBackground().equals(Color.BLACK))) {
			button.setForeground(Color.RED);
			button.setBackground(Color.BLACK);
		}
		else if(SwingUtilities.isRightMouseButton(e)) {
			button.setForeground(Color.BLACK);
			button.setBackground(Color.WHITE);
		}
	}
	public static boolean isFinished() {
		if(count==-1)
		{
			return false;
		}
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++)
			{
				if(buttons[i][j].getBackground().equals(Color.WHITE))
					return false;
			}
		}
		return true;
	}
}
class mouseListener implements MouseListener
{
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(isRightClick(arg0)) 
		{
			JButton button=(JButton) arg0.getSource();
			if(button.getBackground().equals(Color.WHITE)) {
				button.setBackground(Color.RED);
				button.setForeground(Color.RED);
				MineField.count--;
				if(MineField.isFinished()&&MineField.count==0)
				{
					JOptionPane.showMessageDialog(null, "VICTORY");
				}
			}
			else if(button.getBackground().equals(Color.RED))
			{
				MineField.count++;
				button.setBackground(Color.WHITE);
				button.setForeground(Color.WHITE);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
public boolean isRightClick(MouseEvent e) {
	return SwingUtilities.isRightMouseButton(e);
}
}
