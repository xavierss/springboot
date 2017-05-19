package com.example.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.service.UserService;
import com.example.user.service.model.UserVO;


@RestController
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService") private UserService userService;
	
	@RequestMapping(value="/selectUserList")
	public List<UserVO> home() throws Exception {
		UserVO userVO = new UserVO();
		userVO.setId("xavierss");
		
		logger.debug("user ==> {}", userVO.getId());
		
		return userService.selectUserList(userVO);
	}
}
