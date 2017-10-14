/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : 127.0.0.1:3306
Source Database       : onlinesys

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-10-14 14:15:32
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
INSERT INTO `class_info` VALUES ('1', '2', '2', '1', '2017', '33', '1');
INSERT INTO `class_info` VALUES ('4', '24', '2555', '1', '2017', '22', '1');
INSERT INTO `class_info` VALUES ('5', '14905333', '测试班级', '1', '2017', '21', '1');
INSERT INTO `class_info` VALUES ('6', '999', '999', '5', '2017', '999', '1');

-- ----------------------------
-- Table structure for class_test
-- ----------------------------
DROP TABLE IF EXISTS `class_test`;
CREATE TABLE `class_test` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `class_id` int(20) DEFAULT NULL,
  `exam_test_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_test
-- ----------------------------
INSERT INTO `class_test` VALUES ('1', '4', '7');
INSERT INTO `class_test` VALUES ('2', '5', '7');
INSERT INTO `class_test` VALUES ('3', '4', '8');
INSERT INTO `class_test` VALUES ('4', '5', '8');
INSERT INTO `class_test` VALUES ('5', '4', '9');
INSERT INTO `class_test` VALUES ('6', '5', '9');
INSERT INTO `class_test` VALUES ('7', '4', '10');
INSERT INTO `class_test` VALUES ('8', '4', '11');
INSERT INTO `class_test` VALUES ('9', '5', '12');
INSERT INTO `class_test` VALUES ('10', '4', '20');
INSERT INTO `class_test` VALUES ('11', '5', '20');
INSERT INTO `class_test` VALUES ('12', '5', '46');
INSERT INTO `class_test` VALUES ('13', '5', '47');
INSERT INTO `class_test` VALUES ('14', '6', '48');
INSERT INTO `class_test` VALUES ('15', '4', '48');
INSERT INTO `class_test` VALUES ('16', '5', '48');
INSERT INTO `class_test` VALUES ('17', '4', '49');
INSERT INTO `class_test` VALUES ('18', '5', '49');
INSERT INTO `class_test` VALUES ('19', '6', '50');
INSERT INTO `class_test` VALUES ('20', '4', '50');
INSERT INTO `class_test` VALUES ('21', '5', '50');

-- ----------------------------
-- Table structure for exampaper_item
-- ----------------------------
DROP TABLE IF EXISTS `exampaper_item`;
CREATE TABLE `exampaper_item` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exampaper_id` int(10) NOT NULL COMMENT '试卷id',
  `item_id` int(10) NOT NULL COMMENT '题目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exampaper_item
-- ----------------------------
INSERT INTO `exampaper_item` VALUES ('7', '18', '1008');
INSERT INTO `exampaper_item` VALUES ('8', '18', '1039');
INSERT INTO `exampaper_item` VALUES ('9', '18', '1036');
INSERT INTO `exampaper_item` VALUES ('10', '18', '1004');
INSERT INTO `exampaper_item` VALUES ('11', '18', '1003');
INSERT INTO `exampaper_item` VALUES ('12', '19', '1003');
INSERT INTO `exampaper_item` VALUES ('13', '19', '1004');
INSERT INTO `exampaper_item` VALUES ('14', '31', '1003');
INSERT INTO `exampaper_item` VALUES ('15', '31', '1004');
INSERT INTO `exampaper_item` VALUES ('16', '29', '1004');
INSERT INTO `exampaper_item` VALUES ('17', '29', '1005');

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
INSERT INTO `exam_paper` VALUES ('18', '在线考试', '考试要求', '1459401355', '1');
INSERT INTO `exam_paper` VALUES ('19', '第二次考试', '就写两道题', '1460002196', '5');
INSERT INTO `exam_paper` VALUES ('20', '测试201505', '<p>\n	完成下列各题eqqddwww\n</p>', '1462537637', '5');
INSERT INTO `exam_paper` VALUES ('21', '201606', '完成下列各题', '1465178388', '1');
INSERT INTO `exam_paper` VALUES ('22', 'test', 'test', '1465865799', '1');
INSERT INTO `exam_paper` VALUES ('28', '测试', '<p>测试sssss</p>\n', '1478266881', '1');
INSERT INTO `exam_paper` VALUES ('29', '测试试卷新', '<p>测试试卷新测试试卷新</p>\n', '1506148755', '1');

-- ----------------------------
-- Table structure for exam_test
-- ----------------------------
DROP TABLE IF EXISTS `exam_test`;
CREATE TABLE `exam_test` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `exam__paper_id` int(20) DEFAULT NULL,
  `author_id` varchar(33) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` int(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `exam_title` varchar(255) DEFAULT NULL,
  `class_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_test
