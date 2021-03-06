CREATE DATABASE [WebShop_DB]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[IdCategory] [int] IDENTITY(1,1) NOT NULL,
	[Category] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[IdCategory] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order_Details]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_Details](
	[IdOrder] [int] NOT NULL,
	[IdProduct] [int] NOT NULL,
	[ProductQuantity] [int] NOT NULL,
	[ProductTotalPrice] [money] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[IdOrder] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[BuyDate] [date] NOT NULL,
	[Total] [money] NOT NULL,
	[PaymentMethod] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[IdOrder] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[IdProduct] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Price] [money] NOT NULL,
	[Description] [nvarchar](max) NOT NULL,
	[CategoryId] [int] NOT NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[IdProduct] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[IdUser] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nvarchar](50) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Surname] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](100) NOT NULL,
	[Email] [nvarchar](100) NOT NULL,
	[Adress] [nvarchar](50) NULL,
	[City] [nvarchar](50) NULL,
	[RegDate] [date] NULL,
	[IPAdress] [nvarchar](max) NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[IdUser] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[Order_Details]  WITH CHECK ADD  CONSTRAINT [FK_Order_Details_Orders] FOREIGN KEY([IdOrder])
REFERENCES [dbo].[Orders] ([IdOrder])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Order_Details] CHECK CONSTRAINT [FK_Order_Details_Orders]
GO
ALTER TABLE [dbo].[Order_Details]  WITH CHECK ADD  CONSTRAINT [FK_Order_Details_Products] FOREIGN KEY([IdProduct])
REFERENCES [dbo].[Products] ([IdProduct])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Order_Details] CHECK CONSTRAINT [FK_Order_Details_Products]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Users] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([IdUser])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Users]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Category] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Category] ([IdCategory])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Category]
GO
/****** Object:  StoredProcedure [dbo].[getCartItemsByOrderID]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
  create procedure [dbo].[getCartItemsByOrderID]
		@id int
  as
  select od.ProductQuantity,od.ProductTotalPrice,p.* from Order_Details as od
  join Products as p on od.IdProduct=p.IdProduct 
  where od.IdOrder=@id
GO
/****** Object:  StoredProcedure [dbo].[getOrdersByUserId]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
  create procedure [dbo].[getOrdersByUserId]
		@id int
  as
	
  select o.IdOrder,o.BuyDate,o.Total,o.PaymentMethod from Orders as o 
  where o.UserId=@id
GO
/****** Object:  StoredProcedure [dbo].[getProductById]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 create procedure [dbo].[getProductById]
	@id int 
 as
 select p.IdProduct,p.Name,p.Price,p.Description,c.* from Products as p
  inner join Category as c on p.CategoryId=c.IdCategory
  where p.IdProduct=@id
GO
/****** Object:  StoredProcedure [dbo].[getProductsByCategory]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[getProductsByCategory]
	@id int
as
select p.IdProduct,p.Name,p.Price,p.Description,c.* from Products as p
inner join Category as c on p.CategoryId=c.IdCategory
where CategoryId=@id
  
GO
/****** Object:  StoredProcedure [dbo].[getProductWithCategory]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
  create procedure [dbo].[getProductWithCategory] 
  as
  select p.IdProduct,p.Name,p.Price,p.Description,c.* from Products as p
  inner join Category as c on p.CategoryId=c.IdCategory

  
GO
/****** Object:  StoredProcedure [dbo].[insertIntoOrderDetails]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[insertIntoOrderDetails]
	@idOrder int,
	@idProduct int,
	@productQuantitiy int,
	@productTotalPrice money
as
begin
	insert into Order_Details values (@idOrder,@idProduct,@productQuantitiy,@productTotalPrice)
end
GO
/****** Object:  StoredProcedure [dbo].[insertOrder]    Script Date: 23.8.2021. 16:25:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create procedure [dbo].[insertOrder]
	@UserId int,
	@BuyDate date,
	@Total money,
	@PaymentMethod nvarchar(50),
	@id int output
as
begin
	insert into Orders values(@UserId,@BuyDate,@Total,@PaymentMethod)
	set @id=SCOPE_IDENTITY()
end
GO
