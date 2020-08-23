package com.cheguansuo.business.entity;

public class MongoEntity
{
  private String name; //照片文件名
  private String lsh; //流水号
  private String zpzl; //照片种类
  private String zpxh; //照片序号
  private byte[] zp;
  
  public String toString()
  {
    return "MongoEntity [name=" + this.name + ", lsh=" + this.lsh + ", zpzl=" + this.zpzl + "]";
  }
  
  public MongoEntity() {}
  
  public MongoEntity(String name, String lsh, String zpzl, byte[] zp, String zpxh)
  {
    this.name = name;
    this.lsh = lsh;
    this.zpzl = zpzl;
    this.zp = zp;
    this.zpxh = zpxh;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getLsh()
  {
    return this.lsh;
  }
  
  public void setLsh(String lsh)
  {
    this.lsh = lsh;
  }
  
  public String getZpzl()
  {
    return this.zpzl;
  }
  
  public void setZpzl(String zpzl)
  {
    this.zpzl = zpzl;
  }
  
  public byte[] getZp()
  {
    return this.zp;
  }
  
  public void setZp(byte[] zp)
  {
    this.zp = zp;
  }

public String getZpxh() {
	return zpxh;
}

public void setZpxh(String zpxh) {
	this.zpxh = zpxh;
}
  
  
}
