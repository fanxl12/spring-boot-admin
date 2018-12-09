USE admin_system;

CREATE TABLE `admin_user`(
  `id` VARCHAR(32) NOT NULL,
  `username` VARCHAR(32) NOT NULL COMMENT '登录名',
  `password` VARCHAR(255) NOT NULL COMMENT '密码',
  `salt` VARCHAR(128) COMMENT '密码加密盐',
  `phone` VARCHAR(18) COMMENT '手机号码',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '注册时间',
  `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  `head` VARCHAR(256) COMMENT '用户头像',
  `email` VARCHAR(64) COMMENT '邮箱',
  `last_login_time` DATETIME COMMENT '最后登录时间',
  `account_expired` DATETIME COMMENT '账户有效期 默认null永久有效',
  UNIQUE KEY (`username`),
  UNIQUE INDEX (`phone`),
  PRIMARY KEY (`id`),
  KEY (`username`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '用户表';

