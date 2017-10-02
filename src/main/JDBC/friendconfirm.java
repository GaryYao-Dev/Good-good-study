package main.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class friendconfirm {
    public static void confirmfriend(int userid, int  friendid) {
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("update friendship set confirm = 1 where friend_id =? and user_id=? and confirm=?");
            pstmt.setInt(1, friendid);
            pstmt.setInt(2, userid);
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }

    }
//    public static void main(String[] args) {
//
//       confirmfriend(7,10);
//    }
    }









