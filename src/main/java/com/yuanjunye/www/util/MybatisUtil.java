package com.yuanjunye.www.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

	public static SqlSessionFactory getFactory() throws IOException {
		String resource = "spring-mybatis.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		return factory;
	}
	  
	public static void close(SqlSession session) {
		if(session != null) {
			session.close();
		}
	}
}
