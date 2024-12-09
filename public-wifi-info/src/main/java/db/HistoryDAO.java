package db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HistoryDAO {
	final static String DBURL = "jdbc:sqlite:public_wifi.db";

	public String getNowDate() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		return now.format(formatter);
	}

	public void createLocalHistory(Double x, Double y) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);

			String selectSQL = "insert into history (x, y, search_date) values (?, ?, ?)";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setDouble(1, x);
			pstmt.setDouble(2, y);
			pstmt.setString(3, getNowDate());

			int res = pstmt.executeUpdate();

			if (res > 0) {
				System.out.println("위치 히스토리 추가 성공");
			} else {
				System.out.println("위치 히스토리 추가 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public List<History> getLocalHistoryList() {
		List<History> logcalHistorys = new ArrayList<History>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);

			// 데이터 조회
			String selectSQL = "SELECT * FROM history order by id desc;";
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				History logcalHistory = new History();
				logcalHistory.setId(rs.getInt("id"));
				logcalHistory.setX(rs.getDouble("x"));
				logcalHistory.setY(rs.getDouble("y"));
				logcalHistory.setSearchDate(rs.getString("search_date"));

				logcalHistorys.add(logcalHistory);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return logcalHistorys;
	}

	public History getLocalHistory(int id) {
		History lg = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);

			// 데이터 조회
			String selectSQL = "SELECT * FROM history where id = ?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lg = new History();
				lg.setId(rs.getInt("id"));
				lg.setX(rs.getDouble("x"));
				lg.setY(rs.getDouble("y"));
				lg.setSearchDate(rs.getString("search_date"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lg;
	}

	public int deleteLocalHistory(int id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res = -1;

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);

			// 데이터 조회
			String selectSQL = "delete from history where id = ?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, id);
			res = pstmt.executeUpdate();

			if (res > 0) {
				System.out.println("위치 히스토리 삭제 성공");
			} else {
				System.out.println("위치 히스토리 삭제 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return res;
	}

}
