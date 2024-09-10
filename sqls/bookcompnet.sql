CREATE TABLE `bookcompnet` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bookid` int(11) DEFAULT NULL COMMENT '书本id',
  `role` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色 是student就去student',
  `userid` int(11) DEFAULT NULL COMMENT '角色在各自表里面的id',
  `compnet` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论',
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名字',
  `isdel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '可以用吗',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='阅读评论表';