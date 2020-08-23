package com.cheguansuo.business.dao;

import com.cheguansuo.business.entity.Mongodbsczt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MongodbscztDao {

    @Select("select count(*) count from VEH_IS_PHOTO aa where EXISTS(select 1 from MONGODBSCZT bb where lsh between #{start} and #{end} and aa.lsh=bb.lsh)")
    Integer getCountBy(@Param("start") String start, @Param("end") String end);

    @Select("select count(*) count from VEH_IS_PHOTO aa where EXISTS(select 1 from MONGODBSCZT bb where lsh between #{start} and #{end} and sczt=1 and aa.lsh=bb.lsh)")
    Integer getfinalCountBy(@Param("start") String start, @Param("end") String end);

    @Update("update MONGODBSCZT set sczt=1 where lsh=#{lsh}")
    Integer updateEntity(Mongodbsczt mongodbsczt);

}
