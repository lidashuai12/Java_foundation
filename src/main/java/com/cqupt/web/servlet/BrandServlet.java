package com.cqupt.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cqupt.pojo.Brand;
import com.cqupt.pojo.PageBean;
import com.cqupt.service.BrandService;
import com.cqupt.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();

    /**
     * 查询所有
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用service查询
        List<Brand> brands = brandService.selectAll();
        //将数据转换为JSON格式 序列化
        String brands_json = JSON.toJSONString(brands);
        //响应数据,这里设置格式为text/json
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(brands_json);

    }

    /**
     * 添加品牌
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //处理POST请求乱码问题
        req.setCharacterEncoding("utf-8");
        //获取参数,通过ajax发出的请求，无法用request.getParameter()得到
        //获取请求体的数据
        BufferedReader br = req.getReader();
        String params = br.readLine();

        //将JSON字符串转为Java对象
        Brand brand = JSON.parseObject(params, Brand.class);
        System.out.println(brand);
        //调用service层,添加
        brandService.add(brand);
        //响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取前台传给的JSON数据
        BufferedReader br = req.getReader();
        String brands_json = br.readLine();
        //将JSON字符串转换为数组
        int[] ids = JSON.parseObject(brands_json,int[].class);
        brandService.deleteByIds(ids);
        //成功删除返回true
        resp.getWriter().write("success");
        System.out.println("删除成功" + Arrays.toString(ids));

    }


    /**
     * 分页查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取前台的当前页码，还有每页的数据条目数  url?currentPage=1&pageSize=5
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        //转换为int型数据
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);


        //1.调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage,pageSize);
        //将数据转换为JSON格式 序列化
        String pageBean_json = JSON.toJSONString(pageBean);
        //响应数据,这里设置格式为text/json
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(pageBean_json);



    }

    /**
     * 分页条件查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //解决POST请求乱码问题
        req.setCharacterEncoding("utf-8");
        //获取前台的当前页码，还有每页的数据条目数  url?currentPage=1&pageSize=5
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        //转换为int型数据
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取要查询的内容
        BufferedReader br = req.getReader();
        String s = br.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        System.out.println(brand.toString());

        //1.调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);
        //将数据转换为JSON格式 序列化
        String pageBean_json = JSON.toJSONString(pageBean);
        System.out.println(pageBean_json);
        //响应数据,这里设置格式为text/json
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(pageBean_json);



    }


    /**
     * 修改信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        req.setCharacterEncoding("UTF-8");
        BufferedReader br = req.getReader();
        String s = br.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.update(brand);
        resp.getWriter().write("success");

    }


    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取参数id
        req.setCharacterEncoding("utf-8");
        BufferedReader br = req.getReader();
        String s = br.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        try {
            int id = brand.getId();
            brandService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("删除成功！");
        resp.getWriter().write("success");

    }


}
