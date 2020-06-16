INSERT INTO credentials (username, password, role, enabled)
VALUES ('admin', 'nimda', 'REGISTERED', true) ON duplicate KEY UPDATE id = id;
