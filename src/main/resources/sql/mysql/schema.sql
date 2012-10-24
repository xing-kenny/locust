alter table `t_course` drop foreign key `FK_course_plan`;
alter table `t_plan`   drop foreign key `FK_plan_project`;
alter table `t_user`   drop foreign key `FK_user_dept`;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
drop table if exists t_user;
create table t_user (
	id bigint auto_increment,
	login_name varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,

	phone_No varchar(20) DEFAULT NULL,
	email varchar(20) DEFAULT NULL,
	dept_Id int(11) NOT NULL,

	primary key (id)
) engine=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

insert into t_user (id, login_name, name, password, salt, roles, phone_No, email,dept_Id) values(1,'admin','Admin','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','admin',null,null,100);
insert into t_user (id, login_name, name, password, salt, roles, phone_No, email,dept_Id) values(2,'user','Calvin','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','user',null,null,100);

-- ----------------------------
-- Table structure for `t_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_dept` (`sname`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('100','培训中心');
INSERT INTO `t_dept` VALUES ('101','研1');
INSERT INTO `t_dept` VALUES ('102','研2');
INSERT INTO `t_dept` VALUES ('103','研3');
INSERT INTO `t_dept` VALUES ('104','质管');

-- ----------------------------
-- Table structure for `t_project`
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `description` varchar(256) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `mandays` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `t_plan`
-- ----------------------------
DROP TABLE IF EXISTS `t_plan`;
CREATE TABLE `t_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `project_Id` int(11) NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `numbers` int(11) NOT NULL,
  `plan_Id` int(11) NOT NULL,
  `status` int(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_signup`
-- ----------------------------
DROP TABLE IF EXISTS `t_signup`;
CREATE TABLE `t_signup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_Id` int(11) NOT NULL,
  `signer_Id` int(11) NOT NULL,
  `checkIn_Id` int(11) DEFAULT NULL,
  `signUp_Day` datetime DEFAULT null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_checkin`
-- ----------------------------
DROP TABLE IF EXISTS `t_checkin`;
CREATE TABLE `t_checkin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_Id` int(11) NOT NULL,
  `checkIn_User_Id` int(11) NOT NULL,
  `checkIn_Day` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_rungin`
-- ----------------------------
DROP TABLE IF EXISTS `t_rungin`;
CREATE TABLE `t_rungin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_Id` int(11) NOT NULL,
  `rungin_user_Id` int(11) NOT NULL,
  `rungin_Day` date NOT NULL,
  `forenoon` int(1) DEFAULT 0,
  `plan_Id` int(11) NOT NULL,
  `project_Id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;


alter table `t_course`   add CONSTRAINT `FK_course_plan`  FOREIGN KEY (`plan_Id`)    REFERENCES `t_plan`    (`id`);
alter table `t_plan`     add CONSTRAINT `FK_plan_project` FOREIGN KEY (`project_Id`) REFERENCES `t_project` (`id`);
alter table `t_user`     add CONSTRAINT `FK_user_dept`    FOREIGN KEY (`dept_Id`)    REFERENCES `t_dept`    (`id`);

-- ----------------------------
-- VIEWs
-- ----------------------------
create or replace view `v_project` as 
select e.*, sum(if(ifnull(t.id,0) = 0,0,1))/2 as actualMandays
from t_project as e 
left join t_rungin as t on
e.id =  t.project_id
group by e.id;

