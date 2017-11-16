CREATE TABLE CATEGORY(
	X_CAT	 		NUMBER(5),
	NAME			VARCHAR2(50) UNIQUE,
	ID_FOURSQUARE	VARCHAR2(30) UNIQUE NOT NULL
);

ALTER TABLE CATEGORY ADD CONSTRAINT PK_CATEGORY PRIMARY KEY (X_CAT);


CREATE TABLE SUB_CATEGORY (
	X_SUBC 			NUMBER(5),
	NAME			VARCHAR2(50) UNIQUE,
	ID_FOURSQUARE	VARCHAR2(30) UNIQUE NOT NULL,
	CAT_X_CAT		NUMBER(5)
);

ALTER TABLE CATEGORY_SUB ADD CONSTRAINT PK_SUB_CATEGORY PRIMARY KEY (X_SUBC);


CREATE TABLE USER (
	X_USER			NUMBER(5),
	NAME			VARCHAR2(16) UNIQUE NOT NULL,
	PASSWORD		VARCHAR2(16) NOT NULL,
	EMAIL			VARCHAR2(30) NOT NULL,
	CREATION_DATE	DATE,
	TOKEN			VARCHAR2(50)
);

ALTER TABLE USER_NORMAL ADD CONSTRAINT PK_USER_NORMAL PRIMARY KEY (X_USER);


CREATE TABLE PLACE (
	X_PLACE			NUMBER(5),
	NAME			VARCHAR2(50),
	PHOTO			VARCHAR2(300),
	LAT				DECIMAL(20,17),
	LNG 			DECIMAL(20,17),
	ADDRESS			VARCHAR2(80),
	CC				VARCHAR2(10),
	CITY			VARCHAR2(50),
	COUNTRY			VARCHAR2(50),
	POSTAL_CODE		VARCHAR2(16),
	PROVINCE		VARCHAR2(50),
	EMAIL			VARCHAR2(40),
	FACEBOOK		VARCHAR2(50),
	PHONE			VARCHAR2(20),
	TWITTER			VARCHAR2(50),
	ID_FOURSQUARE	VARCHAR2(40) UNIQUE NOT NULL,
	CATEGORY_NAME	VARCHAR2(40),
	CATEGORY_ICON	VARCHAR2(300),
	VERIFIED		NUMBER(1)
);

ALTER TABLE PLACE ADD CONSTRAINT PK_PLACE PRIMARY KEY (X_PLACE);


CREATE TABLE PLACE_COMMENT (
	X_COMM			NUMBER(5),
	TITLE			VARCHAR2(20) NOT NULL, 
	BODY			VARCHAR2(300) NOT NULL,
	STATE			VARCHAR2(10) NOT NULL,
	REASON			VARCHAR2(100),
	USER_X_USER		NUMBER(5),
	PLACE_X_PLACE	NUMBER(5)
); 

ALTER TABLE PLACE_COMMENT ADD CONSTRAINT PK_PLACE_COMMENT PRIMARY KEY (X_COMM);


CREATE TABLE ROUTE (
	X_ROUTE			NUMBER(5),
	NAME			VARCHAR2(20),
	PHOTO			VARCHAR2(300),
	STATE			VARCHAR2(12),
	CITY			VARCHAR2(30),
	COUNTRY			VARCHAR2(30),
	CREATION_DATE	DATE,
	START_DATE		DATE,
	END_DATE		DATE,
	NUM_DAYS		NUMBER(4),
	NUM_PLACES		NUMBER(4),
	DISTANCE		NUMBER(8),
	TIME			NUMBER(20),
	USER_X_USER 	NUMBER(5)
);

ALTER TABLE ROUTE ADD CONSTRAINT PK_ROUTE PRIMARY KEY (X_ROUTE);


CREATE TABLE DAY (
	ROUTE_X_ROUTE	NUMBER(5),
	X_RDAY			NUMBER(5),
	NAME			VARCHAR2(30),
	ORDER_			NUMBER(2),
	START_TIME		NUMBER(20)
);

ALTER TABLE DAY ADD CONSTRAINT PK_DAY PRIMARY KEY (ROUTE_X_ROUTE, X_RDAY);


CREATE TABLE STAY (
	X_STAY			NUMBER(5),
	ROUTE_X_ROUTE	NUMBER(5),
	RDAY_X_RDAY		NUMBER(5),
	ORDER_	 		NUMBER(2),
	TIME_			NUMBER(20),
	TRAVEL_DISTANCE	NUMBER(8),
	TRAVEL_TIME		NUMBER(20),
	TRAVEL_MODE		VARCHAR2(12),
	TYPE			VARCHAR2(2),
	PLACE_X_PLACE	NUMBER(5)
);

ALTER TABLE STAY ADD CONSTRAINT PK_STAY PRIMARY KEY (X_STAY);


CREATE TABLE EVENT (
	X_EVENT			NUMBER(5),
	NAME			VARCHAR2(50),
	DESCRIPTION		VARCHAR2(200),
	CITY			VARCHAR2(50),
	START_DATE		DATE,
	END_DATE		DATE,
);

ALTER TABLE EVENT ADD CONSTRAINT PK_EVENT PRIMARY KEY (X_EVENT);


CREATE TABLE EVENT_LOC (
	X_EVLOC			NUMBER(5),	
	EVENT_X_EVENT	NUMBER(5),
	NAME			VARCHAR2(50),
	DESCRIPTION		VARCHAR2(200),
	LAT				DECIMAL(20,17),
	LNG 			DECIMAL(20,17),
	ADDRESS			VARCHAR2(50),
	HOUR_INIT		TIMESTAMP,
	HOUR_END		TIMESTAMP
);

ALTER TABLE EVENT_LOC ADD CONSTRAINT PK_EVENT_LOC PRIMARY KEY (X_EVLOC);


CREATE TABLE USER_PLACE (
	X_UP			NUMBER(5),
	USER_X_USER		NUMBER(5),
	PLACE_X_PLACE	NUMBER(5),
	STATE			VARCHAR2(16),
	CITY			VARCHAR2(30)
);

ALTER TABLE USER_PLACE ADD CONSTRAINT PK_USER_PLACE PRIMARY KEY (X_UP);


