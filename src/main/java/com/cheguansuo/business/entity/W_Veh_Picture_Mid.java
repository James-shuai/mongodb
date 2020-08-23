package com.cheguansuo.business.entity;

import java.io.Serializable;
import java.util.Date;


public class W_Veh_Picture_Mid
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String lsh;
  private String cjry;
  private Date cjrq;
  private byte[] zp;
  private String zpzl;
  
  public W_Veh_Picture_Mid() {}
  
  public W_Veh_Picture_Mid(String lsh, String cjry, Date cjrq, byte[] zp, String zpzl)
  {
    this.lsh = lsh;
    this.cjry = cjry;
    this.cjrq = cjrq;
    this.zp = zp;
    this.zpzl = zpzl;
  }
  
  public String getLsh()
  {
    return this.lsh;
  }
  
  public void setLsh(String lsh)
  {
    this.lsh = lsh;
  }
  
  public String getCjry()
  {
    return this.cjry;
  }
  
  public void setCjry(String cjry)
  {
    this.cjry = cjry;
  }
  
  public Date getCjrq()
  {
    return this.cjrq;
  }
  
  public void setCjrq(Date cjrq)
  {
    this.cjrq = cjrq;
  }
  

  public byte[] getZp()
  {
    return this.zp;
  }
  
  public void setZp(byte[] zp)
  {
    this.zp = zp;
  }
  
  public String getZpzl()
  {
    return this.zpzl;
  }
  
  public void setZpzl(String zpzl)
  {
    this.zpzl = zpzl;
  }
  
  public String toString()
  {
    return "zp==" + toHexString(this.zp);
  }
  
  public static String toHexString(byte[] byteArray)
  {
    if ((byteArray == null) || (byteArray.length < 1)) {
      throw new IllegalArgumentException("this byteArray must not be null or empty");
    }
    StringBuilder hexString = new StringBuilder();
    for (int i = 0; i < byteArray.length; i++)
    {
      if ((byteArray[i] & 0xFF) < 16) {
        hexString.append("0");
      }
      hexString.append(Integer.toHexString(0xFF & byteArray[i]));
    }
    return hexString.toString().toLowerCase();
  }
}
