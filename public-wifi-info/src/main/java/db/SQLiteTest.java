package db;

import java.sql.*;
import java.util.Iterator;
import java.util.List;

public class SQLiteTest {
	public static void main(String[] args) {
		WifiDAO ws = new WifiDAO();
//		ws.createTable();
		
		// 테이블 생성
		// Open API 와이파이 정보 가져오기 버튼 클릭 시
//		ws.loadWifi();

		// wifi info 전체 조회
//		ws.getWifilist();
		
		// wifi info 상세 조회
//		getWifiInfo("BS100208");
		
		
		// 가까운 와이파이 20개 
//		getWifiInfoList("37.532544", "126.865596");
		
		/////////////////////////////
		
		// 북마크 그룹 추가
//		BookmarkGroupDAO bgDAO = new BookmarkGroupDAO();
//		bgDAO.createBookmarkGroup("즐겨찾기1", 1);
		
		// 북마크 그룹 목록 조회
//		getBookmarkGroups();
		
		
		// 북마크 그룹 상세 조회
//		getBookmarkGroup(1);
		
		// 북마크 그룹 수정 
//		getBookmarkGroup(1);
//		updateBookmarkGroup(1, "북마크 그룹 수정", 11);
//		getBookmarkGroup(1);
		
		// 북마크 그룹 삭제
//		deleteBookmarkGroup(1);
		
		///////
		// 북마크 추가
//		BookmarkDAO bookmarkDao = new BookmarkDAO();
//		bookmarkDao.createBookmark("BS100208", 8);
		
		// 북마크 목록 조회
//		getBookmarkList();
		
		// 북마크 조회
//		getBookmark(1);
		
		// 위치 히스토리 추가
//		createLocalHistory(37.53258, 123.12);
		
		// 위치 히스토리 목록 조회
//		getLocalHistoryList();
		
	
	}
	public static void getWifiInfoList(Double lat, Double lnt) {

		WifiDAO ws = new WifiDAO();
		List<WifiInfo> wifiList = ws.getWifiNearBy(lat, lnt);
		for(int i=0; i < wifiList.size(); i++) {
			WifiInfo wifi = wifiList.get(i);
			
	    	System.out.println("distance : " + wifi.getDistance());
	        System.out.println("WiFi Manager No: " + wifi.getManagerNo());
	        System.out.println("Installation Floor: " + wifi.getInstallationFloor());
	        System.out.println("Work Datetime: " + wifi.getWorkDatetime());
	        System.out.println("Cmcwr: " + wifi.getCmcwr());
	        System.out.println("Remarks: " + wifi.getRemarks3());
	        System.out.println("Installation By: " + wifi.getInstallationBy());
	        System.out.println("In/Out Door: " + wifi.getInOutDoor());
	        System.out.println("Main WiFi Name: " + wifi.getMainName());
	        System.out.println("Latitude: " + wifi.getLatitude());
	        System.out.println("Longitude: " + wifi.getLongitude());
	        System.out.println("District: " + wifi.getDistrict());
	        System.out.println("Installation Type: " + wifi.getInstallationType());
	        System.out.println("Service Type: " + wifi.getServiceType());
	        System.out.println("Address 1: " + wifi.getAddress1());
	        System.out.println("Address 2: " + wifi.getAddress2());
	        System.out.println("Construction Year: " + wifi.getConstructionYear());
			
		}
	}
	
