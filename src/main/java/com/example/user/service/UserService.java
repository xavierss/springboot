package com.example.user.service;

import java.util.List;

import com.example.user.service.model.UserVO;

public interface UserService {
	public List<UserVO> selectUserList(UserVO userVO) throws Exception;
}
