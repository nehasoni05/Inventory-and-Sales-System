package com.wipro.sales.service;

import java.sql.*;
import java.util.ArrayList;

import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.bean.Stock;
import com.wipro.sales.dao.SalesDao;
import com.wipro.sales.dao.StockDao;
import com.wipro.sales.util.DBUtil;

public class Administrator 
{
	StockDao stockObject=new StockDao();
	SalesDao salesObject=new SalesDao();
	PreparedStatement ps;
	Connection con=DBUtil.getDBConnection();
	
	//insert stock in stock table using StockDao class
	public String insertStock(Stock stockobj) throws SQLException
	{
		int check=0;
		String productId="";
		if(stockobj!=null && stockobj.getproductName().length() >= 2)
		{
		    productId=stockObject.generateProductID(stockobj.getproductName());
			stockobj.setproductId(productId);
			System.out.println(productId);
			check=stockObject.insertStock(stockobj);

		}
		
		if(check==1)
			return "Inserted Successfully";
		else
			return "Data not valid for insertion";
		
	}
	
	//delete stock from stock table using StockDao class
	public String deleteStock(String pID)
	{
		int check=0;
		check=stockObject.delStock(pID);
		if(check==1)
		{
			System.out.print("1");
			return "Deleted Successfully";
		
		}
		else
		{
			System.out.print("0");
			return "record cannot be deleted";
		}
	}
	
	//insert stock in sale table using SaleskDao class
	public String insertSales(Sales salesobj) throws SQLException
	{
		int qty=0,check=0,checkUpdate=0;
		java.sql.Date date;
		java.sql.Date currentDate;
		String salesId;
		if(salesobj==null)                                    //condition 1
		{
			return "Object not valid for Insertion";
		}
		if(salesobj.getproductID()==null)                      //condition 2
		{
			return "Unknown product for sales";
		}
		
		//get quantity on hand values from table
		ps=con.prepareStatement("select Quantity_On_Hand from TBL_STOCK where Product_ID=?");
		ps.setString(1, salesobj.getproductID());
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			 qty=rs.getInt(1); 
		if(qty < salesobj.getquantitySold())                          //condition 3
		{ 
			return "Not enough stock on hand for values";
		}
		
		//get current date and sales date to check condition 4
		date= new java.sql.Date(salesobj.getsalesDate().getTime());
		currentDate=new java.sql.Date(new java.util.Date().getTime());
		if(date.compareTo(currentDate)>0)                                 //condition 4
		{
			return "Invalid Date";
		}
		 
		//f all conditions are valid generate sales id
		salesId=salesObject.generateSalesID(salesobj.getsalesDate());
		salesobj.setsalesID(salesId);
		
		//call insert method to insert stock
		check=salesObject.InsertSales(salesobj);
		if(check!=-1)                                         //if insertion is successful
		{
			checkUpdate=stockObject.updateStock(salesobj.getproductID(), salesobj.getquantitySold());
		}
		else
			return "Error in Sales insertion";
		
		//check for both insertion and updation
		if(check!=-1 && checkUpdate!=-1)
			return "Sales Completed";
		else
			return "Error";
	}
	
	//get sales report
	public ArrayList<SalesReport> getSalesReport()
	{
		return salesObject.getSalesReport();
	}
	
}
