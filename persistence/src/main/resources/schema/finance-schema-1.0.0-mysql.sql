DROP TABLE IF EXISTS finance_user, finance_permission;
DROP TABLE IF EXISTS finance_contract, finance_contract_history, finance_signing_mode, finance_contract_status, finance_signing_status;
DROP TABLE IF EXISTS finance_merchants;
DROP TABLE IF EXISTS finance_leaseback_proposal, finance_proposal_history;
DROP TABLE IF EXISTS finance_basic_ledger, finance_added_ledger, finance_pay_status;

SET SESSION SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO';

CREATE TABLE finance_user (
  id               BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username         varchar(200) NOT NULL ,
  password         varchar(200) NOT NULL ,
  permissions      varchar(100) NOT NULL ,
  description      varchar(500),
  createTime       DATETIME(3) not null default current_timestamp(3),
  lastUpdateTime   DATETIME(3) on update current_timestamp(3)
) charset=utf8;

CREATE INDEX idx_finance_user_username ON finance_user (username);
CREATE INDEX idx_finance_user_permissions ON finance_user (permissions);

INSERT INTO finance_user ( username, password, permissions,description)
VALUES
  ('admin', '123456', '1', 'admin user, has all permissions');

CREATE TABLE finance_permission (
  id                SMALLINT         NOT NULL      PRIMARY KEY ,
  permissionName    varchar(100)     NOT NULL ,
  permissionDes     varchar(200),
  createTime       DATETIME(3) not null default current_timestamp(3),
  lastUpdateTime   DATETIME(3) on update current_timestamp(3)
) CHARSET = utf8;

INSERT INTO finance_permission (id, permissionName, permissionDes)
VALUES
  (1, 'ADMIN', 'super administrator privileges'),
  (2, 'READ', 'read privilege'),
  (3, 'WRITE', 'write privilege, include privilege of create and update'),
  (4, 'DELETE', 'delete privilege');

CREATE TABLE finance_contract (
  id               BIGINT             NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  region           VARCHAR(100)       ,
  contractNo       VARCHAR(100)       NOT NULL ,
  contractVersion  VARCHAR(20)        ,
  subscriptionDate DATETIME(3)        ,
  signer           VARCHAR(1000)      ,
  signingMode      SMALLINT           ,
  signingStatus    SMALLINT           ,
  signingDate      DATETIME(3)        ,
  buildingInfo     VARCHAR(100)       ,
  buildingSize     DOUBLE             ,
  originalPrice    INT                ,
  totalPrice       INT                ,
  signTotalPrice   INT                ,
  leasebackPrice   INT                ,
  backPremium      INT                ,
  paybackDate      DATETIME(3)        ,
  payStartDate     DATETIME(3)        ,
  contractTerDate  DATETIME(3)        ,
  beneficiary      VARCHAR(200)       ,
  proposalId       INT                ,
  contractStatus   SMALLINT           NOT NULL ,
  logs             TEXT               NOT NULL ,
  createTime       DATETIME(3) not null default current_timestamp(3),
  lastUpdateTime   DATETIME(3) on update current_timestamp(3)
) charset=utf8;

CREATE INDEX idx_finance_contract_no ON finance_contract (contractNo);
CREATE INDEX idx_finance_contract_signer ON finance_contract (signer);

CREATE TABLE finance_contract_history (
  id               BIGINT             NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  contractId       BIGINT             NOT NULL ,
  region           VARCHAR(100)       ,
  contractNo       VARCHAR(100)       NOT NULL ,
  contractVersion  VARCHAR(20)        ,
  subscriptionDate DATETIME(3)        ,
  signer           VARCHAR(1000)      ,
  signingMode      SMALLINT           ,
  signingStatus    SMALLINT           ,
  signingDate      DATETIME(3)        ,
  buildingInfo     VARCHAR(100)       ,
  buildingSize     DOUBLE             ,
  originalPrice    INT                ,
  totalPrice       INT                ,
  signTotalPrice   INT                ,
  leasebackPrice   INT                ,
  backPremium      INT                ,
  paybackDate      DATETIME(3)        ,
  payStartDate     DATETIME(3)        ,
  contractTerDate  DATETIME(3)        ,
  beneficiary      VARCHAR(200)       ,
  proposalId       INT                ,
  contractStatus   SMALLINT           NOT NULL ,
  logs             TEXT               NOT NULL ,
  effectiveStartTime DATETIME(3)      DEFAULT current_timestamp(3),
  effectiveEndTime   DATETIME(3)      DEFAULT '2099-12-31 23:59:59.999'
) charset=utf8;

