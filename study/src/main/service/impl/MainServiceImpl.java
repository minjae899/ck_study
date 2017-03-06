package main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import cmmn.util.Util;
import main.dao.JDBCMainDAO;
import main.service.MainService;
import main.vo.AttendVO;
import main.vo.MemberVO;

public class MainServiceImpl implements MainService{

	private JDBCMainDAO jdbcDao = new JDBCMainDAO();
	
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
	public boolean doCheck(String id) {
		boolean result = false;
		AttendVO avo = new AttendVO();
		avo = jdbcDao.selectAttend(id);
		if(avo == null){
			jdbcDao.insertCheck(id);
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
		List<MemberVO> list = jdbcDao.selectAttendCheckAllMember(gijunDate);
		
		NavigableSet<String> gijunDateDESC = gijunDate.descendingSet();
		
		String rGijun = gijunDateDESC.toString().replace("[", "").replace("]", "");
		String[] splitGijun = rGijun.split(", ");
		
		//int s = splitGijun.length;
		int s = jdbcDao.selectMemberCnt();
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
	* @date 2017. 2. 27. 오후 4:49:50
	* @other 
	* @param id
	* @param pw
	* @param rvo
	* @return
	* TODO CK
	 */
	@Override
	public String doLogin(String id, String pw, MemberVO rvo) {
		String returnPage = "";
		
		if(jdbcDao.checkId(id)){
			rvo = jdbcDao.doLogin(id, pw);
			if(null == rvo.getId()){
				returnPage = "NotFoundPw";
			}else{
				returnPage = "success";
			}
		}else{
			returnPage = "NotFoundId";
		}
		
		
		return returnPage;
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
	public String doLogin(String id, String pw, HttpServletRequest req) {
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
			if(jdbcDao.checkId(id)){
				rvo = jdbcDao.doLogin(id, pw);
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
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:49:58
	* @other 
	* @param args
	* TODO CK
	 */
	public static void main(String[] args) {
		MainService service = new MainServiceImpl();
		
		//service.doCheck("chunkind");
		//System.out.println(service.selectAllMember().toString());

	}

}
