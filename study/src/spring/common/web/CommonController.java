package spring.common.web;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import spring.common.Common;
import spring.common.util.Util;
import spring.member.service.MemberService;

public class CommonController extends Common{

	@Resource(name="memberService")
	protected MemberService memberService;
	
	@Resource(name="util")
	protected Util util;
	
}
