package com.cqupt.web.servlet.old;

import com.cqupt.pojo.Brand;
import com.cqupt.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    //创建service对象
    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用BrandService完成查询
        List<Brand> brands = brandService.selectAll();
        //存入request域中
        req.setAttribute("brands", brands);
        System.out.println(brands.toString());
        //转发到brand.jsp
        req.getRequestDispatcher("/brand-json.html").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
