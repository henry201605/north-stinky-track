-- ----------------------------
-- Table structure for b_home_area
-- ----------------------------
CREATE TABLE `b_home_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `name` varchar(200) DEFAULT NULL COMMENT '微信昵称',
  `address` varchar(255) DEFAULT NULL,
  `longitude` decimal(15,10) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(15,10) DEFAULT NULL COMMENT '纬度',
  `note` text COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '假删除标识:0:未删除;1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for b_upload_advice
-- ----------------------------
CREATE TABLE `b_upload_advice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_name` varchar(200) DEFAULT NULL COMMENT '微信昵称',
  `telephone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `longitude` decimal(15,10) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(15,10) DEFAULT NULL COMMENT '纬度',
  `note` text COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '假删除标识:0:未删除;1:删除',
  PRIMARY KEY (`id`),
  KEY `index_upload_advice_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for b_upload_stinky
-- ----------------------------
CREATE TABLE `b_upload_stinky` (
  `id` int(11) NOT NULL,
  `wind_direct` varchar(50) DEFAULT NULL COMMENT '风向',
  `wind_power` varchar(20) DEFAULT NULL COMMENT '风力',
  `weather` varchar(255) DEFAULT NULL COMMENT '天气',
  `temperature` float(3,1) DEFAULT NULL COMMENT '温度',
  `humidity` float(3,2) DEFAULT NULL COMMENT '湿度',
  `longitude` decimal(15,10) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(15,10) DEFAULT NULL COMMENT '纬度',
  `stinky_type` int(11) DEFAULT NULL COMMENT '恶臭-sys_dict_item_id',
  `feel_type` int(11) DEFAULT NULL COMMENT '体感-sys_dict_item_id',
  `occur_time` datetime DEFAULT NULL COMMENT '发生时间',
  `user_id` int(11) NOT NULL COMMENT '填报的用户id',
  `user_name` varchar(255) DEFAULT NULL,
  `temp_fied1` varchar(255) DEFAULT NULL,
  `temp_fied2` varchar(255) DEFAULT NULL,
  `note` text,
  `create_time` datetime DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '假删除标识:0:未删除;1:删除',
  PRIMARY KEY (`id`),
  KEY `index_upload_stinky_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_login
-- ----------------------------
CREATE TABLE `log_login` (
  `id` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录Ip',
  `longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) DEFAULT NULL COMMENT '纬度'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
CREATE TABLE `sys_dict_item` (
  `id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '字典内容',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `type_id` int(11) DEFAULT NULL COMMENT '类型id',
  `note` varchar(255) DEFAULT NULL COMMENT '字典的备注说明',
  `create_time` datetime DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '假删除标识:0:未删除;1:删除',
  PRIMARY KEY (`id`),
  KEY `index_dict_item_type_id` (`type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
CREATE TABLE `sys_dict_type` (
  `id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '字典类型名称',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `note` varchar(255) DEFAULT NULL COMMENT '字典的备注说明',
  `create_time` datetime DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_uid` int(11) DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '假删除标识:0:未删除;1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `user_key` varchar(100) NOT NULL COMMENT '微信openId',
  `user_name` varchar(100) DEFAULT NULL COMMENT '微信昵称',
  `telephone` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `home_area` int(11) DEFAULT NULL COMMENT '家庭区域表id',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `note` text COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_sys_user_user_key` (`user_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
