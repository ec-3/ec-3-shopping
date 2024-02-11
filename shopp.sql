CREATE TABLE `ec^3`.`user_Info`(
  `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username`   varchar(32) NOT NULL  DEFAULT '' COMMENT '用户名称',
  `password` varchar(32)  NOT NULL DEFAULT '' COMMENT '密码',
  `card_type`   tinyint(1)  NOT NULL DEFAULT '0' COMMENT '证件类型：0 身份证，1 护照',
  `card_no`     varchar(32)  NOT NULL DEFAULT '0'  COMMENT '证件号码',
  `mobile`      varchar(32)NOT NULL DEFAULT '' COMMENT '手机号',
  `email`       varchar(32) NOT NULL DEFAULT '' COMMENT '邮箱',
  `gender`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别：0 男; 1 女  2：中性',
  `birthday`    timestamp default null COMMENT '出身日期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_name_email` (`user_name`,`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';


CREATE TABLE `ec^3`.`shop_car`(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `user_id`     varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
    `product_num`  varchar(32) NOT NULL DEFAULT '' COMMENT '商品编号',
    `quantity`     int(11) NOT NULL DEFAULT '0' COMMENT '购物车数量',
    `del_flag`  tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除：0 否; 1 是',
    `create_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_product` (`user_id`,`product_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='购物车表';

CREATE TABLE `ec^3`.`address_manage` (
   `id` bigint(20) unsigned  NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `def_flag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否默认：0-否 1-是',
   `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
   `recipient` varchar(32) NOT NULL DEFAULT '' COMMENT '收货人',
   `mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '手机号',
   `province` varchar(32) NOT NULL DEFAULT '' COMMENT '收货地址所在的省',
   `city` varchar(32) NOT NULL DEFAULT '' COMMENT '收货地址所在的市',
   `district` varchar(32) NOT NULL DEFAULT '' COMMENT '收货地址所在的区',
   `address` varchar(512) NOT NULL DEFAULT '' COMMENT '收货详细地址',
   `label` tinyint(2) NOT NULL DEFAULT '0' COMMENT '标签：0-公司 1-家 2-学校',
   `create_operator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_operator` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   `province_area_code` varchar(32) NOT NULL DEFAULT '' COMMENT '省份编码',
   `city_area_code` varchar(32) NOT NULL DEFAULT '' COMMENT '市编码',
   `district_area_code` varchar(32) NOT NULL DEFAULT '' COMMENT '区编码',
   PRIMARY KEY (`id`),
   KEY `idx_cp_addr_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='收货地址管理表';

CREATE TABLE `ec^3`.`order` (
  `id` bigint(20) unsigned  NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_id` varchar(32) NOT NULL DEFAULT '' COMMENT '支付订单号(唯一) 格式：年份后2位(yyyy) + 1年中的第?天数3位+ 一代后2位 + 雪花算法后11位,数字一共18位',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `am_id` varchar(32) NOT NULL DEFAULT '' COMMENT '收货地址ID',
  `user_name` varchar(128) NOT NULL DEFAULT '' COMMENT '用户名称',
  `total_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单总金额-单位分',
  `coupon_discount` bigint(20) NOT NULL DEFAULT '0' COMMENT '现金券抵扣金额-单位分',
  `pay_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '应付金额-单位分',
  `actual_pay_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '实付金额-单位分',
  `pay_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '支付方式：0-余额支付 1-链上支付 2-Visa支付',
  `chain_id` varchar(32) NOT NULL DEFAULT '' COMMENT '区块链ID',
  `chain_tx` varchar(32) NOT NULL DEFAULT '' COMMENT '链上交易tx',
  `recipient` varchar(32) NOT NULL DEFAULT '' COMMENT '收货人',
  `mobile` varchar(32)NOT NULL DEFAULT '' COMMENT '手机号',
  `invoice_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否开票：0-不开票; 1-开普通发票 2-开专用发票',
  `company` varchar(128) NOT NULL DEFAULT '' COMMENT '企业名称',
  `taxpayer_num` varchar(32) NOT NULL DEFAULT '' COMMENT '企业纳税人识别号（税号）',
  `company_email` varchar(32) NOT NULL DEFAULT '' COMMENT '企业邮箱',
  `company_phone` varchar(32) NOT NULL DEFAULT '' COMMENT '企业电话',
  `bank_card` varchar(32) NOT NULL DEFAULT '' COMMENT '银行账号',
  `open_account_bank` varchar(255) NOT NULL DEFAULT '' COMMENT '开户银行',
  `invoice_recipient` varchar(32) NOT NULL DEFAULT '' COMMENT '发票收件人',
  `invoice_recipient_contact` varchar(32) NOT NULL DEFAULT '' COMMENT '发票收件人联系方式',
  `invoice_recipient_email` varchar(32) NOT NULL DEFAULT '' COMMENT '收票人邮箱',
  `invoice_additional_info` varchar(255) NOT NULL DEFAULT '' COMMENT '补充资料',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '支付状态：0-待支付 1-已支付 2-已取消 3-已关闭',
  `pay_time` datetime  DEFAULT null COMMENT '支付时间',
  `delivery_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '发货状态：0-待发货 1-部分发货 2-已发货（全部发货）',
  `cancel_time` datetime  DEFAULT null COMMENT '取消订单时间',
  `create_operator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_operator` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_order_id` (`order_id`),
  KEY `idx_order_user_id` (`user_id`),
  KEY `idx_order_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='订单表';

CREATE TABLE `ec^3`.`order_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` varchar(20) NOT NULL DEFAULT '' COMMENT '用户id',
  `order_id` varchar(32) NOT NULL DEFAULT '' COMMENT '订单号(对应order表的order_id字段)',
  `product_num` varchar(32) NOT NULL DEFAULT '' COMMENT '商品编号',
  `master_pic` varchar(255) NOT NULL DEFAULT '' COMMENT '商品主图',
  `product_name` varchar(32) NOT NULL DEFAULT '' COMMENT '商品名称',
  `quantity` int(11) NOT NULL DEFAULT '0' COMMENT '购买数量',
  `price` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '销售单价',
  `equity_price` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '优惠权益后销售单价',
  `cost_price` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商品成本价',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '商品分类：0-xx, 1-xx',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_item_oid` (`order_id`),
  KEY `idx_order_item_user_id` (`user_id`),
  KEY `idx_order_item_pid` (`product_num`),
  KEY `idx_order_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='订单明细表';


CREATE TABLE `ec^3`.`product_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `product_num` varchar(32) NOT NULL DEFAULT '' COMMENT '商品编号',
  `product_name` varchar(32) NOT NULL DEFAULT '' COMMENT '商品名称',
  `supplier_id` varchar(32) NOT NULL DEFAULT '' COMMENT '供应商ID',
  `price` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '销售单价',
  `cost_price` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '商品成本价',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '商品分类：0-xx, 1-xx',
  `color_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '商品颜色：0-xx, 1-xx',
  `publish_status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '上下架状态：0下架1上架',
  `production_date` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生产日期',
  `describe` varchar(2000) NOT NULL DEFAULT '' COMMENT '商品描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_num` (`product_num`),
  KEY `idx_product_name` (`product_name`),
  KEY `idx_product_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品信息表';


CREATE TABLE `ec^3`.`product_pic` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `product_id` bigint(20) NOT NULL  COMMENT '商品ID',
  `pic_desc` varchar(1000) NOT NULL DEFAULT '' COMMENT '图片描述',
  `pic_url` varchar(100) NOT NULL DEFAULT '' COMMENT '图片URL',
  `is_master` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否主图：0.非主图1.主图',
  `pic_order` tinyint(2) NOT NULL DEFAULT '0' COMMENT '图片排序',
  `pic_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '图片是否有效：0无效 1有效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_pic_url` (`pic_url`),
  KEY `idx_pic_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品图片表';



