package db;

import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class BookmarkDAO {
	final static String DBURL = "jdbc:sqlite:public_wifi.db";
	
	
	public String getNowDate() {
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
       
        return now.format(formatter);
	}
	
	
	public int createBookmark(String managerNo, int bookmarkGroupId) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = -1;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
            String selectSQL = "insert into bookmark (wifi_managerNo, bookmark_group_id, reg_date) values (?, ?, ?)";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, managerNo);
			pstmt.setInt(2, bookmarkGroupId);
			pstmt.setString(3, getNowDate());
			
			res = pstmt.executeUpdate();
			
			if (res > 0) {
				System.out.println("북마크 추가 성공");
			} else {
				System.out.println("북마크 추가 실패");
			}
			
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {	    	
	    	try {
	    		if (pstmt != null && !pstmt.isClosed()) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	        try {
	            if (conn != null && !conn.isClosed()) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
		return res;
		
	}
	
	
	public List<Bookmark> getBookmarkList() {
		List<Bookmark> bookmarks = new ArrayList<Bookmark>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
            // 데이터 조회
            String selectSQL = "select b.id, bg.name as bookmark_name, w.mainName as wifi_name, w.managerNo as wifi_managerNo, b.reg_date "
            		+ "from bookmark as b "
            		+ "join wifi_info as w on b.wifi_managerNo = w.managerNo "
            		+ "join bookmark_group as bg on b.bookmark_group_id = bg.id";
			pstmt = conn.prepareStatement(selectSQL);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {            	
            	Bookmark bookmark = new Bookmark();
            	bookmark.setId(rs.getInt("id"));
            	bookmark.setBookMarkame(rs.getString("bookmark_name"));
            	bookmark.setWifiName(rs.getString("wifi_name"));
            	bookmark.setWifiManagerNo(rs.getString("wifi_managerNo"));
            	bookmark.setRegDate(rs.getString("reg_date"));
            
            	bookmarks.add(bookmark);
            }
			
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	    		if (rs != null && !rs.isClosed()) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	try {
	    		if (pstmt != null && !pstmt.isClosed()) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	        try {
	            if (conn != null && !conn.isClosed()) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
		return bookmarks;
	}
	
	public Bookmark getBookmark(int id) {
		Bookmark bookmark = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
            // 데이터 조회
            String selectSQL = "select b.id, bg.name as bookmark_name, w.mainName as wifi_name, w.managerNo as wifi_managerNo, b.reg_date "
            		+ "from bookmark as b "
            		+ "join wifi_info as w on b.wifi_managerNo = w.managerNo "
            		+ "join bookmark_group as bg on b.bookmark_group_id = bg.id "
            		+ "where b.id = ?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {            	
            	bookmark = new Bookmark();
            	bookmark.setId(rs.getInt("id"));
            	bookmark.setBookMarkame(rs.getString("bookmark_name"));
            	bookmark.setWifiName(rs.getString("wifi_name"));
            	bookmark.setWifiManagerNo(rs.getString("wifi_managerNo"));
            	bookmark.setRegDate(rs.getString("reg_date"));
            }
			
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	    		if (rs != null && !rs.isClosed()) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	try {
	    		if (pstmt != null && !pstmt.isClosed()) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	        try {
	            if (conn != null && !conn.isClosed()) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
		return bookmark;
	}

	public int deleteBookmark(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res = -1;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
            // 데이터 조회
            String selectSQL = "delete from bookmark where id = ?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, id);
            res = pstmt.executeUpdate();
            
            if (res > 0) {
            	System.out.println("북마크 삭제 성공");
            } else {
            	System.out.println("북마크 삭제 실패");
            }
            
			
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	    		if (rs != null && !rs.isClosed()) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	try {
	    		if (pstmt != null && !pstmt.isClosed()) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	        try {
	            if (conn != null && !conn.isClosed()) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
		return res;
		
	}
	
	

}
