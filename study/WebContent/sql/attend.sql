CREATE TABLE ATTEND
( ID VARCHAR2(500) NOT NULL
, CHECK_DATE DATE NOT NULL
);


DROP TABLE ATTEND PURGE;


SELECT ID, CHECK_DATE FROM ATTEND;

INSERT INTO ATTEND(ID, CHECK_DATE) VALUES('chunkind', SYSDATE);

DELETE FROM ATTEND;

select * from attend;


delete from ATTEND where rownum = 1;