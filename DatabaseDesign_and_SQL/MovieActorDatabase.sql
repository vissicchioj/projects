--------------------------------------------------------------------------------------------------
-- Drops the tables from the database if it exists
--------------------------------------------------------------------------------------------------
DROP TABLE IF EXISTS MovieCasts;
DROP TABLE IF EXISTS MovieDirectors;
DROP TABLE IF EXISTS Spouses;
DROP TABLE IF EXISTS Actors;
DROP TABLE IF EXISTS Directors;
DROP TABLE IF EXISTS Movies;
DROP TABLE IF EXISTS People;
DROP TABLE IF EXISTS PostalCodes;

--------------------------------------------------------------------------------------------------
-- Creates the tables in the database
--------------------------------------------------------------------------------------------------
--Postal Codes--
CREATE TABLE PostalCodes (
	postalCode				text not null,
	city					text,
	state					text,
	country					text,
primary key(postalCode)
);

--People--
CREATE TABLE People (
    pID         			int not null,
    firstName   			text,
    lastName    			text,
    DOB         			date,
	streetAddress			text,
	postalCode				text not null references PostalCodes(postalCode),
	hairColor				text,
	eyeColor				text,
	heightInInches			int,
	weightInLbs				int,
	favColor				text,
primary key(pID)
);

--Actors--
CREATE TABLE Actors (
	pID						int not null references People(pID),
	SAGAnniversaryDate		date,
primary key(pID)
);

--Directors--
CREATE TABLE Directors (
	pID						int not null references People(pID),
	filmSchoolAttended		text,
	DGAnniversaryDate		date,
	favLensMaker			text,
primary key(pID)
);

--Spouses--
CREATE TABLE Spouses (
	pID1					int not null references People(pID) CHECK (pID1 <> pID2),
	pID2					int not null references People(pID) CHECK (pID2 <> pID1),
	yearMarried				text,
	isStillTogether			text CHECK (isStillTogether = 'Y' or isStillTogether = 'N'),
primary key(pID1, pID2)
);

--Movies--
CREATE TABLE Movies (
	mID						int not null,
	movieName				text,
	MPAARating				text,
	releaseDate				date,
	domesticBoxOfficeSales	numeric(12,2),
	foreignBoxOfficeSales	numeric(12,2),
	DVDBlueRaySales			numeric(12,2),
primary key(mID)
);

--Movie Casts--
CREATE TABLE MovieCasts(
	mID						int not null references Movies(mID),
	aID						int not null references Actors(pID),
	castRole				text,
primary key(mID,aID)
);

--Movie Directors--
CREATE TABLE MovieDirectors(
	mID						int not null references Movies(mID),
	dID						int not null references Directors(pID),
primary key(mID,dID)
);

--------------------------------------------------------------------------------------------------
-- SQL Statements for loading in the data
--------------------------------------------------------------------------------------------------
--Postal Codes--
INSERT INTO PostalCodes (postalCode, city, state, country)
VALUES
 ('SW4',   'London',       NULL, 'UK'      ),
 ('7500',  'Paris',        NULL, 'France'  ),
 ('16000', 'Algiers',      NULL, 'Algeria' ),
 ('G1',    'Glasgow',      NULL, 'Scotland'),
 ('11937', 'East Hampton', 'NY', 'USA'     )
;

--People--
INSERT INTO People (pID, firstName, lastName, DOB, streetAddress, postalCode, hairColor, eyeColor,
				   heightInInches, weightInLbs, favColor)