CREATE TABLE finance_signing_mode (
  id               SMALLINT           NOT NULL PRIMARY KEY ,
  signModeName     VARCHAR(100)       NOT NULL ,
  signModeDes      VARCHAR(200) ,
  createTime        DATETIME(3)       DEFAULT current_timestamp(3),
  lastUpdateTime    DATETIME(3)       on update current_timestamp(3)
) charset=utf8;

INSERT INTO finance_signing_mode (id, signModeName, signModeDes)
VALUES
  (1 , 'MORTGAGE' , 'pay with mortgage'),
  (2 , 'DISPOSABLE', 'disposable payment');

CREATE TABLE finance_signing_status (
  id               SMALLINT           NOT NULL PRIMARY KEY ,
  signStatusName     VARCHAR(100)       NOT NULL ,
  signStatusDes      VARCHAR(200) ,
  createTime        DATETIME(3)       DEFAULT current_timestamp(3),
  lastUpdateTime    DATETIME(3)       on update current_timestamp(3)
) charset=utf8;

INSERT INTO finance_signing_status (id, signStatusName, signStatusDes)
VALUES
  (1 , 'UNSIGNED' , 'has not signed'),
  (2 , 'SIGNED', 'has been signed');

CREATE TABLE finance_contract_status (
  id                        SMALLINT          NOT NULL PRIMARY KEY ,
  contractStatusName        VARCHAR(100)      NOT NULL ,
  contractStatusDes         VARCHAR(200)      NOT NULL ,
  createTime        DATETIME(3)       DEFAULT current_timestamp(3),
  lastUpdateTime    DATETIME(3)       on update current_timestamp(3)
) charset=utf8;

INSERT INTO finance_contract_status (id, contractStatusName, contractStatusDes)
VALUES
  (1, 'UNSTARTED', 'has not signed'),
  (2, 'PENDINGRENTAL', 'Market cultivation period, did not begin to return rent'),
  (3, 'RENTAL', 'has already begun to return rent'),
  (4, 'NORMALEND', 'the contract ended normally'),
  (5, 'ABNORMALEND', 'the contract ended abnormally');

CREATE TABLE finance_merchants (
  id                BIGINT                NOT NULL  AUTO_INCREMENT PRIMARY KEY ,
  merchantName      VARCHAR(100)          ,
  merchantPhone     VARCHAR(100)           ,
  merchantIdNo      VARCHAR(300)           ,
  bankInfo          VARCHAR(1000)          ,
  bankAccount       VARCHAR(200)           ,
  merchantAddress   VARCHAR(10000)          ,
  createTime        DATETIME(3)       DEFAULT current_timestamp(3),
  lastUpdateTime    DATETIME(3)       on update current_timestamp(3)
) charset=utf8;

CREATE UNIQUE INDEX idx_finance_merchants ON finance_merchants (merchantName, merchantPhone, bankAccount);

/*
CREATE TABLE finance_building (
  id                BIGINT                NOT NULL   AUTO_INCREMENT  PRIMARY KEY ,
  buildingNo        SMALLINT              NOT NULL ,
  floor             SMALLINT ,
  roomNo            SMALLINT ,
  buildingAddress   VARCHAR(200)         NOT NULL ,
  ownerId           BIGINT               NOT NULL ,
  createTime        DATETIME(3)       DEFAULT current_timestamp(3),
  lastUpdateTime    DATETIME(3)       on update current_timestamp(3)
) charset=utf8;


CREATE INDEX idx_finance_building_owner ON finance_building (ownerId);
 */

