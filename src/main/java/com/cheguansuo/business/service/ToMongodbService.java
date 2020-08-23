package com.cheguansuo.business.service;


import com.cheguansuo.business.dao.MongodbscztDao;
import com.cheguansuo.business.dao.StoreinfoDao;
import com.cheguansuo.business.dao.VehisphotoiDao;
import com.cheguansuo.business.dao.WvehpicturemidDao;
import com.cheguansuo.business.entity.*;
import com.cheguansuo.util.DateUtil;
import com.cheguansuo.util.TaskQueueDuo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ToMongodbService {
	@Autowired
	private WvehpicturemidDao wvehpicturemidDao;
	@Autowired
	private MongodbscztDao mongodbscztDao;
	@Autowired
	private StoreinfoDao storeinfoDao;
	@Autowired
	private VehisphotoiDao vehisphotoiDao;
	
	public static Map<String, Map<ViewProgromEntity, List<TaskQueueDuo>>> taskMap = new HashMap<String, Map<ViewProgromEntity, List<TaskQueueDuo>>>();
	
	public void fourth(String start, String end){
		
		List<TaskQueueDuo> tasklist = new ArrayList<TaskQueueDuo>();
		Date date = new Date();
		String format = DateUtil.datetimeSDF19.format(date);
		ViewProgromEntity viewProgromEntity = new ViewProgromEntity();
		viewProgromEntity.setAllcount(mongodbscztDao.getCountBy(start, end));
		viewProgromEntity.setDate(format);
		viewProgromEntity.setCount(0);
		viewProgromEntity.setStartlsh(start);
		viewProgromEntity.setEndlsh(end);
		Map<ViewProgromEntity, List<TaskQueueDuo>> hashMap = new HashMap<ViewProgromEntity, List<TaskQueueDuo>>();
		hashMap.put(viewProgromEntity, tasklist);
		taskMap.put(format,hashMap);
		List<String> mongodbBZ = null;
		int w  = 1;
		mongodbBZ = this.wvehpicturemidDao.Mongodbsczt(start,end);
		TaskQueueDuo one = new TaskQueueDuo();
		TaskQueueDuo two = new TaskQueueDuo();
		TaskQueueDuo three = new TaskQueueDuo();
		tasklist.add(one);
		tasklist.add(two);
		tasklist.add(three);
		MongdbGFS mongdbGFS = new MongdbGFS();
		
		List<String> onelist = new ArrayList<String>();
		
		List<String> twolist = new ArrayList<String>();
		
		List<String> threelist = new ArrayList<String>();
		int i = mongodbBZ.size()/3;
		onelist = mongodbBZ.subList(0, i);
		twolist = mongodbBZ.subList(i, i * 2 );
		threelist = mongodbBZ.subList(i * 2, mongodbBZ.size());

	
		one.add(new bb(onelist,wvehpicturemidDao,mongodbscztDao,one,storeinfoDao,vehisphotoiDao));
		two.add(new bb(twolist,wvehpicturemidDao,mongodbscztDao,two,storeinfoDao,vehisphotoiDao));
		three.add(new bb(threelist,wvehpicturemidDao,mongodbscztDao,three,storeinfoDao,vehisphotoiDao));
		mongodbBZ = null;
		System.gc();
		
	}
	
	public List<ViewProgromEntity> list(){
		List<ViewProgromEntity> arrayList = new ArrayList<ViewProgromEntity>();
		for(String time : ToMongodbService.taskMap.keySet()){
			Map<ViewProgromEntity, List<TaskQueueDuo>> map = ToMongodbService.taskMap.get(time);
			
			for(ViewProgromEntity vpe : map.keySet()){
				vpe.setCount(mongodbscztDao.getfinalCountBy(vpe.getStartlsh(), vpe.getEndlsh()));
				arrayList.add(vpe);
			}
			
		}
		
		return arrayList;
	}
	
	
	public void stop(String date){
		Map<ViewProgromEntity, List<TaskQueueDuo>> map = ToMongodbService.taskMap.get(date);
		for(ViewProgromEntity vpe : map.keySet()){
			List<TaskQueueDuo> list = map.get(vpe);
			for(TaskQueueDuo task : list){
				task.stop();
			}
		}
		
		ToMongodbService.taskMap.remove(date);
		
	}
	
	class bb implements Runnable {
		
		private List<String> mongodbBZ;
		private WvehpicturemidDao wvehpicturemidDao;
		private MongodbscztDao mongodbscztDao;
		private TaskQueueDuo task;
		private StoreinfoDao storeinfoDao;
		private VehisphotoiDao vEH_IS_PHOTO1801Dao;
		int a = 0;
		public bb(List<String> mongodbBZ, WvehpicturemidDao wvehpicturemidDao, MongodbscztDao mongodbscztDao, TaskQueueDuo task, StoreinfoDao storeinfoDao, VehisphotoiDao vEH_IS_PHOTO1801Dao){
			this.mongodbBZ=mongodbBZ;
			this.wvehpicturemidDao = wvehpicturemidDao;
			this.mongodbscztDao = mongodbscztDao;
			this.storeinfoDao = storeinfoDao;
			this.task = task;
			this.vEH_IS_PHOTO1801Dao=vEH_IS_PHOTO1801Dao;;
		}
		

		public void ccMethod(){
			System.out.println("bbmethod-----------start");
			MongdbGFS mongdbGFS = new MongdbGFS();
			for (String lsh : mongodbBZ) {
//				String lsh = (String) bz.get("LSH");
			
				Mongodbsczt sczt = wvehpicturemidDao.getSczt(lsh);
				if ((sczt == null) || (sczt.getSczt().intValue() != 1)) {
					
					//List<VEH_IS_PHOTO> listAllBylsh = this.wvehpicturemidDao.listAllBylsh(lsh);
					List<VEH_IS_PHOTO> listAllBylsh = this.vEH_IS_PHOTO1801Dao.list(lsh);
					int i = 0;
					int wjzl = 0;
					int wjxh = 0;
					System.out.println(lsh + "-------------------"+ listAllBylsh.size());
					for (VEH_IS_PHOTO temp : listAllBylsh) {
						MongoEntity mongoEntity = new MongoEntity(temp.getLsh() + temp.getZpzl() + ".jpg", temp.getLsh(),temp.getZpzl(), temp.getZp(),temp.getZpxh());
					
						if(wjzl != Integer.valueOf(temp.getZpzl())){
							mongdbGFS.deleteFile(mongoEntity.getName());
							wjzl = Integer.valueOf(temp.getZpzl());
						}
						System.out.println(temp.getLsh() + "-------------------"+temp.getZpzl() + "-------------------"+ temp.getZpxh());
						String saveFile = mongdbGFS.saveFile(mongoEntity);
						Veh_StoreinfoEntity veh_StoreinfoEntity = new Veh_StoreinfoEntity(saveFile,temp.getLsh(),temp.getZpzl(), Integer.valueOf(temp.getZpxh()),mongoEntity.getName());
						this.storeinfoDao.saveEntity(veh_StoreinfoEntity);
						i++;
					}
					sczt.setGxsj(new Date());
					sczt.setSczt(Integer.valueOf(1));
					if (i != listAllBylsh.size()) {
						sczt.setScbz(":" + listAllBylsh.size()
								+ "==:" + i);
						sczt.setSczt(Integer.valueOf(2));
					}
					this.mongodbscztDao.updateEntity(sczt);
					a=1;
					listAllBylsh = null;
					System.gc();
				}
			}
			mongdbGFS.close();
			mongdbGFS = null;
		}
		
		public void run() {
			// TODO Auto-generated method stub
			if(a==0){
				ccMethod();
			}else if(a==1){
				task.stopTask();
			}
			 
		}
	}
	
}
