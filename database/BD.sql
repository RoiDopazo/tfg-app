DROP TABLE CATEGORY CASCADE CONSTRAINTS;
DROP TABLE CATEGORY_SUB CASCADE CONSTRAINTS;
DROP TABLE USER_ CASCADE CONSTRAINTS;
DROP TABLE PLACE CASCADE CONSTRAINTS;
DROP TABLE ROUTE CASCADE CONSTRAINTS;
DROP TABLE ROUTE_DAY CASCADE CONSTRAINTS;
DROP TABLE EVENT CASCADE CONSTRAINTS;
DROP TABLE EVENT_DAY CASCADE CONSTRAINTS;
DROP TABLE EVENT_PLACE CASCADE CONSTRAINTS;
DROP TABLE STAY CASCADE CONSTRAINTS;



DROP SEQUENCE CATEGORY_SEQ;

DROP SEQUENCE CATEGORY_SUB_SEQ;

DROP SEQUENCE PLACE_SEQ;

DROP SEQUENCE ROUTE_SEQ;

DROP SEQUENCE STAY_SEQ;

DROP SEQUENCE USER_SEQ;

DROP SEQUENCE EVENT_SEQ;

DROP SEQUENCE EVENT_PLACE_SEQ;


CREATE TABLE CATEGORY(
	X_CAT	 		NUMBER(5),
	NAME			VARCHAR2(70),
	PLURAL_NAME		VARCHAR2(70),
	ID_FOURSQUARE	VARCHAR2(30) NOT NULL,
	ICON_PREFIX 	VARCHAR2(300),
	ICON_SUFFIX		VARCHAR2(10)
);

ALTER TABLE CATEGORY ADD CONSTRAINT PK_CATEGORY PRIMARY KEY (X_CAT);


CREATE TABLE CATEGORY_SUB (
	X_SUBC 			NUMBER(5),
	NAME			VARCHAR2(70),
	PLURAL_NAME		VARCHAR2(70),
	ID_FOURSQUARE	VARCHAR2(30) NOT NULL,
	ICON_PREFIX 	VARCHAR2(300),
	ICON_SUFFIX		VARCHAR2(10),
	CAT_X_CAT		NUMBER(5)
);

ALTER TABLE CATEGORY_SUB ADD CONSTRAINT PK_CATEGORY_SUB PRIMARY KEY (X_SUBC);


CREATE TABLE USER_ (
	X_USER			NUMBER(5),
	USERNAME		VARCHAR2(16) UNIQUE NOT NULL,
	PASSWORD		VARCHAR2(150) NOT NULL,
	EMAIL			VARCHAR2(30) NOT NULL,
	CREATION_DATE	DATE,
	TOKEN			VARCHAR2(150),
	ROLE			VARCHAR2(16)
);

ALTER TABLE USER_ ADD CONSTRAINT PK_USER_NORMAL PRIMARY KEY (X_USER);


CREATE TABLE PLACE (
	X_PLACE			NUMBER(5),
	NAME			VARCHAR2(200),
	PHOTO			VARCHAR2(500),
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
	CATEGORY_NAME	VARCHAR2(50),
	CATEGORY_ICON	VARCHAR2(500),
	VERIFIED		NUMBER(1)
);

ALTER TABLE PLACE ADD CONSTRAINT PK_PLACE PRIMARY KEY (X_PLACE);


