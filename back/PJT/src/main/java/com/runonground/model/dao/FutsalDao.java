package com.runonground.model.dao;

import com.runonground.model.dto.FutsalMatch;

public interface FutsalDao {

	// 매칭 등록
	public void insertPost(FutsalMatch futsalMatch);

	// 매칭글 상세 보기
	public FutsalMatch readMatch(int id);
}
