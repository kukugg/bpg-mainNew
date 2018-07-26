package com.bpg.lr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Title:Application
 * @Description: 启动主类	
 * @Author hjp	
 * @Date 2018年5月29日
 */

@SpringBootApplication(scanBasePackages = "com.bpg")
@EnableTransactionManagement  //事务管理
@MapperScan("com.bpg.lr.*.dao") //扫描dao层
public class Application {
	
	/**
	 * @Title: main   
	 * @Description:  启动入口 
	 * @param @param args   
	 * @return void    
	 * @throws   
	 * @Author hjp
	 * @Date 2018年5月29日
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
