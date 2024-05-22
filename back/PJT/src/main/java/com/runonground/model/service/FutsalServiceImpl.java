package com.runonground.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.runonground.model.dao.FutsalDao;
import com.runonground.model.dto.FutsalMatch;
import com.runonground.model.dto.FutsalRecruitPost;
import com.runonground.model.dto.FutsalTeam;
import com.runonground.model.dto.FutsalTeamMember;

import jakarta.servlet.http.HttpSession;

@Service
public class FutsalServiceImpl implements FutsalService{

	private FutsalDao futsalDao;
	
	public FutsalServiceImpl(FutsalDao futsalDao) {
		this.futsalDao = futsalDao;
	}
	
	// 매칭 등록
	@Override
	public void regist(FutsalMatch futsalMatch) {
		futsalDao.insertPost(futsalMatch);
	}

	// 매칭글 전체보기
	@Override
	public List<FutsalMatch> selectAll(){
		return futsalDao.selectAll();
	}
	
	// 매칭 등록 삭제하기 
	@Override
	public void deleteMatch(int id) {
		futsalDao.deleteMatch(id);
	}
	
	// 매칭글 수정하기
	@Override
	public void updateMatch(FutsalMatch futsalMatch) {
		futsalDao.updateMatch(futsalMatch);
	}

	// B팀이 매칭 신청하기
	@Override
	public void registTeamB(Map<String, Object> map) {
		futsalDao.registTeamB(map);
	}
	
	// B팀의 매칭 취소
	@Override
	public void cancelMatch(int id) {
		futsalDao.cancelMatch(id);
	}
	
	// 매칭글 상세 보기
	@Override
	public FutsalMatch readMatch(int id) {
		return futsalDao.readMatch(id);
	}

	// 풋살 인원 모집글 등록하기
	@Override
	public void findMember(FutsalRecruitPost futsalRecruitPost) {
		futsalDao.findMember(futsalRecruitPost);
	}

	// 팀 생성
	@Override
	public void generateTeam(String leader, String teamName) {
		futsalDao.insertTeam(leader, teamName);
	}
	
	// 팀 조회
	@Override
	public List<FutsalTeam> selectAllTeam(String teamName){
		return futsalDao.selectAllTeam(teamName);
	}
	
	// 팀 삭제
	@Override
	public void removeTeam(String leader) {
		futsalDao.deleteTeam(leader);
	}
	
	// 팀원으로 등록하기
	@Override
	public void registMember(FutsalTeamMember futsalTeamMember) {
		futsalDao.insertMember(futsalTeamMember);
	}
	
	// 팀에 맞는 팀원 조회
	@Override
	public List<FutsalTeamMember> selectAllMember(int teamId){
		return futsalDao.selectAllMember(teamId);
	}
	
	@Override
	public void removeTeamMember(int teamId, String memberName) {
		Map<String, Object> teamMember = new HashMap<>();
		teamMember.put("teamId", teamId);
		teamMember.put("memberName", memberName);
		futsalDao.deleteTeamMember(teamMember);
	}
	
	// 풋살 모집 글 전체 조회
	@Override
	public List<FutsalRecruitPost> selectAllRecruit(String teamName){
		return futsalDao.selectAllRecruit(teamName);
	}

	// 모집 글 하나만 불러오기
	@Override
	public FutsalRecruitPost selectOneRecruit(int id) {
		return futsalDao.selectOneRecruit(id);
	}

	// 모집 글 삭제하기
	@Override
	public void deleteRecruit(int id) {
		futsalDao.deleteRecruit(id);
	}

	// 모집 글 수정하기
	@Override
	public void updateRecruit(FutsalRecruitPost futsalRecruitPost) {
		futsalDao.updateRecruit(futsalRecruitPost);
	}
}
