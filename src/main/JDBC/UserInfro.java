package main.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfro {


//
//        public static void main(String [ ] args)
//    {
////        System.out.println(getUserNameByUserid(7));
//    }
//
//

    public static String getImgPathByUserid(int userid){
        String imgPath = null;
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select photo from users where userid = ?");
            pstmt.setInt(1,userid);
            rs = pstmt.executeQuery();
            while (rs.next()){
                imgPath = rs.getString("photo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        System.out.println(imgPath);
        return  imgPath;
    }

    public static String getUserNameByUserid(int userid){
        String userName = null;
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select userName from users where userid = ?");
            pstmt.setInt(1,userid);
            rs = pstmt.executeQuery();
            while (rs.next()){
                userName = rs.getString("userName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        return  userName;

    }
    public static int getUseridByUsername(String user_name){
        int userid = 0;
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select userid from users where user_name = ?");
            pstmt.setString(1,user_name);
            rs = pstmt.executeQuery();
            while (rs.next()){
                userid = rs.getInt("userid");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }

        return  userid;

    }



}
