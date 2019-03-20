-- drop SCHEMA if EXISTS testdb;

-- CREATE  SCHEMA IF NOT EXISTS testdb;
-- use testdb;
CREATE TABLE IF NOT EXISTS City (
  cityId   INT AUTO_INCREMENT NOT NULL,
  name     VARCHAR(255)       NOT NULL,
  province VARCHAR(255)       NOT NULL,
  PRIMARY KEY (`name`)
)
  DEFAULT CHARSET = utf8mb4;
