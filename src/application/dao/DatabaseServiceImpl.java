package application.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.LogInfo;
import application.Member;
import application.service.CommonService;
import application.service.CommonServiceImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatabaseServiceImpl implements DatabaseService {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	CommonService cs = new CommonServiceImpl();
	String url, user, pass;

	public DatabaseServiceImpl() {
		url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		user = "system";
		pass = "oracle";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean insert(Member m) {
		String sql = "insert into member values (?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPw());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getBirth());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getSalt());
			pstmt.setString(7, m.getId());
			int result = pstmt.executeUpdate();
			if (result >= 1) {
				Alert alr = new Alert(AlertType.INFORMATION);
				alr.setTitle("회원가입 완료");
				alr.showAndWait();
				pstmt.close();
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println("DB 오류. 가입 실패");
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}

	public boolean delete(String id, String pw) {
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
		}
		return false;
	}

	public boolean login(String id, String pw, LogInfo log) {

		return false;
	}

	public String getSalt(String id) {
		String sql = "select salt from member where id=?";
		String result = "";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println(rs.getString(1));
			result = rs.getString(1);
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return "실패";
		}
	}
}
