/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.222
Source Server Version : 50623
Source Host           : 192.168.0.222:3306
Source Database       : project_bespeak

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2016-07-30 15:29:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for aa_template_base
-- ----------------------------
DROP TABLE IF EXISTS `aa_template_base`;
CREATE TABLE `aa_template_base` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `beging` varchar(255) DEFAULT NULL COMMENT '业务字段',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  KEY `main_status` (`main_status`),
  KEY `logic_status` (`logic_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板：业务表';

-- ----------------------------
-- Records of aa_template_base
-- ----------------------------

-- ----------------------------
-- Table structure for aa_template_main
-- ----------------------------
DROP TABLE IF EXISTS `aa_template_main`;
CREATE TABLE `aa_template_main` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `beging` varchar(255) DEFAULT NULL COMMENT '业务字段',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `main_ids` varchar(255) DEFAULT NULL COMMENT 'ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `attr_num` decimal(11,2) DEFAULT NULL COMMENT '备用字段-数字',
  `attr_date` date DEFAULT NULL COMMENT '备用字段-日期',
  `attr_datetime` datetime DEFAULT NULL COMMENT '备用字段-日期时间',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `remark` varchar(2048) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  KEY `main_status` (`main_status`),
  KEY `logic_status` (`logic_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板：业务表';

-- ----------------------------
-- Records of aa_template_main
-- ----------------------------

-- ----------------------------
-- Table structure for account_log
-- ----------------------------
DROP TABLE IF EXISTS `account_log`;
CREATE TABLE `account_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户手机',
  `tel` varchar(255) DEFAULT NULL COMMENT '用户座机',
  `discount` decimal(11,2) DEFAULT NULL COMMENT '折扣',
  `money` decimal(11,2) DEFAULT NULL COMMENT '金额',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `in_or_out` int(2) unsigned DEFAULT NULL COMMENT '收入或支出(1，收入2，支出，3总金额不变)',
  `trade_type_code` varchar(255) DEFAULT NULL COMMENT '交易类型代码',
  `trade_status_code` varchar(255) DEFAULT NULL COMMENT '交易状态代码',
  `fund_opt_type_code` varchar(255) DEFAULT NULL COMMENT '资金操作类型代码',
  `fund_opt_level_code` varchar(255) DEFAULT NULL COMMENT '资金操作等级代码',
  `notice_type_code` varchar(255) DEFAULT NULL COMMENT '通知类型代码',
  `log_type_code` varchar(255) DEFAULT NULL COMMENT '日志记录类型代码',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_way_code` varchar(255) DEFAULT NULL COMMENT '支付方式代码',
  `pay_name` varchar(255) DEFAULT NULL COMMENT '支付方式名称(微信，余额)',
  `trade_user_id` int(255) DEFAULT NULL COMMENT '交易对方ID',
  `trade_username` varchar(255) DEFAULT NULL COMMENT '交易对方名称',
  `trade_user_phone` varchar(255) DEFAULT NULL COMMENT '交易对方手机号码',
  `trade_user_email` varchar(255) DEFAULT NULL COMMENT '交易对方邮箱',
  `trade_user_tel` varchar(255) DEFAULT NULL COMMENT '交易对方电话',
  `avaliable_money_log` decimal(11,2) DEFAULT NULL COMMENT '用户可用资金记录',
  `frozen_money_log` decimal(11,2) DEFAULT NULL COMMENT '用户冻结资金记录',
  `user_integral` int(11) unsigned DEFAULT NULL COMMENT '用户积分',
  `description` varchar(512) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '描述',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串(业务类型 充值 提现)',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商城交易：交易记录表';

-- ----------------------------
-- Records of account_log
-- ----------------------------
INSERT INTO `account_log` VALUES ('1', null, null, null, null, null, null, null, null, null, null, '1', '000', '00', '00', '00', null, '200.00', null, '1', null, null, 'fund_opt_type_freezeMoney', null, null, null, '2016-07-26 11:42:39', null, '微信', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `account_log` VALUES ('2', null, null, null, null, null, null, null, null, null, null, '1', '000', '00', '000', '000', null, '300.00', null, '2', null, null, null, null, null, null, null, null, '微信', null, null, null, null, null, null, null, null, null, null, null, null, null, '充值', null, null, null, null, null, null, null, null, null);
INSERT INTO `account_log` VALUES ('3', null, null, null, null, null, null, null, null, null, null, '1', '888', '8888', '8888', '8888', null, '900.00', null, '1', null, null, null, null, null, null, null, null, '余额', null, null, null, null, null, null, null, null, null, null, null, null, null, '提现', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户手机号码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `qq` varchar(255) DEFAULT NULL COMMENT 'QQ',
  `weixin` varchar(255) DEFAULT NULL COMMENT '微信',
  `role_id` int(11) unsigned DEFAULT NULL COMMENT '角色ID',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='通用：管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', null, null, null, null, null, null, null, null, null, null, 'admin', '21218cca77804d2ba1922c33e0151105', null, '123', null, '123', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-30 14:09:30', null);

-- ----------------------------
-- Table structure for advertisement
-- ----------------------------
DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `cat_id` int(11) unsigned DEFAULT NULL COMMENT '栏目ID（位置ID）',
  `title` varchar(255) DEFAULT NULL COMMENT '发送人名称',
  `description` int(11) unsigned DEFAULT NULL COMMENT '接收人ID',
  `pic_ids` varchar(255) DEFAULT NULL COMMENT '图片ID串',
  `pic_path` varchar(1024) DEFAULT NULL COMMENT '接收人ID串',
  `file_type` int(11) unsigned DEFAULT NULL COMMENT '广告文件类型1:图片 2:第三方广告',
  `width` varchar(255) DEFAULT NULL COMMENT '宽度',
  `height` varchar(255) DEFAULT NULL COMMENT '高度',
  `cell` int(4) DEFAULT NULL COMMENT '占几个位置',
  `hits` int(11) DEFAULT NULL COMMENT '点击数',
  `shows` int(11) DEFAULT NULL COMMENT '展示次数',
  `scans` int(11) DEFAULT NULL COMMENT '浏览次数',
  `to_all` varchar(255) DEFAULT NULL COMMENT '所有人可见',
  `price` decimal(2,0) DEFAULT NULL COMMENT '价格',
  `expiry_date_from` datetime DEFAULT NULL COMMENT '有效期-从',
  `expiry_date_to` varchar(255) DEFAULT NULL COMMENT '有效期-至',
  `is_top` int(4) DEFAULT NULL COMMENT '是否置顶',
  `file_id` int(11) unsigned DEFAULT NULL COMMENT '附件ID',
  `link_url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `sender_id` (`cat_id`),
  KEY `receiver_id` (`description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：广告表';

-- ----------------------------
-- Records of advertisement
-- ----------------------------

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `cat_id` int(11) unsigned DEFAULT NULL COMMENT '栏目ID',
  `title` varchar(1024) DEFAULT NULL COMMENT '标题',
  `sub_title` varchar(1024) DEFAULT NULL COMMENT '子标题',
  `description` text COMMENT '描述',
  `pic_path` varchar(1024) DEFAULT NULL COMMENT '图片路径',
  `pic_id` int(11) unsigned DEFAULT NULL COMMENT '图片ID',
  `institution` varchar(255) DEFAULT NULL COMMENT '机构',
  `link_url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `attr1` varchar(255) DEFAULT NULL COMMENT '自定义属性1',
  `attr2` varchar(255) DEFAULT NULL COMMENT '自定义属性2',
  `is_hot` int(4) unsigned DEFAULT '1' COMMENT '是否热门(1：否，2：是）',
  `is_recommend` int(4) unsigned DEFAULT '1' COMMENT '是否推荐(1：否，2：是）',
  `is_show_index` int(4) unsigned DEFAULT '1' COMMENT '是否首页展示(1：否，2：是）',
  `is_good` int(4) unsigned DEFAULT '1' COMMENT '是否精华(1：否，2：是）',
  `is_top` int(4) unsigned DEFAULT '1' COMMENT '是否置顶(1：否，2：是）',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `author` varchar(11) DEFAULT NULL COMMENT '作者',
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键字',
  `file_id` int(11) unsigned DEFAULT NULL COMMENT '附件ID',
  `scans` int(11) unsigned DEFAULT '0' COMMENT '浏览次数',
  `hits` int(11) unsigned DEFAULT '0' COMMENT '点击次数',
  `allow_comment` tinyint(2) DEFAULT '1' COMMENT '是否允许评论',
  `orgin` varchar(255) DEFAULT NULL COMMENT '来源',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：相册表';

-- ----------------------------
-- Records of album
-- ----------------------------

-- ----------------------------
-- Table structure for album_picture
-- ----------------------------
DROP TABLE IF EXISTS `album_picture`;
CREATE TABLE `album_picture` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `album_id` int(11) unsigned DEFAULT NULL COMMENT '拍卖类型（1：专场，2：微拍，3：即时拍）',
  `pic_id` int(11) unsigned DEFAULT NULL COMMENT '拍卖ID',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `inst_id` int(11) unsigned DEFAULT NULL COMMENT '用户的号牌',
  `is_cover` int(11) unsigned DEFAULT NULL COMMENT '是否显示个人信息',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：相册-图片关联表';

-- ----------------------------
-- Records of album_picture
-- ----------------------------

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `sender_id` int(11) unsigned DEFAULT NULL COMMENT '发送人ID',
  `sender_name` varchar(255) DEFAULT NULL COMMENT '发送人名称',
  `receiver_id` int(11) unsigned DEFAULT NULL COMMENT '接收人ID',
  `receiver_ids` varchar(255) DEFAULT NULL COMMENT '接收人ID串',
  `receiver_group_id` int(11) DEFAULT NULL COMMENT '接收组ID',
  `msg_group` varchar(255) DEFAULT NULL COMMENT '消息类型组',
  `content_type` int(11) unsigned DEFAULT NULL COMMENT '消息类型1:文本消息 2:语音消息 3:视频消息  4:图片消息  5:位置消息  6:文件',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `subject` text COMMENT '主题',
  `content` text COMMENT '内容',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `read_status` int(4) DEFAULT NULL COMMENT '阅读状态',
  `conceal_level` int(11) DEFAULT NULL COMMENT '隐私等级',
  `to_all` varchar(255) DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL COMMENT '有效期',
  `anonymous` varchar(255) DEFAULT NULL COMMENT '是否匿名',
  `is_top` int(4) DEFAULT NULL COMMENT '是否置顶',
  `file_id` int(11) unsigned DEFAULT NULL COMMENT '附件ID',
  `link_url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`),
  KEY `msg_group` (`msg_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：公告表';

-- ----------------------------
-- Records of announcement
-- ----------------------------

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `inst_id` int(11) unsigned DEFAULT NULL COMMENT '机构ID',
  `apply_type` int(11) unsigned DEFAULT NULL COMMENT '申请类型（1专场申请，2论坛申请）',
  `apply_date` datetime DEFAULT NULL COMMENT '申请日期',
  `message` varchar(255) DEFAULT NULL COMMENT '消息',
  `reply_status` varchar(255) DEFAULT NULL COMMENT '回复状态',
  `reply_desc` varchar(255) DEFAULT NULL COMMENT '回复说明',
  `reply_user_id` int(11) DEFAULT NULL COMMENT '回复用户ID',
  `reply_username` varchar(255) DEFAULT NULL COMMENT '回复用户名',
  `file1` varchar(255) DEFAULT NULL COMMENT '附件1',
  `file2` varchar(255) DEFAULT NULL COMMENT '附件2',
  `file3` varchar(255) DEFAULT NULL COMMENT '附件3',
  `file4` varchar(255) DEFAULT NULL COMMENT '附件4',
  `attr1` varchar(255) DEFAULT NULL COMMENT '属性1',
  `attr2` varchar(255) DEFAULT NULL COMMENT '属性2',
  `attr3` varchar(255) DEFAULT NULL COMMENT '属性3',
  `attr4` varchar(255) DEFAULT NULL COMMENT '属性4',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `apply_type` (`apply_type`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `reply_status` (`reply_status`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='通用：申请表';

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES ('1', null, null, null, null, null, null, null, null, null, null, '1', null, '2', '2016-07-11 12:07:01', null, null, null, null, null, null, null, null, null, '1', '1', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `apply` VALUES ('8', null, '1', null, null, null, null, null, null, null, null, '1', null, '2', null, null, null, null, null, null, null, null, null, null, '古玩圈', '6小时', '没有', '没有', null, null, null, null, null, null, null, null, null, null, null, '2016-07-11 14:47:49', 'airson', null, null);
INSERT INTO `apply` VALUES ('9', null, '1', null, null, null, null, null, null, null, null, '1', null, '2', null, null, null, null, null, null, null, null, null, null, '小圈子', '8小时', '没有优势没有优势没有优势', '没有想法', null, null, null, null, null, null, null, null, null, null, null, '2016-07-11 14:50:24', 'airson', null, null);
INSERT INTO `apply` VALUES ('10', null, '1', null, null, null, null, null, null, null, null, '1', null, '2', null, null, null, null, null, null, null, null, null, null, '00', '000', '000', '00000', null, null, null, null, null, null, null, null, null, null, null, '2016-07-11 17:31:35', 'airson', null, null);
INSERT INTO `apply` VALUES ('11', null, '1', null, null, null, null, null, null, null, null, '1', null, '2', null, null, null, null, null, null, null, null, null, null, '000', '000', '000', '0000', null, null, null, null, null, null, null, null, null, null, null, '2016-07-11 17:34:09', 'airson', null, null);
INSERT INTO `apply` VALUES ('12', null, '1', null, null, null, null, null, null, null, null, '1', null, '2', null, null, null, null, null, null, null, null, null, null, '2222', '2222', '22222', '222222', null, null, null, null, null, null, null, null, null, null, null, '2016-07-11 17:34:55', 'airson', null, null);
INSERT INTO `apply` VALUES ('13', null, '1', null, null, null, null, null, null, null, null, '1', null, '2', null, null, null, null, null, null, null, null, null, null, '88888', '8888', '8888', '88888', null, null, null, null, null, null, null, null, null, null, null, '2016-07-11 17:35:56', 'airson', null, null);

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `cat_id` int(11) unsigned DEFAULT NULL COMMENT '栏目ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID（固定位置的文章标识）',
  `title` varchar(1024) DEFAULT '' COMMENT '标题',
  `sub_title` varchar(1024) DEFAULT NULL COMMENT '子标题',
  `description` text COMMENT '描述',
  `pic_paths` varchar(1024) DEFAULT NULL COMMENT '图片路径',
  `content` text COMMENT '内容',
  `organization` varchar(255) DEFAULT NULL COMMENT '公司，团队，机构',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL,
  `link_url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `attr1` varchar(255) DEFAULT NULL COMMENT '自定义属性1',
  `attr2` varchar(255) DEFAULT NULL COMMENT '自定义属性2',
  `is_hot` int(4) unsigned DEFAULT '1' COMMENT '是否热门(1：否，2：是）',
  `is_recommend` int(4) unsigned DEFAULT '1' COMMENT '是否推荐(1：否，2：是）',
  `is_show_index` int(4) unsigned DEFAULT '1' COMMENT '是否首页展示(1：否，2：是）',
  `is_good` int(4) unsigned DEFAULT '1' COMMENT '是否精华(1：否，2：是）',
  `is_top` int(4) unsigned DEFAULT '1' COMMENT '是否置顶(1：否，2：是）',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `author` varchar(11) DEFAULT NULL COMMENT '作者',
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键字',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签',
  `file_id` int(11) unsigned DEFAULT NULL COMMENT '附件ID',
  `file_ids` varchar(255) DEFAULT NULL COMMENT '附件ID串',
  `attach_id` int(11) unsigned DEFAULT NULL COMMENT '附件ID',
  `scans` int(11) unsigned DEFAULT '0' COMMENT '浏览次数',
  `hits` int(11) unsigned DEFAULT '0' COMMENT '点击次数',
  `allow_comment` int(2) DEFAULT '1' COMMENT '是否允许评论',
  `orgin` varchar(255) DEFAULT NULL COMMENT '来源',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `cat_id` (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='通用：文章表';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', null, null, null, null, null, null, null, null, null, null, null, null, '标题', '子标题', '描述', null, '内容', null, null, null, null, null, null, null, '1', '1', '1', '1', '1', null, null, null, null, null, null, null, '0', '0', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for article_picture
-- ----------------------------
DROP TABLE IF EXISTS `article_picture`;
CREATE TABLE `article_picture` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '图片属性商品的id',
  `pic_path` varchar(1024) DEFAULT NULL COMMENT '图片路径',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `cat_id` int(11) unsigned DEFAULT NULL COMMENT '栏目ID',
  `album_id` int(11) unsigned DEFAULT NULL COMMENT '相册ID',
  `is_cover` int(4) unsigned DEFAULT '0' COMMENT '是否为封面图片（主图片）2：是，其他：否',
  `description` varchar(255) DEFAULT NULL COMMENT '图片说明信息',
  `title` varchar(1024) DEFAULT NULL COMMENT '图片名称',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `link_path` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `size` double(2,0) DEFAULT NULL COMMENT '图片大小',
  `ext` varchar(20) DEFAULT NULL COMMENT '图片后缀名',
  `has_watermark` tinyint(2) DEFAULT NULL COMMENT '是否已经水印',
  `grade` int(11) unsigned DEFAULT NULL COMMENT '级别',
  `origin` varchar(255) DEFAULT NULL COMMENT '来源',
  `scans` int(11) unsigned DEFAULT NULL COMMENT '浏览次数',
  `hits` int(11) unsigned DEFAULT NULL COMMENT '点击次数',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：文章（论坛，资讯）图片表';

-- ----------------------------
-- Records of article_picture
-- ----------------------------

-- ----------------------------
-- Table structure for assets
-- ----------------------------
DROP TABLE IF EXISTS `assets`;
CREATE TABLE `assets` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `cat_id` int(11) unsigned DEFAULT NULL COMMENT '栏目ID',
  `role_id` int(11) unsigned DEFAULT NULL COMMENT '固定位置标识ID',
  `title` varchar(1024) DEFAULT NULL COMMENT '名称',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `link_path` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `link_type` int(4) unsigned DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `expiry_date` datetime DEFAULT NULL COMMENT '有效期',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `scans` int(11) unsigned DEFAULT '0' COMMENT '浏览次数',
  `hits` int(11) unsigned DEFAULT '0' COMMENT '点击次数',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：资源表（网站基本资源）';

-- ----------------------------
-- Records of assets
-- ----------------------------

-- ----------------------------
-- Table structure for bonus
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `total_money` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `count` int(11) unsigned DEFAULT NULL COMMENT '红包数量',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '产生时间',
  `deal_time` datetime DEFAULT NULL COMMENT '完成时间',
  `msg` varchar(255) DEFAULT NULL COMMENT '消息',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `link_url` varchar(255) DEFAULT NULL COMMENT '连接地址',
  `link_type` int(11) unsigned DEFAULT NULL COMMENT '链接类型（占用：1用户，2拍卖专场）',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务：红包表';

-- ----------------------------
-- Records of bonus
-- ----------------------------

-- ----------------------------
-- Table structure for bonus_detail
-- ----------------------------
DROP TABLE IF EXISTS `bonus_detail`;
CREATE TABLE `bonus_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `bonus_id` int(11) unsigned DEFAULT NULL COMMENT '红包ID',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `name` varchar(255) DEFAULT NULL COMMENT '名称（用户名，拍场名）',
  `create_time` datetime DEFAULT NULL COMMENT '产生时间',
  `deal_time` datetime DEFAULT NULL COMMENT '完成时间',
  `msg` varchar(255) DEFAULT NULL COMMENT '消息',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `link_url` varchar(255) DEFAULT NULL COMMENT '连接地址',
  `link_type` int(11) unsigned DEFAULT NULL COMMENT '链接类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `bonus_id` (`bonus_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务：红包详情表';

-- ----------------------------
-- Records of bonus_detail
-- ----------------------------

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `shop_id` int(11) unsigned DEFAULT NULL COMMENT '商店ID',
  `session_id` varchar(255) DEFAULT NULL COMMENT 'session_id',
  `goods_id` int(255) unsigned DEFAULT NULL COMMENT '商品的ID,取自表goods的id',
  `goods_sn` varchar(255) DEFAULT NULL COMMENT '商品的货号,取自表goods的goods_sn',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名称,取自表goods的goods_name',
  `shop_price` decimal(11,2) unsigned DEFAULT NULL COMMENT '商品的本店价,取自表goods的shop_price',
  `market_price` decimal(11,2) DEFAULT NULL COMMENT '商品的本店价,取自表市场价',
  `goods_number` int(11) unsigned DEFAULT NULL COMMENT '商品的购买数量,在购物车时,实际库存不减少',
  `goods_attr` varchar(255) DEFAULT NULL COMMENT '商品的扩展属性, 取自goods的extension_code',
  `is_real` tinyint(2) DEFAULT NULL COMMENT '取自goods的is_real',
  `extension_code` varchar(255) DEFAULT NULL COMMENT '商品的扩展属性,取自goods的extension_code',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '该商品的父商品ID,没有该值为0,有的话那该商品就是该id的配件',
  `rec_type` tinyint(2) DEFAULT NULL COMMENT '购物车商品类型(普通，团够，拍卖，夺宝奇兵等)',
  `is_gift` tinyint(2) DEFAULT NULL COMMENT '是否赠品',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城交易：购物车表';

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for charge
-- ----------------------------
DROP TABLE IF EXISTS `charge`;
CREATE TABLE `charge` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `charge_date` datetime DEFAULT NULL COMMENT '充值日期',
  `charge_money` decimal(11,2) DEFAULT NULL COMMENT '充值金额',
  `fee` decimal(11,2) DEFAULT NULL COMMENT '手续费',
  `msg` varchar(512) DEFAULT NULL COMMENT '备注消息',
  `deal_date` datetime DEFAULT NULL COMMENT '处理日期',
  `deal_status` int(11) unsigned DEFAULT '1' COMMENT '处理状态（1发起充值，2充值成功，3取消充值，4充值失败）',
  `app_id` varchar(255) DEFAULT NULL COMMENT '微信支付-公众账号ID',
  `mch_id` varchar(255) DEFAULT NULL COMMENT '微信支付-商户号',
  `nonce_str` varchar(255) DEFAULT NULL COMMENT '微信支付-随机字符串',
  `sign` varchar(255) DEFAULT NULL COMMENT '微信支付-签名',
  `prepay_id` varchar(255) DEFAULT NULL COMMENT '微信支付-预付编号',
  `trade_type` varchar(255) DEFAULT NULL COMMENT '微信支付-交易类型',
  `open_id` varchar(255) DEFAULT NULL COMMENT '微信-用户标识-OPENID',
  `out_trade_no` varchar(255) DEFAULT NULL COMMENT '订单号',
  `pay_type` int(11) DEFAULT NULL COMMENT '直接付款的类型（1交纳保证金，2购买商品）',
  `order_goods_id` int(11) DEFAULT NULL COMMENT '订单商品ID',
  `order_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城交易：充值表';

-- ----------------------------
-- Records of charge
-- ----------------------------

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `sender_id` int(11) unsigned DEFAULT NULL COMMENT '发送人ID',
  `sender_name` varchar(255) DEFAULT NULL COMMENT '发送人名称',
  `receiver_id` int(11) unsigned DEFAULT NULL COMMENT '接收人ID',
  `receiver_ids` varchar(255) DEFAULT NULL COMMENT '接收人ID串',
  `receiver_group_id` int(11) DEFAULT NULL COMMENT '接收组ID',
  `chat_group_id` varchar(255) DEFAULT NULL COMMENT '群组ID',
  `msg_group` varchar(255) DEFAULT NULL COMMENT '消息类型组',
  `content_type` int(11) unsigned DEFAULT NULL COMMENT '消息类型1:文本消息 2:语音消息 3:视频消息  4:图片消息  5:位置消息  6:文件',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `subject` text COMMENT '主题',
  `content` text COMMENT '内容',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `conceal_level` int(11) DEFAULT NULL COMMENT '隐私等级',
  `to_all` varchar(255) DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL COMMENT '有效期',
  `anonymous` varchar(255) DEFAULT NULL COMMENT '是否匿名',
  `is_top` int(4) DEFAULT NULL COMMENT '是否置顶',
  `file_id` int(11) unsigned DEFAULT NULL COMMENT '附件ID',
  `link_url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`),
  KEY `msg_group` (`msg_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：聊天记录表';

-- ----------------------------
-- Records of chat
-- ----------------------------

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `code` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `deal_time` datetime DEFAULT NULL COMMENT '完成时间',
  `msg` varchar(255) DEFAULT NULL COMMENT '消息',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `link_url` varchar(255) DEFAULT NULL COMMENT '连接地址',
  `link_type` int(11) unsigned DEFAULT NULL COMMENT '链接类型（占用：1用户，2拍卖专场）',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`code`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务：代码库表';

-- ----------------------------
-- Records of code
-- ----------------------------

-- ----------------------------
-- Table structure for collect_goods
-- ----------------------------
DROP TABLE IF EXISTS `collect_goods`;
CREATE TABLE `collect_goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '该条收藏记录的会员id',
  `goods_id` int(11) unsigned DEFAULT NULL COMMENT '收藏的商品id',
  `is_attention` tinyint(2) DEFAULT NULL COMMENT '是否关注该收藏商品',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城交易：商品收藏表';

-- ----------------------------
-- Records of collect_goods
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `comment_type_code` varchar(255) DEFAULT NULL COMMENT '数据字典代码：评论类型',
  `object_id` int(11) unsigned DEFAULT NULL COMMENT '文章或者商品的id',
  `content` text COMMENT '内容',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '评论人ID',
  `username` varchar(255) DEFAULT NULL COMMENT '评论人姓名',
  `receiver_id` int(11) unsigned DEFAULT NULL COMMENT '被评论人ID',
  `receiver_name` varchar(255) DEFAULT NULL COMMENT '被评论人名称',
  `user_ip` varchar(255) DEFAULT NULL COMMENT '评论时的IP地址',
  `comment_rank` int(4) unsigned DEFAULT NULL COMMENT '评论打分星级,只有1到5星;其中5代表5星',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '评论的父节点,取值该表的comment_id字段,如果该字段为0,则是一个普通评论,否则该条评论就是该字段的值所对应的评论的回复',
  `is_top` int(4) DEFAULT NULL COMMENT '是否置顶',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `comment_type_id` (`comment_type_code`),
  KEY `object_id` (`object_id`),
  KEY `user_id` (`user_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='通用：评论表';

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', null, null, null, null, null, null, null, null, null, null, 'comment_forumArticle', '1', '内容内容内容内容', '1', '评论人姓名评论人姓名评论人姓名', '2', '被评论人名称被评论人名称', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-11 11:13:54', null, null, null);
INSERT INTO `comment` VALUES ('2', null, null, null, null, null, null, null, null, null, null, 'comment_forumArticle', '1', 'qweqw', '1', 'aaa', '2', 'wqe', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-06 16:15:13', null, null, null);
INSERT INTO `comment` VALUES ('3', null, null, null, null, null, null, null, null, null, null, 'comment_forumArticle', '1', 'er32423', '1', 'gggg', '2', 'rwe', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-12 16:16:27', null, null, null);
INSERT INTO `comment` VALUES ('4', 'cm2016071317395986', '1', null, null, null, null, null, null, null, null, null, '1', '打算打算', '1', 'airson', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-13 17:39:59', 'airson', null, null);
INSERT INTO `comment` VALUES ('5', 'cm2016071317402897', '1', null, null, null, null, null, null, null, null, null, '1', '恶趣味全文', '1', 'airson', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-13 17:40:28', 'airson', null, null);

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `content` varchar(512) DEFAULT NULL COMMENT '充值日期',
  `money` decimal(11,2) DEFAULT NULL COMMENT '金额',
  `discount` int(11) DEFAULT NULL COMMENT '折扣（1-100：除以100）',
  `msg` varchar(512) DEFAULT NULL COMMENT '备注消息',
  `deal_date` datetime DEFAULT NULL COMMENT '处理日期',
  `deal_status` int(11) unsigned DEFAULT '1' COMMENT '处理状态（1发起充值，2充值成功，3取消充值，4充值失败）',
  `user_id` varchar(255) DEFAULT NULL COMMENT '微信支付-公众账号ID',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城交易：优惠券表';

-- ----------------------------
-- Records of coupon
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID（商家）',
  `inst_id` int(11) unsigned DEFAULT NULL COMMENT '机构ID',
  `name` varchar(1024) DEFAULT NULL COMMENT '课程名称',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：课程表';

-- ----------------------------
-- Records of course
-- ----------------------------

-- ----------------------------
-- Table structure for credit
-- ----------------------------
DROP TABLE IF EXISTS `credit`;
CREATE TABLE `credit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `shop_id` int(11) unsigned DEFAULT NULL COMMENT '店铺ID',
  `good_rate_b` decimal(10,2) DEFAULT NULL COMMENT '好评率-作为商家',
  `back_rate_b` decimal(10,2) DEFAULT NULL COMMENT '退货率-作为商家',
  `done_rate_b` decimal(10,2) DEFAULT NULL COMMENT '成交率-作为商家',
  `break_rate_b` decimal(10,2) DEFAULT NULL COMMENT '违约率-作为商家',
  `good_rate_c` decimal(10,2) DEFAULT NULL COMMENT '好评率-作为客户',
  `back_rate_c` decimal(10,2) DEFAULT NULL COMMENT '退货率-作为客户',
  `done_rate_c` decimal(10,2) DEFAULT NULL COMMENT '成交率-作为客户',
  `break_rate_c` decimal(10,2) DEFAULT NULL COMMENT '违约率-作为客户',
  `last_reset_time` datetime DEFAULT NULL,
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `shop_id` (`shop_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务：信誉表';

-- ----------------------------
-- Records of credit
-- ----------------------------

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID（商家）',
  `inst_id` int(11) unsigned DEFAULT NULL COMMENT '机构ID',
  `customer_id` int(11) unsigned DEFAULT NULL COMMENT '客户ID',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：客户表';

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `code` varchar(255) DEFAULT NULL COMMENT '代码(固定的ID标识)',
  `code_name` varchar(255) DEFAULT NULL COMMENT '代码名称(主要向客户展示，易于理解)',
  `parent_code` varchar(255) DEFAULT NULL COMMENT '父代码',
  `dict_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `dict_value` varchar(255) DEFAULT NULL COMMENT '值',
  `dict_type` varchar(255) DEFAULT NULL COMMENT '类型',
  `dict_level` int(4) DEFAULT NULL COMMENT '等级（用户级，系统级等）',
  `second_value` varchar(255) DEFAULT NULL COMMENT '备用值（次值）',
  `dict_desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_leaf` int(2) DEFAULT NULL COMMENT '是否为叶子节点',
  `authority` int(2) DEFAULT '1' COMMENT '权限（1修改，2修改删除,3只读）',
  `expandable` int(2) DEFAULT '1' COMMENT '是否允许增加子节点',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `code` (`code`),
  KEY `parent_code` (`parent_code`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COMMENT='通用：数据字典表';

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('1', '1', null, null, null, null, null, null, null, null, null, 'sys_config', '系统设置', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('2', '2', null, null, null, null, null, null, null, null, null, 'role', '用户角色', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('3', '3', null, null, null, null, null, null, null, null, null, 'sys_data', '官方账号', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('4', '4', null, null, null, null, null, null, null, null, null, 'msg_template', '消息模板', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('5', '5', null, null, null, null, null, null, null, null, null, 'error_type', '错误类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('6', '6', null, null, null, null, null, null, null, null, null, 'apply_type', '申请类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('7', '7', null, null, null, null, null, null, null, null, null, 'trade_type', '交易类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('8', '8', null, null, null, null, null, null, null, null, null, 'fee_type', '费用类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('9', '9', null, null, null, null, null, null, null, null, null, 'article_type', '文章类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('10', '10', null, null, null, null, null, null, null, null, null, 'goods_type', '商品类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('11', '11', null, null, null, null, null, null, null, null, null, 'goods_status', '商品状态', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('12', '12', null, null, null, null, null, null, null, null, null, 'order_status', '订单状态', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('13', '13', null, null, null, null, null, null, null, null, null, 'pay_status', '付款状态', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('14', '14', null, null, null, null, null, null, null, null, null, 'shipping_status', '物流状态', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('15', '15', null, null, null, null, null, null, null, null, null, 'express_status', '快递状态', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('16', '16', null, null, null, null, null, null, null, null, null, 'pay_way', '付款方式', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('17', '17', null, null, null, null, null, null, null, null, null, 'pic_type', '图片类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('18', '18', null, null, null, null, null, null, null, null, null, 'msg_type', '消息类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('19', '19', null, null, null, null, null, null, null, null, null, 'income_type', '收入类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('20', '20', null, null, null, null, null, null, null, null, null, 'money_type', '货币类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('21', '21', null, null, null, null, null, null, null, null, null, 'activity_type', '活动类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('22', '22', null, null, null, null, null, null, null, null, null, 'web_cat', '网站栏目', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('23', '23', null, null, null, null, null, null, null, null, null, 'job', '职业', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('24', '24', null, null, null, null, null, null, null, null, null, 'title', '职称', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('25', '25', null, null, null, null, null, null, null, null, null, 'duty', '职务', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('26', '26', null, null, null, null, null, null, null, null, null, 'department', '部门', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('27', '27', null, null, null, null, null, null, null, null, null, 'goodat', '善长领域', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('28', '28', null, null, null, null, null, null, null, null, null, 'notice_type', '通知类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('29', '29', null, null, null, null, null, null, null, null, null, 'user_group', '用户群组', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('30', '30', null, null, null, null, null, null, null, null, null, 'anno_type', '公告类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('31', '31', null, null, null, null, null, null, null, null, null, 'assets_type', '资源类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('32', '32', null, null, null, null, null, null, null, null, null, 'assets_role', '资源位置', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('33', '33', null, null, null, null, '2016-04-11 14:21:52', null, null, null, null, 'trade_status', '交易状态', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('34', '34', null, null, null, null, '2016-04-11 14:21:52', null, null, null, null, 'fund_opt_type', '资金操作类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('35', '35', null, null, null, null, '2016-04-11 14:21:52', null, null, null, null, 'fund_opt_level', '资金操作等级', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('36', '36', null, null, null, null, '2016-04-11 14:21:52', null, null, null, null, 'log_type', '日志记录类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('37', '37', null, null, null, null, '2016-04-11 14:21:52', null, null, null, null, 'order_done_status', '订单结束状态', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('38', '38', null, null, null, null, '2016-04-11 14:21:52', null, null, null, null, 'comment_type', '评论类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('39', '39', null, null, null, null, '2016-04-11 14:21:52', null, null, null, null, 'auction_status', '拍卖状态', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('40', '40', null, null, null, null, '2016-04-11 14:21:52', null, null, null, null, 'frozenMoney_status', '冻结资金状态', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('41', '41', null, null, null, null, '2016-04-11 14:21:52', null, null, null, null, 'frozenMoney_type', '冻结资金类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('42', '42', null, null, null, null, null, null, null, null, null, 'article_type_news', '新闻资讯', 'article_type', null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('43', '43', null, null, null, null, null, null, null, null, null, 'article_type_activity', '展会活动', 'article_type', null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('44', '44', null, null, null, null, null, null, null, null, null, 'notice_type_sys', '系统通知', 'notice_type', null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('45', null, null, null, null, null, null, null, null, null, null, 'order_status_todo', '订单未完成', 'order_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('46', null, null, null, null, null, null, null, null, null, null, 'order_status_done', '订单已完成', 'order_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('47', null, null, null, null, null, null, null, null, null, null, 'order_status_cancel', '订单已取消', 'order_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('48', null, null, null, null, null, null, null, null, null, null, 'order_status_invalid', '订单无效', 'order_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('49', null, null, null, null, null, null, null, null, null, null, 'order_status_return', '订单已退货', 'order_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('50', null, null, null, null, null, null, null, null, null, null, 'order_status_apply_return', '订单申请退货', 'order_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('51', null, null, null, null, null, null, null, null, null, null, 'shipping_status_todo', '未发货', 'shipping_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('52', null, null, null, null, null, null, null, null, null, null, 'shipping_status_done', '已发货', 'shipping_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('53', null, null, null, null, null, null, null, null, null, null, 'shipping_status_receive', '已收货', 'shipping_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('54', null, null, null, null, null, null, null, null, null, null, 'shipping_status_return', '已退货', 'shipping_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('55', null, null, null, null, null, null, null, null, null, null, 'pay_status_todo', '未付款', 'pay_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('56', null, null, null, null, null, null, null, null, null, null, 'pay_status_ing', '付款中', 'pay_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('57', null, null, null, null, null, null, null, null, null, null, 'pay_status_done', '已付款', 'pay_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('58', null, null, null, null, null, null, null, null, null, null, 'fund_opt_level_sys', '系统操作资金', 'fund_opt_level', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('59', null, null, null, null, null, null, null, null, null, null, 'fund_opt_level_admin', '管理员操作资金', 'fund_opt_level', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('60', null, null, null, null, null, null, null, null, null, null, 'fund_opt_level_user', '用户操作资金', 'fund_opt_level', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('61', null, null, null, null, null, null, null, null, null, null, 'fund_opt_type_freezeMoney', '冻结资金', 'fund_opt_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('62', null, null, null, null, null, null, null, null, null, null, 'fund_opt_type_activateMoney', '解冻资金', 'fund_opt_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('63', null, null, null, null, null, null, null, null, null, null, 'fund_opt_type_incomeMoney', '收入资金至可用余额', 'fund_opt_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('64', null, null, null, null, null, null, null, null, null, null, 'fund_opt_type_outcomeMoney', '支出可用资金', 'fund_opt_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('65', null, null, null, null, null, null, null, null, null, null, 'fund_opt_type_outcomeFrozenMoney', '支出冻结资金', 'fund_opt_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('66', null, null, null, null, null, null, null, null, null, null, 'fund_opt_type_incomeFrozenMoney', '收入资金至冻结余额', 'fund_opt_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('67', null, null, null, null, null, null, null, null, null, null, 'trade_status_todo', '交易未完成', 'trade_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('68', null, null, null, null, null, null, null, null, null, null, 'trade_status_done', '交易已完成', 'trade_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('69', null, null, null, null, null, null, null, null, null, null, 'trade_status_cancel', '交易已取消', 'trade_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('70', null, null, null, null, null, null, null, null, null, null, 'trade_status_invalid', '交易无效', 'trade_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('71', null, null, null, null, null, null, null, null, null, null, 'order_done_status_no', '订单未结束', 'order_done_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('72', null, null, null, null, null, null, null, null, null, null, 'order_done_status_yes', '订单已结束', 'order_done_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('73', null, null, null, null, null, null, null, null, null, null, 'goods_status_sell', '在售', 'goods_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('74', null, null, null, null, null, null, null, null, null, null, 'goods_status_lack', '库存不足', 'goods_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('75', null, null, null, null, null, null, null, null, null, null, 'goods_status_frozen', '冻结中', 'goods_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('76', null, null, null, null, null, null, null, null, null, null, 'goods_status_off', '已下架', 'goods_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('77', null, null, null, null, null, null, null, null, null, null, 'auction_status_waitSubmit', '未提交', 'auction_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('78', null, null, null, null, null, null, null, null, null, null, 'auction_status_verify', '审核中', 'auction_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('79', null, null, null, null, null, null, null, null, null, null, 'auction_status_waitBegin', '未开始', 'auction_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('80', null, null, null, null, null, null, null, null, null, null, 'auction_status_ing', '拍卖中', 'auction_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('81', null, null, null, null, null, null, null, null, null, null, 'auction_status_done', '拍卖结束', 'auction_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('82', null, null, null, null, null, null, null, null, null, null, 'frozenMoney_status_frozen', '冻结中', 'frozenMoney_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('83', null, null, null, null, null, null, null, null, null, null, 'frozenMoney_status_deduct', '已扣除', 'frozenMoney_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('84', null, null, null, null, null, null, null, null, null, null, 'frozenMoney_status_return', '已返还至可用余额', 'frozenMoney_status', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('85', null, null, null, null, null, null, null, null, null, null, 'frozenMoney_type_credit', '诚信保证金', 'frozenMoney_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('86', null, null, null, null, null, null, null, null, null, null, 'frozenMoney_type_goods', '购买商品', 'frozenMoney_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('87', null, null, null, null, null, null, null, null, null, null, 'frozenMoney_type_bonus', '红包', 'frozenMoney_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('88', null, null, null, null, null, null, null, null, null, null, 'auctionProfession_credit', '专场保证金额度', null, null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('89', null, null, null, null, null, null, null, null, null, null, 'goods_type_yushi', '玉石', 'goods_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('90', null, null, null, null, null, null, null, null, null, null, 'goods_type_zhumu', '竹木', 'goods_type', null, '123', null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('91', null, null, null, null, null, null, null, null, null, null, 'goods_type_taochi', '陶瓷', 'goods_type', null, '123', null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('92', null, null, null, null, null, null, null, null, null, null, 'goods_type_shufa', '书法', 'goods_type', null, '123', null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('93', null, null, null, null, null, null, null, null, null, null, 'auctionProfession_credit_100', '100', 'auctionProfession_credit', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('94', null, null, null, null, null, null, null, null, null, null, 'auctionProfession_credit_200', '200', 'auctionProfession_credit', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('95', null, null, null, null, null, null, null, null, null, null, 'auctionProfession_credit_500', '500', 'auctionProfession_credit', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('96', null, null, null, null, null, null, null, null, null, null, 'auctionProfession_credit_1000', '1000', 'auctionProfession_credit', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('97', null, null, null, null, null, null, null, null, null, null, 'auctionProfession_credit_2000', '2000', 'auctionProfession_credit', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('98', null, null, null, null, null, null, null, null, null, null, 'income_amount', '收费金额', null, null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('99', null, null, null, null, null, null, null, null, null, null, 'income_amount_ap_common', '普通专场每件成交收费金额', 'income_amount', null, '3', null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('100', null, null, null, null, null, null, null, null, null, null, 'income_amount_ap_senior', '保证金专场每件成交收费金额', 'income_amount', null, '2', null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('101', null, null, null, null, null, null, null, null, null, null, 'comment_article', '文章评论', 'comment_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('102', null, null, null, null, null, null, null, null, null, null, 'comment_order', '订单评论', 'comment_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('103', null, null, null, null, null, null, null, null, null, null, 'pic_type', '图片类型', null, null, null, null, '2', null, null, null, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('104', null, null, null, null, null, null, null, null, null, null, 'pic_type_goods', '藏品图片', 'pic_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('105', null, null, null, null, null, null, null, null, null, null, 'pic_type_forumArticle', '论坛帖子图片', 'pic_type', null, null, null, '2', null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `dict` VALUES ('106', null, null, null, null, null, null, null, null, null, null, 'comment_forumArticle', '论坛帖子评论', 'comment_type', null, null, null, null, null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for express
-- ----------------------------
DROP TABLE IF EXISTS `express`;
CREATE TABLE `express` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `brief_name` varchar(255) DEFAULT NULL COMMENT '快递公司简称',
  `code` varchar(255) DEFAULT NULL COMMENT '快递公司编号',
  `whole_name` varchar(255) DEFAULT NULL COMMENT '快递公司全称',
  `pinyin_name` varchar(255) DEFAULT NULL COMMENT '快递公司拼音',
  `english_name` varchar(255) DEFAULT NULL COMMENT '快递公司英文名称',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_int` int(11) DEFAULT NULL COMMENT '备用整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=542 DEFAULT CHARSET=utf8 COMMENT='商城交易：快递公司表';

-- ----------------------------
-- Records of express
-- ----------------------------
INSERT INTO `express` VALUES ('1', null, null, null, null, null, null, null, null, null, null, '邮政包裹/平邮', 'youzhengguonei', null, null, null, null, null, null, null, null, null, null, null, null, null, '中国邮政包裹、中国邮政小包、中国邮政平邮，指较大较慢的邮政件，与ems不同，若分不清单号属于哪个公司，建议先拿单号到快递100官网查询', '19', null, null, null, null);
INSERT INTO `express` VALUES ('2', null, null, null, null, null, null, null, null, null, null, '国际包裹', 'youzhengguoji', null, null, null, null, null, null, null, null, null, null, null, null, null, '邮政包裹的国际件，只能查部份从中国寄出或寄往中国的件，若分不清单号属于哪个公司，建议先拿单号到快递100官网查询', '500', null, null, null, null);
INSERT INTO `express` VALUES ('3', null, null, null, null, null, null, null, null, null, null, 'EMS', 'ems', null, null, null, null, null, null, null, null, null, null, null, null, null, '中国邮政EMS、中国邮政速递，指较快的邮政件，与包裹、平邮不同，若分不清单号属于哪个公司，建议先拿单号到快递100官网查询', '20', null, null, null, null);
INSERT INTO `express` VALUES ('4', null, null, null, null, null, null, null, null, null, null, 'EMS-国际件', 'emsguoji', null, null, null, null, null, null, null, null, null, null, null, null, null, '中国邮政EMS、中国邮政速递的国际件，只能查部份从中国寄出或寄往中国的件，若分不清单号属于哪个公司，建议先拿单号到快递100官网查询', '500', null, null, null, null);
INSERT INTO `express` VALUES ('5', null, null, null, null, null, null, null, null, null, null, 'EMS-国际件-英文结果', 'emsinten', null, null, null, null, null, null, null, null, null, null, null, null, null, '邮政EMS、邮政速递的国际件，返回英文结果', '500', null, null, null, null);
INSERT INTO `express` VALUES ('6', null, null, null, null, null, null, null, null, null, null, '北京EMS', 'bjemstckj', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('7', null, null, null, null, null, null, null, null, null, null, '顺丰', 'shunfeng', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '1', null, null, null, null);
INSERT INTO `express` VALUES ('8', null, null, null, null, null, null, null, null, null, null, '申通', 'shentong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '2', null, null, null, null);
INSERT INTO `express` VALUES ('9', null, null, null, null, null, null, null, null, null, null, '圆通', 'yuantong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '3', null, null, null, null);
INSERT INTO `express` VALUES ('10', null, null, null, null, null, null, null, null, null, null, '中通', 'zhongtong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '4', null, null, null, null);
INSERT INTO `express` VALUES ('11', null, null, null, null, null, null, null, null, null, null, '汇通', 'huitongkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '特指百世汇通，百世物流请用baishiwuliu', '5', null, null, null, null);
INSERT INTO `express` VALUES ('12', null, null, null, null, null, null, null, null, null, null, '韵达', 'yunda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '6', null, null, null, null);
INSERT INTO `express` VALUES ('13', null, null, null, null, null, null, null, null, null, null, '宅急送', 'zhaijisong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '8', null, null, null, null);
INSERT INTO `express` VALUES ('14', null, null, null, null, null, null, null, null, null, null, '天天', 'tiantian', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '9', null, null, null, null);
INSERT INTO `express` VALUES ('15', null, null, null, null, null, null, null, null, null, null, '德邦', 'debangwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '10', null, null, null, null);
INSERT INTO `express` VALUES ('16', null, null, null, null, null, null, null, null, null, null, '国通', 'guotongkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '11', null, null, null, null);
INSERT INTO `express` VALUES ('17', null, null, null, null, null, null, null, null, null, null, '增益', 'zengyisudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '12', null, null, null, null);
INSERT INTO `express` VALUES ('18', null, null, null, null, null, null, null, null, null, null, '速尔', 'suer', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '13', null, null, null, null);
INSERT INTO `express` VALUES ('19', null, null, null, null, null, null, null, null, null, null, '中铁物流', 'ztky', null, null, null, null, null, null, null, null, null, null, null, null, null, '历史问题，拼音看起来有误，但其实与名称是对应的', '14', null, null, null, null);
INSERT INTO `express` VALUES ('20', null, null, null, null, null, null, null, null, null, null, '中铁快运', 'zhongtiewuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '历史问题，拼音看起来有误，但其实与名称是对应的', '15', null, null, null, null);
INSERT INTO `express` VALUES ('21', null, null, null, null, null, null, null, null, null, null, '能达', 'ganzhongnengda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '16', null, null, null, null);
INSERT INTO `express` VALUES ('22', null, null, null, null, null, null, null, null, null, null, '优速', 'youshuwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '17', null, null, null, null);
INSERT INTO `express` VALUES ('23', null, null, null, null, null, null, null, null, null, null, '全峰', 'quanfengkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '18', null, null, null, null);
INSERT INTO `express` VALUES ('24', null, null, null, null, null, null, null, null, null, null, '京东', 'jd', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '7', null, null, null, null);
INSERT INTO `express` VALUES ('25', null, null, null, null, null, null, null, null, null, null, 'FedEx-国际', 'fedex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('26', null, null, null, null, null, null, null, null, null, null, 'FedEx-美国', 'fedexus', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('27', null, null, null, null, null, null, null, null, null, null, 'DHL全球件', 'dhlen', null, null, null, null, null, null, null, null, null, null, null, null, null, '英文结果', '510', null, null, null, null);
INSERT INTO `express` VALUES ('28', null, null, null, null, null, null, null, null, null, null, 'DHL', 'dhl', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('29', null, null, null, null, null, null, null, null, null, null, 'DHL-德国', 'dhlde', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('30', null, null, null, null, null, null, null, null, null, null, 'TNT全球件', 'tnten', null, null, null, null, null, null, null, null, null, null, null, null, null, '英文结果', '510', null, null, null, null);
INSERT INTO `express` VALUES ('31', null, null, null, null, null, null, null, null, null, null, 'TNT', 'tnt', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('32', null, null, null, null, null, null, null, null, null, null, 'UPS全球件', 'upsen', null, null, null, null, null, null, null, null, null, null, null, null, null, '英文结果', '510', null, null, null, null);
INSERT INTO `express` VALUES ('33', null, null, null, null, null, null, null, null, null, null, 'UPS', 'ups', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('34', null, null, null, null, null, null, null, null, null, null, 'USPS', 'usps', null, null, null, null, null, null, null, null, null, null, null, null, null, '美国邮政', '510', null, null, null, null);
INSERT INTO `express` VALUES ('35', null, null, null, null, null, null, null, null, null, null, 'DPD', 'dpd', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('36', null, null, null, null, null, null, null, null, null, null, 'DPD Germany', 'dpdgermany', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('37', null, null, null, null, null, null, null, null, null, null, 'DPD Poland', 'dpdpoland', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('38', null, null, null, null, null, null, null, null, null, null, 'DPD UK', 'dpduk', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('39', null, null, null, null, null, null, null, null, null, null, 'GLS', 'gls', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('40', null, null, null, null, null, null, null, null, null, null, 'Toll', 'dpexen', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('41', null, null, null, null, null, null, null, null, null, null, 'Toll Priority(Toll Online)', 'tollpriority', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('42', null, null, null, null, null, null, null, null, null, null, 'Aramex', 'aramex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('43', null, null, null, null, null, null, null, null, null, null, 'DPEX', 'dpex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('44', null, null, null, null, null, null, null, null, null, null, '宅急便', 'zhaijibian', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('45', null, null, null, null, null, null, null, null, null, null, '黑猫雅玛多', 'yamato', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('46', null, null, null, null, null, null, null, null, null, null, '香港邮政(HongKong Post)', 'hkpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('47', null, null, null, null, null, null, null, null, null, null, '英国大包、EMS（Parcel Force）', 'parcelforce', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('48', null, null, null, null, null, null, null, null, null, null, '英国小包（Royal Mail）', 'royalmail', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('49', null, null, null, null, null, null, null, null, null, null, '澳大利亚邮政-英文', 'auspost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('50', null, null, null, null, null, null, null, null, null, null, '加拿大邮政-英文版', 'canpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('51', null, null, null, null, null, null, null, null, null, null, '一统飞鸿', 'yitongfeihong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('52', null, null, null, null, null, null, null, null, null, null, '如风达', 'rufengda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('53', null, null, null, null, null, null, null, null, null, null, '海红网送', 'haihongwangsong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('54', null, null, null, null, null, null, null, null, null, null, '通和天下', 'tonghetianxia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('55', null, null, null, null, null, null, null, null, null, null, '郑州建华', 'zhengzhoujianhua', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('56', null, null, null, null, null, null, null, null, null, null, '红马甲', 'sxhongmajia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('57', null, null, null, null, null, null, null, null, null, null, '芝麻开门', 'zhimakaimen', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('58', null, null, null, null, null, null, null, null, null, null, '乐捷递', 'lejiedi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('59', null, null, null, null, null, null, null, null, null, null, '立即送', 'lijisong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('60', null, null, null, null, null, null, null, null, null, null, '银捷', 'yinjiesudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('61', null, null, null, null, null, null, null, null, null, null, '门对门', 'menduimen', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('62', null, null, null, null, null, null, null, null, null, null, '河北建华', 'hebeijianhua', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('63', null, null, null, null, null, null, null, null, null, null, '微特派', 'weitepai', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('64', null, null, null, null, null, null, null, null, null, null, '风行天下', 'fengxingtianxia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('65', null, null, null, null, null, null, null, null, null, null, '尚橙', 'shangcheng', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('66', null, null, null, null, null, null, null, null, null, null, '新蛋奥硕', 'neweggozzo', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('67', null, null, null, null, null, null, null, null, null, null, '鑫飞鸿', 'xinhongyukuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('68', null, null, null, null, null, null, null, null, null, null, '全一', 'quanyikuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('69', null, null, null, null, null, null, null, null, null, null, '彪记', 'biaojikuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('70', null, null, null, null, null, null, null, null, null, null, '星晨急便', 'xingchengjibian', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('71', null, null, null, null, null, null, null, null, null, null, '亚风', 'yafengsudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('72', null, null, null, null, null, null, null, null, null, null, '源伟丰', 'yuanweifeng', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('73', null, null, null, null, null, null, null, null, null, null, '全日通', 'quanritongkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('74', null, null, null, null, null, null, null, null, null, null, '安信达', 'anxindakuaixi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('75', null, null, null, null, null, null, null, null, null, null, '民航', 'minghangkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('76', null, null, null, null, null, null, null, null, null, null, '凤凰', 'fenghuangkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('77', null, null, null, null, null, null, null, null, null, null, '京广', 'jinguangsudikuaijian', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('78', null, null, null, null, null, null, null, null, null, null, '配思货运', 'peisihuoyunkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('79', null, null, null, null, null, null, null, null, null, null, 'AAE-中国件', 'aae', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('80', null, null, null, null, null, null, null, null, null, null, '大田', 'datianwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('81', null, null, null, null, null, null, null, null, null, null, '新邦', 'xinbangwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('82', null, null, null, null, null, null, null, null, null, null, '龙邦', 'longbanwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('83', null, null, null, null, null, null, null, null, null, null, '一邦', 'yibangwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('84', null, null, null, null, null, null, null, null, null, null, '联昊通', 'lianhaowuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('85', null, null, null, null, null, null, null, null, null, null, '广东邮政', 'guangdongyouzhengwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('86', null, null, null, null, null, null, null, null, null, null, '中邮', 'zhongyouwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('87', null, null, null, null, null, null, null, null, null, null, '天地华宇', 'tiandihuayu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('88', null, null, null, null, null, null, null, null, null, null, '盛辉', 'shenghuiwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('89', null, null, null, null, null, null, null, null, null, null, '长宇', 'changyuwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('90', null, null, null, null, null, null, null, null, null, null, '飞康达', 'feikangda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('91', null, null, null, null, null, null, null, null, null, null, '元智捷诚', 'yuanzhijiecheng', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('92', null, null, null, null, null, null, null, null, null, null, '万家', 'wanjiawuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('93', null, null, null, null, null, null, null, null, null, null, '远成', 'yuanchengwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('94', null, null, null, null, null, null, null, null, null, null, '信丰', 'xinfengwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('95', null, null, null, null, null, null, null, null, null, null, '文捷航空', 'wenjiesudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('96', null, null, null, null, null, null, null, null, null, null, '全晨', 'quanchenkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('97', null, null, null, null, null, null, null, null, null, null, '佳怡', 'jiayiwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('98', null, null, null, null, null, null, null, null, null, null, '快捷', 'kuaijiesudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('99', null, null, null, null, null, null, null, null, null, null, 'D速', 'dsukuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('100', null, null, null, null, null, null, null, null, null, null, '全际通', 'quanjitong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('101', null, null, null, null, null, null, null, null, null, null, '能达', 'ganzhongnengda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('102', null, null, null, null, null, null, null, null, null, null, '青岛安捷', 'anjiekuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('103', null, null, null, null, null, null, null, null, null, null, '越丰', 'yuefengwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('104', null, null, null, null, null, null, null, null, null, null, 'DPEX', 'dpex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('105', null, null, null, null, null, null, null, null, null, null, '急先达', 'jixianda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('106', null, null, null, null, null, null, null, null, null, null, '百福东方', 'baifudongfang', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('107', null, null, null, null, null, null, null, null, null, null, 'BHT', 'bht', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('108', null, null, null, null, null, null, null, null, null, null, '伍圆', 'wuyuansudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('109', null, null, null, null, null, null, null, null, null, null, '蓝镖', 'lanbiaokuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('110', null, null, null, null, null, null, null, null, null, null, 'COE', 'coe', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('111', null, null, null, null, null, null, null, null, null, null, '南京100', 'nanjing', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('112', null, null, null, null, null, null, null, null, null, null, '恒路', 'hengluwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('113', null, null, null, null, null, null, null, null, null, null, '金大', 'jindawuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('114', null, null, null, null, null, null, null, null, null, null, '华夏龙', 'huaxialongwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('115', null, null, null, null, null, null, null, null, null, null, '运通中港', 'yuntongkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('116', null, null, null, null, null, null, null, null, null, null, '佳吉', 'jiajiwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('117', null, null, null, null, null, null, null, null, null, null, '盛丰', 'shengfengwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('118', null, null, null, null, null, null, null, null, null, null, '源安达', 'yuananda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('119', null, null, null, null, null, null, null, null, null, null, '加运美', 'jiayunmeiwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('120', null, null, null, null, null, null, null, null, null, null, '万象', 'wanxiangwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('121', null, null, null, null, null, null, null, null, null, null, '宏品', 'hongpinwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('122', null, null, null, null, null, null, null, null, null, null, '上大', 'shangda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('123', null, null, null, null, null, null, null, null, null, null, '中铁', 'zhongtiewuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '24', null, null, null, null);
INSERT INTO `express` VALUES ('124', null, null, null, null, null, null, null, null, null, null, '原飞航', 'yuanfeihangwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('125', null, null, null, null, null, null, null, null, null, null, '海外环球', 'haiwaihuanqiu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('126', null, null, null, null, null, null, null, null, null, null, '三态', 'santaisudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('127', null, null, null, null, null, null, null, null, null, null, '晋越', 'jinyuekuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('128', null, null, null, null, null, null, null, null, null, null, '联邦', 'lianbangkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('129', null, null, null, null, null, null, null, null, null, null, '飞快达', 'feikuaida', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('130', null, null, null, null, null, null, null, null, null, null, '乐捷递', 'lejiedi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('131', null, null, null, null, null, null, null, null, null, null, '忠信达', 'zhongxinda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('132', null, null, null, null, null, null, null, null, null, null, '芝麻开门', 'zhimakaimen', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('133', null, null, null, null, null, null, null, null, null, null, '海红网送', 'haihongwangsong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('134', null, null, null, null, null, null, null, null, null, null, '共速达', 'gongsuda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('135', null, null, null, null, null, null, null, null, null, null, '嘉里大通', 'jialidatong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('136', null, null, null, null, null, null, null, null, null, null, 'OCS', 'ocs', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('137', null, null, null, null, null, null, null, null, null, null, 'USPS', 'usps', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('138', null, null, null, null, null, null, null, null, null, null, '美国', 'meiguokuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('139', null, null, null, null, null, null, null, null, null, null, '成都立即送', 'lijisong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('140', null, null, null, null, null, null, null, null, null, null, '银捷', 'yinjiesudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('141', null, null, null, null, null, null, null, null, null, null, '门对门', 'menduimen', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('142', null, null, null, null, null, null, null, null, null, null, '递四方', 'disifang', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('143', null, null, null, null, null, null, null, null, null, null, '郑州建华', 'zhengzhoujianhua', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('144', null, null, null, null, null, null, null, null, null, null, '河北建华', 'hebeijianhua', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('145', null, null, null, null, null, null, null, null, null, null, '微特派', 'weitepai', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('146', null, null, null, null, null, null, null, null, null, null, '通和天下', 'tonghetianxia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('147', null, null, null, null, null, null, null, null, null, null, '风行天下', 'fengxingtianxia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('148', null, null, null, null, null, null, null, null, null, null, '康力', 'kangliwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '25', null, null, null, null);
INSERT INTO `express` VALUES ('149', null, null, null, null, null, null, null, null, null, null, '跨越', 'kuayue', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('150', null, null, null, null, null, null, null, null, null, null, '海盟', 'haimengsudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('151', null, null, null, null, null, null, null, null, null, null, '圣安', 'shenganwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('152', null, null, null, null, null, null, null, null, null, null, '中速', 'zhongsukuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('153', null, null, null, null, null, null, null, null, null, null, '新蛋奥硕', 'neweggozzo', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('154', null, null, null, null, null, null, null, null, null, null, 'OnTrac', 'ontrac', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('155', null, null, null, null, null, null, null, null, null, null, '七天连锁', 'sevendays', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('156', null, null, null, null, null, null, null, null, null, null, '明亮', 'mingliangwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('157', null, null, null, null, null, null, null, null, null, null, '凡客配送（作废）', 'vancl', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('158', null, null, null, null, null, null, null, null, null, null, '华企', 'huaqikuaiyun', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('159', null, null, null, null, null, null, null, null, null, null, '城市100', 'city100', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('160', null, null, null, null, null, null, null, null, null, null, '红马甲', 'sxhongmajia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('161', null, null, null, null, null, null, null, null, null, null, '穗佳', 'suijiawuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('162', null, null, null, null, null, null, null, null, null, null, '飞豹', 'feibaokuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('163', null, null, null, null, null, null, null, null, null, null, '传喜', 'chuanxiwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('164', null, null, null, null, null, null, null, null, null, null, '捷特', 'jietekuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('165', null, null, null, null, null, null, null, null, null, null, '隆浪', 'longlangkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('166', null, null, null, null, null, null, null, null, null, null, 'EMS-英文', 'emsen', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('167', null, null, null, null, null, null, null, null, null, null, '中天万运', 'zhongtianwanyun', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('168', null, null, null, null, null, null, null, null, null, null, '邦送', 'bangsongwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('169', null, null, null, null, null, null, null, null, null, null, '澳大利亚(Australia Post)', 'auspost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('170', null, null, null, null, null, null, null, null, null, null, '加拿大(Canada Post)', 'canpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('171', null, null, null, null, null, null, null, null, null, null, '加拿大邮政', 'canpostfr', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('172', null, null, null, null, null, null, null, null, null, null, '顺丰-美国件', 'shunfengen', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('173', null, null, null, null, null, null, null, null, null, null, '汇强', 'huiqiangkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('174', null, null, null, null, null, null, null, null, null, null, '希优特', 'xiyoutekuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('175', null, null, null, null, null, null, null, null, null, null, '昊盛', 'haoshengwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('176', null, null, null, null, null, null, null, null, null, null, '尚橙', 'shangcheng', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('177', null, null, null, null, null, null, null, null, null, null, '亿领', 'yilingsuyun', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('178', null, null, null, null, null, null, null, null, null, null, '大洋', 'dayangwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('179', null, null, null, null, null, null, null, null, null, null, '递达', 'didasuyun', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('180', null, null, null, null, null, null, null, null, null, null, '易通达', 'yitongda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('181', null, null, null, null, null, null, null, null, null, null, '邮必佳', 'youbijia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('182', null, null, null, null, null, null, null, null, null, null, '亿顺航', 'yishunhang', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('183', null, null, null, null, null, null, null, null, null, null, '飞狐', 'feihukuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('184', null, null, null, null, null, null, null, null, null, null, '潇湘晨报', 'xiaoxiangchenbao', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('185', null, null, null, null, null, null, null, null, null, null, '巴伦支', 'balunzhi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('186', null, null, null, null, null, null, null, null, null, null, '闽盛', 'minshengkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('187', null, null, null, null, null, null, null, null, null, null, '佳惠尔', 'syjiahuier', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('188', null, null, null, null, null, null, null, null, null, null, '民邦', 'minbangsudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('189', null, null, null, null, null, null, null, null, null, null, '上海快通', 'shanghaikuaitong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('190', null, null, null, null, null, null, null, null, null, null, '北青小红帽', 'xiaohongmao', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('191', null, null, null, null, null, null, null, null, null, null, 'GSM', 'gsm', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('192', null, null, null, null, null, null, null, null, null, null, '安能', 'annengwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('193', null, null, null, null, null, null, null, null, null, null, 'KCS', 'kcs', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('194', null, null, null, null, null, null, null, null, null, null, 'City-Link', 'citylink', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('195', null, null, null, null, null, null, null, null, null, null, '店通', 'diantongkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('196', null, null, null, null, null, null, null, null, null, null, '凡宇', 'fanyukuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('197', null, null, null, null, null, null, null, null, null, null, '平安达腾飞', 'pingandatengfei', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('198', null, null, null, null, null, null, null, null, null, null, '广东通路', 'guangdongtonglu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('199', null, null, null, null, null, null, null, null, null, null, '中睿', 'zhongruisudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('200', null, null, null, null, null, null, null, null, null, null, '快达', 'kuaidawuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('201', null, null, null, null, null, null, null, null, null, null, '佳吉', 'jiajikuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '26', null, null, null, null);
INSERT INTO `express` VALUES ('202', null, null, null, null, null, null, null, null, null, null, 'ADP国际', 'adp', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '27', null, null, null, null);
INSERT INTO `express` VALUES ('203', null, null, null, null, null, null, null, null, null, null, '颿达国际', 'fardarww', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('204', null, null, null, null, null, null, null, null, null, null, '颿达国际-英文', 'fandaguoji', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('205', null, null, null, null, null, null, null, null, null, null, '林道国际', 'shlindao', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('206', null, null, null, null, null, null, null, null, null, null, '中外运-中文', 'sinoex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('207', null, null, null, null, null, null, null, null, null, null, '中外运', 'zhongwaiyun', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('208', null, null, null, null, null, null, null, null, null, null, '深圳德创', 'dechuangwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('209', null, null, null, null, null, null, null, null, null, null, '林道国际-英文', 'ldxpres', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('210', null, null, null, null, null, null, null, null, null, null, '瑞典（Sweden Post）', 'ruidianyouzheng', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('211', null, null, null, null, null, null, null, null, null, null, 'PostNord(Posten AB)', 'postenab', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('212', null, null, null, null, null, null, null, null, null, null, '偌亚奥国际', 'nuoyaao', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('213', null, null, null, null, null, null, null, null, null, null, '城际', 'chengjisudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('214', null, null, null, null, null, null, null, null, null, null, '祥龙运通', 'xianglongyuntong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('215', null, null, null, null, null, null, null, null, null, null, '品速心达', 'pinsuxinda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('216', null, null, null, null, null, null, null, null, null, null, '宇鑫', 'yuxinwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('217', null, null, null, null, null, null, null, null, null, null, '陪行', 'peixingwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('218', null, null, null, null, null, null, null, null, null, null, '户通', 'hutongwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('219', null, null, null, null, null, null, null, null, null, null, '西安城联', 'xianchengliansudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('220', null, null, null, null, null, null, null, null, null, null, '煜嘉', 'yujiawuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('221', null, null, null, null, null, null, null, null, null, null, '一柒国际', 'yiqiguojiwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('222', null, null, null, null, null, null, null, null, null, null, 'Fedex-国际件-中文', 'fedexcn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('223', null, null, null, null, null, null, null, null, null, null, '联邦-英文', 'lianbangkuaidien', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('224', null, null, null, null, null, null, null, null, null, null, '中通（带电话）', 'zhongtongphone', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('225', null, null, null, null, null, null, null, null, null, null, '赛澳递for买卖宝', 'saiaodimmb', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('226', null, null, null, null, null, null, null, null, null, null, '上海无疆for买卖宝', 'shanghaiwujiangmmb', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('227', null, null, null, null, null, null, null, null, null, null, '新加坡小包(Singapore Post)', 'singpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('228', null, null, null, null, null, null, null, null, null, null, '音素', 'yinsu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('229', null, null, null, null, null, null, null, null, null, null, '南方传媒', 'ndwl', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('230', null, null, null, null, null, null, null, null, null, null, '速呈宅配', 'sucheng', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('231', null, null, null, null, null, null, null, null, null, null, '创一', 'chuangyi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('232', null, null, null, null, null, null, null, null, null, null, '云南滇驿', 'dianyi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('233', null, null, null, null, null, null, null, null, null, null, '重庆星程', 'cqxingcheng', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('234', null, null, null, null, null, null, null, null, null, null, '四川星程', 'scxingcheng', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('235', null, null, null, null, null, null, null, null, null, null, '贵州星程', 'gzxingcheng', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('236', null, null, null, null, null, null, null, null, null, null, '运通中港(作废)', 'ytkd', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('237', null, null, null, null, null, null, null, null, null, null, 'Gati-英文', 'gatien', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('238', null, null, null, null, null, null, null, null, null, null, 'Gati-中文', 'gaticn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('239', null, null, null, null, null, null, null, null, null, null, 'jcex', 'jcex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('240', null, null, null, null, null, null, null, null, null, null, '派尔', 'peex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('241', null, null, null, null, null, null, null, null, null, null, '凯信达', 'kxda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('242', null, null, null, null, null, null, null, null, null, null, '安达信', 'advancing', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('243', null, null, null, null, null, null, null, null, null, null, '汇文', 'huiwen', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('244', null, null, null, null, null, null, null, null, null, null, '亿翔', 'yxexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('245', null, null, null, null, null, null, null, null, null, null, '东红', 'donghong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('246', null, null, null, null, null, null, null, null, null, null, '飞远配送', 'feiyuanvipshop', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('247', null, null, null, null, null, null, null, null, null, null, '好运来', 'hlyex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '28', null, null, null, null);
INSERT INTO `express` VALUES ('248', null, null, null, null, null, null, null, null, null, null, '四川快优达', 'kuaiyouda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('249', null, null, null, null, null, null, null, null, null, null, '日昱', 'riyuwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('250', null, null, null, null, null, null, null, null, null, null, '速通', 'sutongwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('251', null, null, null, null, null, null, null, null, null, null, '晟邦', 'nanjingshengbang', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('252', null, null, null, null, null, null, null, null, null, null, '爱尔兰(An Post)', 'anposten', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('253', null, null, null, null, null, null, null, null, null, null, '日本（Japan Post）', 'japanposten', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('254', null, null, null, null, null, null, null, null, null, null, '丹麦(Post Denmark)', 'postdanmarken', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('255', null, null, null, null, null, null, null, null, null, null, '巴西(Brazil Post/Correios)', 'brazilposten', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('256', null, null, null, null, null, null, null, null, null, null, '荷兰挂号信(PostNL international registered mail)', 'postnlcn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('257', null, null, null, null, null, null, null, null, null, null, '荷兰挂号信(PostNL international registered mail)', 'postnl', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('258', null, null, null, null, null, null, null, null, null, null, '乌克兰EMS-中文(EMS Ukraine)', 'emsukrainecn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('259', null, null, null, null, null, null, null, null, null, null, '乌克兰EMS(EMS Ukraine)', 'emsukraine', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('260', null, null, null, null, null, null, null, null, null, null, '乌克兰邮政包裹', 'ukrpostcn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('261', null, null, null, null, null, null, null, null, null, null, '乌克兰小包、大包(UkrPost)', 'ukrpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('262', null, null, null, null, null, null, null, null, null, null, '海红for买卖宝', 'haihongmmb', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('263', null, null, null, null, null, null, null, null, null, null, 'FedEx-英国件（FedEx UK)', 'fedexuk', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('264', null, null, null, null, null, null, null, null, null, null, 'FedEx-英国件', 'fedexukcn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('265', null, null, null, null, null, null, null, null, null, null, '叮咚', 'dingdong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('266', null, null, null, null, null, null, null, null, null, null, 'UPS Freight', 'upsfreight', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('267', null, null, null, null, null, null, null, null, null, null, 'ABF', 'abf', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('268', null, null, null, null, null, null, null, null, null, null, 'Purolator', 'purolator', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('269', null, null, null, null, null, null, null, null, null, null, '比利时（Bpost）', 'bpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('270', null, null, null, null, null, null, null, null, null, null, '比利时国际(Bpost international)', 'bpostinter', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('271', null, null, null, null, null, null, null, null, null, null, 'LaserShip', 'lasership', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('272', null, null, null, null, null, null, null, null, null, null, 'YODEL', 'yodel', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('273', null, null, null, null, null, null, null, null, null, null, 'DHL-荷兰（DHL Netherlands）', 'dhlnetherlands', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('274', null, null, null, null, null, null, null, null, null, null, 'MyHermes', 'myhermes', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('275', null, null, null, null, null, null, null, null, null, null, 'DPD Germany', 'dpdgermany', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('276', null, null, null, null, null, null, null, null, null, null, 'Fastway Ireland', 'fastway', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('277', null, null, null, null, null, null, null, null, null, null, '法国大包、EMS-法文（Chronopost France）', 'chronopostfra', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('278', null, null, null, null, null, null, null, null, null, null, 'Selektvracht', 'selektvracht', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('279', null, null, null, null, null, null, null, null, null, null, '蓝弧', 'lanhukuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('280', null, null, null, null, null, null, null, null, null, null, '比利时(Belgium Post)', 'belgiumpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('281', null, null, null, null, null, null, null, null, null, null, 'UPS Mail Innovations', 'upsmailinno', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('282', null, null, null, null, null, null, null, null, null, null, '挪威（Posten Norge）', 'postennorge', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('283', null, null, null, null, null, null, null, null, null, null, '瑞士邮政', 'swisspostcn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('284', null, null, null, null, null, null, null, null, null, null, '瑞士(Swiss Post)', 'swisspost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('285', null, null, null, null, null, null, null, null, null, null, '英国邮政小包', 'royalmailcn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('286', null, null, null, null, null, null, null, null, null, null, 'DHL Benelux', 'dhlbenelux', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('287', null, null, null, null, null, null, null, null, null, null, 'Nova Poshta', 'novaposhta', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('288', null, null, null, null, null, null, null, null, null, null, 'DHL-波兰（DHL Poland）', 'dhlpoland', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('289', null, null, null, null, null, null, null, null, null, null, 'Estes', 'estes', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('290', null, null, null, null, null, null, null, null, null, null, 'TNT UK', 'tntuk', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('291', null, null, null, null, null, null, null, null, null, null, 'Deltec Courier', 'deltec', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('292', null, null, null, null, null, null, null, null, null, null, 'OPEK', 'opek', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('293', null, null, null, null, null, null, null, null, null, null, 'DPD Poland', 'dpdpoland', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('294', null, null, null, null, null, null, null, null, null, null, 'Italy SDA', 'italysad', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('295', null, null, null, null, null, null, null, null, null, null, 'MRW', 'mrw', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('296', null, null, null, null, null, null, null, null, null, null, 'Chronopost Portugal', 'chronopostport', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('297', null, null, null, null, null, null, null, null, null, null, '西班牙(Correos de Espa?a)', 'correosdees', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('298', null, null, null, null, null, null, null, null, null, null, 'Direct Link', 'directlink', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('299', null, null, null, null, null, null, null, null, null, null, 'ELTA Hellenic Post', 'eltahell', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('300', null, null, null, null, null, null, null, null, null, null, '捷克（?eská po?ta）', 'ceskaposta', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('301', null, null, null, null, null, null, null, null, null, null, 'Siodemka', 'siodemka', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('302', null, null, null, null, null, null, null, null, null, null, 'International Seur', 'seur', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('303', null, null, null, null, null, null, null, null, null, null, '久易', 'jiuyicn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('304', null, null, null, null, null, null, null, null, null, null, '克罗地亚（Hrvatska Posta）', 'hrvatska', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('305', null, null, null, null, null, null, null, null, null, null, '保加利亚（Bulgarian Posts）', 'bulgarian', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('306', null, null, null, null, null, null, null, null, null, null, 'Portugal Seur', 'portugalseur', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('307', null, null, null, null, null, null, null, null, null, null, 'EC-Firstclass', 'ecfirstclass', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('308', null, null, null, null, null, null, null, null, null, null, 'DTDC India', 'dtdcindia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('309', null, null, null, null, null, null, null, null, null, null, 'Safexpress', 'safexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('310', null, null, null, null, null, null, null, null, null, null, '韩国（Korea Post）', 'koreapost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('311', null, null, null, null, null, null, null, null, null, null, 'TNT Australia', 'tntau', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('312', null, null, null, null, null, null, null, null, null, null, '泰国（Thailand Thai Post）', 'thailand', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('313', null, null, null, null, null, null, null, null, null, null, 'SkyNet Malaysia', 'skynetmalaysia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('314', null, null, null, null, null, null, null, null, null, null, '马来西亚小包（Malaysia Post(Registered)）', 'malaysiapost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('315', null, null, null, null, null, null, null, null, null, null, '马来西亚大包、EMS（Malaysia Post(parcel,EMS)）', 'malaysiaems', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('316', null, null, null, null, null, null, null, null, null, null, '沙特阿拉伯(Saudi Post)', 'saudipost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('317', null, null, null, null, null, null, null, null, null, null, '南非（South African Post Office）', 'southafrican', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('318', null, null, null, null, null, null, null, null, null, null, 'OCA Argentina', 'ocaargen', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('319', null, null, null, null, null, null, null, null, null, null, '尼日利亚(Nigerian Postal)', 'nigerianpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('320', null, null, null, null, null, null, null, null, null, null, '智利(Correos Chile)', 'chile', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('321', null, null, null, null, null, null, null, null, null, null, '以色列(Israel Post)', 'israelpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('322', null, null, null, null, null, null, null, null, null, null, 'Estafeta', 'estafeta', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('323', null, null, null, null, null, null, null, null, null, null, '港快', 'gdkd', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('324', null, null, null, null, null, null, null, null, null, null, '墨西哥（Correos de Mexico）', 'mexico', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('325', null, null, null, null, null, null, null, null, null, null, '罗马尼亚（Posta Romanian）', 'romanian', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('326', null, null, null, null, null, null, null, null, null, null, 'TNT Italy', 'tntitaly', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('327', null, null, null, null, null, null, null, null, null, null, 'Mexico Multipack', 'multipack', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('328', null, null, null, null, null, null, null, null, null, null, '葡萄牙（Portugal CTT）', 'portugalctt', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('329', null, null, null, null, null, null, null, null, null, null, 'Interlink Express', 'interlink', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('330', null, null, null, null, null, null, null, null, null, null, 'DPD UK', 'dpduk', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('331', null, null, null, null, null, null, null, null, null, null, '华航', 'hzpl', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('332', null, null, null, null, null, null, null, null, null, null, 'Gati-KWE', 'gatikwe', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('333', null, null, null, null, null, null, null, null, null, null, 'Red Express', 'redexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('334', null, null, null, null, null, null, null, null, null, null, 'Mexico Senda Express', 'mexicodenda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('335', null, null, null, null, null, null, null, null, null, null, 'TCI XPS', 'tcixps', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('336', null, null, null, null, null, null, null, null, null, null, '高铁', 'hre', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('337', null, null, null, null, null, null, null, null, null, null, '新加坡EMS、大包(Singapore Speedpost)', 'speedpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('338', null, null, null, null, null, null, null, null, null, null, 'EMS-国际件-英文', 'emsinten', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('339', null, null, null, null, null, null, null, null, null, null, 'Asendia USA', 'asendiausa', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('340', null, null, null, null, null, null, null, null, null, null, '法国大包、EMS-英文(Chronopost France)', 'chronopostfren', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('341', null, null, null, null, null, null, null, null, null, null, '意大利(Poste Italiane)', 'italiane', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('342', null, null, null, null, null, null, null, null, null, null, '冠达', 'gda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('343', null, null, null, null, null, null, null, null, null, null, '出口易', 'chukou1', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('344', null, null, null, null, null, null, null, null, null, null, '黄马甲', 'huangmajia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('345', null, null, null, null, null, null, null, null, null, null, '新干线', 'anlexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('346', null, null, null, null, null, null, null, null, null, null, '飞洋', 'shipgce', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('347', null, null, null, null, null, null, null, null, null, null, '贝海国际', 'xlobo', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('348', null, null, null, null, null, null, null, null, null, null, '阿联酋(Emirates Post)', 'emirates', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('349', null, null, null, null, null, null, null, null, null, null, '新顺丰（NSF）', 'nsf', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('350', null, null, null, null, null, null, null, null, null, null, '巴基斯坦(Pakistan Post)', 'pakistan', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('351', null, null, null, null, null, null, null, null, null, null, '世运', 'shiyunkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('352', null, null, null, null, null, null, null, null, null, null, '合众(UCS）', 'ucs', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('353', null, null, null, null, null, null, null, null, null, null, '阿富汗(Afghan Post)', 'afghan', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('354', null, null, null, null, null, null, null, null, null, null, '白俄罗斯(Belpochta)', 'belpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('355', null, null, null, null, null, null, null, null, null, null, '全通', 'quantwl', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('356', null, null, null, null, null, null, null, null, null, null, 'EFS Post', 'efs', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('357', null, null, null, null, null, null, null, null, null, null, 'TNT Post', 'tntpostcn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('358', null, null, null, null, null, null, null, null, null, null, '英脉', 'gml', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('359', null, null, null, null, null, null, null, null, null, null, '广通', 'gtongsudi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('360', null, null, null, null, null, null, null, null, null, null, '东瀚', 'donghanwl', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('361', null, null, null, null, null, null, null, null, null, null, 'rpx', 'rpx', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('362', null, null, null, null, null, null, null, null, null, null, '日日顺', 'rrs', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('363', null, null, null, null, null, null, null, null, null, null, '华通', 'htongexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('364', null, null, null, null, null, null, null, null, null, null, '吉尔吉斯斯坦(Kyrgyz Post)', 'kyrgyzpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('365', null, null, null, null, null, null, null, null, null, null, '拉脱维亚(Latvijas Pasts)', 'latvia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('366', null, null, null, null, null, null, null, null, null, null, '黎巴嫩(Liban Post)', 'libanpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('367', null, null, null, null, null, null, null, null, null, null, '立陶宛（Lietuvos pa?tas）', 'lithuania', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('368', null, null, null, null, null, null, null, null, null, null, '马尔代夫(Maldives Post)', 'maldives', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('369', null, null, null, null, null, null, null, null, null, null, '马耳他（Malta Post）', 'malta', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('370', null, null, null, null, null, null, null, null, null, null, '马其顿(Macedonian Post)', 'macedonia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('371', null, null, null, null, null, null, null, null, null, null, '新西兰（New Zealand Post）', 'newzealand', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('372', null, null, null, null, null, null, null, null, null, null, '摩尔多瓦(Posta Moldovei)', 'moldova', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('373', null, null, null, null, null, null, null, null, null, null, '孟加拉国(EMS)', 'bangladesh', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('374', null, null, null, null, null, null, null, null, null, null, '塞尔维亚(PE Post of Serbia)', 'serbia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('375', null, null, null, null, null, null, null, null, null, null, '塞浦路斯(Cyprus Post)', 'cypruspost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('376', null, null, null, null, null, null, null, null, null, null, '突尼斯EMS(Rapid-Poste)', 'tunisia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('377', null, null, null, null, null, null, null, null, null, null, '乌兹别克斯坦(Post of Uzbekistan)', 'uzbekistan', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('378', null, null, null, null, null, null, null, null, null, null, '新喀里多尼亚[法国](New Caledonia)', 'caledonia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('379', null, null, null, null, null, null, null, null, null, null, '叙利亚(Syrian Post)', 'republic', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('380', null, null, null, null, null, null, null, null, null, null, '亚美尼亚(Haypost-Armenian Postal)', 'haypost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('381', null, null, null, null, null, null, null, null, null, null, '也门(Yemen Post)', 'yemen', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('382', null, null, null, null, null, null, null, null, null, null, '印度(India Post)', 'india', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('383', null, null, null, null, null, null, null, null, null, null, '英国(大包,EMS)', 'england', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('384', null, null, null, null, null, null, null, null, null, null, '约旦(Jordan Post)', 'jordan', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('385', null, null, null, null, null, null, null, null, null, null, '越南小包(Vietnam Posts)', 'vietnam', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('386', null, null, null, null, null, null, null, null, null, null, '黑山(Po?ta Crne Gore)', 'montenegro', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('387', null, null, null, null, null, null, null, null, null, null, '哥斯达黎加(Correos de Costa Rica)', 'correos', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('388', null, null, null, null, null, null, null, null, null, null, '西安喜来', 'xilaikd', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('389', null, null, null, null, null, null, null, null, null, null, '格陵兰[丹麦]（TELE Greenland A/S）', 'greenland', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('390', null, null, null, null, null, null, null, null, null, null, '菲律宾（Philippine Postal）', 'phlpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('391', null, null, null, null, null, null, null, null, null, null, '厄瓜多尔(Correos del Ecuador)', 'ecuador', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('392', null, null, null, null, null, null, null, null, null, null, '冰岛(Iceland Post)', 'iceland', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('393', null, null, null, null, null, null, null, null, null, null, '波兰小包(Poczta Polska)', 'emonitoring', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('394', null, null, null, null, null, null, null, null, null, null, '阿尔巴尼亚(Posta shqipatre)', 'albania', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('395', null, null, null, null, null, null, null, null, null, null, '阿鲁巴[荷兰]（Post Aruba）', 'aruba', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('396', null, null, null, null, null, null, null, null, null, null, '埃及（Egypt Post）', 'egypt', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('397', null, null, null, null, null, null, null, null, null, null, '爱尔兰(An Post)', 'ireland', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('398', null, null, null, null, null, null, null, null, null, null, '爱沙尼亚(Eesti Post)', 'omniva', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('399', null, null, null, null, null, null, null, null, null, null, '云豹国际货运', 'leopard', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('400', null, null, null, null, null, null, null, null, null, null, '中外运空运', 'sinoairinex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('401', null, null, null, null, null, null, null, null, null, null, '上海昊宏国际货物', 'hyk', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('402', null, null, null, null, null, null, null, null, null, null, '城晓国际', 'ckeex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('403', null, null, null, null, null, null, null, null, null, null, '匈牙利（Magyar Posta）', 'hungary', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('404', null, null, null, null, null, null, null, null, null, null, '澳门(Macau Post)', 'macao', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('405', null, null, null, null, null, null, null, null, null, null, '台湾（中华邮政）', 'postserv', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('406', null, null, null, null, null, null, null, null, null, null, '北京EMS', 'bjemstckj', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('407', null, null, null, null, null, null, null, null, null, null, '快淘', 'kuaitao', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '23', null, null, null, null);
INSERT INTO `express` VALUES ('408', null, null, null, null, null, null, null, null, null, null, '秘鲁(SERPOST)', 'peru', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('409', null, null, null, null, null, null, null, null, null, null, '印度尼西亚EMS(Pos Indonesia-EMS)', 'indonesia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('410', null, null, null, null, null, null, null, null, null, null, '哈萨克斯坦(Kazpost)', 'kazpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('411', null, null, null, null, null, null, null, null, null, null, '立白宝凯', 'lbbk', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('412', null, null, null, null, null, null, null, null, null, null, '百千诚', 'bqcwl', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('413', null, null, null, null, null, null, null, null, null, null, '皇家', 'pfcexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('414', null, null, null, null, null, null, null, null, null, null, '法国(La Poste)', 'csuivi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('415', null, null, null, null, null, null, null, null, null, null, '奥地利(Austrian Post)', 'austria', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('416', null, null, null, null, null, null, null, null, null, null, '乌克兰小包、大包(UkrPoshta)', 'ukraine', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('417', null, null, null, null, null, null, null, null, null, null, '乌干达(Posta Uganda)', 'uganda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('418', null, null, null, null, null, null, null, null, null, null, '阿塞拜疆EMS(EMS AzerExpressPost)', 'azerbaijan', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('419', null, null, null, null, null, null, null, null, null, null, '芬兰(Itella Posti Oy)', 'finland', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('420', null, null, null, null, null, null, null, null, null, null, '斯洛伐克(Slovenská Posta)', 'slovak', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('421', null, null, null, null, null, null, null, null, null, null, '埃塞俄比亚(Ethiopian postal)', 'ethiopia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('422', null, null, null, null, null, null, null, null, null, null, '卢森堡(Luxembourg Post)', 'luxembourg', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('423', null, null, null, null, null, null, null, null, null, null, '毛里求斯(Mauritius Post)', 'mauritius', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('424', null, null, null, null, null, null, null, null, null, null, '文莱(Brunei Postal)', 'brunei', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('425', null, null, null, null, null, null, null, null, null, null, 'Quantium', 'quantium', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('426', null, null, null, null, null, null, null, null, null, null, '坦桑尼亚(Tanzania Posts)', 'tanzania', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('427', null, null, null, null, null, null, null, null, null, null, '阿曼(Oman Post)', 'oman', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('428', null, null, null, null, null, null, null, null, null, null, '直布罗陀[英国]( Royal Gibraltar Post)', 'gibraltar', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('429', null, null, null, null, null, null, null, null, null, null, '博源恒通', 'byht', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('430', null, null, null, null, null, null, null, null, null, null, '越南EMS(VNPost Express)', 'vnpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('431', null, null, null, null, null, null, null, null, null, null, '安迅', 'anxl', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('432', null, null, null, null, null, null, null, null, null, null, '达方', 'dfpost', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('433', null, null, null, null, null, null, null, null, null, null, '兰州伙伴', 'huoban', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('434', null, null, null, null, null, null, null, null, null, null, '天纵', 'tianzong', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('435', null, null, null, null, null, null, null, null, null, null, '波黑(JP BH Posta)', 'bohei', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('436', null, null, null, null, null, null, null, null, null, null, '玻利维亚', 'bolivia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('437', null, null, null, null, null, null, null, null, null, null, '柬埔寨(Cambodia Post)', 'cambodia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('438', null, null, null, null, null, null, null, null, null, null, '巴林(Bahrain Post)', 'bahrain', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('439', null, null, null, null, null, null, null, null, null, null, '纳米比亚(NamPost)', 'namibia', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('440', null, null, null, null, null, null, null, null, null, null, '卢旺达(Rwanda i-posita)', 'rwanda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('441', null, null, null, null, null, null, null, null, null, null, '莱索托(Lesotho Post)', 'lesotho', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('442', null, null, null, null, null, null, null, null, null, null, '肯尼亚(POSTA KENYA)', 'kenya', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('443', null, null, null, null, null, null, null, null, null, null, '喀麦隆(CAMPOST)', 'cameroon', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('444', null, null, null, null, null, null, null, null, null, null, '伯利兹(Belize Postal)', 'belize', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('445', null, null, null, null, null, null, null, null, null, null, '巴拉圭(Correo Paraguayo)', 'paraguay', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '510', null, null, null, null);
INSERT INTO `express` VALUES ('446', null, null, null, null, null, null, null, null, null, null, '十方通', 'sfift', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('447', null, null, null, null, null, null, null, null, null, null, '飞鹰', 'hnfy', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('448', null, null, null, null, null, null, null, null, null, null, 'UPS i-parcle', 'iparcel', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('449', null, null, null, null, null, null, null, null, null, null, '鑫锐达', 'bjxsrd', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('450', null, null, null, null, null, null, null, null, null, null, '麦力', 'mailikuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('451', null, null, null, null, null, null, null, null, null, null, '瑞丰', 'rfsd', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('452', null, null, null, null, null, null, null, null, null, null, '美联', 'letseml', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('453', null, null, null, null, null, null, null, null, null, null, 'CNPEX中邮', 'cnpex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('454', null, null, null, null, null, null, null, null, null, null, '鑫世锐达', 'xsrd', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('455', null, null, null, null, null, null, null, null, null, null, '同舟行', 'chinatzx', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('456', null, null, null, null, null, null, null, null, null, null, '秦邦', 'qbexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('457', null, null, null, null, null, null, null, null, null, null, '大达', 'idada', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('458', null, null, null, null, null, null, null, null, null, null, 'skynet', 'skynet', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('459', null, null, null, null, null, null, null, null, null, null, '红马', 'nedahm', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('460', null, null, null, null, null, null, null, null, null, null, '云南中诚', 'czwlyn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('461', null, null, null, null, null, null, null, null, null, null, '万博', 'wanboex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('462', null, null, null, null, null, null, null, null, null, null, '腾达', 'nntengda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('463', null, null, null, null, null, null, null, null, null, null, '郑州速捷', 'sujievip', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('464', null, null, null, null, null, null, null, null, null, null, 'UBI Australia', 'gotoubi', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('465', null, null, null, null, null, null, null, null, null, null, 'ECMS Express', 'ecmsglobal', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('466', null, null, null, null, null, null, null, null, null, null, '速派(FastGo)', 'fastgo', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('467', null, null, null, null, null, null, null, null, null, null, '易客满', 'ecmscn', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('468', null, null, null, null, null, null, null, null, null, null, '俄顺达', 'eshunda', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('469', null, null, null, null, null, null, null, null, null, null, '广东速腾', 'suteng', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('470', null, null, null, null, null, null, null, null, null, null, '新鹏', 'gdxp', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('471', null, null, null, null, null, null, null, null, null, null, '美国韵达', 'yundaexus', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('472', null, null, null, null, null, null, null, null, null, null, '深圳DPEX', 'szdpex', null, null, null, null, null, null, null, null, null, null, null, null, null, '', '500', null, null, null, null);
INSERT INTO `express` VALUES ('473', null, null, null, null, null, null, null, null, null, null, '百世', 'baishiwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, '特指百世物流、百世快运，百世汇通请用huitongkuaidi', '500', null, null, null, null);
INSERT INTO `express` VALUES ('474', null, null, null, null, null, null, null, null, null, null, '荷兰包裹(PostNL International Parcels)', 'postnlpacle', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('475', null, null, null, null, null, null, null, null, null, null, '乐天', 'ltexp', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('476', null, null, null, null, null, null, null, null, null, null, '智通', 'ztong', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('477', null, null, null, null, null, null, null, null, null, null, '鑫通宝', 'xtb', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('478', null, null, null, null, null, null, null, null, null, null, 'airpak expresss', 'airpak', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('479', null, null, null, null, null, null, null, null, null, null, '荷兰邮政-中国件', 'postnlchina', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('480', null, null, null, null, null, null, null, null, null, null, '法国小包（colissimo）', 'colissimo', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('481', null, null, null, null, null, null, null, null, null, null, 'PCA Express', 'pcaexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('482', null, null, null, null, null, null, null, null, null, null, '韩润', 'hanrun', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('483', null, null, null, null, null, null, null, null, null, null, '中远e环球', 'cosco', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('484', null, null, null, null, null, null, null, null, null, null, '顺达', 'sundarexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('485', null, null, null, null, null, null, null, null, null, null, '捷记方舟', 'ajexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('486', null, null, null, null, null, null, null, null, null, null, '方舟', 'arkexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('487', null, null, null, null, null, null, null, null, null, null, '明大', 'adaexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('488', null, null, null, null, null, null, null, null, null, null, '长江国际', 'changjiang', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('489', null, null, null, null, null, null, null, null, null, null, '八达通', 'bdatong', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('490', null, null, null, null, null, null, null, null, null, null, '美国申通', 'stoexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('491', null, null, null, null, null, null, null, null, null, null, '泛捷国际', 'epanex', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('492', null, null, null, null, null, null, null, null, null, null, '顺捷丰达', 'shunjiefengda', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('493', null, null, null, null, null, null, null, null, null, null, '华赫', 'nmhuahe', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('494', null, null, null, null, null, null, null, null, null, null, '德国(Deutsche Post)', 'deutschepost', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('495', null, null, null, null, null, null, null, null, null, null, '百腾', 'baitengwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('496', null, null, null, null, null, null, null, null, null, null, '品骏', 'pjbest', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('497', null, null, null, null, null, null, null, null, null, null, '全速通', 'quansutong', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('498', null, null, null, null, null, null, null, null, null, null, '中技', 'zhongjiwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('499', null, null, null, null, null, null, null, null, null, null, '九曳供应链', 'jiuyescm', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('500', null, null, null, null, null, null, null, null, null, null, '天翼', 'tykd', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('501', null, null, null, null, null, null, null, null, null, null, '德意思', 'dabei', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('502', null, null, null, null, null, null, null, null, null, null, '城际', 'chengji', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('503', null, null, null, null, null, null, null, null, null, null, '程光', 'chengguangkuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('504', null, null, null, null, null, null, null, null, null, null, '佐川急便', 'sagawa', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('505', null, null, null, null, null, null, null, null, null, null, '蓝天', 'lantiankuaidi', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('506', null, null, null, null, null, null, null, null, null, null, '永昌', 'yongchangwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('507', null, null, null, null, null, null, null, null, null, null, '笨鸟海淘', 'birdex', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('508', null, null, null, null, null, null, null, null, null, null, '一正达', 'yizhengdasuyun', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('509', null, null, null, null, null, null, null, null, null, null, '京东订单', 'jdorder', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '21', null, null, null, null);
INSERT INTO `express` VALUES ('510', null, null, null, null, null, null, null, null, null, null, '优配', 'sdyoupei', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('511', null, null, null, null, null, null, null, null, null, null, 'TRAKPAK', 'trakpak', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('512', null, null, null, null, null, null, null, null, null, null, 'GTS', 'gts', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('513', null, null, null, null, null, null, null, null, null, null, 'AOL澳通', 'aolau', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('514', null, null, null, null, null, null, null, null, null, null, '宜送', 'yiex', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('515', null, null, null, null, null, null, null, null, null, null, '通达兴', 'tongdaxing', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('516', null, null, null, null, null, null, null, null, null, null, '香港(HongKong Post)英文', 'hkposten', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('517', null, null, null, null, null, null, null, null, null, null, '苏宁订单', 'suningorder', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('518', null, null, null, null, null, null, null, null, null, null, '飞力士', 'flysman', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('519', null, null, null, null, null, null, null, null, null, null, '转运四方', 'zhuanyunsifang', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('520', null, null, null, null, null, null, null, null, null, null, 'logen路坚', 'ilogen', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('521', null, null, null, null, null, null, null, null, null, null, '成都东骏', 'dongjun', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('522', null, null, null, null, null, null, null, null, null, null, '日本郵便', 'japanpost', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('523', null, null, null, null, null, null, null, null, null, null, '佳家通货运', 'jiajiatong56', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('524', null, null, null, null, null, null, null, null, null, null, '吉日优派', 'jrypex', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('525', null, null, null, null, null, null, null, null, null, null, '西安胜峰', 'xaetc', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('526', null, null, null, null, null, null, null, null, null, null, 'CJ', 'doortodoor', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('527', null, null, null, null, null, null, null, null, null, null, '信天捷', 'xintianjie', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('528', null, null, null, null, null, null, null, null, null, null, '泰国138国际', 'sd138', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('529', null, null, null, null, null, null, null, null, null, null, '猴急送', 'hjs', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('530', null, null, null, null, null, null, null, null, null, null, '全信通', 'quanxintong', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('531', null, null, null, null, null, null, null, null, null, null, 'amazon-国际订单', 'amusorder', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('532', null, null, null, null, null, null, null, null, null, null, '骏丰国际', 'junfengguoji', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('533', null, null, null, null, null, null, null, null, null, null, '货运皇', 'kingfreight', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('534', null, null, null, null, null, null, null, null, null, null, '远成', 'ycexpress', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('535', null, null, null, null, null, null, null, null, null, null, '速必达', 'subida', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('536', null, null, null, null, null, null, null, null, null, null, '特急便', 'sucmj', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('537', null, null, null, null, null, null, null, null, null, null, '亚马逊中国', 'yamaxunwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '22', null, null, null, null);
INSERT INTO `express` VALUES ('538', null, null, null, null, null, null, null, null, null, null, '锦程', 'jinchengwuliu', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('539', null, null, null, null, null, null, null, null, null, null, '景光', 'jgwl', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('540', null, null, null, null, null, null, null, null, null, null, '御风', 'yufeng', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);
INSERT INTO `express` VALUES ('541', null, null, null, null, null, null, null, null, null, null, '至诚通达', 'zhichengtongda', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '500', null, null, null, null);

-- ----------------------------
-- Table structure for fans
-- ----------------------------
DROP TABLE IF EXISTS `fans`;
CREATE TABLE `fans` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID(被关注人）',
  `fans_id` int(11) DEFAULT NULL COMMENT '粉丝ID(关注人）',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  KEY `main_status` (`main_status`),
  KEY `logic_status` (`logic_status`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通用：粉丝好友表';

-- ----------------------------
-- Records of fans
-- ----------------------------
INSERT INTO `fans` VALUES ('1', null, null, null, null, null, null, null, null, null, null, '2', '2', null, null, null, null, null, null, null, null, null, null, null, '2016-06-29 10:25:03', 'airson', null, null);
INSERT INTO `fans` VALUES ('2', null, null, null, null, null, null, null, null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, '2016-07-03 11:52:32', 'airson', null, null);

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '上级论坛ID',
  `name` varchar(50) DEFAULT NULL COMMENT '论坛名称',
  `logo` varchar(255) DEFAULT NULL COMMENT '论坛图标',
  `moderator_id` int(11) unsigned DEFAULT NULL COMMENT '版主id',
  `sub_moderator_id` int(11) unsigned DEFAULT NULL COMMENT '副版本ID',
  `type_desc` text COMMENT '类型描述',
  `rule_desc` longtext COMMENT '版规描述',
  `main_path` varchar(255) DEFAULT NULL COMMENT '论坛主地址',
  `link_path` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `visit_num` int(11) unsigned DEFAULT '0' COMMENT '访问数',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='论坛：论坛表';

-- ----------------------------
-- Records of forum
-- ----------------------------
INSERT INTO `forum` VALUES ('1', null, null, null, null, null, null, null, null, '1', null, '1', '论坛1', 'http://files.chinahtml.com/programming/3/2005-11-25/7aebd09983d138b847a760d18eff7b73.gif', '1', '1', '类型描述类型描述类型描述', '版规描述版规描述版规描述', null, null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `forum` VALUES ('2', null, null, null, null, null, null, null, null, null, null, '2', '论坛2', 'http://files.chinahtml.com/programming/3/2005-11-25/7aebd09983d138b847a760d18eff7b73.gif', null, null, '2222', '2222', null, null, '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `forum` VALUES ('3', null, null, null, null, null, null, null, null, '1', null, '3', '论坛3', 'http://files.chinahtml.com/programming/3/2005-11-25/7aebd09983d138b847a760d18eff7b73.gif', null, null, '3333', '3333', null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `forum` VALUES ('5', null, null, null, null, null, null, null, null, null, null, null, '古玩圈', null, '1', null, '111', null, null, null, '0', null, null, '1', null, null, null, null, null, null, null, null, 'airson', null, null);
INSERT INTO `forum` VALUES ('6', null, null, null, null, null, null, null, null, null, null, null, '小圈子', null, null, null, '111', null, null, null, '0', null, null, '1', null, null, null, null, null, null, null, null, 'airson', null, null);
INSERT INTO `forum` VALUES ('8', null, null, null, null, null, null, null, null, null, null, null, '000', null, null, '1', '111', null, null, null, '0', null, null, '1', null, null, null, null, null, null, null, null, 'airson', null, null);
INSERT INTO `forum` VALUES ('9', null, null, null, null, null, null, null, null, null, null, null, '2222', null, null, null, '111', null, null, null, '0', null, null, '1', null, null, null, null, null, null, null, null, 'airson', null, null);
INSERT INTO `forum` VALUES ('10', null, null, null, null, null, null, null, null, null, null, null, '88888', null, null, null, '11', null, null, null, '0', null, null, '1', null, null, null, null, null, null, null, null, 'airson', null, null);

-- ----------------------------
-- Table structure for forum_article
-- ----------------------------
DROP TABLE IF EXISTS `forum_article`;
CREATE TABLE `forum_article` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `forum_id` int(11) unsigned DEFAULT NULL COMMENT '论坛ID',
  `title` varchar(512) DEFAULT NULL COMMENT '标题',
  `sub_title` varchar(1024) DEFAULT NULL COMMENT '副标题',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `content` longtext COMMENT '内容',
  `pic_paths` varchar(4000) DEFAULT NULL COMMENT '关联图片路径',
  `praise_num` int(11) unsigned DEFAULT NULL COMMENT '点赞数量',
  `author_id` int(11) unsigned DEFAULT NULL COMMENT '作者id',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '关联的用户ID',
  `user_ids` varchar(255) DEFAULT NULL COMMENT '关联的用户ID串',
  `visit_num` int(20) unsigned DEFAULT '0' COMMENT '查看次数',
  `is_essence` int(5) unsigned DEFAULT NULL COMMENT '是否是精华 1:否2:是',
  `is_top` int(5) unsigned DEFAULT NULL COMMENT '是否是置顶 1:否2:是',
  `is_hot` int(5) unsigned DEFAULT NULL COMMENT '是否是热门 1:否2:是',
  `is_recommend` int(5) unsigned DEFAULT NULL COMMENT '是否是推荐 1:否2:是',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `forum_id` (`forum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='论坛：论坛文章表';

-- ----------------------------
-- Records of forum_article
-- ----------------------------
INSERT INTO `forum_article` VALUES ('1', null, null, null, null, null, null, null, null, null, null, '1', '标题标题标题标题11', '副标题', '描述', '内容内容内容', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '22', '1', '1', null, '20', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-07 19:17:37', null, null, null);
INSERT INTO `forum_article` VALUES ('2', null, null, null, null, null, null, null, null, null, null, '1', '标题标题标题22', '副标题副标题', '描述', '内容内容内容', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '22', '1', '1', null, '10', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-08 19:17:37', null, null, null);
INSERT INTO `forum_article` VALUES ('3', null, null, null, null, null, null, null, null, null, null, '2', '标题标题标题标题33', '副标题副标题', '描述', '内容内容内容', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '22', '1', '2', null, '10', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, '2016-07-08 19:17:37', null, null, null);
INSERT INTO `forum_article` VALUES ('4', null, null, null, null, null, null, null, null, null, null, '1', '标题标题标题标题33', '副标题副标题', '描述', '内容内容内容', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '22', '1', '1', null, '10', null, null, null, '1', null, null, null, null, null, null, null, null, null, null, '2016-07-20 19:17:37', null, null, null);
INSERT INTO `forum_article` VALUES ('5', null, null, null, null, null, null, null, null, null, null, '1', '标题标题标题标题33', '副标题副标题', '描述', '内容内容内容', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '22', '1', '2', null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-20 19:17:37', null, null, null);
INSERT INTO `forum_article` VALUES ('6', null, null, null, null, null, null, null, null, null, null, '2', '标题标题标题标题33', '副标题副标题', '描述', '内容内容内容', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '22', '1', '1', null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-20 19:17:37', null, null, null);
INSERT INTO `forum_article` VALUES ('7', null, null, null, null, null, null, null, null, null, null, '3', '标题标题标题标题33', '副标题副标题', '描述', '内容内容内容', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '22', '1', '2', null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-20 19:17:37', null, null, null);
INSERT INTO `forum_article` VALUES ('8', null, null, null, null, null, null, null, null, null, null, '6', '标题标题标题标题33', '副标题副标题', '描述', '内容内容内容', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '22', '1', '1', null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-20 19:17:37', null, null, null);
INSERT INTO `forum_article` VALUES ('9', null, null, null, null, null, null, null, null, null, null, '3', '标题标题标题标题33', '副标题副标题', '描述', '内容内容内容', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '22', '1', '2', null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-20 19:17:37', null, null, null);
INSERT INTO `forum_article` VALUES ('10', null, null, null, null, null, null, null, null, null, null, '3', '标题标题标题标题33', '副标题副标题', '描述', '内容内容内容', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '22', '1', '1', null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-20 19:17:37', null, null, null);
INSERT INTO `forum_article` VALUES ('11', null, null, null, null, null, null, null, null, null, null, '8', '标题标题标题标题33', '副标题副标题', '描述', '内容内容内容', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg,http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '22', '1', '2', null, '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-20 19:17:37', null, null, null);

-- ----------------------------
-- Table structure for forum_article_collect
-- ----------------------------
DROP TABLE IF EXISTS `forum_article_collect`;
CREATE TABLE `forum_article_collect` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `forum_id` int(11) unsigned DEFAULT NULL COMMENT '论坛ID',
  `article_id` int(11) unsigned DEFAULT NULL COMMENT '文章ID',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论坛：论坛文章收藏表';

-- ----------------------------
-- Records of forum_article_collect
-- ----------------------------

-- ----------------------------
-- Table structure for forum_member
-- ----------------------------
DROP TABLE IF EXISTS `forum_member`;
CREATE TABLE `forum_member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `forum_id` int(11) unsigned DEFAULT NULL COMMENT '论坛ID',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `forum_id` (`forum_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='论坛：论坛成员表';

-- ----------------------------
-- Records of forum_member
-- ----------------------------
INSERT INTO `forum_member` VALUES ('49', null, null, null, null, null, null, null, null, null, null, '1', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `forum_member` VALUES ('52', null, null, null, null, null, null, null, null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `shop_id` int(11) unsigned DEFAULT NULL COMMENT '店铺ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `module_id` int(11) DEFAULT NULL COMMENT '所属模块（1店铺，2微拍，3即时拍，4专场，5批发城）',
  `cat_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `goods_sn` varchar(255) DEFAULT NULL COMMENT '商品的唯一货号',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品的名称',
  `goods_name_style` varchar(255) DEFAULT NULL COMMENT '商品名称显示的样式；包括颜色和字体样式',
  `pic_path` varchar(2048) DEFAULT NULL COMMENT '图片路径',
  `pic_paths` varchar(2048) DEFAULT NULL COMMENT '图片路径',
  `status_code` varchar(255) DEFAULT NULL COMMENT '数据字典代码：商品状态',
  `brand_id` int(11) unsigned DEFAULT NULL COMMENT '品牌id',
  `provider_id` int(11) unsigned DEFAULT NULL COMMENT '供货人ID',
  `provider_name` varchar(11) DEFAULT NULL COMMENT '供货人的名称',
  `remain_number` int(11) unsigned DEFAULT '0' COMMENT '商品库存数量',
  `goods_weight` int(11) unsigned DEFAULT '0' COMMENT '商品的重量，以千克为单位',
  `market_price` decimal(11,2) DEFAULT '0.00' COMMENT '市场售价',
  `shop_price` decimal(11,2) DEFAULT '0.00' COMMENT '本店售价',
  `sync_flag` int(11) DEFAULT NULL COMMENT '同步标识(1,是2，不是)',
  `agent_price` decimal(11,0) DEFAULT NULL COMMENT '代理价格',
  `agent_vip_price` decimal(10,2) DEFAULT NULL COMMENT 'VIP代理价格',
  `promote_price` decimal(11,2) DEFAULT '0.00' COMMENT '促销价格',
  `auction_price` decimal(10,2) DEFAULT NULL COMMENT '拍卖起拍价格',
  `promote_start_date` datetime DEFAULT NULL COMMENT '促销价格开始日期',
  `promote_end_date` datetime DEFAULT NULL COMMENT '促销价格结束日期',
  `warn_number` int(11) unsigned DEFAULT NULL COMMENT '商品报警数量',
  `keywords` int(11) unsigned DEFAULT NULL COMMENT '商品关键字',
  `goods_brief` varchar(1024) DEFAULT NULL COMMENT '商品的简短描述',
  `goods_description` text COMMENT '商品的详细描述',
  `goods_thumb_id` int(11) unsigned DEFAULT NULL COMMENT '商品在前台显示的微缩图片，如在分类筛选时显示的小图片',
  `goods_img_id` int(11) unsigned DEFAULT NULL COMMENT '商品的实际大小图片，如进入该商品页时介绍商品属性所显示的大图片',
  `original_img_id` int(11) unsigned DEFAULT NULL COMMENT '上传的商品的原始图片',
  `is_real` tinyint(2) DEFAULT '1' COMMENT '是否是实物',
  `extension_code` varchar(255) DEFAULT NULL COMMENT '商品的扩展属性',
  `is_on_sale` tinyint(2) DEFAULT '1' COMMENT '该商品是否开放销售',
  `is_alone_sale` tinyint(2) DEFAULT '1' COMMENT '是否能单独销售',
  `integral` int(11) unsigned DEFAULT '0' COMMENT '购买该商品可以使用的积分数量',
  `is_best` tinyint(2) DEFAULT '0' COMMENT '是否是精品',
  `is_new` tinyint(2) DEFAULT '0' COMMENT '是否是新品',
  `is_hot` tinyint(2) DEFAULT '0' COMMENT '是否热销',
  `is_promote` tinyint(2) DEFAULT '0' COMMENT '是否特价促销',
  `bonus_type_id` int(11) unsigned DEFAULT NULL COMMENT '购买该商品所能领到的红包类型（1:固定金额，2百分比）',
  `bonus` decimal(11,2) DEFAULT NULL COMMENT '奖金金额（红包）',
  `goods_type_id` int(11) unsigned DEFAULT NULL COMMENT '商品所属类型id',
  `seller_note` varchar(1024) DEFAULT NULL COMMENT '商品的商家备注，仅商家可见',
  `give_integral` int(11) unsigned DEFAULT '0' COMMENT '购买该商品时每笔成功交易赠送的积分数量',
  `postage_fee` decimal(11,2) DEFAULT '0.00' COMMENT '运费（空值为包邮）',
  `is_public` int(11) DEFAULT NULL COMMENT '是否公开(1.公开2，不公开)',
  `is_seven_return` int(4) DEFAULT NULL COMMENT '是否包退(1.包退2，不包退)',
  `pack_fee` decimal(11,2) DEFAULT '0.00' COMMENT '包装费',
  `scans` int(11) unsigned DEFAULT '0' COMMENT '浏览次数',
  `hits` int(11) unsigned DEFAULT '0' COMMENT '点击次数',
  `goods_from` int(11) DEFAULT NULL COMMENT '商品来源',
  `goods_privilege` int(11) DEFAULT NULL COMMENT '商品是否允许复制(1,可以2，不可以)',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `link_type` int(11) unsigned DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商城交易：商品表';

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', null, '78', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-11 10:28:56', '-SYS-');
INSERT INTO `goods` VALUES ('2', 'g2016061510023112', '1', null, null, 'goods_type_zhumu', null, null, null, null, null, '1', '1', null, null, 'g2016061510023112', '风车测试拍品', null, 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg', null, null, null, null, '10', null, null, null, null, null, null, null, '0.00', null, null, null, null, '如假包换，保真', '如假包换，保真', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0.00', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:02:32', 'airson', '2016-07-13 15:34:50', 'airson');
INSERT INTO `goods` VALUES ('3', 'g2016061510280784', '1', null, null, 'goods_type_yushi', null, null, null, null, null, '1', '1', null, null, 'g2016061510280784', '和田玉测试拍品', null, 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', null, null, null, null, '100', null, null, null, null, null, null, null, '100.00', null, null, null, null, '保真', '保真', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0.00', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:28:07', 'airson', '2016-07-13 16:45:59', 'airson');
INSERT INTO `goods` VALUES ('4', 'g2016061510433004', '1', null, null, 'goods_type_yushi', null, null, null, null, null, '1', '1', null, null, 'g2016061510433004', '玉石测试拍品2', null, 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433180.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433180.jpeg', null, null, null, null, '10', null, null, '2000.00', null, null, null, null, '500.00', null, null, null, null, '保真', '保真', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0.00', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:43:31', 'airson', '2016-07-13 15:34:50', 'airson');
INSERT INTO `goods` VALUES ('5', null, '78', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-18 10:20:11', '-SYS-');
INSERT INTO `goods` VALUES ('6', 'g2016061510433004', '71', null, null, null, null, null, null, null, null, '1', '1', null, null, 'g2016061510472717', '玉石拍品', null, 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510472880.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510472880.jpeg', null, null, null, null, '0', '0', '0.00', '0.00', null, null, null, '0.00', null, null, null, null, null, '如假包换，保真', '如假包换，保真', null, null, null, '1', null, '1', '1', '0', '0', '0', '0', '0', null, null, null, null, '0', '0.00', null, null, '0.00', '0', '0', null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-13 15:27:29', 'airson', '2016-07-13 15:34:50', 'airson');

-- ----------------------------
-- Table structure for goods_category
-- ----------------------------
DROP TABLE IF EXISTS `goods_category`;
CREATE TABLE `goods_category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `keywords` varchar(255) DEFAULT NULL COMMENT '分类的关键字,搜索',
  `description` varchar(255) DEFAULT NULL COMMENT '分类描述',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '该分类的父类ID',
  `unit` varchar(20) DEFAULT NULL COMMENT '该分类的计量单位',
  `show_in_nav` tinyint(2) DEFAULT NULL COMMENT '是否显示在导航栏',
  `is_show` tinyint(2) DEFAULT NULL COMMENT '是否在前台页面显示',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城交易：商品分类表';

-- ----------------------------
-- Records of goods_category
-- ----------------------------

-- ----------------------------
-- Table structure for goods_link
-- ----------------------------
DROP TABLE IF EXISTS `goods_link`;
CREATE TABLE `goods_link` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `goods_id` int(11) unsigned DEFAULT NULL COMMENT '商品ID',
  `goods_serial` varchar(255) DEFAULT NULL COMMENT '拍品编号',
  `link_type` int(11) unsigned DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `valid_time` datetime DEFAULT NULL COMMENT '有效时间',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城交易：商品关联表（包括分享的商品，送拍的商品）';

-- ----------------------------
-- Records of goods_link
-- ----------------------------

-- ----------------------------
-- Table structure for goods_picture
-- ----------------------------
DROP TABLE IF EXISTS `goods_picture`;
CREATE TABLE `goods_picture` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `goods_id` int(11) unsigned DEFAULT NULL COMMENT '图片属性商品的id',
  `pic_path` varchar(1024) DEFAULT NULL COMMENT '图片路径',
  `local_pic_path` varchar(1024) DEFAULT NULL COMMENT '本地图片路径',
  `oss_pic_path` varchar(1024) DEFAULT NULL COMMENT 'OSS服务器图片路径',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `cat_id` int(11) unsigned DEFAULT NULL COMMENT '栏目ID',
  `album_id` int(11) unsigned DEFAULT NULL COMMENT '相册ID',
  `is_cover` int(4) unsigned DEFAULT '0' COMMENT '是否为封面图片（主图片）2：是，其他：否',
  `description` varchar(255) DEFAULT NULL COMMENT '图片说明信息',
  `title` varchar(1024) DEFAULT NULL COMMENT '图片名称',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `link_path` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `size` double(2,0) DEFAULT NULL COMMENT '图片大小',
  `ext` varchar(20) DEFAULT NULL COMMENT '图片后缀名',
  `has_watermark` tinyint(2) DEFAULT NULL COMMENT '是否已经水印',
  `origin` varchar(255) DEFAULT NULL COMMENT '来源',
  `scans` int(11) unsigned DEFAULT NULL COMMENT '浏览次数',
  `hits` int(11) unsigned DEFAULT NULL COMMENT '点击次数',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `goods_id` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='商城交易：商品相册表';

-- ----------------------------
-- Records of goods_picture
-- ----------------------------
INSERT INTO `goods_picture` VALUES ('1', 'gp2016061509122766', null, null, null, null, null, null, null, null, null, '1', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061509122705.jpeg', '/file/wx//2016061509122705.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061509122705.jpeg', null, null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 09:12:27', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('2', 'gp2016061509122765', null, null, null, null, null, null, null, null, null, '1', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061509122780.jpeg', '/file/wx//2016061509122780.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061509122780.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 09:12:27', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('3', 'gp2016061509122877', null, null, null, null, null, null, null, null, null, '1', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061509122865.jpeg', '/file/wx//2016061509122865.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061509122865.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 09:12:27', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('4', 'gp2016061510023107', null, null, null, null, null, null, null, null, null, '2', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg', '/file/wx//2016061510023272.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023272.jpeg', null, null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:02:32', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('5', 'gp2016061510023335', null, null, null, null, null, null, null, null, null, '2', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023397.jpeg', '/file/wx//2016061510023397.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023397.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:02:32', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('6', 'gp2016061510023381', null, null, null, null, null, null, null, null, null, '2', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023382.jpeg', '/file/wx//2016061510023382.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023382.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:02:32', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('7', 'gp2016061510023335', null, null, null, null, null, null, null, null, null, '2', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023437.jpeg', '/file/wx//2016061510023437.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510023437.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:02:32', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('8', 'gp2016061510280723', null, null, null, null, null, null, null, null, null, '3', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', '/file/wx//2016061510280819.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280819.jpeg', null, null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:28:07', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('9', 'gp2016061510280948', null, null, null, null, null, null, null, null, null, '3', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280965.jpeg', '/file/wx//2016061510280965.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510280965.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:28:07', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('10', 'gp2016061510433015', null, null, null, null, null, null, null, null, null, '4', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433180.jpeg', '/file/wx//2016061510433180.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433180.jpeg', null, null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:43:31', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('11', 'gp201606151043310', null, null, null, null, null, null, null, null, null, '4', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433143.jpeg', '/file/wx//2016061510433143.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433143.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:43:31', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('12', 'gp2016061510433153', null, null, null, null, null, null, null, null, null, '4', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433132.jpeg', '/file/wx//2016061510433132.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433132.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:43:31', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('13', 'gp2016061510433283', null, null, null, null, null, null, null, null, null, '4', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433203.jpeg', '/file/wx//2016061510433203.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433203.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:43:31', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('14', 'gp2016061510433243', null, null, null, null, null, null, null, null, null, '4', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433286.jpeg', '/file/wx//2016061510433286.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433286.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:43:31', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('15', 'gp2016061510433208', null, null, null, null, null, null, null, null, null, '4', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433216.jpeg', '/file/wx//2016061510433216.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433216.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:43:31', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('16', 'gp2016061510433207', null, null, null, null, null, null, null, null, null, '4', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433293.jpeg', '/file/wx//2016061510433293.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433293.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:43:31', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('17', 'gp2016061510433240', null, null, null, null, null, null, null, null, null, '4', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433296.jpeg', '/file/wx//2016061510433296.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433296.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:43:31', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('18', 'gp2016061510433287', null, null, null, null, null, null, null, null, null, '4', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433398.jpeg', '/file/wx//2016061510433398.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510433398.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:43:31', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('19', 'gp2016061510472721', null, null, null, null, null, null, null, null, null, '5', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510472880.jpeg', '/file/wx//2016061510472880.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510472880.jpeg', null, null, null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:47:28', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('20', 'gp2016061510472811', null, null, null, null, null, null, null, null, null, '5', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510472849.jpeg', '/file/wx//2016061510472849.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510472849.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:47:28', 'airson', null, null);
INSERT INTO `goods_picture` VALUES ('21', 'gp2016061510472830', null, null, null, null, null, null, null, null, null, '5', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510472979.jpeg', '/file/wx//2016061510472979.jpeg', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061510472979.jpeg', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 10:47:28', 'airson', null, null);

-- ----------------------------
-- Table structure for investigation
-- ----------------------------
DROP TABLE IF EXISTS `investigation`;
CREATE TABLE `investigation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：调查报名申请表';

-- ----------------------------
-- Records of investigation
-- ----------------------------

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `login_time` datetime DEFAULT NULL COMMENT '登陆时间',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登陆IP',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `login_time` (`login_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通用：登陆日志表';

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES ('1', null, null, null, null, null, null, null, null, '2', null, '1', 'admin', '2016-07-22 10:25:24', '0:0:0:0:0:0:0:1', '正常登陆', null, null, null, null, null, null, null, null, null, null, null, '2016-07-22 10:25:24', '-SYS-', null, null);
INSERT INTO `login_log` VALUES ('2', null, null, null, null, null, null, null, null, '2', null, '1', 'admin', '2016-07-30 14:09:31', '0:0:0:0:0:0:0:1', '正常登陆', null, null, null, null, null, null, null, null, null, null, null, '2016-07-30 14:09:31', '-SYS-', null, null);

-- ----------------------------
-- Table structure for meeting
-- ----------------------------
DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `name` varchar(255) DEFAULT NULL COMMENT '会议名称',
  `initiator` varchar(255) DEFAULT NULL COMMENT '发起人（召集人）名称',
  `initiatorId` int(11) DEFAULT NULL COMMENT '发起人（召集人）编号',
  `linkman` varchar(255) DEFAULT NULL COMMENT '联系人',
  `linkway` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `address` varchar(255) DEFAULT NULL COMMENT '会议地址',
  `content` varchar(2048) DEFAULT NULL COMMENT '会议内容',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `member_ids` varchar(255) DEFAULT NULL COMMENT '会议成员ID串',
  `member_names` varchar(255) DEFAULT NULL COMMENT '会议成员名字串',
  `notice_time` datetime DEFAULT NULL COMMENT '提醒时间',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `main_ids` varchar(255) DEFAULT NULL COMMENT 'ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `attr_num` decimal(11,2) DEFAULT NULL COMMENT '备用字段-数字',
  `attr_date` date DEFAULT NULL COMMENT '备用字段-日期',
  `attr_datetime` datetime DEFAULT NULL COMMENT '备用字段-日期时间',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `remark` varchar(2048) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  KEY `main_status` (`main_status`),
  KEY `logic_status` (`logic_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理系统：会议表';

-- ----------------------------
-- Records of meeting
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单链接地址',
  `is_leaf` tinyint(2) DEFAULT NULL COMMENT '是否为叶子节点',
  `level_id` int(11) DEFAULT NULL COMMENT '菜单级别：1根菜单，2子菜单（非叶子），3叶子菜单，4按钮，5其他',
  `description` varchar(255) DEFAULT NULL COMMENT '菜单描述',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='通用：菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, null, null, null, null, null, null, null, null, null, null, '系统管理', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('2', null, null, null, null, null, null, null, null, null, null, null, '用户管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('3', null, null, null, null, null, null, null, null, null, null, null, '文章管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('4', null, null, null, null, null, null, null, null, null, null, null, '消息管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('5', null, null, null, null, null, null, null, null, null, null, null, '通知公告管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('6', null, null, null, null, null, null, null, null, null, null, null, '图片管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('7', null, null, null, null, null, null, null, null, null, null, null, '申请管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('8', null, null, null, null, null, null, null, null, null, null, null, '论坛管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('9', null, null, null, null, null, null, null, null, null, null, null, '店铺管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('10', null, null, null, null, null, null, null, null, null, null, null, '财务管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('11', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('12', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('13', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('14', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('15', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('16', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('17', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('18', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('19', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('20', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('21', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('22', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('23', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('24', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('25', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('26', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('27', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('28', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('29', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('30', null, null, null, null, null, '2016-04-07 15:08:13', '-SYS-DEFAULT-', null, null, null, null, '备用根管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('31', null, null, null, null, null, null, null, null, null, null, null, '拍卖管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('32', null, null, null, null, null, null, null, null, null, null, null, '商圈管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('33', null, null, null, null, null, null, null, null, null, null, null, '代理管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('34', null, null, null, null, null, null, null, null, null, null, null, '推广管理', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('35', null, null, null, null, null, null, null, null, null, null, '7', '申请处理', null, '/back/apply', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('36', null, null, null, null, null, null, null, null, null, null, '3', '新闻资讯', null, '/back/article/news', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('37', null, null, null, null, null, null, null, null, null, null, '3', '展会活动', null, '/back/article/activity', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('38', null, null, null, null, null, null, null, null, null, null, '2', '用户信息', null, '/back/userInfo', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('39', null, null, null, null, null, null, null, null, null, null, '2', '用户控制', null, '/back/userControl', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('40', null, null, null, null, null, null, null, null, null, null, '9', '店铺', null, '/back/shop', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('41', null, null, null, null, null, null, null, null, null, null, '9', '藏品', null, '/back/goods', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('42', null, null, null, null, null, null, null, null, null, null, '9', '藏品图片', null, '/back/goodsPicture', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('43', null, null, null, null, null, null, null, null, null, null, '31', '专场机构', null, '/back/ap/inst', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('44', null, null, null, null, null, null, null, null, null, null, '31', '专场场次', null, '/back/ap', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('45', null, null, null, null, null, null, null, null, null, null, '31', '专场商品', null, '/back/ap/goods', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('46', null, null, null, null, null, null, null, null, null, null, '31', '微拍', null, '/back/auctionMicro', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('47', null, null, null, null, null, null, null, null, null, null, '10', '充值记录', null, '/back/charge', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('48', null, null, null, null, null, null, null, null, null, null, '10', '交易记录', null, '/back/accountLog', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('49', null, null, null, null, null, null, null, null, null, null, '10', '提现申请', null, '/back/withdraw', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('50', null, null, null, null, null, null, null, null, null, null, '6', '图片', null, '/back/picture', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('51', null, null, null, null, null, null, null, null, null, null, '8', '论坛', null, '/back/forum', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('52', null, null, null, null, null, null, null, null, null, null, '8', '帖子', null, '/back/forumArticle', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('53', null, null, null, null, null, null, null, null, null, null, '8', '论坛成员', null, '/back/forumMember', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('54', null, null, null, null, null, null, null, null, null, null, '5', '公告', null, '/back/announcement', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('55', null, null, null, null, null, null, null, null, null, null, '5', '消息通知', null, '/back/notice', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('56', null, null, null, null, null, null, null, null, null, null, '5', '单人聊天记录', null, '/back/chat', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('57', null, null, null, null, null, null, null, null, null, null, '5', '专场聊天记录', null, '/back/chatProfession', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('58', null, null, null, null, null, null, null, null, null, null, '32', '商圈', null, '/back/antiqueCity', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('59', null, null, null, null, null, null, null, null, null, null, '32', '商圈成员', null, '/back/antiqueCityMember', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('60', null, null, null, null, null, null, null, null, null, null, '1', '数据字典', null, '/back/dict', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('62', null, null, null, null, null, null, null, null, null, null, '1', '登陆日志', null, '/back/loginLog', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('63', null, null, null, null, null, null, null, null, null, null, '1', '用户', null, '/back/user', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('64', null, null, null, null, null, null, null, null, null, null, '1', '角色', null, '/back/role', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('65', null, null, null, null, null, null, null, null, null, null, '1', '角色菜单', null, '/back/roleMenu', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `menu` VALUES ('66', null, null, null, null, null, null, null, null, null, null, '1', '省市区', null, '/back/pca', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `sender_id` int(11) unsigned DEFAULT NULL COMMENT '发送人ID',
  `sender_name` varchar(255) DEFAULT NULL COMMENT '发送人名称',
  `receiver_id` int(11) unsigned DEFAULT NULL COMMENT '接收人ID',
  `receiver_ids` varchar(255) DEFAULT NULL COMMENT '接收人ID串',
  `receiver_group_id` int(11) DEFAULT NULL COMMENT '接收组ID',
  `msg_group` varchar(255) DEFAULT NULL COMMENT '消息类型组',
  `content_type` int(11) unsigned DEFAULT NULL COMMENT '消息类型1:文本消息 2:语音消息 3:视频消息  4:图片消息  5:位置消息  6:文件',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `subject` text COMMENT '主题',
  `content` text COMMENT '内容',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `conceal_level` int(11) DEFAULT NULL COMMENT '隐私等级',
  `to_all` varchar(255) DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL COMMENT '有效期',
  `anonymous` varchar(255) DEFAULT NULL COMMENT '是否匿名',
  `is_top` int(4) DEFAULT NULL COMMENT '是否置顶',
  `file_id` int(11) unsigned DEFAULT NULL COMMENT '附件ID',
  `link_url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`),
  KEY `msg_group` (`msg_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：消息表';

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `notice_type_code` varchar(255) DEFAULT NULL COMMENT '通知类型代码',
  `sender_id` int(11) unsigned DEFAULT NULL COMMENT '发送人ID',
  `sender_name` varchar(255) DEFAULT NULL COMMENT '发送人名称',
  `receiver_id` int(11) unsigned DEFAULT NULL COMMENT '接收人ID',
  `receiver_ids` varchar(255) DEFAULT NULL COMMENT '接收人ID串',
  `receiver_group_id` int(11) DEFAULT NULL COMMENT '接收组ID',
  `msg_group` varchar(255) DEFAULT NULL COMMENT '消息类型组',
  `content_type` int(11) unsigned DEFAULT NULL COMMENT '消息类型1:文本消息 2:语音消息 3:视频消息  4:图片消息  5:位置消息  6:文件',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `subject` text COMMENT '主题',
  `content` text COMMENT '内容',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `read_status` int(4) DEFAULT NULL COMMENT '阅读状态',
  `conceal_level` int(11) DEFAULT NULL COMMENT '隐私等级',
  `to_all` varchar(255) DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL COMMENT '有效期',
  `anonymous` varchar(255) DEFAULT NULL COMMENT '是否匿名',
  `is_top` int(4) DEFAULT NULL COMMENT '是否置顶',
  `file_id` int(11) unsigned DEFAULT NULL COMMENT '附件ID',
  `link_url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`),
  KEY `msg_group` (`msg_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：通知表';

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operation_ip` varchar(255) DEFAULT NULL COMMENT '操作IP',
  `operation_type` int(11) DEFAULT NULL COMMENT '操作类型',
  `operation_level` int(11) DEFAULT NULL COMMENT '操作等级',
  `link_user_id` int(11) DEFAULT NULL COMMENT '操作关联用户ID',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `operation_time` (`operation_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：操作日志表';

-- ----------------------------
-- Records of operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for order_goods
-- ----------------------------
DROP TABLE IF EXISTS `order_goods`;
CREATE TABLE `order_goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `order_id` int(255) unsigned DEFAULT NULL COMMENT '订单商品信息对应的详细信息id，取值order_info的order_id',
  `goods_type` int(11) DEFAULT NULL COMMENT '商品类型（1普通商品，2批发城商品）',
  `goods_from` int(11) DEFAULT NULL COMMENT '商品来源（1批发城，2店铺，3专场，4微拍，5即时拍）',
  `goods_id` int(255) unsigned DEFAULT NULL COMMENT '商品的的id，取值表goods 的goods_id',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品的名称',
  `goods_sn` varchar(255) DEFAULT NULL COMMENT '商品的唯一货号',
  `goods_number` int(255) unsigned DEFAULT NULL COMMENT '商品的购买数量',
  `market_price` decimal(11,2) unsigned DEFAULT NULL COMMENT '商品的市场售价',
  `shop_price` decimal(11,2) unsigned DEFAULT NULL COMMENT '商品的本店售价',
  `postage_fee` decimal(10,2) DEFAULT '0.00' COMMENT '运费（空值为包邮）',
  `goods_attr` varchar(255) DEFAULT NULL COMMENT '购买该商品时所选择的属性（占用：快递单号）',
  `send_flag` tinyint(2) DEFAULT NULL COMMENT '当不是实物时，是否已发货',
  `is_real` tinyint(2) DEFAULT NULL COMMENT '是否是实物',
  `extension_code` varchar(255) DEFAULT NULL COMMENT '商品的扩展属性，比如像虚拟卡',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '父商品id，取值于ecs_cart的parent_id；如果有该值则是值多代表的物品的配件',
  `is_gift` tinyint(2) DEFAULT NULL COMMENT '是否参加优惠活动',
  `promote_user_id` int(11) DEFAULT NULL COMMENT '推广人ID',
  `promote_username` varchar(255) DEFAULT NULL COMMENT '推广人',
  `promote_benifit_money` decimal(11,2) DEFAULT NULL COMMENT '推广收益金额',
  `scans` int(11) unsigned DEFAULT NULL COMMENT '浏览次数',
  `hits` int(11) unsigned DEFAULT NULL COMMENT '点击次数',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商城交易：订单商品表';

-- ----------------------------
-- Records of order_goods
-- ----------------------------
INSERT INTO `order_goods` VALUES ('2', null, '1', null, null, null, null, null, null, null, null, '2', '1', '4', '1', null, null, '1', null, '5000.00', '0.00', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-29 09:43:16', '-SYS-', null, null);
INSERT INTO `order_goods` VALUES ('3', null, '1', null, null, null, null, null, null, null, null, '3', '1', '4', '1', null, null, '1', null, '5000.00', '0.00', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-29 09:49:15', '-SYS-', null, null);
INSERT INTO `order_goods` VALUES ('4', null, '1', null, null, null, null, null, null, null, null, '4', '1', '4', '1', null, null, '1', null, '200.00', '0.00', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-11 10:27:38', '-SYS-', null, null);
INSERT INTO `order_goods` VALUES ('5', null, '1', null, null, null, null, null, null, null, null, '5', '1', '4', '5', null, null, '1', null, '20.00', '0.00', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-13 15:49:13', '-SYS-', null, null);
INSERT INTO `order_goods` VALUES ('6', null, '1', null, null, null, null, null, null, null, null, '6', '1', '4', '5', null, null, '1', null, '20.00', '0.00', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-18 10:20:05', '-SYS-', null, null);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `order_sn` varchar(255) DEFAULT NULL COMMENT '订单号,唯一',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `order_status_code` varchar(255) DEFAULT NULL COMMENT '数据字典代码：订单的状态',
  `shipping_status_code` varchar(255) DEFAULT NULL COMMENT '数据字典代码：商品配送情况',
  `pay_status_code` varchar(255) DEFAULT NULL COMMENT '数据字典代码：支付状态',
  `order_done_staus_code` varchar(255) DEFAULT NULL COMMENT '数据字典代码：订单完成状态',
  `username` varchar(255) DEFAULT NULL COMMENT '收货人的姓名',
  `country` varchar(255) DEFAULT NULL COMMENT '收货人的国家',
  `province` varchar(255) DEFAULT NULL COMMENT '收货人的省份',
  `city` varchar(255) DEFAULT NULL COMMENT '收货人的城市',
  `district` varchar(255) DEFAULT NULL COMMENT '收货人的地区',
  `address` varchar(255) DEFAULT NULL COMMENT '收货人的详细地址',
  `zipcode` varchar(255) DEFAULT NULL COMMENT '收货人的邮编',
  `phone` varchar(255) DEFAULT NULL COMMENT '收货人的手机',
  `tel` varchar(255) DEFAULT NULL COMMENT '收货人的电话',
  `email` varchar(255) DEFAULT NULL COMMENT '收货人的Email',
  `best_time` datetime DEFAULT NULL COMMENT '收货人的最佳送货时间',
  `postscript` varchar(255) DEFAULT NULL COMMENT '订单附言,由用户提交订单前填写',
  `shipping_id` int(11) unsigned DEFAULT NULL COMMENT '用户选择的配送方式id',
  `shipping_name` varchar(255) DEFAULT NULL COMMENT '用户选择的配送方式的名称',
  `postage_fee` decimal(10,2) DEFAULT '0.00' COMMENT '运费（空值为包邮）',
  `express_id` int(11) DEFAULT NULL COMMENT '快递公司ID',
  `express_name` varchar(255) DEFAULT NULL COMMENT '快递公司名称',
  `express_code` varchar(255) DEFAULT NULL COMMENT '快递公司编码',
  `express_order` varchar(255) DEFAULT NULL COMMENT '快递单号',
  `poll_status` varchar(255) DEFAULT NULL COMMENT '快递100接口的推送订阅状态（200提交成功，701:拒绝订阅的快递公司，700: 订阅方的订阅数据存在错误（如不支持的快递公司、单号为空、单号超长等），600: 您不是合法的订阅者（即授权Key出错），500: 服务器错误，501:重复订阅）',
  `express_status` varchar(255) DEFAULT NULL COMMENT '监控状态:polling:监控中，shutdown:结束，abort:中止，updateall：重新推送。其中当快递单为已签收时status=shutdown，当message为“3天查询无记录”或“60天无变化时”status= abort',
  `express_state` varchar(11) DEFAULT NULL COMMENT '快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单',
  `express_json` varchar(8000) DEFAULT NULL COMMENT '快递100推送数据(Json)',
  `express_receive_time` datetime DEFAULT NULL COMMENT '快递签收日期',
  `pay_id` int(11) unsigned DEFAULT NULL COMMENT '用户选择的支付方式的id',
  `pay_name` varchar(255) DEFAULT NULL COMMENT '用户选择的支付方式名称',
  `how_oos` varchar(255) DEFAULT NULL COMMENT '缺货处理方式,等待所有商品备齐后再发,取消订单;与店主协商',
  `scans` int(11) unsigned DEFAULT NULL COMMENT '浏览次数',
  `hits` int(11) unsigned DEFAULT NULL COMMENT '点击次数',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `order_sn` (`order_sn`),
  KEY `user_id` (`user_id`),
  KEY `order_status` (`order_status_code`),
  KEY `shipping_status` (`shipping_status_code`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='商城交易：订单详细信息表';

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('3', null, '1', null, null, null, null, null, null, null, null, 'od2016062909491452', '1', 'order_status_todo', 'shipping_status_todo', 'pay_status_todo', 'order_done_status_no', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '200', 'shutdown', '0', '{\\\"com\\\":\\\"ems\\\",\\\"condition\\\":\\\"F00\\\",\\\"data\\\":[{\\\"context\\\":\\\"【重庆市】 投递并签收，签收人：本人收\\\",\\\"ftime\\\":\\\"2015-11-18 10:04:52\\\",\\\"time\\\":\\\"2015-11-18 10:04:52\\\"},{\\\"context\\\":\\\"【重庆市】 重庆市邮政速递物流公司黔江分公司直营揽投部安排投递，预计23:59:00前投递（投递员姓名：向昌文;联系电话：）\\\",\\\"ftime\\\":\\\"2015-11-18 08:40:22\\\",\\\"time\\\":\\\"2015-11-18 08:40:22\\\"},{\\\"context\\\":\\\"【重庆市】 离开重庆市 发往重庆市邮政速递物流公司武隆分公司直营揽投部\\\",\\\"ftime\\\":\\\"2015-11-17 03:54:32\\\",\\\"time\\\":\\\"2015-11-17 03:54:32\\\"},{\\\"context\\\":\\\"【重庆市】 到达  重庆航站 处理中心\\\",\\\"ftime\\\":\\\"2015-11-16 15:46:00\\\",\\\"time\\\":\\\"2015-11-16 15:46:00\\\"},{\\\"context\\\":\\\"【武汉市】 离开武汉市 发往重庆市（经转）\\\",\\\"ftime\\\":\\\"2015-11-15 14:25:00\\\",\\\"time\\\":\\\"2015-11-15 14:25:00\\\"},{\\\"context\\\":\\\"【武汉市】 到达武汉市航空邮件转运站处理中心（经转）\\\",\\\"ftime\\\":\\\"2015-11-14 23:27:00\\\",\\\"time\\\":\\\"2015-11-14 23:27:00\\\"},{\\\"context\\\":\\\"【黄石市】 已离开收寄点，发往武汉市航空邮件转运站\\\",\\\"ftime\\\":\\\"2015-11-14 20:05:30\\\",\\\"time\\\":\\\"2015-11-14 20:05:30\\\"},{\\\"context\\\":\\\"【黄石市】 黄石万达揽投部已收件（揽投员姓名：王劲松,联系电话:07146359699）\\\",\\\"ftime\\\":\\\"2015-11-14 17:00:00\\\",\\\"time\\\":\\\"2015-11-14 17:00:00\\\"}],\\\"ischeck\\\":\\\"1\\\",\\\"message\\\":\\\"ok\\\",\\\"nu\\\":\\\"5116741128399\\\",\\\"state\\\":\\\"3\\\",\\\"status\\\":\\\"200\\\"}', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-29 09:49:15', '-SYS-', null, null);
INSERT INTO `order_info` VALUES ('4', null, '1', null, null, null, null, null, null, null, null, 'od2016071110273852', '1', 'order_status_todo', 'shipping_status_todo', 'pay_status_todo', 'order_done_status_no', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '200', 'shutdown', '0', '{\\\"com\\\":\\\"ems\\\",\\\"condition\\\":\\\"F00\\\",\\\"data\\\":[{\\\"context\\\":\\\"【重庆市】 投递并签收，签收人：本人收\\\",\\\"ftime\\\":\\\"2015-11-18 10:04:52\\\",\\\"time\\\":\\\"2015-11-18 10:04:52\\\"},{\\\"context\\\":\\\"【重庆市】 重庆市邮政速递物流公司黔江分公司直营揽投部安排投递，预计23:59:00前投递（投递员姓名：向昌文;联系电话：）\\\",\\\"ftime\\\":\\\"2015-11-18 08:40:22\\\",\\\"time\\\":\\\"2015-11-18 08:40:22\\\"},{\\\"context\\\":\\\"【重庆市】 离开重庆市 发往重庆市邮政速递物流公司武隆分公司直营揽投部\\\",\\\"ftime\\\":\\\"2015-11-17 03:54:32\\\",\\\"time\\\":\\\"2015-11-17 03:54:32\\\"},{\\\"context\\\":\\\"【重庆市】 到达  重庆航站 处理中心\\\",\\\"ftime\\\":\\\"2015-11-16 15:46:00\\\",\\\"time\\\":\\\"2015-11-16 15:46:00\\\"},{\\\"context\\\":\\\"【武汉市】 离开武汉市 发往重庆市（经转）\\\",\\\"ftime\\\":\\\"2015-11-15 14:25:00\\\",\\\"time\\\":\\\"2015-11-15 14:25:00\\\"},{\\\"context\\\":\\\"【武汉市】 到达武汉市航空邮件转运站处理中心（经转）\\\",\\\"ftime\\\":\\\"2015-11-14 23:27:00\\\",\\\"time\\\":\\\"2015-11-14 23:27:00\\\"},{\\\"context\\\":\\\"【黄石市】 已离开收寄点，发往武汉市航空邮件转运站\\\",\\\"ftime\\\":\\\"2015-11-14 20:05:30\\\",\\\"time\\\":\\\"2015-11-14 20:05:30\\\"},{\\\"context\\\":\\\"【黄石市】 黄石万达揽投部已收件（揽投员姓名：王劲松,联系电话:07146359699）\\\",\\\"ftime\\\":\\\"2015-11-14 17:00:00\\\",\\\"time\\\":\\\"2015-11-14 17:00:00\\\"}],\\\"ischeck\\\":\\\"1\\\",\\\"message\\\":\\\"ok\\\",\\\"nu\\\":\\\"5116741128399\\\",\\\"state\\\":\\\"3\\\",\\\"status\\\":\\\"200\\\"}', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-11 10:27:38', '-SYS-', null, null);
INSERT INTO `order_info` VALUES ('5', null, '1', null, null, null, null, null, null, null, null, 'od2016071315491317', '1', 'order_status_todo', 'shipping_status_todo', 'pay_status_todo', 'order_done_status_no', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '200', 'shutdown', '0', '{\\\"com\\\":\\\"ems\\\",\\\"condition\\\":\\\"F00\\\",\\\"data\\\":[{\\\"context\\\":\\\"【重庆市】 投递并签收，签收人：本人收\\\",\\\"ftime\\\":\\\"2015-11-18 10:04:52\\\",\\\"time\\\":\\\"2015-11-18 10:04:52\\\"},{\\\"context\\\":\\\"【重庆市】 重庆市邮政速递物流公司黔江分公司直营揽投部安排投递，预计23:59:00前投递（投递员姓名：向昌文;联系电话：）\\\",\\\"ftime\\\":\\\"2015-11-18 08:40:22\\\",\\\"time\\\":\\\"2015-11-18 08:40:22\\\"},{\\\"context\\\":\\\"【重庆市】 离开重庆市 发往重庆市邮政速递物流公司武隆分公司直营揽投部\\\",\\\"ftime\\\":\\\"2015-11-17 03:54:32\\\",\\\"time\\\":\\\"2015-11-17 03:54:32\\\"},{\\\"context\\\":\\\"【重庆市】 到达  重庆航站 处理中心\\\",\\\"ftime\\\":\\\"2015-11-16 15:46:00\\\",\\\"time\\\":\\\"2015-11-16 15:46:00\\\"},{\\\"context\\\":\\\"【武汉市】 离开武汉市 发往重庆市（经转）\\\",\\\"ftime\\\":\\\"2015-11-15 14:25:00\\\",\\\"time\\\":\\\"2015-11-15 14:25:00\\\"},{\\\"context\\\":\\\"【武汉市】 到达武汉市航空邮件转运站处理中心（经转）\\\",\\\"ftime\\\":\\\"2015-11-14 23:27:00\\\",\\\"time\\\":\\\"2015-11-14 23:27:00\\\"},{\\\"context\\\":\\\"【黄石市】 已离开收寄点，发往武汉市航空邮件转运站\\\",\\\"ftime\\\":\\\"2015-11-14 20:05:30\\\",\\\"time\\\":\\\"2015-11-14 20:05:30\\\"},{\\\"context\\\":\\\"【黄石市】 黄石万达揽投部已收件（揽投员姓名：王劲松,联系电话:07146359699）\\\",\\\"ftime\\\":\\\"2015-11-14 17:00:00\\\",\\\"time\\\":\\\"2015-11-14 17:00:00\\\"}],\\\"ischeck\\\":\\\"1\\\",\\\"message\\\":\\\"ok\\\",\\\"nu\\\":\\\"5116741128399\\\",\\\"state\\\":\\\"3\\\",\\\"status\\\":\\\"200\\\"}', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-13 15:49:13', '-SYS-', null, null);
INSERT INTO `order_info` VALUES ('6', null, '1', null, null, null, null, null, null, null, null, 'od2016071810200473', '1', 'order_status_todo', 'shipping_status_todo', 'pay_status_todo', 'order_done_status_no', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '200', 'shutdown', '0', '{\\\"com\\\":\\\"ems\\\",\\\"condition\\\":\\\"F00\\\",\\\"data\\\":[{\\\"context\\\":\\\"【重庆市】 投递并签收，签收人：本人收\\\",\\\"ftime\\\":\\\"2015-11-18 10:04:52\\\",\\\"time\\\":\\\"2015-11-18 10:04:52\\\"},{\\\"context\\\":\\\"【重庆市】 重庆市邮政速递物流公司黔江分公司直营揽投部安排投递，预计23:59:00前投递（投递员姓名：向昌文;联系电话：）\\\",\\\"ftime\\\":\\\"2015-11-18 08:40:22\\\",\\\"time\\\":\\\"2015-11-18 08:40:22\\\"},{\\\"context\\\":\\\"【重庆市】 离开重庆市 发往重庆市邮政速递物流公司武隆分公司直营揽投部\\\",\\\"ftime\\\":\\\"2015-11-17 03:54:32\\\",\\\"time\\\":\\\"2015-11-17 03:54:32\\\"},{\\\"context\\\":\\\"【重庆市】 到达  重庆航站 处理中心\\\",\\\"ftime\\\":\\\"2015-11-16 15:46:00\\\",\\\"time\\\":\\\"2015-11-16 15:46:00\\\"},{\\\"context\\\":\\\"【武汉市】 离开武汉市 发往重庆市（经转）\\\",\\\"ftime\\\":\\\"2015-11-15 14:25:00\\\",\\\"time\\\":\\\"2015-11-15 14:25:00\\\"},{\\\"context\\\":\\\"【武汉市】 到达武汉市航空邮件转运站处理中心（经转）\\\",\\\"ftime\\\":\\\"2015-11-14 23:27:00\\\",\\\"time\\\":\\\"2015-11-14 23:27:00\\\"},{\\\"context\\\":\\\"【黄石市】 已离开收寄点，发往武汉市航空邮件转运站\\\",\\\"ftime\\\":\\\"2015-11-14 20:05:30\\\",\\\"time\\\":\\\"2015-11-14 20:05:30\\\"},{\\\"context\\\":\\\"【黄石市】 黄石万达揽投部已收件（揽投员姓名：王劲松,联系电话:07146359699）\\\",\\\"ftime\\\":\\\"2015-11-14 17:00:00\\\",\\\"time\\\":\\\"2015-11-14 17:00:00\\\"}],\\\"ischeck\\\":\\\"1\\\",\\\"message\\\":\\\"ok\\\",\\\"nu\\\":\\\"5116741128399\\\",\\\"state\\\":\\\"3\\\",\\\"status\\\":\\\"200\\\"}', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-18 10:20:05', '-SYS-', null, null);

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `is_real_auth` int(4) unsigned DEFAULT NULL COMMENT '是否实名认证',
  `sex` int(4) unsigned DEFAULT NULL COMMENT '性别：1女，2：男',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户手机号码',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录id',
  `tel` varchar(255) DEFAULT NULL COMMENT '座机',
  `phone_conceal` int(4) unsigned DEFAULT NULL COMMENT '手机是否公开',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `pic_path` varchar(1024) DEFAULT NULL COMMENT '图片路径',
  `pic_paths` varchar(2048) DEFAULT NULL COMMENT '多张图片',
  `contact_way` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(255) DEFAULT NULL COMMENT '最后登录IP',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `province` int(11) unsigned DEFAULT NULL COMMENT '省份',
  `city` int(11) unsigned DEFAULT NULL COMMENT '城市',
  `weixin` varchar(255) DEFAULT NULL COMMENT '微信',
  `qq` varchar(255) DEFAULT NULL COMMENT 'QQ',
  `weibo` varchar(255) DEFAULT NULL COMMENT '微博',
  `job_id` int(11) DEFAULT NULL COMMENT '工作ID',
  `job_code` varchar(255) DEFAULT NULL COMMENT '工作代码',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `title_code` varchar(255) DEFAULT NULL COMMENT '职称代码',
  `qr_code` varchar(255) DEFAULT NULL COMMENT '二维码',
  `qr_code2` varchar(255) DEFAULT NULL COMMENT '二维码2',
  `third_name` varchar(255) DEFAULT NULL COMMENT '第三方平台的名称',
  `third_id` varchar(255) DEFAULT NULL COMMENT '第三方ID（聊天接口等）',
  `third_password` varchar(255) DEFAULT NULL COMMENT '第三方平台的密码',
  `wx_openid` varchar(255) DEFAULT NULL COMMENT '微信openid（标识微信用户）',
  `role_id` int(11) unsigned DEFAULT NULL COMMENT '角色ID：1.普通用户2.管理员',
  `organization` varchar(255) DEFAULT NULL COMMENT '公司，团队，机构',
  `idcard_num` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `certificate_type` int(11) unsigned DEFAULT NULL COMMENT '证件类型',
  `certificate_number` varchar(255) DEFAULT NULL COMMENT '证件号码',
  `certificate_name` varchar(255) DEFAULT NULL COMMENT '证件姓名',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `link_id2` int(11) DEFAULT NULL COMMENT '关联ID2',
  `link_id3` int(11) DEFAULT NULL COMMENT '关联ID3',
  `link_id4` int(11) DEFAULT NULL COMMENT '关联ID4',
  `link_id5` int(11) DEFAULT NULL COMMENT '关联ID5',
  `link_code` varchar(255) DEFAULT NULL COMMENT '关联代码',
  `link_code2` varchar(255) DEFAULT NULL COMMENT '关联代码2',
  `link_code3` varchar(255) DEFAULT NULL COMMENT '关联代码3',
  `link_code4` varchar(255) DEFAULT NULL COMMENT '关联代码4',
  `link_code5` varchar(255) DEFAULT NULL COMMENT '关联代码5',
  `link_str` varchar(255) DEFAULT NULL COMMENT '关联字符串',
  `param_json` varchar(255) DEFAULT NULL COMMENT 'json格式参数',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_str3` varchar(255) DEFAULT NULL,
  `attr_int` int(11) unsigned DEFAULT '0' COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `username` (`username`),
  KEY `phone` (`phone`),
  KEY `email` (`email`),
  KEY `main_status` (`main_status`),
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`),
  KEY `link_id` (`link_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通用：简单用户表';

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES ('1', 'u2016061508550275', '1', null, null, null, null, null, null, null, null, 'airson', '21218cca77804d2ba1922c33e0151105', 'airson', null, null, '1', null, null, null, null, null, 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061508550955.jpeg', null, null, null, '2016-07-10 08:55:02', null, null, '383', '384', null, null, null, null, null, null, null, null, null, null, null, null, 'ouqJduOkOtbIGPbS-3rHC6-bvYVk', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-01 08:55:02', null, null, null);
INSERT INTO `people` VALUES ('2', 'u2016061508550276', null, null, null, null, null, null, null, null, null, '老王', null, '老王', null, null, '2', null, null, null, null, null, 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061508550955.jpeg', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-02 16:31:26', null, null, null);

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `cat_id` int(11) unsigned DEFAULT NULL COMMENT '栏目ID',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `title` varchar(1024) DEFAULT NULL COMMENT '图片名称',
  `pic_path` varchar(1024) DEFAULT NULL COMMENT '图片路径',
  `local_pic_path` varchar(1024) DEFAULT NULL COMMENT '本地图片路径',
  `oss_pic_path` varchar(1024) DEFAULT NULL COMMENT 'OSS服务器图片路径',
  `link_path` varchar(512) DEFAULT NULL COMMENT '链接地址',
  `album_id` int(11) unsigned DEFAULT NULL COMMENT '相册ID',
  `link_type` int(4) unsigned DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `expiry_date` datetime DEFAULT NULL COMMENT '有效期',
  `is_cover` int(4) unsigned DEFAULT NULL COMMENT '是否为封面',
  `is_main` int(4) unsigned DEFAULT NULL COMMENT '是否为主图片',
  `is_thumb` int(4) unsigned DEFAULT NULL COMMENT '是不断缩略图',
  `size` double(11,0) DEFAULT NULL COMMENT '图片大小',
  `ext` varchar(50) DEFAULT NULL COMMENT '图片后缀名',
  `description` varchar(1024) DEFAULT NULL COMMENT '图片描述',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `has_watermark` tinyint(4) DEFAULT NULL COMMENT '是否已经水印',
  `origin` varchar(255) DEFAULT NULL COMMENT '来源',
  `scans` int(11) unsigned DEFAULT '0' COMMENT '浏览次数',
  `hits` int(11) unsigned DEFAULT '0' COMMENT '点击次数',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：图片表';

-- ----------------------------
-- Records of picture
-- ----------------------------

-- ----------------------------
-- Table structure for provider
-- ----------------------------
DROP TABLE IF EXISTS `provider`;
CREATE TABLE `provider` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `is_real_auth` int(4) unsigned DEFAULT NULL COMMENT '是否实名认证',
  `sex` int(4) unsigned DEFAULT NULL COMMENT '性别：1女，2：男',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户手机号码',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录id',
  `tel` varchar(255) DEFAULT NULL COMMENT '座机',
  `phone_conceal` int(4) unsigned DEFAULT NULL COMMENT '手机是否公开',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `pic_path` varchar(1024) DEFAULT NULL COMMENT '图片路径',
  `pic_paths` varchar(2048) DEFAULT NULL COMMENT '多张图片',
  `contact_way` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(255) DEFAULT NULL COMMENT '最后登录IP',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `province` int(11) unsigned DEFAULT NULL COMMENT '省份',
  `city` int(11) unsigned DEFAULT NULL COMMENT '城市',
  `weixin` varchar(255) DEFAULT NULL COMMENT '微信',
  `qq` varchar(255) DEFAULT NULL COMMENT 'QQ',
  `weibo` varchar(255) DEFAULT NULL COMMENT '微博',
  `job_id` int(11) DEFAULT NULL COMMENT '工作ID',
  `job_code` varchar(255) DEFAULT NULL COMMENT '工作代码',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `title_code` varchar(255) DEFAULT NULL COMMENT '职称代码',
  `qr_code` varchar(255) DEFAULT NULL COMMENT '二维码',
  `qr_code2` varchar(255) DEFAULT NULL COMMENT '二维码2',
  `third_name` varchar(255) DEFAULT NULL COMMENT '第三方平台的名称',
  `third_id` varchar(255) DEFAULT NULL COMMENT '第三方ID（聊天接口等）',
  `third_password` varchar(255) DEFAULT NULL COMMENT '第三方平台的密码',
  `wx_openid` varchar(255) DEFAULT NULL COMMENT '微信openid（标识微信用户）',
  `role_id` int(11) unsigned DEFAULT NULL COMMENT '角色ID：1.普通用户2.管理员',
  `organization` varchar(255) DEFAULT NULL COMMENT '公司，团队，机构',
  `idcard_num` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `certificate_type` int(11) unsigned DEFAULT NULL COMMENT '证件类型',
  `certificate_number` varchar(255) DEFAULT NULL COMMENT '证件号码',
  `certificate_name` varchar(255) DEFAULT NULL COMMENT '证件姓名',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `link_id2` int(11) DEFAULT NULL COMMENT '关联ID2',
  `link_id3` int(11) DEFAULT NULL COMMENT '关联ID3',
  `link_id4` int(11) DEFAULT NULL COMMENT '关联ID4',
  `link_id5` int(11) DEFAULT NULL COMMENT '关联ID5',
  `link_code` varchar(255) DEFAULT NULL COMMENT '关联代码',
  `link_code2` varchar(255) DEFAULT NULL COMMENT '关联代码2',
  `link_code3` varchar(255) DEFAULT NULL COMMENT '关联代码3',
  `link_code4` varchar(255) DEFAULT NULL COMMENT '关联代码4',
  `link_code5` varchar(255) DEFAULT NULL COMMENT '关联代码5',
  `link_str` varchar(255) DEFAULT NULL COMMENT '关联字符串',
  `param_json` varchar(255) DEFAULT NULL COMMENT 'json格式参数',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_str3` varchar(255) DEFAULT NULL,
  `attr_int` int(11) unsigned DEFAULT '0' COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `username` (`username`),
  KEY `phone` (`phone`),
  KEY `email` (`email`),
  KEY `main_status` (`main_status`),
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`),
  KEY `link_id` (`link_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通用：服务提供者表';

-- ----------------------------
-- Records of provider
-- ----------------------------
INSERT INTO `provider` VALUES ('1', 'u2016061508550275', '1', null, null, null, null, null, null, null, null, 'airson', '21218cca77804d2ba1922c33e0151105', 'airson', null, null, '1', null, null, null, null, null, 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061508550955.jpeg', null, null, null, '2016-07-10 08:55:02', null, null, '383', '384', null, null, null, null, null, null, null, null, null, null, null, null, 'ouqJduOkOtbIGPbS-3rHC6-bvYVk', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-01 08:55:02', null, null, null);
INSERT INTO `provider` VALUES ('2', 'u2016061508550276', null, null, null, null, null, null, null, null, null, '老王', null, '老王', null, null, '2', null, null, null, null, null, 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061508550955.jpeg', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-02 16:31:26', null, null, null);

-- ----------------------------
-- Table structure for province_city_area
-- ----------------------------
DROP TABLE IF EXISTS `province_city_area`;
CREATE TABLE `province_city_area` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '上级地区',
  `area_name` varchar(30) DEFAULT '' COMMENT '地区名称',
  `area_pinyin` varchar(60) DEFAULT '' COMMENT '地区的拼音',
  `area_pinyin_head` varchar(20) DEFAULT '' COMMENT '地区拼音的开头字母',
  `area_short_name` varchar(255) DEFAULT NULL COMMENT '地区短名称',
  `level_id` int(4) DEFAULT NULL COMMENT '地区级别（1省，2市,3区）',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `FK_AREA_FK_AREA` (`parent_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=476 DEFAULT CHARSET=utf8 COMMENT='通用：省市区表';

-- ----------------------------
-- Records of province_city_area
-- ----------------------------
INSERT INTO `province_city_area` VALUES ('1', null, '1', null, null, null, null, null, null, null, null, null, '北京', 'beijing', 'bj', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('2', null, '1', null, null, null, null, null, null, null, null, '1', '东城区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('3', null, '1', null, null, null, null, null, null, null, null, '1', '西城区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('4', null, '1', null, null, null, null, null, null, null, null, '1', '朝阳区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('5', null, '1', null, null, null, null, null, null, null, null, '1', '海淀区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('6', null, '1', null, null, null, null, null, null, null, null, '1', '丰台区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('7', null, '1', null, null, null, null, null, null, null, null, '1', '石景山区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('8', null, '1', null, null, null, null, null, null, null, null, '1', '门头沟区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('9', null, '1', null, null, null, null, null, null, null, null, '1', '昌平区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('10', null, '1', null, null, null, null, null, null, null, null, '1', '大兴区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('11', null, '1', null, null, null, null, null, null, null, null, '1', '怀柔区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('12', null, '1', null, null, null, null, null, null, null, null, '1', '密云县', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('13', null, '1', null, null, null, null, null, null, null, null, '1', '平谷区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('14', null, '1', null, null, null, null, null, null, null, null, '1', '顺义区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('15', null, '1', null, null, null, null, null, null, null, null, '1', '通州区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('16', null, '1', null, null, null, null, null, null, null, null, '1', '延庆县', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('17', null, '1', null, null, null, null, null, null, null, null, '1', '房山区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('18', null, '1', null, null, null, null, null, null, null, null, null, '上海', 'shanghai', 'sh', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('19', null, '1', null, null, null, null, null, null, null, null, '18', '黄浦区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('20', null, '1', null, null, null, null, null, null, null, null, '18', '南市区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('21', null, '1', null, null, null, null, null, null, null, null, '18', '卢湾区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('22', null, '1', null, null, null, null, null, null, null, null, '18', '徐汇区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('23', null, '1', null, null, null, null, null, null, null, null, '18', '长宁区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('24', null, '1', null, null, null, null, null, null, null, null, '18', '静安区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('25', null, '1', null, null, null, null, null, null, null, null, '18', '普陀区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('26', null, '1', null, null, null, null, null, null, null, null, '18', '金山区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('27', null, '1', null, null, null, null, null, null, null, null, '18', '闸北区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('28', null, '1', null, null, null, null, null, null, null, null, '18', '虹口区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('29', null, '1', null, null, null, null, null, null, null, null, '18', '杨浦区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('30', null, '1', null, null, null, null, null, null, null, null, '18', '宝山区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('31', null, '1', null, null, null, null, null, null, null, null, '18', '闵行区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('32', null, '1', null, null, null, null, null, null, null, null, '18', '嘉定区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('33', null, '1', null, null, null, null, null, null, null, null, '18', '松江区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('34', null, '1', null, null, null, null, null, null, null, null, '18', '浦东新区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('35', null, '1', null, null, null, null, null, null, null, null, '18', '青浦县', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('36', null, '1', null, null, null, null, null, null, null, null, '18', '奉贤县', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('37', null, '1', null, null, null, null, null, null, null, null, '18', '南汇县', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('38', null, '1', null, null, null, null, null, null, null, null, '18', '崇明县', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('39', null, '1', null, null, null, null, null, null, null, null, null, '天津', 'tianjin', 'tj', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('40', null, '1', null, null, null, null, null, null, null, null, '39', '和平区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('41', null, '1', null, null, null, null, null, null, null, null, '39', '河东区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('42', null, '1', null, null, null, null, null, null, null, null, '39', '河西区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('43', null, '1', null, null, null, null, null, null, null, null, '39', '河北区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('44', null, '1', null, null, null, null, null, null, null, null, '39', '南开区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('45', null, '1', null, null, null, null, null, null, null, null, '39', '红桥区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('46', null, '1', null, null, null, null, null, null, null, null, '39', '塘沽区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('47', null, '1', null, null, null, null, null, null, null, null, '39', '汉沽区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('48', null, '1', null, null, null, null, null, null, null, null, '39', '大港区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('49', null, '1', null, null, null, null, null, null, null, null, '39', '东丽区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('50', null, '1', null, null, null, null, null, null, null, null, '39', '西青区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('51', null, '1', null, null, null, null, null, null, null, null, '39', '津南区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('52', null, '1', null, null, null, null, null, null, null, null, '39', '北辰区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('53', null, '1', null, null, null, null, null, null, null, null, '39', ' 武清区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('54', null, '1', null, null, null, null, null, null, null, null, '39', '宝坻区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('55', null, '1', null, null, null, null, null, null, null, null, '39', '蓟 县', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('56', null, '1', null, null, null, null, null, null, null, null, '39', '宁河县', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('57', null, '1', null, null, null, null, null, null, null, null, '39', '静海县', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('58', null, '1', null, null, null, null, null, null, null, null, null, '重庆', 'chongqing', 'cq', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('59', null, '1', null, null, null, null, null, null, null, null, '58', '永川市', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('60', null, '1', null, null, null, null, null, null, null, null, '58', '黔江区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('61', null, '1', null, null, null, null, null, null, null, null, '58', '涪陵区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('62', null, '1', null, null, null, null, null, null, null, null, '58', '万洲区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('63', null, '1', null, null, null, null, null, null, null, null, '58', '渝中区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('64', null, '1', null, null, null, null, null, null, null, null, '58', '大渡口区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('65', null, '1', null, null, null, null, null, null, null, null, '58', '江北区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('66', null, '1', null, null, null, null, null, null, null, null, '58', '沙坪坝区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('67', null, '1', null, null, null, null, null, null, null, null, '58', '九龙坡区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('68', null, '1', null, null, null, null, null, null, null, null, '58', '南岸区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('69', null, '1', null, null, null, null, null, null, null, null, '58', '北碚区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('70', null, '1', null, null, null, null, null, null, null, null, '58', '万盛区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('71', null, '1', null, null, null, null, null, null, null, null, '58', '双桥区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('72', null, '1', null, null, null, null, null, null, null, null, '58', '渝北区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('73', null, '1', null, null, null, null, null, null, null, null, '58', '巴南区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('74', null, '1', null, null, null, null, null, null, null, null, '58', '长寿区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('75', null, '1', null, null, null, null, null, null, null, null, null, '黑龙江', 'heilongjiang', 'hlj', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('76', null, '1', null, null, null, null, null, null, null, null, '75', '哈尔滨', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('77', null, '1', null, null, null, null, null, null, null, null, '75', '齐齐哈尔', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('78', null, '1', null, null, null, null, null, null, null, null, '75', '牡丹江', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('79', null, '1', null, null, null, null, null, null, null, null, '75', '鹤岗', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('80', null, '1', null, null, null, null, null, null, null, null, '75', '双鸭山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('81', null, '1', null, null, null, null, null, null, null, null, '75', '鸡西', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('82', null, '1', null, null, null, null, null, null, null, null, '75', '大庆', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('83', null, '1', null, null, null, null, null, null, null, null, '75', '伊春', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('84', null, '1', null, null, null, null, null, null, null, null, '75', '佳木斯', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('85', null, '1', null, null, null, null, null, null, null, null, '75', '七台河', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('86', null, '1', null, null, null, null, null, null, null, null, '75', '黑河', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('87', null, '1', null, null, null, null, null, null, null, null, '75', '绥化', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('88', null, '1', null, null, null, null, null, null, null, null, '75', '大兴安岭地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('89', null, '1', null, null, null, null, null, null, null, null, null, '吉林', 'jilin', 'jl', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('90', null, '1', null, null, null, null, null, null, null, null, '89', '长春', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('91', null, '1', null, null, null, null, null, null, null, null, '89', '吉林市', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('92', null, '1', null, null, null, null, null, null, null, null, '89', '四平', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('93', null, '1', null, null, null, null, null, null, null, null, '89', '辽源', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('94', null, '1', null, null, null, null, null, null, null, null, '89', '通化', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('95', null, '1', null, null, null, null, null, null, null, null, '89', '白山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('96', null, '1', null, null, null, null, null, null, null, null, '89', '松原', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('97', null, '1', null, null, null, null, null, null, null, null, '89', '白城', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('98', null, '1', null, null, null, null, null, null, null, null, '89', '延边', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('99', null, '1', null, null, null, null, null, null, null, null, null, '辽宁', 'liaoning', 'ln', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('100', null, '1', null, null, null, null, null, null, null, null, '99', '沈阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('101', null, '1', null, null, null, null, null, null, null, null, '99', '大连', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('102', null, '1', null, null, null, null, null, null, null, null, '99', '锦州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('103', null, '1', null, null, null, null, null, null, null, null, '99', '鞍山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('104', null, '1', null, null, null, null, null, null, null, null, '99', '抚顺', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('105', null, '1', null, null, null, null, null, null, null, null, '99', '本溪', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('106', null, '1', null, null, null, null, null, null, null, null, '99', '丹东', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('107', null, '1', null, null, null, null, null, null, null, null, '99', '葫芦岛', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('108', null, '1', null, null, null, null, null, null, null, null, '99', '营口', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('109', null, '1', null, null, null, null, null, null, null, null, '99', '盘锦', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('110', null, '1', null, null, null, null, null, null, null, null, '99', '阜新', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('111', null, '1', null, null, null, null, null, null, null, null, '99', '辽阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('112', null, '1', null, null, null, null, null, null, null, null, '99', '铁岭', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('113', null, '1', null, null, null, null, null, null, null, null, '99', '朝阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('114', null, '1', null, null, null, null, null, null, null, null, null, '内蒙古', 'neimenggu', 'nmg', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('115', null, '1', null, null, null, null, null, null, null, null, '114', '呼和浩特', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('116', null, '1', null, null, null, null, null, null, null, null, '114', '包头', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('117', null, '1', null, null, null, null, null, null, null, null, '114', '乌海', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('118', null, '1', null, null, null, null, null, null, null, null, '114', '赤峰', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('119', null, '1', null, null, null, null, null, null, null, null, '114', '通辽', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('120', null, '1', null, null, null, null, null, null, null, null, '114', '鄂尔多斯', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('121', null, '1', null, null, null, null, null, null, null, null, '114', '乌兰察布', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('122', null, '1', null, null, null, null, null, null, null, null, '114', '锡林郭勒盟', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('123', null, '1', null, null, null, null, null, null, null, null, '114', '兴安盟', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('124', null, '1', null, null, null, null, null, null, null, null, '114', '阿拉善盟', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('125', null, '1', null, null, null, null, null, null, null, null, '114', '巴彦淖尔', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('126', null, '1', null, null, null, null, null, null, null, null, '114', '呼伦贝尔', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('127', null, '1', null, null, null, null, null, null, null, null, null, '宁夏', 'ningxia', 'nx', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('128', null, '1', null, null, null, null, null, null, null, null, '127', '银川', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('129', null, '1', null, null, null, null, null, null, null, null, '127', '中卫', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('130', null, '1', null, null, null, null, null, null, null, null, '127', '石嘴山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('131', null, '1', null, null, null, null, null, null, null, null, '127', '吴忠', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('132', null, '1', null, null, null, null, null, null, null, null, '127', '固原', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('133', null, '1', null, null, null, null, null, null, null, null, null, '新疆', 'xinjiang', 'xj', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('134', null, '1', null, null, null, null, null, null, null, null, '133', '乌鲁木齐', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('135', null, '1', null, null, null, null, null, null, null, null, '133', '克拉玛依', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('136', null, '1', null, null, null, null, null, null, null, null, '133', '哈密地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('137', null, '1', null, null, null, null, null, null, null, null, '133', '和田地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('138', null, '1', null, null, null, null, null, null, null, null, '133', '阿克苏地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('139', null, '1', null, null, null, null, null, null, null, null, '133', '喀什地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('140', null, '1', null, null, null, null, null, null, null, null, '133', '克孜勒苏州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('141', null, '1', null, null, null, null, null, null, null, null, '133', '巴音郭楞', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('142', null, '1', null, null, null, null, null, null, null, null, '133', '昌吉州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('143', null, '1', null, null, null, null, null, null, null, null, '133', '博尔塔拉州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('144', null, '1', null, null, null, null, null, null, null, null, '133', '阿拉尔', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('145', null, '1', null, null, null, null, null, null, null, null, '133', '阿勒泰地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('146', null, '1', null, null, null, null, null, null, null, null, '133', '塔城地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('147', null, '1', null, null, null, null, null, null, null, null, '133', '图木舒克', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('148', null, '1', null, null, null, null, null, null, null, null, '133', '石河子', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('149', null, '1', null, null, null, null, null, null, null, null, '133', '吐鲁番地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('150', null, '1', null, null, null, null, null, null, null, null, '133', '五家渠', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('151', null, '1', null, null, null, null, null, null, null, null, '133', '伊犁州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('152', null, '1', null, null, null, null, null, null, null, null, null, '青海', 'qinghai', 'qh', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('153', null, '1', null, null, null, null, null, null, null, null, '152', '西宁', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('154', null, '1', null, null, null, null, null, null, null, null, '152', '海东地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('155', null, '1', null, null, null, null, null, null, null, null, '152', '海北州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('156', null, '1', null, null, null, null, null, null, null, null, '152', '黄南州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('157', null, '1', null, null, null, null, null, null, null, null, '152', '海南州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('158', null, '1', null, null, null, null, null, null, null, null, '152', '果洛州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('159', null, '1', null, null, null, null, null, null, null, null, '152', '玉树州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('160', null, '1', null, null, null, null, null, null, null, null, '152', '海西州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('161', null, '1', null, null, null, null, null, null, null, null, null, '甘肃', 'gansu', 'gs', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('162', null, '1', null, null, null, null, null, null, null, null, '161', '兰州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('163', null, '1', null, null, null, null, null, null, null, null, '161', '天水', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('164', null, '1', null, null, null, null, null, null, null, null, '161', '金昌', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('165', null, '1', null, null, null, null, null, null, null, null, '161', '白银', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('166', null, '1', null, null, null, null, null, null, null, null, '161', '嘉峪关', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('167', null, '1', null, null, null, null, null, null, null, null, '161', '张掖', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('168', null, '1', null, null, null, null, null, null, null, null, '161', '平凉', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('169', null, '1', null, null, null, null, null, null, null, null, '161', '酒泉', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('170', null, '1', null, null, null, null, null, null, null, null, '161', '庆阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('171', null, '1', null, null, null, null, null, null, null, null, '161', '定西地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('172', null, '1', null, null, null, null, null, null, null, null, '161', '陇南地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('173', null, '1', null, null, null, null, null, null, null, null, '161', '甘南州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('174', null, '1', null, null, null, null, null, null, null, null, '161', '临夏州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('175', null, '1', null, null, null, null, null, null, null, null, '161', '武威', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('176', null, '1', null, null, null, null, null, null, null, null, null, '陕西', 'shanxi', 'sx', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('177', null, '1', null, null, null, null, null, null, null, null, '176', '西安', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('178', null, '1', null, null, null, null, null, null, null, null, '176', '宝鸡', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('179', null, '1', null, null, null, null, null, null, null, null, '176', '延安', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('180', null, '1', null, null, null, null, null, null, null, null, '176', '铜川', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('181', null, '1', null, null, null, null, null, null, null, null, '176', '咸阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('182', null, '1', null, null, null, null, null, null, null, null, '176', '渭南', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('183', null, '1', null, null, null, null, null, null, null, null, '176', '汉中', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('184', null, '1', null, null, null, null, null, null, null, null, '176', '榆林', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('185', null, '1', null, null, null, null, null, null, null, null, '176', '安康', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('186', null, '1', null, null, null, null, null, null, null, null, '176', '商洛', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('187', null, '1', null, null, null, null, null, null, null, null, null, '河北', 'hebei', 'hb', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('188', null, '1', null, null, null, null, null, null, null, null, '187', '石家庄', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('189', null, '1', null, null, null, null, null, null, null, null, '187', '保定', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('190', null, '1', null, null, null, null, null, null, null, null, '187', '唐山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('191', null, '1', null, null, null, null, null, null, null, null, '187', '秦皇岛', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('192', null, '1', null, null, null, null, null, null, null, null, '187', '邯郸', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('193', null, '1', null, null, null, null, null, null, null, null, '187', '邢台', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('194', null, '1', null, null, null, null, null, null, null, null, '187', '张家口', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('195', null, '1', null, null, null, null, null, null, null, null, '187', '承德', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('196', null, '1', null, null, null, null, null, null, null, null, '187', '沧州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('197', null, '1', null, null, null, null, null, null, null, null, '187', '廊坊', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('198', null, '1', null, null, null, null, null, null, null, null, '187', '衡水', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('199', null, '1', null, null, null, null, null, null, null, null, null, '河南', 'henan', 'hn', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('200', null, '1', null, null, null, null, null, null, null, null, '199', '郑州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('201', null, '1', null, null, null, null, null, null, null, null, '199', '洛阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('202', null, '1', null, null, null, null, null, null, null, null, '199', '开封', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('203', null, '1', null, null, null, null, null, null, null, null, '199', '平顶山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('204', null, '1', null, null, null, null, null, null, null, null, '199', '焦作', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('205', null, '1', null, null, null, null, null, null, null, null, '199', '鹤壁', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('206', null, '1', null, null, null, null, null, null, null, null, '199', '新乡', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('207', null, '1', null, null, null, null, null, null, null, null, '199', '安阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('208', null, '1', null, null, null, null, null, null, null, null, '199', '濮阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('209', null, '1', null, null, null, null, null, null, null, null, '199', '许昌', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('210', null, '1', null, null, null, null, null, null, null, null, '199', '漯河', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('211', null, '1', null, null, null, null, null, null, null, null, '199', '三门峡', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('212', null, '1', null, null, null, null, null, null, null, null, '199', '南阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('213', null, '1', null, null, null, null, null, null, null, null, '199', '商丘', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('214', null, '1', null, null, null, null, null, null, null, null, '199', '信阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('215', null, '1', null, null, null, null, null, null, null, null, '199', '周口', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('216', null, '1', null, null, null, null, null, null, null, null, '199', '驻马店', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('217', null, '1', null, null, null, null, null, null, null, null, null, '山东', 'shandong', 'sd', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('218', null, '1', null, null, null, null, null, null, null, null, '217', '济南', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('219', null, '1', null, null, null, null, null, null, null, null, '217', '青岛', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('220', null, '1', null, null, null, null, null, null, null, null, '217', '烟台', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('221', null, '1', null, null, null, null, null, null, null, null, '217', '淄博', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('222', null, '1', null, null, null, null, null, null, null, null, '217', '枣庄', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('223', null, '1', null, null, null, null, null, null, null, null, '217', '东营', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('224', null, '1', null, null, null, null, null, null, null, null, '217', '潍坊', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('225', null, '1', null, null, null, null, null, null, null, null, '217', '威海', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('226', null, '1', null, null, null, null, null, null, null, null, '217', '济宁', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('227', null, '1', null, null, null, null, null, null, null, null, '217', '泰安', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('228', null, '1', null, null, null, null, null, null, null, null, '217', '日照', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('229', null, '1', null, null, null, null, null, null, null, null, '217', '莱芜', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('230', null, '1', null, null, null, null, null, null, null, null, '217', '德州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('231', null, '1', null, null, null, null, null, null, null, null, '217', '临沂', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('232', null, '1', null, null, null, null, null, null, null, null, '217', '聊城', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('233', null, '1', null, null, null, null, null, null, null, null, '217', '滨州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('234', null, '1', null, null, null, null, null, null, null, null, '217', '菏泽', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('235', null, '1', null, null, null, null, null, null, null, null, null, '山西', 'shanxi', 'sx', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('236', null, '1', null, null, null, null, null, null, null, null, '235', '太原', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('237', null, '1', null, null, null, null, null, null, null, null, '235', '大同', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('238', null, '1', null, null, null, null, null, null, null, null, '235', '朔州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('239', null, '1', null, null, null, null, null, null, null, null, '235', '阳泉', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('240', null, '1', null, null, null, null, null, null, null, null, '235', '长治', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('241', null, '1', null, null, null, null, null, null, null, null, '235', '晋城', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('242', null, '1', null, null, null, null, null, null, null, null, '235', '忻州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('243', null, '1', null, null, null, null, null, null, null, null, '235', '晋中', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('244', null, '1', null, null, null, null, null, null, null, null, '235', '临汾', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('245', null, '1', null, null, null, null, null, null, null, null, '235', '运城', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('246', null, '1', null, null, null, null, null, null, null, null, '235', '吕梁地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('247', null, '1', null, null, null, null, null, null, null, null, null, '湖北', 'hubei', 'hb', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('248', null, '1', null, null, null, null, null, null, null, null, '247', '武汉', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('249', null, '1', null, null, null, null, null, null, null, null, '247', '黄石', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('250', null, '1', null, null, null, null, null, null, null, null, '247', '襄阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('251', null, '1', null, null, null, null, null, null, null, null, '247', '十堰', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('252', null, '1', null, null, null, null, null, null, null, null, '247', '荆州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('253', null, '1', null, null, null, null, null, null, null, null, '247', '宜昌', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('254', null, '1', null, null, null, null, null, null, null, null, '247', '荆门', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('255', null, '1', null, null, null, null, null, null, null, null, '247', '鄂州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('256', null, '1', null, null, null, null, null, null, null, null, '247', '孝感', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('257', null, '1', null, null, null, null, null, null, null, null, '247', '黄冈', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('258', null, '1', null, null, null, null, null, null, null, null, '247', '咸宁', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('259', null, '1', null, null, null, null, null, null, null, null, '247', '随州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('260', null, '1', null, null, null, null, null, null, null, null, '247', '神龙架地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('261', null, '1', null, null, null, null, null, null, null, null, '247', '恩施', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('262', null, '1', null, null, null, null, null, null, null, null, '247', '天门', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('263', null, '1', null, null, null, null, null, null, null, null, '247', '潜江', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('264', null, '1', null, null, null, null, null, null, null, null, '247', '仙桃', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('265', null, '1', null, null, null, null, null, null, null, null, null, '湖南', 'hunan', 'hn', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('266', null, '1', null, null, null, null, null, null, null, null, '265', '长沙', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('267', null, '1', null, null, null, null, null, null, null, null, '265', '株洲', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('268', null, '1', null, null, null, null, null, null, null, null, '265', '湘潭', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('269', null, '1', null, null, null, null, null, null, null, null, '265', '衡阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('270', null, '1', null, null, null, null, null, null, null, null, '265', '邵阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('271', null, '1', null, null, null, null, null, null, null, null, '265', '岳阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('272', null, '1', null, null, null, null, null, null, null, null, '265', '常德', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('273', null, '1', null, null, null, null, null, null, null, null, '265', '张家界', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('274', null, '1', null, null, null, null, null, null, null, null, '265', '益阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('275', null, '1', null, null, null, null, null, null, null, null, '265', '郴州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('276', null, '1', null, null, null, null, null, null, null, null, '265', '永州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('277', null, '1', null, null, null, null, null, null, null, null, '265', '怀化', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('278', null, '1', null, null, null, null, null, null, null, null, '265', '娄底', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('279', null, '1', null, null, null, null, null, null, null, null, '265', '湘西州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('280', null, '1', null, null, null, null, null, null, null, null, null, '安徽', 'anhui', 'ah', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('281', null, '1', null, null, null, null, null, null, null, null, '280', '合肥', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('282', null, '1', null, null, null, null, null, null, null, null, '280', '芜湖', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('283', null, '1', null, null, null, null, null, null, null, null, '280', '蚌埠', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('284', null, '1', null, null, null, null, null, null, null, null, '280', '淮南', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('285', null, '1', null, null, null, null, null, null, null, null, '280', '马鞍山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('286', null, '1', null, null, null, null, null, null, null, null, '280', '淮北', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('287', null, '1', null, null, null, null, null, null, null, null, '280', '铜陵', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('288', null, '1', null, null, null, null, null, null, null, null, '280', '安庆', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('289', null, '1', null, null, null, null, null, null, null, null, '280', '黄山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('290', null, '1', null, null, null, null, null, null, null, null, '280', '滁州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('291', null, '1', null, null, null, null, null, null, null, null, '280', '阜阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('292', null, '1', null, null, null, null, null, null, null, null, '280', '宿州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('293', null, '1', null, null, null, null, null, null, null, null, '280', '巢湖', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('294', null, '1', null, null, null, null, null, null, null, null, '280', '六安', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('295', null, '1', null, null, null, null, null, null, null, null, '280', '亳州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('296', null, '1', null, null, null, null, null, null, null, null, '280', '池州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('297', null, '1', null, null, null, null, null, null, null, null, '280', '宣城', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('298', null, '1', null, null, null, null, null, null, null, null, null, '江苏', 'jiangsu', 'js', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('299', null, '1', null, null, null, null, null, null, null, null, '298', '南京', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('300', null, '1', null, null, null, null, null, null, null, null, '298', '徐州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('301', null, '1', null, null, null, null, null, null, null, null, '298', '连云港', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('302', null, '1', null, null, null, null, null, null, null, null, '298', '淮安', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('303', null, '1', null, null, null, null, null, null, null, null, '298', '宿迁', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('304', null, '1', null, null, null, null, null, null, null, null, '298', '盐城', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('305', null, '1', null, null, null, null, null, null, null, null, '298', '扬州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('306', null, '1', null, null, null, null, null, null, null, null, '298', '泰州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('307', null, '1', null, null, null, null, null, null, null, null, '298', '南通', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('308', null, '1', null, null, null, null, null, null, null, null, '298', '镇江', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('309', null, '1', null, null, null, null, null, null, null, null, '298', '常州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('310', null, '1', null, null, null, null, null, null, null, null, '298', '无锡', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('311', null, '1', null, null, null, null, null, null, null, null, '298', '苏州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('312', null, '1', null, null, null, null, null, null, null, null, null, '浙江', 'zhejiang', 'zj', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('313', null, '1', null, null, null, null, null, null, null, null, '312', '杭州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('314', null, '1', null, null, null, null, null, null, null, null, '312', '宁波', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('315', null, '1', null, null, null, null, null, null, null, null, '312', '温州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('316', null, '1', null, null, null, null, null, null, null, null, '312', '嘉兴', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('317', null, '1', null, null, null, null, null, null, null, null, '312', '湖州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('318', null, '1', null, null, null, null, null, null, null, null, '312', '绍兴', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('319', null, '1', null, null, null, null, null, null, null, null, '312', '金华', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('320', null, '1', null, null, null, null, null, null, null, null, '312', '衢州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('321', null, '1', null, null, null, null, null, null, null, null, '312', '舟山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('322', null, '1', null, null, null, null, null, null, null, null, '312', '台州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('323', null, '1', null, null, null, null, null, null, null, null, '312', '丽水', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('324', null, '1', null, null, null, null, null, null, null, null, null, '江西', 'jiangxi', 'jx', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('325', null, '1', null, null, null, null, null, null, null, null, '324', '南昌', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('326', null, '1', null, null, null, null, null, null, null, null, '324', '景德镇', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('327', null, '1', null, null, null, null, null, null, null, null, '324', '萍乡', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('328', null, '1', null, null, null, null, null, null, null, null, '324', '新余', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('329', null, '1', null, null, null, null, null, null, null, null, '324', '九江', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('330', null, '1', null, null, null, null, null, null, null, null, '324', '鹰潭', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('331', null, '1', null, null, null, null, null, null, null, null, '324', '赣州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('332', null, '1', null, null, null, null, null, null, null, null, '324', '吉安', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('333', null, '1', null, null, null, null, null, null, null, null, '324', '宜春', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('334', null, '1', null, null, null, null, null, null, null, null, '324', '抚州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('335', null, '1', null, null, null, null, null, null, null, null, '324', '上饶', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('336', null, '1', null, null, null, null, null, null, null, null, null, '广东', 'guangdong', 'gd', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('337', null, '1', null, null, null, null, null, null, null, null, '336', '广州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('338', null, '1', null, null, null, null, null, null, null, null, '336', '深圳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('339', null, '1', null, null, null, null, null, null, null, null, '336', '珠海', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('340', null, '1', null, null, null, null, null, null, null, null, '336', '汕头', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('341', null, '1', null, null, null, null, null, null, null, null, '336', '韶关', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('342', null, '1', null, null, null, null, null, null, null, null, '336', '河源', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('343', null, '1', null, null, null, null, null, null, null, null, '336', '惠州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('344', null, '1', null, null, null, null, null, null, null, null, '336', '汕尾', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('345', null, '1', null, null, null, null, null, null, null, null, '336', '东莞', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('346', null, '1', null, null, null, null, null, null, null, null, '336', '中山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('347', null, '1', null, null, null, null, null, null, null, null, '336', '江门', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('348', null, '1', null, null, null, null, null, null, null, null, '336', '佛山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('349', null, '1', null, null, null, null, null, null, null, null, '336', '阳江', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('350', null, '1', null, null, null, null, null, null, null, null, '336', '湛江', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('351', null, '1', null, null, null, null, null, null, null, null, '336', '茂名', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('352', null, '1', null, null, null, null, null, null, null, null, '336', '肇庆', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('353', null, '1', null, null, null, null, null, null, null, null, '336', '清远', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('354', null, '1', null, null, null, null, null, null, null, null, '336', '潮州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('355', null, '1', null, null, null, null, null, null, null, null, '336', '揭阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('356', null, '1', null, null, null, null, null, null, null, null, '336', '云浮', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('357', null, '1', null, null, null, null, null, null, null, null, '336', '梅州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('358', null, '1', null, null, null, null, null, null, null, null, null, '广西', 'guangxi', 'gx', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('359', null, '1', null, null, null, null, null, null, null, null, '358', '南宁', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('360', null, '1', null, null, null, null, null, null, null, null, '358', '柳州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('361', null, '1', null, null, null, null, null, null, null, null, '358', '桂林', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('362', null, '1', null, null, null, null, null, null, null, null, '358', '梧州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('363', null, '1', null, null, null, null, null, null, null, null, '358', '北海', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('364', null, '1', null, null, null, null, null, null, null, null, '358', '防城港', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('365', null, '1', null, null, null, null, null, null, null, null, '358', '钦州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('366', null, '1', null, null, null, null, null, null, null, null, '358', '贵港', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('367', null, '1', null, null, null, null, null, null, null, null, '358', '玉林', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('368', null, '1', null, null, null, null, null, null, null, null, '358', '百色', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('369', null, '1', null, null, null, null, null, null, null, null, '358', '贺州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('370', null, '1', null, null, null, null, null, null, null, null, '358', '河池', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('371', null, '1', null, null, null, null, null, null, null, null, '358', '来宾', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('372', null, '1', null, null, null, null, null, null, null, null, '358', '崇左', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('373', null, '1', null, null, null, null, null, null, null, null, null, '福建', 'fujian', 'fj', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('374', null, '1', null, null, null, null, null, null, null, null, '373', '福州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('375', null, '1', null, null, null, null, null, null, null, null, '373', '厦门', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('376', null, '1', null, null, null, null, null, null, null, null, '373', '三明', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('377', null, '1', null, null, null, null, null, null, null, null, '373', '莆田', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('378', null, '1', null, null, null, null, null, null, null, null, '373', '泉州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('379', null, '1', null, null, null, null, null, null, null, null, '373', '漳州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('380', null, '1', null, null, null, null, null, null, null, null, '373', '南平', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('381', null, '1', null, null, null, null, null, null, null, null, '373', '龙岩', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('382', null, '1', null, null, null, null, null, null, null, null, '373', '宁德', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('383', null, '1', null, null, null, null, null, null, null, null, null, '四川', 'sichuan', 'sc', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('384', null, '1', null, null, null, null, null, null, null, null, '383', '成都', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('385', null, '1', null, null, null, null, null, null, null, null, '383', '自贡', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('386', null, '1', null, null, null, null, null, null, null, null, '383', '攀枝花', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('387', null, '1', null, null, null, null, null, null, null, null, '383', '泸州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('388', null, '1', null, null, null, null, null, null, null, null, '383', '德阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('389', null, '1', null, null, null, null, null, null, null, null, '383', '绵阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('390', null, '1', null, null, null, null, null, null, null, null, '383', '广元', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('391', null, '1', null, null, null, null, null, null, null, null, '383', '遂宁', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('392', null, '1', null, null, null, null, null, null, null, null, '383', '内江', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('393', null, '1', null, null, null, null, null, null, null, null, '383', '乐山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('394', null, '1', null, null, null, null, null, null, null, null, '383', '南充', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('395', null, '1', null, null, null, null, null, null, null, null, '383', '宜宾', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('396', null, '1', null, null, null, null, null, null, null, null, '383', '广安', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('397', null, '1', null, null, null, null, null, null, null, null, '383', '达州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('398', null, '1', null, null, null, null, null, null, null, null, '383', '巴中', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('399', null, '1', null, null, null, null, null, null, null, null, '383', '雅安', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('400', null, '1', null, null, null, null, null, null, null, null, '383', '眉山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('401', null, '1', null, null, null, null, null, null, null, null, '383', '资阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('402', null, '1', null, null, null, null, null, null, null, null, '383', '阿坝州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('403', null, '1', null, null, null, null, null, null, null, null, '383', '甘孜州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('404', null, '1', null, null, null, null, null, null, null, null, '383', '凉山州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('405', null, '1', null, null, null, null, null, null, null, null, null, '云南', 'yunnan', 'yn', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('406', null, '1', null, null, null, null, null, null, null, null, '405', '昆明', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('407', null, '1', null, null, null, null, null, null, null, null, '405', '曲靖', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('408', null, '1', null, null, null, null, null, null, null, null, '405', '玉溪', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('409', null, '1', null, null, null, null, null, null, null, null, '405', '保山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('410', null, '1', null, null, null, null, null, null, null, null, '405', '昭通', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('411', null, '1', null, null, null, null, null, null, null, null, '405', '普洱', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('412', null, '1', null, null, null, null, null, null, null, null, '405', '临沧', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('413', null, '1', null, null, null, null, null, null, null, null, '405', '丽江', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('414', null, '1', null, null, null, null, null, null, null, null, '405', '文山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('415', null, '1', null, null, null, null, null, null, null, null, '405', '红河州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('416', null, '1', null, null, null, null, null, null, null, null, '405', '西双版纳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('417', null, '1', null, null, null, null, null, null, null, null, '405', '楚雄州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('418', null, '1', null, null, null, null, null, null, null, null, '405', '德宏州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('419', null, '1', null, null, null, null, null, null, null, null, '405', '怒江州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('420', null, '1', null, null, null, null, null, null, null, null, '405', '迪庆州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('421', null, '1', null, null, null, null, null, null, null, null, '405', '大理州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('422', null, '1', null, null, null, null, null, null, null, null, null, '贵州', 'guizhou', 'gz', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('423', null, '1', null, null, null, null, null, null, null, null, '422', '贵阳', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('424', null, '1', null, null, null, null, null, null, null, null, '422', '六盘水', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('425', null, '1', null, null, null, null, null, null, null, null, '422', '遵义', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('426', null, '1', null, null, null, null, null, null, null, null, '422', '安顺', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('427', null, '1', null, null, null, null, null, null, null, null, '422', '铜仁地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('428', null, '1', null, null, null, null, null, null, null, null, '422', '毕节地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('429', null, '1', null, null, null, null, null, null, null, null, '422', '黔西南州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('430', null, '1', null, null, null, null, null, null, null, null, '422', '黔东南州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('431', null, '1', null, null, null, null, null, null, null, null, '422', '黔南州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('432', null, '1', null, null, null, null, null, null, null, null, null, '西藏', 'xizang', 'xz', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('433', null, '1', null, null, null, null, null, null, null, null, '432', '拉萨', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('434', null, '1', null, null, null, null, null, null, null, null, '432', '那曲地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('435', null, '1', null, null, null, null, null, null, null, null, '432', '昌都地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('436', null, '1', null, null, null, null, null, null, null, null, '432', '山南地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('437', null, '1', null, null, null, null, null, null, null, null, '432', '日喀则地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('438', null, '1', null, null, null, null, null, null, null, null, '432', '阿里地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('439', null, '1', null, null, null, null, null, null, null, null, '432', '林芝地区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('440', null, '1', null, null, null, null, null, null, null, null, null, '海南', 'hainan', 'hn', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('441', null, '1', null, null, null, null, null, null, null, null, '440', '海口', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('442', null, '1', null, null, null, null, null, null, null, null, '440', '三亚', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('443', null, '1', null, null, null, null, null, null, null, null, '440', '五指山', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('444', null, '1', null, null, null, null, null, null, null, null, '440', '文昌', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('445', null, '1', null, null, null, null, null, null, null, null, '440', '万宁', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('446', null, '1', null, null, null, null, null, null, null, null, '440', '屯昌', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('447', null, '1', null, null, null, null, null, null, null, null, '440', '陵水', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('448', null, '1', null, null, null, null, null, null, null, null, '440', '临高', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('449', null, '1', null, null, null, null, null, null, null, null, '440', '乐东', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('450', null, '1', null, null, null, null, null, null, null, null, '440', '琼中', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('451', null, '1', null, null, null, null, null, null, null, null, '440', '琼海', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('452', null, '1', null, null, null, null, null, null, null, null, '440', '定安', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('453', null, '1', null, null, null, null, null, null, null, null, '440', '东方', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('454', null, '1', null, null, null, null, null, null, null, null, '440', '澄迈', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('455', null, '1', null, null, null, null, null, null, null, null, '440', '昌江', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('456', null, '1', null, null, null, null, null, null, null, null, '440', '保亭', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('457', null, '1', null, null, null, null, null, null, null, null, '440', '白沙', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('458', null, '1', null, null, null, null, null, null, null, null, '440', '儋州', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('459', null, '1', null, null, null, null, null, null, null, null, null, '香港', 'xianggang', 'xg', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('460', null, '1', null, null, null, null, null, null, null, null, '459', '香港', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('461', null, '1', null, null, null, null, null, null, null, null, null, '澳门', 'aomen', 'am', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('462', null, '1', null, null, null, null, null, null, null, null, '461', '澳门', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('463', null, '1', null, null, null, null, null, null, null, null, null, '台湾', 'taiwan', 'tw', null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('464', null, '1', null, null, null, null, null, null, null, null, '463', '台北', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('465', null, '1', null, null, null, null, null, null, null, null, '463', '高雄', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('466', null, '1', null, null, null, null, null, null, null, null, '463', '台中', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('467', null, '1', null, null, null, null, null, null, null, null, '463', '台南', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', '2016-03-17 16:10:34', '3');
INSERT INTO `province_city_area` VALUES ('468', null, '1', null, null, null, null, null, null, null, null, '463', '基隆', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('469', null, '1', null, null, null, null, null, null, null, null, '463', '新竹', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('470', null, '1', null, null, null, null, null, null, null, null, '463', '新北', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('471', null, '1', null, null, null, null, null, null, null, null, '463', '嘉义', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);
INSERT INTO `province_city_area` VALUES ('474', null, '1', null, null, null, '2016-03-17 15:01:22', '3', null, null, null, '1', '校区', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', '2016-03-17 15:01:22', '3');
INSERT INTO `province_city_area` VALUES ('475', null, '1', null, null, null, null, null, null, null, null, '247', '孝感', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-03-16 15:38:17', 'admin', null, null);

-- ----------------------------
-- Table structure for quick_menu
-- ----------------------------
DROP TABLE IF EXISTS `quick_menu`;
CREATE TABLE `quick_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `menu_id` int(11) unsigned DEFAULT NULL COMMENT '菜单ID',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：用户快捷菜单表';

-- ----------------------------
-- Records of quick_menu
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `parent_id` int(11) DEFAULT NULL COMMENT '父角色ID',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='通用：角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', null, null, null, null, null, null, null, null, null, null, '管理员', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `role_id` int(11) unsigned DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) unsigned DEFAULT NULL COMMENT '菜单ID',
  `menu_ids` varchar(255) DEFAULT NULL COMMENT '菜单ID串',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='通用：角色菜单表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', null, null, null, null, null, null, null, null, null, null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('2', null, null, null, null, null, null, null, null, null, null, '1', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('3', null, null, null, null, null, null, null, null, null, null, '1', '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('4', null, null, null, null, null, null, null, null, null, null, '1', '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('5', null, null, null, null, null, null, null, null, null, null, '1', '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('6', null, null, null, null, null, null, null, null, null, null, '1', '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('7', null, null, null, null, null, null, null, null, null, null, '1', '7', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('8', null, null, null, null, null, null, null, null, null, null, '1', '8', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('9', null, null, null, null, null, null, null, null, null, null, '1', '9', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('10', null, null, null, null, null, null, null, null, null, null, '1', '10', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('11', null, null, null, null, null, null, null, null, null, null, '1', '11', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('12', null, null, null, null, null, null, null, null, null, null, '1', '12', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('13', null, null, null, null, null, null, null, null, null, null, '1', '13', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('14', null, null, null, null, null, null, null, null, null, null, '1', '14', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('15', null, null, null, null, null, null, null, null, null, null, '1', '15', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('16', null, null, null, null, null, null, null, null, null, null, '1', '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('17', null, null, null, null, null, null, null, null, null, null, '1', '17', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('18', null, null, null, null, null, null, null, null, null, null, '1', '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('19', null, null, null, null, null, null, null, null, null, null, '1', '19', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('20', null, null, null, null, null, null, null, null, null, null, '1', '20', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('21', null, null, null, null, null, null, null, null, null, null, '1', '21', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('22', null, null, null, null, null, null, null, null, null, null, '1', '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('23', null, null, null, null, null, null, null, null, null, null, '1', '23', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('24', null, null, null, null, null, null, null, null, null, null, '1', '24', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('25', null, null, null, null, null, null, null, null, null, null, '1', '25', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('26', null, null, null, null, null, null, null, null, null, null, '1', '26', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('27', null, null, null, null, null, null, null, null, null, null, '1', '27', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('28', null, null, null, null, null, null, null, null, null, null, '1', '28', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('29', null, null, null, null, null, null, null, null, null, null, '1', '29', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('30', null, null, null, null, null, null, null, null, null, null, '1', '30', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('31', null, null, null, null, null, null, null, null, null, null, '1', '31', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('32', null, null, null, null, null, null, null, null, null, null, '1', '32', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('33', null, null, null, null, null, null, null, null, null, null, '1', '33', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('34', null, null, null, null, null, null, null, null, null, null, '1', '34', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('35', null, null, null, null, null, null, null, null, null, null, '1', '35', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('36', null, null, null, null, null, null, null, null, null, null, '1', '36', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('37', null, null, null, null, null, null, null, null, null, null, '1', '37', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('38', null, null, null, null, null, null, null, null, null, null, '1', '38', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('39', null, null, null, null, null, null, null, null, null, null, '1', '39', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('40', null, null, null, null, null, null, null, null, null, null, '1', '40', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('41', null, null, null, null, null, null, null, null, null, null, '1', '41', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('42', null, null, null, null, null, null, null, null, null, null, '1', '42', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('43', null, null, null, null, null, null, null, null, null, null, '1', '43', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('44', null, null, null, null, null, null, null, null, null, null, '1', '44', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('45', null, null, null, null, null, null, null, null, null, null, '1', '45', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('46', null, null, null, null, null, null, null, null, null, null, '1', '46', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('47', null, null, null, null, null, null, null, null, null, null, '1', '47', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('48', null, null, null, null, null, null, null, null, null, null, '1', '48', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('49', null, null, null, null, null, null, null, null, null, null, '1', '49', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('50', null, null, null, null, null, null, null, null, null, null, '1', '50', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('51', null, null, null, null, null, null, null, null, null, null, '1', '51', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('52', null, null, null, null, null, null, null, null, null, null, '1', '52', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('53', null, null, null, null, null, null, null, null, null, null, '1', '53', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('54', null, null, null, null, null, null, null, null, null, null, '1', '54', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('55', null, null, null, null, null, null, null, null, null, null, '1', '55', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('56', null, null, null, null, null, null, null, null, null, null, '1', '56', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('57', null, null, null, null, null, null, null, null, null, null, '1', '57', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('58', null, null, null, null, null, null, null, null, null, null, '1', '58', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('59', null, null, null, null, null, null, null, null, null, null, '1', '59', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('60', null, null, null, null, null, null, null, null, null, null, '1', '60', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('61', null, null, null, null, null, null, null, null, null, null, '1', '61', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('62', null, null, null, null, null, null, null, null, null, null, '1', '62', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('63', null, null, null, null, null, null, null, null, null, null, '1', '63', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('64', null, null, null, null, null, null, null, null, null, null, '1', '64', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('65', null, null, null, null, null, null, null, null, null, null, '1', '65', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `role_menu` VALUES ('66', null, null, null, null, null, null, null, null, null, null, '1', '66', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for schedulers
-- ----------------------------
DROP TABLE IF EXISTS `schedulers`;
CREATE TABLE `schedulers` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `task_type_id` int(11) DEFAULT NULL COMMENT '任务类型ID(1开拍提醒)',
  `notice_id` int(11) unsigned DEFAULT NULL COMMENT '通知ID(1专场开拍提醒)',
  `link_url` varchar(255) DEFAULT NULL COMMENT '关联连接地址',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `receiver_id` int(11) unsigned DEFAULT NULL COMMENT '接收人ID',
  `receiver_ids` varchar(255) DEFAULT NULL COMMENT '接收人ID串',
  `description` varchar(1023) DEFAULT NULL COMMENT '描述',
  `executed_num` int(11) unsigned DEFAULT '0' COMMENT '已执行次数',
  `pic_path` varchar(1024) DEFAULT NULL COMMENT '图片路径',
  `pic_id` int(11) unsigned DEFAULT NULL COMMENT '图片ID',
  `grade` int(11) unsigned DEFAULT NULL COMMENT '等级',
  `is_valid` tinyint(4) DEFAULT NULL COMMENT '是否起效',
  `trigger_key` varchar(255) DEFAULT NULL COMMENT '触发条件-key',
  `trigger_value` varchar(255) DEFAULT NULL COMMENT '触发条件-value',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间（触发时间）',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='通用：任务调度表';

-- ----------------------------
-- Records of schedulers
-- ----------------------------
INSERT INTO `schedulers` VALUES ('3', null, null, null, null, null, null, null, null, null, null, '1', '1', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '3', null, null, null, null, null, null, null, null, null, '2016-07-13 15:36:45', 'airson', null, null);

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `name` varchar(255) DEFAULT NULL COMMENT '店铺名称',
  `logo` varchar(255) DEFAULT NULL COMMENT '店铺图标',
  `address` varchar(255) DEFAULT NULL COMMENT '店铺地址',
  `is_warrant` int(4) unsigned DEFAULT NULL COMMENT '担保交易(1.否2.是)',
  `is_seven_return` int(4) unsigned DEFAULT NULL COMMENT '是否七天退货(1.否2.是)',
  `is_free_postage` int(4) unsigned DEFAULT NULL COMMENT '是否包邮(1.否2.是)',
  `credit_margin` int(11) DEFAULT '0' COMMENT '信誉保证金',
  `grade` int(11) unsigned DEFAULT NULL COMMENT '等级',
  `score` varchar(255) DEFAULT NULL COMMENT '评分',
  `is_pc_upload_allowed` int(4) DEFAULT '1' COMMENT '是否电脑端代上传藏品(1否，2是)',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商城交易：店铺表';

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', 's2016061508550993', null, null, null, null, null, null, null, null, null, '1', 'airson', 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061508550955.jpeg', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 08:55:10', 'airson', null, null);

-- ----------------------------
-- Table structure for static_page
-- ----------------------------
DROP TABLE IF EXISTS `static_page`;
CREATE TABLE `static_page` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `path` varchar(255) DEFAULT NULL COMMENT '页面路径',
  `name` varchar(255) DEFAULT NULL COMMENT '页面名称',
  `content` text COMMENT '页面内容',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：静态页面表';

-- ----------------------------
-- Records of static_page
-- ----------------------------

-- ----------------------------
-- Table structure for sys_account_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_log`;
CREATE TABLE `sys_account_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID(产生费用的用户）',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户手机',
  `tel` varchar(255) DEFAULT NULL COMMENT '用户座机',
  `discount` decimal(11,2) DEFAULT NULL COMMENT '折扣',
  `money` decimal(11,2) DEFAULT NULL COMMENT '价格',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_type_id` int(11) unsigned DEFAULT NULL COMMENT '支付类型ID',
  `pay_name` varchar(255) DEFAULT NULL COMMENT '支付方式名称',
  `in_or_out` int(2) unsigned DEFAULT NULL COMMENT '收入或支出(1，收入2，支出)',
  `trade_status` int(2) unsigned DEFAULT NULL COMMENT '交易状态(1未完成，2成功完成，3失败，4其他)',
  `sys_total_income_log` decimal(11,2) DEFAULT NULL COMMENT '系统总收入记录',
  `sys_total_pay_log` decimal(11,2) DEFAULT NULL COMMENT '系统总支出记录',
  `avaliable_money_log` decimal(11,2) DEFAULT NULL COMMENT '用户可用资金',
  `frozen_money_log` decimal(11,2) DEFAULT NULL COMMENT '用户冻结资金',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城交易：系统官方交易记录表';

-- ----------------------------
-- Records of sys_account_log
-- ----------------------------

-- ----------------------------
-- Table structure for time_gap
-- ----------------------------
DROP TABLE IF EXISTS `time_gap`;
CREATE TABLE `time_gap` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `start_time` datetime DEFAULT NULL COMMENT '总金额',
  `end_time` datetime DEFAULT NULL COMMENT '红包数量',
  `link_url` varchar(255) DEFAULT NULL COMMENT '连接地址',
  `link_type` int(11) unsigned DEFAULT NULL COMMENT '链接类型（占用：1用户，2拍卖专场）',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务：时间安排表';

-- ----------------------------
-- Records of time_gap
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `is_real_auth` int(4) unsigned DEFAULT NULL COMMENT '是否实名认证',
  `sex` int(4) unsigned DEFAULT NULL COMMENT '性别：1女，2：男',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户手机号码',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录id',
  `tel` varchar(255) DEFAULT NULL COMMENT '座机',
  `phone_conceal` int(4) unsigned DEFAULT NULL COMMENT '手机是否公开',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `pic_path` varchar(1024) DEFAULT NULL COMMENT '图片路径',
  `pic_paths` varchar(2048) DEFAULT NULL COMMENT '多张图片',
  `contact_way` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(255) DEFAULT NULL COMMENT '最后登录IP',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `province` int(11) unsigned DEFAULT NULL COMMENT '省份',
  `city` int(11) unsigned DEFAULT NULL COMMENT '城市',
  `weixin` varchar(255) DEFAULT NULL COMMENT '微信',
  `qq` varchar(255) DEFAULT NULL COMMENT 'QQ',
  `weibo` varchar(255) DEFAULT NULL COMMENT '微博',
  `job_id` int(11) DEFAULT NULL COMMENT '工作ID',
  `job_code` varchar(255) DEFAULT NULL COMMENT '工作代码',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `title_code` varchar(255) DEFAULT NULL COMMENT '职称代码',
  `qr_code` varchar(255) DEFAULT NULL COMMENT '二维码',
  `qr_code2` varchar(255) DEFAULT NULL COMMENT '二维码2',
  `third_name` varchar(255) DEFAULT NULL COMMENT '第三方平台的名称',
  `third_id` varchar(255) DEFAULT NULL COMMENT '第三方ID（聊天接口等）',
  `third_password` varchar(255) DEFAULT NULL COMMENT '第三方平台的密码',
  `wx_openid` varchar(255) DEFAULT NULL COMMENT '微信openid（标识微信用户）',
  `role_id` int(11) unsigned DEFAULT NULL COMMENT '角色ID：1.普通用户2.管理员',
  `organization` varchar(255) DEFAULT NULL COMMENT '公司，团队，机构',
  `idcard_num` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `certificate_type` int(11) unsigned DEFAULT NULL COMMENT '证件类型',
  `certificate_number` varchar(255) DEFAULT NULL COMMENT '证件号码',
  `certificate_name` varchar(255) DEFAULT NULL COMMENT '证件姓名',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `link_id2` int(11) DEFAULT NULL COMMENT '关联ID2',
  `link_id3` int(11) DEFAULT NULL COMMENT '关联ID3',
  `link_id4` int(11) DEFAULT NULL COMMENT '关联ID4',
  `link_id5` int(11) DEFAULT NULL COMMENT '关联ID5',
  `link_code` varchar(255) DEFAULT NULL COMMENT '关联代码',
  `link_code2` varchar(255) DEFAULT NULL COMMENT '关联代码2',
  `link_code3` varchar(255) DEFAULT NULL COMMENT '关联代码3',
  `link_code4` varchar(255) DEFAULT NULL COMMENT '关联代码4',
  `link_code5` varchar(255) DEFAULT NULL COMMENT '关联代码5',
  `link_str` varchar(255) DEFAULT NULL COMMENT '关联字符串',
  `param_json` varchar(255) DEFAULT NULL COMMENT 'json格式参数',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_str3` varchar(255) DEFAULT NULL,
  `attr_int` int(11) unsigned DEFAULT '0' COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `username` (`username`),
  KEY `phone` (`phone`),
  KEY `email` (`email`),
  KEY `main_status` (`main_status`),
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`),
  KEY `link_id` (`link_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通用：用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'u2016061508550275', '1', null, null, null, null, null, null, null, null, 'airson', '21218cca77804d2ba1922c33e0151105', 'airson', null, null, '1', null, null, null, null, null, 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061508550955.jpeg', null, null, null, '2016-07-10 08:55:02', null, null, '383', '384', null, null, null, null, null, null, null, null, null, null, null, null, 'ouqJduOkOtbIGPbS-3rHC6-bvYVk', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-01 08:55:02', null, null, null);
INSERT INTO `user` VALUES ('2', 'u2016061508550276', null, null, null, null, null, null, null, null, null, '老王', null, '老王', null, null, '2', null, null, null, null, null, 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061508550955.jpeg', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-07-02 16:31:26', null, null, null);

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '该条收藏记录的会员id',
  `address_detail` varchar(255) DEFAULT NULL COMMENT '收货人详细地址',
  `phone` varchar(255) DEFAULT NULL COMMENT '收货人联系电话',
  `receiver_name` varchar(255) DEFAULT NULL COMMENT '收货人姓名',
  `province` int(11) unsigned DEFAULT NULL COMMENT '收货人所在省或直辖市',
  `city` int(11) unsigned DEFAULT NULL COMMENT '收货人所在城市',
  `is_default` int(2) DEFAULT NULL COMMENT '是否为默认地址',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：用户地址表';

-- ----------------------------
-- Records of user_address
-- ----------------------------

-- ----------------------------
-- Table structure for user_control
-- ----------------------------
DROP TABLE IF EXISTS `user_control`;
CREATE TABLE `user_control` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `control_type_id` int(11) unsigned DEFAULT NULL COMMENT '控制类型ID',
  `start_date` datetime DEFAULT NULL COMMENT '控制开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '控制结束时间',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `control_type_id` (`control_type_id`),
  KEY `start_date` (`start_date`),
  KEY `end_date` (`end_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：用户控制表';

-- ----------------------------
-- Records of user_control
-- ----------------------------

-- ----------------------------
-- Table structure for user_customer
-- ----------------------------
DROP TABLE IF EXISTS `user_customer`;
CREATE TABLE `user_customer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `group_id` int(11) DEFAULT NULL COMMENT '组ID',
  `grade_id` int(11) DEFAULT NULL COMMENT '等级',
  `main_status` int(11) DEFAULT NULL COMMENT '业务状态',
  `logic_status` int(11) DEFAULT NULL COMMENT '逻辑状态',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：用户的客户表';

-- ----------------------------
-- Records of user_customer
-- ----------------------------

-- ----------------------------
-- Table structure for user_fund
-- ----------------------------
DROP TABLE IF EXISTS `user_fund`;
CREATE TABLE `user_fund` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `frozen_money` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '冻结资金',
  `avaliable_money` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '可用金额',
  `integral_fund` decimal(10,2) DEFAULT '0.00' COMMENT '积分资产',
  `coin_fund` decimal(10,2) DEFAULT '0.00' COMMENT '金币资产',
  `other_fund` decimal(10,2) DEFAULT '0.00' COMMENT '其他资产（占用：诚信保证金）',
  `other_fund2` decimal(10,2) DEFAULT '0.00' COMMENT '其他资产2',
  `pay_password` varchar(255) DEFAULT NULL COMMENT '支付密码',
  `bank_name1` varchar(255) DEFAULT NULL COMMENT '银行名称1',
  `bank_username1` varchar(255) DEFAULT NULL COMMENT '银行账号用户名称1',
  `bank_card1` varchar(255) DEFAULT NULL COMMENT '银行卡号1',
  `bank_name2` varchar(255) DEFAULT NULL COMMENT '银行名称2',
  `bank_username2` varchar(255) DEFAULT NULL COMMENT '银行账号用户名称2',
  `bank_card2` varchar(255) DEFAULT NULL COMMENT '银行卡号2',
  `bank_name3` varchar(255) DEFAULT NULL COMMENT '银行名称3',
  `bank_username3` varchar(255) DEFAULT NULL COMMENT '银行账号用户名称3',
  `bank_card3` varchar(255) DEFAULT NULL COMMENT '银行卡号3',
  `bank_name4` varchar(255) DEFAULT NULL COMMENT '银行名称4',
  `badk_username4` varchar(255) DEFAULT NULL COMMENT '银行账号用户名称4',
  `bank_card4` varchar(255) DEFAULT NULL COMMENT '银行卡号4',
  `bank_name5` varchar(255) DEFAULT NULL COMMENT '银行名称5',
  `bank_username5` varchar(255) DEFAULT NULL COMMENT '银行账号用户名称5',
  `bank_card5` varchar(255) DEFAULT NULL COMMENT '银行卡号5',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商城交易：用户资金表';

-- ----------------------------
-- Records of user_fund
-- ----------------------------
INSERT INTO `user_fund` VALUES ('1', 'uf2016061508550949', null, null, null, null, null, null, null, null, null, '1', '0.00', '0.00', '0.00', '0.00', '1000.00', '0.00', '21218cca77804d2ba1922c33e0151105', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2016-06-15 08:55:10', 'airson', null, null);

-- ----------------------------
-- Table structure for user_fund_frozen
-- ----------------------------
DROP TABLE IF EXISTS `user_fund_frozen`;
CREATE TABLE `user_fund_frozen` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户手机',
  `trade_type_id` int(11) unsigned DEFAULT NULL COMMENT '交易类型ID',
  `link_url` varchar(255) DEFAULT NULL COMMENT '链接URL',
  `frozen_type_code` varchar(255) DEFAULT NULL COMMENT '数据字典代码：资金冻结类型',
  `frozen_money` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '冻结资金',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '手续费',
  `is_keep` int(4) DEFAULT NULL COMMENT '是否长期把持冻结状态(是：开通专场压金，否：购买商品）',
  `avaliable_money_log` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '账户总可用金额记录',
  `frozen_money_log` decimal(10,2) DEFAULT NULL COMMENT '账户总冻结金额记录',
  `freeze_time` datetime DEFAULT NULL COMMENT '冻结时间',
  `frozen_money_status_code` varchar(255) DEFAULT NULL COMMENT '数据字典代码：冻结资金状态',
  `deal_time` datetime DEFAULT NULL COMMENT '处理时间',
  `the_other_type` varchar(255) DEFAULT NULL COMMENT '对方类型（用户，商店，红包）',
  `the_other_name` varchar(255) DEFAULT NULL COMMENT '对方名称',
  `the_other_id` int(11) unsigned DEFAULT NULL COMMENT '对方ID',
  `charge_id` int(11) DEFAULT NULL COMMENT '关联的充值ID',
  `open_id` varchar(255) DEFAULT NULL COMMENT '用户的微信openId',
  `order_sn` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `title` varchar(512) DEFAULT NULL COMMENT '标题',
  `content` varchar(512) DEFAULT NULL COMMENT '内容',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城交易：用户资金冻结表';

-- ----------------------------
-- Records of user_fund_frozen
-- ----------------------------

-- ----------------------------
-- Table structure for user_praise
-- ----------------------------
DROP TABLE IF EXISTS `user_praise`;
CREATE TABLE `user_praise` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `praise_type` int(11) unsigned DEFAULT NULL COMMENT '点赞类型ID（1：点赞帖子，2：点赞微拍，3：点赞即时拍）',
  `praise_id` int(11) unsigned DEFAULT NULL COMMENT '点赞对象ID',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：用户点赞表';

-- ----------------------------
-- Records of user_praise
-- ----------------------------

-- ----------------------------
-- Table structure for user_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_relation`;
CREATE TABLE `user_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `relation_type` int(11) unsigned DEFAULT NULL COMMENT '关联类型ID',
  `relation_id` int(11) unsigned DEFAULT NULL COMMENT '关联对象ID',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用：用户关联表';

-- ----------------------------
-- Records of user_relation
-- ----------------------------

-- ----------------------------
-- Table structure for withdraw
-- ----------------------------
DROP TABLE IF EXISTS `withdraw`;
CREATE TABLE `withdraw` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增整型ID',
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `apply_date` datetime DEFAULT NULL COMMENT '申请日期',
  `apply_money` decimal(11,2) DEFAULT NULL COMMENT '取现金额',
  `apply_real_money` decimal(11,2) DEFAULT NULL COMMENT '实际取现金额',
  `fee` decimal(11,2) DEFAULT NULL COMMENT '手续费',
  `apply_msg` varchar(512) DEFAULT NULL COMMENT '备注消息',
  `deal_date` datetime DEFAULT NULL COMMENT '处理日期',
  `deal_status` int(11) unsigned DEFAULT NULL COMMENT '处理状态',
  `deal_user_id` int(11) DEFAULT NULL COMMENT '处理用户ID',
  `deal_username` varchar(255) DEFAULT NULL COMMENT '处理用户名',
  `attr1` int(11) unsigned DEFAULT NULL COMMENT '备用整形字段',
  `attr2` varchar(255) DEFAULT NULL COMMENT '备用字符串字段',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_decimal` decimal(11,2) DEFAULT NULL COMMENT 'BigDecimal',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `deal_status` (`deal_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城交易：取现表';

-- ----------------------------
-- Records of withdraw
-- ----------------------------

-- ----------------------------
-- Table structure for wx_msg_count
-- ----------------------------
DROP TABLE IF EXISTS `wx_msg_count`;
CREATE TABLE `wx_msg_count` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '序号',
  `main_status` int(11) DEFAULT '1' COMMENT '业务状态',
  `logic_status` int(11) DEFAULT '1' COMMENT '逻辑状态',
  `type_id` int(11) unsigned DEFAULT NULL COMMENT '类型ID',
  `type_code` varchar(255) DEFAULT NULL COMMENT '类型CODE',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` varchar(255) DEFAULT NULL COMMENT '删除人',
  `group_id` int(11) DEFAULT '1' COMMENT '组ID',
  `grade_id` int(11) DEFAULT '1' COMMENT '等级',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '数据字典CODE',
  `msg_type` int(11) DEFAULT NULL COMMENT '消息类型(1微信客服消息)',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `count` int(11) unsigned DEFAULT NULL COMMENT '当日消息发送数量',
  `max_count` int(11) DEFAULT NULL COMMENT '最大数量',
  `send_date` date DEFAULT NULL COMMENT '发送日期（计算日期）',
  `link_type` int(11) DEFAULT NULL COMMENT '关联类型',
  `link_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '数据拥有者ID',
  `owner_ids` varchar(255) DEFAULT NULL COMMENT '数据拥有者ID串',
  `attr_str` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串',
  `attr_str2` varchar(255) DEFAULT NULL COMMENT '备用字段-字符串2',
  `attr_int` int(11) unsigned DEFAULT NULL COMMENT '备用字段-整型',
  `attr_int2` int(11) DEFAULT NULL COMMENT '备用字段-整型2',
  `sequence` int(11) DEFAULT NULL COMMENT '排列顺序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `main_status` (`main_status`) USING BTREE,
  KEY `logic_status` (`logic_status`) USING BTREE,
  KEY `type_id` (`type_id`) USING BTREE,
  KEY `deleted_at` (`deleted_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务：微信消息记录';

-- ----------------------------
-- Records of wx_msg_count
-- ----------------------------
