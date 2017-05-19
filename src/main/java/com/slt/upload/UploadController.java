package com.slt.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import static java.awt.SystemColor.text;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@RestController
@EnableAutoConfiguration
@ComponentScan("com.slt.upload")
public class UploadController {
    @Autowired
    StaticResourceService staticResourceService;
    @RequestMapping(value = "/fileupload",method = RequestMethod.POST)
    public String home(HttpServletRequest request) throws Exception {
//        String text="abcd";
//        Map<String,String[]> map=request.getParameterMap();
        InputStream is =null;



        staticResourceService.uploadResource(StaticResourceService.IMG,is);
        return "Hello World!";
    }
    @RequestMapping("/del")
    public String del() throws Exception {
        staticResourceService.deleteResource(StaticResourceService.IMG);


        return "del";
    }
    public static void main(String[] args) throws Exception{

        SpringApplication.run(UploadController.class, args);
    }
}
