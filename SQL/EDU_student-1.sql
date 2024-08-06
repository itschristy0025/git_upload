-- //重建表格
drop table FACILITY_INF
drop table DISTRICT_INF
drop table POLICE_INF

--練習2 
-- 建立 DISTRICT_INF
 create table DISTRICT_INF(
 DISTRICT_SERIAL varchar2(10) primary key,
 DISTRICT nvarchar2(10),
 OFFICE_ADDRESS nvarchar2(20),
 OFFICE_TEL varchar2(20)
 )
COMMIT;

create table FACILITY_INF(
TYPE nvarchar2(20),
DISTRICT_SERIAL varchar2(20),
FACILITY_ADDRESS nvarchar2(50),
PEOPLE number,
LAYER number,
POLICE_SERIAL varchar2(20)
)

-- 建立 POLICE_INF
create table POLICE_INF(
POLICE_SERIAL varchar2(10) primary key,
POLICE_NAME nvarchar2(10),
POLICE_ADDRESS nvarchar2(20),
POLICE_TEL varchar2(20)
)
COMMIT;

-- 新增 DISTRICT_INF 資料
insert into DISTRICT_INF values ('C001', '大埔里', '竹南鎮公義路1035號
', '037581072'),
insert into DISTRICT_INF values ('C002', '竹南里', '竹南鎮竹南里中山路103號
', '037472735'),
insert into DISTRICT_INF values ('C003', '山佳里', '竹南鎮山佳里國光街14號
', '037614186'),
insert into DISTRICT_INF values ('C004', '埔頂里', '後龍鎮埔頂里中興路136-1號
', '037724839'),
insert into DISTRICT_INF values ('C005', '緣苗里', '苗栗市綠苗里中正路766號
', '037333240'),
insert into DISTRICT_INF values ('C006', '民族里', '民族里民族路96號
', '037660001'),
insert into DISTRICT_INF values ('C007', '忠孝里', '忠孝里光大街82號', '037661145
'),
insert into DISTRICT_INF values ('C008', '信義里', '信義里信義路53巷1號', '037616072
')
-- 新增POLICE_INF 資料
insert into POLICE_INF values ('M001', '竹南分局', '苗栗縣竹南鎮民族街72號
', '037474796')
insert into POLICE_INF values ('M002', '苗栗分局', '苗栗縣苗栗市金鳳街109號
', '037320059')
insert into POLICE_INF values ('M003', '頭份分局', '苗栗縣頭份市中興路503號
', '037663004')
COMMIT;

-- 新增 FACILITY_INF 資料
insert into FACILITY_INF values ('公寓','C001','苗栗縣竹南鎮中埔街20號', 100, 1,'M001')
insert into FACILITY_INF values ('大樓','C002','苗栗縣竹南鎮和平街79號', 3142, 1,'M001');
insert into FACILITY_INF values ('大樓','C003','苗栗縣竹南鎮龍山路三段142號', 1072, 1,'M001');
insert into FACILITY_INF values ('公共設施','C004','苗栗縣後龍鎮中華路1498號', 32, 1,'M001');
insert into FACILITY_INF values ('公寓','C005','苗栗縣苗栗市米市街80號', 106, 1,'M002');
insert into FACILITY_INF values ('公寓','C005','苗栗縣苗栗市光復路117號', 26, 1,'M002');
insert into FACILITY_INF values ('大樓','C005','苗栗縣苗栗市博愛街109號', 2038, 2,'M002');
insert into FACILITY_INF values ('大樓','C005','苗栗縣苗栗市大同路53號', 128, 2,'M002');
insert into FACILITY_INF values ('公共設施','C006','苗栗縣頭份市民族里和平路102號', 353, 1,'M003');
insert into FACILITY_INF values ('私營單位','C007','苗栗縣頭份市忠孝忠孝一路69號', 501, 1,'M003');
insert into FACILITY_INF values ('公寓','C008','苗栗縣頭份市信義里中正路65號', 194, 1,'M003');
insert into FACILITY_INF values ('私營單位','C008','苗栗縣頭份市信義里中正路116號', 78, 1,'M003');

SELECT * FROM FACILITY_INF

DELETE FROM POLICE_INF WHERE POLICE_SERIAL = '公寓'
COMMIT;
COMMIT;

-- 練習4-1修改
select distinct PI.POLICE_NAME as 轄管分局, PI.POLICE_TEL as 分局連絡電話
 from STUDENT.FACILITY_INF FI
 left join STUDENT.POLICE_INF PI on FI.POLICE_SERIAL = PI.POLICE_SERIAL
 where FI.PEOPLE > 1000
 
-- 練習4-2 修改
select PI.POLICE_NAME as 轄管分局, PI.POLICE_TEL as 分局連絡電話, count(PI.POLICE_SERIAL) over (partition by PI.POLICE_SERIAL ) as 避難設施大於1000容人數量的設施數
from STUDENT.FACILITY_INF FI
left join STUDENT.DISTRICT_INF DI on FI.DISTRICT_SERIAL = DI.DISTRICT_SERIAL
left join STUDENT.POLICE_INF PI on FI.POLICE_SERIAL = PI.POLICE_SERIAL
where FI.PEOPLE > 1000

-- 練習4-3 修改
select PI.POLICE_NAME as 轄管分局, PI.POLICE_TEL as 分局連絡電話, FI.FACILITY_ADDRESS as 避難設施地址, FI.TYPE as 類別, count(PI.POLICE_SERIAL) over (partition by PI.POLICE_SERIAL ) as 避難設施大於1000容人數量的設施數
from STUDENT.FACILITY_INF FI
left join STUDENT.DISTRICT_INF DI on FI.DISTRICT_SERIAL = DI.DISTRICT_SERIAL
left join STUDENT.POLICE_INF PI on FI.POLICE_SERIAL = PI.POLICE_SERIAL
where FI.PEOPLE > 1000
 
 -- 練習4-4修改
select DI.DISTRICT as 村里別, FI.FACILITY_ADDRESS as 避難設施地址, FI.PEOPLE as 容人數量, PI.POLICE_NAME as 轄管分局, PI.POLICE_TEL as 分局聯絡電話 
 from STUDENT.FACILITY_INF FI
 left join STUDENT.DISTRICT_INF DI on DI.DISTRICT_SERIAL = FI.DISTRICT_SERIAL
 left join STUDENT.POLICE_INF PI on PI.POLICE_SERIAL = FI.POLICE_SERIAL
 where FI.FACILITY_ADDRESS like '%中%';

-- 練習4-5修改
select FI.TYPE as 類型, DI.DISTRICT as 村里別, DI.OFFICE_ADDRESS as 村里辦公室位置, FI.FACILITY_ADDRESS as 避難設施地址, FI.PEOPLE as 容人數量
 from STUDENT.FACILITY_INF FI
 left join STUDENT.DISTRICT_INF DI on FI.DISTRICT_SERIAL = DI.DISTRICT_SERIAL
 where TYPE in ('公寓','大樓') 

-- 練習5-1 修改
update STUDENT.FACILITY_INF 
set PEOPLE = 5000
where FACILITY_ADDRESS = '苗栗縣竹南鎮和平街79號'
commit;

-- 練習5-2 修改
delete from STUDENT.FACILITY_INF
where PEOPLE < 1000
commit;