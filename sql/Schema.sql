-- Schema for Traffic Controller System

CREATE DATABASE IF NOT EXISTS traffic_controller
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE traffic_controller;

CREATE TABLE IF NOT EXISTS traffic_signal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    intersection_name VARCHAR(255) NOT NULL,
    direction VARCHAR(10) NOT NULL,
    state VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS route (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    from_intersection VARCHAR(255) NOT NULL,
    to_intersection VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL
);

-- Sample data
INSERT INTO traffic_signal (intersection_name, direction, state, last_updated) VALUES
    ('Main & 1st', 'N', 'GREEN', NOW()),
    ('Main & 1st', 'S', 'RED', NOW()),
    ('Main & 1st', 'E', 'YELLOW', NOW()),
    ('Main & 1st', 'W', 'RED', NOW());

INSERT INTO route (name, from_intersection, to_intersection, status) VALUES
    ('Downtown Loop', 'Main & 1st', 'Central Square', 'OPEN'),
    ('Airport Express', 'Central Square', 'Airport', 'CONGESTED');
