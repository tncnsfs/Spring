
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

-- ��� �����ϴ� ���ΰ��� , ��� ���̺� ���� 
create table tbl_reply(
  rno number(10,0),
  bno number(10,0) not null,
  reply varchar2(1000) not null,
  replyer varchar2(50) not null,
  replyDate date default sysdate,
  updateDate date default sysdate
);

create sequence seq_reply;

alter table tbl_reply add CONSTRAINT pk_reply primary key (rno);

alter table tbl_reply add CONSTRAINT fk_reply_board
foreign key(bno) REFERENCES tbl_board(bno);





