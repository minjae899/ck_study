package spring.common.module.paging;

import java.util.Map;

import spring.common.util.StringUtil;

public class Pagination {
	
	public static void setPagination(PaginationInfo paginationInfo, Map<String , Object> params, int totalCnt ,int  recordCountPerPage , int pageSize){
		
		paginationInfo.setTotalRecordCount(totalCnt);
		
		try{
			recordCountPerPage = Integer.parseInt(params.get("recordCountPerPage").toString());
		}catch(NullPointerException e){
			recordCountPerPage = 10;
		}catch (NumberFormatException e) {
			recordCountPerPage = 10;
		}catch (Exception e){
			e.printStackTrace();
		}
		
		int lastpage = totalCnt / recordCountPerPage;
    	if( totalCnt % recordCountPerPage > 0){
    		lastpage += 1;
    	}

		int pageIndex = !"".equals(StringUtil.toString(params.get("pageIndex"))) ? Integer.parseInt(StringUtil.toString(params.get("pageIndex"))) : 1 ;
		if(pageIndex > lastpage){
			if(pageIndex - lastpage <= 0){
				pageIndex = 1;
			}else{
				pageIndex -= 1;
			}
		}
		
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(recordCountPerPage);
		paginationInfo.setPageSize(pageSize);
		
		params.put("totalCnt", totalCnt);
		params.put("pageIndex", pageIndex);
		params.put("firstIndex", paginationInfo.getFirstRecordIndex());
		params.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
		
	}
}