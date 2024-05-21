package com.runonground.model.service;

public interface RecommendationService {
	
	//추천 검사
	public boolean addRecommendation(String userNickName, int postId, int commentId);
	
}
