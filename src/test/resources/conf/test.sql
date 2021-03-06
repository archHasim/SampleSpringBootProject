  drop table ACCOUNT  if exists;
  CREATE TABLE ACCOUNT  (
	ID NUMBER(19,0) ,
	SORT_CODE NUMBER(19,0),
	 PRIMARY KEY (ID)
) ;
CREATE SEQUENCE ACCOUNT_seq START WITH 1 CACHE 1000;

  CREATE TABLE TRANSACTION  (
	ID NUMBER(19,0) NOT NULL ENABLE, 
	BANK_TRAN_CODE VARCHAR2(255 CHAR), 
	BANK_TRAN_SUB_CODE VARCHAR2(255 CHAR), 
	BOOKED_DATE_TIME TIMESTAMP (6), 
	CREDIT_DEBIT_INDICATOR VARCHAR2(255 CHAR), 
	INTER_BOOKED_BAL_AMT FLOAT(126), 
	INTER_BOOKED_BAL_CURR_CODE VARCHAR2(255 CHAR), 
	INTER_BOOKED_CR_DB_IND VARCHAR2(255 CHAR), 
	MERCHANT_CATEGORY_CODE VARCHAR2(255 CHAR), 
	MERCHANT_NAME VARCHAR2(255 CHAR), 
	PERMANENT_ACCOUNT_NUMBER VARCHAR2(255 CHAR), 
	POSTED_DATE_TIME TIMESTAMP (6), 
	PROPRIETARY_TRAN_CODE VARCHAR2(255 CHAR), 
	PROPRIETARY_TRAN_ISSUER VARCHAR2(255 CHAR), 
	TRANSACTION_AMOUNT FLOAT(126), 
	TRANSACTION_CURR_CODE VARCHAR2(255 CHAR), 
	TRAN_DESCRIPTION VARCHAR2(255 CHAR), 
	TRANSACTION_STATUS VARCHAR2(255 CHAR), 
	TYPE VARCHAR2(255 CHAR), 
	ACCOUNT_ID NUMBER(19,0), 
	 PRIMARY KEY (ID),
  
	 CONSTRAINT FK60OGQ0GA4X4Y0FKEU24TGM0KV FOREIGN KEY (ACCOUNT_ID)
	  REFERENCES ACCOUNT (ID) 
) ;
CREATE SEQUENCE TRANSACTION_seq START WITH 1 CACHE 1000;