-- ----------------------------
INSERT INTO `exam_test` VALUES ('6', '19', 'admin', '2017-09-16 00:05:18', '2017-09-16 00:05:18', '1505045412', '0', '333', null);
INSERT INTO `exam_test` VALUES ('7', '18', '1', '2017-09-20 20:47:39', '2017-09-20 20:47:39', '1505045467', '0', '333', null);
INSERT INTO `exam_test` VALUES ('8', '18', '1', '2017-09-20 20:47:39', '2017-09-20 20:47:39', '1505046802', '0', '呃呃呃e', null);
INSERT INTO `exam_test` VALUES ('9', '18', '1', '2017-09-20 20:47:39', '2017-09-20 20:47:39', '1505046880', '0', 'qqq', null);
INSERT INTO `exam_test` VALUES ('10', '18', '1', '2017-09-20 20:47:52', '2017-09-20 20:47:52', '1505046918', '0', '呃呃呃', null);
INSERT INTO `exam_test` VALUES ('11', '18', '5', '2017-09-20 20:47:53', '2017-09-20 20:47:53', '1505046974', '0', '任溶溶', null);
INSERT INTO `exam_test` VALUES ('12', '18', '5', '2017-09-20 20:47:53', '2017-09-20 20:47:53', '1505047096', '0', '太阳天天', null);
INSERT INTO `exam_test` VALUES ('43', '18', '5', '2017-09-20 20:47:54', '2017-09-20 20:47:54', '1505045412', '0', '333', null);
INSERT INTO `exam_test` VALUES ('44', '18', 'admin', '2017-09-12 20:56:51', '2017-09-12 20:56:51', '1505046918', '0', '呃呃呃', null);
INSERT INTO `exam_test` VALUES ('45', '18', 'admin', '2017-09-12 20:56:51', '2017-09-12 20:56:51', '1505046880', '0', 'qqq', null);
INSERT INTO `exam_test` VALUES ('46', '18', 'admin', '2017-09-13 14:03:43', '2017-09-14 00:00:00', '1505282623', '0', '开始考试', null);
INSERT INTO `exam_test` VALUES ('47', '31', 'admin', '2017-09-16 11:46:39', '2017-09-23 00:00:00', '1505533598', '0', 'ttt', null);
INSERT INTO `exam_test` VALUES ('48', '18', '1', '2017-09-23 14:25:45', '2017-09-23 22:04:04', '1506147945', '1', 'kaoshi yi', null);
INSERT INTO `exam_test` VALUES ('49', '29', '1', '2017-09-23 14:39:46', '2017-09-26 00:00:00', '1506148786', '1', '哦哦哦哦哦哦', null);
INSERT INTO `exam_test` VALUES ('50', '29', '1', '2017-09-23 14:54:53', '2017-09-15 00:00:00', '1506149693', '1', '5555', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('3', 'examTest', 'closeExamTest', null, '0 0/1 * * * ? ', '1', '每分钟执行关闭在线测试已结束的', '2017-09-12 20:44:35');
INSERT INTO `schedule_job` VALUES ('4', 'deleteFileTask', 'deleteFile', null, '0 0 0 * * ? *', '0', '每天0点删除练习题目生成的临时文件', '2017-09-17 18:11:54');

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
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES ('1', '1', 'testTask', 'test', 'renren', '0', null, '1024', '2017-07-03 14:16:20');
INSERT INTO `schedule_job_log` VALUES ('2', '3', 'examTest', 'closeExamTest', null, '1', 'java.lang.NoSuchMethodException: com.twp.task.ExamTest.closeExamTest()', '6', '2017-09-12 20:45:00');
INSERT INTO `schedule_job_log` VALUES ('3', '3', 'examTest', 'closeExamTest', null, '1', 'java.lang.NoSuchMethodException: com.twp.task.ExamTest.closeExamTest()', '1', '2017-09-12 20:46:00');
INSERT INTO `schedule_job_log` VALUES ('4', '3', 'examTest', 'closeExamTest', null, '1', 'java.lang.NoSuchMethodException: com.twp.task.ExamTest.closeExamTest()', '1', '2017-09-12 20:47:00');
INSERT INTO `schedule_job_log` VALUES ('5', '3', 'examTest', 'closeExamTest', null, '1', 'java.lang.NoSuchMethodException: com.twp.task.ExamTest.closeExamTest()', '1', '2017-09-12 20:48:00');
INSERT INTO `schedule_job_log` VALUES ('6', '3', 'examTest', 'closeExamTest', null, '1', 'java.lang.NoSuchMethodException: com.twp.task.ExamTest.closeExamTest()', '1', '2017-09-12 20:49:00');
INSERT INTO `schedule_job_log` VALUES ('7', '3', 'examTest', 'closeExamTest', null, '1', 'java.lang.NoSuchMethodException: com.twp.task.ExamTest.closeExamTest()', '41', '2017-09-12 20:50:00');
INSERT INTO `schedule_job_log` VALUES ('8', '3', 'examTest', 'closeExamTest', null, '1', 'java.lang.NoSuchMethodException: com.twp.task.ExamTest.closeExamTest()', '1', '2017-09-12 20:51:00');
INSERT INTO `schedule_job_log` VALUES ('9', '3', 'examTest', 'closeExamTest', null, '1', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'examTest\' is defined', '5', '2017-09-12 20:51:37');
INSERT INTO `schedule_job_log` VALUES ('10', '3', 'examTest', 'closeExamTest', null, '1', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'examTest\' is defined', '2', '2017-09-12 20:52:00');
INSERT INTO `schedule_job_log` VALUES ('11', '3', 'examTest', 'closeExamTest', null, '1', 'java.util.concurrent.ExecutionException: com.twp.utils.RRException: 执行定时任务失败', '17', '2017-09-12 20:53:00');
INSERT INTO `schedule_job_log` VALUES ('12', '3', 'examTest', 'closeExamTest', null, '1', 'java.util.concurrent.ExecutionException: com.twp.utils.RRException: 执行定时任务失败', '16', '2017-09-12 20:54:00');
INSERT INTO `schedule_job_log` VALUES ('13', '3', 'examTest', 'closeExamTest', null, '1', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'examTest\' is defined', '2', '2017-09-12 20:54:37');
INSERT INTO `schedule_job_log` VALUES ('14', '3', 'examTest', 'closeExamTest', null, '1', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'examTest\' is defined', '3', '2017-09-12 20:55:00');
INSERT INTO `schedule_job_log` VALUES ('15', '3', 'examTest', 'closeExamTest', null, '0', null, '67', '2017-09-12 20:56:07');
INSERT INTO `schedule_job_log` VALUES ('16', '3', 'examTest', 'closeExamTest', null, '0', null, '77', '2017-09-12 20:57:00');
INSERT INTO `schedule_job_log` VALUES ('17', '3', 'examTest', 'closeExamTest', null, '0', null, '56', '2017-09-12 20:58:00');
INSERT INTO `schedule_job_log` VALUES ('18', '3', 'examTest', 'closeExamTest', null, '0', null, '113', '2017-09-12 20:59:00');
INSERT INTO `schedule_job_log` VALUES ('19', '3', 'examTest', 'closeExamTest', null, '1', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'examTest\' is defined', '2', '2017-09-12 21:08:25');
INSERT INTO `schedule_job_log` VALUES ('20', '3', 'examTest', 'closeExamTest', null, '1', 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'examTest\' is defined', '1', '2017-09-12 21:09:00');
INSERT INTO `schedule_job_log` VALUES ('21', '3', 'examTest', 'closeExamTest', null, '0', null, '36', '2017-09-12 21:10:00');
INSERT INTO `schedule_job_log` VALUES ('22', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:11:00');
INSERT INTO `schedule_job_log` VALUES ('23', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:11:58');
INSERT INTO `schedule_job_log` VALUES ('24', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:12:00');
INSERT INTO `schedule_job_log` VALUES ('25', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:13:00');
INSERT INTO `schedule_job_log` VALUES ('26', '3', 'examTest', 'closeExamTest', null, '0', null, '8', '2017-09-12 21:14:00');
INSERT INTO `schedule_job_log` VALUES ('27', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:15:00');
INSERT INTO `schedule_job_log` VALUES ('28', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:16:00');
INSERT INTO `schedule_job_log` VALUES ('29', '3', 'examTest', 'closeExamTest', null, '0', null, '14', '2017-09-12 21:17:00');
INSERT INTO `schedule_job_log` VALUES ('30', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:18:00');
INSERT INTO `schedule_job_log` VALUES ('31', '3', 'examTest', 'closeExamTest', null, '0', null, '7', '2017-09-12 21:19:00');
INSERT INTO `schedule_job_log` VALUES ('32', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:20:00');
INSERT INTO `schedule_job_log` VALUES ('33', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:21:00');
INSERT INTO `schedule_job_log` VALUES ('34', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:22:00');
INSERT INTO `schedule_job_log` VALUES ('35', '3', 'examTest', 'closeExamTest', null, '0', null, '7', '2017-09-12 21:23:00');
INSERT INTO `schedule_job_log` VALUES ('36', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:24:00');
INSERT INTO `schedule_job_log` VALUES ('37', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:25:00');
INSERT INTO `schedule_job_log` VALUES ('38', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:26:00');
INSERT INTO `schedule_job_log` VALUES ('39', '3', 'examTest', 'closeExamTest', null, '0', null, '2', '2017-09-12 21:27:00');
INSERT INTO `schedule_job_log` VALUES ('40', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:28:00');
INSERT INTO `schedule_job_log` VALUES ('41', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:29:00');
INSERT INTO `schedule_job_log` VALUES ('42', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('43', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:31:00');
INSERT INTO `schedule_job_log` VALUES ('44', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 21:32:00');
INSERT INTO `schedule_job_log` VALUES ('45', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:33:00');
INSERT INTO `schedule_job_log` VALUES ('46', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 21:34:00');
INSERT INTO `schedule_job_log` VALUES ('47', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 21:35:00');
INSERT INTO `schedule_job_log` VALUES ('48', '3', 'examTest', 'closeExamTest', null, '1', 'java.util.concurrent.ExecutionException: com.twp.utils.RRException: 执行定时任务失败', '66', '2017-09-12 21:36:00');
INSERT INTO `schedule_job_log` VALUES ('49', '3', 'examTest', 'closeExamTest', null, '0', null, '7', '2017-09-12 21:37:00');
INSERT INTO `schedule_job_log` VALUES ('50', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-12 21:37:32');
INSERT INTO `schedule_job_log` VALUES ('51', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:38:00');
INSERT INTO `schedule_job_log` VALUES ('52', '3', 'examTest', 'closeExamTest', null, '0', null, '17', '2017-09-12 21:39:00');
INSERT INTO `schedule_job_log` VALUES ('53', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:40:00');
INSERT INTO `schedule_job_log` VALUES ('54', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:41:00');
INSERT INTO `schedule_job_log` VALUES ('55', '3', 'examTest', 'closeExamTest', null, '0', null, '10', '2017-09-12 21:42:00');
INSERT INTO `schedule_job_log` VALUES ('56', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-12 21:42:20');
INSERT INTO `schedule_job_log` VALUES ('57', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:43:00');
INSERT INTO `schedule_job_log` VALUES ('58', '3', 'examTest', 'closeExamTest', null, '0', null, '9', '2017-09-12 21:44:00');
INSERT INTO `schedule_job_log` VALUES ('59', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-12 21:45:00');
INSERT INTO `schedule_job_log` VALUES ('60', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:46:00');
INSERT INTO `schedule_job_log` VALUES ('61', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:47:00');
INSERT INTO `schedule_job_log` VALUES ('62', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 21:48:00');
INSERT INTO `schedule_job_log` VALUES ('63', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:49:00');
INSERT INTO `schedule_job_log` VALUES ('64', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:50:00');
INSERT INTO `schedule_job_log` VALUES ('65', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:51:00');
INSERT INTO `schedule_job_log` VALUES ('66', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:52:00');
INSERT INTO `schedule_job_log` VALUES ('67', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:53:00');
INSERT INTO `schedule_job_log` VALUES ('68', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:54:00');
INSERT INTO `schedule_job_log` VALUES ('69', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 21:54:29');
INSERT INTO `schedule_job_log` VALUES ('70', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:55:00');
INSERT INTO `schedule_job_log` VALUES ('71', '3', 'examTest', 'closeExamTest', null, '0', null, '9', '2017-09-12 21:56:00');
INSERT INTO `schedule_job_log` VALUES ('72', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 21:57:00');
INSERT INTO `schedule_job_log` VALUES ('73', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 21:58:00');
INSERT INTO `schedule_job_log` VALUES ('74', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-12 21:59:00');
INSERT INTO `schedule_job_log` VALUES ('75', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 22:00:00');
INSERT INTO `schedule_job_log` VALUES ('76', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 22:01:00');
INSERT INTO `schedule_job_log` VALUES ('77', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:02:00');
INSERT INTO `schedule_job_log` VALUES ('78', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:03:00');
INSERT INTO `schedule_job_log` VALUES ('79', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:04:00');
INSERT INTO `schedule_job_log` VALUES ('80', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:05:00');
INSERT INTO `schedule_job_log` VALUES ('81', '3', 'examTest', 'closeExamTest', null, '0', null, '2', '2017-09-12 22:05:29');
INSERT INTO `schedule_job_log` VALUES ('82', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:06:00');
INSERT INTO `schedule_job_log` VALUES ('83', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 22:07:00');
INSERT INTO `schedule_job_log` VALUES ('84', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 22:08:00');
INSERT INTO `schedule_job_log` VALUES ('85', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-12 22:09:00');
INSERT INTO `schedule_job_log` VALUES ('86', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 22:10:00');
INSERT INTO `schedule_job_log` VALUES ('87', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:11:00');
INSERT INTO `schedule_job_log` VALUES ('88', '3', 'examTest', 'closeExamTest', null, '0', null, '7', '2017-09-12 22:12:00');
INSERT INTO `schedule_job_log` VALUES ('89', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:13:00');
INSERT INTO `schedule_job_log` VALUES ('90', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:14:00');
INSERT INTO `schedule_job_log` VALUES ('91', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:15:00');
INSERT INTO `schedule_job_log` VALUES ('92', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 22:16:00');
INSERT INTO `schedule_job_log` VALUES ('93', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:17:00');
INSERT INTO `schedule_job_log` VALUES ('94', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 22:18:00');
INSERT INTO `schedule_job_log` VALUES ('95', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:19:00');
INSERT INTO `schedule_job_log` VALUES ('96', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:20:00');
INSERT INTO `schedule_job_log` VALUES ('97', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:21:00');
INSERT INTO `schedule_job_log` VALUES ('98', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:22:00');
INSERT INTO `schedule_job_log` VALUES ('99', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:23:00');
INSERT INTO `schedule_job_log` VALUES ('100', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-12 22:24:00');
INSERT INTO `schedule_job_log` VALUES ('101', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:25:00');
INSERT INTO `schedule_job_log` VALUES ('102', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:26:00');
INSERT INTO `schedule_job_log` VALUES ('103', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:27:00');
INSERT INTO `schedule_job_log` VALUES ('104', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:28:00');
INSERT INTO `schedule_job_log` VALUES ('105', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:29:00');
INSERT INTO `schedule_job_log` VALUES ('106', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:30:00');
INSERT INTO `schedule_job_log` VALUES ('107', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:31:00');
INSERT INTO `schedule_job_log` VALUES ('108', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-12 22:32:00');
INSERT INTO `schedule_job_log` VALUES ('109', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:33:00');
INSERT INTO `schedule_job_log` VALUES ('110', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:34:00');
INSERT INTO `schedule_job_log` VALUES ('111', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:35:00');
INSERT INTO `schedule_job_log` VALUES ('112', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:36:00');
INSERT INTO `schedule_job_log` VALUES ('113', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:37:00');
INSERT INTO `schedule_job_log` VALUES ('114', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:38:00');
INSERT INTO `schedule_job_log` VALUES ('115', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:39:00');
INSERT INTO `schedule_job_log` VALUES ('116', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:40:00');
INSERT INTO `schedule_job_log` VALUES ('117', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:41:00');
INSERT INTO `schedule_job_log` VALUES ('118', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:42:00');
INSERT INTO `schedule_job_log` VALUES ('119', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 22:43:00');
INSERT INTO `schedule_job_log` VALUES ('120', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 22:44:00');
INSERT INTO `schedule_job_log` VALUES ('121', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:44:54');
INSERT INTO `schedule_job_log` VALUES ('122', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:45:00');
INSERT INTO `schedule_job_log` VALUES ('123', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:46:00');
INSERT INTO `schedule_job_log` VALUES ('124', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:47:00');
INSERT INTO `schedule_job_log` VALUES ('125', '3', 'examTest', 'closeExamTest', null, '0', null, '13', '2017-09-12 22:48:00');
INSERT INTO `schedule_job_log` VALUES ('126', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:48:06');
INSERT INTO `schedule_job_log` VALUES ('127', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:49:00');
INSERT INTO `schedule_job_log` VALUES ('128', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:50:00');
INSERT INTO `schedule_job_log` VALUES ('129', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:51:00');
INSERT INTO `schedule_job_log` VALUES ('130', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-12 22:52:00');
INSERT INTO `schedule_job_log` VALUES ('131', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:53:00');
INSERT INTO `schedule_job_log` VALUES ('132', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:54:00');
INSERT INTO `schedule_job_log` VALUES ('133', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-12 22:54:54');
INSERT INTO `schedule_job_log` VALUES ('134', '3', 'examTest', 'closeExamTest', null, '0', null, '2', '2017-09-12 22:55:00');
INSERT INTO `schedule_job_log` VALUES ('135', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-12 22:56:00');
INSERT INTO `schedule_job_log` VALUES ('136', '3', 'examTest', 'closeExamTest', null, '0', null, '15', '2017-09-12 22:57:00');
INSERT INTO `schedule_job_log` VALUES ('137', '3', 'examTest', 'closeExamTest', null, '0', null, '14', '2017-09-12 22:58:00');
INSERT INTO `schedule_job_log` VALUES ('138', '3', 'examTest', 'closeExamTest', null, '0', null, '11', '2017-09-13 14:03:33');
INSERT INTO `schedule_job_log` VALUES ('139', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-13 14:04:00');
INSERT INTO `schedule_job_log` VALUES ('140', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-13 14:05:00');
INSERT INTO `schedule_job_log` VALUES ('141', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-13 14:06:00');
INSERT INTO `schedule_job_log` VALUES ('142', '3', 'examTest', 'closeExamTest', null, '0', null, '7', '2017-09-13 14:07:00');
INSERT INTO `schedule_job_log` VALUES ('143', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-13 14:08:00');
INSERT INTO `schedule_job_log` VALUES ('144', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '3', '2017-09-17 18:13:48');
INSERT INTO `schedule_job_log` VALUES ('145', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '35', '2017-09-17 18:14:29');
INSERT INTO `schedule_job_log` VALUES ('146', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '31', '2017-09-17 18:15:29');
INSERT INTO `schedule_job_log` VALUES ('147', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '5', '2017-09-17 20:07:11');
INSERT INTO `schedule_job_log` VALUES ('148', '3', 'examTest', 'closeExamTest', null, '0', null, '17', '2017-09-17 20:09:27');
INSERT INTO `schedule_job_log` VALUES ('149', '3', 'examTest', 'closeExamTest', null, '0', null, '18', '2017-09-17 21:07:02');
INSERT INTO `schedule_job_log` VALUES ('150', '3', 'examTest', 'closeExamTest', null, '0', null, '21', '2017-09-17 21:28:45');
INSERT INTO `schedule_job_log` VALUES ('151', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-17 21:29:00');
INSERT INTO `schedule_job_log` VALUES ('152', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-17 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('153', '3', 'examTest', 'closeExamTest', null, '0', null, '10', '2017-09-17 21:31:00');
INSERT INTO `schedule_job_log` VALUES ('154', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-17 21:32:00');
INSERT INTO `schedule_job_log` VALUES ('155', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-17 21:33:00');
INSERT INTO `schedule_job_log` VALUES ('156', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-17 21:34:00');
INSERT INTO `schedule_job_log` VALUES ('157', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-17 21:35:00');
INSERT INTO `schedule_job_log` VALUES ('158', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-17 21:36:00');
INSERT INTO `schedule_job_log` VALUES ('159', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-17 21:37:00');
INSERT INTO `schedule_job_log` VALUES ('160', '3', 'examTest', 'closeExamTest', null, '0', null, '12', '2017-09-17 21:38:00');
INSERT INTO `schedule_job_log` VALUES ('161', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-17 21:39:00');
INSERT INTO `schedule_job_log` VALUES ('162', '3', 'examTest', 'closeExamTest', null, '0', null, '7', '2017-09-17 21:40:00');
INSERT INTO `schedule_job_log` VALUES ('163', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-17 21:41:00');
INSERT INTO `schedule_job_log` VALUES ('164', '3', 'examTest', 'closeExamTest', null, '0', null, '33', '2017-09-20 20:15:37');
INSERT INTO `schedule_job_log` VALUES ('165', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '6', '2017-09-20 20:15:37');
INSERT INTO `schedule_job_log` VALUES ('166', '3', 'examTest', 'closeExamTest', null, '0', null, '12', '2017-09-20 20:16:00');
INSERT INTO `schedule_job_log` VALUES ('167', '3', 'examTest', 'closeExamTest', null, '0', null, '55', '2017-09-20 20:17:37');
INSERT INTO `schedule_job_log` VALUES ('168', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '5', '2017-09-20 20:17:38');
INSERT INTO `schedule_job_log` VALUES ('169', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-20 20:18:00');
INSERT INTO `schedule_job_log` VALUES ('170', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 20:19:00');
INSERT INTO `schedule_job_log` VALUES ('171', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-20 20:20:00');
INSERT INTO `schedule_job_log` VALUES ('172', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-20 20:21:00');
INSERT INTO `schedule_job_log` VALUES ('173', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-20 20:22:00');
INSERT INTO `schedule_job_log` VALUES ('174', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 20:23:00');
INSERT INTO `schedule_job_log` VALUES ('175', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-20 20:24:00');
INSERT INTO `schedule_job_log` VALUES ('176', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '7', '2017-09-20 20:24:05');
INSERT INTO `schedule_job_log` VALUES ('177', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '8', '2017-09-20 20:24:57');
INSERT INTO `schedule_job_log` VALUES ('178', '3', 'examTest', 'closeExamTest', null, '0', null, '8', '2017-09-20 20:25:00');
INSERT INTO `schedule_job_log` VALUES ('179', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '7', '2017-09-20 20:25:17');
INSERT INTO `schedule_job_log` VALUES ('180', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '1', '2017-09-20 20:25:30');
INSERT INTO `schedule_job_log` VALUES ('181', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-20 20:26:00');
INSERT INTO `schedule_job_log` VALUES ('182', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '3', '2017-09-20 20:26:23');
INSERT INTO `schedule_job_log` VALUES ('183', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-20 20:27:00');
INSERT INTO `schedule_job_log` VALUES ('184', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 20:28:00');
INSERT INTO `schedule_job_log` VALUES ('185', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 20:29:00');
INSERT INTO `schedule_job_log` VALUES ('186', '3', 'examTest', 'closeExamTest', null, '0', null, '13', '2017-09-20 20:30:00');
INSERT INTO `schedule_job_log` VALUES ('187', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 20:31:00');
INSERT INTO `schedule_job_log` VALUES ('188', '3', 'examTest', 'closeExamTest', null, '0', null, '9', '2017-09-20 20:32:00');
INSERT INTO `schedule_job_log` VALUES ('189', '3', 'examTest', 'closeExamTest', null, '0', null, '40', '2017-09-20 20:36:11');
INSERT INTO `schedule_job_log` VALUES ('190', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '12', '2017-09-20 20:36:11');
INSERT INTO `schedule_job_log` VALUES ('191', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-20 20:37:00');
INSERT INTO `schedule_job_log` VALUES ('192', '3', 'examTest', 'closeExamTest', null, '0', null, '22', '2017-09-20 20:38:00');
INSERT INTO `schedule_job_log` VALUES ('193', '3', 'examTest', 'closeExamTest', null, '0', null, '86', '2017-09-20 20:39:00');
INSERT INTO `schedule_job_log` VALUES ('194', '3', 'examTest', 'closeExamTest', null, '0', null, '16', '2017-09-20 20:40:00');
INSERT INTO `schedule_job_log` VALUES ('195', '3', 'examTest', 'closeExamTest', null, '0', null, '22', '2017-09-20 20:41:00');
INSERT INTO `schedule_job_log` VALUES ('196', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 20:42:00');
INSERT INTO `schedule_job_log` VALUES ('197', '3', 'examTest', 'closeExamTest', null, '0', null, '7', '2017-09-20 20:43:00');
INSERT INTO `schedule_job_log` VALUES ('198', '3', 'examTest', 'closeExamTest', null, '0', null, '30', '2017-09-20 20:45:04');
INSERT INTO `schedule_job_log` VALUES ('199', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '5', '2017-09-20 20:45:04');
INSERT INTO `schedule_job_log` VALUES ('200', '3', 'examTest', 'closeExamTest', null, '0', null, '78', '2017-09-20 20:46:00');
INSERT INTO `schedule_job_log` VALUES ('201', '3', 'examTest', 'closeExamTest', null, '0', null, '20', '2017-09-20 20:47:16');
INSERT INTO `schedule_job_log` VALUES ('202', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-20 20:48:00');
INSERT INTO `schedule_job_log` VALUES ('203', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-20 20:49:00');
INSERT INTO `schedule_job_log` VALUES ('204', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-20 20:50:00');
INSERT INTO `schedule_job_log` VALUES ('205', '3', 'examTest', 'closeExamTest', null, '0', null, '38', '2017-09-20 20:52:42');
INSERT INTO `schedule_job_log` VALUES ('206', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '16', '2017-09-20 20:52:42');
INSERT INTO `schedule_job_log` VALUES ('207', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-20 20:53:00');
INSERT INTO `schedule_job_log` VALUES ('208', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-20 20:54:00');
INSERT INTO `schedule_job_log` VALUES ('209', '3', 'examTest', 'closeExamTest', null, '0', null, '9', '2017-09-20 20:55:00');
INSERT INTO `schedule_job_log` VALUES ('210', '3', 'examTest', 'closeExamTest', null, '0', null, '10', '2017-09-20 20:56:00');
INSERT INTO `schedule_job_log` VALUES ('211', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-20 20:57:00');
INSERT INTO `schedule_job_log` VALUES ('212', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 20:58:00');
INSERT INTO `schedule_job_log` VALUES ('213', '3', 'examTest', 'closeExamTest', null, '0', null, '11', '2017-09-20 20:59:00');
INSERT INTO `schedule_job_log` VALUES ('214', '3', 'examTest', 'closeExamTest', null, '0', null, '32', '2017-09-20 21:00:46');
INSERT INTO `schedule_job_log` VALUES ('215', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '5', '2017-09-20 21:00:46');
INSERT INTO `schedule_job_log` VALUES ('216', '3', 'examTest', 'closeExamTest', null, '0', null, '8', '2017-09-20 21:01:00');
INSERT INTO `schedule_job_log` VALUES ('217', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:02:00');
INSERT INTO `schedule_job_log` VALUES ('218', '3', 'examTest', 'closeExamTest', null, '0', null, '8', '2017-09-20 21:03:00');
INSERT INTO `schedule_job_log` VALUES ('219', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:04:00');
INSERT INTO `schedule_job_log` VALUES ('220', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-20 21:05:00');
INSERT INTO `schedule_job_log` VALUES ('221', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:06:00');
INSERT INTO `schedule_job_log` VALUES ('222', '3', 'examTest', 'closeExamTest', null, '0', null, '13', '2017-09-20 21:07:00');
INSERT INTO `schedule_job_log` VALUES ('223', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-20 21:08:00');
INSERT INTO `schedule_job_log` VALUES ('224', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:09:00');
INSERT INTO `schedule_job_log` VALUES ('225', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:10:00');
INSERT INTO `schedule_job_log` VALUES ('226', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-20 21:11:00');
INSERT INTO `schedule_job_log` VALUES ('227', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-20 21:12:00');
INSERT INTO `schedule_job_log` VALUES ('228', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:13:00');
INSERT INTO `schedule_job_log` VALUES ('229', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-20 21:14:00');
INSERT INTO `schedule_job_log` VALUES ('230', '3', 'examTest', 'closeExamTest', null, '0', null, '7', '2017-09-20 21:15:00');
INSERT INTO `schedule_job_log` VALUES ('231', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:16:00');
INSERT INTO `schedule_job_log` VALUES ('232', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:17:00');
INSERT INTO `schedule_job_log` VALUES ('233', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:18:00');
INSERT INTO `schedule_job_log` VALUES ('234', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:19:00');
INSERT INTO `schedule_job_log` VALUES ('235', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:20:00');
INSERT INTO `schedule_job_log` VALUES ('236', '3', 'examTest', 'closeExamTest', null, '0', null, '7', '2017-09-20 21:21:00');
INSERT INTO `schedule_job_log` VALUES ('237', '3', 'examTest', 'closeExamTest', null, '0', null, '7', '2017-09-20 21:22:00');
INSERT INTO `schedule_job_log` VALUES ('238', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-20 21:23:00');
INSERT INTO `schedule_job_log` VALUES ('239', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-20 21:24:00');
INSERT INTO `schedule_job_log` VALUES ('240', '3', 'examTest', 'closeExamTest', null, '0', null, '8', '2017-09-20 21:25:00');
INSERT INTO `schedule_job_log` VALUES ('241', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-20 21:26:00');
INSERT INTO `schedule_job_log` VALUES ('242', '3', 'examTest', 'closeExamTest', null, '0', null, '11', '2017-09-20 21:27:00');
INSERT INTO `schedule_job_log` VALUES ('243', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-20 21:28:00');
INSERT INTO `schedule_job_log` VALUES ('244', '3', 'examTest', 'closeExamTest', null, '0', null, '8', '2017-09-20 21:29:00');
INSERT INTO `schedule_job_log` VALUES ('245', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:30:00');
INSERT INTO `schedule_job_log` VALUES ('246', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-20 21:31:00');
INSERT INTO `schedule_job_log` VALUES ('247', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-20 21:32:00');
INSERT INTO `schedule_job_log` VALUES ('248', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:33:00');
INSERT INTO `schedule_job_log` VALUES ('249', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-20 21:34:00');
INSERT INTO `schedule_job_log` VALUES ('250', '3', 'examTest', 'closeExamTest', null, '0', null, '26', '2017-09-22 14:09:50');
INSERT INTO `schedule_job_log` VALUES ('251', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '4', '2017-09-22 14:09:50');
INSERT INTO `schedule_job_log` VALUES ('252', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-22 14:10:00');
INSERT INTO `schedule_job_log` VALUES ('253', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-22 14:11:00');
INSERT INTO `schedule_job_log` VALUES ('254', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-22 14:12:00');
INSERT INTO `schedule_job_log` VALUES ('255', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-22 14:13:00');
INSERT INTO `schedule_job_log` VALUES ('256', '3', 'examTest', 'closeExamTest', null, '0', null, '22', '2017-09-22 14:24:34');
INSERT INTO `schedule_job_log` VALUES ('257', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '3', '2017-09-22 14:24:34');
INSERT INTO `schedule_job_log` VALUES ('258', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-22 14:25:00');
INSERT INTO `schedule_job_log` VALUES ('259', '3', 'examTest', 'closeExamTest', null, '0', null, '7', '2017-09-22 14:26:00');
INSERT INTO `schedule_job_log` VALUES ('260', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-22 14:27:00');
INSERT INTO `schedule_job_log` VALUES ('261', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-22 14:28:00');
INSERT INTO `schedule_job_log` VALUES ('262', '3', 'examTest', 'closeExamTest', null, '0', null, '24', '2017-09-22 14:31:35');
INSERT INTO `schedule_job_log` VALUES ('263', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '13', '2017-09-22 14:31:35');
INSERT INTO `schedule_job_log` VALUES ('264', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-22 14:32:00');
INSERT INTO `schedule_job_log` VALUES ('265', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-22 14:33:00');
INSERT INTO `schedule_job_log` VALUES ('266', '3', 'examTest', 'closeExamTest', null, '0', null, '2', '2017-09-22 14:34:00');
INSERT INTO `schedule_job_log` VALUES ('267', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-22 14:35:00');
INSERT INTO `schedule_job_log` VALUES ('268', '3', 'examTest', 'closeExamTest', null, '0', null, '0', '2017-09-22 14:36:00');
INSERT INTO `schedule_job_log` VALUES ('269', '3', 'examTest', 'closeExamTest', null, '0', null, '0', '2017-09-22 14:37:00');
INSERT INTO `schedule_job_log` VALUES ('270', '3', 'examTest', 'closeExamTest', null, '0', null, '12', '2017-09-22 14:38:00');
INSERT INTO `schedule_job_log` VALUES ('271', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-22 14:39:00');
INSERT INTO `schedule_job_log` VALUES ('272', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-22 14:40:00');
INSERT INTO `schedule_job_log` VALUES ('273', '3', 'examTest', 'closeExamTest', null, '0', null, '69', '2017-09-23 11:32:04');
INSERT INTO `schedule_job_log` VALUES ('274', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '32', '2017-09-23 11:32:04');
INSERT INTO `schedule_job_log` VALUES ('275', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-23 11:33:00');
INSERT INTO `schedule_job_log` VALUES ('276', '3', 'examTest', 'closeExamTest', null, '0', null, '8', '2017-09-23 11:34:00');
INSERT INTO `schedule_job_log` VALUES ('277', '3', 'examTest', 'closeExamTest', null, '0', null, '63', '2017-09-23 11:35:00');
INSERT INTO `schedule_job_log` VALUES ('278', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-23 11:36:00');
INSERT INTO `schedule_job_log` VALUES ('279', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-23 11:37:00');
INSERT INTO `schedule_job_log` VALUES ('280', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-23 11:38:00');
INSERT INTO `schedule_job_log` VALUES ('281', '3', 'examTest', 'closeExamTest', null, '0', null, '0', '2017-09-23 11:39:00');
INSERT INTO `schedule_job_log` VALUES ('282', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-23 11:40:00');
INSERT INTO `schedule_job_log` VALUES ('283', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-23 11:41:00');
INSERT INTO `schedule_job_log` VALUES ('284', '3', 'examTest', 'closeExamTest', null, '0', null, '0', '2017-09-23 11:42:00');
INSERT INTO `schedule_job_log` VALUES ('285', '3', 'examTest', 'closeExamTest', null, '0', null, '0', '2017-09-23 11:43:00');
INSERT INTO `schedule_job_log` VALUES ('286', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-23 11:44:00');
INSERT INTO `schedule_job_log` VALUES ('287', '3', 'examTest', 'closeExamTest', null, '0', null, '4', '2017-09-23 11:45:00');
INSERT INTO `schedule_job_log` VALUES ('288', '3', 'examTest', 'closeExamTest', null, '0', null, '0', '2017-09-23 11:46:00');
INSERT INTO `schedule_job_log` VALUES ('289', '3', 'examTest', 'closeExamTest', null, '0', null, '75', '2017-09-23 11:47:00');
INSERT INTO `schedule_job_log` VALUES ('290', '3', 'examTest', 'closeExamTest', null, '0', null, '21', '2017-09-23 11:48:00');
INSERT INTO `schedule_job_log` VALUES ('291', '3', 'examTest', 'closeExamTest', null, '0', null, '16', '2017-09-23 11:50:36');
INSERT INTO `schedule_job_log` VALUES ('292', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '0', '2017-09-23 11:50:36');
INSERT INTO `schedule_job_log` VALUES ('293', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-23 11:51:00');
INSERT INTO `schedule_job_log` VALUES ('294', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-23 11:52:00');
INSERT INTO `schedule_job_log` VALUES ('295', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-23 11:53:00');
INSERT INTO `schedule_job_log` VALUES ('296', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-23 11:54:00');
INSERT INTO `schedule_job_log` VALUES ('297', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-23 11:55:00');
INSERT INTO `schedule_job_log` VALUES ('298', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-23 11:56:00');
INSERT INTO `schedule_job_log` VALUES ('299', '3', 'examTest', 'closeExamTest', null, '0', null, '6', '2017-09-23 11:57:00');
INSERT INTO `schedule_job_log` VALUES ('300', '3', 'examTest', 'closeExamTest', null, '0', null, '2', '2017-09-23 11:58:00');
INSERT INTO `schedule_job_log` VALUES ('301', '3', 'examTest', 'closeExamTest', null, '0', null, '35', '2017-09-23 12:00:37');
INSERT INTO `schedule_job_log` VALUES ('302', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '11', '2017-09-23 12:00:37');
INSERT INTO `schedule_job_log` VALUES ('303', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-23 12:01:00');
INSERT INTO `schedule_job_log` VALUES ('304', '3', 'examTest', 'closeExamTest', null, '0', null, '2', '2017-09-23 12:02:00');
INSERT INTO `schedule_job_log` VALUES ('305', '3', 'examTest', 'closeExamTest', null, '0', null, '49', '2017-09-23 13:05:40');
INSERT INTO `schedule_job_log` VALUES ('306', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '41', '2017-09-23 13:05:40');
INSERT INTO `schedule_job_log` VALUES ('307', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-23 13:06:00');
INSERT INTO `schedule_job_log` VALUES ('308', '3', 'examTest', 'closeExamTest', null, '0', null, '10', '2017-09-23 13:07:00');
INSERT INTO `schedule_job_log` VALUES ('309', '3', 'examTest', 'closeExamTest', null, '0', null, '21', '2017-09-23 13:09:27');
INSERT INTO `schedule_job_log` VALUES ('310', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '3', '2017-09-23 13:09:27');
INSERT INTO `schedule_job_log` VALUES ('311', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-23 13:10:00');
INSERT INTO `schedule_job_log` VALUES ('312', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-23 13:11:00');
INSERT INTO `schedule_job_log` VALUES ('313', '3', 'examTest', 'closeExamTest', null, '0', null, '5', '2017-09-23 13:12:00');
INSERT INTO `schedule_job_log` VALUES ('314', '3', 'examTest', 'closeExamTest', null, '0', null, '3', '2017-09-23 13:13:00');
INSERT INTO `schedule_job_log` VALUES ('315', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '14', '2017-09-23 14:15:02');
INSERT INTO `schedule_job_log` VALUES ('316', '3', 'examTest', 'closeExamTest', null, '0', null, '81', '2017-09-23 14:15:02');
INSERT INTO `schedule_job_log` VALUES ('317', '3', 'examTest', 'closeExamTest', null, '0', null, '17', '2017-09-23 14:16:00');
INSERT INTO `schedule_job_log` VALUES ('318', '3', 'examTest', 'closeExamTest', null, '0', null, '11', '2017-09-23 14:17:00');
INSERT INTO `schedule_job_log` VALUES ('319', '3', 'examTest', 'closeExamTest', null, '0', null, '27', '2017-09-23 14:18:00');
INSERT INTO `schedule_job_log` VALUES ('320', '3', 'examTest', 'closeExamTest', null, '0', null, '51', '2017-09-23 14:19:00');
INSERT INTO `schedule_job_log` VALUES ('321', '3', 'examTest', 'closeExamTest', null, '0', null, '49', '2017-09-23 14:20:00');
INSERT INTO `schedule_job_log` VALUES ('322', '3', 'examTest', 'closeExamTest', null, '0', null, '23', '2017-09-23 14:21:00');
INSERT INTO `schedule_job_log` VALUES ('323', '3', 'examTest', 'closeExamTest', null, '0', null, '60', '2017-09-23 14:24:01');
INSERT INTO `schedule_job_log` VALUES ('324', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '19', '2017-09-23 14:24:01');
INSERT INTO `schedule_job_log` VALUES ('325', '3', 'examTest', 'closeExamTest', null, '0', null, '27', '2017-09-23 14:25:00');
INSERT INTO `schedule_job_log` VALUES ('326', '3', 'examTest', 'closeExamTest', null, '0', null, '47', '2017-09-23 14:26:00');
INSERT INTO `schedule_job_log` VALUES ('327', '3', 'examTest', 'closeExamTest', null, '0', null, '11', '2017-09-23 14:27:00');
INSERT INTO `schedule_job_log` VALUES ('328', '3', 'examTest', 'closeExamTest', null, '0', null, '53', '2017-09-23 14:28:00');
INSERT INTO `schedule_job_log` VALUES ('329', '3', 'examTest', 'closeExamTest', null, '0', null, '12', '2017-09-23 14:29:00');
INSERT INTO `schedule_job_log` VALUES ('330', '3', 'examTest', 'closeExamTest', null, '0', null, '15', '2017-09-23 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('331', '3', 'examTest', 'closeExamTest', null, '0', null, '25', '2017-09-23 14:31:00');
INSERT INTO `schedule_job_log` VALUES ('332', '3', 'examTest', 'closeExamTest', null, '0', null, '72', '2017-09-23 14:32:00');
INSERT INTO `schedule_job_log` VALUES ('333', '3', 'examTest', 'closeExamTest', null, '0', null, '189', '2017-09-23 14:36:22');
INSERT INTO `schedule_job_log` VALUES ('334', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '91', '2017-09-23 14:36:22');
INSERT INTO `schedule_job_log` VALUES ('335', '3', 'examTest', 'closeExamTest', null, '0', null, '12', '2017-09-23 14:37:00');
INSERT INTO `schedule_job_log` VALUES ('336', '3', 'examTest', 'closeExamTest', null, '0', null, '13', '2017-09-23 14:38:00');
INSERT INTO `schedule_job_log` VALUES ('337', '3', 'examTest', 'closeExamTest', null, '0', null, '35', '2017-09-23 14:39:00');
INSERT INTO `schedule_job_log` VALUES ('338', '3', 'examTest', 'closeExamTest', null, '0', null, '23', '2017-09-23 14:40:00');
INSERT INTO `schedule_job_log` VALUES ('339', '3', 'examTest', 'closeExamTest', null, '0', null, '19', '2017-09-23 14:41:00');
INSERT INTO `schedule_job_log` VALUES ('340', '3', 'examTest', 'closeExamTest', null, '0', null, '27', '2017-09-23 14:42:00');
INSERT INTO `schedule_job_log` VALUES ('341', '3', 'examTest', 'closeExamTest', null, '0', null, '14', '2017-09-23 14:43:00');
INSERT INTO `schedule_job_log` VALUES ('342', '3', 'examTest', 'closeExamTest', null, '0', null, '13', '2017-09-23 14:44:00');
INSERT INTO `schedule_job_log` VALUES ('343', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '48', '2017-09-23 14:49:39');
INSERT INTO `schedule_job_log` VALUES ('344', '3', 'examTest', 'closeExamTest', null, '0', null, '46', '2017-09-23 14:49:39');
INSERT INTO `schedule_job_log` VALUES ('345', '3', 'examTest', 'closeExamTest', null, '0', null, '37', '2017-09-23 14:52:17');
INSERT INTO `schedule_job_log` VALUES ('346', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '49', '2017-09-23 14:52:17');
INSERT INTO `schedule_job_log` VALUES ('347', '3', 'examTest', 'closeExamTest', null, '0', null, '12', '2017-09-23 14:53:00');
INSERT INTO `schedule_job_log` VALUES ('348', '3', 'examTest', 'closeExamTest', null, '0', null, '17', '2017-09-23 14:54:00');
INSERT INTO `schedule_job_log` VALUES ('349', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '17', '2017-09-23 15:16:24');
INSERT INTO `schedule_job_log` VALUES ('350', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '21', '2017-09-23 15:39:13');
INSERT INTO `schedule_job_log` VALUES ('351', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '51', '2017-09-23 16:03:10');
INSERT INTO `schedule_job_log` VALUES ('352', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '16', '2017-09-23 16:15:26');
INSERT INTO `schedule_job_log` VALUES ('353', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '15', '2017-09-23 16:42:58');
INSERT INTO `schedule_job_log` VALUES ('354', '4', 'deleteFileTask', 'deleteFile', null, '0', null, '49', '2017-09-23 17:00:22');

-- ----------------------------
-- Table structure for stu_exam_item
-- ----------------------------
DROP TABLE IF EXISTS `stu_exam_item`;
CREATE TABLE `stu_exam_item` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `exam_paper_id` int(20) DEFAULT NULL,
  `item_id` int(20) DEFAULT NULL,
  `stu_id` int(20) DEFAULT NULL,
  `score` int(5) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `class_id` int(20) DEFAULT NULL,
  `exam_test_id` int(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_exam_item
-- ----------------------------
INSERT INTO `stu_exam_item` VALUES ('2', '18', '1008', '11', '10', '2017-09-16 00:37:06', '14905333', '46', '0');
INSERT INTO `stu_exam_item` VALUES ('4', '18', '1003', '11', '3', '2017-09-15 22:23:34', '5', '7', '0');
INSERT INTO `stu_exam_item` VALUES ('6', null, '1003', '11', '3', '2017-09-16 00:36:48', '5', '46', '0');
INSERT INTO `stu_exam_item` VALUES ('7', null, '1004', '11', '10', '2017-09-16 00:38:48', '5', '12', '0');
INSERT INTO `stu_exam_item` VALUES ('8', null, '1036', '11', '333', '2017-09-16 00:39:19', '5', '12', '0');
INSERT INTO `stu_exam_item` VALUES ('9', null, '1008', '29', '10', '2017-09-17 14:19:27', '4', '8', '0');

-- ----------------------------
-- Table structure for stu_grade
-- ----------------------------
DROP TABLE IF EXISTS `stu_grade`;
CREATE TABLE `stu_grade` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `exam_paper_id` int(20) DEFAULT NULL,
  `stu_id` int(20) DEFAULT NULL,
  `class_id` int(20) DEFAULT NULL,
  `score` int(5) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `exam_test_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_grade
-- ----------------------------
INSERT INTO `stu_grade` VALUES ('1', '18', '11', '5', '343', '2017-09-16 00:39:19', '12');
INSERT INTO `stu_grade` VALUES ('2', null, '11', '5', '13', '2017-09-15 22:23:34', '12');
INSERT INTO `stu_grade` VALUES ('3', null, '11', '5', '3', '2017-09-15 22:27:26', '8');
INSERT INTO `stu_grade` VALUES ('4', null, '11', '5', '13', '2017-09-16 00:37:06', '46');
INSERT INTO `stu_grade` VALUES ('5', null, '29', '4', '10', '2017-09-17 14:19:27', '8');

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
  `class_no` int(20) DEFAULT NULL,
  `sort_no` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_info
-- ----------------------------
INSERT INTO `stu_info` VALUES ('10', 'rrrr', '11', '444', '5', '1', null, null);
INSERT INTO `stu_info` VALUES ('11', 'yyy', '12', '777', '5', '1', null, null);
INSERT INTO `stu_info` VALUES ('17', '背对阳光', '21', '222222', '4', '1', null, null);
INSERT INTO `stu_info` VALUES ('18', 'zzz', '22', '1144014', '5', '1', null, null);
INSERT INTO `stu_info` VALUES ('19', '宿命', '23', '1322550', '4', '1', null, null);
INSERT INTO `stu_info` VALUES ('20', 'wwwww', '28', '11111', '4', '1', null, null);
INSERT INTO `stu_info` VALUES ('21', 'ww', '29', '222', '4', '1', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=1042 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_item
-- ----------------------------
INSERT INTO `sys_item` VALUES ('1003', 'BCD比较电路', 'BCD_comp', '<p>eeeeeessaaaa</p>\n', '3', '3', '1', '1465701576', '1', null);
INSERT INTO `sys_item` VALUES ('1004', '统计二进制数中含1数量的组合电路', 'countone', '<p>设计一个逻辑电路，以统计8位二进制数中含1的数量。模块定义为</p>\n\n<p>&nbsp;</p>\n\n<p>module countone(datain,out1)；</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n', '10', '2', '1', '1464873476', '1', null);
INSERT INTO `sys_item` VALUES ('1005', '最大值检测电路', 'max4', '<span style=\"font-family:宋体;font-size:small;\"> </span>\r\n<p style=\"margin:0cm 0cm 0pt;line-height:20pt;mso-line-height-rule:exactly;\">\r\n	<b style=\"mso-bidi-font-weight:normal;\"><span style=\"font-family:宋体;font-size:12pt;mso-ascii-font-family:\"Times New Roman\";mso-hansi-font-family:\"Times New Roman\";\">设计一个</span></b><b style=\"mso-bidi-font-weight:normal;\"><span lang=\"EN-US\" style=\"font-size:12pt;\"><span style=\"font-family:\'Times New Roman\';\">4</span></span></b><b style=\"mso-bidi-font-weight:normal;\"><span style=\"font-family:宋体;font-size:12pt;mso-ascii-font-family:\"Times New Roman\";mso-hansi-font-family:\"Times New Roman\";\">位</span></b><b style=\"mso-bidi-font-weight:normal;\"><span lang=\"EN-US\" style=\"font-size:12pt;\"><span style=\"font-family:\'Times New Roman\';\">4</span></span></b><b style=\"mso-bidi-font-weight:normal;\"><span style=\"font-family:宋体;font-size:12pt;mso-ascii-font-family:\"Times New Roman\";mso-hansi-font-family:\"Times New Roman\";\">输入最大数值检测电路。模块描述为</span></b>\r\n</p>\r\n<span style=\"font-family:宋体;font-size:small;\"> </span>\r\n<p style=\"margin:0cm 0cm 0pt;line-height:20pt;mso-line-height-rule:exactly;\">\r\n	<span lang=\"EN-US\" style=\"font-size:12pt;\"><span style=\"font-family:\'Times New Roman\';\">module max4(a,b,c,d,max);</span></span>\r\n</p>\r\n<span style=\"font-family:宋体;font-size:small;\"> </span>', '10', '2', '1', '1464873496', '1', null);
INSERT INTO `sys_item` VALUES ('1006', '2选1多路选择器', 'mux21', '<p>\r\n	设计一个2选1多路选择器。模块描述为\r\n</p>\r\n<p>\r\n	module mux21(a,b,s,y);\r\n</p>', '10', '1', '1', '1462536844', '1', null);
INSERT INTO `sys_item` VALUES ('1007', '8位比较器设计', 'comp', '<p>设计一个8位比较器电路，比较两个8位数入数 a、b的大小。要求设置三个输出gt、eq、lt，满足：</p>\n\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 当 a&gt;b 时，gt=1，eq=0，lt=0；</p>\n\n<p>&nbsp; &nbsp; &nbsp; &nbsp; 当 a=b 时，gt=0，eq=1，lt=0；</p>\n\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 当 a&lt;b 时，gt=0，eq=0，lt=1；</p>\n\n<p>模块定义为module(a,b,lt,gt,eq);</p>\n\n<p>&nbsp;</p>\n', '10', '2', '1', '1465180091', '1', null);
INSERT INTO `sys_item` VALUES ('1008', '4位计数器设计', 'cnt4', '<p>\r\n	设计一个具有数据加载功能的4位计数器。模块定义为：\r\n</p>\r\n<p>\r\n	module cnt4(clk, rst, d, load, q, cout);\r\n</p>\r\n<p>\r\n	其中d为被加载的4位输入，q为计数输出，cout为进位。\r\n</p>', '10', '2', '5', '1478264551', '1', null);
INSERT INTO `sys_item` VALUES ('1036', '333', '3333', '<p>3333</p>\r\n', '333', '2', '4', '2017-04-23', '1', null);
INSERT INTO `sys_item` VALUES ('1039', '555', '555', '<p>5555</p>\r\n', '55', '4', '1', '2017-07-02', '1', null);
INSERT INTO `sys_item` VALUES ('1040', '88', '8888', '<p>8888</p>\r\n', '88', '1', '1', '2017-09-16', null, null);
INSERT INTO `sys_item` VALUES ('1041', '444', '444', '<p>444</p>\n', '4', '1', '1', '2017-09-17', '1', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

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
INSERT INTO `sys_menu` VALUES ('50', '49', '查看', null, 'teachInfo:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('51', '37', '题目列表', '/sysitem/stuItemList.html', null, '1', null, '0');
INSERT INTO `sys_menu` VALUES ('52', '38', '试卷列表', 'examtest/examtest.html', null, '1', null, '0');
INSERT INTO `sys_menu` VALUES ('53', '29', '考试记录列表', 'examtest/examTestHis.html', null, '1', null, '0');
INSERT INTO `sys_menu` VALUES ('54', '39', '我的成绩', '/stugrade/myGradeList.html', null, '1', null, '0');
INSERT INTO `sys_menu` VALUES ('55', '36', '重置密码', null, 'stuinfo:restPassword', '2', null, '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=460 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('325', '3', '37');
INSERT INTO `sys_role_menu` VALUES ('326', '3', '51');
INSERT INTO `sys_role_menu` VALUES ('327', '3', '38');
INSERT INTO `sys_role_menu` VALUES ('328', '3', '52');
INSERT INTO `sys_role_menu` VALUES ('329', '3', '39');
INSERT INTO `sys_role_menu` VALUES ('330', '3', '54');
INSERT INTO `sys_role_menu` VALUES ('393', '2', '50');
INSERT INTO `sys_role_menu` VALUES ('394', '2', '31');
INSERT INTO `sys_role_menu` VALUES ('395', '2', '32');
INSERT INTO `sys_role_menu` VALUES ('396', '2', '40');
INSERT INTO `sys_role_menu` VALUES ('397', '2', '41');
INSERT INTO `sys_role_menu` VALUES ('398', '2', '42');
INSERT INTO `sys_role_menu` VALUES ('399', '2', '43');
INSERT INTO `sys_role_menu` VALUES ('400', '2', '44');
INSERT INTO `sys_role_menu` VALUES ('401', '2', '34');
INSERT INTO `sys_role_menu` VALUES ('402', '2', '35');
INSERT INTO `sys_role_menu` VALUES ('403', '2', '45');
INSERT INTO `sys_role_menu` VALUES ('404', '2', '46');
INSERT INTO `sys_role_menu` VALUES ('405', '2', '47');
INSERT INTO `sys_role_menu` VALUES ('406', '2', '48');
INSERT INTO `sys_role_menu` VALUES ('407', '2', '30');
INSERT INTO `sys_role_menu` VALUES ('408', '2', '36');
INSERT INTO `sys_role_menu` VALUES ('409', '2', '55');
INSERT INTO `sys_role_menu` VALUES ('410', '2', '29');
INSERT INTO `sys_role_menu` VALUES ('411', '2', '53');
INSERT INTO `sys_role_menu` VALUES ('412', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('413', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('414', '2', '15');
INSERT INTO `sys_role_menu` VALUES ('415', '2', '16');
INSERT INTO `sys_role_menu` VALUES ('416', '2', '17');
INSERT INTO `sys_role_menu` VALUES ('417', '2', '18');
INSERT INTO `sys_role_menu` VALUES ('418', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('419', '2', '19');
INSERT INTO `sys_role_menu` VALUES ('420', '2', '20');
INSERT INTO `sys_role_menu` VALUES ('421', '2', '21');
INSERT INTO `sys_role_menu` VALUES ('422', '2', '22');
INSERT INTO `sys_role_menu` VALUES ('423', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('424', '2', '23');
INSERT INTO `sys_role_menu` VALUES ('425', '2', '24');
INSERT INTO `sys_role_menu` VALUES ('426', '2', '25');
INSERT INTO `sys_role_menu` VALUES ('427', '2', '26');
INSERT INTO `sys_role_menu` VALUES ('428', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('429', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('430', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('431', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('432', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('433', '2', '10');
INSERT INTO `sys_role_menu` VALUES ('434', '2', '11');
INSERT INTO `sys_role_menu` VALUES ('435', '2', '12');
INSERT INTO `sys_role_menu` VALUES ('436', '2', '13');
INSERT INTO `sys_role_menu` VALUES ('437', '2', '14');
INSERT INTO `sys_role_menu` VALUES ('438', '2', '27');
INSERT INTO `sys_role_menu` VALUES ('439', '2', '28');
INSERT INTO `sys_role_menu` VALUES ('440', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('441', '1', '32');
INSERT INTO `sys_role_menu` VALUES ('442', '1', '40');
INSERT INTO `sys_role_menu` VALUES ('443', '1', '41');
INSERT INTO `sys_role_menu` VALUES ('444', '1', '42');
INSERT INTO `sys_role_menu` VALUES ('445', '1', '43');
INSERT INTO `sys_role_menu` VALUES ('446', '1', '44');
INSERT INTO `sys_role_menu` VALUES ('447', '1', '34');
INSERT INTO `sys_role_menu` VALUES ('448', '1', '35');
INSERT INTO `sys_role_menu` VALUES ('449', '1', '45');
INSERT INTO `sys_role_menu` VALUES ('450', '1', '46');
INSERT INTO `sys_role_menu` VALUES ('451', '1', '47');
INSERT INTO `sys_role_menu` VALUES ('452', '1', '48');
INSERT INTO `sys_role_menu` VALUES ('453', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('454', '1', '36');
INSERT INTO `sys_role_menu` VALUES ('455', '1', '55');
INSERT INTO `sys_role_menu` VALUES ('456', '1', '29');
INSERT INTO `sys_role_menu` VALUES ('457', '1', '53');
INSERT INTO `sys_role_menu` VALUES ('458', '1', '37');
INSERT INTO `sys_role_menu` VALUES ('459', '1', '51');

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '超级管理员', 'root@renren.io', '13612345678', '1', '2016-11-11 11:11:11', '2');
INSERT INTO `sys_user` VALUES ('3', 'test001', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '教师一', '123@126.com', '123456789', '1', '2017-01-06 23:09:20', '1');
INSERT INTO `sys_user` VALUES ('4', 'stu01', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '3', '5@qq.com', '152', '1', '2017-02-06 11:23:41', '0');
INSERT INTO `sys_user` VALUES ('5', 'tech01', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '教师一教师一', '33', '33', '1', '2017-02-06 13:53:51', '1');
INSERT INTO `sys_user` VALUES ('11', '444', '3538a1ef2e113da64249eea7bd068b585ec7ce5df73b2d1e319d8c9bf47eb314', '苏铭', null, null, '1', '2017-09-09 15:31:29', '0');
INSERT INTO `sys_user` VALUES ('12', '777', 'eaf89db7108470dc3f6b23ea90618264b3e8f8b6145371667c4055e9c5ce9f52', 'yyy', null, null, '1', '2017-09-09 15:32:56', '0');
INSERT INTO `sys_user` VALUES ('19', '131122542', '3a4359f1ac702b49f8ab82115ceb476d9d65f47753446a576ba2594947a93a11', '李四', null, null, '1', '2017-09-10 14:02:53', '0');
INSERT INTO `sys_user` VALUES ('21', '背对阳光', '4cc8f4d609b717356701c57a03e737e5ac8fe885da8c7163d3de47e01849c635', '背对阳光', null, null, '1', '2017-09-10 16:09:45', '0');
INSERT INTO `sys_user` VALUES ('22', '1144014', '2fb49e86645b9abb4a587bc64fe3b470d07246437664b69576ff644b16c35458', 'zzz', null, null, '1', '2017-09-12 19:57:40', '0');
INSERT INTO `sys_user` VALUES ('23', '宿命', '2ebe7fe091ed9a5c6046b2729d09216f9a6cacefa8e7e031c5121f0bd93d4952', '宿命', null, null, '1', '2017-09-12 19:58:00', '0');
INSERT INTO `sys_user` VALUES ('28', '11111', 'd17f25ecfbcc7857f7bebea469308be0b2580943e96d13a3ad98a13675c4bfc2', 'wwwww', null, null, '1', '2017-09-13 22:33:18', '0');
INSERT INTO `sys_user` VALUES ('29', '222', '9b871512327c09ce91dd649b3f96a63b7408ef267c8cc5710114e629730cb61f', 'ww', null, null, '1', '2017-09-17 14:18:36', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

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
INSERT INTO `sys_user_role` VALUES ('14', '17', '3');
INSERT INTO `sys_user_role` VALUES ('15', '18', '3');
INSERT INTO `sys_user_role` VALUES ('16', '19', '3');
INSERT INTO `sys_user_role` VALUES ('17', '20', '3');
INSERT INTO `sys_user_role` VALUES ('19', '22', '3');
INSERT INTO `sys_user_role` VALUES ('20', '23', '3');
INSERT INTO `sys_user_role` VALUES ('21', '28', '3');
INSERT INTO `sys_user_role` VALUES ('22', '29', '3');
INSERT INTO `sys_user_role` VALUES ('24', '21', '3');
