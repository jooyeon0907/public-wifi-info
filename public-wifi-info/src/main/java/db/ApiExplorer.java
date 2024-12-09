package db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ApiExplorer {
    public static String loadWifi(long startIdx, long endIdx) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode("79685769756a6f6f35375379416e71", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startIdx), "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(endIdx), "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
            
        BufferedReader rd;
        

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) { // 연결 성공
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String str = sb.toString();
//        System.out.println("> " + str);
        return str;
    }

    public static void main(String[] args) throws IOException {
        ApiExplorer.loadWifi(24501, 24501);
    }
}
