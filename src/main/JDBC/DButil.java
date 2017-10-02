package main.JDBC;


import java.sql.*;
import java.util.ResourceBundle;




public class DButil {
    private static String driver = null;
    private static String db_url = null;
    private static String user = null;
    private static String password = null;

    //加载数据库驱动
    static {

        ResourceBundle rb = ResourceBundle.getBundle("dbinfro");
        driver = rb.getString("driver");
        db_url = rb.getString("db_url");
        user = rb.getString("user");
        password = rb.getString("password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //建立连接 build connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(db_url,user,password);
    }

    //关闭资源 free connection
    public  static  void closeall(Connection conn, PreparedStatement pstmt, ResultSet rs){

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}



