package com.cqupt.web.servlet.old;

import com.cqupt.pojo.Brand;
import com.cqupt.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    //新建service对象
    private BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理POST请求乱码问题
        req.setCharacterEncoding("utf-8");
        //获取参数
        String brandName = req.getParameter("brandName");
        String companyName = req.getParameter("companyName");
        Integer ordered = Integer.parseInt(req.getParameter("ordered"));
        String description = req.getParameter("description");
        Integer status = Integer.parseInt(req.getParameter("status"));

        //封装Brand对象
        Brand brand = new Brand(null,brandName,companyName,ordered,description,status);
        //调用service层,添加
        brandService.add(brand);

        //将转发到SelectAllServlet，展示修改后的数据库内容
        req.getRequestDispatcher("/selectAllServlet").forward(req,resp);




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
