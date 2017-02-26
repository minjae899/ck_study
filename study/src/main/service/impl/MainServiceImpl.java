package main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import main.dao.JDBCMainDAO;
import main.service.MainService;
import main.vo.AttendVO;
import main.vo.MemberVO;

public class MainServiceImpl implements MainService{

	private JDBCMainDAO jdbcDao = new JDBCMainDAO();
	
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
	
	public static void main(String[] args) {
		MainService service = new MainServiceImpl();
		
		//service.doCheck("chunkind");
		//System.out.println(service.selectAllMember().toString());

	}
}
