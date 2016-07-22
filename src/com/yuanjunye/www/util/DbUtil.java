package com.yuanjunye.www.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {
			// 数据库地址
			private static final String dbUrl="jdbc:mysql://localhost:3306/db_book";
			// 用户名
			private static final String dbUserName="root";
			// 密码
			private static final String dbPassword="123456";
			// 驱动名称
			private static final String jdbcName="com.mysql.jdbc.Driver";
			
			/**
			 * 连接数据库
			 * @return
			 * @throws Exception
			 */
			public static Connection getConn()throws Exception{
				Class.forName(jdbcName);
				Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				return con;
			}
		
			/**
			 * 关闭连接
			 * @param fos
			 * @param con
			 * @throws SQLException 
			 */
			public static void close(ResultSet rs,PreparedStatement fos,Connection con) throws SQLException{
				if(rs != null) {
					rs.close();
					if(fos != null){
						fos.close();
						if(con != null) {
							con.close();
						}
					}
				} 
			}
			
			/**
			 * 关闭连接
			 * @param fos
			 * @param con
			 * @throws SQLException
			 */
			public static void close(PreparedStatement fos,Connection con) throws SQLException{
				
					if(fos != null){
						fos.close();
						if(con != null) {
							con.close();
						}
					}
				} 
			
}
