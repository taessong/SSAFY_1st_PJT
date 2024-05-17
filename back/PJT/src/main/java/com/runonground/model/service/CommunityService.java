package com.runonground.model.service;

import java.util.List;

import com.runonground.model.dto.CommunityComment;
import com.runonground.model.dto.CommunityPost;

public interface CommunityService {

	// 수다글 등록
	public void registPost(CommunityPost communityPost);

	// 수다글 전체 조회
	public List<CommunityPost> searchAllPost(String teamName);
	
	// 수다글 하나 조회
	public CommunityPost searchOnePost(int id);
	
	// 수다글 삭제
	public void deletePost(int id);
	
	// 수다글 수정
	public void updatePost(CommunityPost communityPost);
	
	// 댓글 달기
	public void addComment(CommunityComment communityComment);
	
	// 특정 게시물의 댓글 전체 조회
	public List<CommunityComment> selectAllComment(int id);
	
	// 댓글 수정
	public void updateComment(CommunityComment communityComment);
	
	// 댓글 삭제
	public void deleteComment(int commentId);
	
	// 댓글 추천
	public void recommendComment(int commentId);
	
}

