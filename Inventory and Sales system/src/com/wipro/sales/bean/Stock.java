package com.wipro.sales.bean;

public class Stock
{
	private String productID;
	private String productName;
	private int quantityOnHand;
	private double productUnitPrice;
	private int recorderLevel;
	
	//getters and setters
	//for product id
	public void setproductId(String pId)
	{
		productID=pId;
		//System.out.print("set      ");
	}
	public String getproductId()
	{
		return productID;
	}
	
	//for product name
	public void setproductName(String pName)
	{
		productName=pName;
		System.out.print(pName);
	}
	public String getproductName()
	{
		return productName;
	}
	
	//for quantityOnHand
	public void setquantityOnHand(int q)
	{
		quantityOnHand=q;
		System.out.print("set      ");
	}
	public int getquantityOnHand()
	{
		return quantityOnHand;
	}
	
	//for productUnitPrice
	public void setproductUnitPrice(double p)
	{
		productUnitPrice=p;
		System.out.print("set      ");
	}
	public double getproductUnitPrice()
	{
		return productUnitPrice;
	}
	
	//for recorderLevel
	public void setrecorderLevel(int r)
	{
		recorderLevel=r;
		System.out.print("set      ");
	}
	public int getrecorderLevel()
	{
		return recorderLevel;
	}
}
