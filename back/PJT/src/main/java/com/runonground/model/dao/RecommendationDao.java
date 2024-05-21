package com.runonground.model.dao;

import java.util.Map;

public interface RecommendationDao {
	
	//사용자가 추천을 했는지 확인 기능입니다~
	void addRecommendation(Map<String, Object> params);
	boolean hasUserRecommended(Map<String, Object> params);
	
}
