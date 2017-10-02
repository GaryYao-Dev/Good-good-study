package main.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class anyNotificationNotRead {
    public static boolean anyNotificationNotRead(int userid){
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        int num=0;
        try {

            conn = DButil.getConnection();

            pstmt = conn.prepareStatement("select count(*) from notification where readornot=0 and receiverID="+String.valueOf(userid));
            rs=pstmt.executeQuery();
            while (rs.next()){
                num=rs.getInt(1);
            }
            if (num==0){
                return false;
            }
            else{
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }

        return true;
    }
//	public static void main(String args[]) {
//		String a1=null;
//		System.out.println(anyNotificationNotRead(2));
//	}
}
