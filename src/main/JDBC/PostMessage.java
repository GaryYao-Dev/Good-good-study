package main.JDBC;


import main.model.postMessageBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * class handle data access needed by postMessageServlet
 *
 * use Mysql
 *
 */


public class PostMessage {

//    public static void main(String [ ] args)
//    {
//
////        System.out.println(getUseridByP_id(23432353));
//
//
////
////       // DeletePostMessageByP_id(2);
////        List<postMessageBean> l = getPostByUserid(3);
////        for (postMessageBean p :l
////             ) {
////              System.out.println(p.getP_id());
////               System.out.println(p.getP_content());
////               System.out.println(p.getP_image());
////                System.out.println(p.getP_time());
////            System.out.println(p.getP_userid());
////            System.out.println(p.getUsername());
////            System.out.println(p.getUserPhoto());
////
////          }
//////        //insertMessage("6666666",null,5234123);
////
////
//    }






    /**
     * get all infromation by userid
     * @param p_userid
     * @return all posts from this user
     */

    public static   List<postMessageBean> getPostByUserid(int p_userid){
        List<postMessageBean> _list= new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("\n" +
                    "(\n" +
                    "select p.p_id,p.p_content,p.p_image,p.p_time,p.p_userid,t.name,t.photo\n" +
                    "from post p,\n" +
                    "(\n" +
                    "select u.userid as id ,u.userName as name,u.photo as photo\n" +
                    "from users u, \n" +
                    "(\n" +
                    "select f.friend_id as id\n" +
                    "from users u, friendship f \n" +
                    "where u.userid = f.user_id\n" +
                    "and f.confirm = true\n" +
                    "and user_id = ?\n" +
                    ") as  s \n" +
                    "where s.id = u.userid \n" +
                    "and u.confirm =true\n" +
                    ") as t\n" +
                    "where p.p_userid = t.id \n" +
                    "and p.isVaild = true\n" +
                    ")union\n" +
                    "\n" +
                    "(\n" +
                    "select p.p_id,p.p_content,p.p_image,p.p_time,p.p_userid,t.userName,t.photo\n" +
                    "from post p , users t \n" +
                    "where p.isVaild = true\n" +
                    "and p.p_userid = t.userid\n" +
                    "and p.p_userid = ?\n" +
                    ")order by p_time desc");

            pstmt.setInt(1,p_userid);
            pstmt.setInt(2,p_userid);
            rs = pstmt.executeQuery();
            while (rs.next()){
                postMessageBean p = new postMessageBean();
                p.setP_id(rs.getInt("p_id"));
                p.setP_content(rs.getString("p_content"));
                p.setP_image(rs.getString("p_image"));
                p.setP_time(rs.getTimestamp("p_time"));
                p.setP_userid(rs.getInt("p_userid"));
                p.setUsername(rs.getString("name"));
                p.setUserPhoto(rs.getString("photo"));
                _list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        return _list;
    }


    /**
     * insert a new record into database
     * @param p_content  post的内容
     * @param p_image     post的图片
     * @param p_userid   谁post的
     */

    public static   void insertMessage(String p_content, String p_image,int p_userid){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;


        try {
            conn = DButil.getConnection();

            //get current time
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());

            // insert data
            pstmt = conn.prepareStatement("insert into post(p_content, p_image, p_time, p_userid) values(?,?,?,?)");
            pstmt.setString(1, p_content);
            pstmt.setString(2, p_image);
            pstmt.setTimestamp(3,timestamp);
            pstmt.setInt(4,p_userid);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }

    }


    /**
     * delete post
     * @param p_id
     */

    public static   void DeletePostMessageByP_id(int p_id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            conn = DButil.getConnection();

            // delete data
            pstmt = conn.prepareStatement("update post set isVaild = false where p_id = ?");
            pstmt.setInt(1, p_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }
    }





    public static int getUseridByP_id(int p_id){
        int p_userid = 0;
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select p_userid from post where p_id = ?");
            pstmt.setInt(1,p_id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                p_userid = rs.getInt("p_userid");
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        return  p_userid;

    }

    public static String getPostContentByP_id(int p_id){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String p_content = null;
        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select p_content from post where p_id = ?");
            pstmt.setInt(1,p_id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                p_content = rs.getString("p_content");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        return  p_content;
    }

    public static String getIMGByP_id(int p_id){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        String p_img = null;
        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select p_image from post where p_id = ?");
            pstmt.setInt(1,p_id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                p_img = rs.getString("p_image");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        return  p_img;
    }






}
