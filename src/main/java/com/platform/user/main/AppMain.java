package com.platform.user.main;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
	static Logger log = Logger.getLogger(AppMain.class);
	public static void main(String[] args) {
		String[] xmls = new String[] { "classpath:spring-context.xml" };
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(xmls);
		context.start();
		log.info("启动完成");
	}
}