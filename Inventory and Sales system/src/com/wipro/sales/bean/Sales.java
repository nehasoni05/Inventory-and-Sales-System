package com.wipro.sales.bean;
import java.sql.*;
public class Sales
{
	private String salesID;
	private java.util.Date salesDate;
	private String productID;
	private int quantitySold;
	private double salesPricePerUnit;
	
	//setters and getters
	//for salesID
	public void setsalesID(String sId)
	{
		salesID=sId;
	}
	public String getsalesID()
	{
		return salesID;
	}
	//for date
	public void setsalesDate(java.util.Date d)
	{
		salesDate=d;
	}
	public java.util.Date getsalesDate()
	{
		return salesDate;
	}
	//for product id
	public void setproductID(String pid)
	{
		productID=pid;
	}
	public String getproductID()
	{
		return productID;
	}
	//for quantity sold
	public void setquantitySold(int qSold)
	{
		quantitySold=qSold;
	}
	public int getquantitySold()
	{
		return quantitySold;
	}
	//for sales price per unit
	public void setsalesPrice(double sprice)
	{
		salesPricePerUnit=sprice;
	}
	public double getsalesPrice()
	{
		return salesPricePerUnit;
	}
}
