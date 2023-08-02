import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.*;
import java.sql.*;
import java.time.*;

public class AI extends JFrame {
	private JButton Send;
	private String user;
	private String mood;
	private JTextField Message;
	private Answer answer;
	private String CONTEXT="greeting";
	private int love;
	private int hate;
	private final Color red=new Color(255,0,0);
	private final Color black=new Color(0,0,0);
AI(String user){
	super("Texting Field");
	this.user=user;
	userInit(user);
	mood = moodInit();
answer=new Answer();
answer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
answer.setSize(450,700);
answer.setVisible(true);
//answer.setAlwaysOnTop(true);
	Send=new JButton("Send");
	Send.setBackground(black);
	Send.setForeground(red);
	Message=new JTextField();
	Message.setBackground(black);
	Message.setForeground(red);
	BorderLayout layout=new BorderLayout();
	setLayout(layout);
	add(Send, BorderLayout.EAST);
	add(Message, BorderLayout.CENTER);
	Handler handler=new Handler();
	Send.addActionListener(handler);	
}

private class Handler implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(""))
		{
			AI.this.setVisible(false);
			answer.setVisible(false);
			Menu panel=new Menu(user);
			panel.setSize(750,100);
			panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
			panel.setVisible(true);
		}
		else
		{
			String s=Message.getText();
			Message.setText("");
			answer.cevap(s);
		}
	}

	
}
private class Answer extends JFrame{
	private JButton emoji;
	private JTextArea answer;
	private BorderLayout layout2;
	private String feel="HAPPY";
	private JScrollPane scroll;
	private ImageIcon icon = null;
	private int[] feelings=new int[4];
Answer()
{
	super("Luna");
	layout2=new BorderLayout();
	emoji=new JButton("");
	LocalDateTime ldt = LocalDateTime.now();
	String now = ldt.toString();
	now = now.substring(5,10);
	answer=new JTextArea(513, 55);
	answer.setText("If a man can fly over an ocean and no mountains can stay in his way");
	if(isSpecial(now)) {
		icon = new ImageIcon("Luna_Dress.gif");
	}
	else{
		String mnt = now.substring(0,2);
		int month = Integer.parseInt(mnt);
		if(now.equals("10-31"))
			icon = new ImageIcon("Luna_Halloween.gif");
		else if((month > 2) && (month <= 5))
			icon = new ImageIcon("Luna_Spring.gif");
		else if((month > 5) && (month <= 8))
			icon = new ImageIcon("Luna_Summer.gif");
		else if((month > 8) && (month <= 11))
			icon = new ImageIcon("Luna_Fall.gif");
		else
			icon = new ImageIcon("Luna_Winter.gif");
	}
	if(icon == null)
		icon = new ImageIcon("Luna.gif");
	emoji.setIcon(icon);
	emoji.setBackground(black);
	answer.setEditable(false);
	scroll=new JScrollPane(answer);
	scroll.setEnabled(true);
	answer.setBackground(black);
	answer.setForeground(red);
	setLayout(layout2);
	add(emoji, BorderLayout.NORTH);
	add(scroll,BorderLayout.CENTER);
	Handler listener=new Handler();
	emoji.addActionListener(listener);
}

