delete from t_task;
delete from t_user;
delete from t_dept;

INSERT INTO t_dept VALUES ('100','培训中心');

insert into t_user (id, login_name, name, password, salt, roles, phone_No, email,dept_Id) values(1,'admin','Admin','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','admin',null,null,100);
insert into t_user (id, login_name, name, password, salt, roles, phone_No, email,dept_Id) values(2,'user','Calvin','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','user',null,null,100);

insert into t_task (id, title, description, user_id) values(1, 'Study PlayFramework 2.0','http://www.playframework.org/', 2);
insert into t_task (id, title, description, user_id) values(2, 'Study Grails 2.0','http://www.grails.org/', 2);
insert into t_task (id, title, description, user_id) values(3, 'Try SpringFuse','http://www.springfuse.com/', 2);
insert into t_task (id, title, description, user_id) values(4, 'Try Spring Roo','http://www.springsource.org/spring-roo', 2);
insert into t_task (id, title, description, user_id) values(5, 'Release SpringSide 4.0','As soon as posibble.', 2);

--insert into t_role (id, name, permissions) values(1,'Admin','user:view,user:edit');
--insert into t_role (id, name, permissions) values(2,'User','user:view');
