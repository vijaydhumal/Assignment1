package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDAO;
import model.UserDTO;

@WebServlet(urlPatterns = {"/registeruser","/loginform"})
public class userController extends HttpServlet{
	static Connection con=null;
	static Statement stmt=null;
	static ResultSet rs=null;
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
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		switch(path) {
		case "/registeruser":
			           addUser(req,resp);
			           break;	 
			          
		case "/loginform":
	           validationUser(req, resp);
	           break;	            
		}
	}
	
	protected void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String fname=req.getParameter("fname");
		String lname=req.getParameter("lname");
		String dob1=req.getParameter("dob");
		String address=req.getParameter("address");
		String pno1=req.getParameter("pno");
		
		java.sql.Date dob=java.sql.Date.valueOf(dob1);
		long pno=Long.parseLong(pno1);
		
		UserDTO dto=new UserDTO();
		dto.setUserName(username);
		dto.setPassword(password);
        dto.setfName(fname);
        dto.setlName(lname);
        dto.setDob(dob);
        dto.setAddress(address);
        dto.setpNo(pno);
        
        UserDAO dao=new UserDAO();
        dao.addUser(dto);
        int value=dto.getValues();
        req.setAttribute("values", value);
        
		RequestDispatcher rd=req.getRequestDispatcher("insert.jsp");
		rd.include(req, resp);
	}
	
	protected void validationUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user=req.getParameter("username");
		String password=req.getParameter("password");
	    
		String query="select * from user_info";
		boolean isValidUser=false;
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()) {
				if(user.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
					String dob=rs.getString(5);
					String address=rs.getString(6);
					String pno=rs.getString(7);
				   
					req.setAttribute("user",user);
					req.setAttribute("dob", dob);
					req.setAttribute("address", address);
					req.setAttribute("pno", pno);
					
					isValidUser = true;
		            break; 
				}
			}
	         if (isValidUser) {
	                RequestDispatcher rd = req.getRequestDispatcher("welcome.jsp");
	                rd.forward(req, resp);
	         } else {
	                // Invalid username or password
	                RequestDispatcher rd = req.getRequestDispatcher("index.html");
	                rd.forward(req, resp);
	         }
	} catch (SQLException e) {
		e.printStackTrace();
	
	}
	}
}
