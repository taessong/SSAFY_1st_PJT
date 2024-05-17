package com.runonground.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.runonground.model.dao.FutsalDao;
import com.runonground.model.dto.FutsalMatch;
import com.runonground.model.dto.FutsalRecruitPost;

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
