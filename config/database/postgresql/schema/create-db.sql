CREATE TABLE T_USER
(
  ID BIGSERIAL PRIMARY KEY,
  NAME VARCHAR(255) NOT NULL UNIQUE,
  PASSWORD VARCHAR(255) NOT NULL,
  ENABLED BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE T_MEMBER
(
  ID BIGSERIAL PRIMARY KEY,
  LAST_NAME VARCHAR(50),
  FIRST_NAME VARCHAR(50),
  CHINESE_LAST_NAME VARCHAR(50),
  CHINESE_FIRST_NAME VARCHAR(50),
  GENDER VARCHAR(10),
  INTRODUCER_NAME VARCHAR(50),
  MEMBERSHIP_ACQUISITION_DATE DATE,
  MEMBERSHIP_ACQUISITION_LUNAR_DATE VARCHAR(100),
  MEMBERSHIP_ACQUISITION_TEMPLE_CODE VARCHAR(3),
  MEMBERSHIP_ACQUISITION_CITY_CODE VARCHAR(3),
  MEMBER_PURIFICATION_DATE DATE,
  MEMBER_FAMILY_TEMPLE_DATE DATE,
  MASTER_NAME VARCHAR(50),
  GUARANTOR_NAME VARCHAR(100),
  GROUP_NUMBER INTEGER,
  DOB DATE,
  LAST_UPDATE_USER_ID varchar(50),
  LAST_UPDATE_DATE DATE
);
CREATE INDEX I_MEMBER__LAST_NAME__FIRST_NAME ON T_MEMBER(LAST_NAME, FIRST_NAME);
CREATE INDEX I_MEMBER__CHINESE_LAST_NAME__CHINESS_FIRST_NAME ON T_MEMBER(CHINESE_LAST_NAME, CHINESE_FIRST_NAME);
CREATE INDEX I_MEMBER__MEMBERSHIP_ACQUISITION_DATE ON T_MEMBER(MEMBERSHIP_ACQUISITION_DATE);
CREATE INDEX I_MEMBER__INTRODUCER_NAME ON T_MEMBER(INTRODUCER_NAME);


CREATE TABLE T_MEMBER_CONTACT
(
  MEMBER_ID BIGINT NOT NULL REFERENCES T_MEMBER(ID),
  HOME_PHONE VARCHAR(100),
  MOBILE_PHONE VARCHAR(100),
  EMAIL VARCHAR(100),
  ADDRESS_LINE1 VARCHAR(255),
  ADDRESS_LINE2 VARCHAR(255),
  SUBURB VARCHAR(255),
  STATE VARCHAR(50),
  POSTCODE VARCHAR(10)
);
CREATE INDEX I__MEMBER_CONTACT__MEMBER_ID ON T_MEMBER_CONTACT(MEMBER_ID);


CREATE TABLE T_MEMBER_NOTE
(
  NOTE_ID BIGSERIAL NOT NULL PRIMARY KEY,
  MEMBER_ID BIGINT NOT NULL REFERENCES T_MEMBER(ID),
  NOTE TEXT NOT NULL
);
CREATE INDEX I__MEMBER_NOTE__MEMBER_ID ON T_MEMBER_NOTE(MEMBER_ID);


CREATE TABLE T_TEMPLE
(
  MEMBERSHIP_ACQUISITION_TEMPLE_CODE VARCHAR(5) PRIMARY KEY,
  MEMBERSHIP_ACQUISITION_TEMPLE_NAME VARCHAR(255)
);

CREATE TABLE T_CITY
(
  MEMBERSHIP_ACQUISITION_CITY_CODE VARCHAR(5) PRIMARY KEY,
  MEMBERSHIP_ACQUISITION_CITY_NAME VARCHAR(255)
);