package main.JDBC;

import main.model.FriendProfileBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfro {


//
//        public static void main(String [ ] args)
//    {
//
//        List<FriendProfileBean> f = getFriendlistByuserid(1);
//        System.out.print(f.get(0).getUserName());
//
//    }




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

            while (rs.next()) {
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

            for (rs = pstmt.executeQuery(); rs.next(); imgPath = rs.getString("photo")) {
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


    public static String getUserNameByUserid(int userid) {
        String userName = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select userName from users where userid = ?");
            pstmt.setInt(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                userName = rs.getString("userName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }

        return userName;

    }


    public static int getUseridByUsername(String user_name) {
        int userid = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select userid from users where user_name = ?");
            pstmt.setString(1, user_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                userid = rs.getInt("userid");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }

        return userid;

    }

    public static String getUserEmailByUserid(int userid) {
        String email = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select email from users where userid = ?");
            pstmt.setInt(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }

        return email;

    }

    public static String getUser_nameByUserid(int userid) {
        String user_name = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select user_name from users where userid = ?");
            pstmt.setInt(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user_name = rs.getString("user_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }

        return user_name;

    }




    public static List<FriendProfileBean> getFriendlistByuserid(int user_id) {
        List<FriendProfileBean> friend_list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select * from users u,friendship f where u.userid = f.friend_id and f.user_id = ? and f.confirm = TRUE and u.confirm = TRUE and u.confirm_email = TRUE ");
            pstmt.setInt(1,user_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                FriendProfileBean f = new FriendProfileBean();
                f.setUserid(rs.getInt("userid"));
                f.setPhoto(rs.getString("photo"));
                f.setUserName(rs.getString("userName"));
                f.setEmail(rs.getString("email"));
                f.setGender(rs.getString("gender"));
                f.setU_day(rs.getString("u_day"));
                f.setU_month(rs.getString("u_month"));
                f.setU_year(rs.getString("u_year"));
                friend_list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);

        }
        return friend_list;
    }

}