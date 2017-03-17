package spring.common.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


/**
 * 자주 사용될 만한 기능을 모아둔 유틸클래스
 * 
 * @파일명 StringUtil.java
 */
public class StringUtil {
		
	/**
	 * 파일의 확장자를 추출한다.
	 * <BR>
	 * 예) <BR>
	 * [입력] "<B>abcder.txt</B>" ----> [출력] "<B>.txt</B>" 
	 * 
	 * @param str
	 * @return
	 */
	public static String getFileExtension( String str ) {
		return ( str.lastIndexOf( "." ) > 0 ) ? str.substring( str.lastIndexOf( "." ) ) : str;
	}
	
    /**
     * 문자열의 널값 검사를 한다.
     * <BR>문자열이 null 또는 white space인 경우에는 "참"을 반환한다.
     * 
     * @param str
     * @return  boolean
     */
    public static boolean isNull( String str ) {
        return ( str == null || "null".equals( str ) || "".equals( str ) ); 
    }
    
    /**
     * 문자열의 널값 검사를 한다.
     * <BR>문자열이 null 또는 white space인 경우에는 "거짓"을 반환한다.
     * 
     * @param str
     * @return  boolean
     */
    public static boolean isNotNull( String str ) {
        return !( str == null || "".equals( str ) );
    }
    
    /**
     * 문자열이 null인 경우에는 whiteSpace를 반환한다.
     * 
     * @param string
     * @return
     */
    public static String nullToEmptyString( String string ) {
    	return isNull( string ) ? "" : string;
    }
    
    /**
     * 요청한 페이지의 URL을 반환한다.
     * 
     * @param req
     * @return
     */
    public static String getURL( HttpServletRequest req ) {
        return req.getRequestURL().toString();
    }
    
    /**
     * 목록의 제목이 max보다 클경우 max 크기만큼만 잘라서 반환한다.
     * 
     * @param s - String
     * @param max - int
     * @return
     */
    public synchronized static String formatTitle( String s, int max ) 
    {
        if( s.length() <= max) return s;

        String tmp = null;        
        byte bTmp[] = null;
        String rets = "" ;

        for(int i=0, k=0; i < s.length(); i++) {
            tmp = s.substring( i, i+1 );
            bTmp = tmp.getBytes();
            if( bTmp.length > 1 ) {
                rets += tmp;
                k +=2;
            }  else {
                rets += tmp;
                k ++;
            }

            if( max <= k ) break;
        }
        
        return rets+"...";
    }
    
    
    /**
     * 문자열을 변환한다.
     * 
     * @param str - String
     * @param pattern - String
     * @param replace - String
     * @return
     */ 
    public static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
    
        while ( (e = str.indexOf( pattern, s) ) >= 0 ) {
            result.append( str.substring( s, e ) );
            result.append( replace );
            s = e + pattern.length();
        }
        
