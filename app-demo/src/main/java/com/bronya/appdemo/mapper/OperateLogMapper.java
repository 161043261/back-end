package com.bronya.appdemo.mapper;

import com.bronya.appdemo.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    @Insert("insert into operate_log (operate_user, operate_time, class_name, method_name, args, return_value, benchmark_time)"
            + "values (#{operateUser}, #{operateTime}, #{className}, #{methodName}, #{args}, #{returnValue}, #{benchmarkTime})")
    int insertOperateLog(OperateLog OperateLog);
}
