import java.sql.*;
import java.util.Random;
import javax.swing.*;
public class Game {
    private Random random;
    private String word, b1, b2, b3, b4, b5;
    private int size = 1;
    public Game(){
        random=new Random();
        String sql = "select count(*) from words";
        connectionManager cm = new connectionManager();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = cm.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next())
                size = Integer.parseInt(rs.getString(1));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            cm.closePreparedStatement(ps);
            cm.closeResultSet(rs);
            cm.closeConnection(con);
        }
    }
    public void set(){

    }
    public void start() {
        connectionManager cm = new connectionManager();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int number =  random.nextInt(size)+1;
        try {
            con = cm.getConnection();
            String sql = "select * from Words where id=" + number;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                word = rs.getString(2);
                b1 = rs.getString(3);
                b2 = rs.getString(4);
                b3 = rs.getString(5);
                b4 = rs.getString(6);
                b5 = rs.getString(7);
                Tabu tabu = new Tabu(word, b1, b2, b3, b4, b5, this, 0, 3);
                tabu.setSize(350, 500);
                tabu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tabu.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            cm.closePreparedStatement(ps);
            cm.closeResultSet(rs);
            cm.closeConnection(con);
        }
    }
    public void newWord(int point, int pas){
        connectionManager cm = new connectionManager();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = cm.getConnection();
            String sql = "select * from Words where id=" + random.nextInt(5);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                word = rs.getString(2);
                b1 = rs.getString(3);
                b2 = rs.getString(4);
                b3 = rs.getString(5);
                b4 = rs.getString(6);
                b5 = rs.getString(7);
                Tabu tabu = new Tabu(word, b1, b2, b3, b4, b5, this, 0, 3);
                tabu.setSize(350, 500);
                tabu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tabu.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            cm.closePreparedStatement(ps);
            cm.closeResultSet(rs);
            cm.closeConnection(con);
        }
    }
}
