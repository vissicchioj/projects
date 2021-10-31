--------------------------------------------------------------------------------------------------
-- WORK IN PROGRESS
-- P.S. DO NOT SPOILER MY FAMILY/FRIENDS
--------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------
-- Drops the tables from the database if it exists
--------------------------------------------------------------------------------------------------
DROP TABLE IF EXISTS PeopleGifts CASCADE;
DROP TABLE IF EXISTS ItemsStores CASCADE;
DROP TABLE IF EXISTS Stores CASCADE;
DROP TABLE IF EXISTS People CASCADE;
DROP TABLE IF EXISTS Items CASCADE;


--------------------------------------------------------------------------------------------------
-- Creates the tables in the database
--------------------------------------------------------------------------------------------------
--People--
CREATE TABLE People (
	pid						int not null,
	name					text,
	relationshipToMe		text,
primary key(pid)
);

--Stores--
CREATE TABLE Stores (
	sid						int not null,
	storeName				text,
	openingTime				time,
	closingTime				time,
primary key(sid)
);

--Items--
CREATE TABLE Items (
	iid						int not null,
	itemName				text,
	itemCategory			text,
	itemCostUSD				decimal(10,2),
	stockingStuffer			boolean,
primary key(iid)
);

--ItemsStores--
CREATE TABLE ItemsStores (
	iid						int not null references Items(iid),
	sid						int not null references Stores(sid),
primary key(iid, sid)
);

--PeopleGifts--
CREATE TABLE PeopleGifts (
	pid						int not null references People(pid),
	iid						int not null references Items(iid),
	quant					int,
	result					int,
primary key(pid, iid)
);

--------------------------------------------------------------------------------------------------
-- SQL Statements for loading in the data
--------------------------------------------------------------------------------------------------
--People
INSERT INTO People (pid, name, relationshipToMe)
VALUES
(001, 'Jake', 'Me'),
(002, 'Alexia', 'Girlfriend'),
(003, 'Maryann', 'Mom'),
(004, 'John', 'Dad')
;

--Stores--
INSERT INTO Stores (sid, storeName, openingTime, closingTime)
VALUES
(000, 'Amazon', null, null),
(001, 'Target', '08:00:00', '22:00:00'),
(002, 'FiveBelow', '10:00:00', '20:00:00')
;

--Items--
INSERT INTO Items (iid, itemName, itemCategory, itemCostUSD, stockingStuffer)
VALUES
(101, 'Sour Smog Balls', 'Candy', 1.50, true),
(102, 'Toxic Waste', 'Candy', 1.50, true),
(103, 'Fun Dip Razz-Apple', 'Candy', 0.33, true),
(104, 'Fun Dip Cherry', 'Candy', 0.33, true),
(701, 'ACNH: Happy Home Paradise DLC', 'Video Games', 24.99, false),
(702, 'Nintendo Online: 1 Year', 'Video Games', 20.00, false)
;

--ItemsStores--
INSERT INTO ItemsStores (iid, sid)
VALUES
(101, 002),
(102, 002),
(103, 002),
(104, 002),
(701, 000),
(701, 001),
(702, 001)
;

--PeopleGifts--
INSERT INTO PeopleGifts (pid, iid, quant, result)
VALUES
(002, 101, 1, null),
(002, 102, 1, null),
(002, 103, 2, null),
(002, 104, 1, null),
(002, 701, 1, null),
(002, 702, 1, null)
;

--------------------------------------------------------------------------------------------------
-- SQL Queries
--------------------------------------------------------------------------------------------------
select * from People;
select * from Stores;
select * from Items;
select * from PeopleGifts;
select * from ItemsStores;

select p.name, i.itemName as gift, i.itemCostUSD, g.quant, i.stockingStuffer
	from PeopleGifts g inner join People p on g.pid = p.pid
					   inner join Items i on g.iid = i.iid
					   ;

