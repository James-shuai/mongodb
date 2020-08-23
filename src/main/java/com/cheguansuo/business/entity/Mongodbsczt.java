package com.cheguansuo.business.entity;


import java.util.Date;


public class Mongodbsczt
{
  private String lsh; //流水号
  private Date gxsj; //更新时间
  private Integer sczt; // 上传状态，0=未上传 1=已上传
  private String scbz; //上传备注
  
  public String getLsh()
  {
    return this.lsh;
  }
  
  public void setLsh(String lsh)
  {
    this.lsh = lsh;
  }
  
  public Date getGxsj()
  {
    return this.gxsj;
  }
  
  public void setGxsj(Date gxsj)
  {
    this.gxsj = gxsj;
  }
  
  public Integer getSczt()
  {
    return this.sczt;
  }
  
  public void setSczt(Integer sczt)
  {
    this.sczt = sczt;
  }
  
  public String getScbz()
  {
    return this.scbz;
  }
  
  public void setScbz(String scbz)
  {
    this.scbz = scbz;
  }

@Override
public String toString() {
	return "Mongodbsczt [lsh=" + lsh + ", gxsj=" + gxsj + ", sczt=" + sczt
			+ ", scbz=" + scbz + "]";
}
  
}
