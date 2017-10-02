package main.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class friendconfirm {
    public static void confirmfriend(int userid, int  friendid) {
        Connection conn = null;
        PreparedStatement pstmt1 =null;
        PreparedStatement pstmt2 =null;

        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            conn = DButil.getConnection();
                pstmt1 = conn.prepareStatement("update friendship set confirm = 1 where friend_id ='"+friendid+"'and user_id='"+userid+"' and confirm=0");
                pstmt2 = conn.prepareStatement("update friendship set confirm = 1 where friend_id ='"+userid+"'and user_id='"+friendid+"' and confirm=0");
//                System.out.println(t);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt1, rs1);
            DButil.closeall(conn, pstmt2, rs2);
        }
    }








}