	private boolean isSpecial(String now) {
		connectionManager cm = new connectionManager();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = cm.getConnection();
			String sql ="Select * from Dates where SpecialDay like \'"+now+"\'";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				answer.setText(rs.getString(2));
				return true;
			}
			if(ps!=null) {cm.closePreparedStatement(ps);}
			if(rs!=null) {cm.closeResultSet(rs);}
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			cm.closePreparedStatement(ps);
			cm.closeResultSet(rs);
			cm.closeConnection(con);
		}
		return false;
	}

	private void cevap(String s){
	connectionManager cm = new connectionManager();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		con=cm.getConnection();
		String sql = "";
		String cevap = null;
		if(s.contains("Rule") || s.contains("rule"))
		{
			int index=s.indexOf("Rule");
				if(index<0)
					index=s.indexOf("rule");
			String rule = "";
			if(s.length() > 5)
				rule=s.substring(index+5,s.length());
			if(rule.length() == 0)
				answer.setText("I need a number you know");
			else {
				int id = Integer.parseInt(rule);
				sql = "Select rule from rules where id=" + id + ";";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					answer.setText(rs.getString(1));
				}
				CONTEXT = "Rule";
			}
		}
		else if((s.contains(" or ")) && (s.contains("Choose"))){
			Random random = new Random();
			int number = random.nextInt(2);
			if(number == 1)
				answer.setText("First one");
			else
				answer.setText("Second one");
		}
		else if(s.contains("run \"")){
			String cmd = s.substring(s.indexOf("run \"")+5, s.lastIndexOf('\"'));
			Terminal terminal = new Terminal(cmd);
		}
		else if((s.contains("set up the mood =")) || (s.contains("Set up the mood ="))){
			String playlist = s.substring(s.indexOf('=')+2);
			answer.setText(playlist);
			String cmd = "cmd /c C:\\Users\\"+user+"\\Music\\Playlists\\"+playlist+".wpl";
			Terminal terminal = new Terminal(cmd);
		}
		else {
			if (mood.equals("angry"))
				sql = "Select res_out from Res where context like\'" + CONTEXT + "\' and input_in like \'%" + s + "%\' and mood like \'angry\';";
			else if (mood.equals("love"))
				sql = "Select res_out from Res where context like\'" + CONTEXT + "\' and input_in like \'%" + s + "%\' and mood like \'love\';";
			else if (mood.equals("happy"))
				sql = "Select res_out from Res where context like\'" + CONTEXT + "\' and input_in like \'%" + s + "%\' and mood like \'happy\';";
			else if (mood.equals("sad"))
				sql = "Select res_out from Res where context like\'" + CONTEXT + "\' and input_in like \'%" + s + "%\' and mood like \'sad\';";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cevap = rs.getString(1);
				answer.setText(cevap);
			} else if ((cevap == null) && ((mood.equals("angry") || (mood.equals("love"))))) {
				if (mood.equals("angry"))
					sql = "Select res_out from Res where context like\'" + CONTEXT + "\' and input_in like \'%" + s + "%\' and mood like \'sad\';";
				else if (mood.equals("love"))
					sql = "Select res_out from Res where context like\'" + CONTEXT + "\' and input_in like \'%" + s + "%\' and mood like \'happy\';";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					cevap = rs.getString(1);
					answer.setText(cevap);
				}
			}
			if (cevap == null) {
				sql = "Select res_out from Res where context like\'" + CONTEXT + "\' and input_in like \'%" + s + "%\' and mood like \'void\';";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					cevap = rs.getString(1);
					answer.setText(cevap);
				} else {
					if (mood.equals("angry"))
						sql = "Select res_out, context from Res where input_in like \'%" + s + "%\' and mood like \'angry\';";
					else if (mood.equals("love"))
						sql = "Select res_out, context from Res where input_in like \'%" + s + "%\' and mood like \'love\';";
					else if (mood.equals("happy"))
						sql = "Select res_out, context from Res where input_in like \'%" + s + "%\' and mood like \'happy\';";
					else if (mood.equals("sad"))
						sql = "Select res_out, context from Res where input_in like \'%" + s + "%\' and mood like \'sad\';";
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					if (rs.next()) {
						cevap = rs.getString(1);
						answer.setText(cevap);
						CONTEXT = rs.getString(2);
					} else if ((cevap == null) && ((mood.equals("angry") || (mood.equals("love"))))) {
						if (mood.equals("angry"))
							sql = "Select res_out, context from Res where input_in like \'%" + s + "%\' and mood like \'sad\';";
						else if (mood.equals("love"))
							sql = "Select res_out, context from Res where input_in like \'%" + s + "%\' and mood like \'happy\';";
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();
						if (rs.next()) {
							cevap = rs.getString(1);
							answer.setText(cevap);
							CONTEXT = rs.getString(2);
						}
					}
					if (cevap == null) {
						sql = "Select res_out, context from Res where input_in like \'%" + s + "%\' and mood like \'void\';";
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();
						if (rs.next()) {
							cevap = rs.getString(1);
							answer.setText(cevap);
							CONTEXT = rs.getString(2);
						}
					}
				}
				if (cevap == null) {
					if(s.indexOf("Perhaps") == 0)
						answer.setText("Don't you know?");
					else if(s.indexOf("Yes, ") == 0)
						answer.setText("And how do you feel about this?");
					else if(s.indexOf("No, ") == 0)
						answer.setText("Why don't you tell me more about this?");
					else if(s.contains("I am confused"))
						answer.setText("Are you here because you are confused?");
					else if(s.contains("I am nervous"))
						answer.setText("Do you want to talk about it?");
					else if(s.contains("I want "))
						answer.setText("How long have you wanted that?");
					else if((s.contains("I am saying ") || (s.contains("I said "))))
						answer.setText("Do you know anyone else who said that?");
					else if(s.contains("because "))
						answer.setText("Does that reason seem to explain anything else?");
					else if(s.indexOf("I guess") == 0 || s.contains("My guess "))
						answer.setText("Aren't you sure?");
					else if(s.contains("You")  || s.contains("you"))
						answer.setText("You are not actually talking about me aren't you?");
					else
						answer.setText("Can you tell me more about this?");
					sql = "insert into Res (input_in, context, res_out, mood) values(\'" + s + "\', \'" + CONTEXT + "\', \'\', \'" + mood + "\');";
					File file = new File("updates.txt");
					BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
					writer.newLine();
					writer.write(sql);
					writer.close();
				}
			}
		}
		if(answer.getText().equals("Grab a popcorn")){
			File f=new File("E:\\MovieNight\\Movies.txt");
			BufferedReader reader=new BufferedReader(new FileReader(f));
			Random generator=new Random();
			int numberOfMovies = 0;
			String movieName = reader.readLine();
			String mv = movieName;
			while(movieName != null){
				numberOfMovies++;
				movieName = reader.readLine();
			}
			reader.close();
			reader = new BufferedReader(new FileReader(f));
			int movie = generator.nextInt(numberOfMovies)+1;
			int counter = 0;
			while(counter < movie) {
				mv = reader.readLine();
				counter ++;
			}
			String cmd = "cmd /c E:\\MovieNight\\"+mv+".mp4";
			Terminal terminal = new Terminal(cmd);
		}
		else if(answer.getText().equals("Let me check it.") || answer.getText().equals("Let me check it, with my camera.")){
			String cmd = "cmd.exe /c start microsoft.windows.camera:";
			Terminal terminal = new Terminal(cmd);
		}
		if(ps!=null) {cm.closePreparedStatement(ps);}
		if(rs!=null) {cm.closeResultSet(rs);}
		con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		if(ps != null)
			cm.closePreparedStatement(ps);
		if(rs != null)
			cm.closeResultSet(rs);
		if(con != null)
			cm.closeConnection(con);
	}
	System.out.println(s);
	System.out.println(answer.getText());
}
}
private void userInit(String user){
	connectionManager cm = new connectionManager();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Statement statement = null;
	try{
		con = cm.getConnection();
		String sql ="Select * from users where name = \'"+user+"\'";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		if(rs.next()) {
			love = Integer.parseInt(rs.getString(2));
			hate = Integer.parseInt(rs.getString(3));
		}
		else{
			sql = "insert into users (name, love, hate) values (\'"+user+"\', 50, 50)";
			statement = con.createStatement();
			statement.executeUpdate(sql);
		}
		if(ps!=null) {cm.closePreparedStatement(ps);}
		if(rs!=null) {cm.closeResultSet(rs);}
		con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		if(ps != null)
			cm.closePreparedStatement(ps);
		if(rs != null)
			cm.closeResultSet(rs);
		if(statement != null)
			cm.closeStatement(statement);
		cm.closeConnection(con);
	}
}
private String moodInit(){
	String mood = "";
	int dif = love - hate;
	if(dif < -20)
		mood = "angry";
	else if(dif > 50)
		mood = "love";
	else if(dif < 0)
		mood = "sad";
	else
		mood = "happy";
	return mood;
}
}
