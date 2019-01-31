
delete from tbl_board;
commit;

--������ ����
drop SEQUENCE seq_board;
commit;

--������ ����
create sequence seq_board
start with 1 increment BY 1 maxvalue 10000;
commit;


alter table tbl_board add CONSTRAINT pk_board
primary key (bno);


insert into tbl_board(BNO,TITLE,content,writer)
VALUES(1,'����','����','�۰�');


insert into tbl_board (bno, title, content, writer)
(select seq_board.nextval, title, content, writer from tbl_board);
commit;

select /*+ index_asc(tbl_board, pk_board)*/
bno,title,content,writer,regdate,updatedate from tbl_board;

--������ ��ȣ ����ϱ� 
select last_number from SYS.USER_SEQUENCES where SEQUENCE_NAME = 'seq_board';
alter sequence seq_board increment by -787488;




