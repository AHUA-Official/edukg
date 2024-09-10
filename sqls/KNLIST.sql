CREATE TABLE `KNLIST` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bookid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '书本id',
  `usrid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '用户id',
  `courseid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '课程id',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'fufu' COMMENT '标题',
  `content` longtext COLLATE utf8mb4_unicode_ci  COMMENT '内容',
  `loadpath` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'fufu.json' COMMENT '存储路径',
  `createat` datetime DEFAULT NULL COMMENT '创建时间',
  `updateat` datetime DEFAULT NULL COMMENT '更新时间',
  `isdel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='知识清单表';