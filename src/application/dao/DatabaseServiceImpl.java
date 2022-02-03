package application.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Member;

public class DatabaseServiceImpl implements DatabaseService {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	String url, user, pass;

	public DatabaseServiceImpl() {
		url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		user = "system";
		pass = "oracle";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("등록 성공");
		} catch (Exception e) {
			System.out.println("오라클 드라이버 등록 실패");
			e.printStackTrace();
			// TODO: handle exception
		}

		try {
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("DB 연결 성공");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public boolean insert(Member m) {

		return false;
	}

	public boolean dupChecker(String item, String value) {
		String sql = "select * from member where " + item + " = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println("checker error");
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}
}
