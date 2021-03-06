package com.zzyy.mapper;

import com.zzyy.entity.Express;
import com.zzyy.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User queryUser(@Param("username") String username, @Param("password") String password);

    void addExpress(Express express);

    void deleteExpress(Long id);

    List<Express> findExpress(long id);

    long findExpressCount(long l);
}