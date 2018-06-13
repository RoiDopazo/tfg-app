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











[{'lat': 42.87968,'lng': -8.54511},{'lat': 42.87903,'lng': -8.54509}, {'lat': 42.87816,'lng': -8.54534},{'lat': 42.87738,'lng': -8.54571},{'lat': 42.87752,'lng': -8.54441}, {'lat': 42.87824,'lng': -8.54385},{'lat': 42.87898,'lng': -8.5435},{'lat': 42.87955,'lng': -8.54333}]