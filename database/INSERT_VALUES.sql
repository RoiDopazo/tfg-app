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











[{'lat':42.8743,'lng':-8.54954},{'lat':42.87446,'lng':-8.54968}, {'lat':42.87454,'lng':-8.54932},{'lat':42.87471,'lng':-8.54922},{'lat':42.87488,'lng':-8.54907},{'lat':42.87503,'lng':-8.54895},
{'lat':42.87518,'lng':-8.54889}, {'lat':42.87528,'lng':-8.54869},{'lat':42.87532,'lng':-8.54836},{'lat':42.87537,'lng':-8.5481},{'lat':42.87542,'lng':-8.54787},{'lat':42.87547,'lng':-8.54767},
{'lat':42.87551,'lng':-8.5474},{'lat':42.87556,'lng':-8.5472},{'lat':42.87562,'lng':-8.54693},{'lat':42.87569,'lng':-8.54664},{'lat':42.87574,'lng': -8.54638}, {'lat':42.87579,'lng':-8.54614},
{'lat':42.87596,'lng':-8.54622},{'lat':42.87612,'lng':-8.54628},  {'lat':42.8763,'lng':-8.54634},{'lat':42.87647,'lng':-8.54642},{'lat':42.87661,'lng':-8.54645}, {'lat':42.8767,'lng':-8.54648},
{'lat':42.87675,'lng':-8.5463},{'lat':42.87679,'lng':-8.54613}, {'lat':42.87681,'lng':-8.5459},{'lat':42.87684,'lng':-8.54574}]