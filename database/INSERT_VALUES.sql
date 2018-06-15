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


INSERT INTO USER_(X_USER, USERNAME, PASSWORD, EMAIL, ROLE) VALUES (1, 'admin2', 'IpKkC+VL58aIPFXeoNDqrQ==', 'admin@mail.com', 'ADMIN');
INSERT INTO USER_(X_USER, USERNAME, PASSWORD, EMAIL, ROLE) VALUES (2, 'mod2', 'IpKkC+VL58aIPFXeoNDqrQ==', 'mod@mail.com', 'MODERATOR');
UPDATE USER_ SET CREATION_DATE = (SELECT SYSDATE FROM DUAL WHERE USER_.X_USER = 1 OR USER_.X_USER = 2);






[{'lat': 42.87968,'lng': -8.54511},{'lat': 42.87903,'lng': -8.54509}, {'lat': 42.87816,'lng': -8.54534},{'lat': 42.87738,'lng': -8.54571},{'lat': 42.87752,'lng': -8.54441}, {'lat': 42.87824,'lng': -8.54385},{'lat': 42.87898,'lng': -8.5435},{'lat': 42.87955,'lng': -8.54333}]