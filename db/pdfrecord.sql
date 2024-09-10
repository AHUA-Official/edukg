CREATE TABLE `PDFUploadRecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `storedName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '存名',
  `originalName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原名',
  `uploadTime` datetime DEFAULT NULL COMMENT '上传时间',
  `uploadUser` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上传用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文件注册记录表';