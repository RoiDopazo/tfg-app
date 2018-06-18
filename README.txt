*******************************************************************************
******************************** GUIA INSTALACION *****************************
*******************************************************************************


· Instalación de software
	- JDK
	- Maven
	- NodeJS
	- Apache tomcat

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
		
		
5. Ejecutar scripts Base de datos (/scripts)

	- sqlplus -S etravel/etravel @{path}/{BD.sql}.
	
	- Lo mismo para el esquema etraveltest
	
	- Se crean dos usuarios de administración (admin y moderator) con password = 1234.
	
	
6. Compilar proyecto Java

	- En ./java/util/src/main/resources existe archivo de configuración de la clave hash para las passwords,
		el contexto usado para el cliente resteasy...

	- Ejecutar "mvn clean install -Dmaven.test.skip=true" ./java/app
	
	
7. Desplegar servidor de datos y aplicación web
	
	- Mover archivos WAR (Generados en el modulo application-resteasy y application-webapp al directorio de tomcat)

	- Configurar en tomcat https si fuera necesario y contextos de despliegue:
		- "/" para webapp-war.
		- "app-rest-service/ para resteasy-war (En http si no se posee certificado de confianza).

		
8. Instalación proyecto Ionic.
	
	- Crear proyecto ionic.
		- ionic start tfg-app blank

	- Copiar los archivos fuente al proyecto creado
		- De ./ionic/tfg-app


















	
	
	