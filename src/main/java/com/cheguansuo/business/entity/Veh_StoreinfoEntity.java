package com.cheguansuo.business.entity;


import java.util.Date;


public class Veh_StoreinfoEntity {
	  private String uuid; //mongodb 中图片表id
	  private String lsh; //流水号
	  private String wjzl; //照片种类
	  private int wjxh; //照片序号
	  private String filename; //mongodb 中图片表name
	  private int filetype; //照片类型，默认1，可能为图片
	  private Date gxsj; //更新时间
	  
	  
	public Veh_StoreinfoEntity() {
		super();
	}
	public Veh_StoreinfoEntity(String uuid, String lsh, String wjzl, int wjxh,
                               String filename) {
		super();
		this.uuid = uuid;
		this.lsh = lsh;
		this.wjzl = wjzl;
		this.wjxh = wjxh;
		this.filename = filename;
		this.gxsj = new Date();
		this.filetype = 1;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public String getWjzl() {
		return wjzl;
	}
	public void setWjzl(String wjzl) {
		this.wjzl = wjzl;
	}
	public int getWjxh() {
		return wjxh;
	}
	public void setWjxh(int wjxh) {
		this.wjxh = wjxh;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getGxsj() {
		return gxsj;
	}
	public void setGxsj(Date gxsj) {
		this.gxsj = gxsj;
	}
	public int getFiletype() {
		return filetype;
	}
	public void setFiletype(int filetype) {
		this.filetype = filetype;
	}
	  
	  
}
