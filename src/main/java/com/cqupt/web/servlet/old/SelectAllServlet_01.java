package com.cqupt.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.cqupt.pojo.Brand;
import com.cqupt.service.BrandService;
import com.cqupt.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAllServlet_01")
public class SelectAllServlet_01 extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用service查询
        List<Brand> brands = brandService.selectAll();
        //将数据转换为JSON格式 序列化
        String brands_json = JSON.toJSONString(brands);
        //响应数据,这里设置格式为text/json
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(brands_json);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
