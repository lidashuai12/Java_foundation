package com.cqupt.service;

import com.cqupt.mapper.BrandMapper;
import com.cqupt.pojo.Brand;
import com.cqupt.pojo.PageBean;
import com.cqupt.service.impl.BrandServiceImpl;
import com.cqupt.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     * @return
     */
    public List<Brand> selectAll(){
       return null;
    }

    /**
     * 添加
     * @param brand
     */
    public void add(Brand brand){
    }

    /**
     * 回显数据，根据id查询
     * @param id
     * @return
     */
    public Brand selectById(int id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = mapper.selectById(id);
        sqlSession.close();
        return brand;
    }

/*    *//**
     * 修改信息
     * @param brand
     *//*
    public void update(Brand brand){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }*/

    /**
     * 批量删除
     * @param ids
     */
    public void deleteByIds(int[] ids){}

    /**
     * 分页查询，将页面中的数据封装进PageBean中
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Brand> selectByPage(int currentPage,int pageSize) {
        return null;
    }


    /**
     * 分页条件查询
     *
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        return null;
    }


    /**
     * 修改信息
     * @param brand
     */
    public void update(Brand brand){};


    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(int id){}

}
