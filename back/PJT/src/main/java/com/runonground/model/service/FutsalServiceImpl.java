package com.runonground.model.service;

import org.springframework.stereotype.Service;

import com.runonground.model.dao.FutsalDao;
import com.runonground.model.dto.FutsalMatch;

@Service
public class FutsalServiceImpl implements FutsalService{

	private FutsalDao futsalDao;
	
	public FutsalServiceImpl(FutsalDao futsalDao) {
		this.futsalDao = futsalDao;
	}
	
	// 매칭 등록
	@Override
	public void regist(FutsalMatch futsalMatch) {
		futsalDao.insertPost(futsalMatch);
	}

	// 매칭글 상세 보기
	@Override
	public FutsalMatch readMatch(int id) {
		return futsalDao.readMatch(id);
	}

}
