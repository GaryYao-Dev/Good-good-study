package main.JDBC;

import main.model.FriendProfileBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * bar 上 输入 名字 userName
     * @param username
     * @return
     */
    public static List<FriendProfileBean> getFriendByuserName(String username,int user_id) {
        List<FriendProfileBean> friend_list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select  userid,photo,userName,email,gender,u_day,u_year,u_month \n" +
                    "from users\n" +
                    "where confirm_email = true \n" +
                    "and confirm = true\n" +
                    "and userName like '%"+username+"%';");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if(FriendOrNot(user_id,rs.getInt("userid") )){
                    //已经是朋友
                    continue;
                }
                FriendProfileBean f = new FriendProfileBean();
                f.setUserid(rs.getInt("userid"));
                f.setPhoto(rs.getString("photo"));
                f.setUserName(rs.getString("userName"));
                f.setEmail(rs.getString("email"));
                f.setGender(rs.getString("gender"));
                f.setU_day(rs.getString("u_day"));
                f.setU_year(rs.getString("u_year"));
                f.setU_month(rs.getString("u_month"));
                friend_list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);

        }
        return friend_list;
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

    public static void addFriend(int friendid, int userid) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("insert into friendship(user_id,friend_id) values(?,?)");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);

        }
    }


    public static boolean FriendOrNot(int u1,int u2){
        boolean r = false;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnection();
            pstmt = conn.prepareStatement("select *\n" +
                    "from friendship\n" +
                    "where user_id = ?\n" +
                    "and friend_id= ?");
            pstmt.setInt(1,u1);
            pstmt.setInt(2,u2);
            rs = pstmt.executeQuery();
            while (rs.next()){
                //有记录 说明 已经是朋友了
                r= true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeall(conn, pstmt, rs);
        }
        return r;

    }







//
//    public static void main(String[] args) {
//        String s[] = {"","","","",""};
//        List<String> w = getAdvancedSearch(s);
//        System.out.print(w);
//
//    }
//
//

}