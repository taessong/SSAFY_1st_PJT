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

import com.runonground.model.dto.FutsalMatch;
import com.runonground.model.dto.FutsalRecruitPost;
import com.runonground.model.dto.FutsalTeam;
import com.runonground.model.dto.FutsalTeamMember;
import com.runonground.model.service.FutsalService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/futsal")
public class FutsalRestController {

	private FutsalService futsalService;
	
	public FutsalRestController(FutsalService futsalService) {
		this.futsalService = futsalService;
	}
	
	// 매칭 등록
	// 경기장을 어떻게 선택할까.....??
	@PostMapping("/match")
	@Operation(summary = "매칭 등록")
	public ResponseEntity<Void> regist(@RequestBody FutsalMatch futsalMatch, HttpSession session){
		futsalMatch.setAuthorName((String)session.getAttribute("nickName"));
		futsalMatch.setTeamAName((String)session.getAttribute("favoriteTeam"));
		
		futsalService.regist(futsalMatch);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	// 매칭글 전체 보기
	@GetMapping("/match")
	@Operation(summary = "매칭글 전체 보기")
	public ResponseEntity<List<FutsalMatch>> selectAll(){
		List<FutsalMatch> list = futsalService.selectAll();
		return new ResponseEntity<List<FutsalMatch>>(list, HttpStatus.OK);
	}
	
	// 매칭 등록 삭제하기 
	@DeleteMapping("/match/{id}")
	@Operation(summary = "매칭 등록 삭제하기")
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		futsalService.deleteMatch(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 매칭글 수정하기
	@PutMapping("/match/{id}")
	@Operation(summary = "매칭글 수정하기")
	public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody FutsalMatch futsalMatch) {
        futsalMatch.setMatchId(id);

        System.out.println(futsalMatch.getMatchTime());
        
        futsalService.updateMatch(futsalMatch);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	// B팀이 매칭 신청하기
	@PutMapping("/match/{id}/regist")
	@Operation(summary = "B팀이 매칭 신청하기")
	public ResponseEntity<FutsalMatch> registMatch(@PathVariable("id") int id, HttpSession session){
	    // 다른 팀의 정보 입력
	    // 리더가 무조건 신청해야 함
	    String team = (String)session.getAttribute("favoriteTeam");
	    String leaderName = (String) session.getAttribute("nickName");
	    
	    Map<String, Object> map = new HashMap<>();
	    map.put("id", id);
	    map.put("team", team);
	    map.put("leaderName", leaderName);
	    
	    futsalService.registTeamB(map);
	    
	    FutsalMatch futsalMatch = futsalService.readMatch(id);
	    return new ResponseEntity<FutsalMatch>(futsalMatch, HttpStatus.CREATED);
	}

	// B팀의 매칭 신청 취소
	@DeleteMapping("/match/{id}/cancel")
	@Operation(summary = "B팀의 매칭 신청 취소")
	public ResponseEntity<Void> cancel(@PathVariable("id") int id){
		futsalService.cancelMatch(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 매칭글 상세 보기
	@GetMapping("/match/{id}")
	@Operation(summary = "매칭글 상세 보기")
	public ResponseEntity<FutsalMatch> detail(@PathVariable("id") int id){
		FutsalMatch futsalMatch = futsalService.readMatch(id);
		
		return new ResponseEntity<FutsalMatch>(futsalMatch, HttpStatus.OK);
	}
	
	// 풋살 인원 모집글 등록하기
	@PostMapping("/board")
	@Operation(summary = "풋살 인원 모집글 등록하기")
	public ResponseEntity<Void> findMember(@RequestBody FutsalRecruitPost futsalRecruitPost, HttpSession session){
		String writer = (String) session.getAttribute("nickName");
		String team = (String) session.getAttribute("favoriteTeam");
		
		futsalRecruitPost.setAuthorName(writer);
		futsalRecruitPost.setTeamName(team);
		
		futsalService.findMember(futsalRecruitPost);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	// 팀 생성
	// 프론트 단에서 팀원 모집 게시글을 작성하고
	// 등록하기 버튼을 누르면 이 메서드가 실행되게 하면 글 등록과 동시에 팀 생성 가능
	@PostMapping("/board/team")
	@Operation(summary = "팀 생성")
	 public ResponseEntity<Void> generateTeam(HttpSession session){
		String leader = (String) session.getAttribute("nickName");
		String teamName = (String) session.getAttribute("favoriteTeam");
		
		futsalService.generateTeam(leader, teamName);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	// 팀 조회
	@GetMapping("/board/team")
	@Operation(summary = "팀 조회")
	public ResponseEntity<List<FutsalTeam>> selectTeam(HttpSession session){
		String teamName = (String) session.getAttribute("favoriteTeam");
		
		List<FutsalTeam> list = futsalService.selectAllTeam(teamName);
		return new ResponseEntity<List<FutsalTeam>>(list, HttpStatus.OK);
	}
	
	// 팀 삭제
	@DeleteMapping("/board/team")
	@Operation(summary = "팀 삭제")
	public ResponseEntity<Void> removeTeam(HttpSession session){
		String leader = (String) session.getAttribute("nickName");
		
		futsalService.removeTeam(leader);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 팀원 등록
	@PostMapping("/board/team/{teamId}")
	@Operation(summary = "팀원 등록")
	public ResponseEntity<Void> createTeam(@PathVariable("teamId") int teamId, @RequestBody FutsalTeamMember futsalTeamMember, HttpSession session) {
	    futsalTeamMember.setMemberName((String)session.getAttribute("nickName"));
		futsalTeamMember.setFutsalTeamId(teamId);
	    
	    futsalService.registMember(futsalTeamMember);
	    return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	// 팀에 맞는 팀원 조회
	@GetMapping("/board/team/{teamId}")
	@Operation(summary = "팀에 맞는 팀원 조회")
	public ResponseEntity<List<FutsalTeamMember>> selectMember(@PathVariable("teamId") int teamId){
		List<FutsalTeamMember> list = futsalService.selectAllMember(teamId);
		return new ResponseEntity<List<FutsalTeamMember>>(list, HttpStatus.OK);
	}
	
	// 모집 글 전체 불러오기
	@GetMapping("/board")
	@Operation(summary = "전체 모집 글 불러오기")
	public ResponseEntity<List<FutsalRecruitPost>> selectAllRecruit(HttpSession session){
		String teamName = (String) session.getAttribute("favoriteTeam");
		
		List<FutsalRecruitPost> list = futsalService.selectAllRecruit(teamName);
		return new ResponseEntity<List<FutsalRecruitPost>>(list, HttpStatus.OK);
	}
	
	// 모집글 하나만 불러오기
	@GetMapping("/board/{id}")
	@Operation(summary = "모집 글 하나만 불러오기")
	public ResponseEntity<FutsalRecruitPost> selectOneRecruit(@PathVariable("id") int id){
		FutsalRecruitPost post = futsalService.selectOneRecruit(id);
		return new ResponseEntity<FutsalRecruitPost>(post, HttpStatus.OK);
	}
	
	// 모집글 삭제하기
	@DeleteMapping("/board/{id}")
	@Operation(summary = "모집 글 삭제하기")
	public ResponseEntity<Void> deleteRecruit(@PathVariable("id") int id){
		futsalService.deleteRecruit(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 모집 글 수정하기
	@PutMapping("/board/{id}")
	@Operation(summary = "모집 글 수정하기")
	public ResponseEntity<Void> updateRecruit(@PathVariable("id") int id, @RequestBody FutsalRecruitPost futsalRecruitPost){
		futsalRecruitPost.setRecruitmentId(id);
		
		futsalService.updateRecruit(futsalRecruitPost);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
