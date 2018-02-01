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
public class TestPartitionRead {
	private static Logger log = Logger.getLogger(TestUserCURD.class);

	@Autowired
	private UserBasicInfoService userBasicInfoService;

	@Test
	public void readRecordForPartition1() {
		Map user = new HashedMap<>();
		user.put("user_name", "alan3");
		user = userBasicInfoService.findUserForPartition1(user);
		log.info("查询到的用户信息为:" + JSON.toJSONString(user));
		// 查看mycat debug日子,看该查询是否路由到了partition1节点
		//2018-01-31 19:17:39.098 DEBUG [$_NIOREACTOR-0-RW] (io.mycat.server.NonBlockingSession.execute(NonBlockingSession.java:126)) - ServerConnection [id=738, schema=user_center, host=192.168.188.1, user=root,txIsolation=3, autocommit=true, schema=user_center]select `user_id`, `user_name`, `user_age`, `user_address` from user_basic_info where user_name = 'alan3', route={
		//   1 -> dn_wr1{select `user_id`, `user_name`, `user_age`, `user_address` from user_basic_info where user_name = 'alan3'}
		//} rrs 
	}

	@Test
	public void readRecordForPartition2() {
		Map user = new HashedMap<>();
		user.put("user_name", "alan87");
		user = userBasicInfoService.findUserForPartition2(user);
		log.info("查询到的用户信息为:" + JSON.toJSONString(user));
		// 查看mycat debug日子,看该查询是否路由到了partition2节点
		//2018-01-31 19:17:39.159 DEBUG [$_NIOREACTOR-0-RW] (io.mycat.server.NonBlockingSession.execute(NonBlockingSession.java:126)) - ServerConnection [id=738, schema=user_center, host=192.168.188.1, user=root,txIsolation=3, autocommit=true, schema=user_center]select `user_id`, `user_name`, `user_age`, `user_address` from user_basic_info where user_name = 'alan87', route={
		//   1 -> dn_wr2{select `user_id`, `user_name`, `user_age`, `user_address` from user_basic_info where user_name = 'alan87'}
		//} rrs 
	}

}
