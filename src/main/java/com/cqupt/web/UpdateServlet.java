package com.cqupt.web;

import com.cqupt.pojo.Brand;
import com.cqupt.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    //初始化service对象
    BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理POST请求的乱码问题
        req.setCharacterEncoding("utf-8");
        //获取参数
        int id = Integer.parseInt(req.getParameter("id"));
        String brandName = req.getParameter("brandName");
        String companyName = req.getParameter("companyName");
        int ordered = Integer.parseInt(req.getParameter("ordered"));
        String description = req.getParameter("description");
        int status = Integer.parseInt(req.getParameter("status"));
        //String statusStr = req.getParameter("statusStr");

        //封装Brand对象
        Brand brand = new Brand(id,brandName,companyName,ordered,description,status);
        //调用Service方法
        brandService.update(brand);


        //保存到request域
        req.setAttribute("brand",brand);
        //转发到SelectAllServlet
        req.getRequestDispatcher("/selectAllServlet").forward(req,resp);





    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
    public void sellectAll(){

    }
}
