SELECT * FROM TABS;

CREATE TABLE MEMBER
( USER_ID VARCHAR2(500) PRIMARY KEY
, USER_PW VARCHAR2(500) NOT NULL
);

CREATE TABLE MEMBER_INFO
( USER_ID VARCHAR2(500) PRIMARY KEY
, USER_NAME VARCHAR2(500) NOT NULL
);

DROP TABLE MEMBER PURGE;

INSERT INTO MEMBER VALUES('chunkind','wnstjd88');
INSERT INTO MEMBER VALUES('ksb3145','toqha12');
INSERT INTO MEMBER VALUES('ohs88','ohs');
INSERT INTO MEMBER VALUES('lgb88','lgb');
INSERT INTO MEMBER VALUES('lsc88','lsc');
INSERT INTO MEMBER VALUES('cmj88','cmj');
INSERT INTO MEMBER VALUES('bmh88','bmh');

/* MEMBER_INFO TABLE DATA INSERT */
INSERT INTO MEMBER_INFO VALUES('chunkind','김준성');
INSERT INTO MEMBER_INFO VALUES('ksb3145','김새봄');
INSERT INTO MEMBER_INFO VALUES('ohs88','오형석');
INSERT INTO MEMBER_INFO VALUES('lgb88','이기백');
INSERT INTO MEMBER_INFO VALUES('lsc88','이상철');
INSERT INTO MEMBER_INFO VALUES('cmj88','최민재');
INSERT INTO MEMBER_INFO VALUES('bmh88','박민호');

DELETE FROM MEMBER;

DELETE FROM MEMBER_INFO;

SELECT * FROM MEMBER;

SELECT * FROM MEMBER_INFO;






/* MEMBER 와 MEMBER_INFO를 조인해서 USER_ID, USER_PASS, USER_NAME 가저오기 */

SELECT A.USER_ID			AS userId
	 , A.USER_PW			AS userPw
	 , B.USER_NAME		AS userNm
  FROM MEMBER A, MEMBER_INFO B
 WHERE A.USER_ID = B.USER_ID;

 
 select * from member2;






