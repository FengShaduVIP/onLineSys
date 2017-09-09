/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : 127.0.0.1:3306
Source Database       : onlinesys

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-09-09 20:16:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `class_id` int(32) NOT NULL AUTO_INCREMENT,
  `class_no` varchar(10) NOT NULL COMMENT '班级id',
  `class_name` varchar(50) NOT NULL COMMENT '班级名称',
  `teach_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_year` int(5) NOT NULL COMMENT '创建年份班级届',
  `class_sort` int(2) NOT NULL COMMENT '排序值',
  `status` int(1) NOT NULL COMMENT '是否禁用',
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES ('4', '24', '22', '1', '2017', '22', '1');
INSERT INTO `class_info` VALUES ('5', '14905333', '测试班级', '1', '2017', '21', '1');
INSERT INTO `class_info` VALUES ('6', '14905330', '班级一版', '1', '2017', '2', '1');

-- ----------------------------
-- Table structure for exampaper_item
-- ----------------------------
DROP TABLE IF EXISTS `exampaper_item`;
CREATE TABLE `exampaper_item` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exampaper_id` int(10) NOT NULL COMMENT '试卷id',
  `item_id` int(10) NOT NULL COMMENT '题目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exampaper_item
-- ----------------------------

-- ----------------------------
-- Table structure for exam_info
-- ----------------------------
DROP TABLE IF EXISTS `exam_info`;
CREATE TABLE `exam_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `exampaper_id` int(10) NOT NULL COMMENT '试卷名称',
  `user_id` bigint(10) NOT NULL COMMENT '创建人',
  `class_ids` varchar(50) NOT NULL COMMENT '参与班级',
  `keep_time` int(10) NOT NULL COMMENT '考试时长',
  `is_finish` int(1) NOT NULL DEFAULT '0' COMMENT '0开始中，1该考试结束',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_info
-- ----------------------------

-- ----------------------------
-- Table structure for exam_paper
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper`;
CREATE TABLE `exam_paper` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '试卷标题',
  `detail` text COMMENT '试卷要求',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `author_id` varchar(50) DEFAULT NULL COMMENT '作者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_paper
-- ----------------------------
INSERT INTO `exam_paper` VALUES ('18', '在线考试', '考试要求', '1459401355', '123');
INSERT INTO `exam_paper` VALUES ('19', '第二次考试', '就写两道题', '1460002196', '123');
INSERT INTO `exam_paper` VALUES ('20', '测试201505', '<p>\n	完成下列各题eqqddwww\n</p>', '1462537637', '123');
INSERT INTO `exam_paper` VALUES ('21', '201606', '完成下列各题', '1465178388', '123');
INSERT INTO `exam_paper` VALUES ('22', 'test', 'test', '1465865799', '123');
INSERT INTO `exam_paper` VALUES ('28', '测试', '测试', '1478266881', '123');
INSERT INTO `exam_paper` VALUES ('29', 'kaoshi', 'kaoshiaaa', '1478762326', '123');

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'test', 'renren', '0 0/30 * * * ?', '1', '有参数测试', '2016-12-01 23:16:46');
INSERT INTO `schedule_job` VALUES ('2', 'testTask', 'test2', null, '0 0/30 * * * ?', '1', '无参数测试', '2016-12-03 14:55:56');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES ('1', '1', 'testTask', 'test', 'renren', '0', null, '1024', '2017-07-03 14:16:20');

-- ----------------------------
-- Table structure for stu_info
-- ----------------------------
DROP TABLE IF EXISTS `stu_info`;
CREATE TABLE `stu_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(50) DEFAULT '' COMMENT '学生姓名',
  `user_id` bigint(20) DEFAULT NULL COMMENT '姓名',
  `stu_no` int(10) DEFAULT NULL COMMENT '学号',
  `class_id` int(32) DEFAULT NULL COMMENT '班级',
  `teach_id` bigint(20) DEFAULT NULL COMMENT '教师姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_info
