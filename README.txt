*******************************************************************************
******************************** GUIA INSTALACION *****************************
*******************************************************************************


· Instalación de software
	- JDK
	- Maven
	- NodeJS
	- Cordova
	- Ionic CLI
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

		
8. Configurar entorno proyecto Ionic.

	- Crear proyecto.
		- ionic start <<nombre del proyecto>> blank.
		
	- Añadir código fuente al proyecto.
		- Copiar ionic/ionic-app/src/ a <<nombre del proyecto>>/src
		
	- Añadir plugins nativos (Abrir consola de comandos en el raiz del proeycto creado).
		- ion2-calendar
			$ npm install ion2-calendar moment --save
		
		- native/toast
			$ ionic cordova plugin add cordova-plugin-x-toast
			$ npm install --save @ionic-native/toast
			
		- native/storage
			$ ionic cordova plugin add cordova-plugin-nativestorage
			$ npm install --save @ionic-native/native-storage
		
		- native GoogleMaps*
			$ ionic cordova plugin add cordova-plugin-googlemaps --variable API_KEY_FOR_ANDROID="AIzaSyCbCF71fXfmPZUR5ZMjkTyCG2EfR4IAEuU" --variable API_KEY_FOR_IOS="AIzaSyCbCF71fXfmPZUR5ZMjkTyCG2EfR4IAEuU"
			$ npm install --save @ionic-native/google-maps
		
		- background geolocation
			$ ionic cordova plugin add cordova-plugin-geolocation
			$ npm install --save @ionic-native/geolocation
			$ ionic cordova plugin add cordova-plugin-mauron85-background-geolocation
			$ npm install --save @ionic-native/background-geolocation

	- Especificar en "./src/providers/service/config.ts" la url de conexión con el servicio de datos.
		Por defecto -> http://192.168.0.20:8080/app-rest-service -> Especificar IP de la máquina que desplegará el servicio.
		
	
	- Añadir plataformas
		$ ionic cordova platform add android
		$ ionic cordova platform add ios
		
		
	*Para despleguar la aplicación en iOs es necesario estar subscrito a Apple iOs Developer Program (99$).
	- Despliegue en Android:
		- Si está la depuración USB activada y se dispone de Android SDK tools:
			- Run (Con el dispositivo conectado mediante USB): 
				$ ionic cordova run android
			
		- Si se quiere construir el apk:
			- Run:
				$ cordova build --release android
				$ cd platforms\android\build\outputs\apk
				$ keytool -genkey -v -keystore my-release-key.keystore -alias alias_name -keyalg RSA -keysize 2048 -validity 10000
				$ jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore my-release-key.keystore android-release-unsigned.apk alias_name
				$ zipalign -v 4 android-release-unsigned.apk tfg-app.apk
				
	
#### A mayores, se incluye una carpeta con los archivos compilados y listos para su despliegue. Simplemente incorporar los .war en el directorio de tomcat y desplegar, y instalar el .apk incluido en un dispositivo android. 
	(Este .apk incluye una funcionalidad en la ventana de login, en la parte superior derecha, que permite modificar dinámicamente la IP del servicio de datos, sin necesidad de recompilar la aplicación)
	
	
* Las APIs empleadas utilizan keys privadas del alumno para la realización del desarrollo. 
Estas claves pueden dejar de ser válidas, por lo que sería necesario reemplezarlas por unas propias en el código fuente de la aplicación.

	Directorio de las API:
	
		- APIs de FS y GM del lado servidor.
			# java/app/model/core/src/main/resources/../spring/spring-config.xml
			
		-APIs JS cliente
			# java/app/application/webapp/src/main/webapp/WEB-INF/templates/fragments/app/routedetailfrag.html
			# ionic/ionic-app/src/index.html
			# ionic/ionic-app/src/providers/services/config.ts
		
		-API nativa google maps Ionic
			# ionic/ionic-app/platforms/android/AndroidManifest.xml (API Nativa de Android)





