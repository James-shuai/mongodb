package com.cheguansuo.business.controller;


import com.cheguansuo.business.service.MongoDBService;
import com.cheguansuo.business.service.ToMongodbService;
import com.cheguansuo.util.Data;
import com.cheguansuo.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/open")
public class MongoDBController {

	@Autowired
	private MongoDBService mongoDBService;
	@Autowired
	private ToMongodbService toMongodbService;
	
	public static Logger logger = LoggerFactory.getLogger("mongodbLog");
	
	/**
	 * 开始
	 * @param startlsh
	 * @param endlsh
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/openProgom.do")
	public Object monitoredList(String startlsh, String endlsh){
		Data data = new Data();
		String time = DateUtil.toDateTimeStr19(new Date());
		logger.info("time=" + time + "，startlsh= "+ startlsh +",endlsh="+endlsh);
		try {
			mongoDBService.start(startlsh, endlsh,time);
			data.put("code", 1).put("msg", "操作成功");
			logger.info("time=" + time + "，开始成功");
		} catch (Exception e) {
			e.printStackTrace();
			data.put("code", 0).put("msg", "操作异常");
			logger.info("time=" + time + "，开始异常，信息==" + e.getMessage());
		}
		return data;
	}


	
	/**
	 * 关闭
	 * @param date
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/stopProgom.do")
	public Object monitoredList(String time){
		Data data = new Data();
		try {
			mongoDBService.shutdownExecutor(time);
			data.put("code", 1).put("msg", "操作成功");
			logger.info("time=" + time + "，关闭成功");
		} catch (Exception e) {
			e.printStackTrace();
			data.put("code", 0).put("msg", "操作异常");
			logger.info("time=" + time + "，关闭异常，信息==" + e.getMessage());
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/list.do")
	public Object MongodbStartList(){
		return mongoDBService.taskList();
	}
}