package ch10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbook3db";
	
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"jwbook3","3456");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void addGuest(Guest g)throws Exception{
		Connection conn = open();
		
		String sql = "insert into guest(writer,email,date,title,password,content) values(?,?,CURRENT_TIMESTAMP(0),?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setString(1, g.getWriter());
			pstmt.setString(2, g.getEmail());
			pstmt.setString(3, g.getTitle());
			pstmt.setString(4, g.getPassword());
			pstmt.setString(5, g.getContent());
			
			pstmt.executeUpdate();
		}
	}
	
	public List<Guest> getAll() throws Exception{
		System.out.println("getAll진입");
		Connection conn = open();
		List<Guest> guestList = new ArrayList<>();
		String sql ="select id, writer,email,PARSEDATETIME(date, 'yyyy-MM-dd HH:mm:ss') as cdate, title, password, content from guest";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs){
			while(rs.next()) {
				Guest g = new Guest();
				g.setId(rs.getInt("id"));
				g.setWriter(rs.getString("writer"));
				g.setEmail(rs.getString("email"));
				g.setDate(rs.getString("cdate"));
				g.setTitle(rs.getString("title"));
				g.setPassword(rs.getString("password"));
				g.setContent(rs.getString("content"));
				
				guestList.add(g);
			}
			
			
			return guestList;
		}
	}
	
	public Guest getGuest(int id)throws SQLException{
		System.out.println("dao getguest진입");
		Connection conn = open();
		Guest g = new Guest();
		String sql = "select id,writer,email,PARSEDATETIME(date,'yyyy-MM-dd HH:mm:ss') as cdate,title,password,content from guest where id =?";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		try(conn; pstmt; rs){
			g.setId(rs.getInt("id"));
			g.setWriter(rs.getString("writer"));
			g.setEmail(rs.getString("email"));
			g.setDate(rs.getString("cdate"));
			g.setTitle(rs.getString("title"));
			g.setPassword(rs.getString("password"));
			g.setContent(rs.getString("content"));
			
			pstmt.executeQuery();
			return g;
		}
	}
	
	public void delGuest(int id) throws SQLException{
		Connection conn =open();
		String sql ="delete from guest where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try(conn; pstmt){
			pstmt.setInt(1, id);
			if(pstmt.executeUpdate()==0) {
				throw new SQLException("DB에러");
			}
		}
	}
}
