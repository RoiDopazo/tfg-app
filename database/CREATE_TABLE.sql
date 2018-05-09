CREATE TABLE CATEGORY(
	X_CAT	 		NUMBER(5),
	NAME			VARCHAR2(70),
	PLURAL_NAME		VARCHAR2(70),
	ID_FOURSQUARE	VARCHAR2(30) NOT NULL,
	ICON_PREFIX 	VARCHAR2(100),
	ICON_SUFFIX		VARCHAR2(10)
);

ALTER TABLE CATEGORY ADD CONSTRAINT PK_CATEGORY PRIMARY KEY (X_CAT);


CREATE TABLE CATEGORY_SUB (
	X_SUBC 			NUMBER(5),
	NAME			VARCHAR2(70),
	PLURAL_NAME		VARCHAR2(70),
	ID_FOURSQUARE	VARCHAR2(30) NOT NULL,
	ICON_PREFIX 	VARCHAR2(100),
	ICON_SUFFIX		VARCHAR2(10),
	CAT_X_CAT		NUMBER(5)
);

ALTER TABLE CATEGORY_SUB ADD CONSTRAINT PK_CATEGORY_SUB PRIMARY KEY (X_SUBC);


CREATE TABLE USER_ (
	X_USER			NUMBER(5),
	USERNAME		VARCHAR2(16) UNIQUE NOT NULL,
	PASSWORD		VARCHAR2(16) NOT NULL,
	EMAIL			VARCHAR2(30) NOT NULL,
	CREATION_DATE	DATE,
	TOKEN			VARCHAR2(50)
);

ALTER TABLE USER_ ADD CONSTRAINT PK_USER_NORMAL PRIMARY KEY (X_USER);


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


CREATE TABLE ROUTE (
	X_ROUTE			NUMBER(5),
	NAME			VARCHAR2(20),
	PHOTO			VARCHAR2(300),
	STATE			VARCHAR2(12),
	CITY			VARCHAR2(30),
	LAT				DECIMAL(20,17),
	LNG 			DECIMAL(20,17),
	COUNTRY			VARCHAR2(30),
	CREATION_DATE	DATE,
	START_DATE		DATE,
	END_DATE		DATE,
	NUM_DAYS		NUMBER(4),
	NUM_PLACES		NUMBER(4),
	DISTANCE		NUMBER(8),
	TIME			NUMBER(20),
	PRIVATE			NUMBER(1),
	USER_X_USER 	NUMBER(5)
);

ALTER TABLE ROUTE ADD CONSTRAINT PK_ROUTE PRIMARY KEY (X_ROUTE);


CREATE TABLE ROUTE_DAY (
	ROUTE_X_ROUTE	NUMBER(5),
	X_RDAY			NUMBER(5),
	START_TIME		NUMBER(20),
	REAL_TIME_DATA	CLOB
);

ALTER TABLE ROUTE_DAY ADD CONSTRAINT PK_ROUTE_DAY PRIMARY KEY (ROUTE_X_ROUTE, X_RDAY);


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
	PLACE_X_PLACE	NUMBER(5),
	EVEPL_X_EVEPL	NUMBER(5)
);

ALTER TABLE STAY ADD CONSTRAINT PK_STAY PRIMARY KEY (X_STAY);


CREATE TABLE EVENT (
	X_EVENT			NUMBER(5),
	NAME			VARCHAR2(50),
	DESCRIPTION		VARCHAR2(200),
	CITY			VARCHAR2(50),
	START_DATE		DATE,
	END_DATE		DATE
);

ALTER TABLE EVENT ADD CONSTRAINT PK_EVENT PRIMARY KEY (X_EVENT);


CREATE TABLE EVENT_DAY (
	EVENT_X_EVENT	NUMBER(5),
	X_EVDAY			NUMBER(5),
	DATE_			DATE,
	NUM_EV_PLACES	NUMBER(2)
);

ALTER TABLE EVENT_DAY ADD CONSTRAINT PK_EVENT_DAY PRIMARY KEY (EVENT_X_EVENT, X_EVDAY);


CREATE TABLE EVENT_PLACE (
	X_EVEPL			NUMBER(5),	
	EVENT_X_EVENT	NUMBER(5),
	EVDAY_X_EVDAY	NUMBER(5),
	NAME			VARCHAR2(50),
	DESCRIPTION		VARCHAR2(200),
	LAT				DECIMAL(20,17),
	LNG 			DECIMAL(20,17),
	ADDRESS			VARCHAR2(50),
	START_HOUR		NUMBER(20),
	END_HOUR		NUMBER(20)
);

ALTER TABLE EVENT_PLACE ADD CONSTRAINT PK_EVENT_PLACE PRIMARY KEY (X_EVEPL);







INSERT INTO EVENT(X_EVENT, NAME, DESCRIPTION, CITY) VALUES (100, 'Feria Medieval', 'Feria anual santiaguesa en la que podremos revivir las historias y costumbres de los santiagueses y santiaguesas medievales', 'Santiago de Compostela');

INSERT INTO EVENT_DAY (EVENT_X_EVENT, X_EVDAY, DATE_) VALUES (100, 1, TO_DATE('07/07/2018','DD/MM/YYYY'));
INSERT INTO EVENT_DAY (EVENT_X_EVENT, X_EVDAY, DATE_) VALUES (100, 2, TO_DATE('08/07/2018' ,'DD/MM/YYYY'));

INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (100, 100, 1, 'Mercado Medieval', 'Mercado en el que se ofrecerán productos típicos de la época medieval.', 42.8778269, -8.5454613, 'Rúa do Franco', 36000000, 79200000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (101, 100, 2, 'Mercado Medieval', 'Mercado en el que se ofrecerán productos típicos de la época medieval.', 42.8778269, -8.5454613, 'Rúa do Franco', 36000000, 50400000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (102, 100, 1, 'Bailes y Actuaciones', 'Representación de obras y actuaciones de la época.', 42.8744303, -8.5493914, 'Praza Roxa', 64800000, 72000000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (103, 100, 1, 'Exposición de Obras', 'Exponsición de elementos de la época que nos permitirán comprender la vida en está época.', 42.8804151, -8.5457494, 'Praza do Obradoiro', 57600000, 75600000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (104, 100, 2, 'Exposición de Obras', 'Exponsición de elementos de la época que nos permitirán comprender la vida en está época.', 42.8804151, -8.5457494, 'Praza do Obradoiro', 36000000, 50400000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (105, 100, 1, 'Actividades Infantiles', 'Juegos y actividades destinados a los más pequeños.', 42.8768495, -8.547629, 'Parque da Alameda', 59400000, 68400000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (106, 100, 1, 'Justa Medieval', 'Representación de justas medievales con armas y atuendos de la época.', 42.8768495, -8.547629, 'Parque da Alameda', 64800000, 72000000);