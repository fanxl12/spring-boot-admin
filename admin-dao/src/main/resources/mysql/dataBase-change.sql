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
