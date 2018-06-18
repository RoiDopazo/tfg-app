*******************************************************************************
******************************** GUIA INSTALACION *****************************
*******************************************************************************


· Instalación de software
	- JDK
	- Maven
	- NodeJS

1. Descargar e instalar gestor de base de datos Oracle.
	-http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html
	
2. Establecer varibles de entorno
	- Nombre: JAVA_HOME
      Valor: C:\Program Files\Java\jdk1.8.0_92
    - Nombre: MAVEN_HOME
      Valor: C:\Program Files\Java\apache-maven-3.3.9
    - Nombre: MAVEN_OPTS
      Valor: -Xms512m -Xmx1024m
	  
	  
3. Añadir librería Foursquare modificada al repositorio maven local.
	
	Copiar /fsapi/1.0.3 a la ruta .m2/repository/fi/foyt/foursquare-api/
	
	  
4. Crear usurios en la base de datos
	- create user etravel identified by etravel;
	- create user etraveltest identified by etraveltest;
	
	* Asginar permisos.
	-grant CREATE SESSION, ALTER SESSION, CREATE DATABASE LINK, 
		CREATE MATERIALIZED VIEW, CREATE PROCEDURE, CREATE PUBLIC SYNONYM, CREATE ROLE, 
		CREATE SEQUENCE, CREATE SYNONYM, CREATE TABLE, CREATE TRIGGER, CREATE TYPE, CREATE VIEW, 
		UNLIMITED TABLESPACE, SCHEDULER_ADMIN, CREATE JOB, MANAGE SCHEDULER to etravel;
		
	-grant CREATE SESSION, ALTER SESSION, CREATE DATABASE LINK, 
		CREATE MATERIALIZED VIEW, CREATE PROCEDURE, CREATE PUBLIC SYNONYM, CREATE ROLE, 
		CREATE SEQUENCE, CREATE SYNONYM, CREATE TABLE, CREATE TRIGGER, CREATE TYPE, CREATE VIEW, 
		UNLIMITED TABLESPACE to etraveltest;
		
4. Ejecutar scripts Base de datos (/scripts)