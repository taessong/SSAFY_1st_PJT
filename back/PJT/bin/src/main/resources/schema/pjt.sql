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
    user_imgpath VARCHAR(255) DEFAULT NULL
);

-- 사용자 데이터 삽입
INSERT INTO user (nickname, user_id, password, realname, team_name, user_imgpath) VALUES
('태성', 'kts', '1234', '김태성', '토트넘', NULL),
('도라에몽', 'user1', '1234', 'user1', '토트넘', NULL),
('노진구', 'user2', '1234', 'user2', '토트넘', NULL),
('신이슬', 'user3', '1234', 'user3', '토트넘', NULL),
('왕비실', 'user4', '1234', 'user4', '토트넘', NULL),
('민퉁퉁', 'user5', '1234', 'user5', '토트넘', NULL),

('창호', 'pch', '1234', '박창호', '첼시', NULL),
('용주', 'user11', '1234', '황용주', '첼시', NULL),
('소연', 'user12', '1234', '이소연', '첼시', NULL),
('선옥', 'user13', '1234', '김선옥', '첼시', NULL),
('선하', 'user14', '1234', '김선하', '첼시', NULL),
('성현', 'user15', '1234', '김성현', '첼시', NULL),

('범수', 'mbs', '1234', '문범수', '맨유', NULL),
('맹구', 'user21', '1234', 'user21', '맨유', NULL),
('짱구', 'user22', '1234', 'user22', '맨유', NULL),
('철수', 'user23', '1234', 'user23', '맨유', NULL),
('유리', 'user24', '1234', 'user24', '맨유', NULL),
('훈이', 'user25', '1234', 'user25', '맨유', NULL),

('예본', 'pyb', '1234', '박예본', '아스널', NULL),
('예현', 'user31', '1234', '김예현', '아스널', NULL),
('진현', 'user32', '1234', '김진현', '아스널', NULL),
('태한', 'user33', '1234', '김태한', '아스널', NULL),
('근범', 'user34', '1234', '서근범', '아스널', NULL),
('성윤', 'user35', '1234', '오성윤', '아스널', NULL),  
    
('승탁', 'cst', '1234', '최승탁', '리버풀', NULL),
('승준', 'user41', '1234', '오승준', '리버풀', NULL),
('혜지', 'user42', '1234', '우혜지', '리버풀', NULL),
('수현', 'user43', '1234', '이수현', '리버풀', NULL),
('정현', 'user44', '1234', '이정현', '리버풀', NULL),
('지혜', 'user45', '1234', '이지혜', '리버풀', NULL),

('원빈', 'user51', '1234', '정원빈', '맨시티', NULL),
('현지', 'user52', '1234', '진현지', '맨시티', NULL),
('준혁', 'user53', '1234', '최준혁', '맨시티', NULL),
('정수', 'user54', '1234', '하정수', '맨시티', NULL),
('선주', 'user55', '1234', '홍선주', '맨시티', NULL);


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