        result.append( str.substring( s ) );        
        return result.toString();
    }    

    /**
     * 문자열 해당코드로 변환한다..
     * 
     * @param str - String
     * @param encode - String
     * @param charsetName - String
     * @return
     * 
     */
    public static String encodeText( String str, String encode, String charsetName )  {
    	String result = null;
    	
    	try {
			result = isNull( str ) ? null : new String( str.getBytes( encode ), charsetName );
    	} catch ( UnsupportedEncodingException e ) {
    		e.printStackTrace();
    	}
    	
    	return result;
    }
    
    
    /**
     * 문자열을 UTF-8에서 8859_1로 디코딩 한다.
     * 
     * @param str
     * @return
     * 
     */
    public static String decode( String str ) 
    {
        return encodeText( str, "UTF-8", "8859_1" ); 
    }

	/**
	 * Date 객체의 날짜를 pattern의 형태로 반환한다.
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
    public static String getPatternDate( Date date, String pattern ) {
    	return  new SimpleDateFormat( pattern ).format( date );
    }
    
    /**
     * 문자열을 8859_1에서 UTF-8로 인코딩 한다.
     * @param str
     * @return
     * 
     */        
    public static String encode( String str ) 
    {
        return encodeText( str, "8859_1", "UTF-8" );
    }  
    
    /**
     * 문자열 null check 
     * @param str
     * @return null이 아닐 경우 true / null일 경우 false
     * 
     */
	public static boolean stringCheck(String str) {
		
		if(str != null && !str.trim().equals("") && !str.trim().equals("null")) {
			return true;
		}
		else return false;
	}
	
	/**
     * max만큼 subfix 앞에 pattern 붙인다.
     * @param String seq
     * @param String pattern
     * @param int max
     * @return 문자열
     * 
     */
	public static String getAppendString(String suffix, String pattern , int max){
		String newStr = "";
		int roof = max - suffix.length();
		for(int i = 0 ; i < roof ; i++){
			newStr += pattern;
		}
		newStr = newStr+suffix;
		return newStr;
	}
	
	/**
     * 파일명현재시간으로 생성
     * @param 
     * @return 문자열
     * 
     */
	public static String createFileName() throws Exception{

		Date date = new Date();
		String nowTime = null;

		String nameFormat = "yyyyMMddHHmmssSSS";
		SimpleDateFormat sdf = new SimpleDateFormat(nameFormat);
		nowTime = sdf.format(date);

		return nowTime;
	}
	
	/**@기능 : 문자열 Encoding
	 * @param pstrTarget
	 * @param pstrFromCharset
	 * @param pstrToCharset
	 * @return
	 */
	public static String encodeCharset(String pstrTarget, String pstrFromCharset, String pstrToCharset)
	{
		String strReturn = null;
		try
		{
			strReturn = new String(pstrTarget.getBytes(pstrFromCharset), pstrToCharset);
		}
		catch (Exception e)
		{
		}
		return strReturn;
	}

	/**@기능 : 문자열 Encoding
	 * @param pstrText
	 * @param pstrToCharset
	 * @return
	 */
	public static String encodeCharset(String pstrText, String pstrToCharset)
	{
		return encodeCharset(pstrText, "ISO-8859-1", pstrToCharset);
	}
	
	/**@기능 : Object to String
	 * @param Object
	 * @return String
	 */
	public static String toString(Object obj){
		if(obj != null){
			return obj.toString();
		}else{
			return "";
		}
	}
	
	public static String StringReplace(String pstrTarget) {
		return removeSpecialChars(pstrTarget, "`~!@#$%^*()-_=+[]{}\\|;:'\",.<>/?”’≤？ㆍ· ");
	}
	public static String removeSpecialChars(String pstrTarget, String pstrSpecialChars) {
		char chrSpecialChar;
		String strTarget = "";
		String strRegExpr = "";
		
		if (length(pstrTarget) > 0) {
			strTarget = pstrTarget;
			
			for (int intLoop = 0; intLoop < pstrSpecialChars.length(); intLoop++)
			{
				chrSpecialChar = pstrSpecialChars.charAt(intLoop);
				
				if (chrSpecialChar == '{' || chrSpecialChar == '}' || 
					chrSpecialChar == '(' || chrSpecialChar == ')' ||
					chrSpecialChar == '[' || chrSpecialChar == ']' || 
					chrSpecialChar == '+' || chrSpecialChar == '*' || chrSpecialChar == '?' || 
					chrSpecialChar == '.' || chrSpecialChar == '\\'
				   )
				{
					strRegExpr = "\\" + String.valueOf(chrSpecialChar);
				}
				else {
					strRegExpr = String.valueOf(chrSpecialChar);
				}
				strTarget = strTarget.replaceAll(strRegExpr, "");
			}
		}
		
		return strTarget ;
	}
	
	
	public static int length(String strTarget) {
		int intLength = 0;
		
		if (strTarget == null) {
			intLength = -1;
		}
		else {
			intLength = strTarget.length();
		}
		
		return intLength;
	}
	
	/**
    * yyyyMMdd 형식의 날짜문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다<br/>
    * <pre>
    * ex) 20030405, ch(.) -> 2003.04.05
    * ex) 200304, ch(.) -> 2003.04
    * ex) 20040101,ch(/) --> 2004/01/01 로 리턴
    * </pre>
    *
    * @param date yyyyMMdd 형식의 날짜문자열
    * @param ch 구분자
    * @return 변환된 문자열
     */
    public static String formatDate(String sDate, String ch) {
     //String dateStr = validChkDate(sDate);
        String str = sDate.trim();
        String yyyy = "";
        String mm = "";
        String dd = "";
        if (str.length() == 8) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";
            mm = str.substring(4, 6);
            if (mm.equals("00"))
                return yyyy;
            dd = str.substring(6, 8);
            if (dd.equals("00"))
                return yyyy + ch + mm;
            return yyyy + ch + mm + ch + dd;
        } else if (str.length() == 6) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";
            mm = str.substring(4, 6);
            if (mm.equals("00"))
                return yyyy;
            return yyyy + ch + mm;
        } else if (str.length() == 4) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";
            else
                return yyyy;
        } else if (str.length() > 8) {
         str = str.replaceAll("-", "");
          yyyy = str.substring(0, 4);
         
             if (yyyy.equals("0000"))
                 return "";
             mm = str.substring(4, 6);
             if (mm.equals("00"))
                 return yyyy;
             dd = str.substring(6, 8);
             if (dd.equals("00"))
                 return yyyy + ch + mm;
             return yyyy + ch + mm + ch + dd;
        } else{
          return "";
        }
    }   
    
    
    /**
     * 체크할 숫자 중에서 숫자인지 아닌지 체크하는 기능
     * 숫자이면 True, 아니면 False를 반환한다
     * @param checkStr - 체크문자열
     * @return 숫자여부
     * @exception MyException 
     * @see  
     * 2015-06-09 jms 추가
     */	
	public static Boolean getNumberValidCheck(String checkStr) {

	    if ( checkStr != null && checkStr.length() > 0 ) {
        	int i;
        	//String sourceStr = String.valueOf(sourceInt);
        
        	int checkStrLt = checkStr.length();
        
        	try {
        	    for (i = 0; i < checkStrLt; i++) {
        
            		// 아스키코드값( '0'-> 48, '9' -> 57)
            		if (checkStr.charAt(i) > 47 && checkStr.charAt(i) < 58) {
            		    continue;
            		} else {
            		    return false;
            		}
        	    }
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}
        
        	return true;
	    } else {
	        return false;
	    }
    }

	public static Boolean arrStringCheck(String[] arr){
		boolean result = false;
		for(String s : arr){
			if(!s.toString().isEmpty()){
				result = true;
				break;
			}
		}
		return result;
	}

}
