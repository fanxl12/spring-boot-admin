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
  `code` varchar(32) not null comment '名称',
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
  `category_code` varchar(32) not null comment '分类code',
  `market_region_id` int not null comment '区域id',
  `position` varchar(32) not null comment '摊位位置',
  `name` varchar(32) not null comment '名称',
  `url` varchar(128) not null comment '地址',
  `license_url` varchar(128) not null comment '授权地址',
  `business_license` varchar(128) comment '营业执照',
  `business_width` int default 0 comment '宽',
  `business_height` int default 0 comment '高',
  `license` varchar(128) comment '许可证',
  `license_width` int default 0 comment '宽',
  `license_height` int default 0 comment '高',
  `heath_license` varchar(128) comment '健康证',
  `heath_width` int default 0 comment '宽',
  `heath_height` int default 0 comment '高',
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
   `category_code` varchar(32) not null comment '分类code',
   `food` varchar(32) not null comment '食品',
   `max_price` decimal(8, 2) default 0 comment '最高价',
   `max_price_trend` tinyint(2) default 0 comment '最高价趋势 默认0无变化 1走高 -1走低',
   `average_price` decimal(8, 2) default 0 comment '平均价',
   `low_price` decimal(8, 2) default 0 comment '最低价',
   `low_price_trend` tinyint(2) default 0 comment '最低价趋势 默认0无变化 1走高 -1走低',
   `guide_price` decimal(8, 2) default 0 comment '指导价',
   `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
   `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '指导价格表';

create table `admin_today_price` (
   `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
   `price_date` DATETIME comment '价格时间',
   `food` varchar(32) not null comment '食品',
   `booth` int not null comment '商铺',
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

create table `admin_advert_image` (
   `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
   `url` varchar(128) not null comment '地址',
   `width` int not null comment '宽',
   `height` int not null comment '高',
   `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
   `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '广告图片表';

create table `admin_food_menu_category` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) not null comment '名称',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '食物菜单分类表';

create table `admin_food_menu` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `food_menu_category_id` int not null comment '分类id',
  `url` varchar(128) not null comment '图片',
  `popular` tinyint(2) not null default 0 comment '本周流行 默认0不流行 1流行',
  `difficulty` tinyint(4) not null default 0 comment '难度 默认0 简单 1普通 2困难',
  `main_material` varchar(128) not null comment '主料',
  `second_material` varchar(128) not null comment '辅料',
  `pick_material` varchar(128) default '' comment '调料',
  `effect` varchar(256) default '' comment '功效',
  `step` varchar(512) comment '功效',
  `width` int not null comment '宽',
  `height` int not null comment '高',
  `name` varchar(32) not null comment '名称',
  `cuisine` varchar(32) default '' comment '菜系',
  `cooking` varchar(32) default '' comment '烹饪方式',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '食物菜单表';

CREATE TABLE persistent_logins (
  username VARCHAR(64) NOT NULL,
  series VARCHAR(64) PRIMARY KEY,
  token VARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL
);

create table `admin_pesticide_check` (
 `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `check_date` DATETIME not null comment '检测日期',
 `booth` varchar(32) not null default '' comment '摊位',
 `category` varchar(32) not null default '' comment '品种',
 `check_project` varchar(32) not null default '' comment '检测项目',
 `place` varchar(32) not null default '' comment '产地',
 `check_value` double comment '实测值',
 `result` varchar(32) not null default '' comment '定性值',
 `check_user` varchar(32) not null default '' comment '检测人',
 `recheck_user` varchar(32) not null default '' comment '复检人',
 `check_org` varchar(32) not null default '' comment '检测机构',
 `remark` varchar(512) not null default '' comment '说明',
 `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
 `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '农残检测表';

create table `admin_stock_in` (
 `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
 `shop_name` varchar(32) not null default '' comment '商铺名称',
 `shop_code` varchar(32) not null default '' comment '商铺编号',
 `goods_code` varchar(32) not null default '' comment '货品编号',
 `goods_number` varchar(32) not null default '' comment '商品编码',
 `goods_name` varchar(32) not null default '' comment '商品名称',
 `purchase_type` varchar(32) not null default '' comment '采购模式',
 `purchase_total` double not null default 0 comment '采购总量',
 `purchase_money` decimal(8, 2) default 0 comment '采购总金额',
 `purchase_price` decimal(8, 2) default 0 comment '采购单价',
 `purchase_batch` varchar(32) not null default '' comment '采购批次',
 `supplier_name` varchar(32) not null default '' comment '供应商名称',
 `brand_name` varchar(32) not null default '' comment '品牌商名称',
 `product_place` varchar(32) not null default '' comment '生产基地',
 `purchase_date` DATETIME not null comment '采购日期',
 `purchase_status` varchar(32) not null default '' comment '采购状态',
 `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
 `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '进货录入表';

create table `admin_city` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` varchar(32) not null comment '名称',
    `code` varchar(32) not null comment '编号',
    `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '城市表';

create table `admin_video` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) not null comment '名称',
  `url` varchar(128) not null comment '地址',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '视频表';

create table `admin_plane` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(128) not null comment '地址',
  `width` int not null comment '宽',
  `height` int not null comment '高',
  `create_date` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_date` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '平面图表';
