CREATE SCHEMA soccer; 
use soccer;

-- 팀 테이블
CREATE TABLE team (
    team_name VARCHAR(30) PRIMARY KEY NOT NULL
);

-- 사용자 테이블
CREATE TABLE user (
	nickname VARCHAR(20) PRIMARY KEY,
    user_id VARCHAR(20) UNIQUE,
    password VARCHAR(20) NOT NULL,
    realname VARCHAR(20) NOT NULL,
    team_name VARCHAR(30),
    FOREIGN KEY (team_name) REFERENCES team(team_name),
    user_imgpath VARCHAR(255) NOT NULL
);

-- 커뮤니티 테이블
CREATE TABLE community (
    community_id INT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(30),
    FOREIGN KEY (team_name) REFERENCES team(team_name)
);

-- 커뮤니티 글 테이블
CREATE TABLE community_post (
    post_id INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(20),
    team_name VARCHAR(30),
    content TEXT,
    view_cnt INT,
    post_imgpath VARCHAR(255),
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_name) REFERENCES user(nickname) ON DELETE CASCADE,
    FOREIGN KEY (team_name) REFERENCES team(team_name)
);

-- 커뮤니티 답글 테이블
CREATE TABLE community_comment (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    post_id INT,
    author_name VARCHAR(20),
    content TEXT,
    recommend INT,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES community_post(post_id) ON DELETE CASCADE,
    FOREIGN KEY (author_name) REFERENCES user(nickname) ON DELETE CASCADE
);

-- 풋살 팀 모집 글 테이블
CREATE TABLE futsal_recruit_post (
    recruitment_id INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(20),
    team_name VARCHAR(30),
    content TEXT,
    max_members INT DEFAULT 6,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_full BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (author_name) REFERENCES user(nickname) ON DELETE CASCADE,
    FOREIGN KEY (team_name) REFERENCES team(team_name)
);

-- 풋살 팀 테이블
CREATE TABLE futsal_team (
    futsal_team_id INT PRIMARY KEY AUTO_INCREMENT,
    recruitment_id INT,
    leader_name VARCHAR(20),
    FOREIGN KEY (recruitment_id) REFERENCES futsal_recruit_post(recruitment_id) ON DELETE CASCADE,
    FOREIGN KEY (leader_name) REFERENCES user(nickname) ON DELETE CASCADE
);

-- 풋살 팀 멤버 테이블
CREATE TABLE futsal_team_member (
    futsal_team_id INT PRIMARY KEY AUTO_INCREMENT,
    member_name VARCHAR(20),
    FOREIGN KEY (futsal_team_id) REFERENCES futsal_team(futsal_team_id),
    FOREIGN KEY (member_name) REFERENCES user(nickname) ON DELETE CASCADE
);

-- 경기장 테이블
CREATE TABLE stadium (
    stadium_id INT PRIMARY KEY AUTO_INCREMENT,
    stadium_name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL
);

-- 풋살 매칭 테이블
CREATE TABLE futsal_match (
    match_id INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(20),
    team_a_id INT,
    team_b_id INT,
    match_date DATE,
    match_time TIME,
    stadium_id INT,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_name) REFERENCES user(nickname) ON DELETE CASCADE,
    FOREIGN KEY (team_a_id) REFERENCES futsal_team(futsal_team_id) ON DELETE CASCADE,
    FOREIGN KEY (team_b_id) REFERENCES futsal_team(futsal_team_id),
    FOREIGN KEY (stadium_id) REFERENCES stadium(stadium_id) ON DELETE CASCADE
);

