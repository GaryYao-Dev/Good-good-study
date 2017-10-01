package main.JDBC;



//import java.beans.BeansAppletStub;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
public class FriendsSearch {

        public static List<String> getAllFriend(int userid) {
        List<String> allfriend_list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            String sql = "select * from users where userid !='" + userid + "'";
            int i = 0;
            int nnn = 7;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                allfriend_list.add(rs.getString(2));
                allfriend_list.add(rs.getString(3));
                allfriend_list.add(rs.getString(6));
                allfriend_list.add(rs.getString(9));
                allfriend_list.add(rs.getString(10));
                allfriend_list.add(rs.getString(11));
                allfriend_list.add(rs.getString(12));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);

        }
        return allfriend_list;
    }

    public static List<String> getSpecificFriend(String username, int userid) {
        List<String> specificfriend_list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            String sql = "select * from users where user_name LIKE '" +"%"+username+"%" +"'and userid !='" + userid + "'";
            int i = 0;
            int nnn = 7;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println(rs);
            while (rs.next()) {
                specificfriend_list.add(rs.getString(2));
                //System.out.println(rs.getString(2));
                specificfriend_list.add(rs.getString(3));
                //System.out.println(rs.getString(3));
                specificfriend_list.add(rs.getString(6));
                specificfriend_list.add(rs.getString(9));
               // System.out.println(rs.getString(9));
                specificfriend_list.add(rs.getString(10));
                //System.out.println(rs.getString(10));
                specificfriend_list.add(rs.getString(11));
                specificfriend_list.add(rs.getString(12));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);

        }
        return specificfriend_list;
    }

    public static List<String> getAdvancedSearch(String[] list) {
        List<String> advancedsearch_list = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        for (int i=0;i<5;i++){
            if (list[i] == null){
                list[i] ="";
            }
            if (list[i] != null ){
                list[i] = list[i];
            }
        }
//        System.out.println(list[0]);
//        System.out.println(list[1]);
//        System.out.println(list[2]);
//        System.out.println(list[3]);
//        System.out.println(list[4]);
        try {
            conn = DButil.getConnection();
            String sql = "select * from users where userName LIKE '" +"%"+list[0]+"%" +"'and gender LIKE '" +"%"+list[1]+"%"+ "' and u_year LIKE '" +"%"+list[2]+"%"+"'and u_month LIKE '" +"%"+list[3]+"%"+"'and u_day LIKE '" +"%"+list[4]+"%"+"'";
            int i = 0;
            int nnn = 7;
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
//            System.out.println(rs);
//            rs.next();
            while (rs.next()) {
                advancedsearch_list.add(rs.getString(2));
                //System.out.println(rs.getString(2));
                advancedsearch_list.add(rs.getString(3));
                //System.out.println(rs.getString(3));
                advancedsearch_list.add(rs.getString(6));
                advancedsearch_list.add(rs.getString(9));
                //System.out.println(rs.getString(9));
                advancedsearch_list.add(rs.getString(10));
                // System.out.println(rs.getString(9));
                advancedsearch_list.add(rs.getString(11));
                //System.out.println(rs.getString(10));
                advancedsearch_list.add(rs.getString(12));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);

        }
        return advancedsearch_list;
    }


    public static void deleteFriend(int friendid, int userid) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("update friendship set confirm = 0 where friend_id ='"+friendid+"'and user_id='"+userid+"' and confirm=1");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);

        }

    }




//    public static void main(String[] args) {
//
//        List<String> hhh = new ArrayList();
//        String[] bbbb = new String[5];
////        bbbb[0] = ("james");
////        bbbb[1] = (null);
////        bbbb[2] = ("1993");
////        bbbb[3] = ("6");
////        bbbb[4] = ("14");
//        int friend = 5;
//        int user =1;
//
//        deleteFriend(friend, user);
////        System.out.println(hhh);
//
//
//    }
}