# 数据库改动记录
ALTER TABLE `admin_system`.`admin_market_region`
  ADD COLUMN `market_id` int not null comment '菜场id' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `market_region_id` int not null comment '区域id' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `business_license` varchar(128) comment '营业执照' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `license` varchar(128) comment '许可证' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `heath_license` varchar(128) comment '健康证' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `position` varchar(32) not null comment '摊位位置' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `business_width` int not null comment '宽' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `business_height` int not null comment '高' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `license_width` int not null comment '宽' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `license_height` int not null comment '高' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `heath_width` int not null comment '宽' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `heath_height` int not null comment '高' AFTER `update_date`;

#02-13
ALTER TABLE `admin_system`.`admin_booth`
  ADD COLUMN `license_url` varchar(128) not null comment '授权地址' AFTER `update_date`;

#12-06
ALTER TABLE `admin_system`.`admin_category`
    ADD COLUMN `code` varchar(32) not null comment '编号' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_booth` CHANGE `category_id`
    `category_code` VARCHAR(32) NOT NULL COMMENT '分类code';

ALTER TABLE `admin_system`.`admin_guide_price` CHANGE `food_id`
    `food` varchar(32) not null comment '食品';

ALTER TABLE `admin_system`.`admin_today_price` CHANGE `food_id`
    `food` varchar(32) not null comment '食品';

ALTER TABLE `admin_system`.`admin_guide_price`
    ADD COLUMN `category_code` varchar(32) not null comment '分类code' AFTER `update_date`;

ALTER TABLE `admin_system`.`admin_today_price` CHANGE booth_id
    `booth` varchar(32) not null comment '商铺';

ALTER TABLE `admin_system`.`admin_food_menu` CHANGE cuisine
    `cuisine` varchar(32) default '' comment '菜系';

ALTER TABLE `admin_system`.`admin_food_menu` CHANGE cooking
    `cooking` varchar(32) default '' comment '烹饪方式';

ALTER TABLE `admin_system`.`admin_food_menu` CHANGE effect
    `effect` varchar(256) default '' comment '功效';

ALTER TABLE `admin_system`.`admin_food_menu` CHANGE pick_material
    `pick_material` varchar(128) default '' comment '调料';

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

ALTER TABLE `admin_system`.`admin_guide_price`
    ADD COLUMN `guide_price` decimal(8, 2) default 0 comment '指导价' AFTER `update_date`;