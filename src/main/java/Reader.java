import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.*;
public class Reader extends JFrame {
	private String user;
	private JTextField encoded;
	private JButton decode;
	private JButton r;
	private JPanel panel;
	private BorderLayout layout;
	private final Color red=new Color(255,0,0);
	private final Color black=new Color(0,0,0);
Reader(String user)
{
	super("Decoder.exe");
	this.user=user;
	panel=new JPanel();
	encoded=new JTextField("");
	decode=new JButton("Decode");
	layout=new BorderLayout();
	r=new JButton("Return");
	setLayout(layout);
	add(encoded, BorderLayout.CENTER);
	panel.add(decode);
	panel.add(r);
	add(panel, BorderLayout.SOUTH);
	encoded.setBackground(black);
	encoded.setForeground(red);
	decode.setBackground(black);
	decode.setForeground(red);
	panel.setBackground(black);
	r.setBackground(black);
	r.setForeground(red);
	Handler handler=new Handler();
	decode.addActionListener(handler);
	r.addActionListener(handler);
}
public JTextField getE()
{
	return encoded;
}
private class Handler implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equals("Return"))
		{
			Reader.this.setVisible(false);
			Menu panel=new Menu(user);
			panel.setSize(750,100);
			panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
			panel.setVisible(true);
		}
		else
		{
		String s=encoded.getText();
		String temp="";
		while(s.length()!=0)
		{
			char c=s.charAt(0);
			int i=c+60;
			c=(char)i;
			temp=temp+c;
			s=s.substring(1, s.length());
		}
		encoded.setText(temp);
	}
	}



}


}
