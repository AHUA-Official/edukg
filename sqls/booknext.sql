CREATE TABLE `booknext` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `docname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文档名字',
  `docmary` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文档简介',
  `docpath` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文档路径',
  `uploadone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上传的人',
  `courseid` int(11) DEFAULT '1' COMMENT '课程id',
  `coursename` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '课程名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程资料';