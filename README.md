# Inventory-and-Sales-System
This is a swing based application using JAVA as frontend and ORACLE as backend for their Inventory and Sales maintenance.
As we are using Oracle database in this project so here are the database commands that you have to execute in you database.
1) Create the table called TBL_STOCK :-

create table TBL_STOCK
(
Product_ID varchar(6) NOT NULL PRIMARY KEY,
Product_Name varchar(20) NOT NULL UNIQUE , 
Quantity_On_Hand number CHECK(Quantity_On_Hand>=0),
Product_Unit_Price number CHECK(Product_Unit_Price>=0),
Recorder_Level number CHECK(Recorder_Level>=0)
); 

********************************************************************
2) Create a table called TBL_SALES 

create table TBL_SALES
(
Sales_ID varchar(6) NOT NULL PRIMARY KEY,
Sales_Date DATE DEFAULT sysdate, 
Product_ID varchar(6),
Quantity_Sold number CHECK(Quantity_Sold>=0),
Sales_Price_Per_Unit number CHECK(Sales_Price_Per_Unit>=0),
FOREIGN KEY (Product_ID) REFERENCES TBL_STOCK(Product_ID)
); 

********************************************************************
3) Create the following sequences :-

a)
CREATE SEQUENCE sq1
    INCREMENT BY 10
    START WITH 10
    MINVALUE 10
    MAXVALUE 100
    CYCLE
    CACHE 2;
    
b)
create sequence SEQ_PRODUCT_ID
INCREMENT BY 1
START WITH 1004
NOCACHE
NOCYCLE;

4) Create a view names V_SALES_REPORT using TBL_SALES table joined with TBL_STOCK table based on ProductId order the result based on Profit_Amount in descending and Sales_Id
in ascending.

create view V_SALES_REPORT AS
select Sales_ID,Sales_Date,Product_ID,Product_Name,Quantity_Sold,Product_Unit_Price,Sales_Price_Per_Unit,
(Sales_Price_Per_Unit - Product_Unit_Price) Profit_Amount
FROM TBL_STOCK NATURAL JOIN TBL_SALES
ORDER BY Profit_Amount DESC, Sales_ID ASC;



    
