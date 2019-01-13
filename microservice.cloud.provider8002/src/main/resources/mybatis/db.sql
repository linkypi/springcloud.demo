use cloud_db1;
create table dept(
 deptno int not null AUTO_INCREMENT,
 dname VARCHAR(60),
db_source VARCHAR(50),
PRIMARY key(deptno)
);

INSERT into dept(dname,db_source)values
('开发部',DATABASE()),
('人事部',DATABASE()),
('财务部',DATABASE()),
('运营部',DATABASE()),
('市场部',DATABASE());

SELECT * from dept;