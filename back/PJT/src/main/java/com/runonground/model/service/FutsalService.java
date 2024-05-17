package com.runonground.model.service;

import com.runonground.model.dto.FutsalMatch;

public interface FutsalService {

	// 매칭 등록
	public void regist(FutsalMatch futsalMatch);
	
	// 매칭글 상세 보기
	public FutsalMatch readMatch(int id);
}
