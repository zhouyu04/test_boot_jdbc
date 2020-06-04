package com.zzyy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Controller
@RequestMapping("/user")
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public String toLogin() {

        logger.info("toLogin");
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {

        String queryString = request.getQueryString();
        logger.info("queryString:" + queryString);
        String appId = request.getParameter("appId");
        String username = request.getParameter("username");
        String dbid = request.getParameter("dbid");
        String tenantid = request.getParameter("tenantid");

        request.setAttribute("appId",appId);
        request.setAttribute("username",username);
        request.setAttribute("dbid",dbid);
        request.setAttribute("tenantid",tenantid);


        logger.info("index");
        return "index";
    }

    @RequestMapping(value = "/toChat", method = RequestMethod.GET)
    public String toChat() {
        logger.info("toChat");
        return "chat";
    }

    @RequestMapping(value = "/toExpressIndex", method = RequestMethod.GET)
    public String toExpressIndex() {
        logger.info("toExpressIndex");
        return "expressIndex";
    }

    /*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping("/upload")
    public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        long startTime = System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String path = "E:/springUpload" + file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }

            }

        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        return "/success";
    }
}
