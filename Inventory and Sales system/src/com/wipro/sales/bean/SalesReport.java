package com.wipro.sales.bean;

public class SalesReport 
{
	private String salesID;
	private java.sql.Date salesDate;
	private String productID;
	private String productName;
	private int quantitySold;
	private double productUnitPrice;
	private double salesPricePerUnit;
	private double profitAmount;
	
	//for setters and getters
	//for sales id
	public void setsalesId(String id)
	{
		salesID=id;
	}
	public String getsalesId()
	{
		return salesID;
	}
	//for sales date
	public void setsalesDate(java.sql.Date d)
	{
		salesDate=d;
	}
	public java.sql.Date getsalesDate()
	{
		return salesDate;
	}
	//for product id
	public void setProductId(String id)
	{
		productID=id;
	}
	public String getProductId()
	{
		return productID;
	}
	//for product name
	public void setProductName(String name)
	{
		productName=name;
	}
	public String getProductName()
	{
		return productName;
	}
	//for quan sold
	public void setquanSold(int q)
	{
		quantitySold=q;
	}
	public int getquanSold()
	{
		return quantitySold;
	}
	//for product unit price
	public void setproductUnitPrice(double p)
	{
		productUnitPrice=p;
	}
	public double getproductUnitPrice()
	{
		return productUnitPrice;
	}
	//for sales price per unit
	public void setsalesPricePerUnit(double p)
	{
		salesPricePerUnit=p;
	}
	public double getsalesPricePerUnit()
	{
		return salesPricePerUnit;
	}
	//for profit amount
	public void setprofitAmount(double p)
	{
		profitAmount=p;
	}
	public double getprofitAmount()
	{
		return profitAmount;
	}
}
