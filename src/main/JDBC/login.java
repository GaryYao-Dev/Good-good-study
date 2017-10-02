package main.JDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.JDBC.DButil;

public class login {


//    public static void main(String[] args) {
//        System.out.print(getlogin("vigoss", "ab12356"));
//    }

    public static boolean getlogin(String user_name, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean r = false;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select * from users where user_name=? and password=? and confirm = TRUE ");
            pstmt.setString(1, user_name);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                r = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);

        }
        return r;
    }




}
