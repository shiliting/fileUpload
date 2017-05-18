package com.slt.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @RequestMapping("/")
    public String home() throws Exception {
        String text="abcd";
        InputStream is = new ByteArrayInputStream(text.getBytes());
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
