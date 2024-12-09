package db;

public class Bookmark {
	private int id;
	private String bookMarkame;
	private String wifiName;	
	private String regDate;
	private String wifiManagerNo;
	private int bookmarkGroupId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getWifiManagerNo() {
		return wifiManagerNo;
	}
	public void setWifiManagerNo(String wifiManagerNo) {
		this.wifiManagerNo = wifiManagerNo;
	}
	public int getBookmarkGroupId() {
		return bookmarkGroupId;
	}
	public void setBookmarkGroupId(int bookmarkGroupId) {
		this.bookmarkGroupId = bookmarkGroupId;
	}
	public String getBookMarkame() {
		return bookMarkame;
	}
	public void setBookMarkame(String bookMarkame) {
		this.bookMarkame = bookMarkame;
	}
	public String getWifiName() {
		return wifiName;
	}
	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;
	}
	
	
	


}