VALUES
 (001, 'Roger',    'Moore',      '1927-10-14', NULL, 'SW4',   'Brown',  'Blue',  73,   175,  'Black' ),
 (002, 'Kristina', 'Tholstrup',  '1941-09-01', NULL, 'SW4',   'Blonde', 'Blue',  NULL, NULL, 'Green' ),
 (003, 'Guy',      'Hamilton',   '1922-09-16', NULL, '7500',  'Brown',  'Brown', NULL, NULL, 'Blue'  ),
 (004, 'Kerima',   NULL,         '1925-02-10', NULL, '16000', 'Brown',  'Brown', NULL, NULL, 'Yellow'),
 (005, 'Bob',      'Spiers',     '1945-09-27', NULL, 'G1',    'Brown',  'Blue',  NULL, NULL, 'White' ),
 (006, 'Sophie',   'Richardson', NULL,         NULL, 'G1',    NULL,     NULL,    NULL, NULL, NULL    ),
 (007, 'Robert',   'Downey',     '1965-04-04', NULL, '11937', 'Brown',  'Brown', NULL, NULL, 'Red'   ),
 (008, 'Susan',    'Downey',     '1973-11-06', NULL, '11937', 'Brown',  'Brown', NULL, NULL, 'Red'   ),
 (009, 'Jonathan', 'Favreau',    '1966-10-19', NULL, '11937', 'Black',  'Brown', NULL, NULL, 'Blue'  ),
 (010, 'Joya',     'Tillem',     NULL,         NULL, '11937', 'Blonde', 'Blue',  NULL, NULL, 'Brown' ),
 (011, 'John',     'Glen',       '1932-05-15', NULL, 'SW4',   'White',  'Brown', NULL, NULL, 'Green' ),
 (012, 'Lewis',    'Gilbert',    '1920-03-06', NULL, 'SW4',   'White',  'Blue',  NULL, NULL, 'Purple'),
 (013, 'Hilda',    'Tafler',     NULL,         NULL, 'SW4',   NULL,     NULL,    NULL, NULL, 'Purple')
;

--Actors--
INSERT INTO Actors (pID, SAGAnniversaryDate)
VALUES
 (001, NULL),
 (007, NULL),
 (009, NULL)
;

--Directors--
INSERT INTO Directors (pID, filmSchoolAttended, DGAnniversaryDate, favLensMaker)
VALUES
 (003, NULL, NULL, NULL),
 (005, NULL, NULL, NULL),
 (007, NULL, NULL, NULL),
 (009, NULL, NULL, NULL),
 (011, NULL, NULL, NULL),
 (012, NULL, NULL, NULL)
;

--Spouses--
INSERT INTO Spouses (pID1, pID2, yearMarried, isStillTogether)
VALUES
 (001, 002, '2002', 'N'),
 (003, 004, NULL,   'N'),
 (005, 006, '1992', 'N'),
 (007, 008, '2004', 'Y'),
 (009, 010, '2000', 'Y'),
 (012, 013, '1951', 'N')
;

--Movies--
INSERT INTO Movies (mID, movieName, MPAARating, releaseDate, domesticBoxOfficeSales,
				   foreignBoxOfficeSales, DVDBlueRaySales)
VALUES
 (001, 'Live and Let Die',            'PG', '1973-06-27', 35377836.00,  126400000.00, NULL        ),
 (002, 'Spice World',                 'PG', '1998-01-23', 29342592.00,  26700000.00,  NULL        ),
 (003, 'Iron Man',                    'PG', '2008-05-02', 318604126.00, 266567421.00, 197995384.00),
 (004, 'The Man with the Golden Gun', 'PG', '1964-12-20', 21000000.00,  76600000.00,  NULL        ),
 (005, 'Octopussy',                   'PG', '1983-06-06', 67900000.00,  119600000.00, NULL	      ),
 (006, 'Moonraker',                   'PG', '1979-06-29', 70300000.00,  140000000.00, NULL        )
;

--Movie Casts--
INSERT INTO MovieCasts (mID, aID, castRole)
VALUES
 (001, 001, 'James Bond' ),
 (002, 001, 'Chief'      ),
 (003, 007, 'Tony Stark' ),
 (003, 009, 'Happy Hogan'),
 (004, 001, 'James Bond' ),
 (005, 001, 'James Bond' ),
 (006, 001, 'James Bond' )
;

--Movie Directors--
INSERT INTO MovieDirectors (mID, dID)
VALUES
 (001, 003),
 (002, 005),
 (003, 009),
 (004, 003),
 (005, 011),
 (006, 012)
;

--------------------------------------------------------------------------------------------------
-- Query to show all the directors with whom actor “Roger Moore” has worked
--------------------------------------------------------------------------------------------------
select distinct p.*, d.filmSchoolAttended, d.DGanniversaryDate,d.favLensMaker
from Movies m inner join MovieDirectors md on m.mID = md.mID
			  inner join Directors d on md.dID = d.pID
			  inner join People p on d.pID = p.pID
			  inner join MovieDirectors mdi on d.pID = mdi.dID 
			  inner join Movies mo on mdi.mID = mo.mID
			  inner join MovieCasts mc on mo.mID = mc.mID
			  inner join Actors a on mc.aID = a.pID
			  inner join People pe on a.pID = pe.pID
			  	where pe.pID = 001;