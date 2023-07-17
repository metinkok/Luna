import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.*;
public class Writer extends JFrame {
	private String user;
	private JTextField name;
	private JTextArea writed;
	private JButton encode;
	private JButton r;
	private	String filename;
	private JPanel panel;
	private final Color red=new Color(255,0,0);
	private final Color black=new Color(0,0,0);
Writer(String user)
{
	super("Writer");
	this.user=user;
	BorderLayout layout=new BorderLayout();
	panel=new JPanel();
	setLayout(layout);
	name=new JTextField("");
	writed=new JTextArea("");
	encode=new JButton("Encode&Write");
	r=new JButton("Return");
	add(name, BorderLayout.NORTH);
	add(writed, BorderLayout.CENTER);
	panel.add(encode);
	panel.add(r);
	add(panel, BorderLayout.SOUTH);
	name.setBackground(black);
	name.setForeground(red);
	writed.setBackground(black);
	writed.setForeground(red);
	encode.setBackground(black);
	encode.setForeground(red);
	r.setBackground(black);
	r.setForeground(red);
	panel.setBackground(black);
	Handler hander=new Handler();
	encode.addActionListener(hander);
	r.addActionListener(hander);
}
private class Handler implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equals("Return"))
		{
			Writer.this.setVisible(false);
			Menu panel=new Menu(user);
			panel.setSize(750,100);
			panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
			panel.setVisible(true);
		}
		else
		{
		try {
			filename=name.getText();
			File file=new File(filename+".txt");
			BufferedWriter writer=new BufferedWriter(new FileWriter(file,true));
			//yes no button no system.exit(0); yes do nothing
			String encode=writed.getText();
			String temp="";
			while(encode.length()!=0)
			{
				char c=encode.charAt(0);
				int i=c-60;
				c=(char)i;
				temp=temp+c;
				encode=encode.substring(1, encode.length());
			}
			writer.write(temp+"\n");
			writer.close();
		} catch (IllegalArgumentException k) {
			try {
			File file=new File(filename+".txt");
			BufferedWriter writer=new BufferedWriter(new FileWriter(file,true));
			String encode=writed.getText();
			String temp="";
			while(encode.length()!=0)
			{
				char c=encode.charAt(0);
				int i=c-60;
				c=(char)i;
				temp=temp+c;
				encode=encode.substring(1, encode.length());
			}
			writer.write(temp);
			writer.close();
			}
			catch(IOException z) {
				System.out.println("");
			}
		}	
		catch(IOException e) {
			System.out.println("");
		}
	}
	}
	}
}
