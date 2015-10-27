package com.mawujun.crawler.dianping;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.alibaba.druid.pool.DruidDataSource;

//http://langgufu.iteye.com/blog/2167077
public class DB {
	static DataSource dataSource=null;
	static {
		init();
	}
	public static void init() {  
//        Connection conn = null;  
//        try {  
//            Class.forName("com.mysql.jdbc.Driver");  
//            conn = DriverManager.getConnection(  
//                    "jdbc:mysql://localhost:3306/hujibang", "root",  
//                    "");  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        return conn;  
		
		DruidDataSource dataSource1 = new DruidDataSource(); 
		dataSource1.setDriverClassName("com.mysql.jdbc.Driver"); 
		dataSource1.setUsername("root"); 
		dataSource1.setPassword(""); 
		dataSource1.setUrl("jdbc:mysql://127.0.0.1:3306/hujibang"); 
		dataSource1.setInitialSize(5); 
		dataSource1.setMinIdle(1); 
		dataSource1.setMaxActive(10); 
		//dataSource.setFilters("stat");// for mysql  
		//dataSource.setPoolPreparedStatements(false);
		dataSource=dataSource1;
    }  
	
	
	
	public static  void update(String sql,Object... params){
		// Connection conn = getConnection();  
		QueryRunner qr = new QueryRunner(dataSource);
	    try {  
	       
	        int result = qr.update( sql, params);  
	        if (0 < result)  
	            System.out.println("数据更新成功...");  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } 
	}
	
	public static  void batch(String sql, Object params[][]){
		QueryRunner qr = new QueryRunner(dataSource);
		try {
			qr.batch(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