INSERT INTO community_post (author_name, team_name, title, content, view_cnt, reg_date)
VALUES('태성', '토트넘', '아니근데진짜', '반더벤 개잘해', '377', '2024-05-21 14:27:00'),
		('노진구', '토트넘', '도라에몽', '손 왤캐 부었음?', '142', '2024-05-21 17:34:00'),
        ('민퉁퉁', '토트넘', '이따 저녁 8시에', '노래 부르게 공원으로', '23', '2024-05-21 18:56:00'),
        ('왕비실', '토트넘', '아아', '더미 데이터', '45', '2024-05-21 21:33:00'),
        ('신이슬', '토트넘', '누르고 싶지?', '더미 데이터', '265', '2024-05-22 01:13:00'),
        
        ('창호', '첼시', '다들', '1학기 고생 많으셨습니다', '4', '2024-05-23 04:04:44'),
        ('용주', '첼시', '1학기', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('선옥', '첼시', '고생', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('선하', '첼시', '많았어요', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('성현', '첼시', '2학기도', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('소연', '첼시', '화이팅', '더미 데이터', '4', '2024-05-23 04:04:44'),
        
        ('범수', '맨유', '다들', '1학기 고생 많으셨습니다', '4', '2024-05-23 04:04:44'),
        ('맹구', '맨유', '1학기', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('짱구', '맨유', '고생', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('철수', '맨유', '많았어요', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('유리', '맨유', '2학기도', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('훈이', '맨유', '화이팅', '더미 데이터', '4', '2024-05-23 04:04:44'),
        
        ('예본', '아스널', '다들', '1학기 고생 많으셨습니다', '4', '2024-05-23 04:04:44'),
        ('예현', '아스널', '1학기', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('진현', '아스널', '고생', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('태한', '아스널', '많았어요', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('근범', '아스널', '2학기도', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('성윤', '아스널', '화이팅', '더미 데이터', '4', '2024-05-23 04:04:44'),
        
        ('승탁', '리버풀', '다들', '1학기 고생 많으셨습니다', '4', '2024-05-23 04:04:44'),
        ('승준', '리버풀', '1학기', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('혜지', '리버풀', '고생', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('수현', '리버풀', '많았어요', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('정현', '리버풀', '2학기도', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('지혜', '리버풀', '화이팅', '더미 데이터', '4', '2024-05-23 04:04:44'),
        
        ('원빈', '맨시티', '다들', '1학기 고생 많으셨습니다', '4', '2024-05-23 04:04:44'),
        ('현지', '맨시티', '1학기', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('준혁', '맨시티', '고생', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('정수', '맨시티', '많았어요', '더미 데이터', '4', '2024-05-23 04:04:44'),
        ('선주', '맨시티', '2학기도', '더미 데이터', '4', '2024-05-23 04:04:44');

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

INSERT INTO community_comment (post_id, author_name, content, recommend, reg_date)
VALUES (1, '도라에몽', '그니깐 ㅋㅋㅋㅋㅋㅋ 미쳤음', '33', '2024-05-21 15:02:00'),
		(1, '노진구', '아니근데진짜~ 너 예쁘다니까~', '43', '2024-05-21 16:27:00'),
        (2, '도라에몽', '진구야', '82', '2024-05-21 18:27:00');

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

INSERT INTO futsal_recruit_post (author_name, team_name, content, reg_date) VALUES
('태성', '토트넘', '더워지기 전에 함 안 하나?.? 대전 4반?.?', '2024-05-22 16:53:00'),
('민퉁퉁', '토트넘', '내일 오전 10시 집합', '2024-05-21 14:11:00'),
('범수', '맨유', '더워지기 전에 함 안 하나?.? 대전 4반?.?', '2024-05-22 16:53:00'),
('창호', '첼시', '더워지기 전에 함 안 하나?.? 대전 4반?.?', '2024-05-22 16:53:00'),
('승탁', '리버풀', '더워지기 전에 함 안 하나?.? 대전 4반?.?', '2024-05-22 16:53:00');

SELECT * FROM futsal_recruit_post;

-- 풋살 팀 테이블
CREATE TABLE futsal_team (
    team_id INT PRIMARY KEY AUTO_INCREMENT,
    leader_name VARCHAR(20),
    team_name VARCHAR(30),
    FOREIGN KEY (leader_name) REFERENCES user(nickname) ON DELETE CASCADE,
    FOREIGN KEY (team_name) REFERENCES team(team_name)
);

INSERT INTO futsal_team (leader_name, team_name) VALUES 
('태성', '토트넘'),
('민퉁퉁', '토트넘'),
('범수', '맨유'),
('창호', '첼시'),
('승탁', '리버풀');

SELECT * FROM futsal_team;

-- 풋살 팀 멤버 테이블
CREATE TABLE futsal_team_member (
    id INT PRIMARY KEY AUTO_INCREMENT,
    futsal_team_id INT,
    member_name VARCHAR(20),
    FOREIGN KEY (futsal_team_id) REFERENCES futsal_team(team_id) ON DELETE CASCADE,
    FOREIGN KEY (member_name) REFERENCES user(nickname) ON DELETE CASCADE
);

INSERT INTO futsal_team_member(futsal_team_id, member_name) VALUES 
(1, '노진구'), (1, '도라에몽'), (1, '신이슬'), (1, '왕비실'), (1, '민퉁퉁'),
(3, '맹구'), (3, '짱구'), (3, '철수'), (3, '유리'), (3, '훈이'),
(4, '소연'), (4, '용주'), (4, '선하'), (4, '선옥'), (4, '성현'),
(5, '승준'), (5, '혜지'), (5, '수현'), (5, '정현'), (5, '지혜');

-- 풋살 팀 멤버 테이블 조회
SELECT * FROM futsal_team_member;

-- 경기장 테이블
CREATE TABLE stadium (
    stadium_id INT PRIMARY KEY AUTO_INCREMENT,
    stadium_name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL
);

INSERT INTO stadium (stadium_name, location) VALUES 
('유성풋살구장', '대전 유성구 유성대로713번길 83'),
('대전가장풋살존', '대전 서구 괴정로198번길 43 21-3, 21-9'),
('남선공원풋살장', '대전 서구 남선로 66'),
('가오풋살장', '대전 동구 가오동 7-9');

SELECT * FROM stadium;

-- 풋살 매칭 테이블
CREATE TABLE futsal_match (
    match_id INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(20),
    team_a_id INT,
    team_b_id INT,
    team_a_name VARCHAR(30),
    team_b_name VARCHAR(30),
    team_b_leader VARCHAR(20),
    content VARCHAR(1000),
    match_date DATE,
    match_time TIME,
    stadium_id INT,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_name) REFERENCES user(nickname) ON DELETE CASCADE,
    FOREIGN KEY (team_a_id) REFERENCES futsal_team(team_id) ON DELETE CASCADE,
    FOREIGN KEY (stadium_id) REFERENCES stadium(stadium_id) ON DELETE CASCADE
);

INSERT INTO futsal_match(author_name, team_a_id, team_a_name, content, match_date, match_time, stadium_id)
VALUES
    ('태성', 1, '토트넘', '대전 4반 이대로 끝인가여??', '2024-06-01', '10:00:00', 1),
    ('범수', 3, '맨유', '맹구거리지 마라', '2024-06-02', '09:00:00', 2),
    ('창호', 4, '첼시', '롯데는 해체해라', '2024-06-03', '10:00:00', 3),
    ('승탁', 5, '리버풀', '이번에는 다르다', '2024-06-04', '10:00:00', 4);

SELECT * FROM futsal_match;

CREATE TABLE recommendations (
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_nickname VARCHAR(20),
    post_id INT,
    comment_id INT,
    FOREIGN KEY (user_nickname) REFERENCES user(nickname),
    FOREIGN KEY (post_id) REFERENCES community_post(post_id) ON DELETE CASCADE,
    FOREIGN KEY (comment_id) REFERENCES community_comment(comment_id) ON DELETE CASCADE
);
