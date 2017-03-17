package servlet.orm.mybatis.sql;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import servlet.member.vo.MemberVO;

public class MybtisTestApp {
	
	public static void main(String[] args) throws Throwable {
		Reader reader = Resources.getResourceAsReader("servlet/orm/mybatis/SqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=factory.openSession();
		
		MemberVO mvo = new MemberVO();
		mvo.setId("kjs");
		mvo.setPw("wnstjd");
		
		int result  = (Integer)session.selectOne("selectId", mvo.getId());
		
		System.out.println("result : "+result);
		
		
		/*ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"servlet/orm/mybatis/SqlMapConfig.xml"});*/
		
	}
	
}
