CREATE TABLE IF NOT EXISTS user_otp (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password TEXT NOT NULL);

CREATE TABLE IF NOT EXISTS otp (
    otp_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    code VARCHAR(45) NOT NULL);

INSERT INTO user_otp (user_id, username, password) VALUES ('1', 'NIK', '$2a$10$WDI29xmQHoybP/yzsM7tPeaSQgG8vQYK9JB3JQLOIcmAvVfbRB/0G');
INSERT INTO otp (otp_id, username, code) VALUES ('1', 'NIK', '7789');