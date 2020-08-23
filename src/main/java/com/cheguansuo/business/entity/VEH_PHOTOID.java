package com.cheguansuo.business.entity;

import java.io.Serializable;

public class VEH_PHOTOID implements Serializable {
	
	private String lsh; //流水号
	private String zpzl; //照片种类
	private String zpxh; //照片序号
	
	public VEH_PHOTOID() {
		super();
	}
	public VEH_PHOTOID(String lsh, String zpzl, String zpxh) {
		super();
		this.lsh = lsh;
		this.zpzl = zpzl;
		this.zpxh = zpxh;
	}
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public String getZpzl() {
		return zpzl;
	}
	public void setZpzl(String zpzl) {
		this.zpzl = zpzl;
	}
	public String getZpxh() {
		return zpxh;
	}
	public void setZpxh(String zpxh) {
		this.zpxh = zpxh;
	}

	
}
