package com.cheguansuo.business.entity;


import java.util.Date;


public class VEH_IS_PHOTO {
    private String lsh; //流水号
    private String zpzl; //照片种类
    private String zpxh; //照片序号

    private byte[] zp; //照片
    private String jyw;
    private Date jlsj;


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

    public byte[] getZp() {
        return zp;
    }

    public void setZp(byte[] zp) {
        this.zp = zp;
    }

    public String getJyw() {
        return jyw;
    }

    public void setJyw(String jyw) {
        this.jyw = jyw;
    }

    public Date getJlsj() {
        return jlsj;
    }

    public void setJlsj(Date jlsj) {
        this.jlsj = jlsj;
    }
}
