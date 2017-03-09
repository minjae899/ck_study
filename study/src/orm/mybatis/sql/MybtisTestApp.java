package orm.mybatis.sql;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import member.vo.MemberVO;

public class MybtisTestApp {
	
	public static void main(String[] args) throws Throwable {
		/*
		Reader reader = Resources.getResourceAsReader("orm/mybatis/SqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=factory.openSession();
		
		MemberVO mvo = new MemberVO();
		mvo.setId("kjs");
		mvo.setPw("wnstjd");
		
		MemberVO rvo = (MemberVO)session.selectOne("selectLoginInfo", mvo);
		
		System.out.println(":: 2. get(SELECT)  ? "+rvo.toString());
		*/
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"orm/mybatis/SqlMapConfig.xml"});
		
	}
	
}
