package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
	static Connection con;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1esa22","root","sql123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addUser(UserDTO dto) {
		PreparedStatement pstmt;
		String query="insert into user_info values(?,?,?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, dto.getUserName());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getfName());
			pstmt.setString(4, dto.getlName());
			pstmt.setDate(5, dto.getDob());
			pstmt.setString(6, dto.getAddress());
			pstmt.setLong(7, dto.getpNo());
			
			int count=pstmt.executeUpdate();
			dto.setValues(count);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
