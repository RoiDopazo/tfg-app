CREATE OR REPLACE TRIGGER ROUTE_DERIVED_TIME
AFTER INSERT OR DELETE OR UPDATE OF TRAVEL_TIME ON STAY
DECLARE    
   PRAGMA AUTONOMOUS_TRANSACTION;   
BEGIN
  UPDATE ROUTE SET TIME = (SELECT SUM(TRAVEL_TIME) FROM STAY WHERE STAY.ROUTE_X_ROUTE = ROUTE.X_ROUTE) + (SELECT SUM(TIME_) FROM STAY WHERE STAY.ROUTE_X_ROUTE = ROUTE.X_ROUTE);
  COMMIT;
END;
/

CREATE OR REPLACE TRIGGER ROUTE_DERIVED_DISTANCE
AFTER INSERT OR DELETE OR UPDATE OF TRAVEL_DISTANCE ON STAY
DECLARE    
   PRAGMA AUTONOMOUS_TRANSACTION;   
BEGIN
  UPDATE ROUTE SET DISTANCE = (SELECT SUM(TRAVEL_DISTANCE) FROM STAY WHERE STAY.ROUTE_X_ROUTE = ROUTE.X_ROUTE);
  COMMIT;
END;
/

CREATE OR REPLACE TRIGGER ROUTE_DERIVED_NUMPLACES
AFTER INSERT OR DELETE ON STAY
DECLARE    
   PRAGMA AUTONOMOUS_TRANSACTION;   
BEGIN
  UPDATE ROUTE SET NUM_PLACES = (SELECT COUNT(*) FROM STAY WHERE STAY.ROUTE_X_ROUTE = ROUTE.X_ROUTE);
  COMMIT;
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