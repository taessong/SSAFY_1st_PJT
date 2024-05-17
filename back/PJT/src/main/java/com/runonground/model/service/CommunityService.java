package com.runonground.model.service;

import java.util.List;

import com.runonground.model.dto.CommunityComment;
import com.runonground.model.dto.CommunityPost;
import com.runonground.model.dto.FutsalRecruitPost;
import com.runonground.model.dto.SearchCondition;

public interface CommunityService {
	//수다 글 전체 불러오기(조건을 붙인)
	public List<CommunityPost> readAllCommunityPost(String teamName, SearchCondition searchCondition);
	
	//수다글 하나 불러오기
	public CommunityPost readOneCommunityPost(String teamName, int postId);
	
	//수다글 등록하기
	public boolean writeCommunityPost(String teamName, CommunityPost communityPost);
	
	//수다글 삭제
	public boolean removeCommunityPost(String teamName, int postId);
	
	//수다글 수정
	public boolean modifyCommunityPost(String teamName, CommunityPost communityPost);
	
	//수다글 댓글 작성
	public boolean writeCommunityComment(String teamName, int postId, CommunityComment communityComment);
	
	//수다글 댓글 수정하기
	public boolean modifyCommunityComment(String teamName, int postId, CommunityComment communityComment);
	
	//수다글 댓글 삭제하기
	public boolean removeCommunityComment(String teamName, int postId, int commentId);
	
	//여기까지가 수다글 기능입니다. -----------------------------------------------------------
	
	//모집 글 전체 불러오기 (여기도 조건을 붙인..)
	public List<FutsalRecruitPost> readAllFutsalRecruitPost(String teamName, SearchCondition searchCondition);
	
	//모집글 하나만 불러오기
	public FutsalRecruitPost readOneFutsalRecruitPost(String teamName, int recruitmentId);
	
	//모집글 등록하기
	public boolean writeFutsalRecruitPost(String teamName, FutsalRecruitPost futsalRecruitPost);
	
	//모집 글 삭제하기
	public boolean removeFutsalRecruitPost(String teamName, int recruitmentId);
	
	//모집 글 수정하기
	public boolean modifyFutsalRecruitPost(String teamName, FutsalRecruitPost futsalRecruitPost);
	
	
}
