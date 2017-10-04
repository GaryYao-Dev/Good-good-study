package main.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class finddetail {
    public static String getemail(int userid){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        int num=0;
        String result="";
        try {

            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select email from users where userid="+String.valueOf(userid));

            rs=pstmt.executeQuery();
            while (rs.next()){
                result=rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        return result;

    }
    public static String getname(int userid){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        int num=0;
        String result="";
        try {

            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select username from users where userid="+String.valueOf(userid));

            rs=pstmt.executeQuery();
            while (rs.next()){
                result=rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        return result;

    }
    public static String getuser(int userid){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        int num=0;
        String result="";
        try {

            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select user_name from users where userid="+String.valueOf(userid));

            rs=pstmt.executeQuery();
            while (rs.next()){
                result=rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        return result;

    }
    public static void confirm1(int a,int b){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        int num=0;
        String result="";
        try {

            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("update friendship set confirm=1 where user_id="+String.valueOf(a)+" and friend_id="+String.valueOf(b));
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("update friendship set confirm=1 where user_id="+String.valueOf(b)+" and friend_id="+String.valueOf(a));
            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }


    }
}

