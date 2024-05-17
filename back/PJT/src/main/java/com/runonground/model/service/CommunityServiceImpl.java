package com.runonground.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runonground.model.dao.CommunityDao;
import com.runonground.model.dto.CommunityComment;
import com.runonground.model.dto.CommunityPost;
import com.runonground.model.dto.FutsalRecruitPost;
import com.runonground.model.dto.SearchCondition;

@Service
public class CommunityServiceImpl implements CommunityService{
	
	private final CommunityDao communityDao;
	
	//final로 선언했으므로 생성자 만들어주고.,,
	@Autowired
	public CommunityServiceImpl(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}

	//수다글 검색조건에 따른 전체 불러오기
	@Override
	public List<CommunityPost> readAllCommunityPost(String teamName, SearchCondition searchCondition) {
		return communityDao.selectAllCommunityPost(teamName, searchCondition);
	}

	//수다글 하나만 불러오기
	@Override
	public CommunityPost readOneCommunityPost(String teamName, int postId) {
		//조회수 증가시켜주기!
		communityDao.updateCommunityPostViewCnt(teamName, postId);
		return communityDao.selectOneCommunityPost(teamName, postId);
	}

	//수다글 작성하기
	@Transactional
	@Override
	public boolean writeCommunityPost(String teamName, CommunityPost communityPost) {
		return communityDao.insertCommunityPost(teamName, communityPost) == 1;
	}

	//수다글 삭제
	@Override
	public boolean removeCommunityPost(String teamName, int postId) {
		return communityDao.deleteCommunityPost(teamName, postId) == 1;
	}

	//수다글 수정
	@Override
	public boolean modifyCommunityPost(String teamName, CommunityPost communityPost) {
		return communityDao.updateCommunityPost(teamName, communityPost) == 1;
	}

	//수다글 댓글 작성하기
	@Override
	public boolean writeCommunityComment(String teamName, int postId, CommunityComment communityComment) {
		return communityDao.insertCommunityComment(teamName, postId, communityComment) == 1;
	}

	@Override
	public boolean modifyCommunityComment(String teamName, int postId, CommunityComment communityComment) {
		return communityDao.updateCommunityComment(teamName, postId, communityComment) == 1;
	}

	@Override
	public boolean removeCommunityComment(String teamName, int postId, int commentId) {
		return communityDao.deleteCommunityComment(teamName, postId, commentId) == 1;
	}
	
	
	//--------------------------여기까지가 수다글입니다.------------------------------------
	
	//모집글 전체보기
	@Override
	public List<FutsalRecruitPost> readAllFutsalRecruitPost(String teamName, SearchCondition searchCondition) {
		return communityDao.selectAllFutsalRecruitPost(teamName, searchCondition);
	}

	//모집글 상세보기
	@Override
	public FutsalRecruitPost readOneFutsalRecruitPost(String teamName, int recruitmentId) {
		//똑같이 조회수 증가시켜주고
		communityDao.updateFutsalRecruitPostViewCnt(teamName, recruitmentId);
		return communityDao.selectOneFutsalRecruitPost(teamName, recruitmentId);
	}
	
	//모집글 작성
	@Override
	public boolean writeFutsalRecruitPost(String teamName, FutsalRecruitPost futsalRecruitPost) {
		return communityDao.insertFutsalRecruitPost(teamName, futsalRecruitPost) == 1;
	}

	//모집글 삭제
	@Override
	public boolean removeFutsalRecruitPost(String teamName, int recruitmentId) {
		return communityDao.deleteFutsalRecruitPost(teamName, recruitmentId) == 1;
	}

	//모집글 수정
	@Override
	public boolean modifyFutsalRecruitPost(String teamName, FutsalRecruitPost futsalRecruitPost) {
		return communityDao.updateFutsalRecruitPost(teamName, futsalRecruitPost) == 1;
	}
	
}

