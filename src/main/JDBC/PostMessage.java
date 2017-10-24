package main.JDBC;


import main.admin.curation_model;
import main.model.postMessageBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * class handle data access needed by postMessageServlet
 *
 * use Mysql
 *
 */


public class PostMessage {

//    public static void main(String [ ] args)
//    {
//        List<postMessageBean> p  = getPostByUserid(1);
//        for (int i = 0; i < p.toArray().length; i++) {
//            System.out.println(p.get(i).getP_keyword());
//        }
//
//    }
//





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
                    "select p.p_keyword,p.p_organization,p.p_location,p.p_person,p.p_id,p.p_content,p.p_image,p.p_time,p.p_userid,t.name,t.photo\n" +
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
                    "select p.p_keyword,p.p_organization,p.p_location,p.p_person,p.p_id,p.p_content,p.p_image,p.p_time,p.p_userid,t.userName,t.photo\n" +
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
                p.setP_location(rs.getString("p_location"));
                p.setP_person(rs.getString("p_person"));
                p.setP_keyword(rs.getString("p_keyword"));
                p.setP_organization(rs.getString("p_organization"));
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

    public static   void insertMessage(String p_content, String p_image,int p_userid, String realPath) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;


        try {
            conn = DButil.getConnection();

            //get current time
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());

            // insert data
            pstmt = conn.prepareStatement(
                    "insert into post(p_content, p_image, p_time, p_userid, p_location, p_organization, p_person, p_keyword) " +
                            "values(?,?,?,?, ?, ?, ?, ?)");
            pstmt.setString(1, p_content);
            pstmt.setString(2, p_image);
            pstmt.setTimestamp(3,timestamp);
            pstmt.setInt(4,p_userid);

            //curation data
            // bully
            curation_model curationModel= new curation_model(realPath);
            curationModel.checkBully(p_userid, p_content);

            //location
            Set<String> set_location = curationModel.extractLocation(p_content);
            String p_location = "";
            for (String s: set_location
                 ) {
                p_location+=s.substring(0, s.length()-1)+", ";
            }
            pstmt.setString(5, p_location);
            //organization
            Set<String> set_organization = curationModel.extractOrganization(p_content);
            String p_organization = "";
            for (String s: set_organization
                    ) {
                p_organization+=s.substring(0, s.length()-1)+", ";
            }
            pstmt.setString(6, p_organization);
            //person
            Set<String> set_person = curationModel.extractPerson(p_content);
            String p_person = "";
            for (String s: set_person
                    ) {
                p_person+=s.substring(0, s.length()-1)+", ";
            }
            pstmt.setString(7, p_person);
            //keyWord
            Set<String> set_keyWord = curationModel.extractKeywords(p_content);
            String p_keyWord = "";
            for (String s: set_keyWord
                    ) {
                p_keyWord+=s+", ";
            }
            pstmt.setString(8, p_keyWord);

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


    public static  List<postMessageBean> getUserlistbySearchMessage(String p_content){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<postMessageBean> _list= new ArrayList<>();

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("SELECT * FROM post where p_content like '%"+p_content+"%'");
            rs = pstmt.executeQuery();
            while (rs.next()){
                postMessageBean p = new postMessageBean();
                p.setP_id(rs.getInt("p_id"));
                p.setP_content(rs.getString("p_content"));
                p.setP_time(rs.getTimestamp("p_time"));
                p.setP_userid(rs.getInt("p_userid"));
                _list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        return _list;

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
