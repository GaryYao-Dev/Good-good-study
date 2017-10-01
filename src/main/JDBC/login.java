package main.JDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.JDBC.DButil;


public class login {
    public static String getlogin(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String t = null;
        if (username == null || password == null) {
            t = "wrong";
        }
        try {
            conn = DButil.getConnection();
            if (username != null && password != null) {
                String logname = username;
                String sql = "select count(*) from users where user_name='" + username + "' and password='" + password + "' and confirm = 1";
                int nnn = 0;
                pstmt = (PreparedStatement) conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                rs.next();
                nnn = (int) rs.getLong(1);
                if (nnn == 1) {
                    t = "right";

                } else {
                    t = "wrong";
                }
//                System.out.println(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);

        }
        return t;
        }





//    public static void main(String [ ] args)
//    {
//
//        String deee = null;
//        deee = getlogin("vigoss","ab123456");
//        System.out.println(deee);
//;
//
//
//    }
    }

