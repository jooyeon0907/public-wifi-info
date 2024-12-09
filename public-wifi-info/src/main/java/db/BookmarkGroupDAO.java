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


public class BookmarkGroupDAO {
	final static String DBURL = "jdbc:sqlite:public_wifi.db";
	
	public String getNowDate() {
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
       
        return now.format(formatter);
	}
	
	
	public int createBookmarkGroup(String name, int order) {
//		String name = "";
//		int order = 0;		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = -1;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
            String selectSQL = "insert into bookmark_group (name, order_num, reg_date) values (?, ?, ?)";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, name);
			pstmt.setInt(2, order);
			pstmt.setString(3, getNowDate());
			
			res = pstmt.executeUpdate();
			
			if (res > 0) {
				System.out.println("북마크 그룹 추가 성공");
			} else {
				System.out.println("북마크 그룹 추가 실패");
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
	
	
	public List<BookmarkGroup> getBookmarkGroupList() {
		List<BookmarkGroup> bookmarkGroups = new ArrayList<BookmarkGroup>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
            // 데이터 조회
            String selectSQL = "SELECT * FROM bookmark_group;";
			pstmt = conn.prepareStatement(selectSQL);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {            	
            	BookmarkGroup bookmarkGroup = new BookmarkGroup();
            	bookmarkGroup.setId(rs.getInt("id"));
            	bookmarkGroup.setName(rs.getString("name"));
            	bookmarkGroup.setOrder(rs.getInt("order_num"));
            	bookmarkGroup.setRegDate(rs.getString("reg_date"));
            	bookmarkGroup.setLastUpdated(rs.getString("last_updated"));
            
            	bookmarkGroups.add(bookmarkGroup);
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
		
		return bookmarkGroups;
	}
	
	
	public BookmarkGroup getBookmarkGroup(int id) {
		BookmarkGroup bg = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
            // 데이터 조회
            String selectSQL = "SELECT * FROM bookmark_group where id = ?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {            	
            	bg = new BookmarkGroup();
            	bg.setId(rs.getInt("id"));
            	bg.setName(rs.getString("name"));
            	bg.setOrder(rs.getInt("order_num"));
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
		
		return bg;
	}
	
	
	public int updateBookmarkGroup(int id, String name, int order) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int res = -1;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
            // 데이터 조회
            String selectSQL = "update bookmark_group set name =?, order_num =?, last_updated = ? where id = ?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, name);
			pstmt.setInt(2, order);
			pstmt.setString(3, getNowDate());
			pstmt.setInt(4, id);
            
            res = pstmt.executeUpdate();
            if (res > 0) {
            	System.out.println("북마크 그룹 수정 성공");
            } else {
            	System.out.println("북마크 그룹 수정 실패");
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



	public int deleteBookmarkGroup(int id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res = -1;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
            // 데이터 조회
            String selectSQL = "delete from bookmark_group where id = ?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, id);
            res = pstmt.executeUpdate();
            
            if (res > 0) {
            	System.out.println("북마크 그룹 삭제 성공");
            } else {
            	System.out.println("북마크 그룹 삭제 실패");
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