CREATE TABLE finance_leaseback_proposal (
  id               BIGINT              NOT NULL  AUTO_INCREMENT  PRIMARY KEY ,
  proposalName     VARCHAR(100)          NOT NULL ,
  leasebackLife    SMALLINT              NOT NULL ,
  marketCulLife    SMALLINT              NOT NULL ,
  leasebackStages  SMALLINT              NOT NULL ,
  conf             TEXT                  NOT NULL ,
  proposalDes      VARCHAR(200),
  createTime        DATETIME(3)       DEFAULT current_timestamp(3),
  lastUpdateTime    DATETIME(3)       on update current_timestamp(3)
) charset=utf8;

INSERT INTO finance_leaseback_proposal (proposalName, leasebackLife, marketCulLife, leasebackStages, conf, proposalDes)
VALUES  ('v1', 15, 3, 3, '[{"period":1, "duration":2, "proportion":0.065}, {"period":2, "duration":5, "proportion":0.07}, {"period":3, "duration":5, "proportion":0.075}]','etl'),
  ('v2', 5, 3, 1, '[{"period":1, "duration":2, "proportion":0.065}]', 'etl'),
  ('v3', 15, 3, 3, '[{"period":1, "duration":2, "proportion":0.065}, {"period":2, "duration":10, "proportion":0.07}]', 'etl');


CREATE TABLE finance_proposal_history (
  hisId            INT                   NOT NULL  AUTO_INCREMENT  PRIMARY KEY ,
  proposalId       SMALLINT              NOT NULL ,
  leasebackLife    SMALLINT              NOT NULL ,
  MarketCulLife    SMALLINT              NOT NULL ,
  leasebackStages  SMALLINT              NOT NULL ,
  conf             TEXT                  NOT NULL ,
  proposalDes      VARCHAR(200),
  effectiveStartTime DATETIME(3)      DEFAULT current_timestamp(3),
  effectiveEndTime   DATETIME(3)      DEFAULT '2099-12-31 23:59:59.999'
) charset=utf8;

CREATE TABLE finance_basic_ledger (
  id               BIGINT                NOT NULL   AUTO_INCREMENT  PRIMARY KEY ,
  contractId       BIGINT                NOT NULL ,
  contractNo       VARCHAR(100)          NOT NULL ,
  beneficiaryId    BIGINT                NOT NULL ,
  calFormula       VARCHAR(200)          NOT NULL ,
  planPayDate      DATETIME(3)           NOT NULL ,
  planPayCountPre     DOUBLE                NOT NULL ,
  planPayCountPost     DOUBLE                NOT NULL ,
  actualPayDate    DATETIME(3)           NOT NULL ,
  actualPayCount   DOUBLE                NOT NULL ,
  payStatus        SMALLINT              NOT NULL ,
  createTime        DATETIME(3)       DEFAULT current_timestamp(3),
  lastUpdateTime    DATETIME(3)       on update current_timestamp(3)
) charset=utf8;

CREATE TABLE finance_pay_status (
  id               SMALLINT           NOT NULL PRIMARY KEY ,
  payStatusName     VARCHAR(100)       NOT NULL ,
  payStatusDes      VARCHAR(200) ,
  createTime        DATETIME(3)       DEFAULT current_timestamp(3),
  lastUpdateTime    DATETIME(3)       on update current_timestamp(3)
) charset=utf8;

INSERT INTO finance_pay_status (id, payStatusName, payStatusDes)
VALUES
  (1 , 'UNPAID' , 'has not paid'),
  (2 , 'SUCCESSFULPAID', 'has been paid successful'),
  (3 , 'FAILEDPAID', 'has been paid failed');


CREATE INDEX idx_contractNo_basic_ledger ON finance_basic_ledger (contractNo);

CREATE TABLE finance_added_ledger (
  id               BIGINT                NOT NULL   AUTO_INCREMENT  PRIMARY KEY ,
  contractId       BIGINT                NOT NULL ,
  merchantId       BIGINT                NOT NULL ,
  calFormula       VARCHAR(100)          NOT NULL ,
  planPayDate      DATETIME(3)           NOT NULL ,
  planPayCount     DOUBLE                NOT NULL ,
  actualPayDate    DATETIME(3)           NOT NULL ,
  actualPayCount   DOUBLE                NOT NULL ,
  createTime        DATETIME(3)       DEFAULT current_timestamp(3),
  lastUpdateTime    DATETIME(3)       on update current_timestamp(3)
) charset=utf8;

