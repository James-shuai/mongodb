package com.cheguansuo.business.entity;


import java.util.Date;


public class VEH_IS_PHOTO1801
{
  private VEH_PHOTOID veh_photoid;

  private byte[] zp;
  private String jyw;
  private Date jlsj;
  
 
  
  public VEH_IS_PHOTO1801() {
	super();
}

public byte[] getZp()
  {
    return this.zp;
  }
  
  public void setZp(byte[] zp)
  {
    this.zp = zp;
  }
  
  public String getJyw()
  {
    return this.jyw;
  }
  
  public void setJyw(String jyw)
  {
    this.jyw = jyw;
  }
  
  public Date getJlsj()
  {
    return this.jlsj;
  }
  
  public void setJlsj(Date jlsj)
  {
    this.jlsj = jlsj;
  }
	public String getLsh() {
		return veh_photoid.getLsh();
	}
	public String getZpzl() {
		return veh_photoid.getZpzl();
	}
	public String getZpxh() {
		return veh_photoid.getZpxh();
	}
}
