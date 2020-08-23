package com.cheguansuo.business.service;



import com.cheguansuo.business.dao.MongodbscztDao;
import com.cheguansuo.business.dao.StoreinfoDao;
import com.cheguansuo.business.dao.VehisphotoiDao;
import com.cheguansuo.business.dao.WvehpicturemidDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class MongoDBService {
	
	@Autowired
	private WvehpicturemidDao wvehpicturemidDao;
	@Autowired
	private MongodbscztDao mongodbscztDao;
	@Autowired
	private StoreinfoDao storeinfoDao;
	@Autowired
	private VehisphotoiDao vehisphotoiDao;
	@Autowired
	public MongdbGFSService mongdbGFSService;


	public static int pageSize = 20;
	public static Logger logger = LoggerFactory.getLogger(MongoDBService.class);
	public static Map<String, Map<String, String>> taskMap = new LinkedHashMap<String, Map<String, String>>();
	public static Map<String, ThreadPoolExecutor> executorMap = new HashMap<String, ThreadPoolExecutor>();
	public static Map<String, List<String>> waitListMap = new HashMap<String, List<String>>();
	public static Map<String, Integer> currentIndexMap = new HashMap<String, Integer>();
	public static Map<String, Integer> autoStatusMap = new HashMap<String, Integer>();
	
	
	/**
	 * 创建线程池
	 * @param start
	 * @param end
	 */
	public void start(String start, String end, String time){
		//int currentIndex = -1; //执行到
		//自动转换，较小流水作为开始，较大流水作为结束
		if(start.compareTo(end)>0){
			String temp = start;
			start = end;
			end = temp;
		}
		
		List<String> mongodbBZ = wvehpicturemidDao.Mongodbsczt(start,end);
		logger.info("time="+time+",start="+start +",end="+end +",待执行数量="+mongodbBZ.size());
		if (mongodbBZ.size()==0) {
			System.out.println("----------应迁移的数量为："+mongodbBZ.size());
			return;
		}
		waitListMap.put(time, mongodbBZ);
		currentIndexMap.put(time, -1);
		autoStatusMap.put(time,0);
		
		Map<String, String> task = new HashMap<String, String>();
		task.put("start", start);
		task.put("end", end);
		task.put("total", mongodbBZ.size()+"");
		taskMap.put(time, task);
		
		//初始化线程池
		ThreadPoolExecutor executor = new ThreadPoolExecutor(pageSize, pageSize, 0, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(pageSize), new ThreadPoolExecutor.CallerRunsPolicy());
		executorMap.put(time, executor);
		
		//初次添加线程
//		int addThead = mongodbBZ.size() >= pageSize ?pageSize:mongodbBZ.size();
//		for (int i = 0; i < addThead; i++) {
//			currentIndexMap.put(time,i);
//			Map<String, Object> map = mongodbBZ.get(i);
//			String lsh =(String) map.get("LSH");
//			executor.execute(new MongoDBRunnable(lsh +"_" + currentIndexMap.get(time),wvehpicturemidDao,mongodbscztDao,storeinfoDao,vehisphotoiDao));
//		}

		for (String lsh:mongodbBZ) {
			executor.execute(new MongoDBRunnable(lsh +"_" + currentIndexMap.get(time),wvehpicturemidDao,mongodbscztDao,storeinfoDao,vehisphotoiDao,mongdbGFSService));
		}
		
		//addThread(executor,mongodbBZ,count,currentIndex);
		//shutdownExecutor(time);
	}
	
	/**
	 * 判断有空余线程，立即创建线程
	 */
	public void autoAddThread(){
		for (String time : autoStatusMap.keySet()) {
			Integer status = autoStatusMap.get(time);
			if (status!=1) {
				autoStatusMap.put(time, 1);
				addThread(time);
				shutdownExecutor(time);
			}
		}
	}
	
	/**
	 * 添加线程
	 */
	public void addThread(String time){
		ThreadPoolExecutor executor  = executorMap.get(time);
		List<String> mongodbBZ = waitListMap.get(time);
		int currentIndex = 	currentIndexMap.get(time);	
		int count = mongodbBZ.size();
		while ( executor!=null && currentIndex < (mongodbBZ.size()-1)) {
			int activeCount = executor.getActiveCount();
			int FreeThead = pageSize - activeCount;
			int lastList = count-1-currentIndex;
			
			if (FreeThead > 0) {
				FreeThead = FreeThead >= lastList ?lastList:FreeThead;
				for (int i = 0; i < FreeThead; i++) {
					currentIndexMap.put(time, ++currentIndex );
					String lsh = mongodbBZ.get(currentIndex);
//					String lsh =(String) map.get("LSH");
					executor.execute(new MongoDBRunnable(lsh,wvehpicturemidDao,mongodbscztDao,storeinfoDao,vehisphotoiDao,mongdbGFSService));
				}
			}
		}
		
	}
	
	/**
	 * 关闭线程池
	 */
	public void shutdownExecutor(String time){
		logger.info("进入线程池关闭方法，线程池ID==" + time);
		ThreadPoolExecutor executor = executorMap.get(time);
		if (!executor.isShutdown() && executor.getActiveCount()==0) {
			logger.info(time + "，线程池关闭,准备导出数量："+ waitListMap.get(time).size() +",线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount());
			executor.shutdown();
		}
	}
	
	/**
	 * 获取线程池列表
	 * @return
	 */
	public Object taskList(){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (String time : taskMap.keySet()) {
			Map<String, String> map = taskMap.get(time);
			ThreadPoolExecutor executor = executorMap.get(time);
			List<String> mongodbBZ = wvehpicturemidDao.Mongodbsczt(map.get("start"),map.get("end"));
			int count = Integer.parseInt(map.get("total"))- mongodbBZ.size();
			map.put("complete", count +"");
			map.put("date",time);
			if (executor ==null || executor.isShutdown()) {
				map.put("status","执行结束");
			}else{
				map.put("status","正在运行");
			}
			list.add(map);
		}
		return list;
	}
}
