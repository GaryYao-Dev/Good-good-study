package comp9321;

import main.JDBC.DButil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class notific {
    public List<String> getstr(int userid){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        List<String> result= new ArrayList<>();
        try {

            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select n_content,n_time from notification where readornot=0 and receiverid="+String.valueOf(userid));
            rs=pstmt.executeQuery();
            while (rs.next()){
                result.add(rs.getString(1)+" at "+rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        System.out.print(result);
        return result;
    }
    public void delete(int userid,String ss){
        int a=Integer.valueOf(ss);
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        int num=0;
        String s1="",s2="";

        try {

            conn = DButil.getConnection();
            int nnn=0;
            pstmt = conn.prepareStatement("select n_content,n_time,notificationID from notification where readornot=0 and receiverid="+String.valueOf(userid));
            rs=pstmt.executeQuery();
            while (rs.next()){
                num=num+1;
                s1=rs.getString(1);
                s2=rs.getString(2);
                nnn=rs.getInt(3);
                if (num==a+1)
                    break;
            }
            System.out.println("update notification set readornot=1 where n_content='"+s1+"' and n_time='"+s2+"' and receiverid="+String.valueOf(userid));
            pstmt=conn.prepareStatement("update notification set readornot=1 where n_content='"+s1+"' and n_time='"+s2+"' and receiverid="+String.valueOf(userid)+ " and notificationID="+String.valueOf(nnn));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
    }
}
