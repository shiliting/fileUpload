package com.slt.upload;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;


import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;

import static java.awt.Color.white;
import static java.awt.SystemColor.text;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@RestController
@EnableAutoConfiguration
@ComponentScan("com.slt.upload")
public class UploadController {
    /**
     * for upload
     */
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        return new CommonsMultipartResolver();
//    }
    @Autowired
    StaticResourceService staticResourceService;
    @RequestMapping(value = "/fileupload",method = RequestMethod.POST)
    public String home(HttpServletRequest request) throws Exception {
        InputStream is =null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multiRequest=null;
        if(multipartResolver.isMultipart(request)){

            multiRequest = (MultipartHttpServletRequest)request;
            Map<String, MultipartFile> map=multiRequest.getFileMap();
            Collection<MultipartFile> multipartFiles=map.values();
            for(MultipartFile m:multipartFiles){
                is=m.getInputStream();
            }
        }
        String fileName=StaticResourceService.IMG+ UUID.randomUUID().toString() + ".docx";
        staticResourceService.uploadResource(fileName,is);
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
