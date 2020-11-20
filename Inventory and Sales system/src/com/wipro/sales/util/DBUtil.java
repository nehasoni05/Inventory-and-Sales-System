package com.wipro.sales.util;

import java.sql.*;
public class DBUtil 
{
	
	public static Connection getDBConnection()
	{
		Connection con=null;
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","NEHA","nehasoni");
			//System.out.print("connections done");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
}
