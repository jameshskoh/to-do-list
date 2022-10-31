DROP SCHEMA IF EXISTS `todo_db`;

CREATE SCHEMA `todo_db`;

USE `todo_db`;

DROP TABLE IF EXISTS `todo`;

CREATE TABLE `todo` (
    `id` varchar(36) NOT NULL,
    `user_id` varchar(36) NOT NULL,
    `label` varchar(80) NOT NULL,
    `done` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
)
