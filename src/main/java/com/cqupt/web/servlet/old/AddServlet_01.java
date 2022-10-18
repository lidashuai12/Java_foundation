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
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet_01")
public class AddServlet_01 extends HttpServlet {
    //新建service对象
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}





