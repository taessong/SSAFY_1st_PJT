package com.runonground.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runonground.model.service.CommunityService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/community")
@Tag(name = "Community관련 기능입니다.", description = "커뮤니티, 구인, 댓글 관리")
public class CommunityRestController {
	private static final String SUCCESS = "sueccess";
	private static final String FAIL = "fail";
	
	private final CommunityService communityService;
	
	@Autowired
	public CommunityRestController(CommunityService communityService) {
		this.communityService = communityService;
	}
}
