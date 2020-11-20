package com.wipro.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.wipro.sales.bean.Stock;
import com.wipro.sales.util.DBUtil;

public class StockDao 
{
	PreparedStatement ps;
	DBUtil obj=new DBUtil();
	Connection con=obj.getDBConnection();
	
	//insert stock
	public int insertStock(Stock sale)
	{
		int t=0;
		try
		{
		ps=con.prepareStatement("insert into TBL_STOCK values (?,?,?,?,?)");
		ps.setString(1, sale.getproductId());
		ps.setString(2, sale.getproductName());
		ps.setInt(3, sale.getquantityOnHand());
		ps.setDouble(4, sale.getproductUnitPrice());
		ps.setInt(5,sale.getrecorderLevel());
		t=ps.executeUpdate();
		//con.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		return t;
	}
	
	//generate product id
	public String generateProductID(String pName)
	{
		try
		{
		int nextID_from_seq=0;
		String s=pName.substring(0,2);
	    ps=con.prepareStatement("select SEQ_PRODUCT_ID.nextval from dual");
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			nextID_from_seq=rs.getInt(1);
		
		//concatenate seq id and last 2 digits of year
		String productId=s+nextID_from_seq;
		//con.close();
		return productId;
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
        return null;
	}
	
	//update stock table
	public int updateStock(String productID,int soldQty)
	{
		int t=0,currentQuan,remainingQuan;
		Stock obj=new Stock();
		try
		{
			ps = con.prepareStatement("select Quantity_On_Hand from TBL_STOCK where Product_ID = ?");
			ps.setString(1, productID);
			 ResultSet rs = ps.executeQuery();
			    int curQty = 0;
			    if (rs.next()) {
			      curQty = rs.getInt(1);
			    }
			    ps = con.prepareStatement("update tbl_stock set Quantity_On_Hand = ? where Product_ID = ?");
			    ps.setInt(1, (curQty - soldQty));
			    ps.setString(2, productID);
			t=ps.executeUpdate();
		//	con.close();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	//get Stock
	public Stock getStock(String productID)
	{
		Stock obj=new Stock();
		try 
		{
			ps=con.prepareStatement("select * from TBL_STOCK where Product_ID=?");
			ps.setString(1,productID);
			ResultSet rs=ps.executeQuery();
			if (rs.next())
			{
			obj.setproductId(rs.getString("Product_ID"));
			obj.setproductName(rs.getString("Product_Name"));
			obj.setquantityOnHand(rs.getInt("Quantity_On_Hand"));
			obj.setproductUnitPrice(rs.getDouble("Product_Unit_Price"));
			obj.setrecorderLevel(rs.getInt("Recorder_Level"));
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	//delete stock for given product id
	public int delStock(String pid)
	{
		int t=0,t1=0,t2;
		try
		{
			ps=con.prepareStatement("delete from TBL_SALES where Product_ID = ?");
			ps.setString(1, pid);
			t=ps.executeUpdate();
			ps=con.prepareStatement("delete from TBL_STOCK where Product_ID = ?");
			ps.setString(1, pid);
			t=ps.executeUpdate();
			//System.out.print("1");
			return 1;
			//con.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
			//System.out.print("0D");
		}
		return 0;
	}
}