CREATE TABLE ROUTE (
	X_ROUTE			NUMBER(5),
	PHOTO			VARCHAR2(500),
	STATE			VARCHAR2(12),
	CITY			VARCHAR2(50),
	LAT				DECIMAL(20,17),
	LNG 			DECIMAL(20,17),
	COUNTRY			VARCHAR2(50),
	CREATION_DATE	DATE,
	START_DATE		DATE,
	END_DATE		DATE,
	NUM_DAYS		NUMBER(4),
	NUM_PLACES		NUMBER(4),
	DISTANCE		NUMBER(10),
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
	TRAVEL_DISTANCE	NUMBER(10),
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



CREATE SEQUENCE CATEGORY_SEQ START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCYCLE NOCACHE;

CREATE SEQUENCE CATEGORY_SUB_SEQ START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCYCLE NOCACHE;

CREATE SEQUENCE EVENT_PLACE_SEQ START WITH 8 INCREMENT BY 1 NOMAXVALUE NOCYCLE CACHE 10;

CREATE SEQUENCE EVENT_SEQ START WITH 3 INCREMENT BY 1 NOMAXVALUE NOCYCLE CACHE 10;

CREATE SEQUENCE PLACE_SEQ START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCYCLE CACHE 10;

CREATE SEQUENCE ROUTE_SEQ START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCYCLE CACHE 10;

CREATE SEQUENCE STAY_SEQ START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCYCLE CACHE 10;

CREATE SEQUENCE USER_SEQ START WITH 3 INCREMENT BY 1 NOMAXVALUE NOCYCLE CACHE 10;

ALTER TABLE CATEGORY_SUB ADD CONSTRAINT FK_SUB_CAT_CATEGORY FOREIGN KEY (CAT_X_CAT) REFERENCES CATEGORY(X_CAT);

ALTER TABLE ROUTE ADD CONSTRAINT FK_USER_ROUTE FOREIGN KEY (USER_X_USER) REFERENCES USER_(X_USER);

ALTER TABLE ROUTE_DAY ADD CONSTRAINT FK_ROUTE_ROUTE_DAY FOREIGN KEY (ROUTE_X_ROUTE) REFERENCES ROUTE(X_ROUTE);

ALTER TABLE STAY ADD CONSTRAINT FK_DAY_STAY FOREIGN KEY (ROUTE_X_ROUTE, RDAY_X_RDAY) REFERENCES ROUTE_DAY(ROUTE_X_ROUTE, X_RDAY);

ALTER TABLE STAY ADD CONSTRAINT FK_PLACE_STAY FOREIGN KEY (PLACE_X_PLACE) REFERENCES PLACE (X_PLACE);

ALTER TABLE STAY ADD CONSTRAINT FK_EVENTPLACE_STAY FOREIGN KEY (EVEPL_X_EVEPL) REFERENCES EVENT_PLACE(X_EVEPL);

ALTER TABLE EVENT_DAY ADD CONSTRAINT FK_EVENT_EVENT_DAY	FOREIGN KEY (EVENT_X_EVENT) REFERENCES EVENT(X_EVENT);

ALTER TABLE EVENT_PLACE ADD CONSTRAINT FK_EVENTDAY_EVENT_LOC FOREIGN KEY (EVENT_X_EVENT, EVDAY_X_EVDAY) REFERENCES EVENT_DAY(EVENT_X_EVENT, X_EVDAY);

INSERT INTO EVENT(X_EVENT, NAME, DESCRIPTION, CITY) VALUES (1, 'Feria Medieval', 'Feria anual santiaguesa en la que podremos revivir las historias y costumbres de los santiagueses y santiaguesas medievales', 'Santiago de Compostela');

INSERT INTO EVENT_DAY (EVENT_X_EVENT, X_EVDAY, DATE_) VALUES (1, 1, TO_DATE('07/07/2018','DD/MM/YYYY'));
INSERT INTO EVENT_DAY (EVENT_X_EVENT, X_EVDAY, DATE_) VALUES (1, 2, TO_DATE('08/07/2018' ,'DD/MM/YYYY'));

INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (1, 1, 1, 'Mercado Medieval', 'Mercado en el que se ofrecerán productos típicos de la época medieval.', 42.8778269, -8.5454613, 'Rúa do Franco', 36000000, 79200000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (2, 1, 2, 'Mercado Medieval', 'Mercado en el que se ofrecerán productos típicos de la época medieval.', 42.8778269, -8.5454613, 'Rúa do Franco', 36000000, 50400000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (3, 1, 1, 'Bailes y Actuaciones', 'Representación de obras y actuaciones de la época.', 42.8744303, -8.5493914, 'Praza Roxa', 64800000, 72000000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (4, 1, 1, 'Exposición de Obras', 'Exponsición de elementos de la época que nos permitirán comprender la vida en está época.', 42.8804151, -8.5457494, 'Praza do Obradoiro', 57600000, 75600000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (5, 1, 2, 'Exposición de Obras', 'Exponsición de elementos de la época que nos permitirán comprender la vida en está época.', 42.8804151, -8.5457494, 'Praza do Obradoiro', 36000000, 50400000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (6, 1, 1, 'Actividades Infantiles', 'Juegos y actividades destinados a los más pequeños.', 42.8768495, -8.547629, 'Parque da Alameda', 59400000, 68400000);
INSERT INTO EVENT_PLACE (X_EVEPL, EVENT_X_EVENT, EVDAY_X_EVDAY, NAME, DESCRIPTION, LAT, LNG, ADDRESS, START_HOUR, END_HOUR) VALUES (7, 1, 1, 'Justa Medieval', 'Representación de justas medievales con armas y atuendos de la época.', 42.8768495, -8.547629, 'Parque da Alameda', 64800000, 72000000);


INSERT INTO USER_(X_USER, USERNAME, PASSWORD, EMAIL, ROLE) VALUES (1, 'admin', 'IpKkC+VL58aIPFXeoNDqrQ==', 'admin@mail.com', 'ADMIN');
INSERT INTO USER_(X_USER, USERNAME, PASSWORD, EMAIL, ROLE) VALUES (2, 'mod', 'IpKkC+VL58aIPFXeoNDqrQ==', 'mod@mail.com', 'MODERATOR');
UPDATE USER_ SET CREATION_DATE = (SELECT SYSDATE FROM DUAL WHERE USER_.X_USER = 1 OR USER_.X_USER = 2);


CREATE OR REPLACE TRIGGER ROUTE_DERIVED_TIME
AFTER INSERT OR DELETE OR UPDATE OF TRAVEL_TIME, TIME_ ON STAY
BEGIN
  UPDATE ROUTE SET TIME = (SELECT COALESCE(SUM(TRAVEL_TIME), 0) FROM STAY WHERE STAY.ROUTE_X_ROUTE = ROUTE.X_ROUTE) + (SELECT COALESCE(SUM(TIME_), 0) FROM STAY WHERE STAY.ROUTE_X_ROUTE = ROUTE.X_ROUTE);
END;
/

CREATE OR REPLACE TRIGGER ROUTE_DERIVED_DISTANCE
AFTER INSERT OR DELETE OR UPDATE OF TRAVEL_DISTANCE ON STAY
BEGIN
  UPDATE ROUTE SET DISTANCE = (SELECT COALESCE(SUM(TRAVEL_DISTANCE), 0) FROM STAY WHERE STAY.ROUTE_X_ROUTE = ROUTE.X_ROUTE);
END;
/

CREATE OR REPLACE TRIGGER ROUTE_DERIVED_NUMPLACES
AFTER INSERT OR DELETE ON STAY
BEGIN
  UPDATE ROUTE SET NUM_PLACES = (SELECT COUNT(*) FROM STAY WHERE STAY.ROUTE_X_ROUTE = ROUTE.X_ROUTE);
END;
/


BEGIN
    DBMS_SCHEDULER.CREATE_SCHEDULE (
    	   
        repeat_interval  => 'FREQ=DAILY;BYDAY=MON,TUE,WED,THU,FRI,SAT,SUN;BYHOUR=0;BYMINUTE=0;BYSECOND=1',     
        schedule_name  => '"UPDATE_ROUTE_STATE_SCHEDULE"');
        
END;
/

BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
            job_name => '"ETRAVEL"."UPDATE_ROUTE_STATE_JOB"',
            schedule_name => '"ETRAVEL"."UPDATE_ROUTE_STATE_SCHEDULE"',
            job_type => 'PLSQL_BLOCK',
            job_action => 'BEGIN
FOR route2 IN (SELECT X_ROUTE FROM ETRAVEL.ROUTE WHERE START_DATE is not null AND START_DATE < (SELECT SYSDATE FROM DUAL) AND STATE is not null AND STATE = ''PENDING'') loop 
UPDATE ETRAVEL.ROUTE SET ETRAVEL.ROUTE.STATE = ''IN_PROGRESS'' WHERE ETRAVEL.ROUTE.X_ROUTE = route2.X_ROUTE;
END loop;
COMMIT;
FOR route1 IN (SELECT X_ROUTE FROM ETRAVEL.ROUTE WHERE END_DATE is not null AND END_DATE < (SELECT SYSDATE - 1 FROM DUAL) AND STATE is not null AND STATE = ''IN_PROGRESS'') loop 
UPDATE ETRAVEL.ROUTE SET ETRAVEL.ROUTE.STATE = ''COMPLETED'' WHERE ETRAVEL.ROUTE.X_ROUTE = route1.X_ROUTE;
END loop;
COMMIT;
END;',
            number_of_arguments => 0,
            enabled => FALSE,
            auto_drop => FALSE,
               
            comments => 'Updatear el estado de la ruta en función de su día de inicio y fin');

         
     
 
    DBMS_SCHEDULER.SET_ATTRIBUTE( 
             name => '"ETRAVEL"."UPDATE_ROUTE_STATE_JOB"', 
             attribute => 'logging_level', value => DBMS_SCHEDULER.LOGGING_OFF);
      
  
    
    DBMS_SCHEDULER.enable(
             name => '"ETRAVEL"."UPDATE_ROUTE_STATE_JOB"');
END;
/