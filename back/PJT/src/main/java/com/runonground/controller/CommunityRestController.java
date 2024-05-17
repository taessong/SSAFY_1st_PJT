package com.runonground.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runonground.model.dto.CommunityPost;
import com.runonground.model.service.CommunityService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/community")
public class CommunityRestController {

	private CommunityService communityService;
	
	public CommunityRestController(CommunityService communityService) {
		this.communityService = communityService;
	}
	
	// 수다글 등록
	@PostMapping("/board")
	@Operation(summary = "수다글 등록")
	public ResponseEntity<Void> regist(@RequestBody CommunityPost communityPost, HttpSession session){
		// 세션에 존재하는 로그인 유저의 선호 팀
		String favoriteTeam = (String) session.getAttribute("favoriteTeam");
		
		// 세션에 존재하는 로그인 유저의 별명
		String nickName = (String) session.getAttribute("nickName");
		
		// 작성한 글을 선호팀의 커뮤니티에 올리기 위해 팀 설정
		communityPost.setTeamName(favoriteTeam);
		communityPost.setAuthorName(nickName);
		
		communityService.registPost(communityPost);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// 수다글 전체 조회
	@GetMapping("/board")
	@Operation(summary = "수다글 전체 조회")
	public ResponseEntity<List<CommunityPost>> searchAllPost(HttpSession session){
		// 세선에 존재하는 로그인 유저의 선호 팀
		String favoriteTeam = (String) session.getAttribute("favoriteTeam");
				
		List<CommunityPost> list = communityService.searchAllPost(favoriteTeam);
		return new ResponseEntity<List<CommunityPost>>(list, HttpStatus.OK);
	}
	
	// 수다글 하나 조회
	@GetMapping("/board/{id}")
	@Operation(summary = "수다글 하나 조회")
	public ResponseEntity<CommunityPost> searchOnePost(@PathVariable("id") int id, HttpSession session){
		CommunityPost post = communityService.searchOnePost(id);
		return new ResponseEntity<CommunityPost>(post, HttpStatus.OK);
	}
	
	// 수다글 삭제
	@DeleteMapping("/board/{id}")
	@Operation(summary = "수다글 삭제")
	public ResponseEntity<Void> deletePost(@PathVariable("id") int id){
		// 얘는 id가 중복되지 않기에 선호팀 설정할 필요 없어 보임
		communityService.deletePost(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 수다글 수정
	@PutMapping("/board/{id}")
	@Operation(summary = "수다글 수정")
	public ResponseEntity<Void> updatePost(@PathVariable("id") int id, @RequestBody CommunityPost communityPost){
		communityPost.setPostId(id);
		
		communityService.updatePost(communityPost);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
