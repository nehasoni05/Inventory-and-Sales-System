package com.wipro.sales.dao;

import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.util.DBUtil;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
public class SalesDao 
{
	PreparedStatement ps;
	DBUtil obj=new DBUtil();
	Connection con=obj.getDBConnection();
	public int InsertSales(Sales sale)
	{
		int t=0;
		try
		{
		ps=con.prepareStatement("insert into TBL_SALES values (?,?,?,?,?)");
		ps.setString(1, sale.getsalesID());
		ps.setDate(2,(Date) sale.getsalesDate());
		ps.setString(3, sale.getproductID());
		ps.setInt(4, sale.getquantitySold());
		ps.setDouble(5, sale.getsalesPrice());
		t=ps.executeUpdate();
		//con.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		return t;
	}
	public String generateSalesID(java.util.Date salesDate)
	{
		try
		{
		java.sql.Date dateValue=new java.sql.Date(salesDate.getTime());
		int nextID_from_seq=0;

		  Calendar cal = Calendar.getInstance();
		  cal.setTime(dateValue);
		  int year = cal.get(Calendar.YEAR);                                        
		 
	    ps=con.prepareStatement("select SEQ_SALES_ID.nextval from dual");
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			nextID_from_seq=rs.getInt(1);
		
		//concatenate seq id and last 2 digits of year
		String salesId=""+(year%100)+nextID_from_seq;
	//	con.close();
		return salesId;
		
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
        return null;
	}
	public ArrayList<SalesReport> getSalesReport()
	{
		ArrayList<SalesReport> sale=new ArrayList<SalesReport>();
		try
		{
		ps=con.prepareStatement("select * from V_SALES_REPORT");
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			SalesReport obj=new SalesReport();
			obj.setsalesId(rs.getString("Sales_ID"));
			obj.setsalesDate(rs.getDate("Sales_Date"));
			obj.setProductId(rs.getString("Product_ID"));
			obj.setProductName(rs.getString("Product_Name"));
			obj.setquanSold(rs.getInt("Quantity_Sold"));
			obj.setproductUnitPrice(rs.getDouble("Product_Unit_Price"));
			obj.setsalesPricePerUnit(rs.getDouble("Sales_Price_Per_Unit"));
			obj.setprofitAmount(rs.getDouble("Profit_Amount"));
			sale.add(obj);
		}
		//con.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		return sale;
	}
}
