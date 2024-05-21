package com.runonground.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runonground.model.dao.RecommendationDao;

@Service
public class RecommendationServiceImpl implements RecommendationService{
	
	private final RecommendationDao recommendationDao;
	
	@Autowired
	public RecommendationServiceImpl(RecommendationDao recommendationDao) {
		this.recommendationDao = recommendationDao;
	}
	
	@Override
	public boolean addRecommendation(String userNickName, int postId, int commentId) {
		Map<String, Object> params = new HashMap<>();
		params.put("userNickName", userNickName);
		params.put("postId", postId);
		params.put("commentId", commentId);
		
		//만약에 추천을 이미 했다면!
		if(!recommendationDao.hasUserRecommended(params)) {
			recommendationDao.addRecommendation(params);
			return true;
		}
		else {
			return false;
		}
	}

}
