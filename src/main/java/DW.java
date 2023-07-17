import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
public class DW extends JFrame {
	private String user;
DW(String user){
	this.user=user;
}
public void WordofWisdom()
{
	System.out.println("It is bigger on\n the inside");
	try
	{
	File f=new File("doctor.txt");
	BufferedReader reader=new BufferedReader(new FileReader(f));
	Random generator=new Random();
	int random=generator.nextInt(28)+1;
	String s="";
	for(int i=0; i<random; i++)
	{
		s=reader.readLine();
		System.out.println(s+" "+random);
	}
	JOptionPane.showMessageDialog(null, s);
	
	System.out.println(random);	
	Menu panel=new Menu(user);
	panel.setSize(750,100);
	panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
	panel.setVisible(true);
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	}
}
