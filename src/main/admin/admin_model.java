package main.admin;

import java.sql.*;
import java.util.ArrayList;

public class admin_model {


//    private ArrayList<ArrayList> admin_list;
    Connection conn;

    public admin_model() {
        try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            String url="jdbc:mysql://localhost:3306/unswbook";


            conn = DriverManager.getConnection(url,    "root","root");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

//    public ArrayList<ArrayList> getAdmin_list() {
//        return admin_list;
//    }

    public ArrayList<ArrayList> getTable(String sql) throws SQLException {
        ArrayList<ArrayList> result = new ArrayList<ArrayList>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            ArrayList row = new ArrayList();
            int columnIndex = 1;
            try{
                while (true){
                    row.add(rs.getObject(columnIndex));
                    columnIndex += 1;
                }
            }catch (Exception e){

            }
            result.add(row);
        }
        rs.close();
        stmt.close();
        return result;
    }

    public void ban(Integer userID) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET confirm=0 WHERE userid=?");
        pstmt.setInt(1, userID);
        pstmt.executeUpdate();
        pstmt.close();
        log_ban(userID);
    }

    public void unban(Integer userID) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET confirm=1 WHERE userid=?");
        pstmt.setInt(1, userID);
        pstmt.executeUpdate();
        pstmt.close();
        log_unban(userID);
    }

    private void insert_log(Integer userID, String activity) throws SQLException {
        PreparedStatement log = conn.prepareStatement("INSERT INTO log (userID, activity) VALUES (?,?)");
        log.setInt(1, userID);
        log.setString(2, activity);
        log.executeUpdate();
        log.close();
    }

    public void log_register(Integer userID) throws SQLException {
        String activity = "Register";
        insert_log(userID, activity);
    }

    public void log_login(Integer userID) throws SQLException {
        String activity = "Login";
        insert_log(userID, activity);
    }

    public void log_out(Integer userID) throws SQLException {
        String activity = "Logout";
        insert_log(userID, activity);
    }

    public void log_add_friend(Integer userID, Integer friendID) throws SQLException {
        String activity = "Add "+friendID+" as friend";
        insert_log(userID, activity);
    }

    public void log_delete_friend(Integer userID, Integer friendID) throws SQLException {
        String activity = "Delete friend "+ friendID;
        insert_log(userID, activity);
    }

    public void log_post(Integer userID, String content) throws SQLException {
        String activity = "Post:"+content;
        insert_log(userID, activity);
    }

    public void log_delete_post(Integer userID, String content) throws SQLException {
        String activity = "Delete post:"+content;
        insert_log(userID, activity);
    }



    public void log_ban(Integer userID) throws SQLException {
        String activity = "Banned";
        insert_log(userID, activity);
    }

    public void log_unban(Integer userID) throws SQLException {
        String activity = "Unbanned";
        insert_log(userID, activity);
    }
}
