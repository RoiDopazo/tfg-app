
ALTER TABLE CATEGORY_SUB ADD CONSTRAINT FK_SUB_CAT_CATEGORY FOREIGN KEY (CAT_X_CAT) REFERENCES CATEGORY(X_CAT);

// ALTER TABLE PLACE ADD CONSTRAINT FK_SUBC_PLACE FOREIGN KEY (SUBC_X_SUBC) REFERENCES CATEGORY_SUB(X_SUBC);

ALTER TABLE PLACE_COMMENT ADD CONSTRAINT FK_USER_PLACE_COMMENT FOREIGN KEY (USER_X_USER) REFERENCES USER_NORMAL(X_USER);

ALTER TABLE PLACE_COMMENT ADD CONSTRAINT FK_PLACE_PLACE_COMMENT FOREIGN KEY (PLACE_X_PLACE) REFERENCES PLACE(X_PLACE);

ALTER TABLE ROUTE ADD CONSTRAINT FK_USER_ROUTE FOREIGN KEY (USER_X_USER) REFERENCES USER_NORMAL(X_USER);

ALTER TABLE ROUTE_PLACE ADD CONSTRAINT FK_ROUTE_ROUTE_PLACE FOREIGN KEY (ROUTE_X_ROUTE) REFERENCES ROUTE(X_ROUTE);

ALTER TABLE ROUTE_PLACE ADD CONSTRAINT FK_PLACE_ROUTE_PLACE FOREIGN KEY (PLACE_X_PLACE) REFERENCES PLACE(X_PLACE);

ALTER TABLE USER_PLACE ADD CONSTRAINT FK_USER_USER_PLACE FOREIGN KEY (USER_X_USER) REFERENCES USER_NORMAL(X_USER);

ALTER TABLE USER_PLACE ADD CONSTRAINT FK_PLACE_USER_PLACE FOREIGN KEY (PLACE_X_PLACE) REFERENCES PLACE(X_PLACE);

ALTER TABLE DAY ADD CONSTRAINT FK_ROUTE_DAY FOREIGN KEY (ROUTE_X_ROUTE) REFERENCES ROUTE(X_ROUTE);

ALTER TABLE DAY_PLACE ADD CONSTRAINT FK_DAY_DAYPLACE FOREIGN KEY (ROUTE_X_ROUTE, RDAY_X_RDAY) REFERENCES DAY(ROUTE_X_ROUTE, X_RDAY);

ALTER TABLE DAY_PLACE ADD CONSTRAINT FK_PLACE_DAYPLACE FOREIGN KEY (PLACE_X_PLACE) REFERENCES PLACE(X_PLACE);