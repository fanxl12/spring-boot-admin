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

create table `admin_category` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) not null comment '名称',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '分类表';

create table `admin_wheel_picture` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(128) not null comment '地址',
  `width` int not null comment '宽',
  `height` int not null comment '高',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '轮播图表';

create table `admin_home_picture` (
   `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
   `url` varchar(128) not null comment '地址',
   `width` int not null comment '宽',
   `height` int not null comment '高',
   `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
   `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '主页底部图表';

create table `admin_booth` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category_id` int not null comment '分类id',
  `market_region_id` int not null comment '区域id',
  `position` varchar(32) not null comment '摊位位置',
  `name` varchar(32) not null comment '名称',
  `url` varchar(128) not null comment '地址',
  `business_license` varchar(128) comment '营业执照',
  `license` varchar(128) comment '许可证',
  `heath_license` varchar(128) comment '健康证',
  `star` int not null comment '评星',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '摊位表';

create table `admin_market_region` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) not null comment '名称',
  `market_id` int not null comment '菜场id',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '菜场区域表';

create table `admin_market` (
   `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
   `name` varchar(32) not null comment '名称',
   `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
   `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '菜场表';

create table `admin_food` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) not null comment '名称',
  `category_id` int not null comment '分类id',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '食品表';

create table `admin_guide_price` (
   `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
   `price_date` DATETIME not null comment '价格时间',
   `food_id` int not null comment '食品id',
   `max_price` decimal(8, 2) default 0 comment '最高价',
   `max_price_trend` tinyint(2) default 0 comment '最高价趋势 默认0无变化 1走高 -1走低',
   `average_price` decimal(8, 2) default 0 comment '平均价',
   `low_price` decimal(8, 2) default 0 comment '最低价',
   `low_price_trend` tinyint(2) default 0 comment '最低价趋势 默认0无变化 1走高 -1走低',
   `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
   `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '指导价格表';

create table `admin_today_price` (
   `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
   `price_date` DATETIME not null comment '价格时间',
   `food_id` int not null comment '食品id',
   `booth_id` int not null comment '商铺id',
   `price` decimal(8, 2) default 0 comment '价格',
   `special_price` decimal(8, 2) default 0 comment '特价',
   `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
   `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '今日特价表';

create table `admin_coupon_exercise` (
   `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
   `url` varchar(128) not null comment '地址',
   `width` int not null comment '宽',
   `height` int not null comment '高',
   `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
   `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '优惠活动表';


CREATE TABLE persistent_logins (
  username VARCHAR(64) NOT NULL,
  series VARCHAR(64) PRIMARY KEY,
  token VARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL
)

