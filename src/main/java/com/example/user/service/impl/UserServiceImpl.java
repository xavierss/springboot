package com.example.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.user.service.UserService;
import com.example.user.service.model.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userMapper") UserMapper mapper;
	
	@Override
	public List<UserVO> selectUserList(UserVO userVO) throws Exception {
		return mapper.selectUserList(userVO);
	}
}
