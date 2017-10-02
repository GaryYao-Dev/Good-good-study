package main.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class likeMessage {

//
//    //test
//    public static void main(String [ ] args)
//    {
//
//        HashMap<Integer,Boolean> h = new HashMap<Integer, Boolean>();
//        h = likelist(1);
//        System.out.println(h);
//
//        System.out.println(getlikeUserList(23432478));
//
//
//    }
//
//
//
//
//



    /**
     * add like
     * @param p_id post_id
     * @param likeUser_id  friends who likes your post
     */
    public static void likeMessage(int p_id, int likeUser_id ){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {

            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("insert into likelist(p_id, likeUser_id) VALUES(?,?)");
            pstmt.setInt(1,p_id);
            pstmt.setInt(2,likeUser_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }


    }




    public static void unlikePost(int p_id, int likeUser_id){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("delete from likelist where p_id =? and likeUser_id = ?");
            pstmt.setInt(1,p_id);
            pstmt.setInt(2,likeUser_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }



    }


    /**
     * 返回 自身点过赞 的 所有 post 哈希表
     * @param likeUser_id
     * @return
     */
    public static HashMap<Integer,Boolean> likelist(int likeUser_id){
        HashMap<Integer,Boolean> hlist = new HashMap<Integer, Boolean>();
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select p_id from likelist where likeUser_id = ?;");
            pstmt.setInt(1,likeUser_id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                hlist.put(rs.getInt("p_id"),true);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        return hlist;

    }


    /**
     * 返回 list  所有点过该 post  赞的 人名列表
     */




    public  static List<String> getlikeUserList(int p_id){

        List<String> _list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select u.userName from users u, likelist l where u.userid = l.likeUser_id and u.confirm = true and l.p_id =?");
            pstmt.setInt(1,p_id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                _list.add(rs.getString("userName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }

        return _list;

    }



}
