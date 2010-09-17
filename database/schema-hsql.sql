CREATE TABLE T_FEATURECLASS(
  ID  NUMERIC(8,0),
  CLASSCODE VARCHAR(5),
  SHORTDESC VARCHAR(50),
  DESCRIPTION  VARCHAR(255)
);

CREATE TABLE T_CENSUSCODE(
  ID  NUMERIC(8,0),
  STATECODE NUMERIC(2,0),
  COUNTYCODE  NUMERIC(3,0),
  STATENAME VARCHAR(25),
  COUNTYNAME  VARCHAR(25)
);

CREATE TABLE T_BORDERPOINT(
  ID  NUMERIC(12,0),
  STATECODEID NUMERIC(8,0),
  COUNTYCODEID  NUMERIC(8,0),
  LATITUDE  NUMERIC(9,5),
  LONGITUDE NUMERIC(9,5),
  FEATURECLASSID  NUMERIC(8,0)
);