	public static void getWifiInfo(String managerNo) {
		WifiDAO ws = new WifiDAO();
		WifiInfo wifi = ws.getWifiInfo(managerNo);
		
		if (wifi == null) return;
		
        System.out.println("WiFi Manager No: " + wifi.getManagerNo());
        System.out.println("Installation Floor: " + wifi.getInstallationFloor());
        System.out.println("Work Datetime: " + wifi.getWorkDatetime());
        System.out.println("Cmcwr: " + wifi.getCmcwr());
        System.out.println("Remarks: " + wifi.getRemarks3());
        System.out.println("Installation By: " + wifi.getInstallationBy());
        System.out.println("In/Out Door: " + wifi.getInOutDoor());
        System.out.println("Main WiFi Name: " + wifi.getMainName());
        System.out.println("Latitude: " + wifi.getLatitude());
        System.out.println("Longitude: " + wifi.getLongitude());
        System.out.println("District: " + wifi.getDistrict());
        System.out.println("Installation Type: " + wifi.getInstallationType());
        System.out.println("Service Type: " + wifi.getServiceType());
        System.out.println("Address 1: " + wifi.getAddress1());
        System.out.println("Address 2: " + wifi.getAddress2());
        System.out.println("Construction Year: " + wifi.getConstructionYear());
	}
	
	public static void getBookmarkGroups() {
		BookmarkGroupDAO ws = new BookmarkGroupDAO();
		
		List<BookmarkGroup> bookmarkGroups = ws.getBookmarkGroupList();
		for (int i = 0 ; i < bookmarkGroups.size(); i++) {
			BookmarkGroup bg = bookmarkGroups.get(i);
			System.out.println("id : " + bg.getId());
			System.out.println("name : " + bg.getName());
			System.out.println("order : " + bg.getOrder());
			System.out.println("reg date ; " + bg.getRegDate());
			System.out.println("last update ; " + bg.getLastUpdated());
			System.out.println("-----------");
		}
	}
	

	public static void getBookmarkGroup(int id) {

		BookmarkGroupDAO ws = new BookmarkGroupDAO();
		
		BookmarkGroup bg = ws.getBookmarkGroup(id);
		if (bg == null) return;
		System.out.println("id : " + bg.getId());
		System.out.println("name : " + bg.getName());
		System.out.println("order : " + bg.getOrder());
		System.out.println("reg date ; " + bg.getRegDate());
		System.out.println("-----------");
	}
	
	public static void updateBookmarkGroup(int id, String name, int order) {
		BookmarkGroupDAO ws = new BookmarkGroupDAO();
		ws.updateBookmarkGroup(id, name, order);

	}
	
	public static void deleteBookmarkGroup(int id) {
		BookmarkGroupDAO bg = new BookmarkGroupDAO();
		bg.deleteBookmarkGroup(id);
	}
	
	public static void getBookmarkList() {
		BookmarkDAO bookmarkDao = new BookmarkDAO();
		List<Bookmark> bookmarkList = bookmarkDao.getBookmarkList();
		
		for (int i = 0; i < bookmarkList.size(); i++) {
			Bookmark bookmark = bookmarkList.get(i);
			System.out.println("id : " + bookmark.getId());
			System.out.println("bookmark name : " + bookmark.getBookMarkame());
			System.out.println("wifi name : " + bookmark.getWifiName());
			System.out.println("ref_date : " + bookmark.getBookmarkGroupId() );
		}
		
	}
	
	public static void getBookmark(int id) {
		BookmarkDAO bookmarkDao = new BookmarkDAO();
		Bookmark bookmark = bookmarkDao.getBookmark(id);
		
		System.out.println("id : " + bookmark.getId());
		System.out.println("bookmark name : " + bookmark.getBookMarkame());
		System.out.println("wifi name : " + bookmark.getWifiName());
		System.out.println("ref_date : " + bookmark.getBookmarkGroupId() );
	}
	
	public static void createLocalHistory(Double x, Double y) {
		HistoryDAO historyDao = new HistoryDAO();
		historyDao.createLocalHistory(x, y);
	}
	
	public static void getLocalHistoryList() {
		HistoryDAO historyDao = new HistoryDAO();
		List<History> list = historyDao.getLocalHistoryList();
		
		for (int i = 0; i< list.size(); i++) {
			History history = list.get(i);
			System.out.println("id : " + history.getId());
			System.out.println("x : " +history.getX());
			System.out.println("y : " + history.getY());
			System.out.println("search date : " + history.getSearchDate());
		}
	}
	
}
