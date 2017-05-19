package com.example.user.service.impl;

import com.example.user.service.model.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("userMapper")
public interface UserMapper {
	List<UserVO> selectUserList(UserVO userVO);
}
