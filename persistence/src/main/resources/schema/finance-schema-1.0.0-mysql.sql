DROP TABLE IF EXISTS finance_user, finance_permission;
DROP TABLE IF EXISTS finance_contract, finance_contract_history, finance_signing_mode, finance_contract_status;
DROP TABLE IF EXISTS finance_merchants;
DROP TABLE IF EXISTS finance_leaseback_proposal, finance_proposal_history;
DROP TABLE IF EXISTS finance_basic_ledger, finance_added_ledger;

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
  region           VARCHAR(100)       NOT NULL ,
  contractNo       VARCHAR(100)        ,
  contractVersion  VARCHAR(20)        NOT NULL ,
  subscriptionDate DATETIME(3)        NOT NULL ,
  signer           VARCHAR(1000)       NOT NULL ,
  signingMode      SMALLINT           NOT NULL ,
  signingDate      DATETIME(3)        NOT NULL ,
  buildingInfo     VARCHAR(100)       NOT NULL ,
  buildingSize     DOUBLE             NOT NULL ,
  originalPrice    INT                NOT NULL ,
  totalPrice       INT                NOT NULL ,
  signTotalPrice   INT                NOT NULL ,
  leasebackPrice   INT                NOT NULL ,
  backPremium      INT                NOT NULL ,
  paybackDate      DATETIME(3)        NOT NULL ,
  payStartDate     DATETIME(3)        NOT NULL ,
  contractTerDate  DATETIME(3)        NOT NULL ,
  beneficiary      VARCHAR(200)       NOT NULL ,
  proposalId       INT                NOT NULL ,
  contractStatus   SMALLINT           NOT NULL ,
  tariff           DOUBLE              ,
  taxAmount        INT                 ,
  logs             TEXT               NOT NULL ,
  createTime       DATETIME(3) not null default current_timestamp(3),
  lastUpdateTime   DATETIME(3) on update current_timestamp(3)
) charset=utf8;

CREATE INDEX idx_finance_contract_no ON finance_contract (contractNo);
CREATE INDEX idx_finance_contract_signer ON finance_contract (signer);

CREATE TABLE finance_contract_history (
  id               BIGINT             NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  region           VARCHAR(100)       NOT NULL ,
  contractNo       VARCHAR(100)        ,
  contractVersion  VARCHAR(20)        NOT NULL ,
  subscriptionDate DATETIME(3)        NOT NULL ,
  signer           VARCHAR(1000)       NOT NULL ,
  signingMode      SMALLINT           NOT NULL ,
  signingDate      DATETIME(3)        NOT NULL ,
  buildingInfo     VARCHAR(100)       NOT NULL ,
  buildingSize     DOUBLE             NOT NULL ,
  originalPrice    INT                NOT NULL ,
  totalPrice       INT                NOT NULL ,
  signTotalPrice   INT                NOT NULL ,
  leasebackPrice   INT                NOT NULL ,
  backPremium      INT                NOT NULL ,
  paybackDate      DATETIME(3)        NOT NULL ,
  beneficiary      VARCHAR(200)       NOT NULL ,
  proposalId       INT                NOT NULL ,
  contractStatus   SMALLINT           NOT NULL ,
  tariff           DOUBLE              ,
  taxAmount        INT                 ,
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

CREATE TABLE finance_contract_status (
  id                        SMALLINT          NOT NULL PRIMARY KEY ,
  contractStatusName        VARCHAR(100)      NOT NULL ,
  contractStatusDes         VARCHAR(200)      NOT NULL ,
  createTime        DATETIME(3)       DEFAULT current_timestamp(3),
  lastUpdateTime    DATETIME(3)       on update current_timestamp(3)
) charset=utf8;

INSERT INTO finance_contract_status (id, contractStatusName, contractStatusDes)
VALUES
  (1, 'UNSIGNED', 'has not signed'),
  (2, 'PENDINGRENTAL', 'Market cultivation period, did not begin to return rent'),
  (3, 'RENTAL', 'has already begun to return rent'),
  (4, 'NORMALEND', 'the contract ended normally'),
  (5, 'ABNORMALEND', 'the contract ended abnormally');

CREATE TABLE finance_merchants (
  id                BIGINT                NOT NULL  AUTO_INCREMENT PRIMARY KEY ,
  merchantName      VARCHAR(100)          NOT NULL ,
  merchantPhone     VARCHAR(20)           NOT NULL ,
  merchantIdNo      VARCHAR(30)           NOT NULL ,
  bankInfo          VARCHAR(100)          NOT NULL ,
  bankAccount       VARCHAR(20)           NOT NULL ,
  merchantAddress   VARCHAR(200)          NOT NULL ,
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
  ('v2', 5, 3, 1, '[{"period":1, "duration":2, "proportion":0.065}]', 'etl');


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
  merchantId       BIGINT                NOT NULL ,
  calFormula       VARCHAR(100)          NOT NULL ,
  planPayDate      DATETIME(3)           NOT NULL ,
  planPayCount     DOUBLE                NOT NULL ,
  actualPayDate    DATETIME(3)           NOT NULL ,
  actualPayCount   DOUBLE                NOT NULL ,
  createTime        DATETIME(3)       DEFAULT current_timestamp(3),
  lastUpdateTime    DATETIME(3)       on update current_timestamp(3)
) charset=utf8;

CREATE INDEX idx_merchants_basic_ledger ON finance_basic_ledger (merchantId);

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

SET SESSION SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO';