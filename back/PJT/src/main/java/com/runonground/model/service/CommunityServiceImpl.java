package com.runonground.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.runonground.model.dao.CommunityDao;
import com.runonground.model.dto.CommunityComment;
import com.runonground.model.dto.CommunityPost;

@Service
public class CommunityServiceImpl implements CommunityService{

	private CommunityDao communityDao;
	
	public CommunityServiceImpl(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}

	// 수다글 등록
	@Override
	public void registPost(CommunityPost communityPost) {
		communityDao.insertPost(communityPost);
	}

	// 수다글 전체 조회
	@Override
	public List<CommunityPost> searchAllPost(String teamName) {
		return communityDao.searchAllPost(teamName);
	}

	// 	수다글 하나 조회
	@Override
	public CommunityPost searchOnePost(int id) {
		communityDao.updateViewCnt(id);
		return communityDao.searchOnePost(id);
	}

	// 수다글 삭제
	@Override
	public void deletePost(int id) {
		communityDao.deletePost(id);
	}

	// 수다글 수정
	@Override
	public void updatePost(CommunityPost communityPost) {
		communityDao.updatePost(communityPost);
	}

	// 댓글 달기
	@Override
	public void addComment(CommunityComment communityComment) {
		communityDao.addComment(communityComment);
	}

	// 특정 게시물의 댓글 전체 조회
	@Override
	public List<CommunityComment> selectAllComment(int id) {
		return communityDao.selectAllComment(id);
	}

	// 댓글 수정
	@Override
	public void updateComment(CommunityComment communityComment) {
		communityDao.updateComment(communityComment);
	}

	// 댓글 삭제
	@Override
	public void deleteComment(int commentId) {
		communityDao.deleteComment(commentId);
	}

	// 댓글 추천
	@Override
	public void recommendComment(int commentId) {
		communityDao.recommendComment(commentId);
	}

}
