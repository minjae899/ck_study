package spring.common.service;

import javax.annotation.Resource;

import spring.common.Common;
import spring.common.util.Util;
import spring.member.dao.MemberMapper;

public class CommonService extends Common{
	
	@Resource(name="memberMapper")
	protected MemberMapper dao;
	
	@Resource(name="util")
	protected Util util;
	
}
