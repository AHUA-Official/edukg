CREATE TABLE `ahaveanswer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `questiontext` text COLLATE utf8mb4_unicode_ci COMMENT '问题的文本信息',
  `prompt` text COLLATE utf8mb4_unicode_ci  COMMENT '提升',
  `agent` text COLLATE utf8mb4_unicode_ci COMMENT 'agent',
  `asktime` timestamp DEFAULT CURRENT_TIMESTAMP  COMMENT '问问题的时间',
  `genestatus` int(11) DEFAULT '0' COMMENT '当前的轮状状态',
   `answertime` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '问题被回答的时间', 
  `answer` text COLLATE utf8mb4_unicode_ci  COMMENT '问题的真是回答',
  `tag` text COLLATE utf8mb4_unicode_ci  COMMENT '实体表里面匹配到的东西，当前需求不高',
  `parentid` int(11) DEFAULT '0' COMMENT '父问题ID',
  `context` text COLLATE utf8mb4_unicode_ci  COMMENT '上下文相关，其实目前这个也是摆设',
  `satisfaction` int(11) DEFAULT '100' COMMENT '用户满意度',
  `attachment` varchar(255) COLLATE utf8mb4_unicode_ci  COMMENT '附件，目前没有用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='已经有了答案的表';