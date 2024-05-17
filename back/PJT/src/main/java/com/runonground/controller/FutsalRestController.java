package com.runonground.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runonground.model.dto.FutsalMatch;
import com.runonground.model.dto.FutsalRecruitPost;
import com.runonground.model.service.FutsalService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/futsal")
public class FutsalRestController {

	private FutsalService futsalService;
	
	public FutsalRestController(FutsalService futsalService) {
		this.futsalService = futsalService;
	}
	
	// 매칭 등록
	@PostMapping("/regist")
	@Operation(summary = "매칭 등록")
	public ResponseEntity<Void> regist(@RequestBody FutsalMatch futsalMatch){
		futsalService.regist(futsalMatch);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	// 매칭글 상세 보기
	@GetMapping("/{id}")
	@Operation(summary = "매칭글 상세 보기")
	public ResponseEntity<FutsalMatch> detail(@PathVariable("id") int id){
		FutsalMatch futsalMatch = futsalService.readMatch(id);
		
		if(futsalMatch != null)
			return new ResponseEntity<FutsalMatch>(futsalMatch, HttpStatus.OK);
		
		return new ResponseEntity<FutsalMatch>(HttpStatus.BAD_REQUEST);
	}
}
