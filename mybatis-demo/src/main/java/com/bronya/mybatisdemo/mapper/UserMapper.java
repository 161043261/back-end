package com.bronya.mybatisdemo.mapper;

import com.bronya.mybatisdemo.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> getUserList();

    User getUserByUsername(String username);

    List<User> getUserByUsernamePassword(String username, String password); // returnType: both User and List<User> is ok

    User getUserByMap(Map<String, Object> map);

    int insertUser(User user); // returnValue: rowCount (number of rows affected)

    // @Param(keyName)
    User getUserByParam(@Param("p1") String username, @Param("p2") String password);

    int getUserCount();

    Map<String, Object> getMapById(@Param("id") Integer id);

    // List<User> to List<Map<String, Object>>
    List<Map<String, Object>> getMapList();

    // key = user.id
    // List<User> to Map<Integer, Object>
    @MapKey("id")
    Map<Integer, Object> getMap();

    List<User> fuzzyQuery(@Param("username") String username);

    int batchDelete(@Param("ids") String ids);

    List<User> dynamicTableName(@Param("tableName") String tableName);

    void primaryKeyRetrieval(User user);
}
