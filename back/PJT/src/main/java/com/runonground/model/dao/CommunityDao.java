package com.runonground.model.dao;

<<<<<<< HEAD
import java.util.List;

import com.runonground.model.dto.CommunityComment;
import com.runonground.model.dto.CommunityPost;
import com.runonground.model.dto.FutsalRecruitPost;
import com.runonground.model.dto.SearchCondition;

public interface CommunityDao {
	// 수다글 전체 조회(검색을 포함한...)
	public List<CommunityPost> selectAllCommunityPost(String teamName, SearchCondition searchCondition);

	// 수다글 하나 들고오기
	public CommunityPost selectOneCommunityPost(String teamName, int postId);

	// 수다글 등록하기
	public int insertCommunityPost(String teamName, CommunityPost communityPost);

	// 수다글 삭제
	public int deleteCommunityPost(String teamName, int postId);

	// 수다글 수정
	public int updateCommunityPost(String teamName, CommunityPost communityPost);

	// 수다글에 조회수 증가
	public void updateCommunityPostViewCnt(String teamName, int postId);
	
	// 수다글의 댓글 전체 불러오기
	public List<CommunityComment> selectAllCommunityComment(String teamName, int postId);
	
	// 수다글의 댓글 작성하기
	public int insertCommunityComment(String teamName, int postId, CommunityComment communityComment);
	
	// 수다글 댓글 수정하기
	public int updateCommunityComment(String teamName, int postId, CommunityComment communityComment);
	
	// 수다글 댓글 삭제하기
	public int deleteCommunityComment(String teamName, int postId, int commentId);
	
	// 수다글 댓글의 추천수 증가 (애는 잘 모르겠음)
	public void updateCommunityCommentRecommand(String teamName, int postId, int commentId);
	
	//------------------------------------------여기까지가 수다글 기능입니다.--------------------------------
	
	// 모집 글 전체 불러오기 (여기도 조건을 붙인..)
	public List<FutsalRecruitPost> selectAllFutsalRecruitPost(String teamName, SearchCondition searchCondition);

	// 모집글 하나만 불러오기
	public FutsalRecruitPost selectOneFutsalRecruitPost(String teamName, int recruitmentId);

	// 모집글 등록하기
	public int insertFutsalRecruitPost(String teamName, FutsalRecruitPost futsalRecruitPost);

	// 모집 글 삭제하기
	public int deleteFutsalRecruitPost(String teamName, int recruitmentId);

	// 모집 글 수정하기
	public int updateFutsalRecruitPost(String teamName, FutsalRecruitPost futsalRecruitPost);

	// 모집글에 조회수 증가
	public void updateFutsalRecruitPostViewCnt(String teamName, int recruitmentId);
=======
public interface CommunityDao {
>>>>>>> 5e0d267082c08ca716f95682d6d232c6b20a8dae

}
