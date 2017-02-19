SELECT * FROM TABS;

CREATE TABLE MEMBER
( SEQ INTEGER PRIMARY KEY
, NAME VARCHAR2(500) NOT NULL
, ID VARCHAR2(500)
, PW VARCHAR2(500)
, AGE INTEGER
, GENDER CHAR(1)
, TELL VARCHAR2(500)
, REGISTER_DATE DATE
, UPDATE_DATE DATE
);

DROP TABLE MEMBER PURGE;


INSERT INTO MEMBER(SEQ, NAME, ID, PW, AGE, GENDER, TELL, REGISTER_DATE, UPDATE_DATE) VALUES('1', '김준성', 'chunkind', 'wnstjd', 30, 'M', '01040633145', SYSDATE, NULL);

DELETE FROM MEMBER;

SELECT SEQ
	 , NAME
	 , ID
	 , PW
	 , AGE
	 , GENDER
	 , TELL
	 , REGISTER_DATE
	 , UPDATE_DATE 
  FROM MEMBER;
