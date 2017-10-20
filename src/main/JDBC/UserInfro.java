package main.JDBC;

import main.model.EdgeBean;
import main.model.FriendProfileBean;
import main.model.postMessageBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfro {


//        public static void main(String[] args) {
//
//
//
//            List<postMessageBean> p = new ArrayList<>();
//            p= getpostlist();
//            for (int i = 0; i <p.toArray().length ; i++) {
//                System.out.println(p.get(i).getP_content());
//            }
//
//
//    }


    public static ArrayList<FriendProfileBean> getUserlist() {
        ArrayList<FriendProfileBean> userlist = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select userid,userName,gender,u_year,u_month,u_day from users where confirm_email = TRUE and confirm = TRUE ");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                FriendProfileBean f = new FriendProfileBean();
                f.setUserid(rs.getInt("userid"));
                f.setUserName(rs.getString("userName"));
                f.setGender(rs.getString("gender"));
                f.setU_year(rs.getString("u_year"));
                f.setU_month(rs.getString("u_month"));
                f.setU_day(rs.getString("u_day"));
                userlist.add(f);
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }
        return userlist;
    }

    public static ArrayList<postMessageBean> getpostlist() {
        ArrayList<postMessageBean> postlist = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select p_userid,p_id,p_content,p_time from post where isVaild = TRUE ");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                postMessageBean p = new postMessageBean();
                p.setP_userid(rs.getInt("p_userid"));
                p.setP_id(rs.getInt("p_id"));
                p.setP_content(rs.getString("p_content"));
                p.setP_time(rs.getTimestamp("p_time"));
                postlist.add(p);
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }
        return postlist;
    }

    public static ArrayList<EdgeBean> getfriendship(){

        ArrayList<EdgeBean> friendshiplist = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select user_id,friend_id from friendship where confirm = TRUE ");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                EdgeBean e = new EdgeBean();
                e.setFrom(rs.getInt("user_id"));
                e.setTo(rs.getInt("friend_id"));
                friendshiplist.add(e);
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }
        return friendshiplist;


    }

    public static ArrayList<EdgeBean> getpostship(){

        ArrayList<EdgeBean> postshiplist = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select p_userid,p_id from post WHERE isVaild = TRUE ");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                EdgeBean e = new EdgeBean();
                e.setFrom(rs.getInt("p_userid"));
                e.setTo(rs.getInt("p_id"));
                postshiplist.add(e);
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }
        return postshiplist;

    }

    public static ArrayList<EdgeBean> getlikeship(){

        ArrayList<EdgeBean> likeshiplist = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select likeUser_id,p_id from likelist ");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                EdgeBean e = new EdgeBean();
                e.setFrom(rs.getInt("likeUser_id"));
                e.setTo(rs.getInt("p_id"));
                likeshiplist.add(e);
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }
        return likeshiplist;

    }



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