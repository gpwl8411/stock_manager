
---------------------------------------------------------
--관리자 계정
---------------------------------------------------------
--student 계정 생성
create user student
identified by student
default tablespace users;

grant connect, resource to student;


---------------------------------------------------------
--student 계정
---------------------------------------------------------
-------------------------------------------------------------
--상품 재고관리 프로그램 DB
-------------------------------------------------------------
--상품테이블
create table product_stock(
    product_id varchar2(30) primary key,
    product_name varchar2(30)not null,
    price number(10) not null,
    description varchar2(50),
    stock number default 0
);
--시퀀스
create sequence seq_num
start with 1 --시작값
increment by 1; --증감값
--상품입출고 테이블
create table product_io(
    in_no number primary key,
    product_id varchar2(30),
    iodate date default sysdate,
    amount number,
    status char(1) check (status in('I','O')),
    constraints fk_product_id foreign key(product_id)
                                        references product_stock(product_id)
                                        on delete cascade -- 상품정보 삭제시 io데이터도 삭제되도록
);
--*상품입출고테이블에 데이터가 삽입될때마다, 자동으로 재고테이블의 수량이
--변경될 수 있도록 트리거를 작성.
create or replace trigger trig_insert_product_io
    before insert on product_io
    for each row
begin
    if :new.status = 'I'
    then
        update product_stock set stock = stock + :new.amount where product_id = :new.product_id ;
    else
        update product_stock set stock = stock - :new.amount where product_id = :new.product_id ;
    end if;
end;
/
select * from product_stock;
select * from product_io;
--insert into product_stock  values('nb_ss7','삼성노트북',1570000,'시리즈 7',55);
--insert into product_stock  values('nb_macbook_air','맥북에어',1200000,'애플 울트라북',0);
--insert into product_stock  values('pc_ibm','ibmPC',750000,'windows 8',10);
--commit;
--delete from product_stock;            
            
            
            
            
            
            
            
            
            
            
            
            