package main.JDBC;

import main.model.notificationBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendNotification {


//        public static void main(String [ ] args)
//    {
////       sendnotification(4,2,"sdfasdfdsafadsf");
//        List<notificationBean> l = new ArrayList<>();
//        l = getAllUnReadNotification(2);
//        for (notificationBean n :l
//             ) {
//            System.out.println(n.getN_content());
//            System.out.println(n.getN_time());
//            System.out.println(n.getSenderID());
//        }
//    }

    public static   void sendnotification(int senderID, int receiverID, String n_content){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;


        try {
            conn = DButil.getConnection();

            //get current time
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());

            // insert data
            pstmt = conn.prepareStatement("insert into notification(n_content, senderID, receiverID, n_time) VALUES (?,?,?,?)");
            pstmt.setString(1,n_content);
            pstmt.setInt(2,senderID);
            pstmt.setInt(3,receiverID);
            pstmt.setTimestamp(4,timestamp);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }

    }



    public static   List<notificationBean> getAllUnReadNotification(int userid){
        List<notificationBean> _list= new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select * from notification where receiverID = ? and readornot = false");
            pstmt.setInt(1,userid);
            rs = pstmt.executeQuery();
            while (rs.next()){
                notificationBean n = new notificationBean();
                n.setN_content(rs.getString("n_content"));
                n.setSenderID(rs.getInt("senderID"));
                n.setReceiverID(rs.getInt("receiverID"));
                n.setN_time(rs.getTimestamp("n_time"));
                _list.add(n);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }

        return  _list;

    }





    public static  List<String> find(int userid){

        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<String> res = new ArrayList<>();
        try {

            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select senderId,n_content,n_time from notification where receiverId="+String.valueOf(userid));
            rs=pstmt.executeQuery();
            int num=0;
            while (rs.next()){
                num=num+1;
                res.add(String.valueOf(rs.getLong(1))+" said "+rs.getString(2)+"at"+rs.getString(3));
            }
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        return res;
    }



}