-- ----------------------------
INSERT INTO `stu_info` VALUES ('9', '', '10', '5555', '5', '1');
INSERT INTO `stu_info` VALUES ('10', '', '11', '444', '5', '1');
INSERT INTO `stu_info` VALUES ('11', '', '12', '777', '5', '1');
INSERT INTO `stu_info` VALUES ('12', 'www', '13', '222', '4', '1');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_item`;
CREATE TABLE `sys_item` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `context` text,
  `score` int(3) DEFAULT '0',
  `level` varchar(10) DEFAULT NULL,
  `author` varchar(30) DEFAULT NULL,
  `create_time` varchar(10) DEFAULT NULL,
  `is_visible` varchar(2) DEFAULT '1',
  `item_num` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1040 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_item
-- ----------------------------
INSERT INTO `sys_item` VALUES ('1003', 'BCD比较电路', 'BCD_comp', '<p>eeeeeessaaaa</p>\n', '3', '3', '123', '1465701576', '0', null);
INSERT INTO `sys_item` VALUES ('1004', '统计二进制数中含1数量的组合电路', 'countone', '<p>设计一个逻辑电路，以统计8位二进制数中含1的数量。模块定义为</p>\n\n<p>&nbsp;</p>\n\n<p>module countone(datain,out1)；</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n', '10', '2', '123', '1464873476', '0', null);
INSERT INTO `sys_item` VALUES ('1005', '最大值检测电路', 'max4', '<span style=\"font-family:宋体;font-size:small;\"> </span>\r\n<p style=\"margin:0cm 0cm 0pt;line-height:20pt;mso-line-height-rule:exactly;\">\r\n	<b style=\"mso-bidi-font-weight:normal;\"><span style=\"font-family:宋体;font-size:12pt;mso-ascii-font-family:\"Times New Roman\";mso-hansi-font-family:\"Times New Roman\";\">设计一个</span></b><b style=\"mso-bidi-font-weight:normal;\"><span lang=\"EN-US\" style=\"font-size:12pt;\"><span style=\"font-family:\'Times New Roman\';\">4</span></span></b><b style=\"mso-bidi-font-weight:normal;\"><span style=\"font-family:宋体;font-size:12pt;mso-ascii-font-family:\"Times New Roman\";mso-hansi-font-family:\"Times New Roman\";\">位</span></b><b style=\"mso-bidi-font-weight:normal;\"><span lang=\"EN-US\" style=\"font-size:12pt;\"><span style=\"font-family:\'Times New Roman\';\">4</span></span></b><b style=\"mso-bidi-font-weight:normal;\"><span style=\"font-family:宋体;font-size:12pt;mso-ascii-font-family:\"Times New Roman\";mso-hansi-font-family:\"Times New Roman\";\">输入最大数值检测电路。模块描述为</span></b>\r\n</p>\r\n<span style=\"font-family:宋体;font-size:small;\"> </span>\r\n<p style=\"margin:0cm 0cm 0pt;line-height:20pt;mso-line-height-rule:exactly;\">\r\n	<span lang=\"EN-US\" style=\"font-size:12pt;\"><span style=\"font-family:\'Times New Roman\';\">module max4(a,b,c,d,max);</span></span>\r\n</p>\r\n<span style=\"font-family:宋体;font-size:small;\"> </span>', '10', '2', '123', '1464873496', '0', null);
INSERT INTO `sys_item` VALUES ('1006', '2选1多路选择器', 'mux21', '<p>\r\n	设计一个2选1多路选择器。模块描述为\r\n</p>\r\n<p>\r\n	module mux21(a,b,s,y);\r\n</p>', '10', '1', '123', '1462536844', '1', null);
INSERT INTO `sys_item` VALUES ('1007', '8位比较器设计', 'comp', '<p>设计一个8位比较器电路，比较两个8位数入数 a、b的大小。要求设置三个输出gt、eq、lt，满足：</p>\n\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 当 a&gt;b 时，gt=1，eq=0，lt=0；</p>\n\n<p>&nbsp; &nbsp; &nbsp; &nbsp; 当 a=b 时，gt=0，eq=1，lt=0；</p>\n\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 当 a&lt;b 时，gt=0，eq=0，lt=1；</p>\n\n<p>模块定义为module(a,b,lt,gt,eq);</p>\n\n<p>&nbsp;</p>\n', '10', '2', '123', '1465180091', '1', null);
INSERT INTO `sys_item` VALUES ('1008', '4位计数器设计', 'cnt4', '<p>\r\n	设计一个具有数据加载功能的4位计数器。模块定义为：\r\n</p>\r\n<p>\r\n	module cnt4(clk, rst, d, load, q, cout);\r\n</p>\r\n<p>\r\n	其中d为被加载的4位输入，q为计数输出，cout为进位。\r\n</p>', '10', '2', '123', '1478264551', '1', null);
INSERT INTO `sys_item` VALUES ('1036', '333', '3333', '<p>3333</p>\r\n', '333', '2', 'admin', '2017-04-23', null, null);
INSERT INTO `sys_item` VALUES ('1039', '555', '555', '<p>5555</p>\r\n', '55', '4', 'admin', '2017-07-02', null, null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '5');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user.html', null, '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role.html', null, '1', 'fa fa-user-secret', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu.html', null, '1', 'fa fa-th-list', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'druid/sql.html', null, '1', 'fa fa-bug', '4');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'sys/schedule.html', null, '1', 'fa fa-tasks', '5');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'sys/config.html', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'fa fa-sun-o', '6');
INSERT INTO `sys_menu` VALUES ('28', '1', '代码生成器', 'sys/generator.html', 'sys:generator:list,sys:generator:code', '1', 'fa fa-rocket', '7');
INSERT INTO `sys_menu` VALUES ('29', '0', '成绩管理', null, null, '0', 'fa fa-graduation-cap', '4');
INSERT INTO `sys_menu` VALUES ('30', '0', '学生管理', null, null, '0', 'fa fa-user-o', '3');
INSERT INTO `sys_menu` VALUES ('31', '0', '题目管理', null, null, '0', 'fa fa-file-code-o', '1');
INSERT INTO `sys_menu` VALUES ('32', '31', '题目列表', 'sysitem/sysitem.html', 'tech:items:list', '1', 'fa fa-list-ul', '0');
INSERT INTO `sys_menu` VALUES ('34', '0', '试卷管理', null, null, '0', 'fa fa-file-text-o', '1');
INSERT INTO `sys_menu` VALUES ('35', '34', '试卷列表', 'exampaper/exampaper.html', 'tech:exampaper:list', '1', 'fa fa-list-ul', '0');
INSERT INTO `sys_menu` VALUES ('36', '30', '班级列表', 'classInfo/classInfo.html', '', '1', 'fa fa-list-ul', '0');
INSERT INTO `sys_menu` VALUES ('37', '0', '在线练习', null, null, '0', 'fa fa-bar-chart', '6');
INSERT INTO `sys_menu` VALUES ('38', '0', '在线测试', null, null, '0', 'fa fa-table', '8');
INSERT INTO `sys_menu` VALUES ('39', '0', '成绩查询', null, null, '0', 'fa fa-line-chart', '9');
INSERT INTO `sys_menu` VALUES ('40', '32', '新增', null, 'tech:items:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('41', '32', '修改', null, 'tech:items:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('42', '32', '查看', null, 'tech:items:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('43', '32', '删除', null, 'tech:items:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('44', '32', '更改状态', null, 'tech:items:change', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('45', '35', '新增', null, 'tech:exampaper:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('46', '35', '查看', null, 'tech:exampaper:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('47', '35', '删除', null, 'tech:exampaper:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('48', '35', '修改', null, 'tech:exampaper:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('49', '1', '教师列表', 'teachInfo/teachInfo.html', 'teachInfo:list', '1', 'fa fa-users', '0');
INSERT INTO `sys_menu` VALUES ('50', '49', '查看', null, 'teachInfo:list', '2', null, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '教师', '教师一', '2017-01-06 22:55:03');
INSERT INTO `sys_role` VALUES ('2', '管理员', '超级管理员', '2017-01-06 22:59:53');
INSERT INTO `sys_role` VALUES ('3', '学生', '学生', '2017-01-06 22:59:53');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('256', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('257', '1', '32');
INSERT INTO `sys_role_menu` VALUES ('258', '1', '40');
INSERT INTO `sys_role_menu` VALUES ('259', '1', '41');
INSERT INTO `sys_role_menu` VALUES ('260', '1', '42');
INSERT INTO `sys_role_menu` VALUES ('261', '1', '43');
INSERT INTO `sys_role_menu` VALUES ('262', '1', '44');
INSERT INTO `sys_role_menu` VALUES ('263', '1', '34');
INSERT INTO `sys_role_menu` VALUES ('264', '1', '35');
INSERT INTO `sys_role_menu` VALUES ('265', '1', '45');
INSERT INTO `sys_role_menu` VALUES ('266', '1', '46');
INSERT INTO `sys_role_menu` VALUES ('267', '1', '47');
INSERT INTO `sys_role_menu` VALUES ('268', '1', '48');
INSERT INTO `sys_role_menu` VALUES ('269', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('270', '1', '36');
INSERT INTO `sys_role_menu` VALUES ('271', '2', '31');
INSERT INTO `sys_role_menu` VALUES ('272', '2', '32');
INSERT INTO `sys_role_menu` VALUES ('273', '2', '40');
INSERT INTO `sys_role_menu` VALUES ('274', '2', '41');
INSERT INTO `sys_role_menu` VALUES ('275', '2', '42');
INSERT INTO `sys_role_menu` VALUES ('276', '2', '43');
INSERT INTO `sys_role_menu` VALUES ('277', '2', '44');
INSERT INTO `sys_role_menu` VALUES ('278', '2', '34');
INSERT INTO `sys_role_menu` VALUES ('279', '2', '35');
INSERT INTO `sys_role_menu` VALUES ('280', '2', '45');
INSERT INTO `sys_role_menu` VALUES ('281', '2', '46');
INSERT INTO `sys_role_menu` VALUES ('282', '2', '47');
INSERT INTO `sys_role_menu` VALUES ('283', '2', '48');
INSERT INTO `sys_role_menu` VALUES ('284', '2', '30');
INSERT INTO `sys_role_menu` VALUES ('285', '2', '36');
INSERT INTO `sys_role_menu` VALUES ('286', '2', '29');
INSERT INTO `sys_role_menu` VALUES ('287', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('288', '2', '49');
INSERT INTO `sys_role_menu` VALUES ('289', '2', '50');
INSERT INTO `sys_role_menu` VALUES ('290', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('291', '2', '15');
INSERT INTO `sys_role_menu` VALUES ('292', '2', '16');
INSERT INTO `sys_role_menu` VALUES ('293', '2', '17');
INSERT INTO `sys_role_menu` VALUES ('294', '2', '18');
INSERT INTO `sys_role_menu` VALUES ('295', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('296', '2', '19');
INSERT INTO `sys_role_menu` VALUES ('297', '2', '20');
INSERT INTO `sys_role_menu` VALUES ('298', '2', '21');
INSERT INTO `sys_role_menu` VALUES ('299', '2', '22');
INSERT INTO `sys_role_menu` VALUES ('300', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('301', '2', '23');
INSERT INTO `sys_role_menu` VALUES ('302', '2', '24');
INSERT INTO `sys_role_menu` VALUES ('303', '2', '25');
INSERT INTO `sys_role_menu` VALUES ('304', '2', '26');
INSERT INTO `sys_role_menu` VALUES ('305', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('306', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('307', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('308', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('309', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('310', '2', '10');
INSERT INTO `sys_role_menu` VALUES ('311', '2', '11');
INSERT INTO `sys_role_menu` VALUES ('312', '2', '12');
INSERT INTO `sys_role_menu` VALUES ('313', '2', '13');
INSERT INTO `sys_role_menu` VALUES ('314', '2', '14');
INSERT INTO `sys_role_menu` VALUES ('315', '2', '27');
INSERT INTO `sys_role_menu` VALUES ('316', '2', '28');
INSERT INTO `sys_role_menu` VALUES ('317', '3', '37');
INSERT INTO `sys_role_menu` VALUES ('318', '3', '38');
INSERT INTO `sys_role_menu` VALUES ('319', '3', '39');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `level` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '超级管理员', 'root@renren.io', '13612345678', '1', '2016-11-11 11:11:11', '2');
INSERT INTO `sys_user` VALUES ('3', 'test001', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '教师一', '123@126.com', '123456789', '1', '2017-01-06 23:09:20', '1');
INSERT INTO `sys_user` VALUES ('4', 'stu01', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '3', '5@qq.com', '152', '1', '2017-02-06 11:23:41', '0');
INSERT INTO `sys_user` VALUES ('5', 'tech01', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '4', '33', '33', '1', '2017-02-06 13:53:51', '1');
INSERT INTO `sys_user` VALUES ('10', '5555', 'c1f330d0aff31c1c87403f1e4347bcc21aff7c179908723535f2b31723702525', 'xiao', null, null, '1', '2017-09-09 15:27:58', '0');
INSERT INTO `sys_user` VALUES ('11', '444', '3538a1ef2e113da64249eea7bd068b585ec7ce5df73b2d1e319d8c9bf47eb314', 'rrrr', null, null, '1', '2017-09-09 15:31:29', '0');
INSERT INTO `sys_user` VALUES ('12', '777', 'eaf89db7108470dc3f6b23ea90618264b3e8f8b6145371667c4055e9c5ce9f52', 'yyy', null, null, '1', '2017-09-09 15:32:56', '0');
INSERT INTO `sys_user` VALUES ('13', '222', '9b871512327c09ce91dd649b3f96a63b7408ef267c8cc5710114e629730cb61f', 'www', null, null, '1', '2017-09-09 15:33:56', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('4', '5', '1');
INSERT INTO `sys_user_role` VALUES ('6', '6', '1');
INSERT INTO `sys_user_role` VALUES ('7', '8', '1');
INSERT INTO `sys_user_role` VALUES ('8', '9', '1');
INSERT INTO `sys_user_role` VALUES ('10', '10', '3');
INSERT INTO `sys_user_role` VALUES ('11', '11', '3');
INSERT INTO `sys_user_role` VALUES ('12', '12', '3');
INSERT INTO `sys_user_role` VALUES ('13', '13', '3');