CREATE INDEX idx_merchants_added_ledger ON finance_added_ledger (merchantId);


DROP TABLE IF EXISTS finance_permission_list;
CREATE TABLE finance_permission_list (
  id        BIGINT         NOT NULL      AUTO_INCREMENT,
  menuid    BIGINT     NOT NULL,
  permission     varchar(50) NOT NULL,
  createTime       DATETIME(3) not null default current_timestamp(3),
  lastUpdateTime   DATETIME(3) on update current_timestamp(3),
  PRIMARY KEY (`id`)
) CHARSET = utf8;
insert into `finance_permission_list` (`menuid`,`permission`) values
(7020,'1'),
(7030,'1'),
(8010,'1'),
(8020,'1'),
(9010,'1'),
(9020,'1'),
(9030,'1'),
(9040,'1'),
(9050,'1'),
(9060,'1'),
(7020,'2'),
(7030,'2'),
(8020,'2'),
(9050,'2'),
(9060,'2');

DROP TABLE IF EXISTS `finance_menu`;
CREATE TABLE `finance_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `p_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsni20f28wjqrmpp44uawa2ky4` (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6051 DEFAULT CHARSET=utf8;


/*Data for the table `finance_menu` */
insert into `finance_menu`(`id`,`icon`,`name`,`state`,`url`,`p_id`) values
(1,'menu-plugin','系统菜单',1,NULL,-1),
(90,'menu-6','系统管理',1,NULL,1),
(70,'menu-5','返租资料管理',1,NULL,1),
(80,'menu-3','台账管理',1,NULL,1),
(7020,'menu-13','资料查询',0,'/contract/contractSearch.html',70),
(7030,'menu-4','返租计算',0,'/contract/calculator.html',70),
-- (8010,'menu-32','添加台账',0,'/ledger/addLedger.html',80),
-- (8020,'menu-13','查询台账',0,'/ledger/searchLedger.html',80),
(8010,'menu-54','台账管理',0,'/ledger/ledgerManage.html',80),
(9010,'menu-61','用户管理',0,'/power/userManage.html',90),
(9020,'menu-62','权限管理',0,'/power/permissionManage.html',90),
(9030,'menu-63','方案管理',0,'/power/proposalManage.html',90),
(9040,'menu-65','系统日志',0,'/power/log.html',90),
(9050,'menu-63','修改密码',0,NULL,90),
(9060,'menu-64','安全退出',0,NULL,90);


INSERT INTO `finance_contract` VALUES ('1', '22', 'No01', '1', '2018-01-24 11:29:21.241', '1', '1', '1', '2018-01-24 11:29:21.241', '1层-商10', '221.71', '5200', '6000', '5500', '4877620', null, '2018-01-24 11:29:21.241', '2021-01-25 11:29:21.241', '2036-01-25 11:29:21.241', '{\"id\":1,\"merchantName\":\"李四\",\"merchantPhone\":\"15688888888\",\"merchantIdNo\":\"360424199999999999\",\"bankInfo\":\"建设银行\",\"bankAccount\":\"6227002020200008\",\"merchantAddress\":\"湖北省武汉市\"}', '1', '2', 'mylog', '2018-01-24 11:29:21.566', null);

INSERT INTO `finance_merchants` VALUES ('1', '李四', '15688888888', '360424199999999999', '建设银行', '6227002020200008', '湖北省武汉市', '2018-01-24 11:29:08.324', null);

INSERT INTO `finance_basic_ledger` VALUES ('1', '1', '1', '1+1', '2018-01-24 00:00:00.000', '10000', '2018-01-24 12:22:42.000', '0', '2018-01-24 12:22:49.811', null);
INSERT INTO `finance_basic_ledger` VALUES ('2', '1', '1', '2/8', '2018-01-25 00:00:00.000', '10001', '2018-02-01 12:23:13.000', '10002', '2018-01-24 12:23:20.832', null);

SET SESSION SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO';