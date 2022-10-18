package com.cqupt.service.impl;

        import com.cqupt.mapper.BrandMapper;
        import com.cqupt.pojo.Brand;
        import com.cqupt.pojo.PageBean;
        import com.cqupt.service.BrandService;
        import com.cqupt.util.SqlSessionFactoryUtils;
        import org.apache.ibatis.session.SqlSession;
        import org.apache.ibatis.session.SqlSessionFactory;

        import java.util.List;

/**
 * 查询所有
 */
public class BrandServiceImpl extends BrandService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Brand> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }


    /**
     * 新增品牌
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 批量删除
     * @param ids 传入的需要删除的id
     */
    @Override
    public void deleteByIds(int[] ids){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 分页查询,将查询结果封装进PageBean中
     * @param currentPage 当前页
     * @param pageSize 当前页查询条数
     * @return
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //计算查询起点索引，从上一页最后一条数据的后面开始查
        int begin = (currentPage - 1) * pageSize;
        //计算当前页的数据条目数
        int size = pageSize;

        //查询当前页数据
        List<Brand> rows = mapper.selectByPage(begin, size);
        System.out.println(rows);
        //查询总记录数
        int count = mapper.selectTotalCount();


        //封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(count);

        sqlSession.close();

        return pageBean;


    }


    /**
     * 分页条件查询,将查询结果封装进PageBean中
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //计算查询起点索引，从上一页最后一条数据的后面开始查
        int begin = (currentPage - 1) * pageSize;
        //计算当前页的数据条目数
        int size = pageSize;

        //模糊查询，处理一下输入信息：比如查询框输入华为，要改为%华为%
        //模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0){
            brand.setBrandName("%"+brandName+"%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0){
            brand.setCompanyName("%"+companyName+"%");
        }



        //查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin,size,brand);
        System.out.println(rows);
        //查询总记录数
        int count = mapper.selectTotalCountByCondition(brand);
        System.out.println(count);

        //封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(count);

        sqlSession.close();

        return pageBean;
    }

    /**
     * 修改信息
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(brand);
        sqlSession.commit();
        System.out.println("修改成功！");
        sqlSession.close();
    }

    /**
     * 删除某一条
     * @param id
     */
    public void deleteById(int id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
