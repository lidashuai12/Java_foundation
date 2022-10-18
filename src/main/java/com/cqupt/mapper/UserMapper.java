package com.cqupt.mapper;

import com.cqupt.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /**
     * 登陆，通过用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User select(@Param("username") String username, @Param("password") String password);


    /**
     * 注册，根据用户名查询数据库中用户名是否已经存在
     * @param username
     * @return
     */
    @Select("select * from tb_user where username = #{username}")
    User selectByUsername(@Param("username") String username);


    /**
     * 注册，向数据库添加用户信息
     * @param user
     */
    @Insert("insert into tb_user values (null,#{username},#{password})")
    void add(User user);

}
