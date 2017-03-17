package servlet.common.util;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import servlet.common.dao.CommonDAO;
import servlet.common.dao.impl.CommonDAOImpl;
import servlet.member.vo.MemberVO;

/**
 * @quickCode ##
* @project  study
* @path cmmn.util.Util.java
* @auth CK
* @date 2017. 2. 27. 오후 4:56:01
* @other 
* TODO CK
* 유틸성 로직.
 */
public class Util {
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:49:03
	* @other 
	* @param list
	* @param targetId
	* @return
	* TODO CK
	* 월단위로 로그인한 자신의 지각비의총액을 계산한다.
	 */
	public static String sumMyPenalty(List<HashMap<String, ArrayList<MemberVO>>> list, String targetId){
		String result = "";
		int penalty = 0;
		for(int i=0; i<list.size(); i++){
			Set<String> keys = list.get(i).keySet();
			String key = keys.toString().replace("[", "").replace("]", "").trim();
			ArrayList<MemberVO> memberList = list.get(i).get(key);
			
			for(int j=0; j<memberList.size(); j++){
				if(memberList.get(j).getId().equals(targetId)){
					if(null != memberList.get(j).getPenalty() && !"".equals(memberList.get(j).getPenalty()) && !"null".equals(memberList.get(j).getPenalty())){
						penalty += Integer.parseInt(memberList.get(j).getPenalty());
					}
				}
			}
		}
		
		return penalty+"";
	}
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:50:40
	* @other 
	* @param fullDate
	* @return
	* TODO CK
	* 날자를 받아 지각비를계산한다.
	 */
	public static String sumPenalty(String fullDate){
		String result = "";
		int penaltyUnit = 100; //분당 지각 비용
		int penaltyMax = 3000; //
		
		int hh = 0;
		int mm = 0;
		
		try{
			hh = Integer.parseInt(fullDate.substring(11, 13));
			mm = Integer.parseInt(fullDate.substring(14, 16));
			
			if(hh >= 9){
				if(mm == 0){
					result = "";
				}else if(mm>0 && mm<30){
					result = (mm * penaltyUnit) + "";
				}else{
					result = penaltyMax + "";
				}
			}
			
		}catch(NullPointerException e){
			result = "3000";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 5:08:14
	* @other 
	* @param targetIp
	* @return
	* TODO CK
	* 아이피 체크하는 로직.
	 */
	public static boolean checkIp(String targetIp){
		boolean result = true;
		
		CommonDAO cdao = new CommonDAOImpl();
		
		//게이트웨이 주소.
		String gatewayIp = cdao.selectAllowedIp();
		if(null == gatewayIp || "".equals(gatewayIp) || "null".equals(gatewayIp)){
			return true;
		}
		
		System.out.println("targetIp(받아온아이피) : " + targetIp);
		System.out.println("gatewayIp(디비에등록된아이피) : " + gatewayIp);
		
		String[] sGatewayIp = gatewayIp.split("\\.");
		String[] sTargetIp = targetIp.split("\\.");
		
		for(int i=0; i<3; i++){
			System.out.println(sGatewayIp[i] + " : " + sTargetIp[i]);
			if(!sGatewayIp[i].equals(sTargetIp[i])){
				result = false;
			}
		}
		return result;
	}
	
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 5:08:02
	* @other 
	* @param args
	* TODO CK
	* 태스트를위한로직.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		System.out.println(req.getRemoteAddr());
//		System.out.println(req.getRemoteHost());
//		System.out.println(req.getHeader("x-forwarded-for"));
//		System.out.println(InetAddress.getLocalHost().getHostAddress());
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		System.out.println(Util.checkIp(ip));
	}
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 3. 9. 오전 11:03:23
	* @other 
	* @param args
	* TODO CK
	* 널체크
	 */
	public static Object nvl(Object pram){
		Object result = pram;
		
		try{
			if(null == pram || "" == pram){
				throw new NullPointerException();
			}
		}catch(NullPointerException e){
			result = "";
		}
		
		return result;
	}
	
}
