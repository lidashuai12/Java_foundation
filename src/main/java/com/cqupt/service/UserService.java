package com.cqupt.service;

import com.cqupt.mapper.UserMapper;
import com.cqupt.pojo.User;
import com.cqupt.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 登陆方法
     * @param username
     * @param password
     * @return
     */
    public User login(String username,String password){
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用select方法
        User user = mapper.select(username,password);
        //释放资源
        sqlSession.close();
        return user;
    }

    /**
     * 注册方法
     * @param user
     * @return
     */
    public boolean register(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user_new = mapper.selectByUsername(user.getUsername());
        if (user.getPassword() != null && user_new == null ){
            //表示没被用过可以注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return user_new==null;
    }

    /**
     * ajax验证注册用户名
     * @param username
     * @return
     */
    public boolean register_ajax(String username){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user_ajax = mapper.selectByUsername(username);
        sqlSession.close();
        return user_ajax == null;
    }
}
