CREATE TABLE T_STATE(
  ID  NUMERIC(8,0) GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) PRIMARY KEY,
    STATENAME VARCHAR(25),
    STATEABBR VARCHAR(2),
    STATEFIPS NUMERIC(3,0),
    CONSTRAINT unq_state_name UNIQUE(STATENAME),
    CONSTRAINT unq_state_abbr UNIQUE(STATEABBR)
);

CREATE TABLE T_TIGERFEATURETYPE(
  ID  NUMERIC(4,0) GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) PRIMARY KEY,
        CLASSCODE  VARCHAR(5),
        DESCRIPTION VARCHAR(25),
        CONSTRAINT unq_tft_desc UNIQUE(DESCRIPTION)
);

CREATE TABLE T_CENSUSCLASSCODE(
    ID  NUMERIC(4,0) GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) PRIMARY KEY,
        CLASSCODE   VARCHAR(2),
        DESCRIPTION   VARCHAR(255)
);

CREATE TABLE T_BORDERPOINT(
  ID  BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) PRIMARY KEY,
        STATECODEID INTEGER,
        LATITUDE  DECIMAL(10,5) NOT NULL,
        LONGITUDE DECIMAL(10,5) NOT NULL,
        CONSTRAINT fk_brd_state FOREIGN KEY(STATECODEID) REFERENCES T_STATECODE(ID),
);

CREATE TABLE T_STATECODE(
    ID  INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) PRIMARY KEY,
        STATECODE INTEGER,
        STATENAME  VARCHAR(25),
        STATEABBR VARCHAR(3)
);

CREATE TABLE T_SUBCODE(
    ID  INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) PRIMARY KEY,
    STATECODEID INTEGER,
    SUBCODE INTEGER,
    SUBCODEDESCRIPTION  VARCHAR(25),
    SUBCODETYPE VARCHAR(25),
    CONSTRAINT fk_sub_state FOREIGN KEY(STATECODEID) REFERENCES T_STATECODE(ID)
);
