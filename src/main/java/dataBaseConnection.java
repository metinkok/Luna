import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dataBaseConnection {
	public String getRule(int i)
	{
		connectionManager connectionManager=new connectionManager();
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs= null;
		String re="";
		try {
			String sql="select * from rules where id="+i;
			con=connectionManager.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			re=rs.getString(1)+": "+rs.getString(2);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			connectionManager.closePreparedStatement(ps);
			connectionManager.closeResultSet(rs);
			connectionManager.closeConnection(con);
		}
		return re;
	}
	public String getResponse(String input, String context)
	{
		String sql="select res_out from Res where context="+context;
		
		return"";
	}
}
