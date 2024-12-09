package db;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class WiFiApiTest {
	static int wifiCnt = 0;
	public static boolean insertWifi(long startIdx, long endIdx) {
        try {
	        ApiExplorer wifiInfo = new ApiExplorer();
	        JSONObject result = null;
	        String jsonStr = "";
	        
	        jsonStr = wifiInfo.loadWifi(startIdx, endIdx);
	
	        result = (JSONObject) new JSONParser().parse(jsonStr);
	
	        JSONObject tbPublicWifiInfo = (JSONObject) result.get("TbPublicWifiInfo");
	        JSONArray rowArray = (JSONArray) tbPublicWifiInfo.get("row");
	     
	        for (int i = 0; i < rowArray.size(); i ++) {
	            JSONObject row = (JSONObject) rowArray.get(i);
	
	            String remarks3  = (String) row.get("X_SWIFI_REMARS3");
	            String installationFloor  = (String) row.get("X_SWIFI_INSTL_FLOOR");
	            String workDatetime = (String) row.get("WORK_DTTM");
	            String managerNo  = (String) row.get("X_SWIFI_MGR_NO");
	            String cmcwr  = (String) row.get("X_SWIFI_CMCWR");
	            String installationBy  = (String) row.get("X_SWIFI_INSTL_MBY");
	            String inOutDoor = (String) row.get("X_SWIFI_INOUT_DOOR");
	            String mainName  = (String) row.get("X_SWIFI_MAIN_NM");
	            String latitude = (String) row.get("LAT");
	            String longitude= (String) row.get("LNT");
	            String district  = (String) row.get("X_SWIFI_WRDOFC");
	            String installationType  = (String) row.get("X_SWIFI_INSTL_TY");
	            String serviceType  = (String) row.get("X_SWIFI_SVC_SE");
	            String address1  = (String) row.get("X_SWIFI_ADRES2");
	            String address2  = (String) row.get("X_SWIFI_ADRES1");
	            String constructionYear  = (String) row.get("X_SWIFI_CNSTC_YEAR");
	
//	            System.out.println(i);
	//            System.out.println("WiFi Manager No: " + managerNo);
	//            System.out.println("Installation Floor: " + installationFloor);
	//            System.out.println("Work Datetime: " + workDatetime);
	//            System.out.println("Cmcwr: " + cmcwr);
	//            System.out.println("Remarks: " + remarks3);
	//            System.out.println("Installation By: " + installationBy);
	//            System.out.println("In/Out Door: " + inOutDoor);
	//            System.out.println("Main WiFi Name: " + mainName);
	//            System.out.println("Latitude: " + latitude);
	//            System.out.println("Longitude: " + longitude);
	//            System.out.println("District: " + district);
	//            System.out.println("Installation Type: " + installationType);
	//            System.out.println("Service Type: " + serviceType);
	//            System.out.println("Address 1: " + address1);
	//            System.out.println("Address 2: " + address2);
	//            System.out.println("Construction Year: " + constructionYear);
	//            System.out.println("-------------------------");
	            
	            wifiCnt++;
	        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return true;
	}
	
    public static void main(String[] args) {
        ApiExplorer wifiInfo = new ApiExplorer();
        JSONObject result = null;
        String jsonStr = "";
        try {
            jsonStr = wifiInfo.loadWifi(1, 1);

            result = (JSONObject) new JSONParser().parse(jsonStr);

            JSONObject tbPublicWifiInfo = (JSONObject) result.get("TbPublicWifiInfo");
           
            long listTotalCount = (long) tbPublicWifiInfo.get("list_total_count");
            
           
            for (long startIndex = 1; startIndex <= listTotalCount; startIndex += 1000) {
            	long endIndex = Math.min(startIndex + 999, listTotalCount);
            	insertWifi(startIndex, endIndex);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        System.out.println("wifiCnt : " +wifiCnt);
        
    }
}
