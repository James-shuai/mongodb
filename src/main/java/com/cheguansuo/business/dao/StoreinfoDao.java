package com.cheguansuo.business.dao;

import com.cheguansuo.business.entity.Veh_StoreinfoEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreinfoDao {

    @Insert("insert into VEH_IS_MONGO_FILE_STOREINFO (filename, filetype, gxsj, lsh, wjxh, wjzl, uuid) values(#{filename},#{filetype},#{gxsj},#{lsh},#{wjxh},#{wjzl},#{uuid})")
    Integer saveEntity(Veh_StoreinfoEntity veh);


    @Delete("delete from VEH_IS_MONGO_FILE_STOREINFO where lsh=#{lsh} and wjzl=#{wjzl} and wjxh=#{wjxh}")
    Integer deleteByLSHInfo(Veh_StoreinfoEntity veh);


}
