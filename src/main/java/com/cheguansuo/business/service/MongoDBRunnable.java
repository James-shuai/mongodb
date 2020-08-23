package com.cheguansuo.business.service;

import com.cheguansuo.business.dao.MongodbscztDao;
import com.cheguansuo.business.dao.StoreinfoDao;
import com.cheguansuo.business.dao.VehisphotoiDao;
import com.cheguansuo.business.dao.WvehpicturemidDao;
import com.cheguansuo.business.entity.MongoEntity;
import com.cheguansuo.business.entity.Mongodbsczt;
import com.cheguansuo.business.entity.VEH_IS_PHOTO;
import com.cheguansuo.business.entity.Veh_StoreinfoEntity;
import com.cheguansuo.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;


public class MongoDBRunnable implements Runnable {
	
	private WvehpicturemidDao wvehpicturemidDao;
	private MongodbscztDao mongodbscztDao;
	private StoreinfoDao storeinfoDao;
	private VehisphotoiDao vEH_IS_PHOTO1801Dao;
	private MongdbGFSService mongdbGFSService;

	private String threaName;
	public static Logger logger = LoggerFactory.getLogger("mongodbLog");
	
	public MongoDBRunnable(){
	}
	
	public MongoDBRunnable(String threaName, WvehpicturemidDao wvehpicturemidDao, MongodbscztDao mongodbscztDao, StoreinfoDao storeinfoDao, VehisphotoiDao vEH_IS_PHOTO1801Dao, MongdbGFSService mongdbGFSService){
		this.threaName = threaName;
		this.wvehpicturemidDao = wvehpicturemidDao;
		this.mongodbscztDao = mongodbscztDao;
		this.storeinfoDao = storeinfoDao;
		this.vEH_IS_PHOTO1801Dao = vEH_IS_PHOTO1801Dao;
		this.mongdbGFSService = mongdbGFSService;
	}

	public void run() {
		logger.info(threaName + "，开始时间："+ DateUtil.toDateTimeStr19(new Date()));
		try {
//			MongdbGFS mongdbGFS = new MongdbGFS();
			
			String lsh = threaName.split("_")[0];
			Mongodbsczt sczt = wvehpicturemidDao.getSczt(lsh);
			
			if (sczt != null && sczt.getSczt().intValue() != 1) {
				List<VEH_IS_PHOTO> listAllBylsh = this.vEH_IS_PHOTO1801Dao.list(lsh);
				int i = 0;
				for (VEH_IS_PHOTO temp : listAllBylsh) {
					//保存至mongodb
					MongoEntity mongoEntity = new MongoEntity(temp.getLsh() + temp.getZpzl()+"_"+temp.getZpxh() + ".jpg", temp.getLsh(),temp.getZpzl(), temp.getZp(),temp.getZpxh());
					mongdbGFSService.deleteFile(mongoEntity.getName());
					String saveFile = mongdbGFSService.saveFile(mongoEntity);
					
					//插入中间表
					Veh_StoreinfoEntity veh_StoreinfoEntity = new Veh_StoreinfoEntity(saveFile,temp.getLsh(),temp.getZpzl(), Integer.valueOf(temp.getZpxh()),mongoEntity.getName());
					storeinfoDao.deleteByLSHInfo(veh_StoreinfoEntity);
					storeinfoDao.saveEntity(veh_StoreinfoEntity);
					i++;
				}
				
				//修改照片上传状态
				sczt.setGxsj(new Date());
				sczt.setSczt(Integer.valueOf(1));
				if (i != listAllBylsh.size()) {
					sczt.setScbz("应上传数量:" + listAllBylsh.size() + "，实际上传数量:" + i);
					sczt.setSczt(Integer.valueOf(2));
				}
				
				this.mongodbscztDao.updateEntity(sczt);
				listAllBylsh = null;
				System.gc();
			}
//			mongdbGFS.close();
			mongdbGFSService = null;
		} catch (Exception e) {
			logger.info(threaName + "，发生异常："+e.getMessage());
			e.printStackTrace();
		}
		
		logger.info(threaName + "，结束时间："+ DateUtil.toDateTimeStr19(new Date()));
	}
	

}
