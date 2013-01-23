INSERT INTO t_dept VALUES ('100','培训中心');

insert into t_user (id, login_name, name, password, salt, roles, phone_No, email,dept_Id) values(1,'admin','Admin','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','admin',null,null,100);
insert into t_user (id, login_name, name, password, salt, roles, phone_No, email,dept_Id) values(2,'user','Calvin','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','user',null,null,100);

