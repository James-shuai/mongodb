package com.cheguansuo.business.dao;

import com.cheguansuo.business.entity.VEH_IS_PHOTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VehisphotoiDao {

    @Select("select from VEH_IS_PHOTO1801 where lsh = #{lsh} order by zpzl")
    List<VEH_IS_PHOTO> list103AllBylsh(@Param("lsh") String lsh);


    @Select("select * from VEH_IS_PHOTO where lsh = #{lsh} order by zpzl")
    List<VEH_IS_PHOTO> list(@Param("lsh") String lsh);




    @Select("select * from VEH_IS_PHOTO where lsh = #{lsh} and zpzl = #{zpzl}  and zpxh = #{zpxh}")
    VEH_IS_PHOTO listcc(@Param("lsh") String lsh, @Param("zpzl") String zpzl, @Param("zpxh") String zpxh);

}
