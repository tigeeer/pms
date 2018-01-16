DROP DATABASE IF EXISTS pms;
CREATE DATABASE pms;
USE pms;

CREATE TABLE roles (
  id BIGINT ,
  abbr VARCHAR(255) UNIQUE ,
  name VARCHAR(255) NOT NULL UNIQUE ,
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE users (
  id BIGINT AUTO_INCREMENT ,
  username VARCHAR(255) NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  nickname VARCHAR(255) NOT NULL ,
  avatar VARCHAR(255) NOT NULL ,
  mobile VARCHAR(255) NOT NULL ,
  email VARCHAR(255) NOT NULL ,
  role VARCHAR(255) NOT NULL ,
  deleted BOOLEAN NOT NULL DEFAULT FALSE ,
  createTime DATETIME NOT NULL ,
  createUser BIGINT ,
  PRIMARY KEY (id) ,
  CONSTRAINT `users.role` FOREIGN KEY(role) REFERENCES roles(abbr)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE projects (
  id BIGINT AUTO_INCREMENT ,
  userId VARCHAR(255) NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  description VARCHAR(255) NOT NULL ,
  principal VARCHAR(255) NOT NULL ,
  createTime DATE NOT NULL ,
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE project_period (
  id BIGINT AUTO_INCREMENT ,
  projectId BIGINT NOT NULL ,
  `name` VARCHAR(255) NOT NULL ,
  description VARCHAR(255) NOT NULL ,
  status TINYINT NOT NULL ,
  startTime DATE ,
  endTime DATE ,
  createTime DATE NOT NULL ,
  PRIMARY KEY (id) ,
  CONSTRAINT `project_period.projectId` FOREIGN KEY (projectId) REFERENCES projects(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE project_stages (
  id BIGINT AUTO_INCREMENT ,
  name VARCHAR(255) NOT NULL UNIQUE ,
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

INSERT INTO project_stages(name) VALUES('需求分析'), ('详细设计'), ('编码'), ('测试'), ('运行维护');

CREATE TABLE diaries (
  id BIGINT AUTO_INCREMENT ,
  userId BIGINT NOT NULL ,
  projectId BIGINT NOT NULL ,
  content VARCHAR(500) NOT NULL ,
  createTime DATETIME NOT NULL ,
  PRIMARY KEY (id) ,
  CONSTRAINT `diaries.userId` FOREIGN KEY(userId) REFERENCES users(id) ,
  CONSTRAINT `diaries.projectId` FOREIGN KEY(projectId) REFERENCES projects(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;