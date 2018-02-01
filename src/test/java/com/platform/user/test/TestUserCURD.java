package com.platform.user.test;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.platform.user.services.UserBasicInfoService;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "classpath:/spring-context.xml" })
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestUserCURD {
	private static Logger log = Logger.getLogger(TestUserCURD.class);

	@Autowired
	private UserBasicInfoService userBasicInfoService;

	@Test
	public void userCRUD() {
		// 删除用户
		deleteUserBasicInfo();
		// 添加用户
		addUserBasicInfo();
		// 查询用户
		findUser();
	}

	private void addUserBasicInfo() {
		for (int i = 1; i <= 256; i++) {
			Map user = new HashedMap<>();
			user.put("user_id", i);
			user.put("user_name", "alan"+i);
			user.put("user_age", 18);
			user.put("user_address", "外滩"+i+"号");
			userBasicInfoService.addUser(user);
		}
	}

	private void deleteUserBasicInfo() {
		for (int i = 1; i <= 256; i++) {
			Map user = new HashedMap<>();
			user.put("user_id", i);
			userBasicInfoService.delUser(user);
		}
	}
	
	private void findUser(){
		Map user = new HashedMap<>();
		user.put("user_id", 1);
		user = userBasicInfoService.findUser(user);
		log.info("查询到的用户信息为:"+JSON.toJSONString(user));
	}

}
