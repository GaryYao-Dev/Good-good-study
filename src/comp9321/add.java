package comp9321;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class add {
	private static String []a=new String[100000];
	private static int num=0;
	private static Connection connect() {


			ResourceBundle rb = ResourceBundle.getBundle("dbinfro");
			String driver = rb.getString("driver");
			String url = rb.getString("db_url");
			String username = rb.getString("user");
			String password = rb.getString("password");




		Connection conn=null;
		try {
			Class.forName(driver);
			conn=(Connection) DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	
	}
	private static void insertusers(String username,String password,String name) {
		Connection conn=connect();
		int i=0;
		String sql="insert into users(user_name,password,confirm,userid,userName) values (?,?,?,?,?)";
		int numm=number();
		numm=numm+1;
		PreparedStatement pstmt;
		String confirm="0";
		try {
			pstmt=(PreparedStatement)conn.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2, password);
			pstmt.setString(3,confirm);
			pstmt.setLong(4,numm);
			pstmt.setString(5, name);
			i=pstmt.executeUpdate();
			pstmt.close();conn.close();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void insertuserprofiles(String password, String username,String name,int year,int month,int date,String gender,String email) {
			Connection conn=connect();
			int numm=number();
			numm=numm+1;
			int i=0;
			String sql="insert into users(user_name,userName,gender,u_year,u_month,u_day,email,userid,password,confirm_email,confirm) values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt;
			String dob=year+"-"+month+"-"+date;
			try {
				pstmt=(PreparedStatement)conn.prepareStatement(sql);
				pstmt.setString(1,username);
				pstmt.setString(2, name);
				pstmt.setString(3,gender);
				pstmt.setLong(4,year);
				pstmt.setLong(5, month);
				pstmt.setLong(6, date);
				pstmt.setString(7,email);
				pstmt.setLong(8,numm);
				pstmt.setString(9,password);
				pstmt.setLong(10,0);
				pstmt.setLong(11,1);
				i=pstmt.executeUpdate();
				pstmt.close();conn.close();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}


		
	}
	private static void getall() {
		Connection conn=connect();
		String sql="select user_name from users";
		PreparedStatement pstmt;
		try {
			pstmt=(PreparedStatement)conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			int col=rs.getMetaData().getColumnCount();
			num=0;
			while (rs.next()) {
				num=num+1;
				a[num]=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static String test(String username,String password,String name,int year,int month,int day,String gender,String email) {
		getall();
		System.out.print(username);
		int i;
		Boolean f=true;
		for (i=1;i<=num;i++) {
			if (a[i].equals(username)) {
				f=false;
				break;
			}
		}
		if (gender==null){
			return "gender must be wrong";
		}
		if (f==false) {
			return "username has been use";
		}
		if (username.length()>25) {
			return "username is too long";
		}
		if (password.length()>25) {
			return "password is too long";
		}
		if (name.length()>25) {
			return "yourname is too long";
		}
		if (username.equals("")) {
			return "username can't be null";
		}
		if (password.equals("")) {
			return "password can't be null";
		}
		if (name.equals("")) {
			return "name can't be null";
		}
		if (gender.equals("")) {
			return "gender can't be null";
		}
		if (email.equals("")) {
			return "email can't be null";
		}
		if (year>2017 || year<1900)
			return "year must be wrong";
		if (month>12 || month<1)
			return "month must be wrong";
		if (day>31 || day<1)
			return "day must be wrong";

		if (gender==null){
			return "gender must be wrong";
		}
		if (!(gender.equals("F") || gender.equals("M") || gender.equals("f") || gender.equals("m")))
			return "gender must be wrong";

		if (email.length()>100)
			return "email is too long";
					
//		insertusers(username,password,name);
		insertuserprofiles(password,username,name,year,month,day,(String)gender,email);
		return "A confirm e-mail has been sent to your e-mail";
	}
    public String confirm(String user) {
        getall();
        boolean f=false;
        int i;
        for (i=1;i<=num;i++) {
            if (a[i].equals(user)) {
                f=true;
                break;
            }
        }
        if (f==false) {
            return "no registration information of this username";
        }
        Connection conn=connect();
        String sql="update users set confirm_email=1 where user_name='"+user+"'";
        PreparedStatement pstmt;
        try {
            pstmt=(PreparedStatement) conn.prepareStatement(sql);
            i=pstmt.executeUpdate();
            sql="select userid from users where user_name='"+user+"'";
            pstmt=(PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            long col=0;
            while (rs.next()) {
                col=rs.getLong(1);
            }
            int userid=(int)col;
            sql="INSERT INTO log (userID, activity) VALUES (?,?)";
            PreparedStatement log;
            log=(PreparedStatement) conn.prepareStatement(sql);
            log.setInt(1, userid);
            log.setString(2, "Register");
            log.executeUpdate();
            log.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        return "You registration has been confirm!";
    }
	
	public static String update(String username,String password1,String password2,String name,String year1,String month1,String day1,String gender,String email) {
		if (username==null)
			username="";
		if (password1==null)
			password1="";
		if (password2==null)
			password2="";
		if (name==null)
			name="";
		if (year1==null)
			year1="";
		if (month1==null)
			month1="";
		if (day1==null)
			day1="";
		if (gender==null)
			gender="";
		if (email==null)
			email="";

		Connection conn=connect();
		String sql="select count(*) from users where user_name='"+username+"' and password='"+password1+"'";
		PreparedStatement pstmt;
		int i,nnn=0;
		try {
			pstmt=(PreparedStatement)conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();

			while (rs.next()) {
				nnn=(int) rs.getLong(1);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (nnn==0) {
			return "username ,password not match";
		}
		
		if (username.length()>25) {
			return "username is too long";
		}
		if (password1.length()>25) {
			return "password is too long";
		}
		if (name.length()>25) {
			return "yourname is too long";
		}
		if (username.equals("")) {
			return "username can't be null";
		}
		if (password1.equals("")) {
			return "original password can't be null";
		}
		if (!password2.equals(""))
		{
			sql="update users set password='"+password2+"'where user_name='"+username+"'";
			try {
				pstmt=(PreparedStatement)conn.prepareStatement(sql);
				i=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!name.equals(""))
		{
			System.out.println("name");

			sql="update users set userName='"+name+"'where user_name='"+username+"'";
			try {
				pstmt=(PreparedStatement)conn.prepareStatement(sql);
				i=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!gender.equals(""))
		{
			if (!(gender.equals("F") || gender.equals("M") || gender.equals("f") || gender.equals("m")))
				return "gender must be wrong";
			sql="update users set gender='"+gender+"'where user_name='"+username+"'";
			try {
				pstmt=(PreparedStatement)conn.prepareStatement(sql);
				i=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!email.equals(""))
		{
			sql="update users set email='"+email+"'where user_name='"+username+"'";
			try {
				pstmt=(PreparedStatement)conn.prepareStatement(sql);
				i=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!year1.equals(""))
		{
			sql="update users set u_year= "+year1+" where user_name='"+username+"'";
			try {
				pstmt=(PreparedStatement)conn.prepareStatement(sql);
				i=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!month1.equals(""))
		{
			sql="update users set u_month= "+month1+" where user_name='"+username+"'";
			try {
				pstmt=(PreparedStatement)conn.prepareStatement(sql);
				i=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!day1.equals(""))
		{
			sql="update users set u_day= "+day1+" where user_name='"+username+"'";
			try {
				pstmt=(PreparedStatement)conn.prepareStatement(sql);
				i=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "has updated";
		
		
	
	}
	public static int number() {
		int nn = 0;
		Connection conn=connect();
		String sql="select user_name from users";
		PreparedStatement pstmt;
		try {
			pstmt=(PreparedStatement)conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			int col=rs.getMetaData().getColumnCount();
			num=0;
			while (rs.next()) {
				num=num+1;
				a[num]=rs.getString(1);
			}
			nn=num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return nn;
	}
	public static int getid(String username){
		Connection conn=connect();
		String sql="select userid from users where user_name='"+username+"'";
		PreparedStatement pstmt;
		int result=0;
		try {
			pstmt=(PreparedStatement)conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			int col=rs.getMetaData().getColumnCount();

			while (rs.next()) {
				result=rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static void newphoto(String username,String file) {
		System.out.println(file);
		Connection conn=connect();
		int kkk=number();
		kkk=kkk+1;
		int i=0;
		String sql="update users set photo='"+file+" 'where user_name='"+username+"'" ;
		System.out.println(sql);
		PreparedStatement pstmt;
		String confirm="0";
		try {
			pstmt=(PreparedStatement)conn.prepareStatement(sql);
			

			i=pstmt.executeUpdate();
			pstmt.close();conn.close();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public static void main(String args[]) {
//		String a1=null;
//		System.out.println(update("zhang","ab1233","","","","","","",""));
//	}
}

	
	


