package com.runonground.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runonground.model.service.RecommendationService;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

	private final RecommendationService recommendationService;

	@Autowired
	public RecommendationController(RecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}

	//추천 기능... 저장하고 나중에 중복 방지를 위함
	@PostMapping
    public ResponseEntity<?> recommend(@RequestBody Map<String, Object> payload) {
        String userNickName = (String) payload.get("userNickName");
        
        // Convert postId and commentId from String to Integer
        int postId;
        int commentId;
        try {
            postId = Integer.parseInt((String) payload.get("postId"));
            commentId = Integer.parseInt((String) payload.get("commentId"));
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid postId or commentId");
        }

        if (recommendationService.addRecommendation(userNickName, postId, commentId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User has already recommended this comment");
        }
    }
}
