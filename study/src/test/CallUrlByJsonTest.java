package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CallUrlByJsonTest {
	public static void main(String[] args) {
       String returnJsonData = printJsonByUrl("http://dl.nanet.go.kr/SearchList.do?callback=&searchClass=S&queryText=&searchFlag=S&fieldTextD=&operator=&univSojang=N&dbgubun=ALL&sysid=&hanjaFlag=&detailviewgn=&singleviewvu=&detailYn=N&queryTextD=(%20((%20%2F0GMQX%2F%EC%95%88%EB%85%95%20)%20OR%20(%20%2F2GMQ%2F%EC%95%88%EB%85%95%20))%3Ati%3ADRETITLE%3A_ti%3Aau1%3Aau2%3Aau1_100a%3Aau2_110a%3App%3App_260b%3App_260a%3Asu%3Acn%3Aitemno%3Aisbn%3Aissn%3Asu_653a%20)&showQuery=(`%EC%95%88%EB%85%95`)&qry_term=%EC%95%88%EB%85%95&samenessAuthor=&zone=ALL_NI_TOC&query=%EC%95%88%EB%85%95");
       System.out.println(returnJsonData);
       convertJSON(returnJsonData);
    }
	
	public static JSONParser convertJSON(String json){
		
		JSONParser jsonParser = new JSONParser();
		
		try {
            //JSON데이터를 넣어 JSON Object 로 만들어 준다.
            JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
            //books의 배열을 추출
            JSONArray bookInfoArray = (JSONArray) jsonObject.get("searchList");
 
            for(int i=0; i<bookInfoArray.size(); i++){
                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                JSONObject bookObject = (JSONObject) bookInfoArray.get(i);
                //JSON name으로 추출
                System.out.println("bookInfo: name==>"+bookObject.get("showMode"));
                System.out.println("bookInfo: writer==>"+bookObject.get("payType"));
            }
 
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return jsonParser;
	}
	
	/**
	 * url을 호출하여 json으로 받아 오는 부분.
	 */
	public static String printJsonByUrl(String str_url) {
		StringBuffer sbuf = new StringBuffer();
		try {
			// URL 객체 생성
			URL url = new URL(str_url);
			// URLConnection 생성
			URLConnection urlConn = url.openConnection();
			InputStream is = urlConn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String str;
			while ((str = br.readLine()) != null) {
				sbuf.append(str + "\r\n");
			}
			// 콘솔에 출력하기
			System.out.println(sbuf.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sbuf.toString();
	}
	
}
