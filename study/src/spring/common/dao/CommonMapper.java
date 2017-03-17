package spring.common.dao;

import spring.common.anno.Mapper;

@Mapper("commonMapper")
public interface CommonMapper {
	
	String selectSysdate();
	
	String selectAllowedIp();
	
}
