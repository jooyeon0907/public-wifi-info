package db;

import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class WifiDAO {
	final static String DBURL = "jdbc:sqlite:public_wifi.db";

	
	public void testQuery(String query) {

		Connection conn = null;
		Statement stmt = null;
		
		try {

			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			stmt = conn.createStatement();
			stmt.execute(query);
			System.out.println("query 처리 완료 ");
			
			
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	    		if (stmt != null && !stmt.isClosed()) stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	        try {
	            if (conn != null && !conn.isClosed()) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
	}
	
	public void createTable() {
		System.out.println("DBURL : " + DBURL);

		Connection conn = null;
		Statement stmt = null;
		
		try {

			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			stmt = conn.createStatement();
			

			String createTableSQL = "CREATE TABLE IF NOT EXISTS wifi_info ("
					+ "	managerNo         VARCHAR(50)  PRIMARY KEY,"
					+ "	latitude          FLOAT        NULL,"
					+ "	longitude         FLOAT        NULL,"
					+ "	remarks3          VARCHAR(20)  NULL,"
					+ "	installationFloor VARCHAR(10)  NULL,"
					+ "	workDatetime      DATETIME     NULL,"
					+ "	cmcwr             VARCHAR(10)  NULL,"
					+ "	installationBy    VARCHAR(10)  NULL,"
					+ "	inOutDoor         VARCHAR(10)  NULL,"
					+ "	mainName          VARCHAR(50)  NULL,"
					+ "	district          VARCHAR(20)  NULL,"
					+ "	installationType  VARCHAR(20)  NULL,"
					+ "	serviceType       VARCHAR(10)  NULL,"
					+ "	address1          VARCHAR(100) NULL,"
					+ "	address2          VARCHAR(100) NULL,"
					+ "	constructionYear  INT          NULL"
					+ ")";
			stmt.execute(createTableSQL);
			System.out.println("wifi_info 테이블 생성 완료");
			
			
			createTableSQL = "CREATE TABLE IF NOT EXISTS history ("
					+ "	id       INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	x        FLOAT NOT NULL,"
					+ "	y        FLOAT NOT NULL,"
					+ "	search_date DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP "
					+ ")";
			stmt.execute(createTableSQL);
			System.out.println("history 테이블 생성 완료");
			

			createTableSQL = "CREATE TABLE IF NOT EXISTS bookmark_group ("
					+ "	id        INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	name      VARCHAR(50) NOT NULL,"
					+ "	order_num INT         NOT NULL,"
					+ "	reg_date  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,"
					+ "	last_updated  DATETIME"
					+ ")";
			stmt.execute(createTableSQL);
			System.out.println("bookmark_group 테이블 생성 완료");
			

			createTableSQL = "CREATE TABLE IF NOT EXISTS bookmark ("
					+ "	id                INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	reg_date          DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,"
					+ "	wifi_managerNo    VARCHAR(50)         NULL,"
					+ "	bookmark_group_id INT         NULL,"
					+ "	FOREIGN KEY (wifi_managerNo) REFERENCES  wifi_info (managerNo),"
					+ "	FOREIGN KEY (bookmark_group_id) REFERENCES  bookmark_group (id)"
					+ ")";
			stmt.execute(createTableSQL);
			System.out.println("bookmark 테이블 생성 완료");
			
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	    		if (stmt != null && !stmt.isClosed()) stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	        try {
	            if (conn != null && !conn.isClosed()) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
	}
	
	
//	public List<WifiInfo> list() {
	public void getWifilist() {
		List<WifiInfo> WifiList = new ArrayList<WifiInfo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
            // 데이터 조회
            String selectSQL = "SELECT * FROM wifi_info;";
			pstmt = conn.prepareStatement(selectSQL);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	float latitude = rs.getFloat("latitude");
            	float longitude = rs.getFloat("longitude");
            	String remarks3 = rs.getString("remarks3");
            	String installationFloor = rs.getString("installationFloor");
            	String workDatetime = rs.getString("workDatetime");
            	String managerNo = rs.getString("managerNo");
            	String cmcwr = rs.getString("cmcwr");
            	String installationBy = rs.getString("installationBy");
            	String inOutDoor = rs.getString("inOutDoor");
            	String mainName = rs.getString("mainName");
            	String district = rs.getString("district");
            	String installationType = rs.getString("installationType");
            	String serviceType = rs.getString("serviceType");
            	String address1  = rs.getString("address1");
            	String address2  = rs.getString("address2");
            	int constructionYear = rs.getInt("constructionYear");
            	
                System.out.println("WiFi Manager No: " + managerNo);
                System.out.println("Installation Floor: " + installationFloor);
                System.out.println("Work Datetime: " + workDatetime);
                System.out.println("Cmcwr: " + cmcwr);
                System.out.println("Remarks: " + remarks3);
                System.out.println("Installation By: " + installationBy);
                System.out.println("In/Out Door: " + inOutDoor);
                System.out.println("Main WiFi Name: " + mainName);
                System.out.println("Latitude: " + latitude);
                System.out.println("Longitude: " + longitude);
                System.out.println("District: " + district);
                System.out.println("Installation Type: " + installationType);
                System.out.println("Service Type: " + serviceType);
                System.out.println("Address 1: " + address1);
                System.out.println("Address 2: " + address2);
                System.out.println("Construction Year: " + constructionYear);
                System.out.println("-------------------------");
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
		
//		return WifiList;
	}
	
	public WifiInfo getWifiInfo(String managerNo) {
		WifiInfo wifiInfo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
	        // 데이터 조회
	        String selectSQL = "SELECT * FROM wifi_info where managerNo = ?";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setString(1, managerNo);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	wifiInfo = new WifiInfo();
	        	wifiInfo.setLatitude(rs.getFloat("latitude"));
	        	wifiInfo.setLongitude(rs.getFloat("latitude"));
	        	wifiInfo.setRemarks3(rs.getString("remarks3"));
	        	wifiInfo.setInstallationFloor(rs.getString("installationFloor"));
	        	wifiInfo.setWorkDatetime(rs.getString("workDatetime"));
	        	wifiInfo.setManagerNo( rs.getString("managerNo"));
	        	wifiInfo.setCmcwr(rs.getString("cmcwr"));
	        	wifiInfo.setInstallationBy(rs.getString("installationBy"));
	        	wifiInfo.setInOutDoor(rs.getString("inOutDoor"));
	        	wifiInfo.setMainName(rs.getString("mainName"));
	        	wifiInfo.setDistrict(rs.getString("district"));
	        	wifiInfo.setInstallationType(rs.getString("installationType"));
	        	wifiInfo.setServiceType(rs.getString("serviceType"));
	        	wifiInfo.setAddress1(rs.getString("address1"));
	        	wifiInfo.setAddress2(rs.getString("address2"));
	        	wifiInfo.setConstructionYear(rs.getInt("constructionYear"));
	        	
	        }
			
		} catch (Exception e) {
	        e.printStackTrace();
	        return null;
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
		
		return wifiInfo;
}
	

	public int insertWifi(long startIdx, long endIdx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
	        ApiExplorer wifiInfo = new ApiExplorer();
	        JSONObject result = null;
	        String jsonStr = wifiInfo.loadWifi(startIdx, endIdx);

            result = (JSONObject) new JSONParser().parse(jsonStr);

            JSONObject tbPublicWifiInfo = (JSONObject) result.get("TbPublicWifiInfo");

            JSONArray rowArray = (JSONArray) tbPublicWifiInfo.get("row");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            for (int i = 0; i < rowArray.size(); i ++) {
                JSONObject row = (JSONObject) rowArray.get(i);
               
    			WifiInfo wifi = new WifiInfo();
    			String insertSQL = "INSERT INTO wifi_info (latitude, longitude, remarks3, installationFloor, workDateTime, managerNo, cmcwr, installationBy,"
    					+ "                       inOutDoor, mainName, district, installationType, serviceType, address1, address2, constructionYear)"
    					+ "SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
    					+ "WHERE NOT EXISTS ("
    					+ "    SELECT 1 FROM wifi_info WHERE managerNo = ?"
    					+ ");";
                pstmt = conn.prepareStatement(insertSQL);
                pstmt.setFloat(1, Float.valueOf((String) row.get("LAT")));
                pstmt.setFloat(2, Float.valueOf((String) row.get("LNT")));
                pstmt.setString(3, (String) row.get("X_SWIFI_REMARS3"));
                pstmt.setString(4, (String) row.get("X_SWIFI_INSTL_FLOOR"));
                pstmt.setString(5, (String) row.get("WORK_DTTM"));
                pstmt.setString(6, (String) row.get("X_SWIFI_MGR_NO"));
                pstmt.setString(7, (String) row.get("X_SWIFI_CMCWR"));
                pstmt.setString(8, (String) row.get("X_SWIFI_INSTL_MBY"));
                pstmt.setString(9, (String) row.get("X_SWIFI_INOUT_DOOR"));
                pstmt.setString(10, (String) row.get("X_SWIFI_MAIN_NM"));
                pstmt.setString(11, (String) row.get("X_SWIFI_WRDOFC"));
                pstmt.setString(12, (String) row.get("X_SWIFI_INSTL_TY"));
                pstmt.setString(13, (String) row.get("X_SWIFI_SVC_SE"));
                pstmt.setString(14, (String) row.get("X_SWIFI_ADRES2"));
                pstmt.setString(15, (String) row.get("X_SWIFI_ADRES1"));
                pstmt.setInt(16, Integer.parseInt((String) row.get("X_SWIFI_CNSTC_YEAR")));
                pstmt.setString(17, (String) row.get("X_SWIFI_MGR_NO"));
                
                int executeRes = pstmt.executeUpdate();
                if (executeRes > 0) {
                    cnt++;
//                    System.out.println(i + ": WIFI 정보 업로드 성공");
                } else {
//                    System.out.println(i + ": WIFI 정보 업로드 실패");
                }
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
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

		return cnt;
	}
	
	
	// Open API 와이파이 정보 가져오기 버튼 클릭 시
	public int loadWifi() {
		int cnt = 0;
		try {
	        ApiExplorer wifiInfo = new ApiExplorer();
	        JSONObject result = null;
	        String jsonStr = wifiInfo.loadWifi(1, 1);

            result = (JSONObject) new JSONParser().parse(jsonStr);

            JSONObject tbPublicWifiInfo = (JSONObject) result.get("TbPublicWifiInfo");
            
            long listTotalCount = (long) tbPublicWifiInfo.get("list_total_count");
//            long listTotalCount = 10;

            for (long startIndex = 1; startIndex <= listTotalCount; startIndex += 1000) {
            	long endIndex = Math.min(startIndex + 999, listTotalCount);
            	cnt += insertWifi(startIndex, endIndex);
            	Thread.sleep(500);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
		
		return cnt;
	}
	
	
	public List<WifiInfo> getWifiNearBy(Double lat, Double lnt) { // lat: 위도, lnt: 경도
		List<WifiInfo> WifiList = new ArrayList<WifiInfo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DBURL);
			
            String selectSQL = "SELECT "
            		+ "    6371 * ("
            		+ "        2 * atan2("
            		+ "            sqrt("
            		+ "                sin((radians(latitude) - radians(?)) / 2) * sin((radians(latitude) - radians(?)) / 2) +"
            		+ "                cos(radians(?)) * cos(radians(latitude)) *"
            		+ "                sin((radians(longitude) - radians(?)) / 2) * sin((radians(longitude) - radians(?)) / 2)"
            		+ "            ),"
            		+ "            sqrt("
            		+ "                1 - ("
            		+ "                    sin((radians(latitude) - radians(?)) / 2) * sin((radians(latitude) - radians(?)) / 2) +"
            		+ "                    cos(radians(?)) * cos(radians(latitude)) *"
            		+ "                    sin((radians(longitude) - radians(?)) / 2) * sin((radians(longitude) - radians(?)) / 2)"
            		+ "                )"
            		+ "            )"
            		+ "        )"
            		+ "    ) AS distance, "
            		+ "    latitude, longitude, remarks3, installationFloor, workDateTime, managerNo, cmcwr, installationBy,"
            		+ "    inOutDoor, mainName, district, installationType, serviceType, address1, address2, constructionYear "
            		+ "FROM wifi_info "
            		+ "ORDER BY distance "
            		+ "LIMIT 20;";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setDouble(1, lat);
			pstmt.setDouble(2, lat);
			pstmt.setDouble(3, lat);
			pstmt.setDouble(4, lnt);
			pstmt.setDouble(5, lnt);
			pstmt.setDouble(6, lat);
			pstmt.setDouble(7, lat);
			pstmt.setDouble(8, lat);
			pstmt.setDouble(9, lnt);
			pstmt.setDouble(10, lnt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	WifiInfo wifiInfo = new WifiInfo();
	        	wifiInfo.setDistance(rs.getFloat("distance"));
	        	wifiInfo.setLatitude(rs.getFloat("latitude"));
	        	wifiInfo.setLongitude(rs.getFloat("longitude"));
	        	wifiInfo.setRemarks3(rs.getString("remarks3"));
	        	wifiInfo.setInstallationFloor(rs.getString("installationFloor"));
	        	wifiInfo.setWorkDatetime(rs.getString("workDatetime"));
	        	wifiInfo.setManagerNo( rs.getString("managerNo"));
	        	wifiInfo.setCmcwr(rs.getString("cmcwr"));
	        	wifiInfo.setInstallationBy(rs.getString("installationBy"));
	        	wifiInfo.setInOutDoor(rs.getString("inOutDoor"));
	        	wifiInfo.setMainName(rs.getString("mainName"));
	        	wifiInfo.setDistrict(rs.getString("district"));
	        	wifiInfo.setInstallationType(rs.getString("installationType"));
	        	wifiInfo.setServiceType(rs.getString("serviceType"));
	        	wifiInfo.setAddress1(rs.getString("address1"));
	        	wifiInfo.setAddress2(rs.getString("address2"));
	        	wifiInfo.setConstructionYear(rs.getInt("constructionYear"));
	        	
	        	WifiList.add(wifiInfo);
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
		
		return WifiList;
	}

	

}
