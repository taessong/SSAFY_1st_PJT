CREATE SCHEMA soccer; 
use soccer;

-- 팀 테이블
CREATE TABLE Teams (
    team_id INT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(50) NOT NULL
);

-- 사용자 테이블
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    team_id INT,
    FOREIGN KEY (team_id) REFERENCES Teams(team_id)
);

-- 커뮤니티 테이블
CREATE TABLE Communities (
    community_id INT PRIMARY KEY AUTO_INCREMENT,
    team_id INT,
    FOREIGN KEY (team_id) REFERENCES Teams(team_id)
);

-- 커뮤니티 글 테이블
CREATE TABLE CommunityPosts (
    post_id INT PRIMARY KEY AUTO_INCREMENT,
    author_id INT,
    team_id INT,
    content TEXT,
    image_url VARCHAR(255),
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES Users(user_id),
    FOREIGN KEY (team_id) REFERENCES Teams(team_id)
);

-- 커뮤니티 답글 테이블
CREATE TABLE CommunityComments (
    comment_id INT PRIMARY KEY AUTO_INCREMENT,
    post_id INT,
    author_id INT,
    content TEXT,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES CommunityPosts(post_id),
    FOREIGN KEY (author_id) REFERENCES Users(user_id)
);

-- 풋살 팀 모집 글 테이블
CREATE TABLE FutsalRecruitPosts (
    recruitment_id INT PRIMARY KEY AUTO_INCREMENT,
    author_id INT,
    team_id INT,
    content TEXT,
    max_members INT,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_full BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (author_id) REFERENCES Users(user_id),
    FOREIGN KEY (team_id) REFERENCES Teams(team_id)
);

-- 풋살 팀 테이블
CREATE TABLE FutsalTeams (
    futsal_team_id INT PRIMARY KEY AUTO_INCREMENT,
    recruitment_id INT,
    leader_id INT,
    FOREIGN KEY (recruitment_id) REFERENCES FutsalRecruitPosts(recruitment_id),
    FOREIGN KEY (leader_id) REFERENCES Users(user_id)
);

-- 풋살 팀 멤버 테이블
CREATE TABLE FutsalTeamMembers (
    futsal_team_id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT,
    FOREIGN KEY (futsal_team_id) REFERENCES FutsalTeams(futsal_team_id),
    FOREIGN KEY (member_id) REFERENCES Users(user_id)
);

-- 경기장 테이블
CREATE TABLE Stadiums (
    stadium_id INT PRIMARY KEY AUTO_INCREMENT,
    stadium_name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL
);

-- 풋살 매칭 테이블
CREATE TABLE FutsalMatches (
    match_id INT PRIMARY KEY AUTO_INCREMENT,
    author_id INT,
    team_a_id INT,
    team_b_id INT,
    match_date DATE,
    match_time TIME,
    stadium_id INT,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES Users(user_id),
    FOREIGN KEY (team_a_id) REFERENCES FutsalTeams(futsal_team_id),
    FOREIGN KEY (team_b_id) REFERENCES FutsalTeams(futsal_team_id),
    FOREIGN KEY (stadium_id) REFERENCES Stadiums(stadium_id)
);

