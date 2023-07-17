import java.sql.*;

public class connectionManager {
public Connection getConnection() throws SQLException, ClassNotFoundException{
	Class.forName("org.postgresql.Driver");
	Connection conn=null;
	String url;
	url="jdbc:postgresql://localhost:5432/Luna";
	conn = DriverManager.getConnection(url, "postgres", "admin");
	return conn;
}
public void closeConnection(Connection c) {
	try {
		c.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
public void closePreparedStatement(PreparedStatement ps)
{
	try {
		ps.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
public void closeResultSet(ResultSet rs)
{
	try {
		rs.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
public void closeStatement(Statement st){
	try{
		st.close();
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
}
}
