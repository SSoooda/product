--회원테이블
drop table product;

create table product (
    product_num   number(10),
    product_name  varchar2(40),
    product_cnt   number(10) DEFAULT 0,
    product_pri   number(10) DEFAULT 0
);

--기본키생성
alter table product add Constraint product_product_num_pk primary key (product_num);

--제약조건
alter table product modify product_name constraint product_product_name_nn not null;

--시퀀스
drop sequence product_product_num_seq;
create sequence product_product_num_seq
nocache;

--생성
insert into product (product_num,product_name,product_cnt,product_pri)
values(product_product_num_seq.nextval, '포카칩', 10 , 1500 ) ;
commit;

--전체조회
select product_num ,
       product_name,
       product_cnt,
       product_pri
from   product ;

--조회
select product_num ,
       product_name,
       product_cnt,
       product_pri
from   product
where  product_num = 1 ;

--삭제
delete product
where product_num = 1;
commit;

--수정
update product
set product_name = '가루비',
    product_cnt = 10,
    product_pri = 2000
where product_num = 1 ;
commit;