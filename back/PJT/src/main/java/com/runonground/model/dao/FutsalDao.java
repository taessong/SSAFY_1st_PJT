package com.runonground.model.dao;

import java.util.List;
import java.util.Map;

import com.runonground.model.dto.FutsalMatch;
import com.runonground.model.dto.FutsalRecruitPost;
import com.runonground.model.dto.FutsalTeam;
import com.runonground.model.dto.FutsalTeamMember;

public interface FutsalDao {

	// 매칭 등록
	public void insertPost(FutsalMatch futsalMatch);

	// 매칭글 전체 보기
	public List<FutsalMatch> selectAll();
	
	// 매칭 등록 삭제하기 
	public void deleteMatch(int id);
	
	// B팀이 매칭 신청하기
	public void registTeamB(Map<String, Object> map);
	
	// B팀의 매칭 취소
	public void cancelMatch(int id);
	
	// 매칭글 상세 보기
	public FutsalMatch readMatch(int id);
	
	// 풋살 인원 모집글 등록하기
	public void findMember(FutsalRecruitPost futsalRecruitPost);
	
	// 팀 생성
	public void insertTeam(String leader, String teamName);
	
	// 팀 조회
	public List<FutsalTeam> selectAllTeam(String teamName);
	
	// 팀원으로 등록
	public void insertMember(FutsalTeamMember futsalTeamMember);
	
	// 팀에 맞는 팀원 조회
	public List<FutsalTeamMember> selectAllMember(int teamId);
	
	// 풋살 모집 글 전체 조회
	public List<FutsalRecruitPost> selectAllRecruit(String teamName);
	
	// 모집 글 하나 불러오기
	public FutsalRecruitPost selectOneRecruit(int id);
	
	// 모집 글 삭제하기
	public void deleteRecruit(int id);
	
	// 모집 글 수정하기
	public void updateRecruit(FutsalRecruitPost futsalRecruitPost);
}
