import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
public class Dice extends JFrame {
	private String user;
	private final Color red=new Color(255,0,0);
	private final Color black=new Color(0,0,0);
	private JButton wild=new JButton("Wild Magic");
	private JPanel panel=new JPanel();
	private JButton d20=new JButton("d20");
	private JButton d4=new JButton("d4");
	private JButton d8=new JButton("d8");
	private JButton d100=new JButton("d100");
	private JButton d10=new JButton("d10");
	private JButton d12=new JButton("d12");
	private JButton d6=new JButton("d6");
	private JButton d16=new JButton("d16");
	private JButton Return=new JButton("Return");
	private JScrollPane scroll;
	private JTextArea history;
	private Random generator=new Random();
	Dice(String user)
	{
		super("ROLL A DICE WIN A PRIZE");
		this.user=user;
		history=new JTextArea();
		history.setEditable(false);
		scroll=new JScrollPane(history);
		scroll.setEnabled(true);
		history.setBackground(black);
		history.setForeground(red);
		panel.setLayout(new FlowLayout());
		wild.setBackground(black);
		wild.setForeground(red);
		d20.setBackground(black);
		d20.setForeground(red);
		d4.setBackground(black);
		d4.setForeground(red);
		d8.setBackground(black);
		d8.setForeground(red);
		d10.setBackground(black);
		d10.setForeground(red);
		d16.setBackground(black);
		d16.setForeground(red);
		d12.setBackground(black);
		d12.setForeground(red);
		d6.setBackground(black);
		d6.setForeground(red);
		d100.setBackground(black);
		d100.setForeground(red);
		Return.setBackground(red);
		Return.setForeground(red);
		panel.add(wild);
		panel.add(d4);
		panel.add(d6);
		panel.add(d8);
		panel.add(d10);
		panel.add(d12);
		panel.add(d20);
		panel.add(d100);
		panel.setBackground(black);
		add(scroll, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		add(Return, BorderLayout.NORTH);
		Handler handler=new Handler();
		wild.addActionListener(handler);
		d4.addActionListener(handler);
		d6.addActionListener(handler);
		d8.addActionListener(handler);
		d10.addActionListener(handler);
		d12.addActionListener(handler);
		d16.addActionListener(handler);
		d20.addActionListener(handler);
		d100.addActionListener(handler);
		Return.addActionListener(handler);
	}
	private class Handler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(event.getActionCommand().equals("Wild Magic"))
			{
			Random generator=new Random();
			int i=generator.nextInt(50)+1;
			try
			{
			File f=new File("d&d.txt");
			BufferedReader reader=new BufferedReader(new FileReader(f));
			String s="";
			for(int j=0; j<i; j++)
			{
				s=reader.readLine();
			}
			System.out.println(s);
			JOptionPane.showMessageDialog(null, s);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			}
			else if(event.getActionCommand().equals("d4"))
			{
				Random generator=new Random();
				int i=generator.nextInt(4)+1;
				System.out.println("d4: "+i);
				String s=history.getText();
				s=s+"\n d4: "+i;
				history.setText(s);
			}
			else if(event.getActionCommand().equals("d8"))
			{
				Random generator=new Random();
				int i=generator.nextInt(8)+1;
				System.out.println("d8: "+i);
				String s=history.getText();
				s=s+"\n d8: "+i;
				history.setText(s);
			}
			else if(event.getActionCommand().equals("d10"))
			{
				Random generator=new Random();
				int i=generator.nextInt(10)+1;
				System.out.println("d10: "+i);
				String s=history.getText();
				s=s+"\n d10: "+i;
				history.setText(s);
			}
			else if(event.getActionCommand().equals("d12"))
			{
				Random generator=new Random();
				int i=generator.nextInt(12)+1;
				System.out.println("d12: "+i);
				String s=history.getText();
				s=s+"\n d12: "+i;
				history.setText(s);
			}
			else if(event.getActionCommand().equals("d20"))
			{
				Random generator=new Random();
				int i=generator.nextInt(20)+1;
				System.out.println("d20: "+i);
				String s=history.getText();
				s=s+"\n d20: "+i;
				history.setText(s);
			}
			else if(event.getActionCommand().equals("d100"))
			{
				Random generator=new Random();
				int i=generator.nextInt(100)+1;
				System.out.println("d100: "+i);
				String s=history.getText();
				s=s+"\n d100: "+i;
				history.setText(s);
			}
			else if(event.getActionCommand().equals("d6"))
			{
				Random generator=new Random();
				int i=generator.nextInt(6)+1;
				System.out.println("d6: "+i);
				String s=history.getText();
				s=s+"\n d6: "+i;
				history.setText(s);
			}
			else if(event.getActionCommand().equals("d16"))
			{
				Random generator=new Random();
				int i=generator.nextInt(16)+1;
				System.out.println("d16: "+i);
				String s=history.getText();
				s=s+"\n d16: "+i;
				history.setText(s);
			}
			else if(event.getActionCommand().equals("Return"))
			{
				Dice.this.setVisible(false);
				Menu panel=new Menu(user);
				panel.setSize(750,100);
				panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
				panel.setVisible(true);
			}
		}
	}
}

