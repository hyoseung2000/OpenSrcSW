package movieapi;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class movieApi {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		// TODO Auto-generated method stub
		String clientID="b5az0s4tDfn0VjdX5VXC";
	    String clientSecret="Rwj5MDygZX";
	    
        try {
        	System.out.print("검색어를 입력하세요: ");
        	String input=scan.next();
            String text = URLEncoder.encode(input, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/movie?query="+ text;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientID);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
            JSONArray infoArray = (JSONArray) jsonObject.get("items");
            System.out.println("\n");
            for(int i=0; i<infoArray.size(); i++){
            System.out.println("=item_"+i+"===========================================");
            JSONObject itemObject = (JSONObject) infoArray.get(i);
            System.out.println("title:\t"+itemObject.get("title"));
            System.out.println("subtitle:\t"+itemObject.get("subtitle"));
            System.out.println("director:\t"+itemObject.get("director"));
            System.out.println("actor:\t"+itemObject.get("actor"));
            System.out.println("userRating:\t"+itemObject.get("userRating")+"\n");
            scan.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
	}

}
