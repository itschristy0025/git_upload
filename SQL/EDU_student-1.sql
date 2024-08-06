-- //���ت��
drop table FACILITY_INF
drop table DISTRICT_INF
drop table POLICE_INF

--�m��2 
-- �إ� DISTRICT_INF
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

-- �إ� POLICE_INF
create table POLICE_INF(
POLICE_SERIAL varchar2(10) primary key,
POLICE_NAME nvarchar2(10),
POLICE_ADDRESS nvarchar2(20),
POLICE_TEL varchar2(20)
)
COMMIT;

-- �s�W DISTRICT_INF ���
insert into DISTRICT_INF values ('C001', '�j�H��', '�˫n���q��1035��
', '037581072'),
insert into DISTRICT_INF values ('C002', '�˫n��', '�˫n��˫n�����s��103��
', '037472735'),
insert into DISTRICT_INF values ('C003', '�s�Ψ�', '�˫n��s�Ψ������14��
', '037614186'),
insert into DISTRICT_INF values ('C004', '�H����', '���s��H����������136-1��
', '037724839'),
insert into DISTRICT_INF values ('C005', '�t�]��', '�]�ߥ���]��������766��
', '037333240'),
insert into DISTRICT_INF values ('C006', '���ڨ�', '���ڨ����ڸ�96��
', '037660001'),
insert into DISTRICT_INF values ('C007', '������', '���������j��82��', '037661145
'),
insert into DISTRICT_INF values ('C008', '�H�q��', '�H�q���H�q��53��1��', '037616072
')
-- �s�WPOLICE_INF ���
insert into POLICE_INF values ('M001', '�˫n����', '�]�߿��˫n����ڵ�72��
', '037474796')
insert into POLICE_INF values ('M002', '�]�ߤ���', '�]�߿��]�ߥ������109��
', '037320059')
insert into POLICE_INF values ('M003', '�Y������', '�]�߿��Y����������503��
', '037663004')
COMMIT;

-- �s�W FACILITY_INF ���
insert into FACILITY_INF values ('���J','C001','�]�߿��˫n���H��20��', 100, 1,'M001')
insert into FACILITY_INF values ('�j��','C002','�]�߿��˫n��M����79��', 3142, 1,'M001');
insert into FACILITY_INF values ('�j��','C003','�]�߿��˫n���s�s���T�q142��', 1072, 1,'M001');
insert into FACILITY_INF values ('���@�]�I','C004','�]�߿����s���ظ�1498��', 32, 1,'M001');
insert into FACILITY_INF values ('���J','C005','�]�߿��]�ߥ��̥���80��', 106, 1,'M002');
insert into FACILITY_INF values ('���J','C005','�]�߿��]�ߥ����_��117��', 26, 1,'M002');
insert into FACILITY_INF values ('�j��','C005','�]�߿��]�ߥ��շR��109��', 2038, 2,'M002');
insert into FACILITY_INF values ('�j��','C005','�]�߿��]�ߥ��j�P��53��', 128, 2,'M002');
insert into FACILITY_INF values ('���@�]�I','C006','�]�߿��Y�������ڨ��M����102��', 353, 1,'M003');
insert into FACILITY_INF values ('�p����','C007','�]�߿��Y�������������@��69��', 501, 1,'M003');
insert into FACILITY_INF values ('���J','C008','�]�߿��Y�����H�q��������65��', 194, 1,'M003');
insert into FACILITY_INF values ('�p����','C008','�]�߿��Y�����H�q��������116��', 78, 1,'M003');

SELECT * FROM FACILITY_INF

DELETE FROM POLICE_INF WHERE POLICE_SERIAL = '���J'
COMMIT;
COMMIT;

-- �m��4-1�ק�
select distinct PI.POLICE_NAME as �Һޤ���, PI.POLICE_TEL as �����s���q��
 from STUDENT.FACILITY_INF FI
 left join STUDENT.POLICE_INF PI on FI.POLICE_SERIAL = PI.POLICE_SERIAL
 where FI.PEOPLE > 1000
 
-- �m��4-2 �ק�
select PI.POLICE_NAME as �Һޤ���, PI.POLICE_TEL as �����s���q��, count(PI.POLICE_SERIAL) over (partition by PI.POLICE_SERIAL ) as �����]�I�j��1000�e�H�ƶq���]�I��
from STUDENT.FACILITY_INF FI
left join STUDENT.DISTRICT_INF DI on FI.DISTRICT_SERIAL = DI.DISTRICT_SERIAL
left join STUDENT.POLICE_INF PI on FI.POLICE_SERIAL = PI.POLICE_SERIAL
where FI.PEOPLE > 1000

-- �m��4-3 �ק�
select PI.POLICE_NAME as �Һޤ���, PI.POLICE_TEL as �����s���q��, FI.FACILITY_ADDRESS as �����]�I�a�}, FI.TYPE as ���O, count(PI.POLICE_SERIAL) over (partition by PI.POLICE_SERIAL ) as �����]�I�j��1000�e�H�ƶq���]�I��
from STUDENT.FACILITY_INF FI
left join STUDENT.DISTRICT_INF DI on FI.DISTRICT_SERIAL = DI.DISTRICT_SERIAL
left join STUDENT.POLICE_INF PI on FI.POLICE_SERIAL = PI.POLICE_SERIAL
where FI.PEOPLE > 1000
 
 -- �m��4-4�ק�
select DI.DISTRICT as �����O, FI.FACILITY_ADDRESS as �����]�I�a�}, FI.PEOPLE as �e�H�ƶq, PI.POLICE_NAME as �Һޤ���, PI.POLICE_TEL as �����p���q�� 
 from STUDENT.FACILITY_INF FI
 left join STUDENT.DISTRICT_INF DI on DI.DISTRICT_SERIAL = FI.DISTRICT_SERIAL
 left join STUDENT.POLICE_INF PI on PI.POLICE_SERIAL = FI.POLICE_SERIAL
 where FI.FACILITY_ADDRESS like '%��%';

-- �m��4-5�ק�
select FI.TYPE as ����, DI.DISTRICT as �����O, DI.OFFICE_ADDRESS as �����줽�Ǧ�m, FI.FACILITY_ADDRESS as �����]�I�a�}, FI.PEOPLE as �e�H�ƶq
 from STUDENT.FACILITY_INF FI
 left join STUDENT.DISTRICT_INF DI on FI.DISTRICT_SERIAL = DI.DISTRICT_SERIAL
 where TYPE in ('���J','�j��') 

-- �m��5-1 �ק�
update STUDENT.FACILITY_INF 
set PEOPLE = 5000
where FACILITY_ADDRESS = '�]�߿��˫n��M����79��'
commit;

-- �m��5-2 �ק�
delete from STUDENT.FACILITY_INF
where PEOPLE < 1000
commit;