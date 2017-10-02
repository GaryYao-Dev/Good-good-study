package main.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserInfro {


//
//        public static void main(String [ ] args)
//    {
////        System.out.println(getUserNameByUserid(7));
//    }
//
//


    public static ArrayList<Integer> getFriendByUserid(int userid) {
        ArrayList<Integer> friends_list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select friend_id from friendship where userid = ?");
            pstmt.setInt(1, userid);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                friends_list.add(rs.getInt("friend_id"));
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }

        System.out.println(friends_list);
        return friends_list;
    }

    public static String getImgPathByUserid(int userid) {
        String imgPath = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select photo from users where userid = ?");
            pstmt.setInt(1, userid);

            for(rs = pstmt.executeQuery(); rs.next(); imgPath = rs.getString("photo")) {
                ;
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }

        System.out.println(imgPath);
        return imgPath;
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

    public static String getUserEmailByUserid(int userid){
        String email = null;
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select email from users where userid = ?");
            pstmt.setInt(1,userid);
            rs = pstmt.executeQuery();
            while (rs.next()){
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }

        return  email;

    }

    public static String getUser_nameByUserid(int userid){
        String user_name= null;
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select user_name from users where userid = ?");
            pstmt.setInt(1,userid);
            rs = pstmt.executeQuery();
            while (rs.next()){
                user_name = rs.getString("user_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }

        return  user_name;

    }
    public static int getUseridByUser_name(String user_name){
        int userid =  0;
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


