-- 建表语句
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100),
    age INT,
    active BOOLEAN DEFAULT TRUE,
    created_at DATETIME,
    updated_at DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入示例数据
INSERT INTO users (username, email, full_name, age, active, created_at, updated_at) VALUES
('john_doe', 'john@example.com', 'John Doe', 30, true, NOW(), NOW()),
('jane_smith', 'jane@example.com', 'Jane Smith', 25, true, NOW(), NOW()),
('bob_wilson', 'bob@example.com', 'Bob Wilson', 35, false, NOW(), NOW());