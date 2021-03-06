CREATE TABLE ALLOWED_IP
( SEQ INTEGER PRIMARY KEY
, CODE VARCHAR2(1000) NOT NULL
, NAME VARCHAR2(1000) NOT NULL
, IP VARCHAR2(1000) 
);

INSERT INTO ALLOWED_IP(SEQ, CODE, NAME, IP) VALUES(1, 'CD001', '우리집', '192.168.10.1');
INSERT INTO ALLOWED_IP(SEQ, CODE, NAME, IP) VALUES(2, 'CD002', '이룸스터디', '192.168.10.1');

SELECT * FROM ALLOWED_IP;

SELECT IP FROM ALLOWED_IP WHERE CODE = 'CD002';

DELETE FROM ALLOWED_IP;

DROP TABLE ALLOWED_IP PURGE;