CREATE SCHEMA soccer; 
use soccer;

-- 팀 테이블
CREATE TABLE team (
    team_name VARCHAR(30) PRIMARY KEY NOT NULL
);

INSERT INTO team VALUE("토트넘");
INSERT INTO team VALUE("리버풀");
INSERT INTO team VALUE("맨시티");
INSERT INTO team VALUE("첼시");
INSERT INTO team VALUE("맨유");
INSERT INTO team VALUE("아스널");

SELECT * FROM team;

-- 사용자 테이블
CREATE TABLE user (
	nickname VARCHAR(20) PRIMARY KEY,
    user_id VARCHAR(20) UNIQUE,
    password VARCHAR(20) NOT NULL,
    realname VARCHAR(20),
    team_name VARCHAR(30),
    FOREIGN KEY (team_name) REFERENCES team(team_name),
    user_imgpath VARCHAR(255)
);

INSERT INTO user VALUES ('태쏭', 'kts', 1234, '김태성', '토트넘', '');
INSERT INTO user VALUES ('범슈', 'mbs', 1234, '문범수', '맨유', '');

SELECT * FROM user;

-- 커뮤니티 테이블
CREATE TABLE community (
    community_id INT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(30),
    FOREIGN KEY (team_name) REFERENCES team(team_name)
);

INSERT INTO community (team_name) VALUE("토트넘");
INSERT INTO community (team_name) VALUE("리버풀");
INSERT INTO community (team_name) VALUE("맨시티");
INSERT INTO community (team_name) VALUE("첼시");
INSERT INTO community (team_name) VALUE("맨유");
INSERT INTO community (team_name) VALUE("아스널");

-- 커뮤니티 글 테이블
CREATE TABLE community_post (
    post_id INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(20),
    team_name VARCHAR(30),
    title VARCHAR(30),
    content TEXT,
    view_cnt INT DEFAULT 0,
    post_imgpath VARCHAR(255),
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_name) REFERENCES user(nickname) ON DELETE CASCADE,
    FOREIGN KEY (team_name) REFERENCES team(team_name)
);

SELECT * FROM community_post;

-- 커뮤니티 답글 테이블
CREATE TABLE community_comment (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    post_id INT,
    author_name VARCHAR(20),
    content TEXT,
    recommend INT DEFAULT 0,
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

SELECT * FROM futsal_recruit_post;

-- 풋살 팀 테이블
CREATE TABLE futsal_team (
    futsal_team_id INT PRIMARY KEY AUTO_INCREMENT,
    recruitment_id INT,
    leader_name VARCHAR(20),
    FOREIGN KEY (recruitment_id) REFERENCES futsal_recruit_post(recruitment_id) ON DELETE CASCADE,
    FOREIGN KEY (leader_name) REFERENCES user(nickname) ON DELETE CASCADE
);

SELECT * FROM futsal_team;

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

INSERT INTO  stadium (stadium_name, location)
VALUES ('유성풋살구장', '대전 유성구 유성대로713번길 83');

INSERT INTO  stadium (stadium_name, location)
VALUES ('삼정풋살파크', '대전 유성구 한밭대로371번길 25-4 삼정풋살파크');

INSERT INTO  stadium (stadium_name, location)
VALUES ('대전스카이풋살파크 ', '대전 유성구 대정북로 2');

INSERT INTO  stadium (stadium_name, location)
VALUES ('위너스풋살파크 목원대점', '대전 유성구 원신흥동 576-2');

INSERT INTO  stadium (stadium_name, location)
VALUES ('경성실내풋살장&스크린골프', '대전 서구 갈마로 209 6층');

INSERT INTO  stadium (stadium_name, location)
VALUES ('대전가장풋살존 ', '대전 서구 괴정로198번길 43 21-3, 21-9');

INSERT INTO  stadium (stadium_name, location)
VALUES ('스마트풋살장 ', '대전 유성구 엑스포로 213');

INSERT INTO  stadium (stadium_name, location)
VALUES ('남선공원풋살장', '대전 서구 남선로 66');

INSERT INTO  stadium (stadium_name, location)
VALUES ('가오풋살장', '대전 동구 가오동 7-9');

INSERT INTO  stadium (stadium_name, location)
VALUES ('테크노풋살장', '대전 유성구 배울1로 297');

-- 풋살 매칭 테이블
CREATE TABLE futsal_match (
    match_id INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(20),
    team_a_id INT,
    team_b_id INT,
    content VARCHAR(1000),
    match_date DATE,
    match_time TIME,
    stadium_id INT,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_name) REFERENCES user(nickname) ON DELETE CASCADE,
    FOREIGN KEY (team_a_id) REFERENCES futsal_team(futsal_team_id) ON DELETE CASCADE,
    FOREIGN KEY (team_b_id) REFERENCES futsal_team(futsal_team_id),
    FOREIGN KEY (stadium_id) REFERENCES stadium(stadium_id) ON DELETE CASCADE
);

SELECT * FROM futsal_match;