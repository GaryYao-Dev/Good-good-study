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

            pstmt = conn.prepareStatement("select senderId,n_content,n_time from notification where readornot=0 and receiverid="+String.valueOf(userid));
            rs=pstmt.executeQuery();
            while (rs.next()){
                result.add(rs.getString(1)+" said "+rs.getString(2)+" at "+rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closeall(conn,pstmt,rs);
        }
        System.out.print(result);
        return result;
    }
    public void te(){
        System.out.print("1231231231");
    }
}
