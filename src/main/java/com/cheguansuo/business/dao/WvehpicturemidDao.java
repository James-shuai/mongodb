package com.cheguansuo.business.dao;

import com.cheguansuo.business.entity.Mongodbsczt;
import com.cheguansuo.business.entity.VEH_IS_PHOTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WvehpicturemidDao {

    @Select("select from VEH_IS_PHOTO where to_char(jlsj , 'yyyy-mm-dd') = #{data}")
    List<VEH_IS_PHOTO> listAll(@Param("data") String data);




    @Select("select lsh from MONGODBSCZT where lsh between '${start}' and '${end}' and sczt=0 order by lsh")
    List<String> Mongodbsczt(@Param("start") String start, @Param("end") String end);

    @Select("select from VEH_IS_PHOTO103 where lsh = #{lsh} order by zpzl")
    List<VEH_IS_PHOTO> list103AllBylsh(@Param("lsh") String lsh);




    @Select("select * from MONGODBSCZT where lsh = #{lsh}")
    Mongodbsczt getSczt(@Param("lsh") String lsh);

    @Update("update MongodaBz set scbz=1 where lsh=#{lsh}")
    Integer upbz(@Param("lsh") String lsh);


}
