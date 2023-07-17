import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Main {
	public static void main(String[] args)
	{
		String user = System.getProperty("user.name");
		Menu panel = new Menu(user);
		panel.setSize(750, 100);
		panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setVisible(true);
	}
}
