package cn.bptop.device.dao;

import cn.bptop.device.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper
{
    void addUser(@Param("userId") String userId, @Param("ddUserId") String ddUserId, @Param("ddName") String ddName);

    User findUser(@Param("uuserId") String uuserId, @Param("uuserName") String uuserName);



}
