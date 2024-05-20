CREATE SCHEMA soccer; 
USE soccer;

-- 팀 테이블
CREATE TABLE team (
    team_name VARCHAR(30) PRIMARY KEY NOT NULL
);

INSERT INTO team VALUES ("토트넘"), ("리버풀"), ("맨시티"), ("첼시"), ("맨유"), ("아스널");

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

INSERT INTO user VALUES ('태쏭', 'kts', '1234', '김태성', '토트넘', ''),
						('user1', 'user1', '1234', 'user1', '토트넘', ''),
						('user2', 'user2', '1234', 'user2', '토트넘', ''),
						('user3', 'user3', '1234', 'user3', '토트넘', ''),
						('user4', 'user4', '1234', 'user4', '토트넘', ''),
						('user5', 'user5', '1234', 'user5', '토트넘', ''),
						('user6 ', 'user6', '1234', 'user6', '토트넘', ''),
						('범슈', 'mbs', '1234', '문범수', '맨유', ''),
						('범뀨', 'kbg', '1234', '김범규', '맨시티', '');

-- 커뮤니티 테이블
CREATE TABLE community (
    community_id INT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(30),
    FOREIGN KEY (team_name) REFERENCES team(team_name)
);

INSERT INTO community (team_name) VALUES ("토트넘"), ("리버풀"), ("맨시티"), ("첼시"), ("맨유"), ("아스널");

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

INSERT INTO community_post (author_name, team_name, title, content)
VALUES('태쏭', '토트넘', 'test1', 'test입니다~~~'),
		('태쏭', '토트넘', 'test2', 'test입니다~~~'),
        ('태쏭', '토트넘', 'test3', 'test입니다~~~'),
        ('태쏭', '토트넘', 'test4', 'test입니다~~~');

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

INSERT INTO community_comment (post_id, author_name, content)
VALUES (1, 'user1', '그거 아닌데 ㅋ'),
		(1, 'user1', 'ㅋㅋㅋ'),
        (2, 'user1', '그거 아닌데 ㅋ'),
        (3, 'user1', '그거 아닌데 ㅋ');

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

INSERT INTO futsal_recruit_post (author_name, team_name, content) VALUES
('태쏭', '토트넘', '맹구 잡을사람'),
('태쏭', '토트넘', '첼시전 복수하자');

-- 풋살 팀 테이블
CREATE TABLE futsal_team (
    team_id INT PRIMARY KEY AUTO_INCREMENT,
    leader_name VARCHAR(20),
    team_name VARCHAR(30),
    FOREIGN KEY (leader_name) REFERENCES user(nickname) ON DELETE CASCADE,
    FOREIGN KEY (team_name) REFERENCES team(team_name)
);

INSERT INTO futsal_team (leader_name, team_name)
VALUES 
('태쏭', '토트넘'),
('범슈', '맨유'),
('범뀨', '맨시티');


-- 풋살 팀 멤버 테이블
CREATE TABLE futsal_team_member (
    id INT PRIMARY KEY AUTO_INCREMENT,
    futsal_team_id INT,
    member_name VARCHAR(20),
    FOREIGN KEY (futsal_team_id) REFERENCES futsal_team(team_id) ON DELETE CASCADE,
    FOREIGN KEY (member_name) REFERENCES user(nickname) ON DELETE CASCADE
);

INSERT INTO futsal_team_member(futsal_team_id, member_name) 
VALUES (1, 'user1'), (1, 'user2'), (1, 'user3'), (1, 'user4'), (1, 'user5');

-- 경기장 테이블
CREATE TABLE stadium (
    stadium_id INT PRIMARY KEY AUTO_INCREMENT,
    stadium_name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL
);

INSERT INTO stadium (stadium_name, location) VALUES 
('유성풋살구장', '대전 유성구 유성대로713번길 83'),
('삼정풋살파크', '대전 유성구 한밭대로371번길 25-4 삼정풋살파크'),
('위너스풋살파크 목원대점', '대전 유성구 원신흥동 576-2'),
('대전가장풋살존', '대전 서구 괴정로198번길 43 21-3, 21-9'),
('남선공원풋살장', '대전 서구 남선로 66'),
('가오풋살장', '대전 동구 가오동 7-9');

-- 풋살 매칭 테이블
CREATE TABLE futsal_match (
    match_id INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(20),
    team_a_id INT,
    team_b_id INT,
    team_a_name VARCHAR(30),
    team_b_name VARCHAR(30),
    content VARCHAR(1000),
    match_date DATE,
    match_time TIME,
    stadium_id INT,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    stadium_img VARCHAR(255),
    FOREIGN KEY (author_name) REFERENCES user(nickname) ON DELETE CASCADE,
    FOREIGN KEY (team_a_id) REFERENCES futsal_team(team_id) ON DELETE CASCADE,
    FOREIGN KEY (stadium_id) REFERENCES stadium(stadium_id) ON DELETE CASCADE
);

INSERT INTO futsal_match(author_name, team_a_id, team_a_name, content, match_date, match_time, stadium_id, stadium_img)
VALUES
    ('태쏭', 1, '토트넘', '토트넘이랑 붙을 사람', '2024-06-15', '14:00:00', 1, '가오풋살장/가오풋살장(1).png'),
    ('범슈', 2, '맨유', '맨유와 경기할 팀 모집', '2024-07-20', '16:30:00', 2, '남선공원풋살장/남선공원풋살장(1).png'),
    ('범뀨', 3, '맨시티', '맨시티와 대결할 팀 구합니다', '2024-08-25', '18:00:00', 3, '유성풋살구장/유성풋살구장(1).png');
