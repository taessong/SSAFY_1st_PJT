package com.runonground.model.service;

import java.util.List;
import java.util.Map;

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
	
}

