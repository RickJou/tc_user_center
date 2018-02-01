package com.platform.user.test;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.platform.user.services.UserBasicInfoService;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "classpath:/spring-context.xml" })
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestMasterAndSlaveRead {

	@Autowired
	private UserBasicInfoService userBasicInfoService;

	@Test
	public void readRecordForMaster() {
		Map user = new HashedMap<>();
		user.put("user_id", 1);
		userBasicInfoService.findUserForMastre(user);
		//查看mycat debug日子,看该查询是否路由到了master节点
	}
	@Test
	public void readRecordForSalve() {
		Map user = new HashedMap<>();
		user.put("user_id", 2);
		userBasicInfoService.findUserForSlave(user);
		//查看mycat debug日子,看该查询是否路由到了slave节点
	}

}
