CREATE TABLE user_details (
  username VARCHAR(50) NOT NULL PRIMARY KEY,
  email VARCHAR(50),
  password VARCHAR(500),
  activated VARCHAR(50),
  activationkey VARCHAR(50),
  resetpasswordkey VARCHAR(50)
);

CREATE TABLE authority (
  name VARCHAR(50) NOT NULL PRIMARY KEY
);

CREATE TABLE user_authority 
   (	username VARCHAR(50) NOT NULL,
	authority VARCHAR(50) NOT NULL
	
   );
alter table user_authority add CONSTRAINT FK_user FOREIGN KEY (username) REFERENCES user_details (username) ;
alter table user_authority add CONSTRAINT FK_authority FOREIGN KEY (authority) REFERENCES authority (name) ;
ALTER TABLE user_authority ADD CONSTRAINT Uniqque_authority_unique UNIQUE (username,authority);

CREATE TABLE oauth_access_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication_id VARCHAR(256) DEFAULT NULL,
  user_name VARCHAR(256) DEFAULT NULL,
  client_id VARCHAR(256) DEFAULT NULL,
  authentication BLOB,
  refresh_token VARCHAR(256) DEFAULT NULL
);

CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token BLOB,
  authentication BLOB
);

CREATE TABLE EMPLOYEE  (
	ID NUMBER(19,0) ,
	NAME VARCHAR2(255 CHAR),
	SALARY FLOAT(126), 
	 PRIMARY KEY (ID)
) ;
CREATE SEQUENCE employee_info_seq START WITH 1 CACHE 1000;
