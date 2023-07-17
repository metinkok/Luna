import java.awt.event.*;
 import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
public class Open extends JFrame {
private JButton redVeil;
private JTextField text;
private JTextField password;
private JPanel panel;
private final Color red = new Color(255, 0, 0);
private final Color black = new Color(0, 0, 0);
private String user;

		Open(String user) {
		super("Welcome to Personal Assistant Luna");
		this.user=user;
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		redVeil = new JButton("Login");
		redVeil.setBackground(red);
		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		text = new JTextField(user);
		text.setBackground(black);
		text.setForeground(red);
		panel.add(text);
		password = new JPasswordField("");
		password.setBackground(black);
		password.setForeground(red);
		panel.add(password);
		add(panel, BorderLayout.CENTER);
		add(redVeil, BorderLayout.EAST);

		Handler handler = new Handler();
		redVeil.addActionListener(handler);
		}

private class Handler implements ActionListener {
	private int counter = 3;

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Login")) {
			validate();
		}
	}

	public void validate() {
		int counter = 3;
		connectionManager cm = new connectionManager();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement st=null;
		try {
			con = cm.getConnection();
			String sql = "Select password from Users where name like \'" + text.getText() + "\'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			String pass = rs.getString(1);
			String usr=text.getText();
			if (pass.equals(password.getText())) {
				sql = "UPDATE lastUser set users = \'"+usr+"\' where id=1;";
				st=con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Welcome " + text.getText());
				Open.this.setVisible(false);
				Menu panel = new Menu(text.getText());
				panel.setSize(750, 100);
				panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
				panel.setVisible(true);
			} else {
				if (counter != 0) {
					JOptionPane.showMessageDialog(null, "Wrong password!");
					JOptionPane.showMessageDialog(null, counter + " tries remaining");
					counter--;
				} else {
					JOptionPane.showMessageDialog(null, "!!!Acces DENIED!!!");
					Open.this.setVisible(false);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			cm.closePreparedStatement(ps);
			cm.closeResultSet(rs);
			cm.closeStatement(st);
			cm.closeConnection(con);
		}
	}
}
}