package member.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import cmmn.util.Util;
import member.dao.MemberDAO;
import member.dao.impl.MemberJDBCDAOImpl;
import member.service.MemberService;
import member.vo.AttendVO;
import member.vo.MemberVO;

public class MemberServiceImpl implements MemberService{

	MemberDAO dao = new MemberJDBCDAOImpl();
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:49:42
	* @other 
	* @param id
	* @return
	* TODO CK
	* 오늘 출석 여부를 체크하여
	* 출석을 하였다면 재출석이 안되도록..
	 */
	@Override
	public boolean checkAttend(String id) {
		boolean result = false;
		AttendVO avo = new AttendVO();
		avo = dao.selectAttend(id);
		if(avo == null){
			dao.insertAttend(id);
			result = true;
		}
		return result;
	}
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:49:45
	* @other 
	* @return
	* TODO CK
	 */
	@Override
	public List<HashMap<String, ArrayList<MemberVO>>> selectAllMember() {
		
		List<HashMap<String, ArrayList<MemberVO>>> result = new ArrayList<HashMap<String, ArrayList<MemberVO>>>();
		
		TreeSet<String> gijunDate = new TreeSet<String>();
		List<MemberVO> list = dao.selectAttendCheckAllMember(gijunDate);
		
		NavigableSet<String> gijunDateDESC = gijunDate.descendingSet();
		
		String rGijun = gijunDateDESC.toString().replace("[", "").replace("]", "");
		String[] splitGijun = rGijun.split(", ");
		
		//int s = splitGijun.length;
		int s = dao.selectMemberCnt();
		int cnt = 0;
		ArrayList<MemberVO> containList = new ArrayList<MemberVO>();
		HashMap<String, ArrayList<MemberVO>> obj = new HashMap<String, ArrayList<MemberVO>>();
		for(int i=1; i<=list.size(); i++){
			containList.add(list.get(i-1));
			if(i%s == 0 && containList.size()>1){
				obj.put(splitGijun[cnt], containList);
				result.add(obj);
				obj = new HashMap<String, ArrayList<MemberVO>>();
				containList = new ArrayList<MemberVO>();
				++cnt;
			}
		}
		
		return result;
	}
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:49:54
	* @other 
	* @param id
	* @param pw
	* @param req
	* @return
	* TODO CK
	 */
	@Override
	public String login(HttpServletRequest req, MemberVO pvo) {
		String returnPage = "";
		MemberVO rvo = new MemberVO();
		
		String targetIp;
		try {
			targetIp = req.getRemoteAddr();
			System.out.println("targetIp : " + targetIp);
			
			//아이피 체크.
			boolean allowedIp = Util.checkIp(targetIp);
			if(!allowedIp){
				return "NotAllowedIp";
			}
			
			//로그인 체크.
			if(dao.selectId(pvo.getId())){
				rvo = dao.selectLogin(pvo);
				if(null == rvo.getId()){
					returnPage = "NotFoundPw";
				}else{
					returnPage = "success";
					
					req.getSession().setAttribute("loginVO", rvo);
					String sessionId = ((MemberVO)req.getSession().getAttribute("loginVO")).getId();
					System.out.println(sessionId);
				}
			}else{
				returnPage = "NotFoundId";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnPage;
	}

}
